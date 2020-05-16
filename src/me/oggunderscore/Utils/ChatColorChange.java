package me.oggunderscore.Utils;

import org.bukkit.ChatColor;

public class ChatColorChange {

    public static String chat(String s) {

        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
