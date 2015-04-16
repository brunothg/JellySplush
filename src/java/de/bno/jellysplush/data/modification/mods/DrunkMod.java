package de.bno.jellysplush.data.modification.mods;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import game.engine.time.TimeUtils;
import de.bno.jellysplush.data.Game;
import de.bno.jellysplush.data.JellyFish;
import de.bno.jellysplush.data.Position;
import de.bno.jellysplush.data.modification.DefaultModificator;

public class DrunkMod extends DefaultModificator
{

	private static final long maxTime = TimeUtils.NanosecondsOfSeconds(3);
	private static final long maxModTime = TimeUtils.NanosecondsOfMilliseconds(900);

	private long time = 0;
	private long modTime = 0;

	@Override
	public void modifiy(Game game, JellyFish fish, long elapsedTime)
	{

		modTime += elapsedTime;

		if (modTime >= maxModTime)
		{

			List<Position> pos = Arrays.asList(game.getPlayground().getEmptyFields());
			Collections.shuffle(pos);

			fish.setPosition(pos.get(0));

			modTime %= maxModTime;
		}
	}

	@Override
	public boolean isAlive(long elapsedTime)
	{

		time = Math.min(time + elapsedTime, maxTime);

		if (time >= maxTime)
		{

			return false;
		}

		return true;
	}

}
