package net.minecraft.src;

public class Skill extends Item{
	private EntityPlayer player;
	private boolean isActive = false;
	private int duration = 100;
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
		isActive = true;
		this.onActivate(player);
	}

	public void deactivate(EntityPlayer thePlayer){
		isActive = false;
		this.onDeactivate(thePlayer);
	}

	public int onActivate(EntityPlayer player){
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
