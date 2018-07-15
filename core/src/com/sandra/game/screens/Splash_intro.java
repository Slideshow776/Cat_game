package com.sandra.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

	private BitmapFont font_created_by;
	private BitmapFont font_sandra_moen;
	private float scale = 1.5f;

	public Splash_intro(Cat_game game) {this.game = game;}

	public void show() {
		splash_image = new Sprite(new Texture("images/splash_intro.jpg"));
		splash_image.setSize(splash_image.getWidth() / 2, splash_image.getHeight() / 2);
		splash_image.setPosition(
				Gdx.graphics.getWidth() / 2 / - splash_image.getWidth() / 2,
				Gdx.graphics.getHeight() / 2 - splash_image.getHeight() / 2
		);
		font_created_by = new BitmapFont();
		font_created_by.getData().setScale(scale);
		font_created_by.getRegion().getTexture().setFilter((TextureFilter.Linear), TextureFilter.Linear);
		font_created_by.setColor(1, 1, 1, alpha);

		font_sandra_moen = new BitmapFont();
		font_sandra_moen.getData().setScale(scale);
		font_sandra_moen.getRegion().getTexture().setFilter((TextureFilter.Linear), TextureFilter.Linear);
		font_sandra_moen.setColor(1, 0, 1, alpha);
	}

	public void render(float delta) {		
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
		splash_image.draw(game.batch, alpha);
		
		font_created_by.draw(game.batch, "Created by", Gdx.graphics.getWidth()-320, Gdx.graphics.getHeight() / 2);
		font_created_by.setColor(1, 1, 1, alpha+.1f);
		
		font_sandra_moen.draw(game.batch, "Sandra Moen", Gdx.graphics.getWidth()-200, Gdx.graphics.getHeight() / 2);
		font_sandra_moen.setColor(1, 0, 1, alpha+.1f);
		
        game.batch.end(); 

		if(!pause) {update();}
	}

	public void dispose() {
		splash_image.getTexture().dispose();
		font_created_by.dispose();
	}

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