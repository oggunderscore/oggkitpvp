package me.oggunderscore.Commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.oggunderscore.Core.Events;
import me.oggunderscore.Core.Main;
import me.oggunderscore.Utils.Prefix;

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
					Events.cdEnlightment = 7;
					Events.cdIgnite = 4;
					Events.cdRegen = 8;
					Events.cdEarthquake = 7;
					Events.cdCorruptedOrb = 4;
					Events.cdDash = 2;
					Events.cdEscape = 4;
					Events.cdBlink = 4;
					Events.cdCloak = 10;
				} else {
					Main.getInstance().getConfig().set("urf", false);
					p.sendMessage(Prefix.corePrefix + "URF has been disabled!");
					Main.getPlugin(Main.class).saveConfig();
					Events.cdEnlightment = 13;
					Events.cdIgnite = 7;
					Events.cdRegen = 15;
					Events.cdEarthquake = 14;
					Events.cdCorruptedOrb = 7;
					Events.cdDash = 3;
					Events.cdEscape = 7;
					Events.cdBlink = 7;
					Events.cdCloak = 20;
				}
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
			}
		}
		return false;
	}

}