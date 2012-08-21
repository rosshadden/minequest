package net.minecraft.src;

public class MinequestTraits{
	private EntityPlayer player;

	private int spent = 0;
	private int remaining = 0;

	private int strength = 0;
	private int speed = 0;

	public MinequestTraits(EntityPlayer thePlayer){
		player = thePlayer;
	}

	public int get(String trait){
		if(trait == "strength"){
			return strength;
		}

		if(trait == "speed"){
			return speed;
		}

		return -1;
	}

	public int add(String trait){
		return add(trait, 1);
	}

	public int add(String trait, int number){
		if(trait == "spent"){
			return spent;
		}

		if(trait == "remaining"){
			return remaining;
		}

		//	Increase spent points.
		spent += 1;

		if(trait == "strength"){
			return ++strength;
		}

		if(trait == "speed"){
			speed += 1;

			player.capabilities.setWalkSpeed(0.05F * speed);
			player.capabilities.setFlySpeed(0.01F * speed);

			return speed;
		}

		//	If shit didn't go down, undo that shit.
		spent -= 1;
		return -1;
	}
}
