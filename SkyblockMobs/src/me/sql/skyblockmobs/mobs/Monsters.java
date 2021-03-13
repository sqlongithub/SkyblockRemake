package me.sql.skyblockmobs.mobs;

import java.util.Arrays;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import me.sql.skyblockmobs.mobs.Mob;

public class Monsters {
	
	public static Mob getExistingMob(LivingEntity creature) {
		return null;
		
	}
	
	private static String[] customMobNames =     {"zealot"           };
	
	public static EntityType getType(String _name) {
		String name = _name.toUpperCase();
		
		try {
			return EntityType.valueOf(name);
		} catch(IllegalArgumentException e) {
		
			switch(name) {
			// Custom
			case "ZEALOT":
				return EntityType.ENDERMAN;
		
			}
		}
		return null;
	}
	
	public static Mob getMob(LivingEntity creature, String customName) {
		EntityType entType = creature.getType();
		
		if(Arrays.asList(customMobNames).contains(customName.toLowerCase())) {
			switch(customName) {
			case "zealot":
				return new Mob(55,13000,"Zealot", EntityType.ENDERMAN, true, creature);
			}
			return new Mob(1,100,"Mob",EntityType.ARMOR_STAND,true,creature);
		}
		
		switch(entType) {
		
		// Probably nametag
			
		// Monsters
			
		case ZOMBIE:
			return new Mob(1, 100, "Zombie", EntityType.ZOMBIE, true, creature);
		case PIG_ZOMBIE:
			return new Mob(12, 240, "Pigman", EntityType.PIG_ZOMBIE, true, creature);
		case SPIDER:
			break;
		case CAVE_SPIDER:
			break;
		case SKELETON:
			break;
		case WITHER:
			break; 
		case BLAZE:
			break;
		case BAT:
			break;
		case GHAST:
			break;
		case WITCH:
			break;
		case WOLF:
			break;
		case GIANT:
			break;
		case GUARDIAN:
			break;
		case IRON_GOLEM:
			break;
		case CREEPER:
			break;
		case ENDERMAN:
			break;
		case ENDER_DRAGON:
			break;
		case SLIME:
			break;
		case SILVERFISH:
			break;
		case ENDERMITE:
			break;
			
		// Animals
			
		case CHICKEN:
			return new Mob(1, 20, "Chicken", EntityType.CHICKEN, false, creature);
		case PIG:
			return new Mob(1, 400, "Pig", EntityType.PIG, false, creature);
		case HORSE:
			break;
		case OCELOT:
			break;
		case RABBIT:
			break;
		case SHEEP:
			break;
		case COW:
			break;
		case SNOWMAN:
			break;
		case VILLAGER:
			break;
		case SQUID:
			break;
			
		// Player
			
		case PLAYER:
			break;
		
		default:
			return new Mob(1,100,"Mob",EntityType.ARMOR_STAND, false, creature);
		}
		return null;
	}
	
	
	public static Mob getMob(LivingEntity creature) {
		EntityType entType = creature.getType();
		
		
		switch(entType) {
		
		// Probably nametag
			
		// Monsters
			
		case ZOMBIE:
			return new Mob(1, 100, "Zombie", EntityType.ZOMBIE, true, creature);
		case PIG_ZOMBIE:
			break;
		case SPIDER:
			break;
		case CAVE_SPIDER:
			break;
		case SKELETON:
			break;
		case WITHER:
			break; 
		case BLAZE:
			break;
		case BAT:
			break;
		case GHAST:
			break;
		case WITCH:
			break;
		case WOLF:
			break;
		case GIANT:
			break;
		case GUARDIAN:
			break;
		case IRON_GOLEM:
			break;
		case CREEPER:
			break;
		case ENDERMAN:
			break;
		case ENDER_DRAGON:
			break;
		case SLIME:
			break;
		case SILVERFISH:
			break;
		case ENDERMITE:
			break;
			
		// Animals
			
		case CHICKEN:
			return new Mob(1, 20, "Chicken", EntityType.CHICKEN, false, creature);
		case PIG:
			return new Mob(1, 400, "Pig", EntityType.PIG, false, creature);
		case HORSE:
			break;
		case OCELOT:
			break;
		case RABBIT:
			break;
		case SHEEP:
			break;
		case COW:
			break;
		case SNOWMAN:
			break;
		case VILLAGER:
			break;
		case SQUID:
			break;
			
		// Player
			
		case PLAYER:
			break;
		
		default:
			return new Mob(1,100,"Mob",EntityType.ARMOR_STAND, false, creature);
		
		}
		
		return new Mob(1,100,"Mob",EntityType.ARMOR_STAND, false, creature);
	}
	
}
