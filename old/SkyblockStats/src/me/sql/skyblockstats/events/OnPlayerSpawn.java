package me.sql.skyblockstats.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.metadata.FixedMetadataValue;

import me.sql.skyblockstats.SkyblockStats;
import me.sql.skyblockstats.database.Database;

public class OnPlayerSpawn implements Listener {
	
	public FixedMetadataValue metaValue(Object val) {
		return new FixedMetadataValue(Bukkit.getPluginManager().getPlugin("SkyblockStats"), val);
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player ply = event.getPlayer();
		String dbName = "PLAYERSTATS";
		String plyUUID = ply.getUniqueId().toString();
		if(ply.hasMetadata("hp")) {
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
				
				ply.setMaxHealth(SkyblockStats.getPlayerHearts(ply.getMetadata("hp").get(0).asInt()));
				if(ply.getMetadata("speed").get(0).asInt()<=500) {
					ply.setWalkSpeed(ply.getMetadata("speed").get(0).asFloat()/500);
				} else {
					ply.setWalkSpeed(1);
				}
				
			}
		}
	}
	
}