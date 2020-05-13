package me.oggunderscore.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.oggunderscore.Core.Main;
import me.oggunderscore.Managers.FFAManager;
import me.oggunderscore.Managers.FightManager;
import me.oggunderscore.Managers.StatusManager;
import me.oggunderscore.Utils.Inventories;
import me.oggunderscore.Utils.Locations;
import me.oggunderscore.Utils.Prefix;

public class StaffCommands implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {

		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("update")) {
			if (p.isOp())
				Bukkit.getServer().broadcastMessage(Prefix.corePrefix + "Server will be updating soon! Please rejoin!");
		}
		if (cmd.getName().equalsIgnoreCase("dev")) {
			if (p.isOp()) {
				p.setGameMode(GameMode.CREATIVE);
				p.teleport(Locations.kitpvpDevSpawn);
				p.sendMessage(Prefix.corePrefix + "You have been teleported to the Dev Spawn.");

			} else {
				p.sendMessage(Prefix.errorPrefix + "You do not have permission to use this Command!");
			}
		}

		if (cmd.getName().equalsIgnoreCase("givegold")) {
			if (p.isOp()) {
				if (args.length <= 1) {
					p.sendMessage(Prefix.errorPrefix + "Correct format: /givegold <player / *> <amount>");
				}
				if (args.length == 2) {
					if (args[0].equalsIgnoreCase("*")) {
						for (Player players : Bukkit.getOnlinePlayers()) {
							Player target = players;

							Integer amount = Integer.parseInt(args[1]);

							Integer newGold = (Integer) Main.getInstance().getConfig().getConfigurationSection(target.getName()).getInt("GOLD")
									+ amount;
							Main.getInstance().getConfig().getConfigurationSection(target.getName()).set("GOLD", newGold);
							p.sendMessage(Prefix.goldPrefix + "You gave EVERYONE " + ChatColor.GOLD + args[1] + " Gold");
							target.sendMessage(Prefix.goldPrefix + "You were given " + ChatColor.GOLD + args[1] + " Gold"
									+ ChatColor.GRAY + " from " + p.getName());
							Main.getPlugin(Main.class).saveConfig();
						}
					}
					for (Player players : Bukkit.getOnlinePlayers()) {
						if (args[0].equalsIgnoreCase(players.getName())) {
							Integer amount = Integer.parseInt(args[1]);
							// Integer.valueOf(args[1])
							Integer newGold = (Integer) Main.getInstance().getConfig().getConfigurationSection(players.getName()).getInt("GOLD")
									+ amount;
							Main.getInstance().getConfig().getConfigurationSection(players.getName()).set("GOLD", newGold);

							p.sendMessage(Prefix.goldPrefix + "You gave " + players.getName() + " " + ChatColor.GOLD
									+ args[1] + " Gold");
							players.sendMessage(Prefix.goldPrefix + "You were given " + ChatColor.GOLD + args[1] + " Gold"
									+ ChatColor.GRAY + " from " + p.getName());

							Main.getPlugin(Main.class).saveConfig();
						}
					}
				}
				if (args.length >= 3) {
					p.sendMessage(Prefix.errorPrefix + "Too many Args!");
				}
			} else {
				p.sendMessage(Prefix.errorPrefix + "You don't have permission to run this!");
			}
		}

		if (cmd.getName().equalsIgnoreCase("togglegravity")) {

			if (p.hasPermission("oggcore.staff")) {
				if (StatusManager.gravity.contains(p)) {
					StatusManager.gravity.remove(p);
					p.sendMessage(Prefix.corePrefix + "Gravity Builds disabled!");
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 0, 1);
				} else {
					StatusManager.gravity.add(p);
					p.sendMessage(Prefix.corePrefix + "Gravity Builds enabled!");
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 0, 1);
				}
			} else {
				p.sendMessage(Prefix.errorPrefix + "You don't have permission to run this!");
			}
		}

		if (cmd.getName().equalsIgnoreCase("oggdeop")) {
			if (p.isOp() == true) {
				if (args.length == 0) {
					p.sendMessage(Prefix.errorPrefix + "Specify a player!");
					p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
				} else if (args.length == 1) {
					for (Player players : Bukkit.getOnlinePlayers()) {
						if (Bukkit.getPlayer(players.getName()) != null) {
							Player target = Bukkit.getPlayer(players.getName());
							p.sendMessage(Prefix.corePrefix + "Target will not be deopped until reload.");
							p.playSound(p.getLocation(), Sound.BLOCK_WOODEN_DOOR_CLOSE, 1, 1);
							Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.getInstance(), new Runnable() {
								@Override
								public void run() {
									target.setOp(false);
								}
							}, 0L, 20L);
						}
					}
				} else {
					p.sendMessage(Prefix.errorPrefix + "Too many args!");
					p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
				}
			} else {
				p.sendMessage(Prefix.errorPrefix + "You don't have permission! Nice try.");
				p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
			}

		}

		if (cmd.getName().equalsIgnoreCase("code")) {
			StatusManager.codeMode.add(p);
			p.sendMessage(Prefix.corePrefix + "You entered code mode. Type 'cancel' to exit. Please enter your code:");
		}

		if (cmd.getName().equalsIgnoreCase("oggclear")) {
			if (p.isOp() == true) {
				for (int x = 0; x <= 100; x++) {
					Bukkit.getServer().broadcastMessage("");
				}
				Bukkit.getServer().broadcastMessage(
						Prefix.corePrefix + ChatColor.AQUA + p.getName() + ChatColor.GRAY + " has cleared the chat!");
			} else {
				p.sendMessage(Prefix.errorPrefix + "Don't have permission. Nice try.");
				p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
			}

		}

		if (cmd.getName().equalsIgnoreCase("forceleave")) {
			if (p.getName().equals("ogg_")) {
				Bukkit.getServer().broadcastMessage(Prefix.ffaPrefix + "A Force Leave has been issued!");
				for (Player players : Bukkit.getOnlinePlayers()) {
					if (FFAManager.inFfa.contains(players)) {
						Player target = players;
						if (target.getWorld().equals(Bukkit.getWorld("kitpvp"))) {
							target.setCanPickupItems(false);
							target.setHealth(20.0);
							target.setFoodLevel(20);
							target.setLevel(0);
							target.setCanPickupItems(true);
							Inventories.clear(p);
							target.teleport(Locations.kitpvpSpawn);
							target.setGameMode(GameMode.SURVIVAL);
							target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
							if (FFAManager.inFfa.contains(target)) {
								FFAManager.inFfa.remove(target);
								Bukkit.getServer().broadcastMessage(Prefix.ffaPrefix + ChatColor.AQUA + target.getName() + ChatColor.GRAY
										+ " has left the FFA Arena!");
							} else if (FightManager.inFight.contains(target)) {
								FightManager.inFight.remove(target);
								Bukkit.getServer().broadcastMessage(Prefix.ffaPrefix + ChatColor.AQUA + target.getName() + ChatColor.GRAY
										+ " has left the Fight Arena!");
							}
						} else {
							target.playSound(target.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
							target.sendMessage(Prefix.errorPrefix + "You are not in the KitPVP World!");
							if (FFAManager.inFfa.contains(target)) {
								FFAManager.inFfa.remove(target);
								Bukkit.getServer().broadcastMessage(Prefix.ffaPrefix + ChatColor.AQUA + target.getName() + ChatColor.GRAY
										+ " has left the FFA Arena!");
							} else if (FightManager.inFight.contains(p)) {
								FightManager.inFight.remove(target);
								Bukkit.getServer().broadcastMessage(Prefix.ffaPrefix + ChatColor.AQUA + target.getName() + ChatColor.GRAY
										+ " has left the Fight Arena!");
							}
						}

					}
				}
			}
		}

		if (cmd.getName().equalsIgnoreCase("items")) {
			if (p.hasPermission("oggcore.staff")) {
				
				Inventory itemGui = Bukkit.createInventory(null, 9, "Item Menu");

				ItemStack heavyBow1 = new ItemStack(Material.BOW);
				ItemMeta heavyBow1Meta = heavyBow1.getItemMeta();
				ArrayList<String> heavyBow1Lore = new ArrayList<String>();
				heavyBow1Meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Heavy Bow I");
				heavyBow1Lore.add(ChatColor.RED + "" + ChatColor.BOLD + "Tier I Heavy Bow");
				heavyBow1Meta.setLore(heavyBow1Lore);
				heavyBow1.setItemMeta(heavyBow1Meta);

				ItemStack heavyBow2 = new ItemStack(Material.BOW);
				ItemMeta heavyBow2Meta = heavyBow2.getItemMeta();
				ArrayList<String> heavyBow2Lore = new ArrayList<String>();
				heavyBow2Meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Heavy Bow II");
				heavyBow2Lore.add(ChatColor.RED + "" + ChatColor.BOLD + "Tier II Heavy Bow");
				heavyBow2Meta.setLore(heavyBow2Lore);
				heavyBow2.setItemMeta(heavyBow2Meta);

				ItemStack heavyBow3 = new ItemStack(Material.BOW);
				ItemMeta heavyBow3Meta = heavyBow3.getItemMeta();
				ArrayList<String> heavyBow3Lore = new ArrayList<String>();
				heavyBow3Meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Heavy Bow III");
				heavyBow3Lore.add(ChatColor.RED + "" + ChatColor.BOLD + "Tier III Heavy Bow");
				heavyBow3Meta.setLore(heavyBow3Lore);
				heavyBow3.setItemMeta(heavyBow3Meta);

				itemGui.setItem(0, heavyBow1);
				itemGui.setItem(1, heavyBow2);
				itemGui.setItem(2, heavyBow3);

				p.openInventory(itemGui);
			}
		}

		return false;
	}

}
