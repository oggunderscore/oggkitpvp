package me.oggunderscore.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;

public class Inventories {
	
	public static Inventory kitGui = Bukkit.createInventory(null, 18, "KitPVP Menu");
	public static Inventory itemGui = Bukkit.createInventory(null, 9, "Item Menu");
	public static Inventory settingsGui = Bukkit.createInventory(null, 9, "KitPVP Settings");
	public static Inventory statsGui = Bukkit.createInventory(null, 9, "KitPVP Stats");
	public static Inventory serverGui = Bukkit.createInventory(null, 9, "Server Information");
	
	public static void clear(Player p) {
		if (p.getWorld().equals(Bukkit.getServer().getWorld("world2"))) {
			for (PotionEffect effect : p.getActivePotionEffects()) {
				p.removePotionEffect(effect.getType());
			}
			p.getInventory().clear();
			p.getInventory().setHelmet(null);
			p.getInventory().setChestplate(null);
			p.getInventory().setLeggings(null);
			p.getInventory().setBoots(null);
			p.setFireTicks(0);
			p.setExp(0);
			p.setLevel(0);
			p.setHealth(20.0);
			p.setFoodLevel(20);
		}
	}
}
