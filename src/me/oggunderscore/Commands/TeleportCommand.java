package me.oggunderscore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {

    public static void playertoLoc(Player player, World world, String sX, String sY, String sZ) {
        Player p = Bukkit.getServer().getPlayer(player.getName());
        Location pl = p.getLocation();
        Chunk chunk = p.getWorld().getChunkAt(pl);
        Bukkit.getWorld(p.getWorld().getName()).loadChunk(chunk);
        try {
            double x = Double.parseDouble(sX);
            double y = Double.parseDouble(sY);
            double z = Double.parseDouble(sZ);
            pl = new Location(p.getWorld(), x, y, z);
            p.teleport(pl);
            p.sendMessage("§7[§9Teleport§7] Teleported to " + x + " " + y + " " + z);
        } catch (Exception e) {
            player.sendMessage("§7[§9Teleport§7] §7Commands List:");
            player.sendMessage("§6/tp <target> §7Teleport to Player §6Mod ");
            player.sendMessage("b(ack) <amount> <Player> Teleports");
            player.sendMessage("§4/tp (you) (player) §7Teleports Player to Self §4Admin");
            player.sendMessage("§4/tp <player> <target> §7Teleports Player to Player §4Admin");
            player.sendMessage("§4/tp <X> <Y> <Z> §7Teleports to Location §4Admin");
        }
    }

    public static void playerToPlayer(Player player, String target1, String target2) {
        Player t1 = Bukkit.getPlayerExact(target1);
        Player t2 = Bukkit.getPlayerExact(target2);
        if (t1 == null) {
            player.sendMessage("§7[§9Teleport§7] No target found : " + target1);
            return;
        }
        if (t2 == null) {
            player.sendMessage("§7[§9Teleport§7] No target found : " + target2);
            return;
        }
        t1.teleport(t2.getLocation());
        t1.sendMessage("§7[§9Teleport§7] You were teleported to " + t2.getName());
        player.sendMessage("§7[§9Teleport§7] Teleported " + t1.getName() + " to " + t2.getName());
    }

    public static void playerToSelf(Player player, String target) {
        Player t = Bukkit.getPlayerExact(target);
        if (t == null) {
            player.sendMessage("§7[§9Teleport§7] No target found : " + target);
            return;
        }
        t.teleport(player.getLocation());
        t.sendMessage("§7[§9Teleport§7] " + player.getName() + " teleported to Self.");
        player.sendMessage("§7[§9Teleport§7] Teleported " + target + " to Self.");
    }

    public static void teleportToPlayer(Player player, String target) {
        Player t = Bukkit.getPlayerExact(target);
        if (t == null) {
            player.sendMessage("§7[§9Teleport§7] No target found : " + target);
            return;
        }
        player.sendMessage("§7[§9Teleport§7] Teleported to " + t.getName());
        player.teleport(t.getLocation());
    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {


        if (!(sender instanceof Player)) {
            sender.sendMessage("Players only!");
            return false;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("rank.mod")) {
            player.sendMessage("§7[§9Permissions§7] §7You don't have permission for that.");
            return false;
        }
        if (args.length == 1)
            teleportToPlayer(player, args[0]);
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("here")) {
                if (!player.hasPermission("rank.admin")) {
                    player.sendMessage("§7[§9Permissions§7] §7You don't have permission for that.");
                    return false;
                }
                playerToSelf(player, args[1]);
                return true;
            }
            if (!args[0].equalsIgnoreCase("here")) {
                if (!player.hasPermission("rank.admin")) {
                    player.sendMessage("§7[§9Permissions§7] §7You don't have permission for that.");
                    return false;
                }
                playerToPlayer(player, args[0], args[1]);
                return true;
            }
            if (args.length == 3) {
                if (player.hasPermission("rank.admin"))
                    player.sendMessage("§7[§9Permissions§7] §7You don't have permission for that.");
                return false;
            }
            playertoLoc(player, player.getWorld(), args[0], args[1], args[2]);
            return true;
        }
        if (args.length == 0) {
        	player.sendMessage("§7[§9Teleport§7] §7Commands List:");
            player.sendMessage("§6/tp <target> §7Teleport to Player §6Mod ");
            player.sendMessage("b(ack) <amount> <Player> Teleports");
            player.sendMessage("§4/tp (you) (player) §7Teleports Player to Self §4Admin");
            player.sendMessage("§4/tp <player> <target> §7Teleports Player to Player §4Admin");
            player.sendMessage("§4/tp <X> <Y> <Z> §7Teleports to Location §4Admin");
            return true;
        }
        return false;
    }

}