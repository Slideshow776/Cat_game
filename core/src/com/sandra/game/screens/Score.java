package com.sandra.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.sandra.game.Cat_game;
import com.sandra.game.utils.Constants;
import com.sandra.game.utils.Utils;

public class Score implements Screen {

    private TextureRegion animation_region;
    private float animation_start_time;
    private Vector2 animation_pos;
    private float animation_scale;

    private Sprite background1;
    private float background1ScrollTimer;
	private float background1ScrollSpeed;
    private Sprite background2;
    private float background2ScrollTimer;
	private float background2ScrollSpeed;

    private Cat_game game;

    public Score(Cat_game game) {
        this.game = game;
    }

    @Override
    public void show() {
        animation_start_time = TimeUtils.nanoTime();
        animation_pos = new Vector2(550, 395);
        animation_scale = .25f;

        // Background
        background1 = new Sprite(new Texture("images/star_background1.png"));
        background1.setSize(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        background1.getTexture().setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
        background1ScrollTimer = 0;
        background1ScrollSpeed = .04f;
        background2 = new Sprite(new Texture("images/star_background2.png"));
        background2.setSize(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        background2.getTexture().setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
        background2ScrollTimer = 0;
        background2ScrollSpeed = .1f;

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         
        update(delta);

        game.batch.begin();
        game.batch.draw(background1, 0, 0, 800, 600);
        game.batch.draw(background2, 0, 0, 800, 600);

        //Utils.drawTextureRegion(game.batch, animation_region, animation_pos.x, animation_pos.y, animation_scale, true);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}

    private void update(float delta) {
        groundWrapScrolling1();
        groundWrapScrolling2();

    }

    private void groundWrapScrolling1() {
		background1ScrollTimer += Gdx.graphics.getDeltaTime()*background1ScrollSpeed;
		if(background1ScrollTimer>1.0f) background1ScrollTimer = 0.0f;
		background1.setU(background1ScrollTimer);
		background1.setU2(background1ScrollTimer+1);	
    }
    
    private void groundWrapScrolling2() {
		background2ScrollTimer += Gdx.graphics.getDeltaTime()*background2ScrollSpeed;
		if(background2ScrollTimer>1.0f) background2ScrollTimer = 0.0f;
		background2.setU(background2ScrollTimer);
		background2.setU2(background2ScrollTimer+1);	
	}
}