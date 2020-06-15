package me.oggunderscore.Utils;

import me.oggunderscore.Core.Main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;

public class Inventories {

	static Configuration config = Main.getInstance().getConfig();
	
	public static Inventory kitGui = Bukkit.createInventory(null, 27, "KitPVP Menu");
	public static Inventory itemGui = Bukkit.createInventory(null, 9, "Item Menu");
	public static Inventory settingsGui = Bukkit.createInventory(null, 9, "KitPVP Settings");
	public static Inventory statsGui = Bukkit.createInventory(null, 9, "KitPVP Stats");
	public static Inventory serverGui = Bukkit.createInventory(null, 9, "Server Information");
	public static Inventory unlockGui = Bukkit.createInventory(null, 9, "Unlock Menu");
	public static Inventory confirmationGui = Bukkit.createInventory(null, 9, "Unlock Confirmation");


	public static void setupUIButtons() {
		kitGui.setItem(0, ItemStacks.getItem("Default"));
		kitGui.setItem(1, ItemStacks.getItem("tank"));
		kitGui.setItem(2, ItemStacks.getItem("ares"));
		kitGui.setItem(3, ItemStacks.getItem("berserker"));
		kitGui.setItem(4, ItemStacks.getItem("thor"));
		kitGui.setItem(5, ItemStacks.getItem("apollo"));
		kitGui.setItem(6, ItemStacks.getItem("hades"));
		kitGui.setItem(7, ItemStacks.getItem("ninja"));

		kitGui.setItem(9, ItemStacks.getItem("ffa"));
		kitGui.setItem(13, ItemStacks.getItem("leave"));
		kitGui.setItem(22, ItemStacks.getItem("unlockButton"));
		kitGui.setItem(23, ItemStacks.getItem("settingsButton"));
		kitGui.setItem(24, ItemStacks.getItem("statsButton"));
		kitGui.setItem(25, ItemStacks.getItem("infoButton"));
		kitGui.setItem(26, ItemStacks.getItem("closeMenu"));
		// InventoryManager.kitGui.setItem(10, ItemStacks.getItem("ffa2"));
	}

	public static void setupMenu(Player p) {
		setupUIButtons();
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT").equals("DEFAULT")) {
			kitGui.setItem(0, ItemStacks.getItem("DefaultSelected"));
			kitGui.getItem(0).addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT").equals("TANK")) {
			kitGui.setItem(1, ItemStacks.getItem("tankSelected"));
			kitGui.getItem(1).addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT").equals("ARES")) {
			kitGui.setItem(2, ItemStacks.getItem("aresSelected"));
			kitGui.getItem(2).addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT").equals("BERSERKER")) {
			kitGui.setItem(3, ItemStacks.getItem("berserkerSelected"));
			kitGui.getItem(3).addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT").equals("THOR")) {
			kitGui.setItem(4, ItemStacks.getItem("thorSelected"));
			kitGui.getItem(4).addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT").equals("APOLLO")) {
			kitGui.setItem(5, ItemStacks.getItem("apolloSelected"));
			kitGui.getItem(5).addUnsafeEnchantment(Enchantment.DURABILITY, 10);

		}
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT").equals("HADES")) {
			kitGui.setItem(6, ItemStacks.getItem("hadesSelected"));
			kitGui.getItem(6).addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT").equals("NINJA")) {
			kitGui.setItem(7, ItemStacks.getItem("ninjaSelected"));
			kitGui.getItem(7).addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		}
	}

	public static int getNumOfKitsUnowned(Player p) {
		ConfigurationSection playerConfig = config.getConfigurationSection(p.getName());
		int numOfUnownedKits = 0;

		if (playerConfig.getBoolean("ARES") == false) {
			numOfUnownedKits++;
		}
		if (playerConfig.getBoolean("BERSERKER") == false) {
			numOfUnownedKits++;
		}
		if (playerConfig.getBoolean("THOR") == false) {
			numOfUnownedKits++;
		}
		if (playerConfig.getBoolean("HADES") == false) {
			numOfUnownedKits++;
		}
		if (playerConfig.getBoolean("APOLLO") == false) {
			numOfUnownedKits++;
		}
		if (playerConfig.getBoolean("NINJA") == false) {
			numOfUnownedKits++;
		}

		return numOfUnownedKits;
	}

	public static void setupUnlockGui(Player p) {

		boolean ownsAllKits = false;
		// Setup config
		ConfigurationSection playerConfig = config.getConfigurationSection(p.getName());

		// Check how many kits player owns
		// Will tell player they own all kits
		if (getNumOfKitsUnowned(p) == 0) {
			// Display item Stack "ALL KITS UNLOCKED! More coming soon..."
			unlockGui.setItem(0, ItemStacks.getItem("allKitsUnlocked"));
			// Is cancelled from pickup in MenuManager
			ownsAllKits = true;
		}

		// Setup back button
		unlockGui.setItem(8, ItemStacks.getItem("backButton"));


		if (ownsAllKits == false) {
			// List to hold string of unownedKits
			ArrayList<String> unownedKits = new ArrayList<String>();
			// Check for owned kits and assign itemstacks to menu
			if (playerConfig.getBoolean("ARES") == false) {
				unownedKits.add("aresUnlock");
			}
			if (playerConfig.getBoolean("BERSERKER") == false) {
				unownedKits.add("berserkerUnlock");
			}
			if (playerConfig.getBoolean("THOR") == false) {
				unownedKits.add("thorUnlock");
			}
			if (playerConfig.getBoolean("HADES") == false) {
				unownedKits.add("hadesUnlock");
			}
			if (playerConfig.getBoolean("APOLLO") == false) {
				unownedKits.add("apolloUnlock");
			}
			if (playerConfig.getBoolean("NINJA") == false) {
				unownedKits.add("ninjaUnlock");
			}

			int numUnownedKits = unownedKits.size();

			for (int x = 0; x < numUnownedKits; x++) {
				unlockGui.setItem(x, ItemStacks.getItem(unownedKits.get(0))); // Hope this is how it goes?
				unownedKits.remove(0);
			}
			// TODO: Confirmation screen and Purchasing and click events.
		}

	}
	
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
