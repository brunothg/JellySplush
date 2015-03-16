package de.bno.jellysplush;

import game.engine.frame.SwingGameFrame;
import game.engine.image.ImageUtils;
import game.engine.image.InternalImage;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main
{

	public static final String IMAGE_PATH = "/de/bno/jellysplush/images/";
	public static final Dimension WINDOW_SIZE = new Dimension(23 * 32, 23 * 32);

	private static SwingGameFrame display;

	public static void main(String[] args)
	{

		setLaF();
		setupInternalImage();

		setupWindow();
		new Controller(display);
	}

	private static void setupWindow()
	{

		display = new SwingGameFrame("JellySplush");
		display.setLocationRelativeTo(null);
		display.setSize(WINDOW_SIZE.width, WINDOW_SIZE.height);
		display.setCursor(ImageUtils.createEmptyCursor(null));

		display.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowOpened(WindowEvent e)
			{

				display.setSize(WINDOW_SIZE.width, WINDOW_SIZE.height, true);
			}
		});

		display.addComponentListener(new ComponentAdapter()
		{

			@Override
			public void componentShown(ComponentEvent e)
			{

				display.setSize(WINDOW_SIZE.width, WINDOW_SIZE.height, true);
			}
		});

		display.setVisible(true);

		try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e1)
		{
		}
	}

	private static void setupInternalImage()
	{

		InternalImage.setRootFolder(IMAGE_PATH);
	}

	private static void setLaF()
	{

		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException
			| UnsupportedLookAndFeelException e)
		{
		}
	}

}
