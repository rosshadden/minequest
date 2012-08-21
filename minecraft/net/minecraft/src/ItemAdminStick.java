package net.minecraft.src;

public class ItemAdminStick extends Item{
	public ItemAdminStick(int i){
		super(i);
		maxStackSize = 4;
	}

	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player){
		player.traits.add("speed", 1);

		return item;
	}

	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player){
		player.addExperience(10);

		return true;
	}
}
