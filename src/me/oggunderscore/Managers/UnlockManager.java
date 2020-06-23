package me.oggunderscore.Managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.oggunderscore.Core.Main;
import me.oggunderscore.Utils.Inventories;
import me.oggunderscore.Utils.ItemStacks;
import me.oggunderscore.Utils.Prefix;

public class UnlockManager implements Listener {
	
	static Configuration config = Main.getPlugin(Main.class).getConfig();

	@EventHandler
	public void onUnlockMenuClick(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) { // Why is this safecheck needed?
			Player p = (Player) e.getWhoClicked();

			if (e.getCurrentItem() == null)
				return;
			ItemStack clicked = e.getCurrentItem();

			if (clicked.equals(ItemStacks.getItem("aresUnlock")) || 
			clicked.equals(ItemStacks.getItem("berserkerUnlock")) || 
			clicked.equals(ItemStacks.getItem("thorUnlock")) || 
			clicked.equals(ItemStacks.getItem("hadesUnlock")) ||
			clicked.equals(ItemStacks.getItem("apolloUnlock")) || 
			clicked.equals(ItemStacks.getItem("ninjaUnlock"))) {
				e.setCancelled(true);
				int currentGold = config.getConfigurationSection(p.getName()).getInt("GOLD");
				if (currentGold >= 2500) { // Price of Kits
					Inventories.setupConfirmation(clicked);
					p.closeInventory();
					p.openInventory(Inventories.confirmationGui);
				} else {
					p.closeInventory();
					p.sendMessage(Prefix.errorPrefix + "You do not have enough gold! You need " + (2500 - currentGold) + " more gold.");
				}
			}
			
			if (clicked.equals(ItemStacks.getItem("purchase"))) {
				e.setCancelled(true);
				String purchasedKit = null;
				Boolean executePurchase = false;
				if (e.getInventory().getItem(4).equals(ItemStacks.getItem("aresUnlock"))) {
					config.getConfigurationSection(p.getName()).set("ARES", true);
					executePurchase = true;
					purchasedKit = ChatColor.RED + "" + ChatColor.BOLD + "Ares";
				}
				if (e.getInventory().getItem(4).equals(ItemStacks.getItem("berserkerUnlock"))) {
					config.getConfigurationSection(p.getName()).set("BERSERKER", true);
					executePurchase = true;
					purchasedKit = ChatColor.RED + "" + ChatColor.BOLD + "Berserker";
				}
				if (e.getInventory().getItem(4).equals(ItemStacks.getItem("thorUnlock"))) {
					config.getConfigurationSection(p.getName()).set("THOR", true);
					executePurchase = true;
					purchasedKit = ChatColor.RED + "" + ChatColor.BOLD + "Thor";
				}
				if (e.getInventory().getItem(4).equals(ItemStacks.getItem("hadesUnlock"))) {
					config.getConfigurationSection(p.getName()).set("HADES", true);
					executePurchase = true;
					purchasedKit = ChatColor.RED + "" + ChatColor.BOLD + "Hades";
				}
				if (e.getInventory().getItem(4).equals(ItemStacks.getItem("aresUnlock"))) {
					config.getConfigurationSection(p.getName()).set("ARES", true);
					executePurchase = true;
					purchasedKit = ChatColor.RED + "" + ChatColor.BOLD + "Ares";
				}
				if (e.getInventory().getItem(4).equals(ItemStacks.getItem("apolloUnlock"))) {
					config.getConfigurationSection(p.getName()).set("APOLLO", true);
					executePurchase = true;
					purchasedKit = ChatColor.RED + "" + ChatColor.BOLD + "Apollo";
				}
				if (e.getInventory().getItem(4).equals(ItemStacks.getItem("ninjaUnlock"))) {
					config.getConfigurationSection(p.getName()).set("NINJA", true);
					executePurchase = true;
					purchasedKit = ChatColor.RED + "" + ChatColor.BOLD + "Ninja";
				}
				
				if (executePurchase == true) {
					config.getConfigurationSection(p.getName()).set("GOLD", config.getConfigurationSection(p.getName()).getInt("GOLD") - 2500);
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1); // What does 1 and 1 do
					p.closeInventory();
				}
				
				Main.getPlugin(Main.class).saveConfig();
				
				if (purchasedKit != null)
					Bukkit.broadcastMessage(Prefix.statusPrefix + p.getName() + " has unlocked " + purchasedKit); // Probably will remove as chat will get cluttered.
				
			}
			if (clicked.equals(ItemStacks.getItem("cancelPurchase"))) {
				e.setCancelled(true);
				// Back to Unlock Menu
				p.closeInventory();
				Inventories.setupUnlockGui(p);
				p.openInventory(Inventories.unlockGui);
			}
			
			
		}
	}
}
