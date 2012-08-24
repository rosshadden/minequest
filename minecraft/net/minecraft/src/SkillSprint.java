package net.minecraft.src;

public class SkillSprint extends Skill{
	public SkillSprint(int id, String name){
		super(id, name, 200, 0);

		this.setIconCoord(10, 3);
	}

	public int onActivate(EntityPlayer player){
		System.out.println("LOG: SPRINT IS NOW ACTIVE!");

		player.capabilities.setWalkSpeed(0.12F);

		return 2;
	}

	public void onDeactivate(EntityPlayer player){
		System.out.println("LOG: SPRINT IS NOW INACTIVE!");

		//	WHY DOES THIS NOT RESET SPEED?!?!
		player.capabilities.setWalkSpeed(0.1F);
	}
}
