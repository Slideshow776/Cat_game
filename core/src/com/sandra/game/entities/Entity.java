package com.sandra.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.sandra.game.utils.Enums.*;

public abstract class Entity {

    public Direction direction;
    public Action action;

    protected Vector2 velocity;
    protected Vector2 render_position;
    protected Body body;
    protected String id;

    public void dispose() {}
    public void render(SpriteBatch batch) {}
    public void update(float delta) {}
    
    public String getId() {return id;}
    public Body get_body() {return body;}
    public Vector2 get_velocity() {return velocity;}
    public Vector2 get_render_position() {return render_position;}
    public Direction get_direction() {return direction;}
    public Action get_action() {return action;}

    public void set_action(Action action) {this.action = action;}
}
