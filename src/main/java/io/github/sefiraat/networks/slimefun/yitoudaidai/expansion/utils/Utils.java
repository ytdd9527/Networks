package io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.utils;


import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public final class Utils {

    public static String color(String str) {
        if (str == null) {
            return null;
        }

        return ChatColor.translateAlternateColorCodes('&', str);
    }
    public static void giveOrDropItem(Player p, ItemStack toGive) {
        for (ItemStack leftover : p.getInventory().addItem(toGive).values()) {
            p.getWorld().dropItemNaturally(p.getLocation(), leftover);
        }
    }
    public static void send(CommandSender p, String message) {
        p.sendMessage(color("&7[&6网络拓展&7] &r" + message));
    }
}
