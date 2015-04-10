package de.bno.jellysplush.data.field;

import de.bno.jellysplush.data.JellyFish;
import de.bno.jellysplush.data.PlayGround;
import de.bno.jellysplush.data.powerup.Powerup;

public class PowerupField extends Field implements Powerup
{

	private Powerup powerup;

	public PowerupField(Powerup powerup)
	{

		super(FieldType.POWERUP);
		this.powerup = powerup;
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
	public int getId()
	{

		return powerup.getId();
	}

}
