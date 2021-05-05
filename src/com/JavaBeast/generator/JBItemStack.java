package com.JavaBeast.generator;

import org.bukkit.Material;

public class JBItemStack {

	
	private final String name;
	private final String displayName;
	private final String permission;
	private final String actionType;
	private final String actionObject;
	
	private final boolean moveable;
	
	private final int stackSize;
	
	private final Material material;
	
	public JBItemStack(String name, String displayName, String permission, boolean moveable, int stackSize,String actionType, String actionObject, Material mat) {
		this.name = name;
		this.displayName = displayName;
		this.permission = permission;
		this.actionType = actionType;
		this.actionObject = actionObject;
		this.moveable = moveable;
		
		if(stackSize > 64) {
			stackSize = 64;
		}else if(stackSize < 1) {
			stackSize = 1;
		}
		
		this.stackSize = stackSize;
		
		this.material = mat;
		
		Content.addStack(this);
	}
	
	public String getName() {
		return name;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public String getPermission() {
		return permission;
	}
	
	public String getActionType() {
		return actionType;
	}
	
	public String getActionObject() {
		return actionObject;
	}

	public boolean getMoveable() {
		return moveable;
	}
	
	public int getStackSize() {
		return stackSize;
	}
	
	public Material getMaterial() {
		return material;
	}
	
	public String toString() {
		return "[name='"+name+"', displayName='"+displayName+"', permission='"+permission+"', actionType='"+actionType+"', actionObject='"+actionObject+"', moveable='"+moveable+"\', stackSize=\'"+stackSize+"\', material='"+this.material+"']";
	}
	
}
