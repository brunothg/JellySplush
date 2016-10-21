package com.github.brunothg.jellysplush.data.powerup.powerups;

import com.github.brunothg.jellysplush.data.JellyFish;
import com.github.brunothg.jellysplush.data.modification.mods.DrunkMod;
import com.github.brunothg.jellysplush.data.powerup.DefaultPowerup;
import com.github.brunothg.jellysplush.data.powerup.Powerup;

public class DrunkPowerup extends DefaultPowerup
{

	public DrunkPowerup()
	{

		super(6);
	}

	@Override
	public void manipulateOtherJellyFishs(JellyFish... fishs)
	{

		for (int i = 0; i < fishs.length; i++)
		{

			fishs[i].addModification(new DrunkMod());
		}
	}

	@Override
	public Powerup clone()
	{
		return new DrunkPowerup();
	}
}
