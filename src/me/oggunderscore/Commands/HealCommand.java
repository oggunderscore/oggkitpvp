package me.oggunderscore.Commands;

 import me.oggunderscore.Utils.ChatColorChange;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String stringLabel, String[] args) {


        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("heal")) {
                if (args.length == 0) {

                    if (player.hasPermission("core.heal")) {

                        player.setHealth(20);
                        player.setFoodLevel(20);
                        player.sendMessage(ChatColorChange.chat("&7[&9Heal&7] &7You healed yourself."));



                    } else {

                        player.sendMessage(ChatColorChange.chat("&7[&9Permissions&7] &7No permission."));
                    }


                }
            }
        } else {

            try {

            } catch (ClassCastException e) {
                sender.sendMessage(ChatColorChange.chat("&7Players are only allowed to use this command!"));

            }


            return true;


        }

        return false;
    }
}
