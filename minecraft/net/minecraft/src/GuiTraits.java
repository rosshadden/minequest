package net.minecraft.src;

public class GuiTraits extends GuiScreen{
	private EntityPlayer player;

	private int[] colors = new int[]{
		0xffffff, 0x000000, 0xff0000, 0x66cc00, 0x006400, 0x6a5acd
	};

	public GuiTraits(EntityPlayer thePlayer){
		player = thePlayer;
	}

	public void initGui(){
		controlList.add(new GuiButton(0, width / 2 - 100, height / 6 + 168, "Done"));

		controlList.add(new GuiButton(1, width / 3 + 70, 90, 20, 20, "+"));
		controlList.add(new GuiButton(2, width / 3 + 70, 110, 20, 20, "+"));
	}

	public void drawScreen(int x, int y, float f){
		drawDefaultBackground();

		drawCenteredString(fontRenderer, "Traits", width / 2, 20, colors[0]);

		drawStats();

		super.drawScreen(x, y, f);
	}

	public void drawStats(){
		drawString(fontRenderer, "Experience spent: " + player.traits.get("spent"), width / 3, 40, 0x6600cc);
		drawString(fontRenderer, "Experience remaining: " + player.traits.get("remaining"), width / 3, 60, 0x6600cc);

		drawString(fontRenderer, "Strength:", width / 3, 100, colors[3]);
		drawString(fontRenderer, "" + player.traits.get("strength"), width / 3 + 60, 100, colors[3]);
		drawString(fontRenderer, "Speed:", width / 3, 120, colors[3]);
		drawString(fontRenderer, "" + player.traits.get("speed"), width / 3 + 60, 120, colors[3]);
	}

	public void actionPerformed(GuiButton button){
		switch(button.id){
			//	"Done"
			case 0:
				this.mc.thePlayer.closeScreen();
				break;
			//	Strength++
			case 1:
				player.traits.add("strength");
				break;
			//	Speed++
			case 2:
				player.traits.add("speed");
				break;
		}

		if(button.id != 0){
			drawStats();
		}
	}

	protected void keyTyped(char something, int key){
		//	Close the screen if the user presses the inventory key.
		if (key == 1 || key == this.mc.gameSettings.keyBindInventory.keyCode){
			this.mc.thePlayer.closeScreen();
		}
	}
}
