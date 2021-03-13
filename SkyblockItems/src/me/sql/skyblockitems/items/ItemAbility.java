package me.sql.skyblockitems.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;

public class ItemAbility {

	private String abilityName;
	private AbilityType abilityType;
	private int manaCost;
	private String abilityDescription;
	
	public ItemAbility(String name, AbilityType abilType, String description, int manaCost) {
		this.abilityName = name;
		this.abilityType = abilType;
		this.manaCost = manaCost;
		this.abilityDescription = description;
	}
	
	public String getName() {
		return this.abilityName;
	}
	
	public AbilityType getType() {
		return this.abilityType;
	}
	
	public int getManaCost() {
		return this.manaCost;
	}
	
	public Collection<String> getDescriptionCollection() {
		Bukkit.getLogger().info(this.abilityDescription.split("\n").toString());
		List<String> loreCollection = new ArrayList<String>();
		loreCollection.add("§r§6Item Ability: "+this.abilityName+" §e§lRIGHT CLICK");
		loreCollection.addAll(Arrays.asList(this.abilityDescription.split("\n")));
		return loreCollection;
	}
	
}
