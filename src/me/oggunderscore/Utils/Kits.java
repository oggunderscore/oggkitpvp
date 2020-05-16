package me.oggunderscore.Utils;

import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.oggunderscore.Core.Main;

public class Kits {
	public static final ArrayList<Player> DEFAULT = new ArrayList<Player>();
	public static final ArrayList<Player> TANK = new ArrayList<Player>();
	public static final ArrayList<Player> ARES = new ArrayList<Player>();
	public static final ArrayList<Player> BERSERKER = new ArrayList<Player>();
	public static final ArrayList<Player> THOR = new ArrayList<Player>();
	public static final ArrayList<Player> HADES = new ArrayList<Player>();
	public static final ArrayList<Player> APOLLO = new ArrayList<Player>();
	public static final ArrayList<Player> NINJA = new ArrayList<Player>();
	
	static Configuration config = Main.getPlugin(Main.class).getConfig();
	

	
	public static void setKitDefault(Player p) {
		
		ItemStack Helmet = new ItemStack(Material.IRON_HELMET);
		ItemMeta hm = Helmet.getItemMeta();
		hm.setUnbreakable(true);
		Helmet.setItemMeta(hm);
		
		ItemStack Chestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta cm = Chestplate.getItemMeta();
		cm.setUnbreakable(true);
		Chestplate.setItemMeta(cm);
		
		ItemStack Leggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemMeta lm = Leggings.getItemMeta();
		lm.setUnbreakable(true);
		Leggings.setItemMeta(lm);
		
		ItemStack Boots = new ItemStack(Material.IRON_BOOTS);
		ItemMeta bm = Boots.getItemMeta();
		bm.setUnbreakable(true);
		Boots.setItemMeta(bm);
		
		
		ItemStack Sword = new ItemStack(Material.IRON_SWORD);
		ItemMeta sm = Sword.getItemMeta();
		ArrayList<String> lore1 = new ArrayList<String>();
		lore1.add(ChatColor.YELLOW + "Your loyal Iron Blade.");
		sm.setLore(lore1);
		sm.setUnbreakable(true);
		sm.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "DASH");
		Sword.setItemMeta(sm);
		
		ItemStack Bow = new ItemStack(Material.BOW);
		ItemMeta bowm = Bow.getItemMeta();
		ArrayList<String> lore2 = new ArrayList<String>();
		lore2.add(ChatColor.YELLOW + "Your loyal Long Bow.");
		bowm.setLore(lore1);
		bowm.setUnbreakable(true);
		Bow.setItemMeta(bowm);
		
		if (config.getConfigurationSection(p.getName()).get("HOTBARMODE").equals(1)) {
			p.getInventory().setItem(0, Sword);
			p.getInventory().setItem(1, new ItemStack (Material.ARROW, 64));
			p.getInventory().setItem(2, Bow);
		} else if (config.getConfigurationSection(p.getName()).get("HOTBARMODE").equals(2)) {
			p.getInventory().setItem(0, Sword);
			p.getInventory().setItem(1, Bow);
			p.getInventory().setItem(2, new ItemStack (Material.ARROW, 64));
		}
		
		
		p.getInventory().setHelmet(Helmet);
		p.getInventory().setChestplate(Chestplate);
		p.getInventory().setLeggings(Leggings);
		p.getInventory().setBoots(Boots);
		
		p.getInventory().setItem(4, ItemStacks.getItem("kitpvpButton"));
		
