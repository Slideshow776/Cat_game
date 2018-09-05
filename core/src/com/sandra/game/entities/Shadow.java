package com.sandra.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.sandra.game.utils.Assets;
import com.sandra.game.utils.Enums;
import com.sandra.game.utils.Utils;

public class Shadow extends Entity {
    private long animation_start_time;
    private TextureRegion region;

    public Shadow(Vector2 position) {
        render_position = new Vector2(position.x, position.y - .1f);
        animation_start_time = TimeUtils.nanoTime();
        action = Enums.Action.IDLE;
        region = Assets.instance.shadowAssets.smallest;
    }

    public void render(SpriteBatch batch) {
        float animation_time_seconds = Utils.secondsSince(animation_start_time);
        
        if (action == Enums.Action.IDLE) {
            animation_start_time = TimeUtils.nanoTime();
            region = Assets.instance.shadowAssets.smallest;
        }
        else if (action == Enums.Action.MOVING) {
            System.out.println("MOVING");
            region = Assets.instance.shadowAssets.shadow_growing_animation.getKeyFrame(animation_time_seconds);
        }
        else if (action == Enums.Action.POISED) {
            animation_start_time = TimeUtils.nanoTime();
            region = Assets.instance.shadowAssets.largest;
        }
        else if (action == Enums.Action.ATTACKING) {
            System.out.println("ATTACKING");
            region = Assets.instance.shadowAssets.shadow_shrinking_animation.getKeyFrame(animation_time_seconds);            
        }

        Utils.drawTextureRegion(batch, region, render_position);
    }

    public void update(float delta) {}

    public void dispose() {}
}
