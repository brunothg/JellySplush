package com.github.brunothg.jellysplush.gui.end;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.brunothg.game.engine.d2.commons.Point;
import com.github.brunothg.game.engine.d2.scene.Scene;
import com.github.brunothg.jellysplush.data.JellyFish;
import com.github.brunothg.jellysplush.gui.start.ColorPicker;

public class EndScene implements Scene, KeyListener
{
	private static final Logger LOG = LoggerFactory.getLogger(EndScene.class);

	private static final String WIN_TEXT = "The winner is, with %d points and a life difference of %d ...";

	private static final double COLOR_SIZE = 0.5;

	private static final Color TEXT_COLOR = Color.BLACK;

	private static final Color BACKGROUND_COLOR_1 = new Color(235, 229, 48);
	private static final Color BACKGROUND_COLOR_2 = new Color(255, 248, 24);

	private static final int BACKGROUND_HEIGHT = 20;

	private ActionListener actionListener;

	private String winText;

	private ColorPicker cp = new ColorPicker(TEXT_COLOR);

	public EndScene(JellyFish fish1, JellyFish fish2, boolean rightIsWinner)
	{

		if (rightIsWinner)
		{

			winText = String.format(WIN_TEXT, fish2.getPoints(), fish2.getLifes());

			cp.setColor(fish2.getColor());
		}
		else
		{

			winText = String.format(WIN_TEXT, fish1.getPoints(), fish1.getLifes());

			cp.setColor(fish1.getColor());
		}

		LOG.info(winText);
	}

	@Override
	public void paintScene(Graphics2D g, int width, int height, long elapsedTime)
	{

		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		drawBackground(g, width, height);
		drawStatus(g, width, height);
	}

	private void drawStatus(Graphics2D g, int width, int height)
	{

		cp.setSize((int) (width * COLOR_SIZE), (int) (height * COLOR_SIZE));
		cp.setTopLeftPosition(
			new Point((int) (width * 0.5 - cp.getWidth() * 0.5), (int) (height * 0.5 - cp.getHeight() * 0.5)));
		cp.paintOnScene(g, 0);

		int _width = width;
		int _height = (int) (height * 0.25);

		Font oldFont = g.getFont();
		g.setFont(new Font(oldFont.getName(), oldFont.getStyle(), _height));

		FontMetrics metrics = g.getFontMetrics();
		int mHeight = metrics.getMaxAscent() + metrics.getMaxDescent();
		int mWidth = metrics.stringWidth(winText);

		while (mHeight > _height || mWidth > _width)
		{

			oldFont = g.getFont();
			g.setFont(new Font(oldFont.getName(), oldFont.getStyle(), oldFont.getSize() - 1));

			metrics = g.getFontMetrics();
			mHeight = metrics.getMaxAscent() + metrics.getMaxDescent();
			mWidth = metrics.stringWidth(winText);
		}

		g.setColor(TEXT_COLOR);
		g.drawString(winText, (float) (_width * 0.5 - mWidth * 0.5), (float) (_height * 0.5 - mHeight * 0.5));
	}

	private void drawBackground(Graphics2D g2d, int width, int height)
	{

		boolean cSwitch = true;

		for (int i = 0; i < height; i += BACKGROUND_HEIGHT)
		{

			if (cSwitch)
			{
				g2d.setColor(BACKGROUND_COLOR_1);
			}
			else
			{
				g2d.setColor(BACKGROUND_COLOR_2);
			}

			g2d.fillRect(0, i, width, BACKGROUND_HEIGHT);

			cSwitch = !cSwitch;
		}
	}

	@Override
	public EventListener[] getEventListeners()
	{
		return new EventListener[] { this };
	}

	public ActionListener getActionListener()
	{
		return actionListener;
	}

	public void setActionListener(ActionListener actionListener)
	{
		this.actionListener = actionListener;
	}

	private void fireRestartEvent()
	{

		if (getActionListener() != null)
		{
			getActionListener().actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "restart"));
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
	}

	@Override
	public void keyReleased(KeyEvent e)
	{

		int keyCode = e.getKeyCode();

		switch (keyCode)
		{

			case KeyEvent.VK_ESCAPE:
			case KeyEvent.VK_ENTER:

				fireRestartEvent();
			break;
		}
	}
}
