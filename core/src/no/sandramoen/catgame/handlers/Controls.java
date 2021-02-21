package com.sandra.game.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.sandra.game.entities.Entity;
import com.sandra.game.utils.Constants;
import com.sandra.game.utils.Enums;

/*
    Supports both android and desktop controls.
        Desktop: WASD
        Android: Accelerometer
    
    @author: Sandra Moen
*/

public class Controls {

    private final String TAG = Controls.class.getName();
    private DelayedRemovalArray<Entity> entities;

    private short android_tilt_sensitivity_up = 3;
    private short android_tilt_sensitivity_down = 5;
    private short android_tilt_sensitivity_left = -1;
    private short android_tilt_sensitivity_right = 1;

    private float max_velocity;
    private float max_acceleration;

    public Controls(DelayedRemovalArray<Entity> entities) {
        this.entities = entities;
        max_velocity = Constants.ENTETIES_MAX_VELOCITY;
        max_acceleration = Constants.ENTETIES_ACCELERATION;
    }

    public void add_entities(Entity entity) { entities.add(entity); }

    public void update(float delta) {
        for (Entity entity : entities) {
            if(entity.get_action() == Enums.Action.SWIMMING || entity.is_dead()) {
                max_velocity = Constants.ENTETIES_MAX_VELOCITY / 16;
                max_acceleration = Constants.ENTETIES_ACCELERATION / 16;
            } 
            else {
                max_velocity = Constants.ENTETIES_MAX_VELOCITY;
                max_acceleration = Constants.ENTETIES_ACCELERATION;
            }

            if (Gdx.app.getType() == ApplicationType.Android && !entity.is_spawning()) {
                android_movement(delta, entity);
            } 
            else if (Gdx.app.getType() == ApplicationType.Desktop && !entity.is_spawning()) {
                desktop_movement(delta, entity);
            }
        }
    }

    /*
     * When the accelerometer is zero the phone is flat and leveled with the ground.
     * The up and down movement is set differently such that the player can play
     * with the phone tilted towards their face, thus making it more comfortable to
     * play.
     */
    public void android_movement(float delta, Entity entity) { // Accelerometer = x = y = [-10, 10]
        if (Gdx.input.getAccelerometerX() < android_tilt_sensitivity_up) {
            move_entity_up(delta, entity);
        } // up
        else if (Gdx.input.getAccelerometerX() > android_tilt_sensitivity_down) {
            move_entity_down(delta, entity);
        } // down
        else {
            entity.get_velocity().y = 0;
        }

        if (Gdx.input.getAccelerometerY() < android_tilt_sensitivity_left) {
            move_entity_left(delta, entity);
        } // left
        else if (Gdx.input.getAccelerometerY() > android_tilt_sensitivity_right) {
            move_entity_right(delta, entity);
        } // right
        else {
            entity.get_velocity().x = 0;
        }

        apply_linear_impulse(entity);
    }

    public void desktop_movement(float delta, Entity entity) {
        if (Gdx.input.isKeyPressed(Keys.W)) {
            move_entity_up(delta, entity);
        } // up
        else if (Gdx.input.isKeyPressed(Keys.S)) {
            move_entity_down(delta, entity);
        } // down
        else {
            entity.get_velocity().y = 0;
        }

        if (Gdx.input.isKeyPressed(Keys.A)) {
            move_entity_left(delta, entity);
        } // left
        else if (Gdx.input.isKeyPressed(Keys.D)) {
            move_entity_right(delta, entity);
        } // right
        else {
            entity.get_velocity().x = 0;
        }

        apply_linear_impulse(entity);
    }

    public void removeEntity(int id) {
        for (int i = 0; i < entities.size; i++) {
            if (entities.get(i).getId() == id) {
                entities.removeIndex(i);
                return;
            }
        }
    }

    private void move_entity_up(float delta, Entity entity) {
        if (entity.get_velocity().y >= max_velocity) {
            entity.get_velocity().y = max_velocity; // ensures a constant speed
        } else if (entity.get_velocity().y < max_velocity) {
            entity.get_velocity().y += delta * max_acceleration;
        }
    }

    private void move_entity_down(float delta, Entity entity) {
        if (entity.get_velocity().y <= -max_velocity) {
            entity.get_velocity().y = -max_velocity; // ensures a constant speed
        } else if (entity.get_velocity().y > -max_velocity) {
            entity.get_velocity().y -= delta * max_acceleration;
        }
    }

    private void move_entity_left(float delta, Entity entity) {
        if (entity.get_velocity().x <= -max_velocity) {
            entity.get_velocity().x = -max_velocity; // ensures a constant speed
        } else if (entity.get_velocity().x > -max_velocity) {
            entity.get_velocity().x -= delta * max_acceleration;
        }
    }

    private void move_entity_right(float delta, Entity entity) {
        if (entity.get_velocity().x >= max_velocity) {
            entity.get_velocity().x = max_velocity; // ensures a constant speed
        } else if (entity.get_velocity().x < max_velocity) {
            entity.get_velocity().x += delta * max_acceleration;
        }
    }

    private void apply_linear_impulse(Entity entity) {
        entity.get_body().applyLinearImpulse(entity.get_velocity().x, entity.get_velocity().y,
                entity.get_body().getPosition().x, entity.get_body().getPosition().y, true);
    }
}
