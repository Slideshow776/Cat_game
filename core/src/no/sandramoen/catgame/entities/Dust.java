package com.sandra.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
    private boolean isRegular;
    private TextureRegion region;
    private Sound slide;
    private float animation_time_seconds;
    private float elapsed_time_seconds;

    public Dust(Vector2 position, boolean isRegular) {
        this.isRegular = isRegular;
        render_position = position;
        start_time = TimeUtils.nanoTime();
        delete = false;
        animation_start_time = TimeUtils.nanoTime();
        
        slide = Gdx.audio.newSound(Gdx.files.internal("sounds/slide.wav"));
        slide.play();        
    }

    public void render(SpriteBatch batch) {
        animation_time_seconds = Utils.secondsSince(animation_start_time);
        if (isRegular) region = Assets.instance.dustAssets.dust_animation.getKeyFrame(animation_time_seconds);
        else region = Assets.instance.dustAssets.grey_dust_animation.getKeyFrame(animation_time_seconds);
        Utils.drawTextureRegion(batch, region, render_position);
    }

    public void update(float delta) {
        elapsed_time_seconds = Utils.secondsSince(start_time);
        if (elapsed_time_seconds > Constants.DUST_DURATION) { delete = true; }
    }

    public void dispose() {
        delete = true; 
        slide.dispose();
    }
}
