package com.sandra.game.screens;

import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.sandra.game.Cat_game;
import com.sandra.game.utils.Assets;
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

    private Sprite bronze_title;
    private Sprite silver_title;
    private Sprite gold_title;

    private boolean bronze, silver, gold;

    public Score(Cat_game game, int cat1s, int coins, int numCoins, int deadCat1s) {
        this.game = game;
        
        // Score algorithm
        bronze = silver = gold = false;
        if (deadCat1s == 0 && coins == numCoins) { gold = true; }
        else if (deadCat1s == 0 && coins >= numCoins/2) { silver = true; }
        else { bronze = true; }
    }

    @Override
    public void show() {
        /* animation_start_time = TimeUtils.nanoTime();
        animation_pos = new Vector2(550 / Constants.PPM, 395 / Constants.PPM);
        // animation_scale = .25f / Constants.PPM; */

        // Background
        background1 = new Sprite(new Texture("images/star_background1.png"));
        background1.getTexture().setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
        background1ScrollTimer = 0;
        background1ScrollSpeed = .04f;
        background2 = new Sprite(new Texture("images/star_background2.png"));
        background2.getTexture().setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
        background2ScrollTimer = 0;
        background2ScrollSpeed = .1f;

        // Score title
        bronze_title = new Sprite(new Texture("images/Score_bronze.png"));
        silver_title = new Sprite(new Texture("images/Score_silver.png"));
        gold_title = new Sprite(new Texture("images/Score_gold.png"));

        // Timer to go to menu
        Timer timer = new Timer();
        Task task = new Task() {
            public void run() {
                dispose();
                ((Cat_game) Gdx.app.getApplicationListener()).setScreen(new Menu(game, true));
            }
        };
        timer.schedule(task, 3);
    }

    @Override
    public void render(float delta) {
        update(delta);

        /* float animation_time_seconds = Utils.secondsSince(animation_start_time);
        animation_region = Assets.instance.cat1Assets.cat1_sleeping_animation.getKeyFrame(animation_time_seconds); */
         
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 0, 1, 1); // black

        game.batch.begin();
        game.batch.draw(background1, 0, 0, Constants.GAME_WIDTH / Constants.PPM, Constants.GAME_HEIGHT / Constants.PPM);
        game.batch.draw(background2, 0, 0, Constants.GAME_WIDTH / Constants.PPM, Constants.GAME_HEIGHT / Constants.PPM);
        
        if(bronze) { drawTitle(game.batch, bronze_title); }
        else if (silver) { drawTitle(game.batch, silver_title); }
        else if (gold) { drawTitle(game.batch, gold_title); }
        /* Utils.drawTextureRegion(game.batch, animation_region, animation_pos.x, animation_pos.y); */
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
    public void dispose() {
        background1.getTexture().dispose();
        background2.getTexture().dispose();
        bronze_title.getTexture().dispose();
        silver_title.getTexture().dispose();
        gold_title.getTexture().dispose();
    }

    private void update(float delta) {
        groundWrapScrolling1();
        groundWrapScrolling2();
    }

    private void drawTitle(SpriteBatch sb, Sprite sprite) {
        sb.draw(
            sprite,
            (Constants.GAME_WIDTH / 2 - 283 * 1.5f / 2) / Constants.PPM,
            (Constants.GAME_HEIGHT / 2 - 173 * 1.5f / 2) / Constants.PPM,
            283 * 1.5f / Constants.PPM,
            173 * 1.5f / Constants.PPM
        );
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