package com.sandra.game.utils;

import com.badlogic.gdx.math.Vector2;

public class Constants {
    // System
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 592;
    public static final String TEXTURE_ATLAS = "images/cat_game.pack.atlas";

    // Level
    public static final int TIME = 99;

    // Box2d
    public static final float PPM = 100;
    public static final boolean B2D_DEBUGGING = true;
    public static final float B2D_TIMESTEP = 1 / 30f;
    public static final int B2D_VELOCITY_ITERATIONS = 8;
    public static final int B2D_POSITION_ITERATIONS = 4;
    public static final float B2D_WORLD_BOUNDS_OFFSET = 20f;
    public static final String B2D_WORLD = "world";
    public static final String B2D_LAND_ZONE = "land_zone";
    public static final String B2D_LAVA_ZONE = "lava_zone";

    public static final short B2D_BIT_WORLD = 2;
    public static final short B2D_BIT_CAT1S = 4;
    public static final short B2D_BIT_HOLE = 8;
    public static final short B2D_BIT_COINS = 16;
    public static final short B2D_YARN_BALLS = 32;
    public static final short B2D_THWOMPER = 64;
    public static final short B2D_SAW_BLADE = 128;

    // Entities
    public static final float ENTETIES_ACCELERATION = .002f;
    public static final float ENTETIES_MAX_VELOCITY = .004f;
    public static final float ENTITIES_LINEAR_DAMPING = 1f;

    // Coin
    public static final String COIN_SPRITE_1 = "coin1";
    public static final String COIN_SPRITE_2 = "coin2";
    public static final String COIN_SPRITE_3 = "coin3";
    public static final String COIN_SPRITE_4 = "coin4";
    public static final float COIN_LOOP_DURATION = .15f;
    public static final Vector2 COIN_CENTER = new Vector2(0, 0);
    public static final float COIN_RADIUS = 14f / PPM;
    public static final float COIN_PIXEL_WIDTH = 32;
    public static final float COIN_PIXEL_HEIGHT = 32;

    // Cat1
    public static final String CAT1_IDLE_SPRITE_1 = "cat11";
    public static final String CAT1_IDLE_SPRITE_2 = "cat12";
    public static final String CAT1_IDLE_SPRITE_3 = "cat13";
    public static final String CAT1_IDLE_SPRITE_4 = "cat14";
    public static final String CAT1_IDLE_SPRITE_5 = "cat15";
    public static final String CAT1_SLIDE_SPRITE_1 = "cat1Slide1";
    public static final String CAT1_SLIDE_SPRITE_2 = "cat1Slide2";
    public static final String CAT1_SLIDE_SPRITE_3 = "cat1Slide3";
    public static final String CAT1_SLIDE_SPRITE_4 = "cat1Slide4";
    public static final String CAT1_SLIDE_SPRITE_5 = "cat1Slide5";
    public static final String CAT1_SLIDE_SPRITE_6 = "cat1Slide6";
    public static final String CAT1_SLIDE_SPRITE_7 = "cat1Slide7";
    public static final String CAT1_SWIMMING_SPRITE_1 = "cat1Swim1";
    public static final String CAT1_SWIMMING_SPRITE_2 = "cat1Swim2";
    public static final String CAT1_SWIMMING_SPRITE_3 = "cat1Swim3";
    public static final String CAT1_SWIMMING_SPRITE_4 = "cat1Swim4";
    public static final String CAT1_SWIMMING_SPRITE_5 = "cat1Swim5";
    public static final String CAT1_SWIMMING_SPRITE_6 = "cat1Swim6";
    public static final String CAT1_SWIMMING_SPRITE_7 = "cat1Swim7";
    public static final String CAT1_SWIMMING_SPRITE_8 = "cat1Swim8";
    public static final String CAT1_SWIMMING_SPRITE_9 = "cat1Swim9";
    public static final String CAT1_DEAD_1 = "cat1Dead1";
    public static final String CAT1_DEAD_2 = "cat1Dead2";
    public static final String CAT1_DEAD_3 = "cat1Dead3";
    public static final String CAT1_HEAD = "cat1Head";
    public static final String CAT1_SLEEP1 = "cat1_sleep1";
    public static final String CAT1_SLEEP2 = "cat1_sleep2";
    
