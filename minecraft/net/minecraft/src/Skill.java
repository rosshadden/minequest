package net.minecraft.src;

public class Skill{
	private Item skill;

	public Skill(int id, String name){
		skill = new Item(id);

        skill.setTabToDisplayOn(CreativeTabs.tabCombat);
		skill.setMaxStackSize(1);
		skill.setIconCoord(10, 3);
		skill.setItemName("skill" + name);

		ModLoader.addName(skill, name);
	}
}
