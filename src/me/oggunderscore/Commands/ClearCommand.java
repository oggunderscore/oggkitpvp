package me.oggunderscore.Commands;

import javafx.print.PageLayout;
import me.oggunderscore.Utils.ChatColorChange;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ClearCommand implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPreCommand(PlayerCommandPreprocessEvent e){

        Player player = e.getPlayer();
        if(e.getMessage().startsWith("/clear")){

            e.setCancelled(true);

            if (player.hasPermission("core.clear")) {


                player.getInventory().clear();

                 player.sendMessage(ChatColorChange.chat("&7[&9Inventory&7] &7Inventory Items Cleared!"));


            } else {

                player.sendMessage(ChatColorChange.chat("&7[&9Permissions&7] &7No permission."));

             }


        }
    }
}
