package me.oggunderscore.Commands;

import me.oggunderscore.Utils.ChatColorChange;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvseeCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (!(sender instanceof Player)) {

            sender.sendMessage("You can not use this command!");

            return true;
        } else {

            Player player = (Player) sender;


            if (cmd.getName().equalsIgnoreCase("invsee")) {
                if (player.hasPermission("core.invsee")) {
                    if (args.length == 0) {
                        player.sendMessage(ChatColorChange.chat("&7[&9Invsee&7] &7Specify a Player!"));


                        return true;

                    }

                    if (args.length == 1) {


                        Player targetPlayer = Bukkit.getPlayer(args[0]);

                        if (targetPlayer != null) {

                            player.openInventory(targetPlayer.getInventory());

                            player.sendMessage(ChatColorChange.chat("&7[&9Invsee&7] &7Opened &e" + targetPlayer.getDisplayName() + " &7Inventory!"));

                        } else {

                            player.sendMessage(ChatColorChange.chat("&7[&9Invsee&7] &7Invalid Player!"));

                        }

                    }

                } else {
                    player.sendMessage(ChatColorChange.chat("&7[&9Permissions&7] &7No permission."));

                }
            }

        }
        return false;
    }
}
