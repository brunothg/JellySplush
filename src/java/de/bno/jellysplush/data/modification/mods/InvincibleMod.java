package de.bno.jellysplush.data.modification.mods;

import game.engine.time.TimeUtils;
import de.bno.jellysplush.data.Game;
import de.bno.jellysplush.data.JellyFish;
import de.bno.jellysplush.data.modification.DefaultModificator;

public class InvincibleMod extends DefaultModificator
{

	private static final long maxTime = TimeUtils.NanosecondsOfSeconds(4);

	private long time = 0;
	private boolean alive = true;

	@Override
	public void modifiy(Game game, JellyFish fish, long elapsedTime)
	{

		if (!alive)
		{
			return;
		}

		time = Math.min(time + elapsedTime, maxTime);

		if (time >= maxTime)
		{

			alive = false;
		}

		fish.setInvincible(alive);
	}

	@Override
	public boolean isAlive(long elapsedTime)
	{

		return alive;
	}

}
