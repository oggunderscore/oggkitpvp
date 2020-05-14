package me.oggunderscore.Commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.oggunderscore.Core.Events;
import me.oggunderscore.Core.Main;
import me.oggunderscore.Managers.MenuManager;
import me.oggunderscore.Utils.Inventories;
import me.oggunderscore.Utils.Locations;
import me.oggunderscore.Utils.Prefix;
import me.oggunderscore.Utils.Worlds;

public class GameCommands implements CommandExecutor {



	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {

		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("urf")) {
			if (p.isOp()) {
				if (Main.getInstance().getConfig().getBoolean("urf") == false) {
					Main.getInstance().getConfig().set("urf", true);
					p.sendMessage(Prefix.corePrefix + "URF has been enabled!");
					Main.getPlugin(Main.class).saveConfig();
					Events.cdEnlightment = 3;
					Events.cdIgnite = 1;
					Events.cdRegen = 7;
					Events.cdEarthquake = 6;
					Events.cdCorruptedOrb = 4;
					Events.cdDash = 1;
					Events.cdEscape = 4;
					Events.cdBlink = 2;
					Events.cdCloak = 12;
				} else {
					Main.getInstance().getConfig().set("urf", false);
					p.sendMessage(Prefix.corePrefix + "URF has been disabled!");
					Main.getPlugin(Main.class).saveConfig();
					Events.cdEnlightment = 13;
					Events.cdIgnite = 5;
					Events.cdRegen = 15;
					Events.cdEarthquake = 14;
					Events.cdCorruptedOrb = 7;
					Events.cdDash = 3;
					Events.cdEscape = 7;
					Events.cdBlink = 3;
					Events.cdCloak = 20;
				}
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
		}
		if (cmd.getName().equalsIgnoreCase("coreinfo")) {
			p.sendMessage("");
			p.sendMessage(ChatColor.YELLOW + " " + ChatColor.BOLD + "    Competitive KitPVP & Core");
			p.sendMessage(ChatColor.AQUA + "" + ChatColor.ITALIC + "       Coded by ogg_");
			p.sendMessage("");
			p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "  GOLD " + ChatColor.GOLD
					+ "is the currency used to purchase Kits and more!");
			p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "  COMMANDS: " + ChatColor.AQUA
					+ " Some basic commands are /unlock, /stats, /ffa, /ffa2, and /fight");
			p.sendMessage(ChatColor.BLUE + " " + ChatColor.BOLD + "       Joining a Fight");
			p.sendMessage(ChatColor.YELLOW + "    You can join a fight by typing /fight (name) to");
			p.sendMessage(
					ChatColor.YELLOW + "challenge someone in the open fight list, which is displayed by /fight list. ");
			p.sendMessage(ChatColor.YELLOW + "which is displayed by /fight list. You can join the open fight list by");
			p.sendMessage(ChatColor.YELLOW + "typing /fight! There is also /FFA Good Luck!");
			p.sendMessage("");
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
		}
		if (cmd.getName().equalsIgnoreCase("unlock")) {
			int playerGold = (int) Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("GOLD");
			if (args.length == 0) {
				p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
				p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY + "] " + ChatColor.GRAY
						+ "Please type a valid purchasable kit! Kits: Ares, Berserker, Thor, Hades, Apollo, "
						+ ChatColor.GREEN + "" + ChatColor.BOLD + "NEW " + ChatColor.GRAY + "NINJA");
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("ARES")) {
					if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("ARES").equals(true)) {
						p.playSound(p.getLocation(), Sound.ENTITY_BAT_HURT, 1, 1);
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY + "] "
								+ ChatColor.GRAY + "You already own Kit " + ChatColor.YELLOW + "Ares!");
					} else if (playerGold >= 1500) {
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Unlock" + ChatColor.GRAY + "] "
								+ ChatColor.GREEN + "You purchased Kit " + ChatColor.YELLOW + "Ares!");
						Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("GOLD", playerGold - 1500);
						Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("ARES", true);
						Main.getPlugin(Main.class).saveConfig();
					} else {
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY + "] "
								+ ChatColor.GRAY + "You need 1500 Gold to purchase Kit " + ChatColor.YELLOW + "Ares!");
					}
				}
				if (args[0].equalsIgnoreCase("BERSERKER")) {
					if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("BERSERKER").equals(true)) {
						p.playSound(p.getLocation(), Sound.ENTITY_BAT_HURT, 1, 1);
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY + "] "
								+ ChatColor.GRAY + "You already own Kit " + ChatColor.YELLOW + "Berserker!");
					} else if (playerGold >= 2500) {
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Unlock" + ChatColor.GRAY + "] "
								+ ChatColor.GREEN + "You purchased Kit " + ChatColor.YELLOW + "Berserker!");
						Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("GOLD", playerGold - 2500);
						Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("BERSERKER", true);
						Main.getPlugin(Main.class).saveConfig();
					} else {
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
						p.sendMessage(
								ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY + "] " + ChatColor.GRAY
										+ "You need 2500 Gold to purchase Kit " + ChatColor.YELLOW + "Berserker!");
					}
				}
				if (args[0].equalsIgnoreCase("THOR")) {
					if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("THOR").equals(true)) {
						p.playSound(p.getLocation(), Sound.ENTITY_BAT_HURT, 1, 1);
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY + "] "
								+ ChatColor.GRAY + "You already own Kit " + ChatColor.YELLOW + "Thor!");
					} else if (playerGold >= 2500) {
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Unlock" + ChatColor.GRAY + "] "
								+ ChatColor.GREEN + "You purchased Kit " + ChatColor.YELLOW + "Thor!");
						Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("GOLD", playerGold - 2500);
						Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("THOR", true);
						Main.getPlugin(Main.class).saveConfig();

					} else {
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY + "] "
								+ ChatColor.GRAY + "You need 2500 to purchase Kit " + ChatColor.YELLOW + "Thor!");
					}
				}
				if (args[0].equalsIgnoreCase("NINJA")) {
					if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("NINJA").equals(true)) {
						p.playSound(p.getLocation(), Sound.ENTITY_BAT_HURT, 1, 1);
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY + "] "
								+ ChatColor.GRAY + "You already own Kit " + ChatColor.YELLOW + "Ninja!");
					} else if (playerGold >= 3000) {
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Unlock" + ChatColor.GRAY + "] "
								+ ChatColor.GREEN + "You purchased Kit " + ChatColor.YELLOW + "Ninja!");
						Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("GOLD", playerGold - 3000);
						Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("NINJA", true);
						Main.getPlugin(Main.class).saveConfig();

					} else {
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY + "] "
								+ ChatColor.GRAY + "You need 3000 Gold to purchase Kit " + ChatColor.YELLOW + "Ninja!");
					}
				}
				if (args[0].equalsIgnoreCase("HADES")) {
					if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("HADES").equals(true)) {
						p.playSound(p.getLocation(), Sound.ENTITY_BAT_HURT, 1, 1);
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY + "] "
								+ ChatColor.GRAY + "You already own Kit " + ChatColor.YELLOW + "Hades!");
					} else if (playerGold >= 1500) {
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Unlock" + ChatColor.GRAY + "] "
								+ ChatColor.GREEN + "You purchased Kit " + ChatColor.YELLOW + "Hades!");
						Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("GOLD", playerGold - 1500);
						Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("HADES", true);
						Main.getPlugin(Main.class).saveConfig();

					} else {
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY + "] "
								+ ChatColor.GRAY + "You need 1500 Gold to purchase Kit " + ChatColor.YELLOW + "Hades!");
					}
				}
				if (args[0].equalsIgnoreCase("APOLLO")) {
					if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("APOLLO").equals(true)) {
						p.playSound(p.getLocation(), Sound.ENTITY_BAT_HURT, 1, 1);
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY + "] "
								+ ChatColor.GRAY + "You already own Kit " + ChatColor.YELLOW + "Apollo!");
					} else if (playerGold >= 2000) {
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Unlock" + ChatColor.GRAY + "] "
								+ ChatColor.GREEN + "You purchased Kit " + ChatColor.YELLOW + "Apollo!");
						Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("GOLD", playerGold - 2000);
						Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("APOLLO", true);
						Main.getPlugin(Main.class).saveConfig();

					} else {
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
						p.sendMessage(
								ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY + "] " + ChatColor.GRAY
										+ "You need 2000 Gold to purchase Kit " + ChatColor.YELLOW + "Apollo!");
					}
				}

			}
			if (args.length >= 2) {
				p.sendMessage(
						ChatColor.GRAY + "[" + ChatColor.RED + "Unlock" + ChatColor.GRAY + "] Too Many Arguments!");
				p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
			}
		}

		if (cmd.getName().equalsIgnoreCase("kitpvp")) {

			if (args.length == 0) {

				if (!p.getWorld().equals(Worlds.kitpvpWorld)) {
					p.teleport(Locations.spawn);
					p.setGameMode(GameMode.ADVENTURE);
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "KitPVP" + ChatColor.GRAY
							+ "] You have been teleported to the KitPVP Lobby!");
					p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
				}

				MenuManager.setupMenu(p);

				p.openInventory(Inventories.kitGui);
			}
			
			/*
			if (args.length == 1) { // Currently not in use
				if (args[0].equalsIgnoreCase("leave")) {
					if (p.getWorld().equals(kitpvpWorld)) {
						InventoryManager.clear(p);
						p.teleport(Locations.spawn);
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
						if (FFAManager.inFfa.contains(p)) {
							FFAManager.inFfa.remove(p);
							Main.broadcast(ChatColor.GRAY + "[" + ChatColor.BLUE + "FFA" + ChatColor.GRAY + "] "
									+ ChatColor.AQUA + p.getName() + ChatColor.GRAY + " has left the FFA Arena!");
						} else if (FightManager.inFight.contains(p)) {
							FightManager.inFight.remove(p);
							Main.broadcast(ChatColor.GRAY + "[" + ChatColor.BLUE + "Fight" + ChatColor.GRAY + "] "
									+ ChatColor.AQUA + p.getName() + ChatColor.GRAY + " has left the Fight Arena!");
						}
					} else {
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY
								+ "] You are not in the KitPVP World!");
						if (FFAManager.inFfa.contains(p)) {
							FFAManager.inFfa.remove(p);
							Main.broadcast(ChatColor.GRAY + "[" + ChatColor.BLUE + "FFA" + ChatColor.GRAY + "] "
									+ ChatColor.AQUA + p.getName() + ChatColor.GRAY + " has left the FFA Arena!");
						} else if (FightManager.inFight.contains(p)) {
							FightManager.inFight.remove(p);
							Main.broadcast(ChatColor.GRAY + "[" + ChatColor.BLUE + "Fight" + ChatColor.GRAY + "] "
									+ ChatColor.AQUA + p.getName() + ChatColor.GRAY + " has left the Fight Arena!");
						}
					}
				} else {
					p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY
							+ "] Unknown subcommand! Are you sure you typed it right?");
				}
			} else if (args.length >= 1) {
				p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
				p.sendMessage(
						ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY + "] Too many args! Agh!");
			}
			
			*/

		}
		
		/*

		if (cmd.getName().equalsIgnoreCase("autocast")) {
			if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("AUTOCAST").equals("TRUE")) {
				Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("AUTOCAST", "FALSE");
				Main.getPlugin(Main.class).saveConfig();
				p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
				p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Settings" + ChatColor.GRAY
						+ "] AutoCast Setting: " + ChatColor.RED + "DISABLED");
			} else if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("AUTOCAST").equals("FALSE")) {
				Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("AUTOCAST", "TRUE");
				Main.getPlugin(Main.class).saveConfig();
				p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
				p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Settings" + ChatColor.GRAY
						+ "] AutoCast Setting: " + ChatColor.GREEN + "ENABLED");
			}
		}
		if (cmd.getName().equalsIgnoreCase("hotbarmode")) {
			if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("HOTBARMODE").equals("1")) {
				Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("HOTBARMODE", "2");
				Main.getPlugin(Main.class).saveConfig();
				p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
				p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Settings" + ChatColor.GRAY
						+ "] HotBarMode Setting: " + ChatColor.BLUE + "2: Sword, Bow, Items");
			} else if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("HOTBARMODE").equals("2")) {
				Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("HOTBARMODE", "1");
				Main.getPlugin(Main.class).saveConfig();
				p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
				p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Settings" + ChatColor.GRAY
						+ "] HotBarMode Setting: " + ChatColor.BLUE + "1: Sword, Items, Bow");
			}
		}
		
		*/

		return false;
	}

}