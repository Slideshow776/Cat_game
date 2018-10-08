package com.sandra.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sandra.game.Cat_game;
import com.sandra.game.utils.Constants;

public class Splash_intro implements Screen {

    private Cat_game game;
	private Sprite splash_image;
	
	private float alpha = 0;
	private float alphaSpeed = 0;	
	private boolean pause = false;

	OrthographicCamera camera;

	public Splash_intro(Cat_game game) {
		this.game = game;
	}

	public void show() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        game.batch.setProjectionMatrix(camera.combined);

		splash_image = new Sprite(new Texture("images/splash_intro2.jpg"));
		splash_image.setSize(splash_image.getWidth() / 3.9f, splash_image.getHeight() / 3.4f);
		splash_image.setPosition(
			Constants.GAME_WIDTH / 2  - splash_image.getWidth() / 2.4f,
			Constants.GAME_HEIGHT / 2 - splash_image.getHeight() / 2
		);
	}

	public void render(float delta) {		
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1); // black
		
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
        game.batch.setProjectionMatrix(camera.combined);

		alpha = (float) Math.sin(alphaSpeed);
		if(alpha < 0) {
			try {Thread.sleep(250);} // creates a dramatic pause between splash and next game-state.
			catch (InterruptedException e) {e.printStackTrace();}
			((Game) Gdx.app.getApplicationListener()).setScreen(new Menu(game, true));
		}
		else if(alpha > .6f) {alphaSpeed+=.016f;} // peek speed
		else {alphaSpeed+=.008f;} // standard speed
	}
}
