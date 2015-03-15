package de.bno.jellysplush;

import game.engine.frame.SwingGameFrame;
import game.engine.image.InternalImage;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.bno.jellysplush.gui.start.StartScene;

public class Main {

	public static final String IMAGE_PATH = "/de/bno/jellysplush/images/";
	public static final Dimension WINDOW_SIZE = new Dimension(700, 700);

	private static SwingGameFrame display;

	public static void main(String[] args) {

		setLaF();
		setupInternalImage();

		setupWindow();
		setStartScene();
	}

	private static void setStartScene() {

		display.setScene(new StartScene());
	}

	private static void setupWindow() {

		display = new SwingGameFrame("JellySplush");
		display.setLocationRelativeTo(null);
		display.setSize(WINDOW_SIZE.width, WINDOW_SIZE.height);

		display.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {

				display.setSize(WINDOW_SIZE.width, WINDOW_SIZE.height, true);
			}
		});

		display.setVisible(true);
	}

	private static void setupInternalImage() {

		InternalImage.setRootFolder(IMAGE_PATH);
	}

	private static void setLaF() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
		}
	}

}
