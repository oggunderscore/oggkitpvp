package me.oggunderscore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerCommands implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		
		
		
		Player p = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("poke")) {

			for (Player players : Bukkit.getOnlinePlayers()) {
				if (players == Bukkit.getPlayerExact(args[0])) {

					Player target = Bukkit.getPlayerExact(args[0]);
					if (args.length == 0) {
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Poke" + ChatColor.GRAY
								+ "] Choose someone to Poke!");
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 0, 2);
					}

					else {
						target.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Poke" + ChatColor.GRAY
								+ "] You have been Poked by " + ChatColor.BLUE + p.getDisplayName() + ChatColor.GRAY
								+ "!!!");
						target.playSound(target.getLocation(), Sound.ENTITY_GHAST_SCREAM, 2, 2);
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Poke" + ChatColor.GRAY + "] You poked "
								+ ChatColor.BLUE + target.getDisplayName() + ChatColor.GRAY + "!!!");
						target.playSound(target.getLocation(), Sound.ENTITY_GHAST_SCREAM, 2, 2);
						p.playSound(p.getLocation(), Sound.BLOCK_WOODEN_BUTTON_CLICK_ON, 0, 2);
					}
				}
			}
		}

		
		
		
		return false;
		
	}
}
		
		
	
	


