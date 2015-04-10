package de.bno.jellysplush.data.powerup;

import game.engine.time.TimeUtils;
import de.bno.jellysplush.data.JellyFish;
import de.bno.jellysplush.data.PlayGround;
import de.bno.jellysplush.data.field.Field;
import de.bno.jellysplush.data.field.FieldType;

public abstract class DefaultPowerup extends Field implements Powerup
{

	private long time = 0;

	private long lifetimeOnBoard = TimeUtils.NanosecondsOfSeconds(5);

	private boolean alive;
	boolean fetched = false;

	public DefaultPowerup()
	{

		super(FieldType.POWERUP);
	}

	@Override
	public boolean isAlive(long elapsedTime)
	{

		if (fetched)
		{

			alive = false;
			return false;
		}

		time += elapsedTime;

		if (time > lifetimeOnBoard)
		{

			alive = false;
		}
		else
		{

			alive = true;
		}

		return alive;
	}

	@Override
	public void fetched()
	{

		time = 0;
		fetched = true;
	}

	@Override
	public void manipulateOwnJellyFish(JellyFish fish)
	{
	}

	@Override
	public void manipulateOtherJellyFishs(JellyFish... fishs)
	{
	}

	@Override
	public void manipulatePlayGround(PlayGround playground)
	{
	}

	@Override
	public int getId()
	{

		return getImage();
	}

	public long getLifetimeOnBoard()
	{
		return lifetimeOnBoard;
	}

	public void setLifetimeOnBoard(long lifetimeOnBoard)
	{
		this.lifetimeOnBoard = Math.max(0, lifetimeOnBoard);
	}

}
