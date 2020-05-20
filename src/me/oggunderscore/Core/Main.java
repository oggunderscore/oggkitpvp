package me.oggunderscore.Core;

import me.oggunderscore.Commands.*;
import me.oggunderscore.Utils.ActionBar;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.oggunderscore.Managers.ArenaManager;
import me.oggunderscore.Managers.EnvironmentManager;
import me.oggunderscore.Managers.FFAManager;
import me.oggunderscore.Managers.FightManager;
import me.oggunderscore.Managers.MenuManager;
import me.oggunderscore.Utils.Locations;
import me.oggunderscore.Utils.Prefix;

import java.util.ArrayList;
import java.util.UUID;

public class Main extends JavaPlugin {

	private static Plugin instance;
	public static boolean serverDisabled;
	
	public static Plugin getInstance() {
		return instance;
	}

	private ArrayList<UUID> uuids;

	private ActionBar actionBar;

	public void onEnable() {

		uuids = new ArrayList<>();



		ArenaManager.arenaNumber = 0;
		ArenaManager.chosenArena1 = Locations.twilightArena1;
		ArenaManager.chosenArena2 = Locations.twilightArena2;
		ArenaManager.arenaName = "Twilight Forest" + ChatColor.AQUA + " by Kreed95";

		getConfig().options().copyDefaults(true);
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new Events(this, uuids
		), this);
		pm.registerEvents(new FightManager(), this);
		pm.registerEvents(new FFAManager(), this);
		pm.registerEvents(new EnvironmentManager(), this);
		pm.registerEvents(new MenuManager(), this);
		pm.registerEvents(new FlyCommand(), this);

		getCommand("invsee").setExecutor(new InvseeCommand());
		getCommand("tp").setExecutor(new TeleportCommand());
		getCommand("dev").setExecutor(new StaffCommands());
		getCommand("stats").setExecutor(new PlayerCommands());
		getCommand("coreinfo").setExecutor(new GameCommands());
		getCommand("unlock").setExecutor(new GameCommands());
		getCommand("arena").setExecutor(new ArenaManager());
		getCommand("givegold").setExecutor(new StaffCommands());
		getCommand("togglegravity").setExecutor(new StaffCommands());
		getCommand("website").setExecutor(new PlayerCommands());
		getCommand("discord").setExecutor(new PlayerCommands());
		getCommand("apply").setExecutor(new PlayerCommands());
		getCommand("update").setExecutor(new StaffCommands());
		getCommand("commands").setExecutor(new PlayerCommands());
		getCommand("event").setExecutor(new PlayerCommands());
		getCommand("poke").setExecutor(new PlayerCommands());
		getCommand("staff").setExecutor(new PlayerCommands());
		getCommand("ranks").setExecutor(new PlayerCommands());
		getCommand("ping").setExecutor(new PingCommand());
		
		//getCommand("autocast").setExecutor(new GameCommands()); // Disabled
		//getCommand("hotbarmode").setExecutor(new GameCommands());
		getCommand("kitpvp").setExecutor(new GameCommands());
		getCommand("heal").setExecutor(new HealCommand());
		getCommand("fly").setExecutor(new FlyCommand());
		getCommand("broadcast").setExecutor(new BroadcastCommand());
		getCommand("items").setExecutor(new StaffCommands());
		getCommand("forceleave").setExecutor(new StaffCommands());
		getCommand("code").setExecutor(new StaffCommands());
		getCommand("oggclear").setExecutor(new StaffCommands());
		getCommand("oggdeop").setExecutor(new StaffCommands());
		getCommand("urf").setExecutor(new GameCommands());

		getCommand("vanish").setExecutor(new VanishCommand(this, uuids));

		instance = this; //  What does this do?


		Bukkit.getServer().broadcastMessage(Prefix.corePrefix + "has been enabled!");
		Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "" + ChatColor.ITALIC + "Plugin Version 0.97");

		saveConfig();

		pm.registerEvents(new ClearCommand(), this);

	}

	public void onDisable() {
		Bukkit.getServer().broadcastMessage(Prefix.corePrefix + "has been disabled!");
	}

}
