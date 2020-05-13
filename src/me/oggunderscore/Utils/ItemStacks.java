package me.oggunderscore.Utils;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStacks {
	
	public static ItemStack getItem(String search) {
		ItemStack Default = new ItemStack(Material.IRON_SWORD);
		ItemMeta defaultMeta = Default.getItemMeta();
		ArrayList<String> defaultLore = new ArrayList<String>();
		defaultMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Default");
		defaultLore.add(ChatColor.YELLOW + "The Classic Kit.");
		defaultMeta.setLore(defaultLore);
		Default.setItemMeta(defaultMeta);

		ItemStack DefaultSelected = new ItemStack(Material.IRON_SWORD);
		ItemMeta defaultSelectedMeta = DefaultSelected.getItemMeta();
		ArrayList<String> defaultSelectedLore = new ArrayList<String>();
		defaultSelectedMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Default (SELECTED)");
		defaultSelectedLore.add(ChatColor.YELLOW + "The Classic Kit.");
		defaultSelectedMeta.setLore(defaultSelectedLore);
		DefaultSelected.setItemMeta(defaultSelectedMeta);

		ItemStack tank = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta tankMeta = tank.getItemMeta();
		ArrayList<String> tankLore = new ArrayList<String>();
		tankMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Tank");
		tankLore.add(ChatColor.YELLOW + "Noone shall pass!");
		tankMeta.setLore(tankLore);
		tank.setItemMeta(tankMeta);

		ItemStack tankSelected = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta tankSelectedMeta = tankSelected.getItemMeta();
		ArrayList<String> tankSelectedLore = new ArrayList<String>();
		tankSelectedMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Tank (SELECTED)");
		tankSelectedLore.add(ChatColor.YELLOW + "Noone shall pass!");
		tankSelectedMeta.setLore(tankSelectedLore);
		tankSelected.setItemMeta(tankSelectedMeta);

		ItemStack berserker = new ItemStack(Material.REDSTONE);
		ItemMeta berserkerMeta = berserker.getItemMeta();
		ArrayList<String> berserkerLore = new ArrayList<String>();
		berserkerMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Berserker");
		berserkerLore.add(ChatColor.YELLOW + "Time to go wild!");
		berserkerMeta.setLore(berserkerLore);
		berserker.setItemMeta(berserkerMeta);

		ItemStack berserkerSelected = new ItemStack(Material.REDSTONE);
		ItemMeta berserkerSelectedMeta = berserkerSelected.getItemMeta();
		ArrayList<String> berserkerSelectedLore = new ArrayList<String>();
		berserkerSelectedMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Berserker (SELECTED)");
		berserkerSelectedLore.add(ChatColor.YELLOW + "Time to go wild!");
		berserkerSelectedMeta.setLore(berserkerSelectedLore);
		berserkerSelected.setItemMeta(berserkerSelectedMeta);

		ItemStack ares = new ItemStack(Material.FIRE_CHARGE);
		ItemMeta aresMeta = ares.getItemMeta();
		ArrayList<String> aresLore = new ArrayList<String>();
		aresMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Ares");
		aresLore.add(ChatColor.YELLOW + "Behold the fire.");
		aresMeta.setLore(aresLore);
		ares.setItemMeta(aresMeta);

		ItemStack aresSelected = new ItemStack(Material.FIRE_CHARGE);
		ItemMeta aresSelectedMeta = aresSelected.getItemMeta();
		ArrayList<String> aresSelectedLore = new ArrayList<String>();
		aresSelectedMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Ares (SELECTED)");
		aresSelectedLore.add(ChatColor.YELLOW + "Behold the fire.");
		aresSelectedMeta.setLore(aresSelectedLore);
		aresSelected.setItemMeta(aresSelectedMeta);

		ItemStack thor = new ItemStack(Material.IRON_AXE);
		ItemMeta thorMeta = thor.getItemMeta();
		ArrayList<String> thorLore = new ArrayList<String>();
		thorMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Thor");
		thorLore.add(ChatColor.YELLOW + "Destroy all among you and cause devastation!");
		thorMeta.setLore(thorLore);
		thor.setItemMeta(thorMeta);

		ItemStack thorSelected = new ItemStack(Material.IRON_AXE);
		ItemMeta thorSelectedMeta = thorSelected.getItemMeta();
		ArrayList<String> thorSelectedLore = new ArrayList<String>();
		thorSelectedMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Thor (SELECTED)");
		thorSelectedLore.add(ChatColor.YELLOW + "Destroy all among you and cause devastation!");
		thorSelectedMeta.setLore(thorSelectedLore);
		thorSelected.setItemMeta(thorSelectedMeta);

		ItemStack hades = new ItemStack(Material.STONE_AXE);
		ItemMeta hadesMeta = hades.getItemMeta();
		ArrayList<String> hadesLore = new ArrayList<String>();
		hadesMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Hades");
		hadesLore.add(ChatColor.YELLOW + "May those who fall under your hands die.");
		hadesMeta.setLore(hadesLore);
		hades.setItemMeta(hadesMeta);

		ItemStack hadesSelected = new ItemStack(Material.STONE_AXE);
		ItemMeta hadesSelectedMeta = hadesSelected.getItemMeta();
		ArrayList<String> hadesSelectedLore = new ArrayList<String>();
		hadesSelectedMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Hades (SELECTED)");
		hadesSelectedLore.add(ChatColor.YELLOW + "May those who fall under your hands die.");
		hadesSelectedMeta.setLore(hadesSelectedLore);
		hadesSelected.setItemMeta(hadesSelectedMeta);

		ItemStack apollo = new ItemStack(Material.BOW);
		ItemMeta apolloMeta = apollo.getItemMeta();
		ArrayList<String> apolloLore = new ArrayList<String>();
		apolloMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Apollo");
		apolloLore.add(ChatColor.YELLOW + "Every arrow counts.");
		apolloMeta.setLore(apolloLore);
		apollo.setItemMeta(apolloMeta);

		ItemStack apolloSelected = new ItemStack(Material.BOW);
		ItemMeta apolloSelectedMeta = apolloSelected.getItemMeta();
		ArrayList<String> apolloSelectedLore = new ArrayList<String>();
		apolloSelectedMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Apollo (SELECTED)");
		apolloSelectedLore.add(ChatColor.YELLOW + "Every arrow counts.");
		apolloSelectedMeta.setLore(apolloSelectedLore);
		apolloSelected.setItemMeta(apolloSelectedMeta);

		ItemStack ninja = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta ninjaMeta = ninja.getItemMeta();
		ArrayList<String> ninjaLore = new ArrayList<String>();
		ninjaMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Ninja");
		ninjaLore.add(ChatColor.YELLOW + "Be One to Master the Techniques of the Ninja.");
		ninjaMeta.setLore(ninjaLore);
		ninja.setItemMeta(ninjaMeta);

		ItemStack ninjaSelected = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta ninjaSelectedMeta = ninjaSelected.getItemMeta();
		ArrayList<String> ninjaSelectedLore = new ArrayList<String>();
		ninjaSelectedMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Ninja (SELECTED)");
		ninjaSelectedLore.add(ChatColor.YELLOW + "Be One to Master the Techniques of the ninjaSelected.");
		ninjaSelectedMeta.setLore(ninjaSelectedLore);
		ninjaSelected.setItemMeta(ninjaSelectedMeta);

		ItemStack closeMenu = new ItemStack(Material.BARRIER);
		ItemMeta closeMenuMeta = closeMenu.getItemMeta();
		closeMenuMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Close Menu");
		closeMenu.setItemMeta(closeMenuMeta);
		
		ItemStack settingsButton = new ItemStack(Material.CRAFTING_TABLE);
		ItemMeta settingsButtonMeta = settingsButton.getItemMeta();
		settingsButtonMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Settings Menu");
		settingsButton.setItemMeta(settingsButtonMeta);
		
		ItemStack statsButton = new ItemStack(Material.POTION);
		ItemMeta statsButtonMeta = statsButton.getItemMeta();
		statsButtonMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Stats Menu");
		statsButton.setItemMeta(statsButtonMeta);
		
		ItemStack infoButton = new ItemStack(Material.BOOK);
		ItemMeta infoButtonMeta = infoButton.getItemMeta();
		infoButtonMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Server Info");
		infoButton.setItemMeta(infoButtonMeta);

		ItemStack ffa = new ItemStack(Material.DIAMOND);
		ItemMeta ffaMeta = ffa.getItemMeta();
		ArrayList<String> ffaLore = new ArrayList<String>();
		ffaMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "FFA");
		ffaLore.add(ChatColor.YELLOW + "Teleport to FFA!");
		ffaMeta.setLore(ffaLore);
		ffa.setItemMeta(ffaMeta);

		ItemStack ffa2 = new ItemStack(Material.EMERALD);
		ItemMeta ffa2Meta = ffa2.getItemMeta();
		ArrayList<String> ffa2Lore = new ArrayList<String>();
		ffa2Meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "FFA 2");
		ffa2Lore.add(ChatColor.YELLOW + "Teleport to FFA 2!");
		ffa2Meta.setLore(ffa2Lore);
		ffa2.setItemMeta(ffa2Meta);
		
		ItemStack leave = new ItemStack(Material.COMPASS);
		ItemMeta leaveMeta = leave.getItemMeta();
		ArrayList<String> leaveLore = new ArrayList<String>();
		leaveMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Leave Arena");
		leaveLore.add(ChatColor.YELLOW + "Leave your current arena.");
		leaveMeta.setLore(leaveLore);
		leave.setItemMeta(leaveMeta);
		
		ItemStack autocastFalse = new ItemStack(Material.BLAZE_ROD);
		ItemMeta autocastFalseMeta = autocastFalse.getItemMeta();
		ArrayList<String> autocastFalseLore = new ArrayList<String>();
		autocastFalseMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "AutoCast (FALSE)");
		autocastFalseLore
				.add(ChatColor.YELLOW + "Allows you to cast the ability by just hitting the ability number.");
		autocastFalseMeta.setLore(autocastFalseLore);
		autocastFalse.setItemMeta(autocastFalseMeta);

		ItemStack autocastTrue = new ItemStack(Material.BLAZE_ROD);
		ItemMeta autocastTrueMeta = autocastTrue.getItemMeta();
		ArrayList<String> autocastTrueLore = new ArrayList<String>();
		autocastTrueMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "AutoCast (TRUE)");
		autocastTrueLore
				.add(ChatColor.YELLOW + "Allows you to cast the ability by just hitting the ability number.");
		autocastTrueMeta.setLore(autocastTrueLore);
		autocastTrue.setItemMeta(autocastTrueMeta);

		ItemStack hotbarmode1 = new ItemStack(Material.ANVIL);
		ItemMeta hotbarmode1Meta = hotbarmode1.getItemMeta();
		ArrayList<String> hotbarmode1Lore = new ArrayList<String>();
		hotbarmode1Meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "HotBarMode: 1");
		hotbarmode1Lore.add(ChatColor.YELLOW + "Different Kit Setups! Depending on the kit.");
		hotbarmode1Meta.setLore(hotbarmode1Lore);
		hotbarmode1.setItemMeta(hotbarmode1Meta);

		ItemStack hotbarmode2 = new ItemStack(Material.ANVIL);
		ItemMeta hotbarmode2Meta = hotbarmode2.getItemMeta();
		ArrayList<String> hotbarmode2Lore = new ArrayList<String>();
		hotbarmode2Meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "HotBarMode: 2");
		hotbarmode2Lore.add(ChatColor.YELLOW + "Different Kit Setups! Depending on the kit.");
		hotbarmode2Meta.setLore(hotbarmode2Lore);
		hotbarmode2.setItemMeta(hotbarmode2Meta);

		ItemStack backButton = new ItemStack(Material.BARRIER);
		ItemMeta backButtonMeta = backButton.getItemMeta();
		ArrayList<String> backButtonLore = new ArrayList<String>();
		backButtonMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Back");
		backButtonLore.add(ChatColor.YELLOW + "Back to Kit Selection");
		backButtonMeta.setLore(backButtonLore);
		backButton.setItemMeta(backButtonMeta);
		
		ItemStack kitpvpButton = new ItemStack(Material.POPPY);
		ItemMeta kitpvpButtonMeta = kitpvpButton.getItemMeta();
		ArrayList<String> kitpvpButtonLore = new ArrayList<String>();
		kitpvpButtonMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "KitPVP Menu");
		kitpvpButtonLore.add(ChatColor.YELLOW + "Opens the KitPVP Menu!");
		kitpvpButtonMeta.setLore(kitpvpButtonLore);
		kitpvpButton.setItemMeta(kitpvpButtonMeta);
		
		ItemStack searchedItem = null;

		if (search.equalsIgnoreCase("default")) {
			searchedItem = Default;
		}
		if (search.equalsIgnoreCase("defaultSelected")) {
			searchedItem = DefaultSelected;
		}
		if (search.equalsIgnoreCase("tank")) {
			searchedItem = tank;
		}
		if (search.equalsIgnoreCase("tankSelected")) {
			searchedItem = tankSelected;
		}
		if (search.equalsIgnoreCase("berserker")) {
			searchedItem = berserker;
		}
		if (search.equalsIgnoreCase("berserkerSelected")) {
			searchedItem = berserkerSelected;
		}
		if (search.equalsIgnoreCase("ares")) {
			searchedItem = ares;
		}
		if (search.equalsIgnoreCase("aresSelected")) {
			searchedItem = aresSelected;
		}
		if (search.equalsIgnoreCase("thor")) {
			searchedItem = thor;
		}
		if (search.equalsIgnoreCase("thorSelected")) {
			searchedItem = thorSelected;
		}
		if (search.equalsIgnoreCase("hades")) {
			searchedItem = hades;
		}
		if (search.equalsIgnoreCase("hadesSelected")) {
			searchedItem = hadesSelected;
		}
		if (search.equalsIgnoreCase("apollo")) {
			searchedItem = apollo;
		}
		if (search.equalsIgnoreCase("apolloSelected")) {
			searchedItem = apolloSelected;
		}
		if (search.equalsIgnoreCase("ninja")) {
			searchedItem = ninja;
		}
		if (search.equalsIgnoreCase("ninjaSelected")) {
			searchedItem = ninjaSelected;
		}
		if (search.equalsIgnoreCase("closeMenu")) {
			searchedItem = closeMenu;
		}
		if (search.equalsIgnoreCase("ffa")) {
			searchedItem = ffa;
		}
		if (search.equalsIgnoreCase("ffa2")) {
			searchedItem = ffa2;
		}
		if (search.equalsIgnoreCase("leave")) {
			searchedItem = leave;
		}
		if (search.equalsIgnoreCase("autocastFalse")) {
			searchedItem = autocastFalse;
		}
		if (search.equalsIgnoreCase("autocastTrue")) {
			searchedItem = autocastTrue;
		}
		if (search.equalsIgnoreCase("hotbarmode1")) {
			searchedItem = hotbarmode1;
		}
		if (search.equalsIgnoreCase("hotbarmode2")) {
			searchedItem = hotbarmode2;
		}
		if (search.equalsIgnoreCase("backButton")) {
			searchedItem = backButton;
		}
		if (search.equalsIgnoreCase("settingsButton")) {
			searchedItem = settingsButton;
		}
		if (search.equalsIgnoreCase("statsButton")) {
			searchedItem = statsButton;
		}
		if (search.equalsIgnoreCase("infoButton")) {
			searchedItem = infoButton;
		}
		if (search.equalsIgnoreCase("kitpvpButton")) {
			searchedItem = kitpvpButton;
		}
		
		
		return searchedItem;
		
		
	}

}
