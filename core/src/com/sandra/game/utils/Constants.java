package com.sandra.game.utils;

import com.badlogic.gdx.math.Vector2;

public class Constants {
    // System
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 592;
    public static final String TEXTURE_ATLAS = "images/cat_game.pack.atlas";

    // Box2d
    public static final float PPM = 100;
    public static final boolean B2D_DEBUGGING = false;
    public static final float B2D_TIMESTEP = 1 / 30f;
    public static final int B2D_VELOCITY_ITERATIONS = 8;
    public static final int B2D_POSITION_ITERATIONS = 3;
    public static final float B2D_WORLD_BOUNDS_OFFSET = 20f;
    public static final String B2D_WORLD = "world";
    public static final String B2D_LAND_ZONE = "land_zone";

    public static final short B2D_BIT_WORLD = 2;
    public static final short B2D_BIT_CAT1S = 4;
    public static final short B2D_BIT_HOLE = 8;
    public static final short B2D_BIT_COINS = 16;
    public static final short B2D_YARN_BALLS = 32;

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
    
    public static final float CAT1_SCALE = 1.3f;
    public static final float CAT1_LOOP_DURATION = .15f;
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
    public static final float DUST_GENERATION_RATIO = .13f;
    public static final float DUST_LOOP_DURATION = .12f;
    public static final float DUST_DURATION = DUST_LOOP_DURATION * DUST_NUMBER_OF_FRAMES;

    // Buttons
    public static String BUTTON = "level1_1_btn";
    public static String BUTTON_SELECTED  = "level1_1_btn_selected";
}
