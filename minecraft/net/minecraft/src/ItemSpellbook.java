package net.minecraft.src;

public class ItemSpellbook extends Item{
	public ItemSpellbook(int i){
		super(i);
        this.setTabToDisplayOn(CreativeTabs.tabCombat);
		maxStackSize = 1;
	}

	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player){
		ModLoader.openGUI(player, new GuiSkills());

		return item;
	}
}
