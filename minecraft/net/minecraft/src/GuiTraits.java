package net.minecraft.src;

public class GuiTraits extends GuiScreen{
	private EntityPlayer player;

	public GuiTraits(EntityPlayer thePlayer){
		player = thePlayer;
	}

	public void initGui(){
		controlList.add(new GuiButton(0, width / 2 - 100, height / 6 + 168, "Done"));
	}

	public void drawScreen(int x, int y, float f){
		drawDefaultBackground();

		drawCenteredString(fontRenderer, "Traits", width / 2, 20, 0xffffff);

		drawCenteredString(fontRenderer, "Strength: " + player.traits.get("strength"), width / 4, 100, 0x990000);
		drawCenteredString(fontRenderer, "Speed: " + player.traits.get("speed"), 3 * width / 4, 100, 0x990000);

		super.drawScreen(x, y, f);
	}
}
