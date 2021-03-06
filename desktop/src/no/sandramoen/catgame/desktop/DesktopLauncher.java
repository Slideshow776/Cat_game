package no.sandramoen.catgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Cat Game";
		int scale = 2; // resolution of lg g4 is 1440 x 2560 (in portrait mode)
		config.height = (int)1440 / scale;
		config.width = (int)2560 / scale;
		config.resizable = false;
		new LwjglApplication(new com.sandra.game.Cat_game(), config);
	}
}
