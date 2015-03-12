package de.bno.jellysplush;

import game.engine.image.InternalImage;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

	public static final String IMAGE_PATH = "/de/bno/jellysplush/images/";

	public static void main(String[] args) {

		setLaF();
		setupInternalImage();
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
