package me.oggunderscore.Commands;

import me.oggunderscore.Utils.ChatColorChange;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FlyCommand implements CommandExecutor, Listener {


    private List<UUID> list = new ArrayList<>();


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;


            if (cmd.getName().equalsIgnoreCase("fly")) {
                if (args.length == 0) {
                    if (player.hasPermission("core.fly")) {

                        if (!list.contains(player.getUniqueId())) {

                            list.add(player.getUniqueId());

                            player.setAllowFlight(true);
                            player.setFlying(true);
                             player.sendMessage(ChatColorChange.chat("&7[&9Flight&7] &7You have enabled flight"));


                        } else {
                            list.remove(player.getUniqueId());

                            player.setAllowFlight(false);
                            player.setFlying(false);

                            player.sendMessage(ChatColorChange.chat("&7[&9Flight&7] &7You have disabled flight"));

                        }

                    } else {

                        player.sendMessage(ChatColorChange.chat("&7[&9Permissions&7] &7No permission."));
                    }
                    return true;

                }
            }
        }


        return false;
    }




}
