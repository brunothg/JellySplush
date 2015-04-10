package de.bno.jellysplush.data.powerup;

import de.bno.jellysplush.data.JellyFish;
import de.bno.jellysplush.data.PlayGround;

public interface Powerup {

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

	public int getId();

	public Powerup clone();
}
