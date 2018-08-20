package com.sandra.game.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;

/* 
    Utility class for Box2D world implementations for levels.
    @author Sandra Moen
*/

public class box2D {

    World b2d_world;
	private Array<PolygonMapObject> land_polygon;

    public box2D(World b2d_world) {
        this.b2d_world = b2d_world;
    }

    public void popuate_zones_from_map(TiledMap map) {
        land_polygon = map.getLayers().get("zone 1").getObjects()
                .getByType(PolygonMapObject.class);

        for (int j = 0; j < land_polygon.size; j++) {
            float[] raw_verticies = land_polygon.get(j).getPolygon().getTransformedVertices();
            Vector2[] verticies = new Vector2[raw_verticies.length / 2];

            for (int i = 0; i < verticies.length; i++) {
                verticies[i] = new Vector2(
                    raw_verticies[i * 2] / Constants.PPM,
                    raw_verticies[(i * 2) + 1] / Constants.PPM
                );
            }

            BodyDef bdef = new BodyDef();
            bdef.type = BodyType.StaticBody;
            bdef.position.set(0, 0);

            PolygonShape shape = new PolygonShape();
            shape.set(verticies);

            FixtureDef fdef = new FixtureDef();
            fdef.shape = shape;
            fdef.filter.categoryBits = Constants.B2D_BIT_WORLD;
            fdef.filter.maskBits = Constants.B2D_BIT_CAT1S | Constants.B2D_YARN_BALLS;
            fdef.isSensor = true;

            Body land_zone = b2d_world.createBody(bdef);
            land_zone.createFixture(fdef).setUserData(Constants.B2D_LAND_ZONE);
        }
    }

    public void set_world_impassables(TiledMap map) {
        for (MapObject object : map.getLayers().get("impassable").getObjects().getByType(RectangleMapObject.class)) {
            Vector2 position = new Vector2(((RectangleMapObject) object).getRectangle().getX() / Constants.PPM,
                    ((RectangleMapObject) object).getRectangle().getY() / Constants.PPM);
            float width = ((RectangleMapObject) object).getRectangle().getWidth() / Constants.PPM;
            float height = ((RectangleMapObject) object).getRectangle().getHeight() / Constants.PPM;

            BodyDef bdef = new BodyDef();
            bdef.type = BodyType.StaticBody;
            bdef.position.set(
                position.x + width / 2,
                position.y + height / 2
            );

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(width / 2, height / 2);
            FixtureDef fdef = new FixtureDef();
            fdef.shape = shape;
            fdef.filter.categoryBits = Constants.B2D_BIT_WORLD;
            fdef.filter.maskBits = Constants.B2D_BIT_CAT1S | Constants.B2D_YARN_BALLS;

            Body impassable = b2d_world.createBody(bdef);
            impassable.createFixture(fdef).setUserData(Constants.B2D_WORLD);
        }
    }

    public void set_world_bounds() {
        // LOWER--------------------------------
		BodyDef bdef = new BodyDef();
        bdef.type = BodyType.StaticBody;
		bdef.position.set(
            (Constants.GAME_WIDTH / 2) / Constants.PPM,
            (-Constants.B2D_WORLD_BOUNDS_OFFSET) / Constants.PPM
        );

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(
            Constants.GAME_WIDTH / Constants.PPM,
            Constants.B2D_WORLD_BOUNDS_OFFSET / Constants.PPM
        );
        FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.filter.categoryBits = Constants.B2D_BIT_WORLD;
		fdef.filter.maskBits = Constants.B2D_BIT_CAT1S | Constants.B2D_YARN_BALLS;

		Body world_lower = b2d_world.createBody(bdef);
		world_lower.createFixture(fdef).setUserData(Constants.B2D_WORLD);
        
        // Upper -------------------------------
		bdef.position.set(
            (Constants.GAME_WIDTH / 2) / Constants.PPM,
            (Constants.GAME_HEIGHT + Constants.B2D_WORLD_BOUNDS_OFFSET) / Constants.PPM
        );

        //shape.setAsBox(Gdx.graphics.getWidth(), Constants.B2D_WORLD_BOUNDS_OFFSET);
		fdef.shape = shape;
		fdef.filter.categoryBits = Constants.B2D_BIT_WORLD;
		fdef.filter.maskBits = Constants.B2D_BIT_CAT1S | Constants.B2D_YARN_BALLS;

        Body world_upper = b2d_world.createBody(bdef);
        world_upper.createFixture(fdef).setUserData(Constants.B2D_WORLD);
        
        // Left --------------------------------
        bdef.position.set(
            (-Constants.B2D_WORLD_BOUNDS_OFFSET) / Constants.PPM,
            (Constants.GAME_HEIGHT / 2) / Constants.PPM
        );

        shape.setAsBox(
            (Constants.B2D_WORLD_BOUNDS_OFFSET) / Constants.PPM,
            (Constants.GAME_HEIGHT) / Constants.PPM
        );
		fdef.shape = shape;
		fdef.filter.categoryBits = Constants.B2D_BIT_WORLD;
		fdef.filter.maskBits = Constants.B2D_BIT_CAT1S | Constants.B2D_YARN_BALLS;

        Body world_left = b2d_world.createBody(bdef);
        world_left.createFixture(fdef).setUserData(Constants.B2D_WORLD);

        // Right -------------------------------
        bdef.position.set(
            (Constants.GAME_WIDTH + Constants.B2D_WORLD_BOUNDS_OFFSET) / Constants.PPM,
            (Constants.GAME_HEIGHT / 2) / Constants.PPM
        );
		fdef.shape = shape;
		fdef.filter.categoryBits = Constants.B2D_BIT_WORLD;
		fdef.filter.maskBits = Constants.B2D_BIT_CAT1S | Constants.B2D_YARN_BALLS;

        Body world_right = b2d_world.createBody(bdef);
        world_right.createFixture(fdef).setUserData(Constants.B2D_WORLD);
    }
}