package me.oggunderscore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.oggunderscore.Core.Main;

public class PlayerCommands implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		
		
		
		Player p = (Player) sender;
		//TODO: Fix for offline players
				if (cmd.getName().equalsIgnoreCase("stats")) {
					if (args.length == 0) {
						p.sendMessage("");
						p.sendMessage(ChatColor.AQUA + " " + ChatColor.BOLD + "       Statistics " + ChatColor.BLUE + "| "
								+ ChatColor.GRAY + p.getName());
						p.sendMessage("");
						//p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "  RANK " + ChatColor.BLUE + Main.getInstance().getConfig().getConfigurationSection(p.getName()).getString("RANK"));
						p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "  GOLD " + ChatColor.GOLD
								+ Main.getInstance().getConfig().getConfigurationSection(p.getName()).getString("GOLD"));
						p.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "  WINS " + ChatColor.LIGHT_PURPLE
								+ Main.getInstance().getConfig().getConfigurationSection(p.getName()).getString("WINS"));
						p.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "  LOSES " + ChatColor.WHITE
								+ Main.getInstance().getConfig().getConfigurationSection(p.getName()).getString("LOSES"));
						p.sendMessage("");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, 1);
					}
					if (args.length == 1) {
						for (Player players : Bukkit.getOnlinePlayers()) {
							if (args[0].equals(players.getName())) {
								p.sendMessage("");
								p.sendMessage(ChatColor.AQUA + " " + ChatColor.BOLD + "       Statistics " + ChatColor.BLUE
										+ "| " + ChatColor.GRAY + players.getName());
								p.sendMessage("");
								//p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "  RANK " + ChatColor.BLUE + Main.getInstance().getConfig().getConfigurationSection(players.getName()).getString("RANK"));
								p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "  GOLD " + ChatColor.GOLD
										+ Main.getInstance().getConfig().getConfigurationSection(players.getName()).getString("GOLD"));
								p.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "  WINS " + ChatColor.LIGHT_PURPLE
										+ Main.getInstance().getConfig().getConfigurationSection(players.getName()).get("WINS"));
								p.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "  LOSES " + ChatColor.WHITE
										+ Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("LOSES"));
								p.sendMessage("");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, 1);
							} else {
								p.playSound(p.getLocation(), Sound.ENTITY_BAT_HURT, 1, 1);
								p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY + "] "
										+ ChatColor.GRAY + "That player doesn't seem to be online!");
							}
						}
					}
				}
		
		if (cmd.getName().equalsIgnoreCase("discord")) {
			p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Discord" + ChatColor.GRAY
					+ "] Discord: https://discord.gg/k3KuhTt");
		}
		if (cmd.getName().equalsIgnoreCase("ranks")) {
			p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);

			p.sendMessage("");
			p.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "         -- " + ChatColor.BLUE + ""
					+ ChatColor.BOLD + "Member Tiers " + ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "-------");
			p.sendMessage("");
			p.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "Tier 1 (0 hours) " + ChatColor.AQUA + ""
					+ ChatColor.BOLD + "- " + ChatColor.YELLOW + ChatColor.BOLD + "⭑");
			p.sendMessage("    " + ChatColor.GRAY + "- /kit member, food");
			p.sendMessage("    " + ChatColor.GRAY + "- Basic Permissions");
			p.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "Tier 2 (10 hours) " + ChatColor.AQUA + ""
					+ ChatColor.BOLD + "- " + ChatColor.YELLOW + ChatColor.BOLD + "⭑⭑");
			p.sendMessage("    " + ChatColor.GRAY + "- /kit member2");
			p.sendMessage("    " + ChatColor.GRAY + "- 2 Homes, $1,000,000, 2 Player Vaults");
			p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Tier 3 (30 hours) " + ChatColor.AQUA + ""
					+ ChatColor.BOLD + "- " + ChatColor.YELLOW + ChatColor.BOLD + "⭑⭑⭑");
			p.sendMessage("    " + ChatColor.GRAY + "- /kit member3");
			p.sendMessage("    " + ChatColor.GRAY + "- 3 Homes, $10,000,000, 3 Player Vaults");
			p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Tier 4 (50 hours) " + ChatColor.AQUA + ""
					+ ChatColor.BOLD + "- " + ChatColor.YELLOW + ChatColor.BOLD + "⭑⭑⭑⭑");
			p.sendMessage("    " + ChatColor.GRAY + "- /kit member4");
			p.sendMessage("    " + ChatColor.GRAY + "- 4 Homes, $100,000,000, 4 Player Vaults");
			p.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Tier 5 (100 hours) " + ChatColor.AQUA + ""
					+ ChatColor.BOLD + "- " + ChatColor.YELLOW + ChatColor.BOLD + "⭑⭑⭑⭑⭑");
			p.sendMessage("    " + ChatColor.GRAY + "- /kit member5");
			p.sendMessage("    " + ChatColor.GRAY + "- 5 Homes, $1,000,000,000, 5 Player Vaults");

		}
		if (cmd.getName().equalsIgnoreCase("staff")) {

			p.sendMessage("");
			p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "      Staff List");
			p.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "---");
			p.sendMessage("");
			p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Owner: Riyrus");
			p.sendMessage(ChatColor.RED + "Managing Director and Founder of IgnitedMC");
			p.sendMessage("");
			p.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "---");
			p.sendMessage("");
			p.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Development Team: ogg_ and coopjc");
			p.sendMessage(ChatColor.DARK_AQUA
					+ "These are the server developers, they fix issues, and bugs related to Ignited. ");
			p.sendMessage("");
			p.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "---");
			p.sendMessage("");
			p.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + " Administrator: Kreed95");
			p.sendMessage(ChatColor.DARK_RED
					+ "This person manages staff, the community, reports for the server, websites, etc.");
			p.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "---");
			p.sendMessage("");
			p.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "Moderator: Inns and Alli");
			p.sendMessage(ChatColor.YELLOW
					+ "These are server moderators, they ban/kick/mute offensers, and basically are the guards of Ignited.");
			p.sendMessage("");
			p.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "---");
			p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "General Staff: 1amachip, ooklol, and xImDevil");
			p.sendMessage("");

		}
		if (cmd.getName().equalsIgnoreCase("apply")) {
			p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Info" + ChatColor.GRAY
					+ "] Applications are currently CLOSED! Please check back next month!");
		}
		if (cmd.getName().equalsIgnoreCase("website")) {
			p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Info" + ChatColor.GRAY
					+ "] Website: IgnitedMC.us");
		}
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

		if (cmd.getName().equalsIgnoreCase("commands")) {
			p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Info" + ChatColor.GRAY
					+ "] Availiable Commands:  /kit /event /apply /warp /wild /f /discord /website /apply /poke /kitpvp /fight /ffa /ffa2 /coreinfo /staff /ranks");
		}
		if (cmd.getName().equalsIgnoreCase("event")) {
			p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Event" + ChatColor.GRAY
					+ "] Staff Meeting: 12:00PM PST ALL STAFF REQUIRED!");
		}
		
		
		
		return false;
		
	}
}
		
		
	
	


