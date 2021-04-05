package me.sql.skyblockdamage.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import me.sql.skyblockmobs.SkyblockMobs;

public class Damage implements Listener {

    private SkyblockMobs sbMobs = (SkyblockMobs) Bukkit.getPluginManager().getPlugin("SkyblockMobs");
    
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent event) {
		Entity damagerEntity = event.getDamager();
		Entity victimEntity = event.getEntity();
		LivingEntity damager = null;
		LivingEntity victim = null;
	
		Player dmgPly = null;
		Player vicPly = null;
		if(damagerEntity instanceof LivingEntity)
			damager = (LivingEntity) damagerEntity;;
		if(victimEntity instanceof LivingEntity)
			victim = (LivingEntity) victimEntity;
		if(damagerEntity instanceof Player)
			dmgPly = (Player) damagerEntity;
		if(victimEntity instanceof Player)
			vicPly = (Player) victimEntity;
		
		if(dmgPly != null && victim != null) 
			playerHitLivingEntity(event, dmgPly, victim);
		if(dmgPly != null && vicPly != null) 
			playerHitPlayer(event, dmgPly, vicPly);
		if(damager != null && victim != null && dmgPly == null) 
			livingEntityHitLivingEntity(event, damager, victim);
		if(dmgPly == null && vicPly != null && damager != null) 
			livingEntityHitPlayer(event, dmgPly, vicPly);
		
		if(dmgPly != null && vicPly == null && victim == null)
			playerHitEntity(event, dmgPly, victimEntity);
		if(damager != null && victim == null && vicPly == null && dmgPly == null) {
			livingEntityHitEntity(event, damager, victim);
		}
		
	}
		
	
	public void playerHitLivingEntity(EntityDamageByEntityEvent event, Player ply, LivingEntity vic) {
		if(vic.hasMetadata("level")) {
			
			// This is the damage bonus from the Weapon Ability as a double/float. Example: +150% damage = weaponBonus = 1.5
			double weaponBonus = 0;
			// This is the damage bonus from the Armor Ability as a double/float. Example 150% damage = armorBonus = 1.5
			double armorBonus = 1;
			
			double baseDamage = (5 + /* Weapon DMG */ 0 + Math.floor(ply.getMetadata("str").get(0).asInt()/5)) * (1 + (ply.getMetadata("str").get(0).asInt()/100));
			double damageMultiplier = (1 + (/* CombatLevel*0.04, but we assume combat 35 */ 1.4) + /* Enchants, we dont have any */ + 0 + weaponBonus);
			int finalDamage =  (int) Math.floor(baseDamage * damageMultiplier * armorBonus * (1+(ply.getMetadata("cd").get(0).asInt()/100)));
			
			Bukkit.broadcastMessage("Hit damage: "+finalDamage);
			sbMobs.damageMob(vic, finalDamage);
			if(vic instanceof Enderman) {
				((Enderman) vic).setTarget(ply);
			}
		} else {
			Bukkit.broadcastMessage("§cFatal Damage Error: No metadata. Player: §a"+ply.getName()+"§c Entity: §a"+vic.getName());
		}
	}
	
	private void playerHitPlayer(EntityDamageByEntityEvent event, Player ply, LivingEntity vic) {
		
	}
	
	private void livingEntityHitLivingEntity(EntityDamageByEntityEvent event, LivingEntity dmg, LivingEntity vic) {
		
	}
	
	private void livingEntityHitPlayer(EntityDamageByEntityEvent event, LivingEntity dmg, Player vic) {
		
	}
	
	
	private void playerHitEntity(EntityDamageByEntityEvent event, Player ply, Entity vic) {
		if(vic.getType()==EntityType.ARMOR_STAND)
			event.setCancelled(true);
	}
	
	private void livingEntityHitEntity(EntityDamageByEntityEvent event, LivingEntity dmg, Entity vic) {
		
	}
}
