package de.bno.jellysplush.data.powerup;

import de.bno.jellysplush.data.JellyFish;
import de.bno.jellysplush.data.PlayGround;

public interface Powerup
{

	public void manipulateOwnJellyFish(JellyFish fish);

	public void manipulateOtherJellyFishs(JellyFish... fishs);

	public void manipulatePlayGround(PlayGround playground);
}
