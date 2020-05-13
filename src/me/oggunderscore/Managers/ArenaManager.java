package me.oggunderscore.Managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.oggunderscore.Utils.Locations;

public class ArenaManager implements CommandExecutor {
	
	
	
	public static int arenaNumber = 0;
	public static String arenaName = ""; 
	public static Location chosenArena1;
	public static Location chosenArena2;
	
	public static void rotateArenas() {
		arenaNumber = arenaNumber + 1;

		if (arenaNumber == 0) {
			chosenArena1 = Locations.twilightArena1;
			chosenArena2 = Locations.twilightArena2;
			arenaName = "Twilight Forest" + ChatColor.AQUA + " by Kreed95";
			Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Arena" + ChatColor.GRAY
					+ "] Arena has been rotated to " + ChatColor.GREEN + arenaName);
		}
		if (arenaNumber == 1) {
			chosenArena1 = Locations.tombArena1;
			chosenArena2 = Locations.tombArena2;
			arenaName = "Hades Tomb" + ChatColor.AQUA + " by Kreed95";
			Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Arena" + ChatColor.GRAY
					+ "] Arena has been rotated to " + ChatColor.GREEN + arenaName);
		}
		if (arenaNumber == 2) {
			arenaNumber = 0;
			chosenArena1 = Locations.salonArena1;
			chosenArena2 = Locations.salonArena2;
			arenaName = "The Salon" + ChatColor.AQUA + " by ogg_";
			Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Arena" + ChatColor.GRAY
					+ "] Arena has been rotated to " + ChatColor.GREEN + arenaName);
		}
		/*
		if (arenaNumber == 3) {
			chosenArena1 = Locations.icyArena1;
			chosenArena2 = Locations.icyArena2;
			arenaName = "Ice Palace" + ChatColor.AQUA + " by Kasuuu_";
			Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Arena" + ChatColor.GRAY
					+ "] Arena has been rotated to " + ChatColor.GREEN + arenaName);
		}
		if (arenaNumber == 4) {
			chosenArena1 = Locations.fourCornersArena1;
			chosenArena2 = Locations.fourCornersArena2;
			arenaName = "Four Corners" + ChatColor.AQUA + " by KingFluffy_";
			Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Arena" + ChatColor.GRAY
					+ "] Arena has been rotated to " + ChatColor.GREEN + arenaName);
		}
		
		if (arenaNumber == 5) {
			chosenArena1 = Locations.clayArena1;
			chosenArena2 = Locations.clayArena2;
			arenaName = "Clay Designs" + ChatColor.AQUA + " by Inns";
			Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Arena" + ChatColor.GRAY
					+ "] Arena has been rotated to " + ChatColor.GREEN + arenaName);
		}

		if (arenaNumber == 6) {
			chosenArena1 = Locations.jailArena1;
			chosenArena2 = Locations.jailArena2;
			arenaName = "Jail" + ChatColor.AQUA + " by xmonisdead";
			Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Arena" + ChatColor.GRAY
					+ "] Arena has been rotated to " + ChatColor.GREEN + arenaName);
		}
		if (arenaNumber == 7) {
			chosenArena1 = Locations.forestArena1;
			chosenArena2 = Locations.forestArena2;
			arenaName = "The Forest" + ChatColor.AQUA + " by ogg_";
			Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Arena" + ChatColor.GRAY
					+ "] Arena has been rotated to " + ChatColor.GREEN + arenaName);
		}
		if (arenaNumber == 8) {
			chosenArena1 = Locations.seaArena1;
			chosenArena2 = Locations.seaArena2;
			arenaName = "The Sea" + ChatColor.AQUA + " by myhealth";
			Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Arena" + ChatColor.GRAY
					+ "] Arena has been rotated to " + ChatColor.GREEN + arenaName);
		}
		if (arenaNumber == 9) {
			chosenArena1 = Locations.solarArena1;
			chosenArena2 = Locations.solarArena2;
			arenaName = "Solar System" + ChatColor.AQUA + " by myhealth";
			Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Arena" + ChatColor.GRAY
					+ "] Arena has been rotated to " + ChatColor.GREEN + arenaName);
		}
		if (arenaNumber == 10) {
			arenaNumber = 4;
			chosenArena1 = Locations.purpleDome1;
			chosenArena2 = Locations.purpleDome2;
			arenaName = "???" + ChatColor.AQUA + " by myhealth & ???";
			Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Arena" + ChatColor.GRAY
					+ "] Arena has been rotated to " + ChatColor.GREEN + arenaName);
		}
		*/
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("arena")) {
			if (p.hasPermission("oggcore.staff")) {
				if (args.length == 0) {
					p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Arena" + ChatColor.GRAY + "] Please specity a Arena! Arenas Available: twilightArena, hadesTomb, salon");
				}
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("twilightForest")) {
						arenaNumber = 0;
						chosenArena1 = Locations.twilightArena1;
						chosenArena2 = Locations.twilightArena2;
						arenaName = "Twilight Arena" + ChatColor.AQUA + " by Kreed95";
						Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Arena" + ChatColor.GRAY + "] Arena has been set to " + ChatColor.GREEN + arenaName);
					} else if (args[0].equalsIgnoreCase("hadesTomb")) {
						arenaNumber = 1;
						chosenArena1 = Locations.tombArena1;
						chosenArena2 = Locations.tombArena2;
						arenaName = "Hades Tomb" + ChatColor.AQUA + " by Kreed95";
						Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Arena" + ChatColor.GRAY + "] Arena has been set to " + ChatColor.GREEN + arenaName);
					} else if (args[0].equalsIgnoreCase("salon")) {
						arenaNumber = 2;
						chosenArena1 = Locations.salonArena1;
						chosenArena2 = Locations.salonArena2;
						arenaName = "The Salon" + ChatColor.AQUA + " by ogg_";
						Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Arena" + ChatColor.GRAY + "] Arena has been set to " + ChatColor.GREEN + arenaName);
					} else {
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Arena" + ChatColor.GRAY + "] Invalid Arena! Arenas Available: twilightArena, hadesTomb, salon");
					}
				}
			} else {
				p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
				p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Arena" + ChatColor.GRAY + "] You do not have permission!");
			}
		}
		return false;
	}

}
