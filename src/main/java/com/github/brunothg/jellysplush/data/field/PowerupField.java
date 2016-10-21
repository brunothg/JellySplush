package com.github.brunothg.jellysplush.data.field;

import com.github.brunothg.jellysplush.data.JellyFish;
import com.github.brunothg.jellysplush.data.PlayGround;
import com.github.brunothg.jellysplush.data.powerup.Powerup;

public class PowerupField extends Field implements Powerup
{

	private Powerup powerup;

	public PowerupField(Powerup powerup)
	{

		super(FieldType.POWERUP);
		this.powerup = powerup;
		setImage(this.powerup.getImageId());
	}

	public Powerup getPowerup()
	{
		return powerup;
	}

	public void setPowerup(Powerup powerup)
	{
		this.powerup = powerup;
	}

	@Override
	public boolean isAlive(long elapsedTime)
	{

		return powerup.isAlive(elapsedTime);
	}

	@Override
	public void fetched()
	{

		powerup.fetched();
	}

	@Override
	public void manipulateOwnJellyFish(JellyFish fish)
	{

		powerup.manipulateOwnJellyFish(fish);
	}

	@Override
	public void manipulateOtherJellyFishs(JellyFish... fishs)
	{

		powerup.manipulateOtherJellyFishs(fishs);
	}

	@Override
	public void manipulatePlayGround(PlayGround playground)
	{

		powerup.manipulatePlayGround(playground);
	}

	@Override
	public int getImageId()
	{

		return powerup.getImageId();
	}

	@Override
	public Powerup clone()
	{

		return powerup.clone();
	}

	@Override
	public String toString()
	{
		return "PowerupField [powerup=" + powerup + ", getImageId()=" + getImageId() + "]";
	}
}
