package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class GuiSkillbar extends Gui{
	private EntityPlayer player;
	private Container skills;
	private Minecraft game = ModLoader.getMinecraftInstance();

	public GuiSkillbar(EntityPlayer thePlayer){
		player = thePlayer;
	}

	public void draw(){
		ScaledResolution screen = new ScaledResolution(game.gameSettings, game.displayWidth, game.displayHeight);
		int width = screen.getScaledWidth();
		int height = screen.getScaledHeight();
		int barWidth = 182;
		int barHeight = 22;

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, game.renderEngine.getTexture("/gui/gui.png"));

		drawTexturedModalRect(width / 2 - barWidth / 2, 0, 0, 0, barWidth, barHeight);
	}
}
