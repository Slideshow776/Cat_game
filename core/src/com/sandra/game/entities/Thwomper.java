package com.sandra.game.entities;

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
import com.sandra.game.utils.Enums;
import com.sandra.game.utils.Utils;
import com.sandra.game.utils.Enums.Action;

public class Thwomper extends Entity {    
    private World b2d_world;

    private TextureRegion region;
    private float animation_start_time;
    private Action action;
    private boolean awaken;

    private float max_height;
    private float move_increment;
    
    public Thwomper(Vector2 position, World b2d_world) {
        this.b2d_world = b2d_world;
        render_position = position;
        animation_start_time = TimeUtils.nanoTime();
        init_body();

        action = Enums.Action.IDLE;
        awaken = false;
        max_height = render_position.y +  (Constants.THWOMPER_MAX_HEIGHT_MOVE / Constants.PPM);
        move_increment = 0;
    }

    public void render(SpriteBatch batch) {
        float animation_time_seconds = Utils.secondsSince(animation_start_time);
        
        if (action == Enums.Action.IDLE) {
            region = Assets.instance.thwomperAssets.thwomper_idle_animation.getKeyFrame(animation_time_seconds);
        } else if (action == Enums.Action.MOVING) {
            region = Assets.instance.thwomperAssets.thwomper_moving_animation.getKeyFrame(animation_time_seconds);
        } else if (action == Enums.Action.POISED) {
            region = Assets.instance.thwomperAssets.thwomper_floating_animation.getKeyFrame(animation_time_seconds);
        } else if (action == Enums.Action.ATTACKING) {
            region = Assets.instance.thwomperAssets.thwomper_attacking_animation.getKeyFrame(animation_time_seconds);
        }

        Utils.drawTextureRegion(batch, region, render_position.x, render_position.y);
    }

    public void update(float delta) {
        if (action == Enums.Action.IDLE && body.getUserData() == "cat_collision") {
            awaken = true;
            body.setUserData(null);
        }
        System.out.println(body.getUserData());
        if (awaken) {
            action = Enums.Action.MOVING;
            if (render_position.y <= max_height) {
                move_increment += 1 / Constants.PPM;
            } else {
                action = Enums.Action.POISED;
            }
        }
        if (action == Enums.Action.POISED) {
            if (body.getUserData() == "cat_collision") {
                action = Enums.Action.ATTACKING;
            }
        }
        if (action == Enums.Action.ATTACKING) {
            awaken = false;
        }

        render_position.set(
            (body.getPosition().x - Constants.THWOMPER_PIXEL_WIDTH / 2 / Constants.PPM) + .075f,
            body.getPosition().y + move_increment - Constants.THWOMPER_PIXEL_HEIGHT / 2 / Constants.PPM
        );
    }

    public void dispose() {b2d_world.dispose();}

    private void init_body() {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyType.StaticBody;
        bdef.position.set(
            (render_position.x + (Constants.THWOMPER_PIXEL_WIDTH / 2 / Constants.PPM) / 2),
            (render_position.y + (Constants.THWOMPER_PIXEL_HEIGHT / Constants.PPM) / 2)
        );

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.THWOMPER_HALF_WIDTH, Constants.THWOMPER_HALF_HEIGHT);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        // fdef.density = Constants.THWOMPER_DENSITY;
        // fdef.friction = 0;
        // fdef.restitution = Constants.THWOMPER_RESTITUTION;
		fdef.filter.categoryBits = Constants.B2D_THWOMPER;
        fdef.filter.maskBits = Constants.B2D_BIT_CAT1S | Constants.B2D_YARN_BALLS;
        fdef.isSensor = true;

		this.body = b2d_world.createBody(bdef);
        this.body.createFixture(fdef).setUserData(Constants.THWOMPER_IDLE_SPRITE1);
    }
}