package me.oggunderscore.Commands;

import me.oggunderscore.Utils.ChatColorChange;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {


            Player player = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("broadcast")) {
                if (args.length == 0) {
                    if (!player.hasPermission("core.broadcast")) {
                        player.sendMessage(ChatColorChange.chat("&7[&9Permission&7] &7No permission."));

                    } else {
                        player.sendMessage(ChatColorChange.chat("&7[&9Broadcast&7] &7/broadcast <message>"));

                        return true;

                    }
                    return true;
                }

                if (args.length >= 1 ) {
                    if (player.hasPermission("core.broadcast")) {

                        StringBuilder stringBuilder = new StringBuilder();

                        for (int i = 0; i < args.length; i++) {

                            stringBuilder.append(args[i]).append(" ");

                        }

                        String broadcastMessage = stringBuilder.toString();


                        Bukkit.broadcastMessage(ChatColorChange.chat("&7[&9Broadcast&7] " + broadcastMessage));

                    } else {

                        player.sendMessage(ChatColorChange.chat("&7[&9Permission&7] &7No permission."));

                    }



                }

            }


        } else {

            sender.sendMessage("You need to be a player to use this command.");
        }


        return false;
    }
}
