package com.github.brunothg.jellysplush.gui.start;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.font.LineMetrics;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import com.github.brunothg.game.engine.d2.object.SceneObject;
import com.github.brunothg.game.engine.time.TimeUtils;
import com.github.brunothg.jellysplush.Constants;

public class Countdown extends SceneObject
{

	private AtomicLong number = new AtomicLong();
	private AtomicBoolean running = new AtomicBoolean();

	private AtomicLong time = new AtomicLong();

	public Countdown()
	{

		number.set(-1);
		running.set(false);
	}

	@Override
	protected void paint(Graphics2D g, long elapsedTime)
	{

		long now = time.addAndGet(elapsedTime);
		long seconds = now / TimeUtils.NANOSECONDS_PER_SECOND;
		time.addAndGet(-(seconds * TimeUtils.NANOSECONDS_PER_SECOND));

		long zahl = number.addAndGet(-seconds);

		paintNumber(Math.max(0, zahl), g);
	}

	private void paintNumber(long zahl, Graphics2D g)
	{

		g.setColor(Constants.COUNTDOWN_COLOR);

		String str = "" + zahl;

		Font f = new Font(Font.SANS_SERIF, Font.BOLD, Math.min(getWidth(), getHeight()));

		FontMetrics metrics = g.getFontMetrics(f);
		LineMetrics lineMetrics = metrics.getLineMetrics(str, g);

		float _width;
		float _height;
		do
		{

			f = new Font(Font.SANS_SERIF, Font.BOLD, f.getSize() - 1);
			g.setFont(f);
			metrics = g.getFontMetrics(f);
			lineMetrics = metrics.getLineMetrics(str, g);

			_width = metrics.stringWidth(str);
			_height = lineMetrics.getAscent() + lineMetrics.getDescent();
		}
		while (_height > getHeight() || _width > getWidth());

		float _x = (float) ((getWidth() - _width) * 0.5);
		float _y = (float) (lineMetrics.getAscent() + (getHeight() - (_height)) * 0.5);

		g.drawString(str, _x, _y);
	}

	public long getNumber()
	{
		return number.get();
	}

	public void setNumber(int number)
	{
		this.number.set(number);
		this.time.set(0);
	}

	public void setRunning(boolean b)
	{

		running.set(b);
	}

	public boolean isRunning()
	{

		return running.get();
	}
}
