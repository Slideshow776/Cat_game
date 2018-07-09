package com.sandra.game.screens;

import java.awt.Point;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sandra.game.Cat_game;

public class Splash_intro implements Screen {

    private Cat_game game;
	private Sprite splash_image;
	private SpriteBatch sb;	
	private float alpha = 0;
	private float alphaSpeed = 0;
	
	private boolean pause = false;

	public Splash_intro(Cat_game game) {
		this.game = game;
		System.out.println("TEST: " + game);
	}

	public void show() {
		
		splash_image = new Sprite(new Texture("images/splash_intro.jpg"));
		splash_image.setSize(splash_image.getWidth() / 2, splash_image.getHeight() / 2);
		splash_image.setPosition(
				Gdx.graphics.getWidth() / 2 / - splash_image.getWidth() / 2,
				Gdx.graphics.getHeight() / 2 - splash_image.getHeight() / 2
		);
	}

	public void render(float delta) {		
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin(); 
		splash_image.draw(game.batch, alpha);
        game.batch.end(); 

		if(!pause) {update();}
	}

	public void dispose() {splash_image.getTexture().dispose();}
	public void resize(int width, int height) {}
	public void hide() {dispose();}	
	public void pause() {pause = true;}
	public void resume() {pause = false;}	

	private void update() { // alpha fade in/out
		alpha = (float) Math.sin(alphaSpeed);
		if(alpha < 0) {
			try {Thread.sleep(250);} // creates a dramatic pause between splash and next game-state.
			catch (InterruptedException e) {e.printStackTrace();}
			((Game) Gdx.app.getApplicationListener()).setScreen(new Level_1_1(game));
		}
		else if(alpha > .6f) {alphaSpeed+=.016f;} // peek speed
		else {alphaSpeed+=.008f;} // standard speed
	}
}