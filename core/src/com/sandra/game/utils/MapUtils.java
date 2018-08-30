package com.sandra.game.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.sandra.game.entities.Cat1;
import com.sandra.game.entities.Coin;
import com.sandra.game.entities.Entity;
import com.sandra.game.entities.Portal;
import com.sandra.game.entities.Thwomper;
import com.sandra.game.entities.Yarn_Ball;

public class MapUtils {
    
    private int catId = 0;

    public DelayedRemovalArray<Entity> populate_entity_from_map(String entity, DelayedRemovalArray<Entity> entity_list,
     TiledMap map, World b2d_world, DelayedRemovalArray<Entity> second_entity_list) {
        for (MapObject object : map.getLayers().get(entity).getObjects().getByType(RectangleMapObject.class)) {
            Vector2 position = new Vector2(((RectangleMapObject) object).getRectangle().getX() / Constants.PPM,
                    ((RectangleMapObject) object).getRectangle().getY() / Constants.PPM);
            if (entity == "cats") {                
                catId++;
                entity_list.add(new Cat1(position, b2d_world, catId));
            }
            else if (entity == "coins")
                entity_list.add(new Coin(position, b2d_world));
            else if (entity == "yarn_balls")
                entity_list.add(new Yarn_Ball(position, b2d_world));
            else if (entity == "portals")
                entity_list.add(new Portal(position, b2d_world));
            else if (entity == "thwompers")
                entity_list.add(new Thwomper(position, b2d_world, second_entity_list));
        }
        return entity_list;
    }
}