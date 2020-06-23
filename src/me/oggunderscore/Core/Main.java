package me.oggunderscore.Core;

import me.oggunderscore.Commands.*;
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
import me.oggunderscore.Managers.UnlockManager;
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


	public void onEnable() {

		uuids = new ArrayList<>();



		ArenaManager.arenaNumber = 0;
		ArenaManager.chosenArena1 = Locations.twilightArena1;
		ArenaManager.chosenArena2 = Locations.twilightArena2;
		ArenaManager.arenaName = "Twilight Forest" + ChatColor.AQUA + " by Kreed95";

		getConfig().options().copyDefaults(true);
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new Events(this, uuids), this);
		pm.registerEvents(new FightManager(), this);
		pm.registerEvents(new FFAManager(), this);
		pm.registerEvents(new EnvironmentManager(), this);
		pm.registerEvents(new MenuManager(), this);
		pm.registerEvents(new FlyCommand(), this);
		pm.registerEvents(new UnlockManager(), this);

		getCommand("invsee").setExecutor(new InvseeCommand());
		getCommand("tp").setExecutor(new TeleportCommand());
		getCommand("givegold").setExecutor(new StaffCommands());
		getCommand("update").setExecutor(new StaffCommands());
		getCommand("poke").setExecutor(new PlayerCommands());
		getCommand("ping").setExecutor(new PingCommand());
		getCommand("heal").setExecutor(new HealCommand());
		getCommand("fly").setExecutor(new FlyCommand());
		getCommand("broadcast").setExecutor(new BroadcastCommand());
		getCommand("forceleave").setExecutor(new StaffCommands());
		getCommand("oggclear").setExecutor(new StaffCommands());
		getCommand("urf").setExecutor(new GameCommands());

		getCommand("vanish").setExecutor(new VanishCommand(this, uuids));

		instance = this; //  What does this do?


		Bukkit.getServer().broadcastMessage(Prefix.corePrefix + "has been enabled!");
		Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "" + ChatColor.ITALIC + "Version 1.0");

		saveConfig();

		pm.registerEvents(new ClearCommand(), this);

	}

	public void onDisable() {
		Bukkit.getServer().broadcastMessage(Prefix.corePrefix + "has been disabled!");
	}

}
