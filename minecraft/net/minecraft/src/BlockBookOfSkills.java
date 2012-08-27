package net.minecraft.src;

public class BlockBookOfSkills extends Block{
	public BlockBookOfSkills(int i){
		super(i, Material.wood);
        this.blockIndexInTexture = 48;
        this.setLightOpacity(0);
        this.setCreativeTab(CreativeTabs.tabDeco);
	}

	public int getBlockTextureFromSide(int i){
		return i * 2 + 16;
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9){
		ModLoader.openGUI(player, new GuiSkills(player));

		return true;
	}
}
