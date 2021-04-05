package me.sql.skyblockmobs.events;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

public class CreatureBurn implements Listener {
	
	private List<EntityType> mobsBurnedInSunlight = Arrays.asList(EntityType.SKELETON, EntityType.ZOMBIE);
	
    public boolean exposedToSunlight(Entity entity) {
    	 
        Vector vector = entity.getLocation().toVector();
        // When using 256 it loops back to 0.
        int distance = 255 - vector.getBlockY();
        BlockIterator it =
                new BlockIterator(entity.getWorld(), vector, new Vector(0, 1, 0), 0,
                        distance);
        while (it.hasNext()) {
            if (it.next().getType().isSolid())
                return false;
        }
        return true;
    }
    
    @EventHandler
    public void OnCreatureBurn(EntityCombustEvent event) {
    	if(exposedToSunlight(event.getEntity()) && mobsBurnedInSunlight.contains(event.getEntityType())) {
    		event.setCancelled(true);
    	}
    }
}
