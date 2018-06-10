package com.sandra.game.utils;

import com.badlogic.gdx.math.Vector2;

public class Constants {
    // System
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    public static final String TEXTURE_ATLAS = "images/cat_game.pack.atlas";
    
    // Box2d
    public static final int PPM = 100;
    public static final boolean B2D_DEBUGGING = true;
    public static final float B2D_TIMESTEP = 1 / 30f;
    public static final int B2D_VELOCITY_ITERATIONS = 8;
    public static final int B2D_POSITION_ITERATIONS = 3;
    public static final float B2D_WORLD_BOUNDS_OFFSET = 20f;

    public static final short B2D_BIT_WORLD = 2;
    public static final short B2D_BIT_CAT1S = 4;
    public static final short B2D_BIT_HOLE = 8;
    
    // Entities
    public static final float ENTETIES_ACCELERATION = .002f;
    public static final float ENTETIES_MAX_VELOCITY = .004f;
    public static final float ENTITIES_LINEAR_DAMPING = 1f;

    // Cat1
    public static final String CAT1_SPRITE = "cat1";
    public static final Vector2 CAT1_CENTER = new Vector2(0, 0);
    public static final float CAT1_MOVE_SPEED = 50f;
    public static final float CAT1_PIXEL_WIDTH = 27f;
    public static final float CAT1_PIXEL_HEIGHT = 21f;
    public static final float CAT1_DENSITY = .65f;
    public static final float CAT1_RESTITUTION = .1f;
    public static final float CAT1_HALF_WIDTH = 23f / PPM;    // cat length = .46m, from bird's perspective
    public static final float CAT1_HALF_HEIGHT = 12.5f / PPM; // cat width = .25m, from bird's perspective

    // Hole
    public static final String HOLE_SPRITE = "hole";
    public static final Vector2 HOLE_CENTER = new Vector2(0, 0);
    public static final float HOLE_PIXEL_WIDTH = 30;
    public static final float HOLE_PIXEL_HEIGHT = 20;
    public static final float HOLE_HALF_WIDTH = 4.5f / PPM;
    public static final float HOLE_HALF_HEIGHT = 3f / PPM;}
