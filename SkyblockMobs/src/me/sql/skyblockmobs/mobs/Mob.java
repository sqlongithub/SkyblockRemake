package me.sql.skyblockmobs.mobs;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class Mob {
	
	public int level;
	public int hp;
	public int maxHP;
	public String name;
	public EntityType type;
	public boolean nametag;
	
	public Mob(int lvl, int hp, String name, EntityType type, boolean nametag, LivingEntity creature) {
		this.level = lvl;
		this.maxHP = hp;
		this.name = name;
		this.type = type;
		this.nametag = nametag;
		this.hp = this.maxHP;
		
	}
}
