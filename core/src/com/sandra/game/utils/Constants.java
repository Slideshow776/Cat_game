package com.sandra.game.utils;

import com.badlogic.gdx.math.Vector2;

public class Constants {
    // System
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 592;
    public static final String TEXTURE_ATLAS = "images/cat_game.pack.atlas";
    
    // Box2d
    public static final float PPM = 100;
    public static final boolean B2D_DEBUGGING = true;
    public static final float B2D_TIMESTEP = 1 / 30f;
    public static final int B2D_VELOCITY_ITERATIONS = 8;
    public static final int B2D_POSITION_ITERATIONS = 3;
    public static final float B2D_WORLD_BOUNDS_OFFSET = 20f;
    public static final String B2D_IDENTITY = "world";

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
    public static final String CAT1_SPRITE = "cat1";
    public static final Vector2 CAT1_CENTER = new Vector2(0, 0);
    public static final float CAT1_MOVE_SPEED = 50f;
    public static final float CAT1_PIXEL_WIDTH = 27f;
    public static final float CAT1_PIXEL_HEIGHT = 21f;
    public static final float CAT1_DENSITY = 1.0f;
    public static final float CAT1_RESTITUTION = .1f;
    public static final float CAT1_HALF_WIDTH = 13f / PPM;    // cat length = .46m, from bird's perspective
    public static final float CAT1_HALF_HEIGHT = 11f / PPM; // cat width = .25m, from bird's perspective

    // Yarn ball
    public static final String YARN_BALL_SPRITE_1 = "yarn_ball1";
    public static final String YARN_BALL_SPRITE_2 = "yarn_ball2";
    public static final String YARN_BALL_SPRITE_3 = "yarn_ball3";
    public static final String YARN_BALL_SPRITE_4 = "yarn_ball4";
    public static final Vector2 YARN_BALL_CENTER = new Vector2(0, 0);
    public static final float YARN_BALL_MOVE_SPEED = 50f;
    public static final float YARN_BALL_PIXEL_WIDTH = 64f;
    public static final float YARN_BALL_PIXEL_HEIGHT = 32f;
    public static final float YARN_BALL_DENSITY = .65f;
    public static final float YARN_BALL_RESTITUTION = 1f;
    public static final float YARN_BALL_RADIUS = 14f / PPM;
    public static final float YARN_BALL_LOOP_DURATION = .15f;
    public static final float YARN_BALL_VELOCITY = .05f;

    // Hole
    public static final String HOLE_SPRITE = "hole";
    public static final Vector2 HOLE_CENTER = new Vector2(0, 0);
    public static final float HOLE_PIXEL_WIDTH = 30;
    public static final float HOLE_PIXEL_HEIGHT = 20;
    public static final float HOLE_HALF_WIDTH = 4.5f / PPM;
    public static final float HOLE_HALF_HEIGHT = 3f / PPM;}
