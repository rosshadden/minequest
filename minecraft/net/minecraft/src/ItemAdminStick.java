package net.minecraft.src;

public class ItemAdminStick extends Item{
	public ItemAdminStick(int i){
		super(i);
        this.setTabToDisplayOn(CreativeTabs.tabCombat);
		maxStackSize = 4;
	}

	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player){
		player.addExperience(10);

		return item;
	}
}
