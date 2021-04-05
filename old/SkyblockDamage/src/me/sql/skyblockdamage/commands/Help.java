package me.sql.skyblockdamage.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Help {
    public static boolean help(CommandSender sender) {
        if(sender instanceof Player) {
            Player ply = (Player) sender;
            ply.sendMessage("§7--------§8[§6SkyblockDamage§8]§7--------\n" +
                            "§aHelp Menu §6(1/1)\n");
            return true;
        }
        return false;
    }
}
