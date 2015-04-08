package de.bno.jellysplush.gui.start;

import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_UP;
import static java.awt.event.KeyEvent.VK_W;
import game.engine.stage.scene.Scene;
import game.engine.stage.scene.object.Point;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.EventListener;

public class StartScene implements Scene, KeyListener
{

	private static final String TXT = "Choose your colors - Press [Enter] to start";
	private static final double TXT_LOCATION_X = 0.1;

	private static final double PICKER_SIZE = 0.1;
	private static final double PICKER_LOCATION_Y = 0.5;
	private static final double PICKER_LOCATION_X = 0.1;

	private static final Color BACKGROUND_COLOR_1 = new Color(235, 229, 48);
	private static final Color BACKGROUND_COLOR_2 = new Color(255, 248, 24);

	private static final int BACKGROUND_HEIGHT = 20;

	private static final Color[] colors = new Color[] { new Color(0, 153, 255), Color.YELLOW, Color.ORANGE,
			new Color(148, 0, 211), new Color(204, 0, 204), Color.WHITE };

	private int selCol1 = 0;
	private int selCol2 = 1;

	private ColorPicker cp1 = new ColorPicker(colors[selCol1]);
	private ColorPicker cp2 = new ColorPicker(colors[selCol2]);

	private ActionListener actionListener;

	@Override
	public void paintScene(Graphics2D g2d, int width, int height, long elapsed)
	{

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		drawBackground(g2d, width, height);
		drawText(g2d, width, height);

		recalculateSizes(width, height);
		recalculateLocations(width, height);

		cp1.paintOnScene(g2d, elapsed);
		cp2.paintOnScene(g2d, elapsed);
	}

	private void drawText(Graphics2D g2d, int width, int height)
	{

		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));

		FontMetrics metrics = g2d.getFontMetrics();
		Rectangle2D bounds = metrics.getStringBounds(TXT, g2d);

		int stringWidth = (int) bounds.getWidth();
		int stringHeight = (int) bounds.getHeight();

		g2d.drawString(TXT, (int) (width * 0.5 - stringWidth * 0.5), (int) (stringHeight + height * TXT_LOCATION_X));
	}

	private void recalculateLocations(int width, int height)
	{

		double _y1 = height * PICKER_LOCATION_Y - cp1.getHeight() * 0.5;
		double _y2 = height * PICKER_LOCATION_Y - cp2.getHeight() * 0.5;

		double _x1 = width * PICKER_LOCATION_X;
		double _x2 = width - width * PICKER_LOCATION_X - cp2.getWidth();

		int y1 = (int) _y1;
		int y2 = (int) _y2;

		int x1 = (int) _x1;
		int x2 = (int) _x2;

		cp1.setTopLeftPosition(new Point(x1, y1));
		cp2.setTopLeftPosition(new Point(x2, y2));
	}

	private void recalculateSizes(int width, int height)
	{

		double _width = width * PICKER_SIZE;
		double _height = height * PICKER_SIZE;

		width = (int) _width;
		height = (int) _height;

		cp1.setSize(width, height);
		cp2.setSize(width, height);
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

	private void setColor(int col, boolean right)
	{

		if (right)
		{

			selCol2 = col;
			if (selCol2 >= colors.length)
			{
				selCol2 = 0;
			}
			else if (selCol2 < 0)
			{
				selCol2 = colors.length - 1;
			}
		}
		else
		{

			selCol1 = col;
			if (selCol1 >= colors.length)
			{
				selCol1 = 0;
			}
			else if (selCol1 < 0)
			{
				selCol1 = colors.length - 1;
			}
		}

	}

	private void down(boolean right)
	{

		if (right)
		{

			setColor(selCol2 + 1, true);

			if (selCol2 == selCol1)
			{
				setColor(selCol2 + 1, true);
			}

			cp2.setColor(colors[selCol2]);
		}
		else
		{

			setColor(selCol1 + 1, false);

			if (selCol2 == selCol1)
			{
				setColor(selCol1 + 1, false);
			}

			cp1.setColor(colors[selCol1]);
		}

		System.out.println("Down... " + right);
	}

	private void up(boolean right)
	{

		if (right)
		{

			setColor(selCol2 - 1, true);

			if (selCol2 == selCol1)
			{
				setColor(selCol2 - 1, true);
			}

			cp2.setColor(colors[selCol2]);
		}
		else
		{

			setColor(selCol1 - 1, false);

			if (selCol2 == selCol1)
			{
				setColor(selCol1 - 1, false);
			}

			cp1.setColor(colors[selCol1]);
		}

		System.out.println("Up... " + right);
	}

	private void select()
	{

		System.out.println("Select...");

		ActionListener al = getActionListener();

		if (al != null)
		{

			al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "start_game"));
		}
	}

	@Override
	public EventListener[] getEventListeners()
	{
		return new EventListener[] { this };
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void keyPressed(KeyEvent e)
	{

		int keyCode = e.getKeyCode();

		switch (keyCode)
		{
			case VK_ENTER:
				select();
			break;

			case VK_UP:
				up(true);
			break;
			case VK_DOWN:
				down(true);
			break;
			case VK_LEFT:
				up(true);
			break;
			case VK_RIGHT:
				down(true);
			break;
			case VK_W:
				up(false);
			break;
			case VK_S:
				down(false);
			break;
			case VK_A:
				up(false);
			break;
			case VK_D:
				down(false);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{

	}

	public ActionListener getActionListener()
	{
		return actionListener;
	}

	public void setActionListener(ActionListener actionListener)
	{
		this.actionListener = actionListener;
	}

	public Color getColor(boolean right)
	{

		if (right)
		{

			return colors[selCol2];
		}
		else
		{

			return colors[selCol1];
		}
	}

}
