package me.oggunderscore.Commands;

import me.oggunderscore.Utils.ChatColorChange;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor  {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (!(sender instanceof Player)) {

            sender.sendMessage("You are not allowed to use this command!");

            return true;

        }

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("ping")) {
            if (args.length == 0) {

               int ping = ((CraftPlayer) player).getHandle().ping;

               player.sendMessage(ChatColorChange.chat("&7[&9Ping&7]&7 " + player.getDisplayName() + "'s ping is &e" + ping + "ms"));

            return true;

            }
            if (args.length == 1) {  

                Player targetPlayer = Bukkit.getPlayerExact(args[0]);
                if (targetPlayer != null) {

                    int targetPing = ((CraftPlayer) targetPlayer).getHandle().ping;

                    player.sendMessage(ChatColorChange.chat("&7[&9Ping&7]&7 " + targetPlayer.getDisplayName() + "'s ping is &e" + targetPing + "ms"));


                } else {

                    player.sendMessage(ChatColorChange.chat("&7[&9Ping&7]&7 The player you entered is invalid!"));

                }





                return true;

            }


        }




        return false;
    }
}