		p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
		
		
		DEFAULT.add(p);
		
	}
	
	
	
	public static void setKitTank(Player p) {
		
		ItemStack Helmet = new ItemStack(Material.DIAMOND_HELMET);
		ItemMeta hm = Helmet.getItemMeta();
		hm.setUnbreakable(true);
		Helmet.setItemMeta(hm);
		
		ItemStack Chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta cm = Chestplate.getItemMeta();
		cm.setUnbreakable(true);
		Chestplate.setItemMeta(cm);
		
		ItemStack Leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemMeta lm = Leggings.getItemMeta();
		lm.setUnbreakable(true);
		Leggings.setItemMeta(lm);
		
		ItemStack Boots = new ItemStack(Material.DIAMOND_BOOTS);
		ItemMeta bm = Boots.getItemMeta();
		bm.setUnbreakable(true);
		Boots.setItemMeta(bm);
		
		
		ItemStack Sword = new ItemStack(Material.WOODEN_SWORD);
		ItemMeta sm = Sword.getItemMeta();
		ArrayList<String> lore1 = new ArrayList<String>();
		lore1.add(ChatColor.YELLOW + "Your loyal Woodem Blade.");
		sm.setLore(lore1);
		sm.setUnbreakable(true);
		Sword.setItemMeta(sm);
		
		p.getInventory().addItem(Sword);
		
		p.getInventory().setHelmet(Helmet);
		p.getInventory().setChestplate(Chestplate);
		p.getInventory().setLeggings(Leggings);
		p.getInventory().setBoots(Boots);
		
		p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
		
		p.getInventory().setItem(4, ItemStacks.getItem("kitpvpButton"));
		
		TANK.add(p);
		
	}
	
	
	public static void setKitAres(Player p) {

		ItemStack AresHelmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta ahm = (LeatherArmorMeta) AresHelmet.getItemMeta();
		ahm.setColor(Color.ORANGE);
		ahm.setUnbreakable(true);
		AresHelmet.setItemMeta(ahm);

		ItemStack AresChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta acm = (LeatherArmorMeta) AresChestplate.getItemMeta();
		acm.setColor(Color.ORANGE);
		acm.setUnbreakable(true);
		AresChestplate.setItemMeta(acm);

		ItemStack AresLeggings = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta alm = (LeatherArmorMeta) AresLeggings.getItemMeta();
		alm.setColor(Color.ORANGE);
		alm.setUnbreakable(true);
		AresLeggings.setItemMeta(alm);

		ItemStack AresBoots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta abm = (LeatherArmorMeta) AresBoots.getItemMeta();
		abm.setColor(Color.ORANGE);
		abm.setUnbreakable(true);
		AresBoots.setItemMeta(abm);

		ItemStack Sword = new ItemStack(Material.STONE_SWORD);
		ItemMeta sm = Sword.getItemMeta();
		ArrayList<String> lore1 = new ArrayList<String>();
		lore1.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Enlightment");
		lore1.add(ChatColor.YELLOW + "Leap in a direction and gain Absorbation!");
		sm.setLore(lore1);
		sm.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Ares Sword");
		sm.setUnbreakable(true);
		Sword.setItemMeta(sm);

		ItemStack Ignite = new ItemStack(Material.FIRE_CHARGE);
		ItemMeta im = Ignite.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Ignite");
		lore.add(ChatColor.YELLOW + "Right Click to set nearby");
		lore.add(ChatColor.YELLOW + "enemies on fire for 9 seconds!");
		im.setLore(lore);
		Ignite.setItemMeta(im);
		Ignite.setAmount(1);

		ItemStack Regen = new ItemStack(Material.ROSE_BUSH);
		ItemMeta rm = Regen.getItemMeta();
		ArrayList<String> lore2 = new ArrayList<String>();
		rm.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Regeneration Flower");
		lore2.add(ChatColor.YELLOW + "Right Click to gain");
		lore2.add(ChatColor.YELLOW + "Regeneration 2 for 5 seconds!");
		rm.setLore(lore2);
		Regen.setItemMeta(rm);

		p.getInventory().setHelmet(AresHelmet);
		p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
		p.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
		p.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));

		p.getInventory().setHeldItemSlot(0);
		p.getInventory().setItem(0, Sword);
		p.getInventory().setItem(1, Ignite);
		p.getInventory().addItem(Regen);
		
		p.getInventory().setItem(4, ItemStacks.getItem("kitpvpButton"));
		
		p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
		
		ARES.add(p);
				
	}
	
	public static void setKitBerserker(Player p) {
		
		ItemStack Helmet = new ItemStack(Material.IRON_HELMET);
		ItemMeta hm = Helmet.getItemMeta();
		hm.setUnbreakable(true);
		Helmet.setItemMeta(hm);
		
		ItemStack Chestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta cm = Chestplate.getItemMeta();
		cm.setUnbreakable(true);
		Chestplate.setItemMeta(cm);
		
		ItemStack Leggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemMeta lm = Leggings.getItemMeta();
		lm.setUnbreakable(true);
		Leggings.setItemMeta(lm);
		
		ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bm = (LeatherArmorMeta) Boots.getItemMeta();
		bm.setColor(Color.RED);
		bm.setUnbreakable(true);
		Boots.setItemMeta(bm);
		
		ItemStack Sword = new ItemStack(Material.STONE_AXE);
		ItemMeta sm = Sword.getItemMeta();
		ArrayList<String> lore1 = new ArrayList<String>();
		lore1.add(ChatColor.YELLOW + "Cleave through your enemies! Cost: 80 Energy");
		sm.setLore(lore1);
		sm.setUnbreakable(true);
		sm.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "CLEAVE");
		Sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, -1);
		Sword.setItemMeta(sm);
		
		ItemStack Ultimate = new ItemStack(Material.REDSTONE);
		ItemMeta um = Ultimate.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.YELLOW + "Release a powerful ROAR! Cost: 100 Energy");
		um.setLore(lore);
		um.setUnbreakable(true);
		um.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "ROAR");
		Ultimate.setItemMeta(um);
		
		ItemStack QuickFix = new ItemStack(Material.LEATHER);
		ItemMeta qm = QuickFix.getItemMeta();
		ArrayList<String> lore2 = new ArrayList<String>();
		lore2.add(ChatColor.YELLOW + "Instantly Heal 1.5 hearts! Cost: 40 Energy");
		qm.setLore(lore2);
		qm.setUnbreakable(true);
		qm.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Quick Fix");
		QuickFix.setItemMeta(qm);
		
		p.getInventory().setItem(0, Sword);
		p.getInventory().setItem(1, QuickFix);
		p.getInventory().setItem(2, Ultimate);
		
		p.getInventory().setHelmet(Helmet);
		p.getInventory().setChestplate(Chestplate);
		p.getInventory().setLeggings(Leggings);
		p.getInventory().setBoots(Boots);
		
		p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
		
		p.getInventory().setItem(4, ItemStacks.getItem("kitpvpButton"));
		
		BERSERKER.add(p);
		
	}
	public static void setKitThor(Player p) {
		
		ItemStack Helmet = new ItemStack(Material.GOLDEN_HELMET);
		ItemMeta hm = Helmet.getItemMeta();
		hm.setUnbreakable(true);
		Helmet.setItemMeta(hm);
		
		ItemStack Chestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta cm = Chestplate.getItemMeta();
		cm.setUnbreakable(true);
		Chestplate.setItemMeta(cm);
		
		ItemStack Leggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemMeta lm = Leggings.getItemMeta();
		lm.setUnbreakable(true);
		Leggings.setItemMeta(lm);
		
		ItemStack Boots = new ItemStack(Material.GOLDEN_BOOTS);
		ItemMeta bm = Boots.getItemMeta();
		bm.setUnbreakable(true);
		Boots.setItemMeta(bm);
	
		
		ItemStack Sword = new ItemStack(Material.IRON_AXE);
		ItemMeta sm = Sword.getItemMeta();
		ArrayList<String> lore1 = new ArrayList<String>();
		lore1.add(ChatColor.YELLOW + "Summon an Earthquake!");
		sm.setLore(lore1);
		sm.setUnbreakable(true);
		sm.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "EARTHQUAKE");
		Sword.setItemMeta(sm);
		
		p.getInventory().setItem(0, Sword);
		
		p.getInventory().setHelmet(Helmet);
		p.getInventory().setChestplate(Chestplate);
		p.getInventory().setLeggings(Leggings);
		p.getInventory().setBoots(Boots);
		
		p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
		
		p.getInventory().setItem(4, ItemStacks.getItem("kitpvpButton"));
		
		THOR.add(p);
		
	}

	public static void setKitHades(Player p) {
		
		ItemStack Helmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta hm = (LeatherArmorMeta) Helmet.getItemMeta();
		hm.setColor(Color.PURPLE);
		hm.setUnbreakable(true);
		Helmet.setItemMeta(hm);
		
		ItemStack Chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta cm = Chestplate.getItemMeta();
		cm.setUnbreakable(true);
		Chestplate.setItemMeta(cm);
		
		ItemStack Leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemMeta lm = Leggings.getItemMeta();
		lm.setUnbreakable(true);
		Leggings.setItemMeta(lm);
		
		ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bm = (LeatherArmorMeta) Boots.getItemMeta();
		bm.setColor(Color.PURPLE);
		bm.setUnbreakable(true);
		Boots.setItemMeta(bm);
		
		ItemStack Sword = new ItemStack(Material.IRON_AXE);
		ItemMeta sm = Sword.getItemMeta();
		ArrayList<String> lore1 = new ArrayList<String>();
		lore1.add(ChatColor.YELLOW + "Summon a Corrupted Orb!");
		sm.setLore(lore1);
		sm.setUnbreakable(true);
		sm.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Corrupted Orb!");
		Sword.setItemMeta(sm);
		
		p.getInventory().setItem(0, Sword);
		
		p.getInventory().setHelmet(Helmet);
		p.getInventory().setChestplate(Chestplate);
		p.getInventory().setLeggings(Leggings);
		p.getInventory().setBoots(Boots);
		
		p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
		
		p.getInventory().setItem(4, ItemStacks.getItem("kitpvpButton"));
		
		HADES.add(p);
		
	}
	public static void setKitApollo(Player p) {
		
		ItemStack Helmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta hm = (LeatherArmorMeta) Helmet.getItemMeta();
		hm.setColor(Color.YELLOW);
		hm.setUnbreakable(true);
		Helmet.setItemMeta(hm);
		
		ItemStack AresChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta acm = (LeatherArmorMeta) AresChestplate.getItemMeta();
		acm.setColor(Color.YELLOW);
		acm.setUnbreakable(true);
		AresChestplate.setItemMeta(acm);
		
		ItemStack Leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		ItemMeta lm = Leggings.getItemMeta();
		lm.setUnbreakable(true);
		Leggings.setItemMeta(lm);
		
		ItemStack AresBoots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta abm = (LeatherArmorMeta) AresBoots.getItemMeta();
		abm.setColor(Color.YELLOW);
		abm.setUnbreakable(true);
		AresBoots.setItemMeta(abm);
		
		ItemStack Sword = new ItemStack(Material.BOW);
		ItemMeta sm = Sword.getItemMeta();
		ArrayList<String> lore1 = new ArrayList<String>();
		lore1.add(ChatColor.YELLOW + "Escape the Danger!");
		sm.setLore(lore1);
		sm.setUnbreakable(true);
		sm.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Escape!");
		sm.addEnchant(Enchantment.KNOCKBACK, 3, true);
		sm.addEnchant(Enchantment.ARROW_FIRE, 1, true);
		sm.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		Sword.setItemMeta(sm);
		
		p.getInventory().setItem(0, Sword);
		p.getInventory().setItem(8, new ItemStack(Material.ARROW));
		
		p.getInventory().setHelmet(Helmet);
		p.getInventory().setChestplate(AresChestplate);
		p.getInventory().setLeggings(Leggings);
		p.getInventory().setBoots(AresBoots);
		
		
		p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
		
		p.getInventory().setItem(4, ItemStacks.getItem("kitpvpButton"));
		
		APOLLO.add(p);
		
	}
	
	public static void setKitNinja(Player p) {
		
		ItemStack Helmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta hm = (LeatherArmorMeta) Helmet.getItemMeta();
		hm.setColor(Color.BLACK);
		hm.setUnbreakable(true);
		Helmet.setItemMeta(hm);
		
		ItemStack Chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta cm = (LeatherArmorMeta) Helmet.getItemMeta();
		cm.setColor(Color.BLACK);
		cm.setUnbreakable(true);
		Chestplate.setItemMeta(cm);
		
		ItemStack Leggings = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lm = (LeatherArmorMeta) Leggings.getItemMeta();
		lm.setColor(Color.BLACK);
		lm.setUnbreakable(true);
		Leggings.setItemMeta(lm);
		
		ItemStack Boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bm = (LeatherArmorMeta) Boots.getItemMeta();
		bm.setColor(Color.BLACK);
		bm.setUnbreakable(true);
		Boots.setItemMeta(bm);
		
		ItemStack Sword = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta sm = Sword.getItemMeta();
		ArrayList<String> lore1 = new ArrayList<String>();
		lore1.add(ChatColor.YELLOW + "Teleport a Distance!");
		sm.setLore(lore1);
		sm.setUnbreakable(true);
		sm.setDisplayName("Blink!");
		Sword.setItemMeta(sm);
		
		ItemStack Cloak = new ItemStack(Material.FIRE_CHARGE);
		ItemMeta cloakMeta = Cloak.getItemMeta();
		ArrayList<String> cloakLore = new ArrayList<String>();
		cloakLore.add(ChatColor.YELLOW + "Cloak for 7 Seconds!");
		cloakMeta.setLore(cloakLore);
		cloakMeta.setUnbreakable(true);
		cloakMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Cloak");
		Cloak.setItemMeta(cloakMeta);
		
		p.getInventory().setItem(0, Sword);
		p.getInventory().setItem(1, Cloak);
		
		p.getInventory().setHelmet(Helmet);
		p.getInventory().setChestplate(Chestplate);
		p.getInventory().setLeggings(Leggings);
		p.getInventory().setBoots(Boots);
		
		p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
		
		PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 9999 * 20, 1);
		p.addPotionEffect(speed);
		
		p.getInventory().setItem(4, ItemStacks.getItem("kitpvpButton"));
		
		NINJA.add(p);
		
	}
	
}
