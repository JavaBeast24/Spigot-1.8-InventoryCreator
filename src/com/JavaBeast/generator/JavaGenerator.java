package com.JavaBeast.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;

public class JavaGenerator {

	public static void generateFile() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("package myPackage;\n"
				+ "\n"
				+ "import java.util.ArrayList;\r\n"
				+ "import java.util.HashMap;\r\n"
				+ "import java.util.List;\r\n"
				+ "\r\n"
				+ "import org.bukkit.Bukkit;\r\n"
				+ "import org.bukkit.Material;\r\n"
				+ "import org.bukkit.entity.Player;\r\n"
				+ "import org.bukkit.event.EventHandler;\r\n"
				+ "import org.bukkit.event.Listener;\r\n"
				+ "import org.bukkit.event.inventory.InventoryClickEvent;\r\n"
				+ "import org.bukkit.inventory.Inventory;\r\n"
				+ "import org.bukkit.inventory.ItemStack;\r\n"
				+ "import org.bukkit.inventory.meta.ItemMeta;"
				+ "\n");
		
		builder.append("public class JBInventorys implements Listener {\n");
		builder.append("	\n");
		builder.append("	\n");
		
		builder.append("	private static HashMap<String, String[]> actions = new HashMap<String, String[]>() {{\n");
					
		for(JBItemStack itemStack:Content.getStacks()) {
			builder.append("		put(\""+itemStack.getName()+"\", new String[] {\""+itemStack.getActionType()+"\", \""+itemStack.getActionObject()+"\", \""+itemStack.getPermission()+"\", \""+itemStack.getMoveable()+"\"});\n");
		}
		
		builder.append("	}};\n");
		
		builder.append("	\n");
		builder.append("	\n");
		
		for(JBItemStack is:Content.getStacks()) {
			builder.append("	private static ItemStack createItemStack_"+is.getName()+"() {\n");
			builder.append("		ItemStack itemStack = new ItemStack(Material."+is.getMaterial()+");\n");
			builder.append("		\n");
			builder.append("		ItemMeta itemMeta = itemStack.getItemMeta();\n");
			builder.append("		itemMeta.setDisplayName(\""+is.getName()+"\");\n");
			builder.append("		List<String> lore = new ArrayList<>();\n");
			builder.append("		lore.add(\""+is.getName()+"\");\n");
			builder.append("		\n");
			builder.append("		itemMeta.setLore(lore);\n");
			builder.append("		itemStack.setItemMeta(itemMeta);\n");
			builder.append("		\n");
			builder.append("		itemStack.setAmount("+is.getStackSize()+");\n");
			builder.append("		return itemStack;\n");
			builder.append("	}\n");
		}
		
		
		builder.append("	\n");
		builder.append("	\n");
		
		
		for(JBInventory inv:Content.getInventorys()) {
			builder.append("	public static Inventory createInventory_"+inv.getName()+"() {\n");
			int size = 9;
			
			for(int i:inv.getItemStakcks().keySet()) {
				if(i > size-1) {
					size = i/9;
					
					if(i%9 != 0) {
						size += 1;
					}
					
					size *= 9;
				}
			}
			builder.append("		Inventory inventory = Bukkit.createInventory(null, "+size+", \""+inv.getTitle()+"\");\n");
			builder.append("		\n");
			
			for(int i:inv.getItemStakcks().keySet()) {
				JBItemStack stack = inv.getItemStakcks().get(i);
				builder.append("		inventory.setItem("+i+", createItemStack_"+stack.getName()+"());\n");
			}
			
			builder.append("		return inventory;\n");
			
			builder.append("	}\n");
			
		}
		
		builder.append("	\n");
		builder.append("	\n");

		builder.append("	@EventHandler\n");
		builder.append("	public void onClick(InventoryClickEvent event) {\n");
		builder.append("		if(event.getCurrentItem() == null)\n");
		builder.append("			return;\n");
		builder.append("		String title = event.getClickedInventory().getTitle();\n");
		builder.append("		\n");
		
		for(JBInventory inventory:Content.getInventorys()) {
			builder.append("		if(title.equals(\""+inventory.getTitle()+"\")) {\n");
			builder.append("			if(event.getCurrentItem().equals(createInventory_"+inventory.getName()+"().getItem(event.getSlot()))) {\n");
			builder.append("				String name = event.getCurrentItem().getItemMeta().getLore().get(0);\n");
			builder.append("				if(actions.get(name)[3].equals(\"true\")) event.setCancelled(true);\n");
			builder.append("				if(event.getWhoClicked().hasPermission(actions.get(name)[2])) {\n");
			builder.append("					Action action = new Action(actions.get(name)[0], actions.get(name)[1]);\n");
			builder.append("					action.executeAction(event.getInventory(), event.getCurrentItem(), (Player) event.getWhoClicked());\n");
			builder.append("				}\n");
			builder.append("			}\n");
			builder.append("		}\n");
		}
		builder.append("	}\n");
		
		builder.append("	\n");
		builder.append("	\n");
		builder.append("}\n");
		builder.append("class Action {\n");
		builder.append("	\n");
		builder.append("	private final String actionType;\n");
		builder.append("	private final String actionObject;\n");
		builder.append("	\n");
		builder.append("	public Action (String actionType, String actionObject) {\n");
		builder.append("		this.actionType = actionType;\n");
		builder.append("		this.actionObject = actionObject;\n");
		builder.append("	}\n");
		builder.append("	\n");
		builder.append("	public void executeAction(Inventory inventory, ItemStack clickedItem, Player player) {\n");
		builder.append("		if(actionType.equals(\"CLOSE_INVENTORY\")) {\n");
		builder.append("			player.closeInventory();\n");
		builder.append("		} else if(actionType.equals(\"OPEN_INVENTORY\")) {\n");
		builder.append("			player.closeInventory();\n");
		builder.append("			switch(actionObject) {\n");
		builder.append("			\n");
		
		for(JBInventory inv:Content.getInventorys()) {
			builder.append("			case \""+inv.getName()+"\":\n");
			builder.append("				player.openInventory(JBInventorys.createInventory_"+inv.getName()+"());\n");
			builder.append("				break;\n");
		}
		
		builder.append("			}\n");
		builder.append("		} else if(actionType.equals(\"TRIGGER_EVENT\")) {\n");
		builder.append("			switch(actionObject) {\n");
		
		for(String event:Content.getEvents()) {
			builder.append("			case \""+event+"\":\n");
			builder.append("				ActionObject.execute_"+event+"(inventory, clickedItem, player);\n");
			builder.append("				break;\n");
		}
		
		builder.append("			}\n");
		builder.append("		}\n");
		builder.append("	}\n");
		builder.append("	\n");
		builder.append("}\n");
		builder.append("class ActionObject {\n");
		builder.append("	\n");
		
		for(String event:Content.getEvents()) {
			builder.append("	public static void execute_"+event+"(Inventory inventory, ItemStack clickedItem, Player player) {\n");
			builder.append("		// TODO: write your code to execute here\n");
			builder.append("	}\n");
		}
		
		builder.append("	\n");
		builder.append("}\n");
		
		try {
			
			CodeSource codeSource = GeneratorFrame.class.getProtectionDomain().getCodeSource();
			File jarFile = new File(codeSource.getLocation().toURI().getPath());
			String jarDir = jarFile.getParentFile().getPath();
			
			File file = new File(jarDir+"/JBInventorys.java");
			
			System.out.println(file.getPath());
			
			if(!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter writer = new FileWriter(file);
			writer.write(builder.toString());
			writer.flush();
			writer.close();
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
