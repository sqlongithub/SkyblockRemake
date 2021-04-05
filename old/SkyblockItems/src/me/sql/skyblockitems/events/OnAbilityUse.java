package me.sql.skyblockitems.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import me.sql.skyblockitems.items.ItemAbility;
import me.sql.skyblockitems.items.SkyblockItem;
import me.sql.skyblockitems.items.customitems.SbItems;

public class OnAbilityUse implements Listener {

	
//	private Location getBlockTeleportLocation(Vector vec, Block block, Vector direction) {
//		Location loc = block.getLocation();
//		loc.setDirection(direction);
//		
//		Bukkit.broadcastMessage("Y vec: "+direction.getY());
//		
//		if(vec.getX()>0.8) {
//			// Right side looking at left of block
//			return loc.add(1.5, 0, 0.5);
//		} else if(vec.getX()<-0.8) {
//			// Left side looking at right of block
//			return loc.add(-0.5,0,0.5);
//		} else if(vec.getZ()>0.8) {
//			// Bottom side looking at top of block
//			return loc.add(0.5,0,1.5);
//		} else if(vec.getZ()<-0.8) {
//			// Top side looking at bottom of block
//			return loc.add(0.5,0,0.5);
//		} else if(vec.getX()>0.6 && vec.getZ()>0.6) {
//			// Bottom right side looking at top left of block
//			return loc.add(1.5,0,1.5);
//		} else if(vec.getX()>0.6 && vec.getZ()<-0.6) {
//			// Top right side looking at bottom left of block
//			return loc.add(1.5,0,-0.5);
//		} else if(vec.getX()<-0.6 && vec.getZ()>0.6) {
//			// Bottom left side looking at top right of block
//			return loc.add(-0.5,0,1.5);
//		} else if(vec.getX()<-0.6 && vec.getZ()<-0.6) {
//			// Top left side looking at bottom right of block
//			return loc.add(-0.5,0,-0.5);
//		}
//		return loc;
//		
//	}
	
	@EventHandler
	public void onAbilityUse(PlayerInteractEvent event) {
		if(event.getAction()==Action.RIGHT_CLICK_AIR || event.getAction()==Action.RIGHT_CLICK_BLOCK) {
			Player ply = event.getPlayer();
			ItemStack itemUsed = event.hasItem() ? event.getItem() : null;
			String itemName = itemUsed.getItemMeta().hasDisplayName()
				? itemUsed.getItemMeta().getDisplayName().replaceAll("[\\W][1-9r]", "").replace(' ', '_').toUpperCase()
				: "";
			itemName = itemName.replaceAll("[\\W][1-9r]", "");
			SkyblockItem item;
			try {
				item = SbItems.valueOf(itemName).getItem();
			} catch (IllegalArgumentException e) {
				return;
			}
			ItemAbility itemAbility = item.getAbility();
			if(event.getAction()==Action.RIGHT_CLICK_AIR || event.getAction()==Action.RIGHT_CLICK_BLOCK) {
				Location loc = ply.getEyeLocation();
				Location teleportLoc = ply.getLocation();
				BlockIterator blockIterator = new BlockIterator(loc, 0, itemAbility.getType().getTeleportBlocks());
//				boolean blockTp = false;
				while(blockIterator.hasNext()) {
					Block nextBlock = blockIterator.next();
					if(!nextBlock.isEmpty()) {
						teleportLoc = nextBlock.getLocation().subtract(loc.getDirection().multiply(new Vector(0,2,0)));
						ply.sendMessage("§cThere are blocks in the way!");
//						blockTp = true;
						break;
					}
					teleportLoc.add(loc.getDirection());
				}
//				if(!blockTp) {
//					teleportLoc.add(loc.getDirection().multiply(8));
//					ply.teleport(teleportLoc);
//				} else {
//					ply.teleport(teleportLoc);
//				}
				
				
				
				// Teleport player
				teleportLoc.setDirection(loc.getDirection());
				
				ply.teleport(ply.getLocation().add(ply.getLocation().getDirection().multiply(itemAbility.getType().getTeleportBlocks())));
				// Deal aoe damage
				if(itemAbility.getType().getAreaRange()>0) {
					
				}
				
			}	
		}
	}
	
}
