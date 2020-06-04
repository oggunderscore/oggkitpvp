package me.oggunderscore.Core;

import java.util.ArrayList;
import java.util.UUID;

import com.mysql.fabric.xmlrpc.base.Array;
import me.oggunderscore.Utils.*;
import me.oggunderscore.Utils.ActionBar;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.oggunderscore.Managers.EnvironmentManager;
import me.oggunderscore.Managers.FFAManager;
import me.oggunderscore.Managers.FightManager;
import me.oggunderscore.Managers.StatusManager;

@SuppressWarnings("deprecation")
public class Events implements Listener {

	static Configuration config = Main.getPlugin(Main.class).getConfig();

	public static final ArrayList<Object> cooldownEnlightment = new ArrayList<Object>();
	public static final ArrayList<Object> cooldownIgnite = new ArrayList<Object>();
	public static final ArrayList<Object> cooldownRegen = new ArrayList<Object>();
	public static final ArrayList<Object> cooldownEarthquake = new ArrayList<Object>();
	public static final ArrayList<Object> cooldownCorruptedOrb = new ArrayList<Object>();
	public static final ArrayList<Object> cooldownEscape = new ArrayList<Object>();
	public static final ArrayList<Object> cooldownDash = new ArrayList<Object>();
	public static final ArrayList<Object> cooldownBlink = new ArrayList<Object>();
	public static final ArrayList<Object> cooldownCloak = new ArrayList<Object>();

	public static ArrayList<FallingBlock> arr = new ArrayList<FallingBlock>();
	public static ArrayList<FallingBlock> rem = new ArrayList<FallingBlock>();
	
	public static int cdEnlightment = 13;
	public static int cdIgnite = 7;
	public static int cdRegen = 15;
	public static int cdEarthquake = 14;
	public static int cdCorruptedOrb = 7;
	public static int cdDash = 3;
	public static int cdEscape = 7;
	public static int cdBlink = 7;
	public static int cdCloak = 20;
	
	private Player thrower;


	private Main main;
	private ArrayList<UUID> uuids;
	public Events(Main main, ArrayList<UUID> uuids) {

		 this.uuids = uuids;
		this.main = main;
	}

	@EventHandler // @OddFoam, merge with Environment Manager join event?
	public void onJoin2(PlayerJoinEvent event) {

		Player player = event.getPlayer();

		UUID uuid = player.getUniqueId();

		if (uuids.contains(uuid)) {

		 new ActionBar(player, ChatColorChange.chat("&7You are in &9vanish &7mode. &7You are only visible to &6Mod+"), main, uuids);

		}


	}


