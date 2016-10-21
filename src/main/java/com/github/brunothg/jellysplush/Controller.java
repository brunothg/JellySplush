package com.github.brunothg.jellysplush;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.brunothg.game.engine.d2.frame.SwingGameFrame;
import com.github.brunothg.jellysplush.data.JellyFish;
import com.github.brunothg.jellysplush.data.powerup.DefaultStrategy;
import com.github.brunothg.jellysplush.data.powerup.PowerupStrategy;
import com.github.brunothg.jellysplush.gui.end.EndScene;
import com.github.brunothg.jellysplush.gui.game.GameListener;
import com.github.brunothg.jellysplush.gui.game.GameScene;
import com.github.brunothg.jellysplush.gui.start.StartScene;

public class Controller implements GameListener
{
	private static final Logger LOG = LoggerFactory.getLogger(Controller.class);

	private SwingGameFrame display;

	public Controller(SwingGameFrame disp)
	{

		this.display = disp;

		initialize();
	}

	private void initialize()
	{

		setStartScene();
	}

	private void setStartScene()
	{

		final StartScene startScene = new StartScene();
		startScene.setActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{

				setGameScene(startScene.getColor(false), startScene.getColor(true));
			}
		});

		display.setScene(startScene);
	}

	private void setGameScene(Color c1, Color c2)
	{
		LOG.info("Start game: Left[{}] - Right[{}]", c1, c2);

		PowerupStrategy strategy = new DefaultStrategy();

		try
		{
			Object strategyLoaded = Settings.getClass(Settings.KEY_STRATEGY, strategy.getClass()).newInstance();
			if (PowerupStrategy.class.isInstance(strategyLoaded))
			{
				strategy = (PowerupStrategy) strategyLoaded;
			}
		}
		catch (Exception e)
		{
			LOG.warn("Strategy could not be loaded", e);
		}

		final GameScene gameScene = new GameScene(c1, c2, strategy);
		gameScene.setFreeMovement(Settings.getBoolean(Settings.KEY_FREE_MOVEMENT, true));
		gameScene.setGameListener(this);

		display.setScene(gameScene);
	}

	@Override
	public void gameOver(JellyFish fish1, JellyFish fish2, boolean rightIsWiner)
	{
		LOG.debug("-> Game Over Screen");

		final EndScene endScene = new EndScene(fish1, fish2, rightIsWiner);
		endScene.setActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{

				setStartScene();
			}
		});

		display.setScene(endScene);
	}

	@Override
	public int getMaxPoints()
	{

		return Settings.getInt(Settings.KEY_MAX_POINTS, Constants.MAX_POINTS_DEFAULT);
	}

	@Override
	public int getMaxLifes()
	{

		return Settings.getInt(Settings.KEY_MAX_LIFES, Constants.MAX_LIFES_DEFAULT);
	}
}
