package net.minecraft.src;

public class ItemAdminStick extends Item{
	public ItemAdminStick(int i){
		super(i);
        this.setTabToDisplayOn(CreativeTabs.tabCombat);
		maxStackSize = 4;
	}

	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player){
		player.addExperience(10);

		return true;
	}
}
