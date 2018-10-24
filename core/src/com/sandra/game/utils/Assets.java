package com.sandra.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
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
    public MenuAssets menuAssets;
    public ThwomperAssets thwomperAssets;
    public ShadowAssets shadowAssets;
    public BloodAssets bloodAssets;
    public HUDAssets hudAssets;
    public LavaBubbleBurstAssets lavaBubbleBurstAssets;
    public SawBlade sawBladeAssets;

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
        menuAssets = new MenuAssets(atlas);
        thwomperAssets = new ThwomperAssets(atlas);
        shadowAssets = new ShadowAssets(atlas);
        bloodAssets = new BloodAssets(atlas);
        hudAssets = new HUDAssets(atlas);
        lavaBubbleBurstAssets = new LavaBubbleBurstAssets(atlas);
        sawBladeAssets = new SawBlade(atlas);
    }

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error(TAG, "Could not load asset: " + asset.fileName, throwable);
	}

	@Override
	public void dispose() {
        assetManager.dispose();
    }

    public class SawBlade {
        public final Animation<TextureRegion> saw_blade_animation;

        public SawBlade(TextureAtlas atlas) {
            Array<TextureRegion> saw_blade_frames = new Array<TextureRegion>();
            saw_blade_frames.add(atlas.findRegion(Constants.SAW_BLADE1));
            saw_blade_frames.add(atlas.findRegion(Constants.SAW_BLADE2));
            saw_blade_frames.add(atlas.findRegion(Constants.SAW_BLADE3));
            saw_blade_frames.add(atlas.findRegion(Constants.SAW_BLADE4));
            saw_blade_frames.add(atlas.findRegion(Constants.SAW_BLADE5));
            saw_blade_frames.add(atlas.findRegion(Constants.SAW_BLADE6));
            saw_blade_frames.add(atlas.findRegion(Constants.SAW_BLADE7));
            saw_blade_frames.add(atlas.findRegion(Constants.SAW_BLADE8));
            saw_blade_frames.add(atlas.findRegion(Constants.SAW_BLADE9));
            saw_blade_frames.add(atlas.findRegion(Constants.SAW_BLADE10));
            saw_blade_frames.add(atlas.findRegion(Constants.SAW_BLADE11));
            saw_blade_frames.add(atlas.findRegion(Constants.SAW_BLADE12));
            saw_blade_animation = new Animation<TextureRegion>(Constants.SAW_BLADE_LOOP_DURATION, saw_blade_frames, PlayMode.LOOP);
        }
    }

    public class HUDAssets {
        public final TextureRegion _x;
        public final TextureRegion _0;
        public final TextureRegion _1;
        public final TextureRegion _2;
        public final TextureRegion _3;
        public final TextureRegion _4;
        public final TextureRegion _5;
        public final TextureRegion _6;
        public final TextureRegion _7;
        public final TextureRegion _8;
        public final TextureRegion _9;
        public final TextureRegion btn_pause;
        public final TextureRegion btn_pause_pressed;
        public final TextureRegion btn_return;

        public HUDAssets(TextureAtlas atlas) {
            _x = new TextureRegion(atlas.findRegion(Constants._x));
            _0 = new TextureRegion(atlas.findRegion(Constants._0));
            _1 = new TextureRegion(atlas.findRegion(Constants._1));
            _2 = new TextureRegion(atlas.findRegion(Constants._2));
            _3 = new TextureRegion(atlas.findRegion(Constants._3));
            _4 = new TextureRegion(atlas.findRegion(Constants._4));
            _5 = new TextureRegion(atlas.findRegion(Constants._5));
            _6 = new TextureRegion(atlas.findRegion(Constants._6));
            _7 = new TextureRegion(atlas.findRegion(Constants._7));
            _8 = new TextureRegion(atlas.findRegion(Constants._8));
            _9 = new TextureRegion(atlas.findRegion(Constants._9));
            
            btn_pause = new TextureRegion(atlas.findRegion(Constants.BTN_PAUSE));
            btn_pause_pressed = new TextureRegion(atlas.findRegion(Constants.BTN_PAUSE_PRESSED));
            btn_return = new TextureRegion(atlas.findRegion(Constants.BTN_RETURN));
        }
    }

    public class DustAssets {
        public final Animation<TextureRegion> dust_animation;
        public final Animation<TextureRegion> grey_dust_animation;

        public DustAssets(TextureAtlas atlas) {
            Array<TextureRegion> dust_frames = new Array<TextureRegion>();
            dust_frames.add(atlas.findRegion(Constants.DUST_SPRITE_1));
            dust_frames.add(atlas.findRegion(Constants.DUST_SPRITE_2));
            dust_frames.add(atlas.findRegion(Constants.DUST_SPRITE_3));
            dust_frames.add(atlas.findRegion(Constants.DUST_SPRITE_4));

            dust_animation = new Animation<TextureRegion>(Constants.DUST_LOOP_DURATION, dust_frames, PlayMode.NORMAL);
        
            Array<TextureRegion> grey_dust_frames = new Array<TextureRegion>();
            grey_dust_frames.add(atlas.findRegion(Constants.GREY_DUST_SPRITE_1));
            grey_dust_frames.add(atlas.findRegion(Constants.GREY_DUST_SPRITE_2));
            grey_dust_frames.add(atlas.findRegion(Constants.GREY_DUST_SPRITE_3));
            grey_dust_frames.add(atlas.findRegion(Constants.GREY_DUST_SPRITE_4));

            grey_dust_animation = new Animation<TextureRegion>(Constants.DUST_LOOP_DURATION, grey_dust_frames, PlayMode.NORMAL);
        }
    }

    public class MenuAssets {
        // public final AtlasRegion button;
        public final TextureRegion menu_title;
        public final TextureRegion button_1_1;
        public final TextureRegion button_1_2;
        public final TextureRegion button_1_3;
        public final TextureRegion button_2_1;
        public final TextureRegion star_background1;
        // public final AtlasRegion button_selected;

        public MenuAssets(TextureAtlas atlas) {
            menu_title = atlas.findRegion(Constants.MENU_TITLE);
            button_1_1 = atlas.findRegion(Constants.BUTTON_1_1);
            button_1_2 = atlas.findRegion(Constants.BUTTON_1_2);
            button_1_3 = atlas.findRegion(Constants.BUTTON_1_3);
            button_2_1 = atlas.findRegion(Constants.BUTTON_2_1);
            star_background1 = atlas.findRegion(Constants.STAR_BACKGROUND1);
            // button_selected = atlas.findRegion(Constants.BUTTON_SELECTED);
        }
    }

    public class BloodAssets {
        public final TextureRegion bloodSplatter1;
        public final TextureRegion bloodDrag1;

        public BloodAssets(TextureAtlas atlas) {
            bloodSplatter1 = new TextureRegion(atlas.findRegion(Constants.BLOOD_SPLATTER1));
            bloodDrag1 = new TextureRegion(atlas.findRegion(Constants.BLOOD_DRAG1));
        }
    }

    public class LavaBubbleBurstAssets {
		public final Animation<TextureRegion> lava_bubble_burst_animation;

        public LavaBubbleBurstAssets(TextureAtlas atlas) {
            Array<TextureRegion> lava_bubble_burst_frames = new Array<TextureRegion>();
            lava_bubble_burst_frames.add(atlas.findRegion(Constants.LAVA_BUBBLE_BURST1));
            lava_bubble_burst_frames.add(atlas.findRegion(Constants.LAVA_BUBBLE_BURST2));
            lava_bubble_burst_frames.add(atlas.findRegion(Constants.LAVA_BUBBLE_BURST3));
            lava_bubble_burst_frames.add(atlas.findRegion(Constants.LAVA_BUBBLE_BURST4));
            lava_bubble_burst_frames.add(atlas.findRegion(Constants.LAVA_BUBBLE_BURST5));
            lava_bubble_burst_frames.add(atlas.findRegion(Constants.LAVA_BUBBLE_BURST6));
            lava_bubble_burst_frames.add(atlas.findRegion(Constants.LAVA_BUBBLE_BURST7));
            lava_bubble_burst_animation = new Animation<TextureRegion>(
                Constants.LAVA_BUBBLE_BURST_LOOP_DURATION,
                lava_bubble_burst_frames,
                PlayMode.NORMAL
            );
        }
    }

    public class ShadowAssets {
        public final Animation<TextureRegion> shadow_growing_animation;
        public final Animation<TextureRegion> shadow_shrinking_animation;
        public final TextureRegion smallest;
        public final TextureRegion largest;

        public ShadowAssets(TextureAtlas atlas) {
            Array<TextureRegion> shadow_frames = new Array<TextureRegion>();
            shadow_frames.add(atlas.findRegion(Constants.SHADOW_SPRITE3));
            shadow_frames.add(atlas.findRegion(Constants.SHADOW_SPRITE3));
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
        public final Animation<TextureRegion> cat1_sleeping_animation;
        public final TextureRegion dead1;
        public final TextureRegion dead2;
        public final TextureRegion dead3;
        public final TextureRegion cathead;

        public Cat1Assets(TextureAtlas atlas) {
            dead1 = new TextureRegion(atlas.findRegion(Constants.CAT1_DEAD_1));
            dead2 = new TextureRegion(atlas.findRegion(Constants.CAT1_DEAD_2));
            dead3 = new TextureRegion(atlas.findRegion(Constants.CAT1_DEAD_3));
            cathead = new TextureRegion(atlas.findRegion(Constants.CAT1_HEAD));

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
            
            Array<TextureRegion> cat1_sleeping_frames = new Array<TextureRegion>();
            cat1_sleeping_frames.add(atlas.findRegion(Constants.CAT1_SLEEP2));
            cat1_sleeping_frames.add(atlas.findRegion(Constants.CAT1_SLEEP1));

            cat1_sleeping_animation = new Animation<TextureRegion>(Constants.CAT1_SLEEP_LOOP_DURATION, cat1_sleeping_frames, PlayMode.LOOP);
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
        public final TextureRegion coin;

        public CoinAssets(TextureAtlas atlas) {
            Array<TextureRegion> coin_frames = new Array<TextureRegion>();
            coin_frames.add(atlas.findRegion(Constants.COIN_SPRITE_1));
            coin_frames.add(atlas.findRegion(Constants.COIN_SPRITE_2));
            coin_frames.add(atlas.findRegion(Constants.COIN_SPRITE_3));
            coin_frames.add(atlas.findRegion(Constants.COIN_SPRITE_4));
            
            coin_animation = new Animation<TextureRegion>(Constants.COIN_LOOP_DURATION, coin_frames, PlayMode.LOOP_PINGPONG);
            coin = new TextureRegion(atlas.findRegion(Constants.COIN_SPRITE_1));
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
