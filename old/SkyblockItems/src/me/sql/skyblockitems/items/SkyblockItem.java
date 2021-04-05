package me.sql.skyblockitems.items;

import java.util.List;

import org.bukkit.Material;

public class SkyblockItem {
	
	private String name;
	private ItemAbility ability;
	private ItemRarity rarity;
	private List<String> lore;
	private int itemDamage;
	private int itemStrength;
	private int itemIntelligence;
	private int itemFerocity;
	private int itemAttackSpeed;
	private int itemCritChance;
	private int itemCritDamage;
	private int itemGearScore;
	private Material mat;
	
	public SkyblockItem(String name, Material mat, ItemAbility ability, ItemRarity rarity, int itemDamage, int itemStrength, int itemIntelligence, int itemFerocity, int itemAttackSpeed, int itemCritChance, int itemCritDamage, int itemGearScore, List<String> lore) {
		this.name = name;
		this.mat = mat;
		this.ability = ability;
		this.rarity = rarity;
		this.lore = lore;
		this.itemDamage = itemDamage;
		this.itemStrength = itemStrength;
		this.itemIntelligence = itemIntelligence;
		this.itemFerocity = itemFerocity;
		this.itemAttackSpeed = itemAttackSpeed;
		this.itemCritChance = itemCritChance;
		this.itemCritDamage = itemCritDamage;
		this.itemGearScore = itemGearScore;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Material getMaterial() {
		return this.mat;
	}
	
	public ItemAbility getAbility() {
		return this.ability;
	}
	
	public ItemRarity getRarity() {
		return this.rarity;
	}
	
	public List<String> getLore() {
		return this.lore;
	}

	public int getItemDamage() {
		return itemDamage;
	}

	public int getItemStrength() {
		return itemStrength;
	}
	
	public int getItemIntelligence() {
		return itemIntelligence;
	}
	
	public int getItemFerocity() {
		return itemFerocity;
	}
	
	public int getItemAttackSpeed() {
		return itemAttackSpeed;
	}
	
	public int getItemCritChance() {
		return itemCritChance;
	}
	
	public int getItemCritDamage() {
		return itemCritDamage;
	}
	
	public int getItemGearScore() {
		return itemGearScore;
	}
	
	
}
