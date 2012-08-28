package net.minecraft.src;

public class Skill extends Item{
	private EntityPlayer player;
	private int duration;

	private boolean isActive = false;
	private int activeFor = 0;

	public Skill(int id, String name, int rechargeTime, int castTime){
		super(id);

        this.setTabToDisplayOn(CreativeTabs.tabCombat);
		// this.setMaxStackSize(1);
		this.setItemName("skill" + name);

		ModLoader.addName(this, name);
	}

	public void activate(EntityPlayer thePlayer){
		player = thePlayer;
		duration = this.onActivate(player, player.experienceLevel);
		isActive = true;
	}

	public void deactivate(EntityPlayer thePlayer){
		isActive = false;
		this.onDeactivate(thePlayer);
	}

	public int onActivate(EntityPlayer player, int level){
		return -1;
	}

	public void onDeactivate(EntityPlayer player){}

	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player){
		activate(player);

		return item;
	}

	public void onUpdate(ItemStack item, World world, Entity entity, int num, boolean bool){
		if(isActive && entity instanceof EntityPlayer){
			if(activeFor + 1 == duration){
				deactivate(player);
			}

			activeFor = (activeFor + 1) % duration;
		}
	}
}
