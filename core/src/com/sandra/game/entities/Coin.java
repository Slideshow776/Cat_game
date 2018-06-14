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
import com.sandra.game.utils.Assets;
import com.sandra.game.utils.Constants;
import com.sandra.game.utils.Utils;

public class Coin extends Entity {

    private World b2d_world;
    private long animation_start_time;

    public Coin(Vector2 position, World b2d_world) {
        render_position = position;
        this.b2d_world = b2d_world;
        animation_start_time = TimeUtils.nanoTime();
        init_body();
    }

    public void render(SpriteBatch batch) {
        float animation_time_seconds = Utils.secondsSince(animation_start_time);
        TextureRegion region = Assets.instance.coinAssets.coin_animation.getKeyFrame(animation_time_seconds);
        Utils.drawTextureRegion(batch, region, render_position);
    }

    public void update() {}

    public void dispose() {b2d_world.destroyBody(body);}

    private void init_body() {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyType.StaticBody;
        bdef.position.set(
            (render_position.x + (Constants.COIN_PIXEL_WIDTH / Constants.PPM) / 2),
            (render_position.y + (Constants.COIN_PIXEL_HEIGHT / Constants.PPM) / 2)
        );

        CircleShape shape = new CircleShape();
        shape.setRadius(Constants.COIN_RADIUS);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.isSensor = true;
		fdef.filter.categoryBits = Constants.B2D_BIT_COINS;
		fdef.filter.maskBits = Constants.B2D_BIT_WORLD | Constants.B2D_BIT_CAT1S;

		this.body = b2d_world.createBody(bdef);
        this.body.createFixture(fdef).setUserData(Constants.COIN_SPRITE_1);
    }
}