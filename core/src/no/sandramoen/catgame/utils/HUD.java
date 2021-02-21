package com.sandra.game.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.sandra.game.Cat_game;
import com.sandra.game.handlers.ButtonListener;
import com.sandra.game.screens.Level;
import com.sandra.game.screens.Menu;

public class HUD {    
    private Sprite coin_image, cathead_image, clock_image;

    private Sprite[] numbers;
    private Sprite cathead_ones, cathead_tens;
    private Sprite coins_ones, coins_tens;    
    private Sprite x_cat, x_coins;
    private int num_coins, num_cats, time;

    private Button btn_pause;
    private Button btn_return;
    private ButtonListener btn_pause_listener;
    private ButtonListener btn_return_listener;
    private Stage stage;

    private Level level;
    private Cat_game game;

    private Sprite clock_ones, clock_tens, x_clock;

    public HUD(Cat_game game, OrthographicCamera camera, Level level) {
        this.game = game;
        this.level = level;
        num_cats = num_coins = 0;

        coin_image = new Sprite(Assets.instance.coinAssets.coin);
        coin_image.setSize(25 / Constants.PPM, 25 / Constants.PPM);
        coin_image.setPosition(4f, 5.6f);

        x_cat = new Sprite(Assets.instance.hudAssets._x);
        x_cat.setSize(12.5f / Constants.PPM, 12.5f / Constants.PPM);
        x_cat.setPosition(4.23f, 5.62f);
        
        cathead_image = new Sprite(Assets.instance.cat1Assets.cathead);
        cathead_image.setSize(25 / Constants.PPM, 25 / Constants.PPM);
        cathead_image.setPosition(3f, 5.6f);
        
        x_coins = new Sprite(Assets.instance.hudAssets._x);
        x_coins.setSize(12.5f / Constants.PPM, 12.5f / Constants.PPM);
        x_coins.setPosition(3.23f, 5.62f);
        
        clock_image = new Sprite(Assets.instance.hudAssets.clock);
        clock_image.setSize(25 / Constants.PPM, 25 / Constants.PPM);
        clock_image.setPosition(5f, 5.6f);

        x_clock = new Sprite(Assets.instance.hudAssets._x);
        x_clock.setSize(12.5f / Constants.PPM, 12.5f / Constants.PPM);
        x_clock.setPosition(5.23f, 5.62f);

        numbers = new Sprite[10];
        numbers[0] = new Sprite(Assets.instance.hudAssets._0);
        numbers[1] = new Sprite(Assets.instance.hudAssets._1);
        numbers[2] = new Sprite(Assets.instance.hudAssets._2);
        numbers[3] = new Sprite(Assets.instance.hudAssets._3);
        numbers[4] = new Sprite(Assets.instance.hudAssets._4);
        numbers[5] = new Sprite(Assets.instance.hudAssets._5);
        numbers[6] = new Sprite(Assets.instance.hudAssets._6);
        numbers[7] = new Sprite(Assets.instance.hudAssets._7);
        numbers[8] = new Sprite(Assets.instance.hudAssets._8);
        numbers[9] = new Sprite(Assets.instance.hudAssets._9);

        for (Sprite number : numbers) 
            number.setSize(25 / Constants.PPM, 25 / Constants.PPM);

        cathead_ones = new Sprite();
        cathead_ones.set(numbers[0]);
        cathead_ones.setSize(25 / Constants.PPM, 25 / Constants.PPM);
        cathead_tens = new Sprite();
        cathead_tens.set(numbers[1]);
        cathead_tens.setSize(25 / Constants.PPM, 25 / Constants.PPM);
       
        coins_ones = new Sprite();
        coins_ones.set(numbers[0]);
        coins_ones.setSize(25 / Constants.PPM, 25 / Constants.PPM);
        coins_tens = new Sprite();
        coins_tens.set(numbers[1]);
        coins_tens.setSize(25 / Constants.PPM, 25 / Constants.PPM);
        coins_ones = new Sprite();
        
        clock_ones = new Sprite();
        clock_ones.set(numbers[0]);
        clock_ones.setSize(25 / Constants.PPM, 25 / Constants.PPM);
        clock_ones.setPosition(5.35f, 5.6f);
        clock_tens = new Sprite();
        clock_tens.set(numbers[1]);
        clock_tens.setSize(25 / Constants.PPM, 25 / Constants.PPM);
        clock_tens.setPosition(5.55f, 5.6f);

        stage = new Stage(new StretchViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT, camera));
        Gdx.input.setInputProcessor(stage);

        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(Assets.instance.hudAssets.btn_pause);
        btn_pause = new Button(buttonStyle);
        btn_pause_listener = new ButtonListener();
        btn_pause.addListener(btn_pause_listener);
        btn_pause.setSize(40 / Constants.PPM, 40 / Constants.PPM);
        btn_pause.setPosition(7.5f, 5.45f);

