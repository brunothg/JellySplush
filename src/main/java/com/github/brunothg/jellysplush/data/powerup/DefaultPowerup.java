package com.github.brunothg.jellysplush.data.powerup;

import com.github.brunothg.game.engine.time.TimeUtils;
import com.github.brunothg.jellysplush.Constants;
import com.github.brunothg.jellysplush.data.JellyFish;
import com.github.brunothg.jellysplush.data.PlayGround;

public abstract class DefaultPowerup implements Powerup
{

	private long time = 0;

	private long lifetimeOnBoard;

	private boolean alive = true;
	boolean fetched = false;

	private int id;

	public DefaultPowerup(int id)
	{

		this(id, TimeUtils.NanosecondsOfSeconds(Constants.POWERUP_LIFETIME_DEFAULT));
	}

	public DefaultPowerup(int id, long lifeTime)
	{
		this.id = id;
		this.lifetimeOnBoard = lifeTime;
		this.alive = true;
	}

	@Override
	public boolean isAlive(long elapsedTime)
	{

		if (!alive)
		{
			return alive;
		}

		//GGF. weitere berechnungen, die für ein ableben sorgen könnten
		if (fetched)
		{

			alive = false;
		}

		time += elapsedTime;

		if (time > lifetimeOnBoard)
		{

			alive = false;
		}

		return alive;
	}

	public void die()
	{

		alive = false;
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
	public int getImageId()
	{

		return id;
	}

	public long getLifetimeOnBoard()
	{
		return lifetimeOnBoard;
	}

	public void setLifetimeOnBoard(long lifetimeOnBoard)
	{
		this.lifetimeOnBoard = Math.max(0, lifetimeOnBoard);
	}

	@Override
	public abstract Powerup clone();
}
