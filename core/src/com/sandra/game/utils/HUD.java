package com.sandra.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class HUD {

    private int numCats;
    private int numCoins;

	private TextureRegion coin_tex;
    private TextureRegion cathead_tex;
    
    private Image coin_image;
    private Image cathead_image;

    private Label.LabelStyle label1Style;
    BitmapFont myFont;
    Label label1;

    BitmapFont test;

    public HUD(int numCats, int numCoins) {
        this.numCats = numCats;
        this.numCoins = numCoins;

        coin_tex = Assets.instance.coinAssets.coin;
        coin_image = new Image(coin_tex);
        coin_image.setSize(100 / Constants.PPM, 100 / Constants.PPM);

        cathead_tex = Assets.instance.cat1Assets.cathead;
        cathead_image = new Image(cathead_tex);
        cathead_image.setSize(100 / Constants.PPM, 100 / Constants.PPM);

        label1Style = new Label.LabelStyle();
        myFont = new BitmapFont(Gdx.files.internal("bitmapfont/Amble-Regular-26.fnt"));
        label1Style.font = myFont;
        label1Style.fontColor = Color.RED;

        label1 = new Label("Title (BitmapFont)", label1Style);
        label1.setFontScale(.04f);
        label1.setSize(
            .00000001f / Constants.PPM,
            .00000001f / Constants.PPM
        );
        label1.setPosition(
            -4,
            4
        );

        test = new BitmapFont();
        test.getData().setScale(.125f, .125f);
    }

    public void render(SpriteBatch batch) {
        label1.draw(batch, 1);
        /* test.draw(
            batch,
            "BANANANA SPLITS! ZOMG!!!",
            0,
            4
        ); */
        // cathead_image.draw(batch, 1);
        // coin_image.draw(batch, 1);
    }

    public void dispose() {
        coin_tex.getTexture().dispose();
        cathead_tex.getTexture().dispose();
    }
}