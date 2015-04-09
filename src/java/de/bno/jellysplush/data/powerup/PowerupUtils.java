package de.bno.jellysplush.data.powerup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PowerupUtils {

	private static final List<Powerup> powerups = new ArrayList<Powerup>();

	static {

		powerups.add(new SpeedPowerup());
		powerups.add(new SlowPowerup());
	}

	public static List<Powerup> getPowerups() {

		List<Powerup> ret = new ArrayList<Powerup>(powerups.size());
		Collections.copy(ret, powerups);

		return ret;
	}

	public static Powerup[] getRandomPowerups(int anz) {

		Collections.shuffle(powerups);

		Powerup[] ret = new Powerup[anz];

		int index = 0;

		while (index < ret.length) {
			for (Powerup power : powerups) {

				if (index >= ret.length) {
					break;
				}

				ret[index] = power;
				index++;
			}
		}

		return ret;
	}
}
