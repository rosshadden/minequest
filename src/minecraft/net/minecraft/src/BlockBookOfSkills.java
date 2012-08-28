package net.minecraft.src;

import java.util.Random;

public class BlockBookOfSkills extends Block{
	public BlockBookOfSkills(int i){
		super(i, Material.wood);
        this.blockIndexInTexture = 48;
        this.setLightOpacity(0);
        this.setCreativeTab(CreativeTabs.tabDeco);
	}

    public int idDropped(int i, Random random){
        return mod_Minequest.bookOfSkills.blockID;
    }

	public int getBlockTextureFromSide(int i){
		return i * 2 + 16;
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9){
		TileEntitySkills tileEntity = (TileEntitySkills)world.getBlockTileEntity(x, y, z);

		ModLoader.openGUI(player, new GuiSkills(player.inventory, tileEntity));

		return true;
	}
}
