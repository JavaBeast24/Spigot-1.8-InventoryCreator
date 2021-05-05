package com.JavaBeast.generator;

import java.util.HashMap;

public class JBInventory {

	private final String name;
	private final String title;
	
	private final HashMap<Integer, JBItemStack> ItemStacks;
	
	public JBInventory(String name, String title, HashMap<Integer, JBItemStack> ItemStacks) {
		this.name = name;
		this.title = title;
		this.ItemStacks = ItemStacks;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTitle() {
		return title;
	}
	
	public HashMap<Integer, JBItemStack> getItemStakcks(){
		return ItemStacks;
	}
	
}
