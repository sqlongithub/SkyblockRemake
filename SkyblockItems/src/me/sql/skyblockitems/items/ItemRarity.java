package me.sql.skyblockitems.items;

public enum ItemRarity {
	COMMON("§f§lCOMMON"),
	UNCOMMON("§a§lUNCOMMON"),
	RARE("§9§lRARE"),
	EPIC("§5§lEPIC"),
	LEGENDARY("§6§lLEGENDARY"),
	MYTHIC("§d§l§kA §r§d§lMYTHIC");
	
	private String name;
	
	private ItemRarity(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	
}
