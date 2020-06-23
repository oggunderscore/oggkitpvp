package me.oggunderscore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.oggunderscore.Core.Main;
import me.oggunderscore.Managers.FFAManager;
import me.oggunderscore.Managers.FightManager;
import me.oggunderscore.Utils.Inventories;
import me.oggunderscore.Utils.ItemStacks;
import me.oggunderscore.Utils.Locations;
import me.oggunderscore.Utils.Prefix;
import me.oggunderscore.Utils.Worlds;

public class StaffCommands implements CommandExecutor {

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
						if (target.getWorld().equals(Worlds.kitpvpWorld)) {
							target.setCanPickupItems(false);
							target.setHealth(20.0);
							target.setFoodLevel(20);
							target.setLevel(0);
							target.setCanPickupItems(true);
							Inventories.clear(p);
							target.teleport(Locations.kitpvpSpawn);
							target.setGameMode(GameMode.ADVENTURE);
							p.getInventory().setItem(0, ItemStacks.getItem("kitpvpButton"));
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

		return false;
	}

}
