package com.sandra.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.sandra.game.utils.Assets;
import com.sandra.game.utils.Utils;

public class Blood extends Entity {

    TextureRegion region;
    boolean splatter;
    boolean render_flipped;
    
    public Blood(Vector2 position, Boolean splatter) {
        render_position = position;
        this.splatter = splatter;
        if(Math.random() < .5f) render_flipped = true;
        else render_flipped = false;
    }

    public void render(SpriteBatch batch) {        
        if (splatter) region = Assets.instance.bloodAssets.bloodSplatter1;
        else region = Assets.instance.bloodAssets.bloodDrag1;
        Utils.drawTextureRegion(batch, region, render_position.x, render_position.y, render_flipped);
    }

    public void update(float delta) {}
    public void dispose() {}
}
