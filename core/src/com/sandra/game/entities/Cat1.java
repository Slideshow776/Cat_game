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
import com.badlogic.gdx.utils.TimeUtils;
import com.sandra.game.utils.Assets;
import com.sandra.game.utils.Constants;
import com.sandra.game.utils.Utils;

public class Cat1 extends Entity {
    private World b2d_world;
    private float animation_start_time;

    public Cat1(Vector2 position, World b2d_world) {
        render_position = position;
        velocity = new Vector2(0, 0);
        id = UUID.randomUUID().toString();
        animation_start_time = TimeUtils.nanoTime();
        
        this.b2d_world = b2d_world;
        init_body();        
    }

    public void render(SpriteBatch batch) {
        float animation_time_seconds = Utils.secondsSince(animation_start_time);
        TextureRegion region = Assets.instance.cat1Assets.cat1_animation.getKeyFrame(animation_time_seconds);
        Utils.drawTextureRegion(batch, region, render_position); 
    }

    public void update(float delta) {
        if (body.getUserData() == "collision") {
            body.setUserData(Constants.CAT1_SPRITE_1);
            velocity.x = velocity.y = 0;
        }
        render_position.set(
            body.getPosition().x - Constants.CAT1_PIXEL_WIDTH / 2 / Constants.PPM,
            body.getPosition().y - Constants.CAT1_PIXEL_HEIGHT / 2 / Constants.PPM
        );
    }

    public void dispose() {b2d_world.destroyBody(body);}

    private void init_body() {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyType.DynamicBody;
        bdef.fixedRotation = true;
        bdef.position.set(
            (render_position.x + (Constants.CAT1_PIXEL_WIDTH / Constants.PPM) / 2),
            (render_position.y + (Constants.CAT1_PIXEL_HEIGHT / Constants.PPM) / 2)
        );

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.CAT1_HALF_WIDTH, Constants.CAT1_HALF_HEIGHT);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = Constants.CAT1_DENSITY;
        //fdef.friction = 50f;
        fdef.restitution = Constants.CAT1_RESTITUTION;
		fdef.filter.categoryBits = Constants.B2D_BIT_CAT1S;
        fdef.filter.maskBits = Constants.B2D_BIT_WORLD | Constants.B2D_BIT_CAT1S | 
            Constants.B2D_BIT_HOLE | Constants.B2D_BIT_COINS | Constants.B2D_YARN_BALLS;

		this.body = b2d_world.createBody(bdef);
        this.body.setLinearDamping(Constants.ENTITIES_LINEAR_DAMPING);
        this.body.createFixture(fdef).setUserData(Constants.CAT1_SPRITE_1);
    }    
}
    