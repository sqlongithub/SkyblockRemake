package me.sql.skyblockstats;

import java.util.UUID;

import org.bukkit.entity.Player;

public class PlayerStats {
	
	public UUID plyUUID;
	public int hp = 100;
	public int def = 0;
	public int str = 0;
	public int fero = 0;
	public int abildmg = 0;
	public int intel = 0;
	public int attackspd = 0;
	public int cc = 30;
	public int cd = 50;
	public int miningspd = 0;
	public int speed = 100;
	
	
	public PlayerStats(Player ply, int hp, int def, int str, int fero, int abildmg, int intel, int attackspd, int cc, int cd, int miningspd, int speed) {
		
		this.plyUUID = ply.getUniqueId();
		this.hp = hp;
		this.def = def;
		this.str = str;
		this.fero = fero;
		this.abildmg = abildmg;
		this.intel = intel;
		this.attackspd = attackspd;
		this.cc = cc;
		this.cd = cd;
		this.miningspd = miningspd;
		this.speed = speed;
		
	}
	public PlayerStats(Player ply) {
		
		this.plyUUID = ply.getUniqueId();
		
	}
	
	public String getUUIDString() {
		return this.plyUUID.toString();
	}
	
	
}
