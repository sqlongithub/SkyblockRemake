package me.sql.skyblockmobs.mobs.customentities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.entity.Enderman;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import net.minecraft.server.v1_8_R3.EntityEnderman;
import net.minecraft.server.v1_8_R3.World;

public class CustomEntityEnderman extends EntityEnderman {

	public CustomEntityEnderman(World world) {
		super(world);
		
		
		Bukkit.getLogger().info("Enderman");
	}
	
	
	public static Enderman spawn(Location loc) {
		World mcWorld = (World) ((CraftWorld) loc.getWorld()).getHandle();
		final CustomEntityEnderman customEntity = new CustomEntityEnderman(mcWorld);
		
		customEntity.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		((CraftLivingEntity) customEntity.getBukkitEntity()).setRemoveWhenFarAway(false);
		mcWorld.addEntity(customEntity, SpawnReason.DEFAULT);
		return (Enderman) customEntity.getBukkitEntity();
	}
	
	// Stop the enderman from teleporting
	@Override
	public boolean k(double d0, double d1, double d2) {
		return false;
	}
	     
	
	

}
