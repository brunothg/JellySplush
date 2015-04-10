package de.bno.jellysplush.data.powerup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import de.bno.jellysplush.Settings;
import de.bno.jellysplush.data.powerup.powerups.SlowPowerup;
import de.bno.jellysplush.data.powerup.powerups.SpeedPowerup;
import de.bno.jellysplush.data.powerup.powerups.TransporterPowerup;
import de.bno.jellysplush.data.powerup.powerups.WallPowerup;

public class PowerupUtils {

	private static final List<Powerup> powerups = new ArrayList<Powerup>(2);

	static {

		powerups.add(new SpeedPowerup());
		powerups.add(new SlowPowerup());
		powerups.add(new TransporterPowerup());
		powerups.add(new WallPowerup());

		int[] usedPowerups = Settings.getIntArray(Settings.KEY_USED_POWERUPS,
				null);

		if (usedPowerups != null && usedPowerups.length > 0) {

			Iterator<Powerup> iterator = powerups.iterator();
			while (iterator.hasNext()) {

				Powerup next = iterator.next();
				if (!contains(usedPowerups, next.getId())) {

					iterator.remove();
					System.out.println("Remove powerup " + next.getId());
				}
			}
		}

	}

	private static boolean contains(int[] usedPowerups, int id) {

		for (int i = 0; i < usedPowerups.length; i++) {

			if (usedPowerups[i] == id) {

				return true;
			}
		}

		return false;
	}

	public static List<Powerup> getPowerups() {

		List<Powerup> ret = new ArrayList<Powerup>(powerups.size());
		ret.addAll(powerups);

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
