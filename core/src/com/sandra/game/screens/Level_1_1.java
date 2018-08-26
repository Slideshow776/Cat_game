package com.sandra.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sandra.game.Cat_game;
import com.sandra.game.entities.Cat1;
import com.sandra.game.entities.Coin;
import com.sandra.game.entities.Entity;
import com.sandra.game.entities.Portal;
import com.sandra.game.entities.Yarn_Ball;
import com.sandra.game.handlers.Controls;
import com.sandra.game.utils.Assets;
import com.sandra.game.utils.Constants;
import com.sandra.game.utils.MapUtils;
import com.sandra.game.handlers.MyContactListener;
import com.sandra.game.utils.box2D;

public class Level_1_1 implements Screen {

    public static final String TAG = Level_1_1.class.getName();

    private Cat_game game;
    private OrthographicCamera camera;
    private Boolean pause;

    private World b2d_world;
    private Box2DDebugRenderer b2d_Renderer;
    private box2D box2d;
    private MyContactListener myContactListener;

    private Sound purr1;
    private Sound score1;
    private Sound coin1;
    private Sound bump1;
    private Music generic_music;

    private DelayedRemovalArray<Entity> cat1s;
    private DelayedRemovalArray<Entity> coins;
    private DelayedRemovalArray<Entity> yarn_balls;
    private DelayedRemovalArray<Entity> entities;
    private DelayedRemovalArray<Entity> portals;
	private DelayedRemovalArray<Entity> thwompers;

    private Controls controls;

    private TiledMap map;
    private OrthoCachedTiledMapRenderer mapRenderer;

    private int[] backgroundLayers = { 0, 1 };
    private int[] foregroundLayers = { 2 };

    private MapUtils mapUtils;


    public Level_1_1(Cat_game game) {
        this.game = game;
        pause = false;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.GAME_WIDTH / Constants.PPM, Constants.GAME_HEIGHT / Constants.PPM);
        game.batch.setProjectionMatrix(camera.combined);

        // box2d
        b2d_world = new World(new Vector2(0, 0), true);
        myContactListener = new MyContactListener();
        b2d_world.setContactListener(myContactListener);
        if (Constants.B2D_DEBUGGING) {
            b2d_Renderer = new Box2DDebugRenderer();
        }
        box2d = new box2D(b2d_world);
        box2d.set_world_bounds();

        AssetManager assetManager = new AssetManager();
        Assets.instance.init(assetManager);

        // sounds
        score1 = Gdx.audio.newSound(Gdx.files.internal("sounds/score1.wav"));
        coin1 = Gdx.audio.newSound(Gdx.files.internal("sounds/coin1.wav"));
        purr1 = Gdx.audio.newSound(Gdx.files.internal("sounds/purr1.wav"));
        bump1 = Gdx.audio.newSound(Gdx.files.internal("sounds/yarn_ball_bump1.wav"));

        // music
        generic_music = Gdx.audio.newMusic(Gdx.files.internal("music/video-game-7.wav"));
        generic_music.setLooping(true);
        generic_music.setVolume(.1f);

        // map
        map = new TmxMapLoader().load("levels/level1-1.tmx");
        mapRenderer = new OrthoCachedTiledMapRenderer(map, 1 / Constants.PPM, 8191); // 8191 is max
        mapRenderer.setView(camera);
        MapUtils mapUtils = new MapUtils();

        // Entities
        cat1s = new DelayedRemovalArray<Entity>();
        mapUtils.populate_entity_from_map("cats", cat1s, map, b2d_world);

        coins = new DelayedRemovalArray<Entity>();
        mapUtils.populate_entity_from_map("coins", coins, map, b2d_world);

        yarn_balls = new DelayedRemovalArray<Entity>();
        mapUtils.populate_entity_from_map("yarn_balls", yarn_balls, map, b2d_world);

        portals = new DelayedRemovalArray<Entity>();
        mapUtils.populate_entity_from_map("portals", portals, map, b2d_world);
        
        // thwompers
        thwompers = new DelayedRemovalArray<Entity>();
        mapUtils.populate_entity_from_map("thwompers", thwompers, map, b2d_world);

        box2d.set_world_impassables(map);
        box2d.popuate_zones_from_map(map);

        // controls
        entities = new DelayedRemovalArray<Entity>();
        entities.addAll(cat1s);
        controls = new Controls(entities);
    }

    private void update_entities(float delta) {
        for (Entity portal : portals) {
            portal.update(delta);
        }

        for (Entity cat1 : cat1s) {
            if (cat1.get_body().getUserData() == "win_condition") {
                score1.play();
                cat1.dispose();
                cat1s.removeValue(cat1, false);
            }
            cat1.update(delta);
        }

        for (Entity coin : coins) {
            if (coin.get_body().getUserData() == "remove_me") {
                coin1.play();
                coin.dispose();
                coins.removeValue(coin, false);
            }
            coin.update(delta);
        }

        for (Entity yarn_ball : yarn_balls) {
            if (yarn_ball.get_body().getUserData() == "collision") {
                bump1.play();
            }
            yarn_ball.update(delta);
        }

        for (Entity thwomper: thwompers) {
            thwomper.update(delta);
        }
    }

    private void update(float delta) {
        camera.update();
        b2d_world.step(Constants.B2D_TIMESTEP, Constants.B2D_VELOCITY_ITERATIONS, Constants.B2D_POSITION_ITERATIONS);
        controls.update(delta);
        game.batch.setProjectionMatrix(camera.combined);
        update_entities(delta);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1); // black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (!pause)
            update(delta);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        mapRenderer.render(backgroundLayers);
        Gdx.gl.glDisable(GL20.GL_BLEND);

        game.batch.begin();
        for (Entity portal : portals)
            portal.render(game.batch);
        for (Entity coin : coins)
            coin.render(game.batch);
        for (Entity yarn_ball : yarn_balls)
            yarn_ball.render(game.batch);
        for (Entity cat1 : cat1s)
            cat1.render(game.batch);
        for (Entity thwomper: thwompers)
            thwomper.render(game.batch);
        game.batch.end();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        mapRenderer.render(foregroundLayers);
        Gdx.gl.glDisable(GL20.GL_BLEND);

        if (Constants.B2D_DEBUGGING)
            b2d_Renderer.render(b2d_world, camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, (Constants.GAME_WIDTH) / Constants.PPM, (Constants.GAME_HEIGHT) / Constants.PPM);
        game.batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {
        pause = !pause;
        generic_music.pause();
    }

    @Override
    public void resume() {
        pause = !pause;
        generic_music.play();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void show() {
        purr1.play();
        generic_music.play();
    }

    @Override
    public void dispose() {
        Assets.instance.dispose();
        for (Entity cat1 : cat1s) {
            controls.removeEntity(cat1.getId());
            cat1.dispose();
        }
        for (Entity portal : portals)
            portal.dispose();
        for (Entity coin : coins)
            coin.dispose();
        for (Entity yarn_ball : yarn_balls)
            yarn_ball.dispose();
        for (Entity thwomper : thwompers) {
            thwomper.dispose();
        }
        generic_music.dispose();
        purr1.dispose();
        b2d_world.dispose();
        b2d_Renderer.dispose();
        mapRenderer.dispose();
    }
}
