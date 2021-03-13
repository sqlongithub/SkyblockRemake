package me.sql.skyblockitems.items.customitems;

import me.sql.skyblockitems.items.SkyblockItem;

public enum SbItems {
	
	ASPECT_OF_THE_END(new AspectOfTheEnd().getItem());
	
	private SkyblockItem item;
	
	SbItems(SkyblockItem item) {
		this.item = item;
	}

	public SkyblockItem getItem() {
		return this.item;
	}
	
	
	
	
}
