package me.sql.skyblockitems.items;

public enum ItemType {
	
	SWORD("SWORD"),
	BOOTS("BOOTS"),
	LEGGINGS("LEGGINGS"),
	CHESTPLATE("CHESTPLATE"),
	HELMET("HELMET"),
	ITEM("ITEM"),
	REWARD_ITEM("REWARD ITEM");
	
	private String name;
	
	private ItemType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
}
