package com.JavaBeast.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Content {
	
	private static List<JBItemStack> stacks = new ArrayList<>();
	private static HashMap<String, JBItemStack> itemStacks = new HashMap<>();

	
	public static List<JBItemStack> getStacks(){
		return stacks;
	}
	
	public static JBItemStack getStack(String name) {
		return itemStacks.get(name);
	}
	
	public static void addStack(JBItemStack stack) {
		stacks.remove(stack);
		stacks.add(stack);
		itemStacks.remove(stack.getName());
		itemStacks.put(stack.getName(), stack);
	}
	
	
	private static List<JBInventory> inventorys = new ArrayList<>();
	private static HashMap<String, JBInventory> _inventorys = new HashMap<>();
	
	public static List<JBInventory> getInventorys(){
		return inventorys;
	}
	
	public static JBInventory getInventory(String name) {
		return _inventorys.get(name);
	}
	
	public static void addInventory(JBInventory inventory) {
	
		for(JBInventory inv:inventorys) {
			if(inv.getName().equals(inventory.getName())) {
				inventorys.remove(inv);
				_inventorys.remove(inv.getName());
				break;
			}
		}
		
		inventorys.add(inventory);
		_inventorys.put(inventory.getName(), inventory);
	}
	
	
	
	private static List<String> EVENTS = new ArrayList<>();
	
	public static List<String> getEvents(){
		return EVENTS;
	}
	
	public static void addEvent(String event) {
		EVENTS.add(event);
	}
	
}
