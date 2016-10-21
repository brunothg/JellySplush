package com.github.brunothg.jellysplush.gui.game;

import java.awt.Graphics2D;

import com.github.brunothg.game.engine.d2.object.SceneObject;
import com.github.brunothg.game.engine.image.sprite.Sprite;

public class AnimatedSprite extends SceneObject
{

	private Sprite sprite;
	private long timePerFrame;

	private int x = 0;
	private int y = 0;

	private long time = 0;

	public AnimatedSprite(Sprite sprite, long timePerFrame)
	{
		this.sprite = sprite;
		this.timePerFrame = timePerFrame;
	}

	@Override
	protected void paint(Graphics2D g2d, long ealpsedTime)
	{
		time += ealpsedTime;
		long frames = time / timePerFrame;
		time -= frames * timePerFrame;
		increaseFrame(frames);

		sprite.drawTile(g2d, x, y, getWidth(), getHeight());
	}

	private void increaseFrame(long frames)
	{
		while (frames > 0)
		{
			frames--;
			x++;

			if (x >= sprite.getColumns())
			{
				x = 0;
				y++;
			}
			if (y >= sprite.getRows())
			{
				x = 0;
				y = 0;
			}
		}
	}

}
