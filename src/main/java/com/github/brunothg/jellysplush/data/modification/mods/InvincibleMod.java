package com.github.brunothg.jellysplush.data.modification.mods;

import com.github.brunothg.game.engine.time.TimeUtils;
import com.github.brunothg.jellysplush.data.Game;
import com.github.brunothg.jellysplush.data.JellyFish;
import com.github.brunothg.jellysplush.data.modification.DefaultModificator;

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
