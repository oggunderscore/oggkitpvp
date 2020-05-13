package me.oggunderscore.Managers;

import java.util.ArrayList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.event.Listener;
import me.oggunderscore.Core.Main;

public class FFAManager implements CommandExecutor, Listener {
	
	public static final ArrayList<Object> inFfa = new ArrayList<Object>();
	
	static Configuration config = Main.getPlugin(Main.class).getConfig();
	
	public static int ffaCancel = 0;
	
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		/*
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("FFA")) {
			ffaCancel = 0;
			if (!(inFfa.contains(p))) {
				if (!(config.getConfigurationSection(p.getName()).get("KIT")).equals(null)) {
				if (FightManager.fightList.contains(p)) {
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY + "] Please leave the Fight Queue before joining the FFA Arena!");
					ffaCancel = 1;
				}
				if (FightManager.inFight.contains(p)) {
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY + "] Please finish the Fight before joining the FFA Arena!");
					ffaCancel = 1;
				}
				
				if (ffaCancel == 0) {
					p.teleport(Locations.ffaspawn);
					p.setGameMode(GameMode.ADVENTURE);
					InventoryManager.clear(p);
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
					Main.broadcast(ChatColor.GRAY + "[" + ChatColor.BLUE + "FFA" + ChatColor.GRAY + "] " + ChatColor.AQUA + p.getName() + ChatColor.GRAY + " has joined the FFA Arena!");
					inFfa.add(p);
					
					if (config.getConfigurationSection(p.getName()).get("KIT").equals("DEFAULT")) {
						Kits.setKitDefault(p);
					}
					if (config.getConfigurationSection(p.getName()).get("KIT").equals("TANK")) {
						Kits.setKitTank(p);
					}
					if (config.getConfigurationSection(p.getName()).get("KIT").equals("BERSERKER")) {
						Kits.setKitBerserker(p);
					}
					if (config.getConfigurationSection(p.getName()).get("KIT").equals("APOLLO")) {
						Kits.setKitApollo(p);
					}
					if (config.getConfigurationSection(p.getName()).get("KIT").equals("HADES")) {
						Kits.setKitHades(p);
					}
					if (config.getConfigurationSection(p.getName()).get("KIT").equals("NINJA")) {
						Kits.setKitNinja(p);
					}
					if (config.getConfigurationSection(p.getName()).get("KIT").equals("THOR")) {
						Kits.setKitThor(p);
					}
					if (config.getConfigurationSection(p.getName()).get("KIT").equals("ARES")) {
						Kits.setKitAres(p);
					}
					
					
					
				}
				
				
			
				
				
			} else {
				p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Kit" + ChatColor.GRAY + "] Please chose a Kit first before joining the FFA Arena!");
				p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
			}
			} else {
				p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "FFA" + ChatColor.GRAY + "] You cannot use /ffa while in the FFA Arena!");
				p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
			}
		}
		
		if (cmd.getName().equalsIgnoreCase("FFA2")) {
			ffaCancel = 0;
			if (!(inFfa.contains(p))) {
			if (!(config.getConfigurationSection(p.getName()).get("KIT")).equals(null)) {
				if (FightManager.fightList.contains(p)) {
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY + "] Please leave the Fight Queue before joining the FFA Arena!");
					ffaCancel = 1;
				}
				if (FightManager.inFight.contains(p)) {
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY + "] Please finish the Fight before joining the FFA Arena!");
					ffaCancel = 1;
				}
				
				if (ffaCancel == 0) {
					p.setGameMode(GameMode.SURVIVAL);
					InventoryManager.clear(p);
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
					Main.broadcast(ChatColor.GRAY + "[" + ChatColor.BLUE + "FFA" + ChatColor.GRAY + "] " + ChatColor.AQUA + p.getName() + ChatColor.GRAY + " has joined the FFA Arena!");
					inFfa.add(p);
					
					if (config.getConfigurationSection(p.getName()).get("KIT").equals("DEFAULT")) {
						Kits.setKitDefault(p);
					}
					if (config.getConfigurationSection(p.getName()).get("KIT").equals("TANK")) {
						Kits.setKitTank(p);
					}
					if (config.getConfigurationSection(p.getName()).get("KIT").equals("BERSERKER")) {
						Kits.setKitBerserker(p);
					}
					if (config.getConfigurationSection(p.getName()).get("KIT").equals("APOLLO")) {
						Kits.setKitApollo(p);
					}
					if (config.getConfigurationSection(p.getName()).get("KIT").equals("HADES")) {
						Kits.setKitHades(p);
					}
					if (config.getConfigurationSection(p.getName()).get("KIT").equals("NINJA")) {
						Kits.setKitNinja(p);
					}
					if (config.getConfigurationSection(p.getName()).get("KIT").equals("THOR")) {
						Kits.setKitThor(p);
					}
					if (config.getConfigurationSection(p.getName()).get("KIT").equals("ARES")) {
						Kits.setKitAres(p);
					}
					
					
					//Random Locations of FFA2
					Random random = new Random();
					int choice = random.nextInt(5);
					if (choice == 0) {
						p.teleport(Locations.ffa2spawn1);
					} 
					if (choice == 1) {
						p.teleport(Locations.ffa2spawn2);
					} 
					if (choice == 2) {
						p.teleport(Locations.ffa2spawn3);
					} 
					if (choice == 3) {
						p.teleport(Locations.ffa2spawn4);
					} 
					if (choice == 4) {
						p.teleport(Locations.ffa2spawn5);
					} 
					if (choice == 5) {
						p.teleport(Locations.ffa2spawn6);
					} 
					
				}
				
				
			
				
				
			} else {
				p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Kit" + ChatColor.GRAY + "] Please chose a Kit first before joining the FFA Arena!");
				p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
			}
			} else {
				p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "FFA" + ChatColor.GRAY + "] You cannot use /ffa while in the FFA Arena!");
				p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
			}
		}
		
		
		
		
		*/
		
		return false;
	}
	

}
