package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class GuiSkills extends GuiContainer{
	public GuiSkills(InventoryPlayer inventory, IInventory skillbar){
		super(new ContainerSkills(inventory, skillbar));
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	protected void drawGuiContainerForegroundLayer(){
		this.fontRenderer.drawString("Skills", 8, 22, 0x404040);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 0x404040);
	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3){
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		int texture = this.mc.renderEngine.getTexture("/img/gui/skills.png");
		this.mc.renderEngine.bindTexture(texture);

		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;

		//	Background, and inventory slots.
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
	}
}
