package me.sql.skyblockitems.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class OnItemDurabilityLoss implements Listener {
	
	@EventHandler
	public void onItemDurabilityLoss(PlayerItemDamageEvent event) {
		event.setCancelled(true);
	}
	
}
