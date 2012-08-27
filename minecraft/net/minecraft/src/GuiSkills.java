package net.minecraft.src;

public class GuiSkills extends GuiScreen{
	private EntityPlayer player;
	private Container skills;

	public GuiSkills(EntityPlayer thePlayer){
		player = thePlayer;
	}

	public boolean doesGuiPauseGame(){
		return false;
	}

	public void initGui(){
		controlList.add(new GuiButton(0, width / 2 - 100, height / 6 + 168, "Exit"));
	}

	public void drawScreen(int x, int y, float f){
		drawDefaultBackground();

		drawCenteredString(fontRenderer, "Skills", width / 2, 40, 0xffffff);

		super.drawScreen(x, y, f);
	}

	public void actionPerformed(GuiButton button){
		switch(button.id){
			//	Exit.
			case 0:
				player.closeScreen();
				break;
		}
	}

	protected void keyTyped(char something, int key){
		//	Close the screen if the user presses the inventory key.
		if (key == 1 || key == this.mc.gameSettings.keyBindInventory.keyCode){
			player.closeScreen();
		}
	}
}
