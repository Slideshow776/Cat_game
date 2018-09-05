package com.sandra.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.TimeUtils;
import com.sandra.game.utils.Assets;
import com.sandra.game.utils.Constants;
import com.sandra.game.utils.Enums;
import com.sandra.game.utils.Utils;
import com.sandra.game.utils.Enums.Action;

public class Thwomper extends Entity {    
    private World b2d_world;

    private TextureRegion region;
    private float animation_start_time;
    private Action action;

    private float max_height;
    private float move_increment;
    private float ground;
    private Array<Integer> id_of_cats_beneath;
    
    private DelayedRemovalArray<Entity> entity_list;
    private Shadow shadow;
    
    public Thwomper(Vector2 position, World b2d_world, DelayedRemovalArray<Entity> entity_list) {
        this.b2d_world = b2d_world;
        this.entity_list = entity_list;
        render_position = position;
        animation_start_time = TimeUtils.nanoTime();
        init_body();

        action = Enums.Action.IDLE;
        max_height = render_position.y +  (Constants.THWOMPER_MAX_HEIGHT_MOVE / Constants.PPM);
        move_increment = 0;
        ground = render_position.y;
        id_of_cats_beneath = new Array<Integer>(0);

        shadow = new Shadow(render_position);
    }

    public void render(SpriteBatch batch) {
        float animation_time_seconds = Utils.secondsSince(animation_start_time);
        
        if (action == Enums.Action.IDLE) {
            region = Assets.instance.thwomperAssets.thwomper_idle_animation.getKeyFrame(animation_time_seconds);
        } else if (action == Enums.Action.MOVING) {
            region = Assets.instance.thwomperAssets.thwomper_moving_animation.getKeyFrame(animation_time_seconds);
        } else if (action == Enums.Action.POISED) {
            region = Assets.instance.thwomperAssets.thwomper_floating_animation.getKeyFrame(animation_time_seconds);
        } else if (action == Enums.Action.ATTACKING) {
            region = Assets.instance.thwomperAssets.thwomper_attacking_animation.getKeyFrame(animation_time_seconds);
        }

        shadow.set_action(action);
        shadow.render(batch);
        
        Utils.drawTextureRegion(batch, region, render_position.x, render_position.y);
    }

    public void update(float delta) {        
        String userData = (String)body.getUserData();
        String[] collisionString = {};
        if (userData != null) collisionString = userData.split("-");

        if (collisionString != null && collisionString.length ==  3) {
            if(collisionString[1].equals("increment")) {
                id_of_cats_beneath.add(Integer.parseInt(collisionString[2]));
            } else if (collisionString[1].equals("decrement")) {
                for (int j = 0; j < id_of_cats_beneath.size; j++) {
                    if (Integer.parseInt(collisionString[2]) == id_of_cats_beneath.get(j)) {
                        id_of_cats_beneath.removeIndex(j);
                    }
                }
            }
        }
        
        if (action == Enums.Action.IDLE && collisionString[0].equals("cat_collision")) {
            action = Enums.Action.MOVING;
        } else if (action == Enums.Action.MOVING) {        
            if (render_position.y <= max_height) {
                move_increment += 1 / Constants.PPM;
            } else {
                action = Enums.Action.POISED;
            }
        } else if (action == Enums.Action.POISED) {
            if (collisionString[0].equals("cat_collision") || id_of_cats_beneath.size > 0) {
                action = Enums.Action.ATTACKING;
            }
        } else if (action == Enums.Action.ATTACKING) {
            if (render_position.y >= ground) {
                move_increment -= 12 / Constants.PPM;
            } else {
                action = Enums.Action.IDLE;
                for (Entity cat: entity_list) {
                    for (int cat_to_die = 0; cat_to_die < id_of_cats_beneath.size; cat_to_die++){
                        if (cat.getId() == id_of_cats_beneath.get(cat_to_die)) {
                            cat.set_dead(true);
                        }
                    }
                }
            }
        }
        body.setUserData(Constants.THWOMPER_IDLE_SPRITE1);

        render_position.set(
            (body.getPosition().x - Constants.THWOMPER_PIXEL_WIDTH / 2 / Constants.PPM) + .075f,
            body.getPosition().y + move_increment - Constants.THWOMPER_PIXEL_HEIGHT / 2 / Constants.PPM
        );
        shadow.update(delta);
    }

    public void dispose() {b2d_world.dispose();}

    private void init_body() {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyType.StaticBody;
        bdef.position.set(
            (render_position.x + (Constants.THWOMPER_PIXEL_WIDTH / 2 / Constants.PPM) / 2),
            (render_position.y + (Constants.THWOMPER_PIXEL_HEIGHT / Constants.PPM) / 2)
        );

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.THWOMPER_HALF_WIDTH, Constants.THWOMPER_HALF_HEIGHT);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        // fdef.density = Constants.THWOMPER_DENSITY;
        // fdef.friction = 0;
        // fdef.restitution = Constants.THWOMPER_RESTITUTION;
		fdef.filter.categoryBits = Constants.B2D_THWOMPER;
        fdef.filter.maskBits = Constants.B2D_BIT_CAT1S | Constants.B2D_YARN_BALLS;
        fdef.isSensor = true;

		body = b2d_world.createBody(bdef);
        body.createFixture(fdef).setUserData(Constants.THWOMPER_IDLE_SPRITE1);
        body.setUserData(Constants.THWOMPER_IDLE_SPRITE1);
    }
}