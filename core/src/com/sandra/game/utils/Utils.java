package com.sandra.game.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class Utils {

    public static void drawTextureRegion(SpriteBatch batch, TextureRegion region, Vector2 position) {
        drawTextureRegion(batch, region, position.x, position.y);
    }

    public static void drawTextureRegion(SpriteBatch batch, TextureRegion region, Vector2 position, Vector2 offset) {
        drawTextureRegion(batch, region, position.x - offset.x, position.y - offset.y);
    }

    public static void drawTextureRegion(SpriteBatch batch, TextureRegion region, float x, float y) {
        batch.draw(
                region.getTexture(),
                x,
                y,
                0,
                0,
                (region.getRegionWidth()*1f) / Constants.PPM,    // pixel width
                (region.getRegionHeight()*1f) / Constants.PPM,   // pixel height
                1,
                1,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);
    }

    public static void drawTextureRegion(SpriteBatch batch, TextureRegion region, float x, float y, boolean flipx) {
        batch.draw(
                region.getTexture(),
                x,
                y,
                0,
                0,
                (region.getRegionWidth()*1f) / Constants.PPM,    // pixel width
                (region.getRegionHeight()*1f) / Constants.PPM,   // pixel height
                1,
                1,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                flipx,
                false);
    }

    public static float secondsSince(float animation_start_time) {
        return MathUtils.nanoToSec * (TimeUtils.nanoTime() - animation_start_time);
    }

	public static void drawTextureRegion(SpriteBatch batch, TextureRegion region, float x, float y, float scale) {
        batch.draw(
                region.getTexture(),
                x,
                y,
                0,
                0,
                (region.getRegionWidth()*1f) / Constants.PPM,    // pixel width
                (region.getRegionHeight()*1f) / Constants.PPM,   // pixel height
                scale,
                scale,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);
    }
    
    public static void drawTextureRegion(SpriteBatch batch, TextureRegion region, float x, float y, float scale, boolean normal) {
        batch.draw(
                region.getTexture(),
                x,
                y,
                0,
                0,
                region.getRegionWidth()*1f,    // pixel width
                region.getRegionHeight()*1f,   // pixel height
                scale,
                scale,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);
	}

	public static void drawTextureRegion(SpriteBatch batch, TextureRegion region, float x, float y, boolean flipx, float scale) {
                batch.draw(
                region.getTexture(),
                x,
                y,
                0,
                0,
                (region.getRegionWidth()*1f) / Constants.PPM,    // pixel width
                (region.getRegionHeight()*1f) / Constants.PPM,   // pixel height
                scale,
                scale,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                flipx,
                false);
	}
}
