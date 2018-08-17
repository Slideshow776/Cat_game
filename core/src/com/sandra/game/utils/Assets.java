package com.sandra.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();

    public Cat1Assets cat1Assets;
    public HoleAssets holeAssets;
    public CoinAssets coinAssets;
    public YarnBallAssets yarnBallAssets;
    public ButtonsAssets buttonsAssets;

    private AssetManager assetManager;

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);
        cat1Assets = new Cat1Assets(atlas);
        holeAssets = new HoleAssets(atlas);
        coinAssets = new CoinAssets(atlas);
        yarnBallAssets = new YarnBallAssets(atlas);
        buttonsAssets = new ButtonsAssets(atlas);
    }

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error(TAG, "Could not load asset: " + asset.fileName, throwable);
	}

	@Override
	public void dispose() {
        assetManager.dispose();
    }

    public class ButtonsAssets {
        public final AtlasRegion button;
        //public final AtlasRegion button_selected;

        public ButtonsAssets(TextureAtlas atlas) {
            button = atlas.findRegion(Constants.BUTTON);
            //button_selected = atlas.findRegion(Constants.BUTTON_SELECTED);
        }
    }

    public class Cat1Assets {
        public final Animation<TextureRegion> cat1_idle_animation;
        public final Animation<TextureRegion> cat1_sliding_animation;
        public final Animation<TextureRegion> cat1_swimming_animation;

        public Cat1Assets(TextureAtlas atlas) {
            Array<TextureRegion> cat1_idle_frames = new Array<TextureRegion>();
            cat1_idle_frames.add(atlas.findRegion(Constants.CAT1_IDLE_SPRITE_1));
            cat1_idle_frames.add(atlas.findRegion(Constants.CAT1_IDLE_SPRITE_2));
            cat1_idle_frames.add(atlas.findRegion(Constants.CAT1_IDLE_SPRITE_3));
            cat1_idle_frames.add(atlas.findRegion(Constants.CAT1_IDLE_SPRITE_4));
            cat1_idle_frames.add(atlas.findRegion(Constants.CAT1_IDLE_SPRITE_5));
            cat1_idle_animation = new Animation<TextureRegion>(Constants.CAT1_LOOP_DURATION, cat1_idle_frames, PlayMode.LOOP_PINGPONG);
            
            Array<TextureRegion> cat1_sliding_frames = new Array<TextureRegion>();
            cat1_sliding_frames.add(atlas.findRegion(Constants.CAT1_SLIDE_SPRITE_1));
            cat1_sliding_frames.add(atlas.findRegion(Constants.CAT1_SLIDE_SPRITE_2));
            cat1_sliding_frames.add(atlas.findRegion(Constants.CAT1_SLIDE_SPRITE_3));
            cat1_sliding_frames.add(atlas.findRegion(Constants.CAT1_SLIDE_SPRITE_4));
            cat1_sliding_frames.add(atlas.findRegion(Constants.CAT1_SLIDE_SPRITE_4));
            cat1_sliding_frames.add(atlas.findRegion(Constants.CAT1_SLIDE_SPRITE_4));
            cat1_sliding_frames.add(atlas.findRegion(Constants.CAT1_SLIDE_SPRITE_5));
            cat1_sliding_frames.add(atlas.findRegion(Constants.CAT1_SLIDE_SPRITE_6));
            cat1_sliding_frames.add(atlas.findRegion(Constants.CAT1_SLIDE_SPRITE_7));
            cat1_sliding_animation = new Animation<TextureRegion>(Constants.CAT1_LOOP_DURATION, cat1_sliding_frames, PlayMode.LOOP_PINGPONG);

            Array<TextureRegion> cat1_swimming_frames = new Array<TextureRegion>();
            cat1_swimming_frames.add(atlas.findRegion(Constants.CAT1_SWIMMING_SPRITE_1));
            cat1_swimming_frames.add(atlas.findRegion(Constants.CAT1_SWIMMING_SPRITE_2));
            cat1_swimming_frames.add(atlas.findRegion(Constants.CAT1_SWIMMING_SPRITE_3));
            cat1_swimming_frames.add(atlas.findRegion(Constants.CAT1_SWIMMING_SPRITE_4));
            cat1_swimming_frames.add(atlas.findRegion(Constants.CAT1_SWIMMING_SPRITE_5));
            cat1_swimming_frames.add(atlas.findRegion(Constants.CAT1_SWIMMING_SPRITE_6));
            cat1_swimming_frames.add(atlas.findRegion(Constants.CAT1_SWIMMING_SPRITE_7));
            cat1_swimming_frames.add(atlas.findRegion(Constants.CAT1_SWIMMING_SPRITE_8));
            cat1_swimming_frames.add(atlas.findRegion(Constants.CAT1_SWIMMING_SPRITE_9));
            cat1_swimming_animation = new Animation<TextureRegion>(Constants.CAT1_LOOP_DURATION, cat1_swimming_frames, PlayMode.LOOP_PINGPONG);
        }
    }

    public class YarnBallAssets {
        public final Animation<TextureRegion> yarn_ball_animation;

        public YarnBallAssets(TextureAtlas atlas) {
            Array<TextureRegion> yarn_ball_frames = new Array<TextureRegion>();
            yarn_ball_frames.add(atlas.findRegion(Constants.YARN_BALL_SPRITE_1));
            yarn_ball_frames.add(atlas.findRegion(Constants.YARN_BALL_SPRITE_2));
            yarn_ball_frames.add(atlas.findRegion(Constants.YARN_BALL_SPRITE_3));
            yarn_ball_frames.add(atlas.findRegion(Constants.YARN_BALL_SPRITE_4));

            yarn_ball_animation = new Animation<TextureRegion>(Constants.YARN_BALL_LOOP_DURATION, yarn_ball_frames, PlayMode.LOOP);
        }
    }

    public class CoinAssets {
        public final Animation<TextureRegion> coin_animation;

        public CoinAssets(TextureAtlas atlas) {
            Array<TextureRegion> coin_frames = new Array<TextureRegion>();
            coin_frames.add(atlas.findRegion(Constants.COIN_SPRITE_1));
            coin_frames.add(atlas.findRegion(Constants.COIN_SPRITE_2));
            coin_frames.add(atlas.findRegion(Constants.COIN_SPRITE_3));
            coin_frames.add(atlas.findRegion(Constants.COIN_SPRITE_4));
            
            coin_animation = new Animation<TextureRegion>(Constants.COIN_LOOP_DURATION, coin_frames, PlayMode.LOOP_PINGPONG);
        }
    }

    public class HoleAssets {
        public final AtlasRegion hole;

        public HoleAssets(TextureAtlas atlas) {
            hole = atlas.findRegion(Constants.HOLE_SPRITE);
        }
    }
}
