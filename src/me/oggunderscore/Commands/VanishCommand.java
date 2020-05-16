package me.oggunderscore.Commands;

import com.google.gson.internal.$Gson$Preconditions;
import me.oggunderscore.Core.Main;
import me.oggunderscore.Utils.ActionBar;
import me.oggunderscore.Utils.ChatColorChange;
import net.minecraft.server.v1_15_R1.PacketPlayOutPlayerInfo;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class VanishCommand implements CommandExecutor {


    private ArrayList<UUID> uuids;

    private Main main;

    public VanishCommand(Main main, ArrayList<UUID> uuids) {


        this.uuids = uuids;
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {


        if (!(sender instanceof Player))  {

            sender.sendMessage("Only players in-game can use this command!");

        } else {



            Player player = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("vanish")) {
                if (args.length == 0) {
                    if (player.hasPermission("core.vanish")) {

                        if (uuids.contains(player.getUniqueId())) {



                            uuids.remove(player.getUniqueId());

                            player.sendMessage(ChatColorChange.chat("&7[&9Vanish&7] &7You exited vanish mode."));

                        } else {

                            uuids.add(player.getUniqueId());

                            // Added
                          new ActionBar(player, ChatColorChange.chat("&7You are in &9vanish &7mode. &7You are only visible to &6Mod+"), main, uuids);

                            player.sendMessage(ChatColorChange.chat("&7[&9Vanish&7] &7You entered vanish mode."));

                        }




                    } else {

                        player.sendMessage(ChatColorChange.chat("&7[&9Permission&7] &7No permission."));
                    }
                }

            }

        }



        return false;
    }
}
