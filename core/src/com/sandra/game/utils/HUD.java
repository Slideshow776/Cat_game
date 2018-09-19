package com.sandra.game.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD {    
    private Sprite coin_image;
    private Sprite cathead_image;

    private Sprite[] numbers; // Holds numbers 0-9
    private Sprite cathead_ones, cathead_tens;
    private Sprite coins_ones, coins_tens;
    private int num_coins, num_cats;
    private Sprite x_cat, x_coins;

    public HUD(OrthographicCamera camera) {
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
    }        

    public void render(SpriteBatch batch) {
        cathead_image.draw(batch);
        coin_image.draw(batch);

        cathead_ones.draw(batch);
        x_cat.draw(batch);
        coins_ones.draw(batch);
        x_coins.draw(batch);

        if (num_cats >= 10) cathead_tens.draw(batch);
        if (num_coins >= 10) coins_tens.draw(batch);
    }

    public void update(float delta, int numCats, int numCoins) {
        this.num_cats = numCats;
        this.num_coins = numCoins;
        if (numCats < 10) {
            cathead_ones.set(numbers[num_cats]);
            cathead_ones.setPosition(3.35f, 5.6f);
        } else {
            cathead_tens.setPosition(3.35f, 5.6f);
            cathead_ones.setPosition(3.55f, 5.6f);
        }

        if (numCoins < 10) {
            coins_ones.setPosition(4.35f, 5.6f);
        } else {
            coins_tens.setPosition(4.35f, 5.6f);
            coins_ones.setPosition(4.55f, 5.6f);
        }
    }

    public void dispose() {}
}