    public static final float CAT1_SCALE = 1.3f;
    public static final float CAT1_LOOP_DURATION = .15f;
    public static final float CAT1_SLEEP_LOOP_DURATION = 1.5f;
    public static final Vector2 CAT1_CENTER = new Vector2(0, 0);
    public static final float CAT1_MOVE_SPEED = 50f;
    public static final float CAT1_PIXEL_WIDTH = 21f;
    public static final float CAT1_PIXEL_HEIGHT = 25f;
    public static final float CAT1_DENSITY = 1.0f;
    public static final float CAT1_RESTITUTION = .15f;
    public static final float CAT1_HALF_WIDTH = 12f / PPM; // cat length = .46m, from bird's perspective
    public static final float CAT1_HALF_HEIGHT = 16f / PPM; // cat width = .25m, from bird's perspective

    // Yarn ball
    public static final String YARN_BALL_SPRITE_1 = "beach_ball1";
    public static final String YARN_BALL_SPRITE_2 = "beach_ball2";
    public static final String YARN_BALL_SPRITE_3 = "beach_ball3";
    public static final String YARN_BALL_SPRITE_4 = "beach_ball4";
    public static final String YARN_BALL_SPRITE_5 = "beach_ball5";
    public static final String YARN_BALL_SPRITE_6 = "beach_ball6";
    public static final Vector2 YARN_BALL_CENTER = new Vector2(0, 0);
    public static final float YARN_BALL_MOVE_SPEED = 50f;
    public static final float YARN_BALL_PIXEL_WIDTH = 32f;
    public static final float YARN_BALL_PIXEL_HEIGHT = 32f;
    public static final float YARN_BALL_DENSITY = .65f;
    public static final float YARN_BALL_RESTITUTION = 1f;
    public static final float YARN_BALL_RADIUS = 14f / PPM;
    public static final float YARN_BALL_LOOP_DURATION = .15f;
    public static final float YARN_BALL_VELOCITY = .041f;

    // Portal
    public static final float PORTAL_SCALE = .25f;
    public static final float PORTAL_PIXEL_WIDTH = 238 * PORTAL_SCALE;
    public static final float PORTAL_PIXEL_HEIGHT = 113 * PORTAL_SCALE;
    public static final float PORTAL_HALF_WIDTH = 6f / PPM;
    public static final float PORTAL_HALF_HEIGHT = 3f / PPM;
    public static final float PORTAL_LOOP_DURATION = .15f;
    public static final String PORTAL_SPRITE_1 = "portal1";
    public static final String PORTAL_SPRITE_2 = "portal2";
    public static final String PORTAL_SPRITE_3 = "portal3";
    public static final String PORTAL_SPRITE_4 = "portal4";
    public static final Vector2 PORTAL_CENTER = new Vector2(0, 0);

    // Dust
    public static final int DUST_NUMBER_OF_FRAMES = 4;
    public static final String DUST_SPRITE_1 = "dust1";
    public static final String DUST_SPRITE_2 = "dust2";
    public static final String DUST_SPRITE_3 = "dust3";
    public static final String DUST_SPRITE_4 = "dust4";
    public static final String GREY_DUST_SPRITE_1 = "grey_dust1";
    public static final String GREY_DUST_SPRITE_2 = "grey_dust2";
    public static final String GREY_DUST_SPRITE_3 = "grey_dust3";
    public static final String GREY_DUST_SPRITE_4 = "grey_dust4";
    public static final float DUST_GENERATION_RATIO = .13f;
    public static final float DUST_LOOP_DURATION = .12f;
    public static final float DUST_DURATION = DUST_LOOP_DURATION * DUST_NUMBER_OF_FRAMES;

    // Menu
    public static String MENU_TITLE = "game_title";
    public static String BUTTON_1_1 = "level1_1_btn";
    public static String BUTTON_1_2 = "level1_2_btn";
    public static String BUTTON_1_3 = "level1_3_btn";
    public static String BUTTON_2_1 = "level2_1_btn";
    public static String BUTTON_SELECTED  = "level1_1_btn_selected";

    // Thwomper
    public static String THWOMPER_IDLE_SPRITE1 = "thwomper_idle1";
    public static String THWOMPER_MOVING_SPRITE1 = "thwomper_moving1";
    public static String THWOMPER_FLOATING_SPRITE1 = "thwomper_floating1";
    public static String THWOMPER_FLOATING_SPRITE2 = "thwomper_floating2";
    public static String THWOMPER_FLOATING_SPRITE3 = "thwomper_floating3";
    public static final float THWOMPER_IDLE_LOOP_DURATION = .12f;
    public static final float THWOMPER_MOVING_LOOP_DURATION = .08f;
    public static final float THWOMPER_FLOATING_LOOP_DURATION = .5f;
    public static final float THWOMPER_PIXEL_WIDTH = 45f;
    public static final float THWOMPER_PIXEL_HEIGHT = 33f;
    public static final float THWOMPER_HALF_WIDTH = 14f / PPM;
    public static final float THWOMPER_HALF_HEIGHT = 10f / PPM;
    public static final float THWOMPER_MAX_HEIGHT_MOVE = 125;
    
