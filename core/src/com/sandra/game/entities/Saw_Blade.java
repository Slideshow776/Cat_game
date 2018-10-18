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
import com.sandra.game.utils.Utils;

public class Saw_Blade extends Entity {

    private World b2d_world;
    private float animation_start_time;
    private TextureRegion region;

    public Saw_Blade(Vector2 position, World b2d_world) {
        this.b2d_world = b2d_world;
        render_position = position;
        animation_start_time = TimeUtils.nanoTime();
        init_body();
    }

    public void render(SpriteBatch batch) {
        float animation_time_seconds = Utils.secondsSince(animation_start_time);
        region = Assets.instance.sawBladeAssets.saw_blade_animation.getKeyFrame(animation_time_seconds);
        Utils.drawTextureRegion(batch, region, render_position.x, render_position.y, 1.5f);
    }

    public void update(float delta) {

    }

    public void dispose() { b2d_world.dispose(); }

    private void init_body() {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyType.KinematicBody;
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
        // fdef.restitution = ;
		fdef.filter.categoryBits = Constants.B2D_THWOMPER;
        fdef.filter.maskBits = Constants.B2D_BIT_CAT1S | Constants.B2D_YARN_BALLS;
        fdef.isSensor = true;

		body = b2d_world.createBody(bdef);
        body.createFixture(fdef).setUserData(Constants.THWOMPER_IDLE_SPRITE1);
        body.setUserData(Constants.THWOMPER_IDLE_SPRITE1);
    }
}