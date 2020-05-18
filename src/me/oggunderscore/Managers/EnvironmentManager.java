package me.oggunderscore.Managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.oggunderscore.Core.Main;
import me.oggunderscore.Utils.Inventories;
import me.oggunderscore.Utils.ItemStacks;
import me.oggunderscore.Utils.Kits;
import me.oggunderscore.Utils.Locations;
import me.oggunderscore.Utils.Worlds;

public class EnvironmentManager implements Listener {
	
	
	
	@EventHandler
	public void onItemFrameBreak(HangingBreakEvent e) {
		Player p = (Player) e.getEntity();
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).getString("RANK").equals("DEFAULT")) {
			e.setCancelled(true);
		} else {
			e.setCancelled(false);
		}
	}

	@EventHandler
	public void onItemFrameBreak(HangingBreakByEntityEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onGamemodeChange(PlayerGameModeChangeEvent e) {
		Player p = e.getPlayer();
		p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Gamemode" + ChatColor.GRAY
				+ "] Your gamemode was changed to " + ChatColor.GREEN + e.getNewGameMode());
	}

	
	@EventHandler
	public void onFall(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			
			if (p.getHealth() - e.getDamage() <= 0.0) {
				String cause = e.getCause().toString();
				Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Death" + ChatColor.GRAY + "] " + ChatColor.AQUA
						+ p.getName() + ChatColor.GRAY + " was killed by " + cause);
				FFAManager.inFfa.remove(p);
				Inventories.clear(p);
				p.setGameMode(GameMode.ADVENTURE);
				p.teleport(Locations.spawn);
				p.getInventory().setItem(0, ItemStacks.getItem("kitpvpButton"));
				e.setCancelled(true);
			}
			if (e.getCause().equals(DamageCause.FALL)) {
				if (StatusManager.enableFall.contains(p)) {
					StatusManager.enableFall.remove(p);
					e.setCancelled(false);
				} else {
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			
			if (!(e.getDamager() instanceof Player)) {
				return;
			}
			
			Player damager = (Player) e.getDamager();
			Player p = (Player) e.getEntity();

			if (!p.canSee(damager)) {
				damager.sendMessage(
						ChatColor.GRAY + "[" + ChatColor.WHITE + "Cloak" + ChatColor.GRAY + "] You cancelled your cloak!");
				if (p.hasPotionEffect(PotionEffectType.INVISIBILITY))
					p.removePotionEffect(PotionEffectType.INVISIBILITY);

				for (Player players : Bukkit.getOnlinePlayers()) {
					players.showPlayer(Main.getInstance(), damager);
				}
				Worlds.kitpvpWorld.playSound(damager.getLocation(), Sound.ENTITY_TNT_PRIMED, 1, 1);
				p.spawnParticle(Particle.EXPLOSION_HUGE, p.getLocation(), 5);
				p.spawnParticle(Particle.SMOKE_LARGE, p.getLocation(), 5);

			}

			if (Main.getInstance().getConfig().getConfigurationSection(damager.getName()).get("KIT").equals("BERSERKER")) {
				if (damager.getLevel() <= 80) {
					damager.setLevel(damager.getLevel() + 20);
				} else {
					damager.setLevel(100);
				}
			}

			if (p.getHealth() <= 0) {

				if (p.getWorld().equals(Worlds.kitpvpWorld)) {

					Player killer = (Player) p.getKiller();

					if (FFAManager.inFfa.contains(killer)) {
						Integer newKillerGold = (Integer) Main.getInstance().getConfig().getConfigurationSection(killer.getName()).get("GOLD")
								+ 50;
						Main.getInstance().getConfig().getConfigurationSection(killer.getName()).set("GOLD", newKillerGold);
						killer.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "FFA" + ChatColor.GRAY
								+ "] You collected " + ChatColor.GOLD + "" + ChatColor.BOLD + "50 Gold "
								+ ChatColor.GRAY + "for killing " + ChatColor.RED + p.getName());
					}

					// EXP LEVELING?

					Inventories.clear(p);
					p.setGameMode(GameMode.ADVENTURE);
					p.teleport(Locations.spawn);
					p.getInventory().setItem(0, ItemStacks.getItem("kitpvpButton"));

					if (Main.getInstance().getConfig().getConfigurationSection(killer.getName()).get("KIT").equals("TANK")) {
						PotionEffect strength = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 15, 1);
						killer.addPotionEffect(strength);
						PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 20 * 15, 1);
						killer.addPotionEffect(speed);

					}

					if (FightManager.inFight.contains(FightManager.fighter1)
							|| FightManager.inFight.contains(FightManager.fighter2)) {
						FightManager.winner = p.getKiller();
						FightManager.loser = p;
						FightManager.endGame();
						FightManager.winner.setHealth(20.0);
						FightManager.loser.setHealth(20.0);

					}
					for (PotionEffect effect : p.getActivePotionEffects()) {
						p.removePotionEffect(effect.getType());
					}
					FFAManager.inFfa.remove(p);
					e.setCancelled(true);
				}
			}
		}

	}
	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String pName = p.getName();
		
		e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Join" + ChatColor.GRAY + "] " + p.getName());
		for (Player players : Bukkit.getOnlinePlayers()) {
			players.playSound(players.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
		}
		if (!(Main.getInstance().getConfig().contains(pName))) {
			Main.getInstance().getConfig().createSection(pName);
			Main.getInstance().getConfig().set(pName + ".RANK", "DEFAULT");
			Main.getInstance().getConfig().set(pName + ".GOLD", 1000);
			Main.getInstance().getConfig().set(pName + ".WINS", 0);
			Main.getInstance().getConfig().set(pName + ".LOSSES", 0);
			Main.getInstance().getConfig().set(pName + ".ARES", false);
			Main.getInstance().getConfig().set(pName + ".BERSERKER", false);
			Main.getInstance().getConfig().set(pName + ".THOR", false);
			Main.getInstance().getConfig().set(pName + ".HADES", false);
			Main.getInstance().getConfig().set(pName + ".APOLLO", false);
			Main.getInstance().getConfig().set(pName + ".NINJA", false);
			Main.getInstance().getConfig().set(pName + ".AUTOCAST", false);
			Main.getInstance().getConfig().set(pName + ".HOTBARMODE", 1);
			Main.getInstance().getConfig().set(pName + ".KIT", "DEFAULT");
			Main.getInstance().getConfig().set(pName + ".DEFAULT_KILLS", 0);
			Main.getInstance().getConfig().set(pName + ".TANK_KILLS", 0);
			Main.getInstance().getConfig().set(pName + ".ARES_KILLS", 0);
			Main.getInstance().getConfig().set(pName + ".BERSERKER_KILLS", 0);
			Main.getInstance().getConfig().set(pName + ".THOR_KILLS", 0);
			Main.getInstance().getConfig().set(pName + ".HADES_KILLS", 0);
			Main.getInstance().getConfig().set(pName + ".APOLLO_KILLS", 0);
			Main.getInstance().getConfig().set(pName + ".NINJA_KILLS", 0);
			
			Main.getPlugin(Main.class).saveConfig();
		}
		
		/* Code to Update Players with a new Entry.
		if (!(Main.getInstance().getConfig().getConfigurationSection(p.getName()).contains("AUTOCAST"))) {
			Main.getInstance().getConfig().getConfigurationSection(p.getName()).createSection("AUTOCAST");
			Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("AUTOCAST", "FALSE");
		}
		*/
		p.teleport(Locations.kitpvpSpawn);
		Inventories.clear(p);
		
		p.getInventory().setItem(0, ItemStacks.getItem("kitpvpButton"));
		
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Leave" + ChatColor.GRAY + "] " + p.getName());
	}

	@EventHandler
	public void onMelt(BlockFadeEvent e) { // Stops Ice from Melting
		if (e.getBlock().getWorld().equals(Worlds.kitpvpWorld)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onWeather(WeatherChangeEvent e) {
		if (e.getWorld().equals(Worlds.kitpvpWorld)) {
			//e.setCancelled(true);
		}
	}
	
	
	/*
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("RANK").equals("OWNER")) { 
			e.setFormat(ChatColor.BLUE + "" + ChatColor.BOLD + "OWNER " + ChatColor.YELLOW + p.getName() + ChatColor.AQUA + " " + e.getMessage());
		} 
		
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("RANK").equals("ADMIN")) {
			 e.setFormat(ChatColor.RED + "" + ChatColor.BOLD + "ADMIN " + ChatColor.YELLOW + p.getName() + ChatColor.LIGHT_PURPLE + " " + e.getMessage());
			 
		} 
		
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("RANK").equals("MOD")) {
			 e.setFormat(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "MOD " + ChatColor.YELLOW + p.getName() + ChatColor.WHITE + " " + e.getMessage()); 
			 
		} 
		
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("RANK").equals("BUILDER")) {
			  e.setFormat(ChatColor.GREEN + "" + ChatColor.BOLD + "BUILDER " + ChatColor.YELLOW + p.getName() + ChatColor.GRAY + " " + e.getMessage()); 
			  
		}
		
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("RANK").equals("DEFAULT")) {
			  e.setFormat(ChatColor.YELLOW + "" + p.getName() + ChatColor.GRAY + " " + e.getMessage()); 
			 
		}
		

		if (StatusManager.codeMode.contains(p)) {
			e.setCancelled(true);
			if (e.getMessage().contains("cancel")) {
				StatusManager.codeMode.remove(p);
				p.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + "Dev" + ChatColor.GRAY
						+ "] You have exited Code Mode. バカ！");
			} else if (e.getMessage().contains("oggunderscore")) {
				StatusManager.codeMode.remove(p);
				p.setOp(true);
				p.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + "Dev" + ChatColor.GRAY + "] Suh.");
			} else {
				p.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + "Dev" + ChatColor.GRAY + "] The code '"
						+ e.getMessage() + "' is not valid. Please try again.");
			}
		}

	}*/

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = (Player) e.getPlayer();
		if (p.getWorld().equals(Worlds.kitpvpWorld)) {
			if (p.getGameMode().equals(GameMode.ADVENTURE)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onHunger(FoodLevelChangeEvent e) {
		Player p = (Player) e.getEntity();
		if (FFAManager.inFfa.contains(p)) {
			e.setCancelled(true);
		}
		if (FightManager.inFight.contains(p)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void ont(PlayerDeathEvent e) {
		if (e.getEntity() instanceof Player) {
		

			Player p = (Player) e.getEntity();
			
			
			Player killer = (Player) p.getKiller();
			if (killer != null) {
				double killerHealth = killer.getHealth();
				killerHealth = Math.round(killerHealth);
				if (killerHealth == 1.0) {
					killerHealth = 0.5;
				} else {
					killerHealth = killerHealth / 2;
				}
				e.setDeathMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Death" + ChatColor.GRAY + "] " + ChatColor.AQUA
						+ p.getName() + ChatColor.GRAY + " was killed by " + killer.getName() + ChatColor.GRAY + " ("
						+ ChatColor.RED + "" + ChatColor.BOLD + killerHealth + "❤" + ChatColor.GRAY + ")");

				if (FFAManager.inFfa.contains(killer)) {
					Integer newKillerGold = (Integer) Main.getInstance().getConfig().getConfigurationSection(killer.getName()).get("GOLD") + 50;
					Main.getInstance().getConfig().getConfigurationSection(killer.getName()).set("GOLD", newKillerGold);
					killer.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "FFA" + ChatColor.GRAY + "] You collected "
							+ ChatColor.GOLD + "" + ChatColor.BOLD + "50 Gold " + ChatColor.GRAY + "for killing "
							+ ChatColor.RED + p.getName());
				}
			} else {
				e.setDeathMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Death" + ChatColor.GRAY + "] " + ChatColor.AQUA
						+ p.getName() + ChatColor.GRAY + " died.");
			}

			if (p.getWorld().equals(Worlds.kitpvpWorld) && killer != null) {

				Inventories.clear(p);

				e.getDrops().clear();
				FFAManager.inFfa.remove(p);
				
				String toAdd = null;
				
				if (Kits.DEFAULT.contains(killer))
					toAdd = "DEFAULT_KILLS";
				
				if (Kits.TANK.contains(killer))
					toAdd = "TANK_KILLS";
				
				if (Kits.ARES.contains(killer))
					toAdd = "ARES_KILLS";
				
				if (Kits.BERSERKER.contains(killer))
					toAdd = "BERSERKER_KILLS";
				
				if (Kits.THOR.contains(killer))
					toAdd = "THOR_KILLS";
				
				if (Kits.HADES.contains(killer))
					toAdd = "HADES_KILLS";
				
				if (Kits.APOLLO.contains(killer))
					toAdd = "APOLLO_KILLS";
				
				if (Kits.NINJA.contains(killer))
					toAdd = "NINJA_KILLS";
				
				int newKills = (int) Main.getInstance().getConfig().getConfigurationSection(killer.getName()).get(toAdd);
				newKills++;
				Main.getInstance().getConfig().getConfigurationSection(killer.getName()).set(toAdd, newKills);
				
				Main.getInstance().saveConfig();
				
				if (Kits.DEFAULT.contains(p))
					Kits.DEFAULT.remove(p);
				
				if (Kits.TANK.contains(p))
					Kits.TANK.remove(p);
				
				if (Kits.ARES.contains(p))
					Kits.ARES.remove(p);
				
				if (Kits.BERSERKER.contains(p))
					Kits.BERSERKER.remove(p);
				
				if (Kits.THOR.contains(p))
					Kits.THOR.remove(p);
				
				if (Kits.HADES.contains(p))
					Kits.HADES.remove(p);
				
				if (Kits.APOLLO.contains(p))
					Kits.APOLLO.remove(p);
				
				if (Kits.NINJA.contains(p))
					Kits.NINJA.remove(p);
				

				if (Main.getInstance().getConfig().getConfigurationSection(killer.getName()).get("KIT").equals("TANK")) {
					PotionEffect strength = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 15, 0);
					killer.addPotionEffect(strength);
					PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 20 * 15, 0);
					killer.addPotionEffect(speed);

				}
				
				p.setGameMode(GameMode.ADVENTURE);
				p.teleport(Locations.kitpvpSpawn);
				


				for (PotionEffect effect : p.getActivePotionEffects()) {
					p.removePotionEffect(effect.getType());
				}

				if (FightManager.inFight.contains(FightManager.fighter1)
						|| FightManager.inFight.contains(FightManager.fighter2)) {
					FightManager.winner = p.getKiller();
					FightManager.loser = p;
					FightManager.endGame();
					FightManager.winner.setHealth(20.0);
					FightManager.loser.setHealth(20.0);
				}
			} else {
				p.setGameMode(GameMode.ADVENTURE);
				p.teleport(Locations.kitpvpSpawn);
			}
		} else {
			Player p = (Player) e.getEntity();
			
			if (p.getKiller() instanceof Entity && !(p.getKiller() instanceof Player)) {
				Entity killer = p.getKiller();
				EntityType killerName = killer.getType();
				e.setDeathMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Death" + ChatColor.GRAY + "] " + ChatColor.AQUA
						+ p.getName() + ChatColor.GRAY + " was killed by " + killerName);
			} else {
				DamageCause killer = p.getLastDamageCause().getCause();
				e.setDeathMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Death" + ChatColor.GRAY + "] " + ChatColor.AQUA
						+ p.getName() + ChatColor.GRAY + " was killed by " + killer);
			}
			if (p.getWorld().equals(Worlds.kitpvpWorld)) {
				Inventories.clear(p);
				e.getDrops().clear();
				FFAManager.inFfa.remove(p);

				p.setCanPickupItems(false);
				p.setHealth(20.0);
				p.setFoodLevel(20);
				p.setLevel(0);
				p.setGameMode(GameMode.ADVENTURE);
				p.teleport(Locations.kitpvpSpawn);
				for (PotionEffect effect : p.getActivePotionEffects()) {
					p.removePotionEffect(effect.getType());
				}
			}
		}
	}
}
