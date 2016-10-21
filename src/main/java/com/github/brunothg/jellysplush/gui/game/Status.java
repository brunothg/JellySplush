package com.github.brunothg.jellysplush.gui.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.LineMetrics;

import com.github.brunothg.game.engine.d2.object.SceneObject;
import com.github.brunothg.game.engine.image.ImageUtils;
import com.github.brunothg.game.engine.image.InternalImage;
import com.github.brunothg.game.engine.image.sprite.DefaultSprite;
import com.github.brunothg.game.engine.image.sprite.Sprite;
import com.github.brunothg.jellysplush.Constants;

public class Status extends SceneObject
{

	private static final Color TEXT_COLOR = Color.BLACK;
	private static final Color BACKGROUND_COLOR = new Color(255, 255, 255, 120);

	private int points = 0;
	private int lifes = 0;
	private boolean invincible = false;

	private Sprite pics = new DefaultSprite(ImageUtils.BufferedImage(InternalImage.load("PointSprite.png")),
		Constants.TILE_WIDTH, Constants.TILE_HEIGHT);

	@Override
	protected void paint(Graphics2D g, long elapsedTime)
	{

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		drawStatus(g);
	}

	private void drawStatus(Graphics2D g)
	{

		double _widthPoints;
		double _widthLifes;

		_widthPoints = _widthLifes = getWidth() * 0.5;

		double _height = getHeight();

		double _widthIcPoints = Math.min(_widthPoints * 0.3, _height);
		double _widthTxPoints = _widthPoints - _widthIcPoints;

		double _widthIcLifes = Math.min(_widthLifes * 0.3, _height);
		double _widthTxLifes = _widthLifes - _widthIcLifes;

		drawIc((Graphics2D) g.create(0, 0, (int) _widthIcLifes, (int) _height), (int) _widthIcLifes, (int) _height, 0);
		drawTx((Graphics2D) g.create((int) _widthIcLifes, 0, (int) _widthTxLifes, (int) _height), (int) _widthTxLifes,
			(int) _height, getLifeString());

		drawIc((Graphics2D) g.create((int) (_widthPoints), 0, (int) _widthIcPoints, (int) _height),
			(int) _widthIcPoints, (int) _height, 1);
		drawTx((Graphics2D) g.create((int) (_widthPoints) + (int) (_widthIcPoints), 0, (int) _widthTxPoints,
			(int) _height), (int) _widthTxPoints, (int) _height, String.format("%d", points));

	}

	private String getLifeString()
	{
		if (isInvincible())
		{

			return "∞";
		}

		return String.format("%d", lifes);
	}

	private void drawTx(Graphics2D g, int width, int height, String txt)
	{

		Font f = new Font(Font.SANS_SERIF, Font.BOLD, height + 1);
		FontMetrics metrics = g.getFontMetrics(f);
		LineMetrics lineMetrics = metrics.getLineMetrics(txt, g);

		float _height;
		float _width;
		do
		{

			f = new Font(Font.SANS_SERIF, Font.BOLD, f.getSize() - 1);

			g.setFont(f);
			metrics = g.getFontMetrics(f);
			lineMetrics = metrics.getLineMetrics(txt, g);
			_height = lineMetrics.getAscent() + lineMetrics.getDescent();
			_width = metrics.stringWidth(txt);
		}
		while (_width > width || _height > height);

		g.setColor(BACKGROUND_COLOR);
		g.fillRoundRect(0, 0, width, height, (int) (width * 0.2), (int) (height * 0.2));

		g.setColor(TEXT_COLOR);
		g.drawString(txt, (float) ((width - _width) * 0.1f),
			(float) (lineMetrics.getAscent() + (height - (_height)) * 0.5));
	}

	private void drawIc(Graphics2D g, int width, int height, int index)
	{

		pics.drawTile(g, index, 0, width, height);
	}

	public int getPoints()
	{
		return points;
	}

	public void setPoints(int points)
	{
		this.points = points;
	}

	public int getLifes()
	{
		return lifes;
	}

	public void setLifes(int lifes)
	{
		this.lifes = lifes;
	}

	public boolean isInvincible()
	{
		return invincible;
	}

	public void setInvincible(boolean invincible)
	{
		this.invincible = invincible;
	}

}
