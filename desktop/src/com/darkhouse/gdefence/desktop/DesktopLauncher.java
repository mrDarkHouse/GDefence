package com.darkhouse.gdefence.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.darkhouse.gdefence.GDefence;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "GDefence";
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new GDefence(), config);
	}
}
