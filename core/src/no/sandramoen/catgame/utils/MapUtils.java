package com.sandra.game.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.sandra.game.entities.Cat1;
import com.sandra.game.entities.Coin;
import com.sandra.game.entities.Entity;
import com.sandra.game.entities.Portal;
import com.sandra.game.entities.Saw_Blade;
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
            else if (entity == "saw_blades")
                entity_list.add(new Saw_Blade(position, b2d_world));
        }
        return entity_list;
    }

    public Array<Vector2> get_position_from_map(String entity, Array<Vector2> list, TiledMap map) {
        for (MapObject object : map.getLayers().get(entity).getObjects().getByType(RectangleMapObject.class)) {
            Vector2 position = new Vector2(((RectangleMapObject) object).getRectangle().getX() / Constants.PPM,
                    ((RectangleMapObject) object).getRectangle().getY() / Constants.PPM);
        
            if (entity == "lava_bubble_bursts")
                list.add(position);
        }
        return list;
    }
}
