package net.minecraft.src;

public class mod_Minequest extends BaseMod{
	public static final Item adminStick = (new ItemAdminStick(512)).setIconCoord(5, 3).setItemName("adminStick");
	public static final Block bookOfTraits = (new BlockBookOfTraits(160)).setBlockName("bookOfTraits").setHardness(3F).setResistance(4F).setLightValue(1F);
	public static final Block bookOfSkills = (new BlockBookOfSkills(161)).setBlockName("bookOfSkills").setHardness(3F).setResistance(4F).setLightValue(1F);

	public static final Skill sprint = new SkillSprint(513, "sprint");

	public void load(){
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
	}

	public String getVersion(){
		return "1.3.2";
	}
}
