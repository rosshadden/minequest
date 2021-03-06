package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class mod_Minequest extends BaseMod{
	private EntityPlayer player = ModLoader.getMinecraftInstance().thePlayer;

	private GuiSkillbar skillbar = new GuiSkillbar(player);

	public static final Item spellbook = (new ItemSpellbook(512)).setIconCoord(11, 3).setItemName("spellbook");
	public static final Item adminStick = (new ItemAdminStick(513)).setIconCoord(5, 3).setItemName("adminStick");
	public static final Block bookOfTraits = (new BlockBookOfTraits(160)).setBlockName("bookOfTraits").setHardness(3F);
	public static final Block bookOfSkills = (new BlockBookOfSkills(161)).setBlockName("bookOfSkills").setHardness(3.5F).setRequiresSelfNotify();

	public static final Skill sprint = new SkillSprint(1024, "sprint");

	public void load(){
		ModLoader.registerTileEntity(net.minecraft.src.TileEntitySkills.class, "Skills");

		ModLoader.addName(spellbook, "Spellbook");
		ModLoader.addRecipe(
			new ItemStack(spellbook, 1),

			new Object[]{
				"@@@",
				"@#@",
				"@@@",

				'#', Item.stick,
				'@', Block.dirt
			}
		);

		ModLoader.addName(adminStick, "Admin Stick");
		ModLoader.addRecipe(
			new ItemStack(adminStick, 1),

			new Object[]{
				"  #",
				" # ",
				"#  ",

				'#', Item.stick
			}
		);

		ModLoader.registerBlock(bookOfTraits);
		ModLoader.addName(bookOfTraits, "Book o' Traits");
		ModLoader.addRecipe(
			new ItemStack(bookOfTraits, 1),

			new Object[]{
				"@",
				"#",

				'@', Item.pickaxeWood,
				//	This will be Item.book or Item.enchantmentTable.
				'#', Block.dirt
			}
		);

		ModLoader.registerBlock(bookOfSkills);
		ModLoader.addName(bookOfSkills, "Book o' Skills");
		ModLoader.addRecipe(
			new ItemStack(bookOfSkills, 1),

			new Object[]{
				"@",
				"#",

				'@', Item.swordWood,
				//	This will be Item.book or Item.enchantmentTable.
				'#', Block.dirt
			}
		);

		ModLoader.setInGameHook(this, true, false);
	}

	public boolean onTickInGame(float time, Minecraft game){
		skillbar.draw();

		return true;
	}

	public String getVersion(){
		return "1.3.2";
	}
}
