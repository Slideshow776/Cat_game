package com.sandra.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.sandra.game.utils.Assets;
import com.sandra.game.utils.Constants;
import com.sandra.game.utils.Utils;

public class Dust extends Entity {
    private float start_time;
    private long animation_start_time;

    public Dust(Vector2 position) {
        render_position = position;
        start_time = TimeUtils.nanoTime();
        delete = false;
        animation_start_time = TimeUtils.nanoTime();
    }

    public void render(SpriteBatch batch) {
        float animation_time_seconds = Utils.secondsSince(animation_start_time);
        TextureRegion region = Assets.instance.dustAssets.dust_animation.getKeyFrame(animation_time_seconds);
        Utils.drawTextureRegion(batch, region, render_position);
    }

    public void update(float delta) {
        float elapsed_time_seconds = Utils.secondsSince(start_time);
        if (elapsed_time_seconds > Constants.DUST_DURATION) { delete = true; }
    }

    public void dispose() { delete = true; }
}
