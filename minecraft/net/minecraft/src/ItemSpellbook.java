package net.minecraft.src;

public class ItemSpellbook extends Item{
	public ItemSpellbook(int i){
		super(i);
        this.setTabToDisplayOn(CreativeTabs.tabCombat);
		maxStackSize = 1;
	}

	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player){
		double x = player.posX;
		double y = player.posY;
		double z = player.posZ;

		TileEntitySkills tileEntitySkills = (TileEntitySkills)world.getBlockTileEntity((int)x, (int)y, (int)z);

		ModLoader.openGUI(player, new GuiSkills(player.inventory, tileEntitySkills));

		return item;
	}
}
