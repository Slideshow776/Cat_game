package com.sandra.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sandra.game.screens.Level_1_1;
import com.sandra.game.screens.Level_1_2;
import com.sandra.game.screens.Splash_intro;
import com.sandra.game.screens.Menu;

public class Cat_game extends Game {
	
	public SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		// setScreen(new Splash_intro(this));
		// setScreen(new Menu(this));
		// setScreen(new Level_1_1(this));
		setScreen(new Level_1_2(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
		super.dispose();
	}	
}
