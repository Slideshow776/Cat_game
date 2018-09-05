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
    public PortalAssets portalAssets;
    public CoinAssets coinAssets;
    public YarnBallAssets yarnBallAssets;
    public DustAssets dustAssets;
    public ButtonsAssets buttonsAssets;
    public ThwomperAssets thwomperAssets;
    public ShadowAssets shadowAssets;

    private AssetManager assetManager;

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);
        cat1Assets = new Cat1Assets(atlas);
        portalAssets = new PortalAssets(atlas);
        coinAssets = new CoinAssets(atlas);
        dustAssets = new DustAssets(atlas);
        yarnBallAssets = new YarnBallAssets(atlas);
        buttonsAssets = new ButtonsAssets(atlas);
        thwomperAssets = new ThwomperAssets(atlas);
        shadowAssets = new ShadowAssets(atlas);
    }

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error(TAG, "Could not load asset: " + asset.fileName, throwable);
	}

	@Override
	public void dispose() {
        assetManager.dispose();
    }

    public class DustAssets {
        public final Animation<TextureRegion> dust_animation;

        public DustAssets(TextureAtlas atlas) {
            Array<TextureRegion> dust_frames = new Array<TextureRegion>();
            dust_frames.add(atlas.findRegion(Constants.DUST_SPRITE_1));
            dust_frames.add(atlas.findRegion(Constants.DUST_SPRITE_2));
            dust_frames.add(atlas.findRegion(Constants.DUST_SPRITE_3));
            dust_frames.add(atlas.findRegion(Constants.DUST_SPRITE_4));

            dust_animation = new Animation<TextureRegion>(Constants.DUST_LOOP_DURATION, dust_frames, PlayMode.NORMAL);
        }
    }

    public class ButtonsAssets {
        public final AtlasRegion button;
        //public final AtlasRegion button_selected;

        public ButtonsAssets(TextureAtlas atlas) {
            button = atlas.findRegion(Constants.BUTTON);
            //button_selected = atlas.findRegion(Constants.BUTTON_SELECTED);
        }
    }

    public class ShadowAssets {
        public final Animation<TextureRegion> shadow_growing_animation;
        public final Animation<TextureRegion> shadow_shrinking_animation;
        public final TextureRegion smallest;
        public final TextureRegion largest;

        public ShadowAssets(TextureAtlas atlas) {
            Array<TextureRegion> shadow_frames = new Array<TextureRegion>();
            shadow_frames.add(atlas.findRegion(Constants.SHADOW_SPRITE1));
            shadow_frames.add(atlas.findRegion(Constants.SHADOW_SPRITE2));
            shadow_frames.add(atlas.findRegion(Constants.SHADOW_SPRITE3));
            shadow_frames.add(atlas.findRegion(Constants.SHADOW_SPRITE4));
            shadow_frames.add(atlas.findRegion(Constants.SHADOW_SPRITE5));
            shadow_frames.add(atlas.findRegion(Constants.SHADOW_SPRITE6));
            shadow_frames.add(atlas.findRegion(Constants.SHADOW_SPRITE7));
            shadow_frames.add(atlas.findRegion(Constants.SHADOW_SPRITE8));
            shadow_frames.add(atlas.findRegion(Constants.SHADOW_SPRITE9));
            shadow_frames.add(atlas.findRegion(Constants.SHADOW_SPRITE10));
            shadow_frames.add(atlas.findRegion(Constants.SHADOW_SPRITE11));
            shadow_frames.add(atlas.findRegion(Constants.SHADOW_SPRITE12));

            smallest = new TextureRegion(atlas.findRegion(Constants.SHADOW_SPRITE1));
            largest = new TextureRegion(atlas.findRegion(Constants.SHADOW_SPRITE12));
            shadow_growing_animation = new Animation<TextureRegion>(Constants.SHADOW_LOOP_DURATION, shadow_frames, PlayMode.LOOP);
            shadow_shrinking_animation = new Animation<TextureRegion>(Constants.SHADOW_LOOP_DURATION, shadow_frames, PlayMode.LOOP_REVERSED);
        }
    }

    public class ThwomperAssets {
        public final Animation<TextureRegion> thwomper_idle_animation;
        public final Animation<TextureRegion> thwomper_moving_animation;
        public final Animation<TextureRegion> thwomper_attacking_animation;
        public final Animation<TextureRegion> thwomper_floating_animation;

        public ThwomperAssets(TextureAtlas atlas) {
            Array<TextureRegion> thwomper_idle_frames = new Array<TextureRegion>();
            thwomper_idle_frames.add(atlas.findRegion(Constants.THWOMPER_IDLE_SPRITE1));
            thwomper_idle_animation = new Animation<TextureRegion>(Constants.THWOMPER_IDLE_LOOP_DURATION, thwomper_idle_frames, PlayMode.LOOP);
            
            Array<TextureRegion> thwomper_moving_frames = new Array<TextureRegion>();
            thwomper_moving_frames.add(atlas.findRegion(Constants.THWOMPER_MOVING_SPRITE1));
            thwomper_moving_animation = new Animation<TextureRegion>(Constants.THWOMPER_MOVING_LOOP_DURATION, thwomper_moving_frames, PlayMode.NORMAL);
            thwomper_attacking_animation = new Animation<TextureRegion>(Constants.THWOMPER_MOVING_LOOP_DURATION, thwomper_moving_frames, PlayMode.REVERSED);
            
            Array<TextureRegion> thwomper_floating_frames = new Array<TextureRegion>();
            thwomper_floating_frames.add(atlas.findRegion(Constants.THWOMPER_FLOATING_SPRITE1));
            thwomper_floating_frames.add(atlas.findRegion(Constants.THWOMPER_FLOATING_SPRITE2));
            thwomper_floating_frames.add(atlas.findRegion(Constants.THWOMPER_FLOATING_SPRITE3));
            thwomper_floating_animation = new Animation<TextureRegion>(Constants.THWOMPER_FLOATING_LOOP_DURATION, thwomper_floating_frames, PlayMode.LOOP_RANDOM);
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
            yarn_ball_frames.add(atlas.findRegion(Constants.YARN_BALL_SPRITE_5));
            yarn_ball_frames.add(atlas.findRegion(Constants.YARN_BALL_SPRITE_6));

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

    public class PortalAssets {
        public final Animation<TextureRegion> portal_animation;

        public PortalAssets(TextureAtlas atlas) {
            Array<TextureRegion> portal_frames = new Array<TextureRegion>();
            portal_frames.add(atlas.findRegion(Constants.PORTAL_SPRITE_1));
            portal_frames.add(atlas.findRegion(Constants.PORTAL_SPRITE_2));
            portal_frames.add(atlas.findRegion(Constants.PORTAL_SPRITE_3));
            portal_frames.add(atlas.findRegion(Constants.PORTAL_SPRITE_4));
            
            portal_animation = new Animation<TextureRegion>(Constants.PORTAL_LOOP_DURATION, portal_frames, PlayMode.LOOP);
        }
    }
}
