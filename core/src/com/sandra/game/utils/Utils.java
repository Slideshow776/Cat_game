package com.sandra.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class Utils {

    private static float scaleX, scaleY; 

    public static void drawTextureRegion(SpriteBatch batch, TextureRegion region, Vector2 position) {
        drawTextureRegion(batch, region, position.x, position.y);
    }

    public static void drawTextureRegion(SpriteBatch batch, TextureRegion region, Vector2 position, Vector2 offset) {
        drawTextureRegion(batch, region, position.x - offset.x, position.y - offset.y);
    }

    public static void drawTextureRegion(SpriteBatch batch, TextureRegion region, float x, float y) {
        if (Gdx.app.getType() == ApplicationType.Desktop) {scaleX = scaleY = .5f;}
        else {scaleX = scaleY = 1f;}
        //System.out.println(region.getRegionWidth() + ", " + region.getRegionHeight() + " ");
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

    public static float secondsSince(long timeNanos) {
        return MathUtils.nanoToSec * (TimeUtils.nanoTime() - timeNanos);
    }
}