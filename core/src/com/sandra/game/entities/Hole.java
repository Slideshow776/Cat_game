package com.sandra.game.entities;

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

public class Hole extends Entity {

    private World b2d_world;

    public Hole(Vector2 position, World b2d_world) {
        render_position = position;
        this.b2d_world = b2d_world;
        init_body();
    }

    public void render(SpriteBatch batch) {
        final TextureRegion region = Assets.instance.holeAssets.hole;
        render_position.x = Constants.GAME_WIDTH / 2 / Constants.PPM; // TODO: this is wrong
        render_position.y = Constants.GAME_HEIGHT / 2 / Constants.PPM;
        Utils.drawTextureRegion(batch, region, render_position, Constants.HOLE_CENTER);
    }

    public void update(float delta) {}

    public void dispose() {b2d_world.destroyBody(body);}

    private void init_body() {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyType.StaticBody;
        bdef.position.set(
            (render_position.x + (Constants.HOLE_PIXEL_WIDTH / Constants.PPM) / 2),
            (render_position.y + (Constants.HOLE_PIXEL_HEIGHT / Constants.PPM) / 2)
        );

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.HOLE_HALF_WIDTH, Constants.HOLE_HALF_HEIGHT);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        //fdef.friction = 50f;
		fdef.filter.categoryBits = Constants.B2D_BIT_HOLE;
		fdef.filter.maskBits = Constants.B2D_BIT_CAT1S;

		this.body = b2d_world.createBody(bdef);
        this.body.createFixture(fdef).setUserData(Constants.HOLE_SPRITE);
    }
}