        buttonStyle = new ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(Assets.instance.hudAssets.btn_return);
        btn_return = new Button(buttonStyle);
        btn_return_listener = new ButtonListener();
        btn_return.addListener(btn_return_listener);
        btn_return.setSize(40 / Constants.PPM, 40 / Constants.PPM);
        btn_return.setPosition(7.1f, 5.45f);
        
        stage.addActor(btn_pause);
        stage.addActor(btn_return);
        stage.setDebugAll(true);
    }        

    public void render(SpriteBatch batch) {
        cathead_image.draw(batch);
        coin_image.draw(batch);
        clock_image.draw(batch);

        cathead_ones.draw(batch);
        x_cat.draw(batch);
        coins_ones.draw(batch);
        x_coins.draw(batch);

        if (num_cats >= 10) cathead_tens.draw(batch);
        if (num_coins >= 10) coins_tens.draw(batch);

        x_clock.draw(batch);
        clock_ones.draw(batch);
        clock_tens.draw(batch);

        btn_pause.draw(batch, 1);
        btn_return.draw(batch, 1);
        //stage.draw();
    }

    public void update(float delta, int numCats, int numCoins, int time) {
        update_clock(time);
        update_score(numCats, numCoins);
        listen_on_buttons(delta);
    }
    
    public void dispose() {
        // stage.dispose();
        cathead_image.getTexture().dispose(); 
        cathead_ones.getTexture().dispose();
        cathead_tens.getTexture().dispose();
        coin_image.getTexture().dispose();
        coins_ones.getTexture().dispose();
        coins_tens.getTexture().dispose();
        clock_image.getTexture().dispose();
    }
    
    private void listen_on_buttons(float delta) {
        stage.act(delta);

        if (btn_pause_listener.getTouched()) {
            level.set_pause(btn_pause_listener.getTouched());
            btn_pause.setColor(Color.DARK_GRAY);
        } else {
            level.set_pause(false);
            btn_pause.setColor(Color.WHITE);
        }

        if(btn_return_listener.getTouched()) {
            dispose();
            ((Game) Gdx.app.getApplicationListener()).setScreen(new Menu(game, true));
        }
    }

    private void update_clock(int time) {
        this.time = time;
        if (time <= 0) {
            clock_ones.set(numbers[0]);
            clock_tens.set(numbers[0]);
        } else {
            clock_ones.set(numbers[time%10]);
            clock_tens.set(numbers[(time %100) / 10]);
        }

        clock_tens.setPosition(5.35f, 5.6f);
        clock_ones.setPosition(5.55f, 5.6f);
    }

    private void update_score(int numCats, int numCoins) {
        this.num_cats = numCats;
        this.num_coins = numCoins;
    
        if (num_coins < 10) {
            coins_ones.set(numbers[numCoins%10]);
            coins_ones.setPosition(4.35f, 5.6f);
        } else {
            coins_ones.set(numbers[numCoins%10]);
            coins_ones.setPosition(4.55f, 5.6f);
            coins_tens.set(numbers[(numCoins %100) / 10]);
            coins_tens.setPosition(4.35f, 5.6f);
        }
    
        if (num_cats < 10) {
            cathead_ones.set(numbers[num_cats%10]);
            cathead_ones.setPosition(3.35f, 5.6f);
        } else {
            cathead_ones.set(numbers[num_cats%10]);
            cathead_ones.setPosition(3.55f, 5.6f);
            cathead_tens.set(numbers[(num_cats %100) / 10]);
            cathead_tens.setPosition(3.35f, 5.6f);
        }
    }
}
