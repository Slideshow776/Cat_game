package com.sandra.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.TimeUtils;
import com.sandra.game.utils.Utils;
import com.sandra.game.utils.Assets;
import com.sandra.game.utils.Constants;

public class Yarn_Ball extends Entity {
    private World b2d_world;
    private float animation_start_time;
    private Vector2 original_velocity;

    public Yarn_Ball(Vector2 position, World b2d_world) {
        render_position = position;
        velocity = new Vector2(.1f, .05f); // initial velocity should only be positive
        animation_start_time = TimeUtils.nanoTime();
        
        this.b2d_world = b2d_world;
        init_body();       

        body.applyLinearImpulse(velocity, body.getWorldCenter(), true);
        original_velocity = new Vector2(body.getLinearVelocity());
    }

    public void render(SpriteBatch batch) {
        float animation_time_seconds = Utils.secondsSince(animation_start_time);
        TextureRegion region = Assets.instance.yarnBallAssets.yarn_ball_animation.getKeyFrame(animation_time_seconds);
        Utils.drawTextureRegion(batch, region, render_position); 
    }

    public void update(float delta) {
        if (body.getUserData() == "collision") {
            body.setUserData(Constants.YARN_BALL_SPRITE_1);

            if (((body.getLinearVelocity().x < original_velocity.x) && body.getLinearVelocity().x >= 0) ||          // ++
                    (body.getLinearVelocity().y < original_velocity.y) && body.getLinearVelocity().y >= 0) {
                body.applyLinearImpulse(velocity, body.getWorldCenter(), true);
            } else if (((body.getLinearVelocity().x < original_velocity.x) && body.getLinearVelocity().x >= 0) ||   // +-
                    (body.getLinearVelocity().y > -original_velocity.y) && body.getLinearVelocity().y <= 0) {
                body.setLinearVelocity(0, 0);
                body.applyLinearImpulse(velocity.x, velocity.y*-1, body.getWorldCenter().x, body.getWorldCenter().y, true);
            } else if (((body.getLinearVelocity().x > -original_velocity.x) && body.getLinearVelocity().x <= 0) ||  // -+
                    (body.getLinearVelocity().y < original_velocity.y) && body.getLinearVelocity().y >= 0) {
                body.setLinearVelocity(0, 0);
                body.applyLinearImpulse(velocity.x*-1, velocity.y, body.getWorldCenter().x, body.getWorldCenter().y, true);
            } else if (((body.getLinearVelocity().x > -original_velocity.x) && body.getLinearVelocity().x <= 0) ||  // --
                    (body.getLinearVelocity().y > -original_velocity.y) && body.getLinearVelocity().y <= 0) {
                body.setLinearVelocity(0, 0);
                body.applyLinearImpulse(velocity.x*-1, velocity.y*-1, body.getWorldCenter().x, body.getWorldCenter().y, true);
            }
        }

        render_position.set(
            body.getPosition().x - Constants.YARN_BALL_PIXEL_WIDTH / 2 / 2 / Constants.PPM,
            body.getPosition().y - Constants.YARN_BALL_PIXEL_HEIGHT / 2 / Constants.PPM
        );
    }

    public void dispose() {}

    private void init_body() {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyType.DynamicBody;
        bdef.position.set(
            (render_position.x + (Constants.YARN_BALL_PIXEL_WIDTH / 2 / Constants.PPM) / 2),
            (render_position.y + (Constants.YARN_BALL_PIXEL_HEIGHT / Constants.PPM) / 2)
        );

        CircleShape shape = new CircleShape();
        shape.setRadius(Constants.YARN_BALL_RADIUS);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = Constants.YARN_BALL_DENSITY;
        fdef.friction = 0; // so it doesn't stick to the walls.
        fdef.restitution = Constants.YARN_BALL_RESTITUTION;
		fdef.filter.categoryBits = Constants.B2D_YARN_BALLS;
        fdef.filter.maskBits = Constants.B2D_BIT_WORLD | Constants.B2D_BIT_CAT1S | Constants.B2D_YARN_BALLS;

		this.body = b2d_world.createBody(bdef);
        this.body.createFixture(fdef).setUserData(Constants.YARN_BALL_SPRITE_1);
    }
}
