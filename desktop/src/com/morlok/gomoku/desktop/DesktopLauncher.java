package com.morlok.gomoku.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.morlok.gomoku.Gomoku;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Gomoku";
		config.width = 270;
		config.height = 330;
		new LwjglApplication(new Gomoku(), config);
	}
}
