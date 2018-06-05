package com.sandra.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.sandra.game.utils.Assets;
import com.sandra.game.utils.Constants;
import com.sandra.game.utils.Utils;

public class Cat1 {

    public Vector2 position;
    public Vector2 velocity;

    private World b2d_world;
    private Body cat1_body;
    private Boolean collision;

    public Cat1(Vector2 position, World b2d_world) {
        this.position = position;
        velocity = new Vector2(0, 0);
        this.b2d_world = b2d_world;
        set_body();
        collision = false;
    }

    public void render(SpriteBatch batch) {
        final TextureRegion region = Assets.instance.cat1Assets.cat1;
        Utils.drawTextureRegion(batch, region, position, Constants.CAT1_CENTER);
    }

    public void update(float delta, boolean collision) {
        movement2(delta);
        this.collision = collision;
    }

    private void set_body() {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyType.DynamicBody;
        bdef.position.set( // TODO: Do I need this?
            (position.x + Constants.CAT1_WIDTH / 2) / Constants.PPM,
            (position.y + Constants.CAT1_HEIGHT / 2) / Constants.PPM
        );

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(23f / Constants.PPM, 12.5f / Constants.PPM);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = .65f;
		fdef.filter.categoryBits = Constants.B2D_BIT_CAT1S;
		fdef.filter.maskBits = Constants.B2D_BIT_WORLD | Constants.B2D_BIT_CAT1S;

		cat1_body = b2d_world.createBody(bdef);
        cat1_body.createFixture(fdef).setUserData("cat1");
        cat1_body.setLinearDamping(50f);
        //cat1_body.setAngularDamping(50f);
    }

    private void movement2(float delta) {
        if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.getAccelerometerX() < 0) {
            velocity.y += delta * Constants.ENTETIES_ACCELERATION;
        } else if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.getAccelerometerX() > 0) {
            velocity.y -= delta * Constants.ENTETIES_ACCELERATION;
        }

        if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.getAccelerometerY() < 0) {
            velocity.x -= delta * Constants.ENTETIES_ACCELERATION;
        } else if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.getAccelerometerY() > 0) {
            velocity.x += delta * Constants.ENTETIES_ACCELERATION;
        }

        //System.out.println(velocity);
        System.out.println(position.x * Constants.PPM + ", " + position.y * Constants.PPM);
        cat1_body.applyLinearImpulse(velocity, cat1_body.getPosition(), true);
        position.x = cat1_body.getPosition().x - Constants.CAT1_WIDTH / 2 / Constants.PPM;
        position.y = cat1_body.getPosition().y - Constants.CAT1_HEIGHT / 2 / Constants.PPM;
    }

    private void movement(float delta) {
        if ((Gdx.input.isKeyPressed(Keys.W) || Gdx.input.getAccelerometerX() < 0)) {// &&            // up
                //position.y + Constants.CAT1_HEIGHT < Gdx.app.getGraphics().getHeight()) {
            velocity.y += delta * Constants.ENTETIES_ACCELERATION;
            //position.y += delta * velocity.y;
        } else if ((Gdx.input.isKeyPressed(Keys.S) || Gdx.input.getAccelerometerX() > 0)) {// &&     // down
                //position.y > 0) {
            velocity.y -= delta * Constants.ENTETIES_ACCELERATION;
            //position.y += delta * velocity.y;
        }else {
            //velocity.y = 0f;
        }

        if ((Gdx.input.isKeyPressed(Keys.A) || Gdx.input.getAccelerometerY() < 0)){ //&&            // left
                //position.x > 0) {
            velocity.x -= delta * Constants.ENTETIES_ACCELERATION;
            //position.x += delta * velocity.x;
        } else if ((Gdx.input.isKeyPressed(Keys.D) || Gdx.input.getAccelerometerY() > 0)){ //&&     // right
                //position.x + Constants.CAT1_WIDTH < Gdx.app.getGraphics().getWidth()) {
            velocity.x += delta * Constants.ENTETIES_ACCELERATION;
            //position.x += delta * velocity.x;
        }
        else {
            //velocity.x = 0f;
        }
        
        cat1_body.setLinearVelocity(velocity);
        position = cat1_body.getPosition();
        //if (!collision) {
            cat1_body.setTransform(position.x + Constants.CAT1_WIDTH / 2, position.y + Constants.CAT1_HEIGHT / 2, 0);
        //}
    }
}