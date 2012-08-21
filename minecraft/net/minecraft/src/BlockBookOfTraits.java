package net.minecraft.src;

public class BlockBookOfTraits extends Block{
	public BlockBookOfTraits(int i){
		super(i, Material.wood);
        this.blockIndexInTexture = 26;
	}

	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player){
		ModLoader.openGUI(player, new GuiTraits(player));

		return true;
	}
}
