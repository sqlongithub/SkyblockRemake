package me.sql.skyblockitems.items;

import org.bukkit.Effect;

public enum AbilityType {

	AOTE_TELEPROT("Teleport", 8, 0, 0);
	private String name;
	private int teleportBlocks;
	private int areaDamage;
	private int areaRange;
	private Effect abilParticle;
	
	private AbilityType(String name, int teleportBlocks, int areaDamage, int areaRange, Effect abilParticle) {
		this.name = name;
		this.teleportBlocks = teleportBlocks;
		this.areaDamage = areaDamage;
		this.areaRange = areaRange;
		this.abilParticle = abilParticle;
	}
	
	private AbilityType(String name, int teleportBlocks, int areaDamage, int areaRange) {
		this.name = name;
		this.teleportBlocks = teleportBlocks;
		this.areaDamage = areaDamage;
		this.areaRange = areaRange;
	}
	
	public String getName() {
		return name;
	}
	
	public int getTeleportBlocks() {
		return teleportBlocks;
	}
	
	public int getAreaDamage() {
		return areaDamage;
	}
	
	public int getAreaRange() {
		return areaRange;
	}
	
	public Effect getEffect() {
		return abilParticle;
	}
	
	
	
	
}
