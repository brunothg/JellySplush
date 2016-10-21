package com.github.brunothg.jellysplush.data.powerup;

import com.github.brunothg.jellysplush.data.JellyFish;
import com.github.brunothg.jellysplush.data.PlayGround;

public interface Powerup
{

	/**
	 * Ob das Powerup entfernt werden muss (Spielfeld)
	 */
	public boolean isAlive(long elapsedTime);

	/**
	 * Powerup wurde aufgenommen
	 */
	public void fetched();

	public void manipulateOwnJellyFish(JellyFish fish);

	public void manipulateOtherJellyFishs(JellyFish... fishs);

	public void manipulatePlayGround(PlayGround playground);

	public int getImageId();

	public Powerup clone();
}
