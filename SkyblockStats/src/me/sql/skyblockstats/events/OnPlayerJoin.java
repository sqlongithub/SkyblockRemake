package me.sql.skyblockstats.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import me.sql.skyblockstats.PlayerStats;
import me.sql.skyblockstats.SkyblockStats;
import me.sql.skyblockstats.database.Database;

public class OnPlayerJoin implements Listener {

	private String dbName = "PLAYERSTATS.db";
	private Plugin plugin = Bukkit.getPluginManager().getPlugin("SkyblockStats");
	
	public FixedMetadataValue metaValue(Object val) {
		return new FixedMetadataValue(plugin, val);
	}
	
	@EventHandler
	public void onJoin(PlayerLoginEvent event) {
		Bukkit.getLogger().info("Player joined");
		Player ply = event.getPlayer();
		String plyUUID = ply.getUniqueId().toString();
		
		// Player hasn't joined before
		if(!Database.rowExists(dbName, "playerstats", plyUUID) || !Database.databaseExists(dbName)) {
			Bukkit.getLogger().info("Player hasn't played before, or database doesnt exist");
			PlayerStats plyStats = new PlayerStats(ply);
			if(Database.databaseExists(dbName)) {
				Bukkit.getLogger().info("Database Exists");
				Database.createTable(dbName);
				Database.createRowInTable(dbName, "playerstats", plyStats);
			} else {
				Bukkit.getLogger().info("Database doesn't Exist");
				Database.createDatabase(dbName);
				Database.createTable(dbName);
				Database.createRowInTable(dbName, "playerstats", plyStats);
			}
			
		} else {
			// Player has joined before, set metadata
			if(Database.rowExists(dbName, "playerstats", plyUUID)) {
				ply.setMetadata("hp", metaValue(Database.getValue(dbName, "playerstats", "hp", plyUUID)));
				ply.setMetadata("def", metaValue(Database.getValue(dbName, "playerstats", "def", plyUUID)));
				ply.setMetadata("str", metaValue(Database.getValue(dbName, "playerstats", "str", plyUUID)));
				ply.setMetadata("fero", metaValue(Database.getValue(dbName, "playerstats", "fero", plyUUID)));
				ply.setMetadata("abildmg", metaValue(Database.getValue(dbName, "playerstats", "abildmg", plyUUID)));
				ply.setMetadata("attackspd", metaValue(Database.getValue(dbName, "playerstats", "attackspd", plyUUID)));
				ply.setMetadata("cc", metaValue(Database.getValue(dbName, "playerstats", "cc", plyUUID)));
				ply.setMetadata("cd", metaValue(Database.getValue(dbName, "playerstats", "cd", plyUUID)));
				ply.setMetadata("intel", metaValue(Database.getValue(dbName, "playerstats", "intel", plyUUID)));
				ply.setMetadata("miningspd", metaValue(Database.getValue(dbName,"playerstats","miningspd", plyUUID)));
				ply.setMetadata("speed", metaValue(Database.getValue(dbName, "playerstats", "speed", plyUUID)));	
				
				ply.setMaxHealth(SkyblockStats.getPlayerHearts(ply.getMetadata("hp").get(0).asInt()));
				if(ply.getMetadata("speed").get(0).asInt()<=500) {
					ply.setWalkSpeed(ply.getMetadata("speed").get(0).asFloat()/500);
				}
				
			}
		}	
	}
}
