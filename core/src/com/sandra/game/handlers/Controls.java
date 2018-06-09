package com.sandra.game.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.sandra.game.entities.Entity;
import com.sandra.game.utils.Constants;

public class Controls {
    
    private DelayedRemovalArray<Entity> entities;

    public Controls(DelayedRemovalArray<Entity> entities) {
        this.entities = entities;
    }

    public void update(float delta) {
        for (Entity entity: entities) {
            // android_movement(delta, entity); // TODO: if both are active get_velocity will be set to 0 and something else at the same time ...
            desktop_movement(delta, entity);
        }
    }

    public void android_movement(float delta, Entity entity) {
        System.out.println(
            "getAccelerometerX: " +
            Gdx.input.getAccelerometerX() +
             ", getAccelerometerY: " +
            Gdx.input.getAccelerometerY()
        );
        if ((Gdx.input.getAccelerometerX() < 0) && entity.get_velocity().y < Constants.ENTETIES_MAX_VELOCITY) {          // up
            entity.get_velocity().y += delta * Constants.ENTETIES_ACCELERATION;
        } else if ((Gdx.input.getAccelerometerX() > 0) && entity.get_velocity().y > -Constants.ENTETIES_MAX_VELOCITY) {  // down
            entity.get_velocity().y -= delta * Constants.ENTETIES_ACCELERATION;
        } else {entity.get_velocity().y = 0;}
        
        if ((Gdx.input.getAccelerometerY() < 0) && entity.get_velocity().x > -Constants.ENTETIES_MAX_VELOCITY) {         // left
            entity.get_velocity().x -= delta * Constants.ENTETIES_ACCELERATION;
        } else if ((Gdx.input.getAccelerometerY() > 0) && entity.get_velocity().x < Constants.ENTETIES_MAX_VELOCITY) {   // right
            entity.get_velocity().x += delta * Constants.ENTETIES_ACCELERATION;
        } else {entity.get_velocity().x = 0;}
        
        entity.get_body().applyLinearImpulse(entity.get_velocity().x, entity.get_velocity().y, entity.get_body().getPosition().x, entity.get_body().getPosition().y, true);
        entity.get_render_position().set(
            entity.get_body().getPosition().x - Constants.CAT1_WIDTH / 2 / Constants.PPM,
            entity.get_body().getPosition().y - Constants.CAT1_HEIGHT / 2 / Constants.PPM
        );
    }

    public void desktop_movement(float delta, Entity entity) {
        if (Gdx.input.isKeyPressed(Keys.W) && entity.get_velocity().y < Constants.ENTETIES_MAX_VELOCITY) {           // up
            entity.get_velocity().y += delta * Constants.ENTETIES_ACCELERATION;
        } else if (Gdx.input.isKeyPressed(Keys.S) && entity.get_velocity().y > -Constants.ENTETIES_MAX_VELOCITY) {   // down
            entity.get_velocity().y -= delta * Constants.ENTETIES_ACCELERATION;
        } else {entity.get_velocity().y = 0;}
        
        if (Gdx.input.isKeyPressed(Keys.A) && entity.get_velocity().x > -Constants.ENTETIES_MAX_VELOCITY) {          // left
            entity.get_velocity().x -= delta * Constants.ENTETIES_ACCELERATION;
        } else if (Gdx.input.isKeyPressed(Keys.D) && entity.get_velocity().x < Constants.ENTETIES_MAX_VELOCITY) {    // right
            entity.get_velocity().x += delta * Constants.ENTETIES_ACCELERATION;
        } else {entity.get_velocity().x = 0;}
        
        entity.get_body().applyLinearImpulse(entity.get_velocity().x, entity.get_velocity().y, entity.get_body().getPosition().x, entity.get_body().getPosition().y, true);
        entity.get_render_position().set(
            entity.get_body().getPosition().x - Constants.CAT1_WIDTH / 2 / Constants.PPM,
            entity.get_body().getPosition().y - Constants.CAT1_HEIGHT / 2 / Constants.PPM
        );
    }
}