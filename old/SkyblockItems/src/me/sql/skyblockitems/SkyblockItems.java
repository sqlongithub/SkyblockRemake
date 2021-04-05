package me.sql.skyblockitems;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.meta.ItemMeta;

import me.sql.skyblockitems.events.OnAbilityUse;
import me.sql.skyblockitems.events.OnItemDurabilityLoss;
import me.sql.skyblockitems.items.SkyblockItem;
import me.sql.skyblockitems.items.customitems.SbItems;

public class SkyblockItems extends JavaPlugin {

	
	
	private boolean aliasCheck(String s) {
		return s.equalsIgnoreCase("sbi") || s.equalsIgnoreCase("sbitems") || s.equalsIgnoreCase("skyblockitems");
	}
	
	public void giveItem(String itemName, Player ply) {
		ItemStack item;
		SkyblockItem sbItem = SbItems.ASPECT_OF_THE_END.getItem();
		try {
			sbItem = SbItems.valueOf(SbItems.class, itemName.toUpperCase()).getItem();
		} catch(IllegalArgumentException e) {
			Bukkit.getLogger().info(e.getMessage());
		}
		item = new ItemStack(sbItem.getMaterial(), 1);
		ItemMeta itemMeta = item.getItemMeta();
		
		itemMeta.setLore(sbItem.getLore());
		itemMeta.setDisplayName(sbItem.getName());
		item.setItemMeta(itemMeta);
		
		ply.getInventory().addItem(item);
	}
	
	@Override
	public void onEnable() {
		
		Bukkit.getServer().getPluginManager().registerEvents(new OnAbilityUse(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OnItemDurabilityLoss(), this);
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player ply = null;
		if(sender instanceof Player) {
			ply = (Player) sender;
		} 
		
		if(aliasCheck(label)) {
			if(args.length>0) {
			
			}
		} else if(label.equalsIgnoreCase("sbitem")) {
			if(args.length>0 && ply!=null) {
				giveItem(args[0],ply);
			}
			return true;
		}
		return false;
	}
	
}
