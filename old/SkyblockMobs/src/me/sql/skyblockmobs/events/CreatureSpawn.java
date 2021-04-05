package me.sql.skyblockmobs.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;

import me.sql.skyblockmobs.SkyblockMobs;
import me.sql.skyblockmobs.SpecialSymbols;
import me.sql.skyblockmobs.mobs.Mob;
import me.sql.skyblockmobs.mobs.Monsters;

public class CreatureSpawn implements Listener {

	World theWorld = Bukkit.getServer().getWorld("world");
	static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("SkyblockMobs");
	SkyblockMobs sbMobs = (SkyblockMobs) Bukkit.getPluginManager().getPlugin("SkyblockMobs");
	
	public static void createName(LivingEntity creature, Mob mob) {
		if(mob.nametag) {
			Location loc = creature.getLocation();
			ArmorStand as = (ArmorStand) Bukkit.getWorld("world").spawnEntity(loc, EntityType.ARMOR_STAND);
			as.setVisible(false);
			as.teleport(creature.getLocation().add(0, creature.getEyeHeight()+0.3, 0));

			as.setCustomName("§8[§7Lv"+mob.level+"§8] §c"+mob.name+" §a"+mob.hp+"§r/§a"+mob.maxHP+SpecialSymbols.healthSymbol);
			as.setCustomNameVisible(true);
			as.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999, 127, false, false));
			as.setGravity(false);
			as.setSmall(true);
			as.setMarker(true);
			double height = ((CraftLivingEntity) creature).getHandle().getHeadHeight();
			new BukkitRunnable(){
			    @Override
			    public void run(){
			        as.teleport(creature.getLocation().add(0, height+0.1, 0));
			    }
			}.runTaskTimer(plugin, 0L, 1L);
		}
	}
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		if(event.getSpawnReason() == SpawnReason.NATURAL|| event.getSpawnReason() == SpawnReason.CHUNK_GEN || event.getSpawnReason() == SpawnReason.JOCKEY) {
			event.setCancelled(true);
		}
		else if(event.getSpawnReason() != SpawnReason.CUSTOM) {
			event.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999999, 10, false, false));
			createName(event.getEntity(), Monsters.getMob(event.getEntity()));
			Mob mob = Monsters.getMob(event.getEntity());
			sbMobs.setMetadata(event.getEntity(), mob);
		}
		
	}
	
	@EventHandler
	public void onCreatureDeath(EntityDeathEvent event) {
		event.getEntity().getLocation().getWorld().getNearbyEntities(event.getEntity().getLocation(), 1.0, 5.0, 1.0).forEach(entity -> {
			if(entity.getType()==EntityType.ARMOR_STAND)
				entity.remove();
		});
		
	}


	
}
