package com.github.brunothg.jellysplush.data.powerup.powerups;

import com.github.brunothg.jellysplush.data.JellyFish;
import com.github.brunothg.jellysplush.data.modification.mods.InvincibleMod;
import com.github.brunothg.jellysplush.data.powerup.DefaultPowerup;
import com.github.brunothg.jellysplush.data.powerup.Powerup;

public class ShieldPowerup extends DefaultPowerup
{

	public ShieldPowerup()
	{

		super(7);
	}

	@Override
	public void manipulateOwnJellyFish(JellyFish fish)
	{

		fish.addModification(new InvincibleMod());
	}

	@Override
	public Powerup clone()
	{
		return new ShieldPowerup();
	}
}
