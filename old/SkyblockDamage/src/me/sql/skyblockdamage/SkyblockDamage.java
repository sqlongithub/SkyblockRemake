package me.sql.skyblockdamage;

import me.sql.skyblockdamage.events.Damage;
import me.sql.skyblockmobs.SkyblockMobs;
import me.sql.skyblockdamage.commands.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyblockDamage extends JavaPlugin {
	
	public SkyblockMobs sbmobs;
	public SkyblockMobs sbmobs_instance;
	
	private boolean aliasCheck(String s) {
		return s.equalsIgnoreCase("sbd") || s.equalsIgnoreCase("skyblockdamage") || s.equalsIgnoreCase("sbdamage");
	}
	
	
    @Override
    public void onEnable() {
    	// Register listeners
    	getServer().getPluginManager().registerEvents(new Damage(), this);
    	sbmobs = (SkyblockMobs) getServer().getPluginManager().getPlugin("SkyblockMobs");
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	if(aliasCheck(label)) {
    		if (args.length>0 && args[0].equalsIgnoreCase("help"))
    			return Help.help(sender);
    		
    		return true;
    	}
        return false;
    }
}
