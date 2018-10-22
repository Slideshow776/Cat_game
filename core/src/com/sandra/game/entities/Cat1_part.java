package com.sandra.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.TimeUtils;
import com.sandra.game.utils.Constants;
import com.sandra.game.utils.Utils;

public class Cat1_part extends Entity {

    private World b2d_world;
    private boolean top_part;
    private float animation_start_time;

    public Cat1_part(Vector2 position, World b2d_world, boolean top_part) {
        render_position = position;
        this.b2d_world = b2d_world;
        this.top_part = top_part;

        animation_start_time = TimeUtils.nanoTime();
        init_body();
        velocity = new Vector2(0, 0);
    }

    public void render(SpriteBatch batch) {
        float animation_time_seconds = Utils.secondsSince(animation_start_time);   

        /* region = Assets.instance.sawBladeAssets.saw_blade_animation.getKeyFrame(animation_time_seconds);
        Utils.drawTextureRegion(
            batch,
            region,
            render_position.x - .205f, // magic number found by manual positioning
            render_position.y - .09f, // magic number found by manual positioning
            !moving_right,
            1.5f
        ); */
    }

    public void update(float delta) {

    }

    private void init_body() {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyType.DynamicBody;
        bdef.position.set(
            (render_position.x + (Constants.SAW_BLADE_PIXEL_WIDTH / 2 / Constants.PPM) / 2),
            (render_position.y + (Constants.SAW_BLADE_PIXEL_HEIGHT / Constants.PPM) / 2)
        );

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.SAW_BLADE_HALF_WIDTH, Constants.SAW_BLADE_HALF_HEIGHT);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        // fdef.density = ;
        // fdef.friction = ;
        fdef.restitution = Constants.CAT1_RESTITUTION;
		fdef.filter.categoryBits = Constants.B2D_BIT_CAT1S;
        fdef.filter.maskBits = Constants.B2D_BIT_WORLD | Constants.B2D_BIT_CAT1S | 
            Constants.B2D_BIT_HOLE | Constants.B2D_BIT_COINS | Constants.B2D_YARN_BALLS
            | Constants.B2D_THWOMPER | Constants.B2D_SAW_BLADE;

        body = b2d_world.createBody(bdef);
        id = 0; // we don't need ids here, only applies for thwomper collision/detection logic
        this.body.createFixture(fdef).setUserData(Constants.CAT1_IDLE_SPRITE_1 + "-" + id);
        body.setUserData(Constants.SAW_BLADE1);
    }
}
