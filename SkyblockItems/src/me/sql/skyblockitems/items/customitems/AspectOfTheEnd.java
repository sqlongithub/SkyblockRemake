package me.sql.skyblockitems.items.customitems;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import me.sql.skyblockitems.SpecialSymbols;
import me.sql.skyblockitems.items.AbilityType;
import me.sql.skyblockitems.items.ItemAbility;
import me.sql.skyblockitems.items.ItemRarity;
import me.sql.skyblockitems.items.SkyblockItem;

public class AspectOfTheEnd extends SkyblockItem {
	
	static List<String> itemLore = new ArrayList<String>();
	static ItemAbility itemAbility = new ItemAbility("Instant Transmission", AbilityType.AOTE_TELEPROT, "§7Teleport §a8 blocks §7ahead of\n§7you and gain §a+50 §f"+SpecialSymbols.speedSymbol+" Speed\n§7for §a3 seconds§7.", 50);
	
	public AspectOfTheEnd() {
		super("§r§9Aspect of the End", Material.DIAMOND_SWORD, itemAbility, ItemRarity.RARE, 
				/*Damage*/ 100, 
				/*Strength*/ 100,
				/*Intelligence*/ 0,
				/*Ferocity*/ 0,
				/*AttackSpeed*/ 0,
				/*CritChance*/ 0,
				/*CritDamage*/ 50,
				/*GearScore*/ 0,
				 itemLore);
		
		itemLore.add("§7Damage: §c+100");
		itemLore.add("§7Strength: §c+100");
		itemLore.add("");
		itemLore.addAll(itemAbility.getDescriptionCollection());
		itemLore.add("");
		itemLore.add("§r§8This item can be reforged!");
		itemLore.add("§r§9§lRARE SWORD");
		
		
	}
	
	public SkyblockItem getItem() {
		return new SkyblockItem("§r§9Aspect of the End", Material.DIAMOND_SWORD, itemAbility, ItemRarity.RARE, 
				/*Damage*/ 100, 
				/*Strength*/ 100,
				/*Intelligence*/ 0,
				/*Ferocity*/ 0,
				/*AttackSpeed*/ 0,
				/*CritChance*/ 0,
				/*CritDamage*/ 50,
				/*GearScore*/ 0,
				 itemLore);
	}

	
	
}
