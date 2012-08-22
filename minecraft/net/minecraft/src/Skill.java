package net.minecraft.src;

public class Skill extends Item{
	public Skill(int id, String name){
		super(id);

        this.setTabToDisplayOn(CreativeTabs.tabCombat);
		this.setMaxStackSize(1);
		this.setIconCoord(10, 3);
		this.setItemName("skill" + name);

		ModLoader.addName(this, name);
	}
}
