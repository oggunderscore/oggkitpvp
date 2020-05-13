
package me.oggunderscore.Managers;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import me.oggunderscore.Core.Main;
import me.oggunderscore.Utils.Inventories;
import me.oggunderscore.Utils.Kits;
import me.oggunderscore.Utils.Locations;
import me.oggunderscore.Utils.Prefix;

public class FightManager implements CommandExecutor, Listener {

	public static final ArrayList<Object> invited = new ArrayList<Object>();
	public static final ArrayList<Object> inFight = new ArrayList<Object>();
	public static final ArrayList<Object> fightList = new ArrayList<Object>();

	public static Player fighter1;
	public static Player fighter2;
	public static Player winner;
	public static Player loser;

	private int cancel;

	public void startGame() {
		FightManager.fightList.remove(fighter1);
		FightManager.fightList.remove(fighter2);

		fighter1.teleport(ArenaManager.chosenArena1);
		fighter2.teleport(ArenaManager.chosenArena2);

		Inventories.clear(fighter1);
		Inventories.clear(fighter2);

		if (Main.getInstance().getConfig().getConfigurationSection(fighter1.getName()).get("KIT").equals("null")) {
			Kits.setKitDefault(fighter1);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(fighter1.getName()).get("KIT").equals("DEFAULT")) {
			Kits.setKitDefault(fighter1);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(fighter1.getName()).get("KIT").equals("ARES")) {
			Kits.setKitAres(fighter1);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(fighter1.getName()).get("KIT").equals("BERSERKER")) {
			Kits.setKitBerserker(fighter1);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(fighter1.getName()).get("KIT").equals("TANK")) {
			Kits.setKitTank(fighter1);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(fighter1.getName()).get("KIT").equals("THOR")) {
			Kits.setKitThor(fighter1);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(fighter1.getName()).get("KIT").equals("NINJA")) {
			Kits.setKitNinja(fighter1);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(fighter1.getName()).get("KIT").equals("HADES")) {
			Kits.setKitHades(fighter1);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(fighter1.getName()).get("KIT").equals("APOLLO")) {
			Kits.setKitApollo(fighter1);
		}

		if (Main.getInstance().getConfig().getConfigurationSection(fighter2.getName()).get("KIT").equals("null")) {
			Kits.setKitDefault(fighter2);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(fighter2.getName()).get("KIT").equals("DEFAULT")) {
			Kits.setKitDefault(fighter2);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(fighter2.getName()).get("KIT").equals("ARES")) {
			Kits.setKitAres(fighter2);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(fighter2.getName()).get("KIT").equals("BERSERKER")) {
			Kits.setKitBerserker(fighter2);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(fighter2.getName()).get("KIT").equals("TANK")) {
			Kits.setKitTank(fighter2);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(fighter2.getName()).get("KIT").equals("THOR")) {
			Kits.setKitThor(fighter2);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(fighter2.getName()).get("KIT").equals("NINJA")) {
			Kits.setKitNinja(fighter2);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(fighter2.getName()).get("KIT").equals("HADES")) {
			Kits.setKitHades(fighter2);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(fighter2.getName()).get("KIT").equals("APOLLO")) {
			Kits.setKitApollo(fighter2);
		}
		FightManager.inFight.add(fighter1);
		FightManager.inFight.add(fighter2);

		fighter1.setGameMode(GameMode.ADVENTURE);
		fighter2.setGameMode(GameMode.ADVENTURE);

		// TODO: Match Timer
		fighter1.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Fight" + ChatColor.GRAY
				+ "] You are now fighting " + ChatColor.AQUA + fighter2.getName() + ChatColor.GRAY + "! Good Luck!");
		fighter2.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Fight" + ChatColor.GRAY
				+ "] You are now fighting " + ChatColor.AQUA + fighter1.getName() + ChatColor.GRAY + "! Good Luck!");
		fighter1.playSound(fighter1.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
		fighter2.playSound(fighter2.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

	}

	public void preGame() {
		// TODO: Will add match timer to here, using everything in startGame();, but
		// start game just unfreezes them.

	}

	public static void endGame() {

		Object newWinnerGold = (Integer) Main.getInstance().getConfig().getConfigurationSection(winner.getName()).get("GOLD") + 150;
		Object newLoserGold = (Integer) Main.getInstance().getConfig().getConfigurationSection(loser.getName()).get("GOLD") + 75;
		Object newWinnerWins = (Integer) Main.getInstance().getConfig().getConfigurationSection(winner.getName()).get("WINS") + 1;
		Object newLoserLoses = (Integer) Main.getInstance().getConfig().getConfigurationSection(loser.getName()).get("LOSES") + 1;

		Main.getInstance().getConfig().getConfigurationSection(winner.getName()).set("GOLD", newWinnerGold);
		Main.getInstance().getConfig().getConfigurationSection(loser.getName()).set("GOLD", newLoserGold);
		Main.getInstance().getConfig().getConfigurationSection(winner.getName()).set("WINS", newWinnerWins);
		Main.getInstance().getConfig().getConfigurationSection(loser.getName()).set("LOSES", newLoserLoses);
		Main.getPlugin(Main.class).saveConfig();

		winner.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Fight" + ChatColor.GRAY + "] Your new Gold is "
				+ ChatColor.GOLD + newWinnerGold);
		loser.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Fight" + ChatColor.GRAY + "] Your new Gold is "
				+ ChatColor.GOLD + newLoserGold);

		FightManager.inFight.remove(fighter1);
		FightManager.inFight.remove(fighter2);

		loser.teleport(Locations.kitpvpSpawn);
		loser.setGameMode(GameMode.ADVENTURE);
		loser.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Fight" + ChatColor.GRAY
				+ "] You have lost the match against " + ChatColor.GREEN + winner.getName() + ChatColor.GRAY + "!");
		Inventories.clear(loser);
		loser.playSound(loser.getLocation(), Sound.ENTITY_HORSE_HURT, 1, 1);

		winner.teleport(Locations.kitpvpSpawn);
		winner.setGameMode(GameMode.ADVENTURE);
		winner.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Fight" + ChatColor.GRAY
				+ "] You have won the match against " + ChatColor.RED + loser.getName() + ChatColor.GRAY + "!");
		Inventories.clear(winner);
		winner = null;
		loser = null;
		fighter1 = null;
		fighter2 = null;

		ArenaManager.rotateArenas();

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("fight")) {
			if (!(FFAManager.inFfa.contains(p))) {
				if (!(Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT")).equals(null)) {
					if (args.length == 0) {
						if (!(FightManager.fightList.contains(p))) {
							FightManager.fightList.add(p);
							Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Server" + ChatColor.GRAY
									+ "] " + ChatColor.AQUA + p.getName() + ChatColor.GRAY
									+ " has joined the Open Fight List! Type /fight " + p.getName());
						} else {
							FightManager.fightList.remove(p);
							Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Server" + ChatColor.GRAY
									+ "] " + ChatColor.AQUA + p.getName() + ChatColor.GRAY
									+ " has left the Open Fight List!");

						}
						// TODO: check if player is in queue then leave
					}
					if (args.length == 1) {
						for (Player players : Bukkit.getOnlinePlayers()) {
							if (args[0].equalsIgnoreCase(players.getName())) {
								if (FightManager.fightList.contains(p)) {
									p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY
											+ "] Please leave the Open Fights Queue first before fighting a player!");
									cancel = 1;
								}
								if (inFight.size() >= 1) {
									p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY
											+ "] A fight is currently going on! Please wait!");
									cancel = 1;
								}
								if (cancel == 0) {
									if (fightList.contains(Bukkit.getPlayer(args[0]))) {
										if (!(inFight.size() == 2)) {
											fighter1 = p;
											fighter2 = Bukkit.getPlayer(args[0]);
											startGame();
											Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Fight"
													+ ChatColor.GRAY + "] Match: " + ChatColor.RED + fighter1.getName()
													+ ChatColor.WHITE + "" + ChatColor.BOLD + " V.S. " + ChatColor.AQUA
													+ fighter2.getName() + ChatColor.GOLD + "" + ChatColor.BOLD + ""
													+ " " + ChatColor.MAGIC + "|" + ChatColor.RESET + " "
													+ ChatColor.LIGHT_PURPLE + ArenaManager.arenaName);
										}
									}
								}
								cancel = 0;
							}
						}
						if (args[0].equalsIgnoreCase("list")) {
							p.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Fight" + ChatColor.GRAY
									+ "] Availiable Fighters: " + FightManager.fightList.toString());
						}
						if (args[0].equalsIgnoreCase("spawn")) {
							p.sendMessage(Prefix.corePrefix + "You have been teleported to the KitPVP Spawn!");
							p.teleport(Locations.kitpvpSpawn);
						}
						if (args[0].equalsIgnoreCase("spectate")) {
							if (ArenaManager.arenaNumber == 0) {
								p.teleport(Locations.arena0spectate);
								p.sendMessage(Prefix.fightPrefix + "Now spectating match: " + ChatColor.RED
										+ fighter1.getName() + ChatColor.WHITE + "" + ChatColor.BOLD + " V.S. "
										+ ChatColor.AQUA + fighter2.getName() + ChatColor.GRAY + " in "
										+ ChatColor.LIGHT_PURPLE + ArenaManager.arenaName);
							} else if (ArenaManager.arenaNumber == 1) {
								p.teleport(Locations.arena0spectate);
								p.sendMessage(Prefix.fightPrefix + "Now spectating match: " + ChatColor.RED
										+ fighter1.getName() + ChatColor.WHITE + "" + ChatColor.BOLD + " V.S. "
										+ ChatColor.AQUA + fighter2.getName() + ChatColor.GRAY + " in "
										+ ChatColor.LIGHT_PURPLE + ArenaManager.arenaName);
							} else if (ArenaManager.arenaNumber == 2) {
								p.teleport(Locations.arena0spectate);
								p.sendMessage(Prefix.fightPrefix + "Now spectating match: " + ChatColor.RED
										+ fighter1.getName() + ChatColor.WHITE + "" + ChatColor.BOLD + " V.S. "
										+ ChatColor.AQUA + fighter2.getName() + ChatColor.GRAY + " in "
										+ ChatColor.LIGHT_PURPLE + ArenaManager.arenaName);
							} else if (ArenaManager.arenaNumber == 3) {
								p.teleport(Locations.arena0spectate);
								p.sendMessage(Prefix.fightPrefix + "Now spectating match: " + ChatColor.RED
										+ fighter1.getName() + ChatColor.WHITE + "" + ChatColor.BOLD + " V.S. "
										+ ChatColor.AQUA + fighter2.getName() + ChatColor.GRAY + " in "
										+ ChatColor.LIGHT_PURPLE + ArenaManager.arenaName);
							} else if (ArenaManager.arenaNumber == 4) {

							}
						}
					}
				} else {
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Kit" + ChatColor.GRAY
							+ "] Please chose a Kit first before Fighting!");
					p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
				}
			} else {
				p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "FFA" + ChatColor.GRAY
						+ "] Please leave the FFA Arena before Fighting!");
				p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
			}
		}
		return false;
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {

		Player p = e.getEntity();

		if (FightManager.inFight.contains(fighter1) || FightManager.inFight.contains(fighter2)) {
			winner = p.getKiller();
			loser = p;
			endGame();
		}
	}

}
