package com.sandra.game.entities;

import java.util.Random;

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

public class Cat1_part extends Entity {
    private World b2d_world;
    private TextureRegion region;        
    private boolean top_part;

    public Cat1_part(Vector2 position, World b2d_world, boolean top_part) {
        render_position = position;
        this.b2d_world = b2d_world;
        this.top_part = top_part;

        init_body();
        velocity = new Vector2(0, 0);
        set_dead(true);

        Random random = new Random();
        float x = random.nextFloat() * 2 - 1.5f;
        float y = random.nextFloat() * 2 - 1.5f;
        System.out.println(x + y);
        body.setLinearVelocity(x, y); // Gives a random velocity push from being killed by the saw blade.
    }

    public void render(SpriteBatch batch) {
        if (top_part)
            region = Assets.instance.cat1Assets.dead3;
        else
            region = Assets.instance.cat1Assets.dead2;
            
        Utils.drawTextureRegion(
            batch,
            region,
            render_position.x - .08f, // magic number found by manual positioning
            render_position.y,
            1.5f
        );
    }

    public void dispose() { b2d_world.destroyBody(body); }

    public void update(float delta) {
        if (body.getUserData() == "annihilate") set_annihilated(true);
        if (body.getUserData() == "zone_count_up") body.setUserData(Constants.CAT1_IDLE_SPRITE_1);
        else if (body.getUserData() == "zone_count_down") body.setUserData(Constants.CAT1_IDLE_SPRITE_1);

        render_position.set(
            body.getPosition().x - (Constants.CAT1_PIXEL_WIDTH / 2 / Constants.PPM) / 2,
            body.getPosition().y - (Constants.CAT1_PIXEL_HEIGHT / Constants.PPM) / 2
        );
    }

    private void init_body() {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyType.DynamicBody;
        bdef.fixedRotation = true;
        bdef.position.set(
            (render_position.x + (Constants.CAT1_PIXEL_WIDTH / 2 / Constants.PPM) / 2),
            (render_position.y + (Constants.CAT1_PIXEL_HEIGHT / Constants.PPM) / 2)
        );

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.CAT1_HALF_WIDTH, Constants.CAT1_HALF_HEIGHT / 2);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = Constants.CAT1_DENSITY;
        // fdef.friction = ;
        fdef.restitution = Constants.CAT1_RESTITUTION;
		fdef.filter.categoryBits = Constants.B2D_BIT_CAT1S;
        fdef.filter.maskBits = Constants.B2D_BIT_WORLD | Constants.B2D_BIT_CAT1S | 
            Constants.B2D_BIT_HOLE | Constants.B2D_BIT_COINS | Constants.B2D_YARN_BALLS
            | Constants.B2D_THWOMPER | Constants.B2D_SAW_BLADE;

            
        this.body = b2d_world.createBody(bdef);
        id = 0; // we don't need ids here, only applies for Thwomper collision/detection logic
        this.body.setLinearDamping(Constants.ENTITIES_LINEAR_DAMPING);
        this.body.createFixture(fdef).setUserData(Constants.CAT1_IDLE_SPRITE_1 + "-" + id);
    }
}