    // Thwomper's shadow
    public static String SHADOW_SPRITE1 = "shadow1";
    public static String SHADOW_SPRITE2 = "shadow2";
    public static String SHADOW_SPRITE3 = "shadow3";
    public static String SHADOW_SPRITE4 = "shadow4";
    public static String SHADOW_SPRITE5 = "shadow5";
    public static String SHADOW_SPRITE6 = "shadow6";
    public static String SHADOW_SPRITE7 = "shadow7";
    public static String SHADOW_SPRITE8 = "shadow8";
    public static String SHADOW_SPRITE9 = "shadow9";
    public static String SHADOW_SPRITE10 = "shadow10";
    public static String SHADOW_SPRITE11 = "shadow11";
    public static String SHADOW_SPRITE12 = "shadow12";
    public static final float SHADOW_PIXEL_WIDTH = 27 / PPM;
    public static final float SHADOW_PIXEL_HEIGHT = 5 / PPM;
    public static final float SHADOW_LOOP_DURATION = .19f;

    // Blood
    public static String BLOOD_SPLATTER1 = "bloodSplatter1";
    public static String BLOOD_DRAG1 = "bloodDrag1";
    public static float BLOOD_GENERATION_RATIO = .26f;

    // HUD
    public static String _x = "x";
    public static String _0 = "0";
    public static String _1 = "1";
    public static String _2 = "2";
    public static String _3 = "3";
    public static String _4 = "4";
    public static String _5 = "5";
    public static String _6 = "6";
    public static String _7 = "7";
    public static String _8 = "8";
    public static String _9 = "9";
    
    public static String BTN_PAUSE = "btn_pause";
    public static String BTN_PAUSE_PRESSED = "btn_pause_pressed";
    public static String BTN_RETURN = "btn_return";
    public static String CLOCK = "clock";

    // Menu
    public static String STAR_BACKGROUND1 = "star_background1";

    // Lava bubble burst
    public static String LAVA_BUBBLE_BURST1 = "lava_bubble_burst1";
    public static String LAVA_BUBBLE_BURST2 = "lava_bubble_burst2";
    public static String LAVA_BUBBLE_BURST3 = "lava_bubble_burst3";
    public static String LAVA_BUBBLE_BURST4 = "lava_bubble_burst4";
    public static String LAVA_BUBBLE_BURST5 = "lava_bubble_burst5";
    public static String LAVA_BUBBLE_BURST6 = "lava_bubble_burst6";
    public static String LAVA_BUBBLE_BURST7 = "lava_bubble_burst7";
    public static int LAVA_BUBBLE_BURST_NUMBER_OF_FRAMES = 7;
    public static float LAVA_BUBBLE_BURST_LOOP_DURATION = .20f;
    public static float LAVA_BUBBLE_BURST_DURATION = LAVA_BUBBLE_BURST_LOOP_DURATION * LAVA_BUBBLE_BURST_NUMBER_OF_FRAMES;

    // Saw blade
    public static String SAW_BLADE1 = "saw_blade1";
    public static String SAW_BLADE2 = "saw_blade2";
    public static String SAW_BLADE3 = "saw_blade3";
    public static String SAW_BLADE4 = "saw_blade4";
    public static String SAW_BLADE5 = "saw_blade5";
    public static String SAW_BLADE6 = "saw_blade6";
    public static String SAW_BLADE7 = "saw_blade7";
    public static String SAW_BLADE8 = "saw_blade8";
    public static String SAW_BLADE9 = "saw_blade9";
    public static String SAW_BLADE10 = "saw_blade10";
    public static String SAW_BLADE11 = "saw_blade11";
    public static String SAW_BLADE12 = "saw_blade12";
    public static float SAW_BLADE_LOOP_DURATION = .02f;
    public static int SAW_BLADE_PIXEL_WIDTH = 27;
    public static int SAW_BLADE_PIXEL_HEIGHT = 14;
    public static float SAW_BLADE_HALF_WIDTH = 19f / PPM;
    public static float SAW_BLADE_HALF_HEIGHT = 9f / PPM;
}
