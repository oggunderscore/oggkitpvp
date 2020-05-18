package me.oggunderscore.Commands;

import me.oggunderscore.Utils.ChatColorChange;
import org.bukkit.Bukkit;
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

        String[] args = e.getMessage().split(" ");

        if(args[0].equalsIgnoreCase("/clear")){

                 e.setCancelled(true);

            if (args.length >=2) {
                 if (Bukkit.getPlayer(args[1]) != null) {

                     Player targetPlayer = Bukkit.getPlayer(args[1]);

                     targetPlayer.getInventory().clear();

                     player.sendMessage(ChatColorChange.chat("&7[&9Inventory&7] &7Inventory Items Cleared For Player &e" + targetPlayer.getDisplayName() + "!"));

                     }
                 } else {
                     player.sendMessage(ChatColorChange.chat("&7[&9Inventory&7] Invalid Player!"));


                 }









                return;



        }
    }
}
