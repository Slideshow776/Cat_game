package com.sandra.game.entities;

import java.util.UUID;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.TimeUtils;
import com.sandra.game.utils.Assets;
import com.sandra.game.utils.Constants;
import com.sandra.game.utils.Utils;
import com.sandra.game.utils.Enums.Action;
import com.sandra.game.utils.Enums.Direction;
import com.sandra.game.utils.Enums;

public class Cat1 extends Entity {
    private World b2d_world;

    private TextureRegion region;
    private float animation_start_time;

	private Action action;
    private Direction direction;

    public Cat1(Vector2 position, World b2d_world) {
        render_position = position;
        velocity = new Vector2(0, 0);
        id = UUID.randomUUID().toString();
        animation_start_time = TimeUtils.nanoTime();
        
        this.b2d_world = b2d_world;
        init_body();
        action = Enums.Action.IDLE;
        direction = Enums.Direction.LEFT;
    }

    public void render(SpriteBatch batch) {
        float animation_time_seconds = Utils.secondsSince(animation_start_time);

        // Actions
        if(action == Enums.Action.IDLE) {
            region = Assets.instance.cat1Assets.cat1_idle_animation.getKeyFrame(animation_time_seconds);
        }
        else if (action == Enums.Action.SLIDING) {
            region = Assets.instance.cat1Assets.cat1_sliding_animation.getKeyFrame(animation_time_seconds);
        }
        else if (action == Enums.Action.SWIMMING) {
            region = Assets.instance.cat1Assets.cat1_swimming_animation.getKeyFrame(animation_time_seconds);
        }

        // Directions
        if (direction == Enums.Direction.LEFT) {
            Utils.drawTextureRegion(batch, region, render_position.x, render_position.y, Constants.CAT1_SCALE);
        }
        else if (direction == Enums.Direction.RIGHT) {
            Utils.drawTextureRegion(batch, region, render_position.x, render_position.y, true, Constants.CAT1_SCALE);
        }
    }

    public void update(float delta) {
        if (action == Enums.Action.IDLE) {
            render_position.set( // the "- .1f" is a magical offset render position when idle ...
                (body.getPosition().x - (Constants.CAT1_PIXEL_WIDTH * Constants.CAT1_SCALE) / 2 / Constants.PPM) - .1f,
                body.getPosition().y - (Constants.CAT1_PIXEL_HEIGHT * Constants.CAT1_SCALE) / 2 / Constants.PPM
                );
        } else {
            render_position.set(
                body.getPosition().x - (Constants.CAT1_PIXEL_WIDTH * Constants.CAT1_SCALE) / 2 / Constants.PPM,
                body.getPosition().y - (Constants.CAT1_PIXEL_HEIGHT * Constants.CAT1_SCALE) / 2 / Constants.PPM
                );
        }
            
            if (body.getLinearVelocity().x > 0) {direction = Enums.Direction.RIGHT;} 
            else if (body.getLinearVelocity().x < 0) {direction = Enums.Direction.LEFT;}
            
        try {
            if ((Integer)body.getUserData() == 0) {
                action = Enums.Action.SWIMMING;
            }
            else if (body.getLinearVelocity().x == 0 && body.getLinearVelocity().y == 0) {
                action = Enums.Action.IDLE;
            }
            else {
                action = Enums.Action.SLIDING;
            }
        } catch(Exception e) {}
        set_action(action);
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
        this.body.createFixture(fdef).setUserData(Constants.CAT1_IDLE_SPRITE_1);
    }    
}
    