	@EventHandler
	public void onPlayerPickup(EntityPickupItemEvent e) {
		
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			ItemStack item = e.getItem().getItemStack();
			if (item.getType() == Material.ENDER_EYE) {
				e.setCancelled(true);
				if (player == thrower) {
					e.setCancelled(true);
				} else {
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 8 * 20, 40));
					player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 8 * 20, 4));
					player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 8 * 20, 4));
					//player.damage(16);
				}
				World world = Worlds.kitpvpWorld;
				Location location = player.getLocation();
				world.playSound(location, Sound.ENTITY_ARROW_HIT, 1.0F, 1.0F);
				player.spawnParticle(Particle.LAVA, player.getLocation(), 5);
				e.getItem().remove();
			}
		}
		
	}

	@EventHandler
	public void switchEvent(PlayerItemHeldEvent e) {
		Player p = e.getPlayer();
		int newSlot = e.getNewSlot();
		int oldSlot = e.getPreviousSlot();
		ItemStack newStack = p.getInventory().getItem(newSlot);

		ItemStack Ignite = new ItemStack(Material.FIRE_CHARGE);
		ItemMeta im = Ignite.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Ignite");
		lore.add(ChatColor.YELLOW + "Right Click to set nearby");
		lore.add(ChatColor.YELLOW + "enemies on fire for 9 seconds!");
		im.setLore(lore);
		Ignite.setItemMeta(im);
		
		

		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("AUTOCAST").equals(true)) {
			if (!(newStack == null)) {

				if (newStack.getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.GREEN + "" + ChatColor.BOLD + "Cloak")) {
					if (cooldownCloak.contains(p)) {
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Cooldown" + ChatColor.GRAY + "] "
								+ ChatColor.GREEN + "" + ChatColor.BOLD + "Cloak " + ChatColor.WHITE
								+ "is still on cooldown!");
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
						p.getInventory().setHeldItemSlot(oldSlot);
					} else {

						PotionEffect invis = new PotionEffect(PotionEffectType.INVISIBILITY, 7 * 20, 0);
						p.addPotionEffect(invis);
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.WHITE + "Cloak" + ChatColor.GRAY
								+ "] You are now cloaked!");

						Worlds.kitpvpWorld.playSound(p.getLocation(), Sound.ENTITY_TNT_PRIMED, 1, 1);
						Worlds.kitpvpWorld.spawnParticle(Particle.EXPLOSION_HUGE, p.getLocation(), 2);
						Worlds.kitpvpWorld.spawnParticle(Particle.SMOKE_LARGE, p.getLocation(), 2);
						p.getInventory().setHeldItemSlot(oldSlot);

						// maybe
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {

							@Override
							public void run() {
								for (Player players : Bukkit.getOnlinePlayers()) {
									players.showPlayer(Main.getInstance(), p);
								}

								p.sendMessage(ChatColor.GRAY + "[" + ChatColor.WHITE + "Cloak" + ChatColor.GRAY
										+ "] You are no longer cloaked!");
								Worlds.kitpvpWorld.playSound(p.getLocation(), Sound.ENTITY_TNT_PRIMED, 1, 1);
								Worlds.kitpvpWorld.spawnParticle(Particle.EXPLOSION_HUGE, p.getLocation(), 2);
								Worlds.kitpvpWorld.spawnParticle(Particle.SMOKE_LARGE, p.getLocation(), 2);
							}
						}, 20 * 7);

						for (Player players : Bukkit.getServer().getOnlinePlayers()) {
							players.hidePlayer(Main.getInstance(), p);
						}

						cooldownCloak.add(p);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
							public void run() {
								cooldownCloak.remove(p);
								p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Ability" + ChatColor.GRAY + "] "
										+ ChatColor.GREEN + "" + ChatColor.BOLD + "Cloak " + ChatColor.GRAY + "is "
										+ ChatColor.GREEN + "READY!");
							}
						}, cdCloak * 20);

					}
				}

				if (newStack.getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.RED + "" + ChatColor.BOLD + "ROAR")) {
					if (!(p.getLevel() == 100)) {
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Energy" + ChatColor.GRAY + "] "
								+ ChatColor.WHITE + "You do not have enough Energy to use " + ChatColor.RED + ""
								+ ChatColor.BOLD + "ROAR");
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
						p.getInventory().setHeldItemSlot(oldSlot);
					} else {
						p.setLevel(0);
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
						Worlds.kitpvpWorld.spawnParticle(Particle.CRIT, p.getLocation(), 5);
						Worlds.kitpvpWorld.spawnParticle(Particle.EXPLOSION_HUGE, p.getLocation(), 5);
						PotionEffect strength = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3 * 20, 2);
						p.addPotionEffect(strength);
						p.getInventory().setHeldItemSlot(oldSlot);
						for (Player players : Bukkit.getOnlinePlayers()) {
							if (p.getNearbyEntities(5.0, 5.0, 5.0).contains(players)) {
								players.damage(2.0);
								EnvironmentManager.damageList.put(players, p);
								players.playEffect(EntityEffect.HURT);
								Worlds.kitpvpWorld.spawnParticle(Particle.SPELL_INSTANT, p.getLocation(), 5);
								PotionEffect slow = new PotionEffect(PotionEffectType.SLOW, 5 * 20, 4);
								players.addPotionEffect(slow);
							}
						}

					}
				}
				if (newStack.getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.GREEN + "" + ChatColor.BOLD + "Quick Fix")) {
					if (!(p.getLevel() >= 40)) {
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Energy" + ChatColor.GRAY + "] "
								+ ChatColor.WHITE + "You do not have enough Energy to use " + ChatColor.GREEN + ""
								+ ChatColor.BOLD + "Quick Fix");
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
						p.getInventory().setHeldItemSlot(oldSlot);
					} else {
						p.setLevel(p.getLevel() - 40);
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CAT_PURR, 1, 1);
						Worlds.kitpvpWorld.spawnParticle(Particle.HEART, p.getLocation(), 5);
						p.getInventory().setHeldItemSlot(oldSlot);
						if ((p.getHealth() >= 16.0)) {
							p.setHealth(20.0);
						} else {
							p.setHealth(p.getHealth() + 4.0);
						}

					}
				}
				if (newStack.getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.RED + "" + ChatColor.BOLD + "Regeneration Flower")) {
					if (cooldownRegen.contains(p)) {
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Cooldown" + ChatColor.GRAY + "] "
								+ ChatColor.RED + "" + ChatColor.BOLD + "Regeneration Flower " + ChatColor.WHITE
								+ "is still on cooldown!");
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
						p.getInventory().setHeldItemSlot(oldSlot);
					} else {
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 1, 1);
						PotionEffect regen = new PotionEffect(PotionEffectType.REGENERATION, 5 * 20, 2);
						p.addPotionEffect(regen);
						Worlds.kitpvpWorld.spawnParticle(Particle.HEART, p.getLocation(), 5);
						p.getInventory().setHeldItemSlot(oldSlot);
						cooldownRegen.add(p);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {

							public void run() {
								cooldownRegen.remove(p);
								p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Ability" + ChatColor.GRAY + "] "
										+ ChatColor.RED + "" + ChatColor.BOLD + "Regeneration Flower " + ChatColor.GRAY
										+ "is " + ChatColor.GREEN + "READY!");
							}
						}, cdRegen * 20);

					}
				}
				if (newStack.getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.RED + "" + ChatColor.BOLD + "Ignite")) {
					if (cooldownIgnite.contains(p)) {
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Cooldown" + ChatColor.GRAY + "] "
								+ ChatColor.RED + "Ignite " + ChatColor.WHITE + "is still on cooldown!");
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
						p.getInventory().setHeldItemSlot(oldSlot);
					} else {
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_BLAZE_HURT, 1, 1);
						p.getWorld().playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 2);
						
						p.getInventory().setHeldItemSlot(oldSlot);

						for (Player players : Bukkit.getOnlinePlayers()) {
							if (p.getNearbyEntities(3.0, 3.0, 3.0).contains(players)) {
								players.setFireTicks(9 * 20);
								EnvironmentManager.damageList.put(players, p);
								Worlds.kitpvpWorld.spawnParticle(Particle.FLAME, p.getLocation(), 5);
								p.getWorld().playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
							}
						}

						// CD
						cooldownIgnite.add(p);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
							public void run() {
								cooldownIgnite.remove(p);
								p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Ability" + ChatColor.GRAY + "] "
										+ ChatColor.RED + "Ignite " + ChatColor.GRAY + "is " + ChatColor.GREEN
										+ "READY!");
							}
						}, cdIgnite * 20);
					}
				}
			}
		}

	}

	private void playEffect(final Item item) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
				if (item.isOnGround() && !item.isDead()) {
					Location location = item.getLocation();
					World world = item.getWorld();
					world.spawnParticle(Particle.LAVA, location, 5);
				}
			}
		}, 0L, 40L);
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		// Ares

		if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			if (e.getItem() != null) {

				if (e.getItem().hasItemMeta()) {
					if (e.getItem().getItemMeta().hasDisplayName()) {
						if (e.getItem().getItemMeta().getDisplayName()
								.equalsIgnoreCase(ChatColor.RED + "" + ChatColor.BOLD + "Escape!")) {
							if (cooldownEscape.contains(p)) {
								p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Cooldown" + ChatColor.GRAY + "] "
										+ ChatColor.RED + "" + ChatColor.BOLD + "Escape! " + ChatColor.WHITE
										+ "is still on cooldown!");
								p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
							} else {
								cooldownEscape.add(p);

								Vector escape = p.getLocation().getDirection().multiply(-1.0D).setY(1.0D);
								p.setVelocity(escape);
								p.getWorld().playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 0, 1);
								PotionEffect regen = new PotionEffect(PotionEffectType.REGENERATION, 4 * 20, 1);
								PotionEffect slow = new PotionEffect(PotionEffectType.SPEED, 3 * 20, 0);
								p.addPotionEffect(slow);
								p.addPotionEffect(regen);
								Worlds.kitpvpWorld.spawnParticle(Particle.SNOWBALL, p.getLocation(), 5);

								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(),
										new Runnable() {
											public void run() {
												cooldownEscape.remove(p);
												p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Ability"
														+ ChatColor.GRAY + "] " + ChatColor.RED + "" + ChatColor.BOLD
														+ "Escape " + ChatColor.GRAY + "is " + ChatColor.GREEN
														+ "READY!");
											}
										}, cdEscape * 20);

							}
						}
					} else {
						return;
					}
				}
			}

		}

		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (e.getItem() != null) {
				if (e.getItem().getItemMeta().getDisplayName().equals("Blink!")) {
					if (cooldownBlink.contains(p)) {
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Cooldown" + ChatColor.GRAY
								+ "] " + ChatColor.WHITE + "" + ChatColor.BOLD + "BLINK " + ChatColor.WHITE
								+ "is still on cooldown!");
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					} else {
						cooldownBlink.add(p);

						Location location = p.getLineOfSight(null, 8).get(p.getLineOfSight(null, 8).size() - 2).getLocation();
						location.setYaw(p.getLocation().getYaw());
						location.setPitch(p.getLocation().getPitch());
						p.teleport(location);
						
						Worlds.kitpvpWorld.spawnParticle(Particle.CLOUD, p.getLocation(), 5);
						Worlds.kitpvpWorld.spawnParticle(Particle.FIREWORKS_SPARK, p.getLocation(), 5);
						Worlds.kitpvpWorld.spawnParticle(Particle.FLASH, p.getLocation(), 5);
						p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(),
								new Runnable() {

									public void run() {
										cooldownBlink.remove(p);
										p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Ability"
												+ ChatColor.GRAY + "] " + ChatColor.WHITE + ""
												+ ChatColor.BOLD + "BLINK " + ChatColor.GRAY + "is "
												+ ChatColor.GREEN + "READY!");
									}
								}, cdBlink * 20);

					}
				}

				if (e.getItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.RED + "" + ChatColor.BOLD + "DASH")) {
					if (cooldownDash.contains(p)) {
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Cooldown" + ChatColor.GRAY
								+ "] " + ChatColor.RED + "" + ChatColor.BOLD + "DASH " + ChatColor.WHITE
								+ "is still on cooldown!");
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					} else {
						cooldownDash.add(p);

						Vector dash = p.getLocation().getDirection().multiply(1.25D).setY(0.25D);
						p.setVelocity(dash);

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(),
								new Runnable() {
									public void run() {
										cooldownDash.remove(p);
										p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Ability"
												+ ChatColor.GRAY + "] " + ChatColor.RED + ""
												+ ChatColor.BOLD + "Dash " + ChatColor.GRAY + "is "
												+ ChatColor.GREEN + "READY!");
									}
								}, 3 * 20);

					}
				}

				if (e.getItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.RED + "" + ChatColor.BOLD + "Corrupted Orb!")) {
					if (cooldownCorruptedOrb.contains(p)) {
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Cooldown" + ChatColor.GRAY
								+ "] " + ChatColor.RED + "" + ChatColor.BOLD + "Corrupted Orb "
								+ ChatColor.WHITE + "is still on cooldown!");
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					} else {
						cooldownCorruptedOrb.add(p);

						Vector dropsite = p.getLocation().getDirection().normalize().multiply(1.0);
						Item item = p.getWorld().dropItem(p.getLocation().add(0, 1.5, 0),
								new ItemStack(Material.ENDER_EYE));
						item.setVelocity(dropsite);

						playEffect(item);

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(),
								new Runnable() {
									public void run() {
										cooldownCorruptedOrb.remove(p);
										p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Ability"
												+ ChatColor.GRAY + "] " + ChatColor.RED + ""
												+ ChatColor.BOLD + "Corrupted Orb " + ChatColor.GRAY + "is "
												+ ChatColor.GREEN + "READY!");
									}
								}, cdCorruptedOrb * 20);

					}
				}

				if (e.getItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.RED + "" + ChatColor.BOLD + "EARTHQUAKE")) {
					if (cooldownEarthquake.contains(p)) {
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Cooldown" + ChatColor.GRAY
								+ "] " + ChatColor.RED + "" + ChatColor.BOLD + "EARTHQUAKE "
								+ ChatColor.WHITE + "is still on cooldown!");
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					} else {
						Worlds.kitpvpWorld.spawnParticle(Particle.VILLAGER_ANGRY, p.getLocation(), 5);
						cooldownEarthquake.add(p);
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_BLAZE_HURT, 1, 1);
						for (Player players : Bukkit.getOnlinePlayers()) {
							if (p.getNearbyEntities(8.0, 8.0, 8.0).contains(players)) {
								EnvironmentManager.damageList.put(players, p);
								players.damage(18.0);
								Vector knockup = players.getLocation().getDirection().multiply(0.1D)
										.setY(1.8D);
								players.setVelocity(knockup);
								Worlds.kitpvpWorld.spawnParticle(Particle.EXPLOSION_LARGE, p.getLocation(), 5);
								PotionEffect slow = new PotionEffect(PotionEffectType.SLOW, 2 * 20, 1);
								players.addPotionEffect(slow);
								//StatusManager.enableFall.add(players); 

							}
						}

						for (Player players1 : Bukkit.getOnlinePlayers()) {
							if (p.getNearbyEntities(12.0, 12.0, 12.0).contains(players1)) {
								Worlds.kitpvpWorld.strikeLightning(players1.getLocation()); 
								EnvironmentManager.damageList.put(players1, p);
							}
						}

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(),
								new Runnable() {

									public void run() {
										cooldownEarthquake.remove(p);
										p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Ability"
												+ ChatColor.GRAY + "] " + ChatColor.RED + ""
												+ ChatColor.BOLD + "EARTHQUAKE " + ChatColor.GRAY + "is "
												+ ChatColor.GREEN + "READY!");
									}
								}, cdEarthquake * 20);

					}
				}

				if (e.getItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.RED + "" + ChatColor.BOLD + "CLEAVE")) {
					if (!(p.getLevel() >= 80)) {
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Energy" + ChatColor.GRAY
								+ "] " + ChatColor.WHITE + "You do not have enough Energy to use "
								+ ChatColor.RED + "" + ChatColor.BOLD + "CLEAVE");
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					} else {
						p.setLevel(p.getLevel() - 80);
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_BLAZE_HURT, 1, 1);
						Worlds.kitpvpWorld.spawnParticle(Particle.EXPLOSION_NORMAL, p.getLocation(), 5);
						for (Player players : Bukkit.getOnlinePlayers()) {
							if (p.getNearbyEntities(2.0, 2.0, 2.0).contains(players)) {
								EnvironmentManager.damageList.put(players, p);
								players.damage(20.0);
								players.playEffect(EntityEffect.HURT);
								//Worlds.kitpvpWorld.spawnParticle(Particle.BLOCK_DUST, p.getLocation(), 5);
							}
						}

					}
				}

				if (e.getItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Ares Sword")) {
					if (cooldownEnlightment.contains(p)) {
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Cooldown" + ChatColor.GRAY
								+ "] " + ChatColor.LIGHT_PURPLE + "Enlightment " + ChatColor.WHITE
								+ "is still on cooldown!");
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					} else {
						p.setVelocity(p.getLocation().getDirection().multiply(1.5));
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
						PotionEffect absorb = new PotionEffect(PotionEffectType.ABSORPTION, 10 * 20, 0);
						p.addPotionEffect(absorb);

						// CD
						cooldownEnlightment.add(p);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(),
								new Runnable() {
									public void run() {
										cooldownEnlightment.remove(p);
										p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Ability"
												+ ChatColor.GRAY + "] " + ChatColor.LIGHT_PURPLE
												+ "Enlightment " + ChatColor.GRAY + "is " + ChatColor.GREEN
												+ "READY!");
									}
								}, cdEnlightment * 20);
					}
				}

				if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("AUTOCAST").equals(false)) {
					if (e.getItem() != null) {
						if (e.getItem().hasItemMeta()) {
							if (e.getItem().getItemMeta().getDisplayName()
									.equalsIgnoreCase(ChatColor.GREEN + "" + ChatColor.BOLD + "Cloak")) {
								if (cooldownCloak.contains(p)) {
									p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Cooldown" + ChatColor.GRAY
											+ "] " + ChatColor.GREEN + "" + ChatColor.BOLD + "Cloak " + ChatColor.WHITE
											+ "is still on cooldown!");
									p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
								} else {

									PotionEffect invis = new PotionEffect(PotionEffectType.INVISIBILITY, 7 * 20, 0);
									p.addPotionEffect(invis);
									p.sendMessage(ChatColor.GRAY + "[" + ChatColor.WHITE + "Cloak" + ChatColor.GRAY
											+ "] You are now cloaked!");

									Worlds.kitpvpWorld.playSound(p.getLocation(), Sound.ENTITY_TNT_PRIMED, 1, 1);
									Worlds.kitpvpWorld.spawnParticle(Particle.EXPLOSION_HUGE, p.getLocation(), 5);
									Worlds.kitpvpWorld.spawnParticle(Particle.SMOKE_LARGE, p.getLocation(), 5);

									// maybe
									Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {

										@Override
										public void run() {
											for (Player players : Bukkit.getOnlinePlayers()) {
												if (!players.canSee(p)) {

													players.showPlayer(Main.getInstance(), p);

													p.sendMessage(ChatColor.GRAY + "[" + ChatColor.WHITE + "Cloak"
															+ ChatColor.GRAY + "] You are no longer cloaked!");
													Worlds.kitpvpWorld.playSound(p.getLocation(), Sound.ENTITY_TNT_PRIMED, 1,
															1);
													Worlds.kitpvpWorld.spawnParticle(Particle.EXPLOSION_HUGE, p.getLocation(), 5);
													Worlds.kitpvpWorld.spawnParticle(Particle.SMOKE_LARGE, p.getLocation(), 5);
												}
											}
										}
									}, 20 * 7);

									for (Player players : Bukkit.getServer().getOnlinePlayers()) {
										players.hidePlayer(Main.getInstance(), p);
									}

									cooldownCloak.add(p);
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(),
											new Runnable() {
												public void run() {
													cooldownCloak.remove(p);
													p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Ability"
															+ ChatColor.GRAY + "] " + ChatColor.GREEN + ""
															+ ChatColor.BOLD + "Cloak " + ChatColor.GRAY + "is "
															+ ChatColor.GREEN + "READY!");
												}
											}, cdCloak * 20);

								}
							}

							if (e.getItem().getItemMeta().getDisplayName()
									.equalsIgnoreCase(ChatColor.RED + "" + ChatColor.BOLD + "ROAR")) {
								if (!(p.getLevel() == 100)) {
									p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Energy" + ChatColor.GRAY
											+ "] " + ChatColor.WHITE + "You do not have enough Energy to use "
											+ ChatColor.RED + "" + ChatColor.BOLD + "ROAR");
									p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
								} else {
									p.setLevel(0);
									p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
									Worlds.kitpvpWorld.spawnParticle(Particle.CRIT, p.getLocation(), 5);
									Worlds.kitpvpWorld.spawnParticle(Particle.EXPLOSION_HUGE, p.getLocation(), 5);
									PotionEffect strength = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5 * 20,
											1);
									p.addPotionEffect(strength);
									for (Player players : Bukkit.getOnlinePlayers()) {
										if (p.getNearbyEntities(5.0, 5.0, 5.0).contains(players)) {
											players.damage(2.0);
											EnvironmentManager.damageList.put(players, p);
											players.playEffect(EntityEffect.HURT);
											Worlds.kitpvpWorld.spawnParticle(Particle.SPELL_INSTANT, p.getLocation(), 5);
											PotionEffect slow = new PotionEffect(PotionEffectType.SLOW, 10 * 20, 3);
											players.addPotionEffect(slow);
										}
									}

								}
							}
							if (e.getItem().getItemMeta().getDisplayName()
									.equalsIgnoreCase(ChatColor.GREEN + "" + ChatColor.BOLD + "Quick Fix")) {
								if (!(p.getLevel() >= 40)) {
									p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Energy" + ChatColor.GRAY
											+ "] " + ChatColor.WHITE + "You do not have enough Energy to use "
											+ ChatColor.GREEN + "" + ChatColor.BOLD + "Quick Fix");
									p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
								} else {
									p.setLevel(p.getLevel() - 40);
									p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CAT_PURR, 1, 1);
									Worlds.kitpvpWorld.spawnParticle(Particle.HEART, p.getLocation(), 5);
									if ((p.getHealth() >= 16.0)) {
										p.setHealth(20.0);
									} else {
										p.setHealth(p.getHealth() + 4.0);
									}

								}
							}
							if (e.getItem().getItemMeta().getDisplayName()
									.equalsIgnoreCase(ChatColor.RED + "" + ChatColor.BOLD + "Regeneration Flower")) {
								if (cooldownRegen.contains(p)) {
									p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Cooldown" + ChatColor.GRAY
											+ "] " + ChatColor.RED + "" + ChatColor.BOLD + "Regeneration Flower "
											+ ChatColor.WHITE + "is still on cooldown!");
									p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
								} else {
									p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 1, 1);
									PotionEffect regen = new PotionEffect(PotionEffectType.REGENERATION, 5 * 20, 3);
									p.addPotionEffect(regen);
									Worlds.kitpvpWorld.spawnParticle(Particle.HEART, p.getLocation(), 5);

									cooldownRegen.add(p);
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(),
											new Runnable() {

												public void run() {
													cooldownRegen.remove(p);
													p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Ability"
															+ ChatColor.GRAY + "] " + ChatColor.RED + ""
															+ ChatColor.BOLD + "Regeneration Flower " + ChatColor.GRAY
															+ "is " + ChatColor.GREEN + "READY!");
												}
											}, cdRegen * 20);

								}
							}
							if (e.getItem().getItemMeta().getDisplayName()
									.equalsIgnoreCase(ChatColor.RED + "" + ChatColor.BOLD + "Ignite")) {
								if (cooldownIgnite.contains(p)) {
									p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Cooldown" + ChatColor.GRAY
											+ "] " + ChatColor.RED + "Ignite " + ChatColor.WHITE
											+ "is still on cooldown!");
									p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
								} else {
									p.getWorld().playSound(p.getLocation(), Sound.ENTITY_BLAZE_HURT, 1, 1);
									p.getWorld().playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 2);


									for (Player players : Bukkit.getOnlinePlayers()) {
										if (p.getNearbyEntities(3.0, 3.0, 3.0).contains(players)) {
											EnvironmentManager.damageList.put(players, p);
											players.setFireTicks(9 * 20);
											Worlds.kitpvpWorld.spawnParticle(Particle.FLAME, p.getLocation(), 5);
											p.getWorld().playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);

										}
									}

									// CD
									cooldownIgnite.add(p);
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(),
											new Runnable() {
												public void run() {
													cooldownIgnite.remove(p);
													p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Ability"
															+ ChatColor.GRAY + "] " + ChatColor.RED + "Ignite "
															+ ChatColor.GRAY + "is " + ChatColor.GREEN + "READY!");
												}
											}, cdIgnite * 20);
								}

							}

							
						}
					}
				}

			}
		}

	}

	
	


	

}
