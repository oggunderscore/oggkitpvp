package me.oggunderscore.Managers;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.oggunderscore.Core.Main;
import me.oggunderscore.Utils.Inventories;
import me.oggunderscore.Utils.ItemStacks;
import me.oggunderscore.Utils.Kits;
import me.oggunderscore.Utils.Locations;
import me.oggunderscore.Utils.Worlds;

public class MenuManager implements Listener {

	public static void setupUIButtons() {
		Inventories.kitGui.setItem(0, ItemStacks.getItem("Default"));
		Inventories.kitGui.setItem(1, ItemStacks.getItem("tank"));
		Inventories.kitGui.setItem(2, ItemStacks.getItem("ares"));
		Inventories.kitGui.setItem(3, ItemStacks.getItem("berserker"));
		Inventories.kitGui.setItem(4, ItemStacks.getItem("thor"));
		Inventories.kitGui.setItem(5, ItemStacks.getItem("apollo"));
		Inventories.kitGui.setItem(6, ItemStacks.getItem("hades"));
		Inventories.kitGui.setItem(7, ItemStacks.getItem("ninja"));

		Inventories.kitGui.setItem(9, ItemStacks.getItem("ffa"));
		Inventories.kitGui.setItem(13, ItemStacks.getItem("leave"));
		Inventories.kitGui.setItem(15, ItemStacks.getItem("statsButton"));
		Inventories.kitGui.setItem(16, ItemStacks.getItem("infoButton"));
		Inventories.kitGui.setItem(17, ItemStacks.getItem("closeMenu"));
		// InventoryManager.kitGui.setItem(10, ItemStacks.getItem("ffa2"));
	}

