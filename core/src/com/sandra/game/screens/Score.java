package com.sandra.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sandra.game.Cat_game;
import com.sandra.game.utils.Constants;

public class Score implements Screen {

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
    private Sprite fade_transition;
    private float transition_alpha;

    private boolean pause;
    private boolean fading_in;
    private Sprite game_over;
    private boolean is_all_cat1s_annihilated;

    public Score(Cat_game game, boolean is_all_cat1s_annihilated, int coins, int numCoins, int deadCat1s, int time) {
        this.game = game;
        this.is_all_cat1s_annihilated = is_all_cat1s_annihilated;

        // Score algorithm
        bronze = silver = gold = false;
        if (
            deadCat1s == 0 &&
                coins == numCoins &&
                    time >= 2*(Constants.TIME/3)
            ) {
            gold = true; 
        }
        else if (deadCat1s == 0 &&
                    coins >= numCoins/2 &&
                            time >= 1*(Constants.TIME/3)
                ) {
            silver = true; 
        }
        else {
            bronze = true; 
        }
    }

    @Override
    public void show() {
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
        game_over = new Sprite(new Texture("images/Game_Over.png"));
        
        // Transition effect
        fade_transition = new Sprite(new Texture("images/black_screen.png"));
        fade_transition .setSize(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        fade_transition .setPosition(0, 0);
        transition_alpha = 1f;
        fading_in = true;
    }

    @Override
    public void render(float delta){
        if(!pause) update(delta);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1); // black

        game.batch.begin();
        game.batch.draw(background1, 0, 0, Constants.GAME_WIDTH / Constants.PPM, Constants.GAME_HEIGHT / Constants.PPM);
        game.batch.draw(background2, 0, 0, Constants.GAME_WIDTH / Constants.PPM, Constants.GAME_HEIGHT / Constants.PPM);
        
        if(is_all_cat1s_annihilated) { 
            game.batch.draw(
                game_over,
                (Constants.GAME_WIDTH / 2 - 530 * 1.0f / 2) / Constants.PPM,
                (Constants.GAME_HEIGHT / 2 - 83 * 1.0f / 2) / Constants.PPM,
                530 * 1.0f / Constants.PPM,
                83 * 1.0f / Constants.PPM
            );
        }
        else if(bronze) { drawTitle(bronze_title); }
        else if (silver) { drawTitle(silver_title); }
        else if (gold) { drawTitle(gold_title); }
        fade_transition.draw(game.batch, transition_alpha); // 1 is black, 0 is transparent
        game.batch.end();
    }
    
    public void resize(int width, int height) {}
	public void pause() {pause = true;}
	public void resume() {pause = false;}
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
        update_alpha();
        groundWrapScrolling1();
        groundWrapScrolling2();
    }

    private void update_alpha() {
        if (transition_alpha > 0 && fading_in) {
            transition_alpha -= .008f;
        } else if (transition_alpha <= 0 && fading_in) {
            transition_alpha = 0;
            fading_in = false;
        } else if (transition_alpha < 1f) {
            transition_alpha += .008f;
        } else {
            ((Cat_game) Gdx.app.getApplicationListener()).setScreen(new Menu(game, true));
        }
    }

    private void drawTitle(Sprite sprite) {
        game.batch.draw(
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