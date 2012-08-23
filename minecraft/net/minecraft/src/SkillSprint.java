package net.minecraft.src;

public class SkillSprint extends Skill{
	private EntityPlayer player;
	private boolean isActive = false;
	private int duration = 100;
	private int activeFor = 0;

	public SkillSprint(int id, String name){
		super(id, name);

		this.setIconCoord(10, 3);
	}

	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer thePlayer){
		player = thePlayer;

		isActive = true;

		// Class c = ModLoader.getMinecraftInstance().thePlayer.capabilities.getClass();
		// Field f = c.getDeclaredField("walkSpeed");
		// f.setAccessible(true);
		// f.set(ModLoader.getMinecraftInstance().thePlayer.capabilities, 1.0F);

		return item;
	}

	public void onUpdate(ItemStack item, World world, Entity entity, int num, boolean bool){
		if(isActive && entity instanceof EntityPlayer){
			if(activeFor < duration){
				player.capabilities.setWalkSpeed(0.12F);
			}else{
				//	WHY DOES THIS NOT RESET SPEED?!?!
				player.capabilities.setWalkSpeed(0.1F);
				isActive = false;
			}

			activeFor = (activeFor + 1) % duration;
		}
	}
}