	public static void setupMenu(Player p) {
		setupUIButtons();
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT").equals("DEFAULT")) {
			Inventories.kitGui.setItem(0, ItemStacks.getItem("DefaultSelected"));
			Inventories.kitGui.getItem(0).addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT").equals("TANK")) {
			Inventories.kitGui.setItem(1, ItemStacks.getItem("tankSelected"));
			Inventories.kitGui.getItem(1).addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT").equals("ARES")) {
			Inventories.kitGui.setItem(2, ItemStacks.getItem("aresSelected"));
			Inventories.kitGui.getItem(2).addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT").equals("BERSERKER")) {
			Inventories.kitGui.setItem(3, ItemStacks.getItem("berserkerSelected"));
			Inventories.kitGui.getItem(3).addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT").equals("THOR")) {
			Inventories.kitGui.setItem(4, ItemStacks.getItem("thorSelected"));
			Inventories.kitGui.getItem(4).addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT").equals("APOLLO")) {
			Inventories.kitGui.setItem(5, ItemStacks.getItem("apolloSelected"));
			Inventories.kitGui.getItem(5).addUnsafeEnchantment(Enchantment.DURABILITY, 10);

		}
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT").equals("HADES")) {
			Inventories.kitGui.setItem(6, ItemStacks.getItem("hadesSelected"));
			Inventories.kitGui.getItem(6).addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		}
		if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT").equals("NINJA")) {
			Inventories.kitGui.setItem(7, ItemStacks.getItem("ninjaSelected"));
			Inventories.kitGui.getItem(7).addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack item = e.getItem();

		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

			if (item == null) { // Null check
				return;
			}

			if (item.equals(ItemStacks.getItem("kitpvpButton"))) {
				e.setCancelled(true); // Dont do anything with the item, cancel it.
				setupMenu(p); // Setup the menu for player p specifically.
				p.openInventory(Inventories.kitGui);
			}

		}
	}

	@EventHandler
	public void onInventoryMove(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();

			if (e.getCurrentItem() == null)
				return;
			ItemStack clicked = e.getCurrentItem();

			if (clicked.equals(ItemStacks.getItem("default"))) {
				e.setCancelled(true);
				e.setCurrentItem(ItemStacks.getItem("defaultSelected"));
				e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 10);
				Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("KIT", "DEFAULT");
				Main.getPlugin(Main.class).saveConfig();
				p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
				e.getInventory().setItem(1, ItemStacks.getItem("tank"));
				e.getInventory().setItem(2, ItemStacks.getItem("ares"));
				e.getInventory().setItem(3, ItemStacks.getItem("berserker"));
				e.getInventory().setItem(4, ItemStacks.getItem("thor"));
				e.getInventory().setItem(5, ItemStacks.getItem("apollo"));
				e.getInventory().setItem(6, ItemStacks.getItem("hades"));
				e.getInventory().setItem(7, ItemStacks.getItem("ninja"));
			} else if (clicked.equals(ItemStacks.getItem("tank"))) {
				e.setCancelled(true);
				e.setCurrentItem(ItemStacks.getItem("tankSelected"));
				e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 10);
				Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("KIT", "TANK");
				Main.getPlugin(Main.class).saveConfig();
				p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
				e.getInventory().setItem(0, ItemStacks.getItem("default"));
				e.getInventory().setItem(2, ItemStacks.getItem("ares"));
				e.getInventory().setItem(3, ItemStacks.getItem("berserker"));
				e.getInventory().setItem(4, ItemStacks.getItem("thor"));
				e.getInventory().setItem(5, ItemStacks.getItem("apollo"));
				e.getInventory().setItem(6, ItemStacks.getItem("hades"));
				e.getInventory().setItem(7, ItemStacks.getItem("ninja"));
			} else if (clicked.equals(ItemStacks.getItem("ares"))) {
				if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("ARES").equals(true)) {
					e.setCancelled(true);
					e.setCurrentItem(ItemStacks.getItem("aresSelected"));
					e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 10);
					Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("KIT", "ARES");
					Main.getPlugin(Main.class).saveConfig();
					p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
					e.getInventory().setItem(1, ItemStacks.getItem("tank"));
					e.getInventory().setItem(0, ItemStacks.getItem("default"));
					e.getInventory().setItem(3, ItemStacks.getItem("berserker"));
					e.getInventory().setItem(4, ItemStacks.getItem("thor"));
					e.getInventory().setItem(5, ItemStacks.getItem("apollo"));
					e.getInventory().setItem(6, ItemStacks.getItem("hades"));
					e.getInventory().setItem(7, ItemStacks.getItem("ninja"));
				} else {
					e.setCancelled(true);
					p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY
							+ "] You do not own this Kit!");
				}
			} else if (clicked.equals(ItemStacks.getItem("berserker"))) {
				if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("BERSERKER").equals(true)) {
					e.setCancelled(true);
					e.setCurrentItem(ItemStacks.getItem("berserkerSelected"));
					e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 10);
					Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("KIT", "BERSERKER");
					Main.getPlugin(Main.class).saveConfig();
					p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
					e.getInventory().setItem(1, ItemStacks.getItem("tank"));
					e.getInventory().setItem(2, ItemStacks.getItem("ares"));
					e.getInventory().setItem(0, ItemStacks.getItem("default"));
					e.getInventory().setItem(4, ItemStacks.getItem("thor"));
					e.getInventory().setItem(5, ItemStacks.getItem("apollo"));
					e.getInventory().setItem(6, ItemStacks.getItem("hades"));
					e.getInventory().setItem(7, ItemStacks.getItem("ninja"));
				} else {
					e.setCancelled(true);
					p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY
							+ "] You do not own this Kit!");
				}
			} else if (clicked.equals(ItemStacks.getItem("thor"))) {
				if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("THOR").equals(true)) {
					e.setCancelled(true);
					e.setCurrentItem(ItemStacks.getItem("thorSelected"));
					e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 10);
					Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("KIT", "THOR");
					Main.getPlugin(Main.class).saveConfig();
					p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
					e.getInventory().setItem(1, ItemStacks.getItem("tank"));
					e.getInventory().setItem(2, ItemStacks.getItem("ares"));
					e.getInventory().setItem(3, ItemStacks.getItem("berserker"));
					e.getInventory().setItem(0, ItemStacks.getItem("default"));
					e.getInventory().setItem(5, ItemStacks.getItem("apollo"));
					e.getInventory().setItem(6, ItemStacks.getItem("hades"));
					e.getInventory().setItem(7, ItemStacks.getItem("ninja"));
				} else {
					e.setCancelled(true);
					p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY
							+ "] You do not own this Kit!");
				}
			} else if (clicked.equals(ItemStacks.getItem("apollo"))) {
				if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("APOLLO").equals(true)) {
					e.setCancelled(true);
					e.setCurrentItem(ItemStacks.getItem("apolloSelected"));
					e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 10);
					Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("KIT", "APOLLO");
					Main.getPlugin(Main.class).saveConfig();
					p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
					e.getInventory().setItem(1, ItemStacks.getItem("tank"));
					e.getInventory().setItem(2, ItemStacks.getItem("ares"));
					e.getInventory().setItem(3, ItemStacks.getItem("berserker"));
					e.getInventory().setItem(4, ItemStacks.getItem("thor"));
					e.getInventory().setItem(0, ItemStacks.getItem("default"));
					e.getInventory().setItem(6, ItemStacks.getItem("hades"));
					e.getInventory().setItem(7, ItemStacks.getItem("ninja"));
				} else {
					e.setCancelled(true);
					p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY
							+ "] You do not own this Kit!");
				}
			} else if (clicked.equals(ItemStacks.getItem("hades"))) {
				if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("HADES").equals(true)) {
					e.setCancelled(true);
					e.setCurrentItem(ItemStacks.getItem("hadesSelected"));
					e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 10);
					Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("KIT", "HADES");
					Main.getPlugin(Main.class).saveConfig();
					p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
					e.getInventory().setItem(1, ItemStacks.getItem("tank"));
					e.getInventory().setItem(2, ItemStacks.getItem("ares"));
					e.getInventory().setItem(3, ItemStacks.getItem("berserker"));
					e.getInventory().setItem(4, ItemStacks.getItem("thor"));
					e.getInventory().setItem(5, ItemStacks.getItem("apollo"));
					e.getInventory().setItem(0, ItemStacks.getItem("default"));
					e.getInventory().setItem(7, ItemStacks.getItem("ninja"));
				} else {
					e.setCancelled(true);
					p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY
							+ "] You do not own this Kit!");
				}
			} else if (clicked.equals(ItemStacks.getItem("ninja"))) {
				if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("NINJA").equals(true)) {
					e.setCancelled(true);
					e.setCurrentItem(ItemStacks.getItem("ninjaSelected"));
					e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 10);
					Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("KIT", "NINJA");
					Main.getPlugin(Main.class).saveConfig();
					p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
					e.getInventory().setItem(1, ItemStacks.getItem("tank"));
					e.getInventory().setItem(2, ItemStacks.getItem("ares"));
					e.getInventory().setItem(3, ItemStacks.getItem("berserker"));
					e.getInventory().setItem(4, ItemStacks.getItem("thor"));
					e.getInventory().setItem(5, ItemStacks.getItem("apollo"));
					e.getInventory().setItem(6, ItemStacks.getItem("hades"));
					e.getInventory().setItem(0, ItemStacks.getItem("default"));
				} else {
					e.setCancelled(true);
					p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY
							+ "] You do not own this Kit!");
				}
			} else if (clicked.equals(ItemStacks.getItem("ffa"))) {
				e.setCancelled(true);

				if (!(FFAManager.inFfa.contains(p))) {
					if (!(Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT"))
							.equals(null)) {
						if (FightManager.fightList.contains(p)) {
							p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY
									+ "] Please leave the Fight Queue before joining the FFA Arena!");
							FFAManager.ffaCancel = 1;
							p.closeInventory();
						}
						if (FightManager.inFight.contains(p)) {
							p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY
									+ "] Please finish the Fight before joining the FFA Arena!");
							FFAManager.ffaCancel = 1;
						}

						if (FFAManager.ffaCancel == 0) {
							p.closeInventory();
							p.teleport(Locations.ffaspawn);

							p.setHealth(20.0);
							p.setGameMode(GameMode.ADVENTURE);

							Inventories.clear(p);
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
							Bukkit.getServer()
									.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "FFA" + ChatColor.GRAY
											+ "] " + ChatColor.AQUA + p.getName() + ChatColor.GRAY
											+ " has joined the FFA Arena!");
							FFAManager.inFfa.add(p);

							if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT")
									.equals("DEFAULT")) {
								Kits.setKitDefault(p);
							}
							if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT")
									.equals("TANK")) {
								Kits.setKitTank(p);
							}
							if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT")
									.equals("BERSERKER")) {
								Kits.setKitBerserker(p);
							}
							if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT")
									.equals("APOLLO")) {
								Kits.setKitApollo(p);
							}
							if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT")
									.equals("HADES")) {
								Kits.setKitHades(p);
							}
							if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT")
									.equals("NINJA")) {
								Kits.setKitNinja(p);
							}
							if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT")
									.equals("THOR")) {
								Kits.setKitThor(p);
							}
							if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT")
									.equals("ARES")) {
								Kits.setKitAres(p);
							}
						}
					} else {
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Kit" + ChatColor.GRAY
								+ "] Please chose a Kit first before joining the FFA Arena!");
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					}
				} else {
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "FFA" + ChatColor.GRAY
							+ "] You cannot use /ffa while in the FFA Arena!");
					p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					p.closeInventory();
				}

			} else if (clicked.equals(ItemStacks.getItem("ffa2"))) {
				e.setCancelled(true);

				if (!(FFAManager.inFfa.contains(p))) {
					if (!(Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT"))
							.equals(null)) {
						if (FightManager.fightList.contains(p)) {
							p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY
									+ "] Please leave the Fight Queue before joining the FFA Arena!");
							FFAManager.ffaCancel = 1;
							p.closeInventory();
						}
						if (FightManager.inFight.contains(p)) {
							p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY
									+ "] Please finish the Fight before joining the FFA Arena!");
							FFAManager.ffaCancel = 1;
						}

						if (FFAManager.ffaCancel == 0) {
							p.closeInventory();
							p.setGameMode(GameMode.SURVIVAL);
							Inventories.clear(p);
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
							Bukkit.getServer()
									.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "FFA" + ChatColor.GRAY
											+ "] " + ChatColor.AQUA + p.getName() + ChatColor.GRAY
											+ " has joined the FFA Arena!");
							FFAManager.inFfa.add(p);

							if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT")
									.equals("DEFAULT")) {
								Kits.setKitDefault(p);
							}
							if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT")
									.equals("TANK")) {
								Kits.setKitTank(p);
							}
							if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT")
									.equals("BERSERKER")) {
								Kits.setKitBerserker(p);
							}
							if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT")
									.equals("APOLLO")) {
								Kits.setKitApollo(p);
							}
							if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT")
									.equals("HADES")) {
								Kits.setKitHades(p);
							}
							if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT")
									.equals("NINJA")) {
								Kits.setKitNinja(p);
							}
							if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT")
									.equals("THOR")) {
								Kits.setKitThor(p);
							}
							if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("KIT")
									.equals("ARES")) {
								Kits.setKitAres(p);
							}

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
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Kit" + ChatColor.GRAY
								+ "] Please chose a Kit first before joining the FFA Arena!");
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					}
				} else {
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "FFA" + ChatColor.GRAY
							+ "] You cannot use /ffa while in the FFA Arena!");
					p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					p.closeInventory();
				}

			} else if (clicked.equals(ItemStacks.getItem("closeMenu"))) {
				if (e.getAction().equals(InventoryAction.PICKUP_ALL)
						|| e.getAction().equals(InventoryAction.PICKUP_HALF)) {
					e.setCancelled(true);
					p.closeInventory();
				} else {
					e.setCancelled(true);
					p.closeInventory();
				}
			} else if (clicked.equals(ItemStacks.getItem("infoButton"))) {
				if (e.getAction().equals(InventoryAction.PICKUP_ALL)
						|| e.getAction().equals(InventoryAction.PICKUP_HALF)) {
					e.setCancelled(true);
					// p.closeInventory(); Currently Disabled
				} else {
					e.setCancelled(true);
					p.closeInventory();
				}
			} else if (clicked.equals(ItemStacks.getItem("statsButton"))) {
				if (e.getAction().equals(InventoryAction.PICKUP_ALL)
						|| e.getAction().equals(InventoryAction.PICKUP_HALF)) {
					e.setCancelled(true);

					String name = p.getName();
					String rank = (String) Main.getInstance().getConfig().getConfigurationSection(p.getName())
							.get("RANK");
					int gold = (int) Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("GOLD");
					int wins = (int) Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("WINS");
					int losses = (int) Main.getInstance().getConfig().getConfigurationSection(p.getName())
							.get("LOSSES");
					int defaultKills = (int) Main.getInstance().getConfig().getConfigurationSection(p.getName())
							.get("DEFAULT_KILLS");
					int tankKills = (int) Main.getInstance().getConfig().getConfigurationSection(p.getName())
							.get("TANK_KILLS");
					int aresKills = (int) Main.getInstance().getConfig().getConfigurationSection(p.getName())
							.get("ARES_KILLS");
					int berserkerKills = (int) Main.getInstance().getConfig().getConfigurationSection(p.getName())
							.get("BERSERKER_KILLS");
					int thorKills = (int) Main.getInstance().getConfig().getConfigurationSection(p.getName())
							.get("THOR_KILLS");
					int hadesKills = (int) Main.getInstance().getConfig().getConfigurationSection(p.getName())
							.get("HADES_KILLS");
					int apolloKills = (int) Main.getInstance().getConfig().getConfigurationSection(p.getName())
							.get("APOLLO_KILLS");
					int ninjaKills = (int) Main.getInstance().getConfig().getConfigurationSection(p.getName())
							.get("NINJA_KILLS");

					ItemStack playerInfo = new ItemStack(Material.COMMAND_BLOCK);
					ItemMeta playerInfoMeta = playerInfo.getItemMeta();
					ArrayList<String> playerInfoLore = new ArrayList<String>();
					playerInfoMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + name);
					playerInfoLore.add(ChatColor.WHITE + "Rank: " + ChatColor.BOLD + rank);
					playerInfoLore.add(ChatColor.WHITE + "Gold: " + ChatColor.BOLD + gold);
					playerInfoLore.add(ChatColor.WHITE + "Wins: " + ChatColor.BOLD + wins);
					playerInfoLore.add(ChatColor.WHITE + "Losses: " + ChatColor.BOLD + losses);
					playerInfoLore.add("");
					playerInfoLore.add(ChatColor.WHITE + "-- Kit-specific Kills --");
					playerInfoLore.add(ChatColor.WHITE + "Default: " + ChatColor.BOLD + defaultKills);
					playerInfoLore.add(ChatColor.WHITE + "Tank: " + ChatColor.BOLD + tankKills);
					playerInfoLore.add(ChatColor.WHITE + "Ares: " + ChatColor.BOLD + aresKills);
					playerInfoLore.add(ChatColor.WHITE + "Berserker: " + ChatColor.BOLD + berserkerKills);
					playerInfoLore.add(ChatColor.WHITE + "Thor: " + ChatColor.BOLD + thorKills);
					playerInfoLore.add(ChatColor.WHITE + "Hades: " + ChatColor.BOLD + hadesKills);
					playerInfoLore.add(ChatColor.WHITE + "Apollo: " + ChatColor.BOLD + apolloKills);
					playerInfoLore.add(ChatColor.WHITE + "Ninja: " + ChatColor.BOLD + ninjaKills);
					playerInfoMeta.setLore(playerInfoLore);
					playerInfo.setItemMeta(playerInfoMeta);

					Inventories.statsGui.setItem(0, playerInfo);
					Inventories.statsGui.setItem(8, ItemStacks.getItem("backButton"));
					p.closeInventory();
					p.openInventory(Inventories.statsGui);

				} else {
					e.setCancelled(true);
					p.closeInventory();
				}
			} else if (clicked.equals(ItemStacks.getItem("settingsMenu"))) {
				if (e.getAction().equals(InventoryAction.PICKUP_ALL)
						|| e.getAction().equals(InventoryAction.PICKUP_HALF)) {
					e.setCancelled(true);

					if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("AUTOCAST")
							.equals(false)) {
						Inventories.settingsGui.setItem(0, ItemStacks.getItem("autocastFalse"));
					}
					if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("AUTOCAST")
							.equals(true)) {
						Inventories.settingsGui.setItem(0, ItemStacks.getItem("autocastTrue"));
					}
					if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("HOTBARMODE")
							.equals(1)) {
						Inventories.settingsGui.setItem(1, ItemStacks.getItem("hotbarmode1"));
					}
					if (Main.getInstance().getConfig().getConfigurationSection(p.getName()).get("HOTBARMODE")
							.equals(2)) {
						Inventories.settingsGui.setItem(1, ItemStacks.getItem("hotbarmode2"));
					}

					Inventories.settingsGui.setItem(8, ItemStacks.getItem("backButton"));
					p.closeInventory();
					p.openInventory(Inventories.settingsGui);

				} else {
					e.setCancelled(true);
					p.closeInventory();
				}
			} else if (clicked.equals(ItemStacks.getItem("backButton"))) {
				e.setCancelled(true);
				p.closeInventory();
				p.openInventory(Inventories.kitGui);
			} else if (clicked.equals(ItemStacks.getItem("leave"))) {
				if (e.getAction().equals(InventoryAction.PICKUP_ALL)
						|| e.getAction().equals(InventoryAction.PICKUP_HALF)) {
					e.setCancelled(true);
					if (p.getWorld().equals(Worlds.kitpvpWorld) && FFAManager.inFfa.contains(p)) {
						p.closeInventory();
						Inventories.clear(p);
						p.teleport(Locations.spawn);
						p.getInventory().setItem(0, ItemStacks.getItem("kitpvpButton"));
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
						if (FFAManager.inFfa.contains(p)) {
							FFAManager.inFfa.remove(p);
							Bukkit.getServer()
									.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "FFA" + ChatColor.GRAY
											+ "] " + ChatColor.AQUA + p.getName() + ChatColor.GRAY
											+ " has left the FFA Arena!");
						} else if (FightManager.inFight.contains(p)) {
							FightManager.inFight.remove(p);
							Bukkit.getServer()
									.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "Fight" + ChatColor.GRAY
											+ "] " + ChatColor.AQUA + p.getName() + ChatColor.GRAY
											+ " has left the Fight Arena!");
						}
					} else {
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "Error" + ChatColor.GRAY
								+ "] You are not in the KitPVP World!");
					}
				}
			}
			if (e.getInventory().equals(Inventories.settingsGui)) {
				if (clicked.equals(ItemStacks.getItem("autocastFalse"))) {
					e.setCancelled(true);
					Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("AUTOCAST", true);
					e.getInventory().setItem(0, ItemStacks.getItem("autocastTrue"));
					p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
				} else if (clicked.equals(ItemStacks.getItem("autocastTrue"))) {
					e.setCancelled(true);
					Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("AUTOCAST", false);
					e.getInventory().setItem(0, ItemStacks.getItem("autocastFalse"));
					p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
				} else if (clicked.equals(ItemStacks.getItem("hotbarmode1"))) {
					e.setCancelled(true);
					Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("HOTBARMODE", 2);
					e.getInventory().setItem(1, ItemStacks.getItem("hotbarmode2"));
					p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
				} else if (clicked.equals(ItemStacks.getItem("hotbarmode2"))) {
					e.setCancelled(true);
					Main.getInstance().getConfig().getConfigurationSection(p.getName()).set("HOTBARMODE", 1);
					e.getInventory().setItem(1, ItemStacks.getItem("hotbarmode1"));
					p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
				}
			}

			if (clicked != null && clicked.containsEnchantment(Enchantment.DURABILITY)
					&& e.getInventory().equals(Inventories.kitGui)) {
				e.setCancelled(true);
				p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
			}
		}
	}

}
