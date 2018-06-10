package com.sandra.game.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.sandra.game.entities.Entity;
import com.sandra.game.utils.Constants;

public class Controls {
    
    private final String TAG = Controls.class.getName();
    private DelayedRemovalArray<Entity> entities;

    public Controls(DelayedRemovalArray<Entity> entities) {
        this.entities = entities;
    }

    public void update(float delta) {
        for (Entity entity: entities) {
            if(Gdx.app.getType() == ApplicationType.Android) {android_movement(delta, entity);}
            else if(Gdx.app.getType() == ApplicationType.Desktop) {desktop_movement(delta, entity);}
        }
    }

    public void android_movement(float delta, Entity entity) {  // Accelerometer  = x = y = [-10, 10]
        if (Gdx.input.getAccelerometerX() < -1) {           // up
            if (entity.get_velocity().y >= Constants.ENTETIES_MAX_VELOCITY) {
                entity.get_velocity().y = Constants.ENTETIES_MAX_VELOCITY; // ensures a constant speed
            } else if (entity.get_velocity().y < Constants.ENTETIES_MAX_VELOCITY) {
                entity.get_velocity().y += delta * Constants.ENTETIES_ACCELERATION;
            }
        } else if (Gdx.input.getAccelerometerX() > 1) {     // down
            if (entity.get_velocity().y <= -Constants.ENTETIES_MAX_VELOCITY) {
                entity.get_velocity().y = -Constants.ENTETIES_MAX_VELOCITY; // ensures a constant speed
            } else if (entity.get_velocity().y > -Constants.ENTETIES_MAX_VELOCITY) {
                entity.get_velocity().y -= delta * Constants.ENTETIES_ACCELERATION;
            }
        } else {entity.get_velocity().y = 0;}
        
        if (Gdx.input.getAccelerometerY() < -1) {           // left
            if (entity.get_velocity().x <= -Constants.ENTETIES_MAX_VELOCITY) {
                entity.get_velocity().x = -Constants.ENTETIES_MAX_VELOCITY; // ensures a constant speed
            } else if (entity.get_velocity().x > -Constants.ENTETIES_MAX_VELOCITY) {
                entity.get_velocity().x -= delta * Constants.ENTETIES_ACCELERATION;
            }
        } else if (Gdx.input.getAccelerometerY() > 1) {     // right
            if (entity.get_velocity().x >= Constants.ENTETIES_MAX_VELOCITY) {
                entity.get_velocity().x = Constants.ENTETIES_MAX_VELOCITY; // ensures a constant speed
            } else if (entity.get_velocity().x < Constants.ENTETIES_MAX_VELOCITY) {
                entity.get_velocity().x += delta * Constants.ENTETIES_ACCELERATION;
            }
        } else {entity.get_velocity().x = 0;}

        entity.get_body().applyLinearImpulse(entity.get_velocity().x, entity.get_velocity().y, entity.get_body().getPosition().x, entity.get_body().getPosition().y, true);
        entity.get_render_position().set(
            entity.get_body().getPosition().x - Constants.CAT1_PIXEL_WIDTH / 2 / Constants.PPM,
            entity.get_body().getPosition().y - Constants.CAT1_PIXEL_HEIGHT / 2 / Constants.PPM
        );
    }

    public void desktop_movement(float delta, Entity entity) {
        if (Gdx.input.isKeyPressed(Keys.W)) {           // up
            if (entity.get_velocity().y >= Constants.ENTETIES_MAX_VELOCITY) {
                entity.get_velocity().y = Constants.ENTETIES_MAX_VELOCITY; // ensures a constant speed
            } else if (entity.get_velocity().y < Constants.ENTETIES_MAX_VELOCITY) {
                entity.get_velocity().y += delta * Constants.ENTETIES_ACCELERATION;
            }
        } else if (Gdx.input.isKeyPressed(Keys.S)) {    // down
            if (entity.get_velocity().y <= -Constants.ENTETIES_MAX_VELOCITY) {
                entity.get_velocity().y = -Constants.ENTETIES_MAX_VELOCITY; // ensures a constant speed
            } else if (entity.get_velocity().y > -Constants.ENTETIES_MAX_VELOCITY) {
                entity.get_velocity().y -= delta * Constants.ENTETIES_ACCELERATION;
            }
        } else {entity.get_velocity().y = 0;}
        
        if (Gdx.input.isKeyPressed(Keys.A)) {           // left
            if (entity.get_velocity().x <= -Constants.ENTETIES_MAX_VELOCITY) {
                entity.get_velocity().x = -Constants.ENTETIES_MAX_VELOCITY; // ensures a constant speed
            } else if (entity.get_velocity().x > -Constants.ENTETIES_MAX_VELOCITY) {
                entity.get_velocity().x -= delta * Constants.ENTETIES_ACCELERATION;
            }
        } else if(Gdx.input.isKeyPressed(Keys.D)) {     // right
            if (entity.get_velocity().x >= Constants.ENTETIES_MAX_VELOCITY) {
                entity.get_velocity().x = Constants.ENTETIES_MAX_VELOCITY; // ensures a constant speed
            } else if (entity.get_velocity().x < Constants.ENTETIES_MAX_VELOCITY) {
                entity.get_velocity().x += delta * Constants.ENTETIES_ACCELERATION;
            }
        } else {entity.get_velocity().x = 0;}

        entity.get_body().applyLinearImpulse(
            entity.get_velocity().x,
            entity.get_velocity().y,
            entity.get_body().getPosition().x,
            entity.get_body().getPosition().y,
            true
        );
        entity.get_render_position().set(
            entity.get_body().getPosition().x - Constants.CAT1_PIXEL_WIDTH / 2 / Constants.PPM,
            entity.get_body().getPosition().y - Constants.CAT1_PIXEL_HEIGHT / 2 / Constants.PPM
        );
    }

    public void removeEntity(String id) {
        for (int i=0; i < entities.size; i++) {
            if (entities.get(i).getId() == id) {
                entities.removeIndex(i);
                return;
            }
        }
    }
}
