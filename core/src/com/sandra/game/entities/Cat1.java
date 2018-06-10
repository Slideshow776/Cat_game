package com.sandra.game.entities;

import java.util.UUID;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.sandra.game.utils.Assets;
import com.sandra.game.utils.Constants;
import com.sandra.game.utils.Utils;

public class Cat1 extends Entity{

    private World b2d_world;

    public Cat1(Vector2 position, World b2d_world) {
        render_position = position;
        velocity = new Vector2(0, 0);
        this.b2d_world = b2d_world;
        set_body();
        collision = false;
        id = UUID.randomUUID().toString();
    }

    public void render(SpriteBatch batch) {
        final TextureRegion region = Assets.instance.cat1Assets.cat1;
        Utils.drawTextureRegion(batch, region, render_position, Constants.CAT1_CENTER);
    }

    public void update(float delta, boolean collision) {
        this.collision = collision;
    }

    public void dispose() {b2d_world.destroyBody(body);}

    private void set_body() {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyType.DynamicBody;
        bdef.position.set(
            (render_position.x + Constants.CAT1_PIXEL_WIDTH / 2) / Constants.PPM,
            (render_position.y + Constants.CAT1_PIXEL_HEIGHT / 2) / Constants.PPM
        );

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.CAT1_HALF_WIDTH, Constants.CAT1_HALF_HEIGHT);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = Constants.CAT1_DENSITY;
        //fdef.friction = 50f;
		fdef.filter.categoryBits = Constants.B2D_BIT_CAT1S;
		fdef.filter.maskBits = Constants.B2D_BIT_WORLD | Constants.B2D_BIT_CAT1S;

		this.body = b2d_world.createBody(bdef);
        this.body.setLinearDamping(Constants.ENTITIES_LINEAR_DAMPING);
        this.body.createFixture(fdef).setUserData(Constants.CAT1_SPRITE);
    }    
}
    