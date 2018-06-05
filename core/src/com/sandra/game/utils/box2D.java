package com.sandra.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class box2D {

    World b2d_world;

    public box2D(World b2d_world) {
        this.b2d_world = b2d_world;
    }

    public void set_world_bounds() {

        // LOWER--------------------------------
		BodyDef bdef = new BodyDef();
        bdef.type = BodyType.StaticBody;
		bdef.position.set(
            (Constants.GAME_WIDTH / 2) / Constants.PPM,
            (-Constants.B2D_WORLD_BOUNDS_OFFSET) / Constants.PPM
        );

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(
            Constants.GAME_WIDTH / Constants.PPM,
            Constants.B2D_WORLD_BOUNDS_OFFSET / Constants.PPM
        );
        FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.filter.categoryBits = Constants.B2D_BIT_WORLD;   // BIT_WORLD;
		fdef.filter.maskBits = Constants.B2D_BIT_CAT1S;       // BIT_PLAYER;

		Body world_lower = b2d_world.createBody(bdef);
		world_lower.createFixture(fdef).setUserData("world");
        
        // Upper -------------------------------
		bdef.position.set(
            (Constants.GAME_WIDTH / 2) / Constants.PPM,
            (Constants.GAME_HEIGHT + Constants.B2D_WORLD_BOUNDS_OFFSET) / Constants.PPM
        );

        //shape.setAsBox(Gdx.graphics.getWidth(), Constants.B2D_WORLD_BOUNDS_OFFSET);
		fdef.shape = shape;
		fdef.filter.categoryBits = Constants.B2D_BIT_WORLD;
		fdef.filter.maskBits = Constants.B2D_BIT_CAT1S;

        Body world_upper = b2d_world.createBody(bdef);
        world_upper.createFixture(fdef).setUserData("world");
        
        // Left --------------------------------
        bdef.position.set(
            (-Constants.B2D_WORLD_BOUNDS_OFFSET) / Constants.PPM,
            (Constants.GAME_HEIGHT / 2) / Constants.PPM
        );

        shape.setAsBox(
            (Constants.B2D_WORLD_BOUNDS_OFFSET) / Constants.PPM,
            (Constants.GAME_HEIGHT) / Constants.PPM
        );
		fdef.shape = shape;
		fdef.filter.categoryBits = Constants.B2D_BIT_WORLD;
		fdef.filter.maskBits = Constants.B2D_BIT_CAT1S;

        Body world_left = b2d_world.createBody(bdef);
        world_left.createFixture(fdef).setUserData("world");

        // Right -------------------------------
        bdef.position.set(
            (Constants.GAME_WIDTH + Constants.B2D_WORLD_BOUNDS_OFFSET) / Constants.PPM,
            (Constants.GAME_HEIGHT / 2) / Constants.PPM
        );

        // bdef.position.set(Gdx.graphics.getWidth() + Constants.B2D_WORLD_BOUNDS_OFFSET / 2, Gdx.graphics.getHeight() / 2);

        //shape.setAsBox(Constants.B2D_WORLD_BOUNDS_OFFSET, Gdx.graphics.getHeight());
		fdef.shape = shape;
		fdef.filter.categoryBits = Constants.B2D_BIT_WORLD;
		fdef.filter.maskBits = Constants.B2D_BIT_CAT1S;

        Body world_right = b2d_world.createBody(bdef);
        world_right.createFixture(fdef).setUserData("world");
    }
}