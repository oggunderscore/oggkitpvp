package me.oggunderscore.Utils;

import com.mysql.fabric.xmlrpc.base.Array;
import me.oggunderscore.Core.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_15_R1.EntityPlayer;
import net.minecraft.server.v1_15_R1.IChatBaseComponent;
import net.minecraft.server.v1_15_R1.PacketPlayOutChat;
import net.minecraft.server.v1_15_R1.PacketPlayOutPlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class ActionBar {

    private ArrayList<UUID> uuids;

    private Main main;

     public ActionBar(Player player, String message, Main main, ArrayList<UUID> uuids) {




        new BukkitRunnable() {

            @Override
            public void run() {

                if (!uuids.contains(player.getUniqueId())) {


                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColorChange.chat("")));


                    for (Player p : Bukkit.getOnlinePlayers()) {

                        p.showPlayer(main, player);
                    }



                    this.cancel();

                } else {

                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.hasPermission("core.vanish.see") | p.isOp()) {
                            p.showPlayer(main, player);

                        } else {

                            p.hidePlayer(main, player);

                        }
                     }


                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColorChange.chat(message)));
                }


            }
        }.runTaskTimer(main, 1L, 1L);

        this.uuids = uuids;

        this.main = main;



    }


}
