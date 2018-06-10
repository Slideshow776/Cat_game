package com.sandra.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class Entity {

    protected Vector2 velocity;
    protected Vector2 render_position;
    protected Body body;
    protected Boolean collision;
    protected String id;

    public void dispose() {}
    public void render(SpriteBatch batch) {}
    public void update(float delta, boolean collision) {}

    public String getId() {return id;}
    public Body get_body() {return body;}
    public Boolean collided() {return collision;}
    public Vector2 get_velocity() {return velocity;}
    public Vector2 get_render_position() {return render_position;}    
}