package com.sandra.game.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.sandra.game.Cat_game;
import com.sandra.game.entities.Blood;
import com.sandra.game.entities.Cat1;
import com.sandra.game.entities.Coin;
import com.sandra.game.entities.Dust;
import com.sandra.game.entities.Entity;
import com.sandra.game.entities.Lava_bubble_burst;
import com.sandra.game.entities.Portal;
import com.sandra.game.entities.Yarn_Ball;
import com.sandra.game.entities.Shadow;
import com.sandra.game.entities.Cat1_part;
import com.sandra.game.handlers.Controls;
import com.sandra.game.utils.Assets;
import com.sandra.game.utils.Constants;
import com.sandra.game.utils.HUD;
import com.sandra.game.utils.MapUtils;
import com.sandra.game.handlers.MyContactListener;
import com.sandra.game.utils.box2D;

public abstract class Level implements Screen {

    public static final String TAG = Level.class.getName();

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
	private DelayedRemovalArray<Entity> shadows;
    private DelayedRemovalArray<Entity> annihilate_dusts;
    private Array<Vector2> lava_bubble_bursts_positions;
    private DelayedRemovalArray<Entity> saw_blades;
    private DelayedRemovalArray<Entity> cat1_parts;

    private Controls controls;

    private TiledMap map;
    private OrthoCachedTiledMapRenderer mapRenderer;
    private int[] backgroundLayers = { 0, 1 };
    private int[] foregroundLayers = { 2 };
    private MapUtils mapUtils;

    private Array<Blood> blood_list;
    private float blood_timer;

    private HUD hud;
    private int coinScore, cat1Score, cat1DeadScore, numCoins;

    private Sprite fade_transition;
    private float transition_alpha;

    private int total_num_cat1s;
    private int total_cat1s_annihilated;

    private DelayedRemovalArray<Entity> lava_bubble_bursts;

    private float lava_bubble_burst_timer;

    private float lava_bubble_burst_ratio;

    private float end_level_timer;


    public Level(Cat_game game, String level_filename) {
        this.game = game;
        pause = false;
        end_level_timer = 0f;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.GAME_WIDTH / Constants.PPM, Constants.GAME_HEIGHT / Constants.PPM);
        game.batch.setProjectionMatrix(camera.combined);

        // box2d
        b2d_world = new World(new Vector2(0, 0), true);
        myContactListener = new MyContactListener();
        b2d_world.setContactListener(myContactListener);
        if (Constants.B2D_DEBUGGING) b2d_Renderer = new Box2DDebugRenderer();
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
        map = new TmxMapLoader().load(level_filename);
        mapRenderer = new OrthoCachedTiledMapRenderer(map, 1 / Constants.PPM, 8191); // 8191 is max
        mapRenderer.setView(camera);
        mapUtils = new MapUtils();

        // Entities
        cat1s = new DelayedRemovalArray<Entity>();
        mapUtils.populate_entity_from_map("cats", cat1s, map, b2d_world, null);

        coins = new DelayedRemovalArray<Entity>();
        mapUtils.populate_entity_from_map("coins", coins, map, b2d_world, null);

        yarn_balls = new DelayedRemovalArray<Entity>();
        mapUtils.populate_entity_from_map("yarn_balls", yarn_balls, map, b2d_world, null);

        portals = new DelayedRemovalArray<Entity>();
        mapUtils.populate_entity_from_map("portals", portals, map, b2d_world, null);
        
        thwompers = new DelayedRemovalArray<Entity>();
        mapUtils.populate_entity_from_map("thwompers", thwompers, map, b2d_world, cat1s);

        shadows = new DelayedRemovalArray<Entity>();
        for (Entity thwomper: thwompers) { shadows.add(new Shadow(thwomper.get_render_position())); }

        saw_blades = new DelayedRemovalArray<Entity>();
        mapUtils.populate_entity_from_map("saw_blades", saw_blades, map, b2d_world, null);

        lava_bubble_bursts = new DelayedRemovalArray<Entity>();
        lava_bubble_bursts_positions = new Array<Vector2>();
        mapUtils.get_position_from_map("lava_bubble_bursts", lava_bubble_bursts_positions, map);        
        lava_bubble_burst_ratio = 1;
        
        annihilate_dusts = new DelayedRemovalArray<Entity>();
        cat1_parts = new DelayedRemovalArray<Entity>();        

        blood_list = new Array<Blood>();
        blood_timer = 0;
        total_num_cat1s = cat1s.size;
        total_cat1s_annihilated = 0;

        box2d.set_world_impassables(map);
        box2d.popuate_zones_from_map(map, "zone 1"); // land
        box2d.popuate_zones_from_map(map, "zone 2"); // lava

        // controls
        entities = new DelayedRemovalArray<Entity>();
        entities.addAll(cat1s);
        controls = new Controls(entities);

        // HUD
        hud = new HUD(game, camera, this);
        coinScore = cat1Score = cat1DeadScore = 0;
        numCoins = coins.size;

        // Transition effect
        fade_transition = new Sprite(new Texture("images/black_screen.png"));
        fade_transition .setSize(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        fade_transition .setPosition(0, 0);
        transition_alpha = 1f;
    }

    public void set_pause(boolean pause) { this.pause = pause; }
    public boolean get_pause() { return pause; }

    private void update_entities(float delta) {
        for (Entity portal : portals) {
            portal.update(delta);
        }

        for (Entity cat1 : cat1s) {
            if (cat1.get_body().getUserData() == "win_condition") {
                score1.play();
                cat1.dispose();
                cat1s.removeValue(cat1, false);
                cat1Score++;
            }
            if (cat1.is_dead()) {
                cat1DeadScore++;
                // purr1.play();
                // cat1.dispose();
                // cat1s.removeValue(cat1, false);
            }
            if (cat1.is_annihilated()) {
                total_cat1s_annihilated++;
                annihilate_dusts.add(new Dust(
                    new Vector2(
                        cat1.get_render_position().x - Constants.CAT1_HALF_WIDTH,
                        cat1.get_render_position().y - Constants.CAT1_HALF_HEIGHT),
                    false
                ));
                cat1.get_body().setTransform(new Vector2(-99, -99), 0);
                cat1s.removeValue(cat1, false);
            } else if (cat1.is_cut()) {

                cat1.set_cut(false);
                cat1.set_dead(true);
                cat1DeadScore++;

                // Split the cat1 in two body parts
                Cat1_part top_part = new Cat1_part(new Vector2(cat1.get_render_position()), b2d_world, true);
                Cat1_part bottom_part = new Cat1_part(new Vector2(cat1.get_render_position()), b2d_world, false);                
                controls.add_entities(top_part);
                controls.add_entities(bottom_part);
                cat1_parts.add(top_part);
                cat1_parts.add(bottom_part);

                cat1.get_body().setTransform(new Vector2(-99, -99), 0);
                cat1s.removeValue(cat1, false);
            }
            cat1.update(delta);
        }

        for (Entity cat1_part : cat1_parts) {
            if (cat1_part.get_body().getUserData() == "win_condition") {
                score1.play();
                cat1_part.dispose();
                cat1_parts.removeValue(cat1_part, false);
                cat1Score += .5f;
            } else if (cat1_part.is_annihilated()) {
                total_cat1s_annihilated += .5f;
                annihilate_dusts.add(new Dust(
                    new Vector2(
                        cat1_part.get_render_position().x - Constants.CAT1_HALF_WIDTH,
                        cat1_part.get_render_position().y - Constants.CAT1_HALF_HEIGHT),
                    false
                ));
                cat1_part.get_body().setTransform(new Vector2(-99, -99), 0);
                cat1_parts.removeValue(cat1_part, false);
            }
            cat1_part.update(delta);
        }

        for (Entity coin : coins) {
            if (coin.get_body().getUserData() == "remove_me") {
                coin1.play();
                coin.dispose();
                coins.removeValue(coin, false);
                coinScore++;
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
            for (Entity shadow: shadows) {
                shadow.set_action(thwomper.get_action());
                shadow.update(delta);
            }
        }

        for (Entity cat1_part : cat1_parts) {
            cat1_part.increment_blood_timer(delta);
            blood_timer += delta;
            if(cat1_part.get_blood_timer() >= Constants.BLOOD_GENERATION_RATIO) {
                blood_list.add(new Blood(new Vector2(cat1_part.get_render_position()), true));
                cat1_part.set_blood_timer(0);
            }
        }

        for (Entity cat1 : cat1s) {
            if (cat1.is_dead()) {
                cat1.increment_blood_timer(delta);
                blood_timer += delta;
                if(cat1.get_blood_timer() >= Constants.BLOOD_GENERATION_RATIO) {
                    blood_list.add(new Blood(new Vector2(cat1.get_render_position()), true));
                    cat1.set_blood_timer(0);
                }
            }
        }

        for (Entity bubble : lava_bubble_bursts) {
            bubble.update(delta);
            if (bubble.get_delete()) lava_bubble_bursts.removeValue(bubble, false);
        }

        for (Entity dust : annihilate_dusts) { 
            dust.update(delta);
            if (dust.get_delete()) annihilate_dusts.removeValue(dust, false);
        }

        for (Entity saw_blade : saw_blades) {
            saw_blade.update(delta);
        }
    }

    private void end_level_condition(float delta) {
        if (cat1s.size == 0 && cat1_parts.size == 0) {
            end_level_timer += delta;
            if (end_level_timer >= .9f) {
                dispose();
                ((Cat_game) Gdx.app.getApplicationListener()).setScreen(
                    new Score(
                        game,
                        total_num_cat1s == total_cat1s_annihilated,
                        coinScore,
                        numCoins,
                        cat1DeadScore
                    )
                );
            }
        }
    }

    private void create_and_update_lava_bubble_burst(float delta) {
        // when random time create and add to list a new random Lava_bubble_burst entity from positions list
        if (lava_bubble_bursts_positions.size > 0) {
            lava_bubble_burst_timer += delta;
            if (lava_bubble_burst_timer >= lava_bubble_burst_ratio) {
                
                Random random = new Random();
                int random_position = random.nextInt(lava_bubble_bursts_positions.size);

                lava_bubble_bursts.add(new Lava_bubble_burst(lava_bubble_bursts_positions.get(random_position)));
                lava_bubble_burst_timer -= lava_bubble_burst_ratio;
            }
        }
    }

    private void update(float delta) {
        if (!pause) {
            end_level_condition(delta);
            camera.update();
            b2d_world.step(Constants.B2D_TIMESTEP, Constants.B2D_VELOCITY_ITERATIONS, Constants.B2D_POSITION_ITERATIONS);
            controls.update(delta);
            game.batch.setProjectionMatrix(camera.combined);
            update_entities(delta);
            create_and_update_lava_bubble_burst(delta);
            if (transition_alpha > 0) { transition_alpha -= .03f; }
            else if (transition_alpha <= 0) { transition_alpha = 0; }
        }
        hud.update(delta, cat1Score, coinScore);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         
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
        for (Entity shadow: shadows)
            shadow.render(game.batch);
        for (Blood blood: blood_list) 
            blood.render(game.batch);
        for (Entity cat1 : cat1s) 
            cat1.render(game.batch);
        for (Entity thwomper: thwompers)
            thwomper.render(game.batch);
        for (Entity bubble : lava_bubble_bursts)
            bubble.render(game.batch);
        for (Entity dust : annihilate_dusts) 
            dust.render(game.batch);
        for (Entity saw_blade : saw_blades)
            saw_blade.render(game.batch);
        for (Entity cat1_part : cat1_parts)
            cat1_part.render(game.batch);
        hud.render(game.batch);
        game.batch.end();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        mapRenderer.render(foregroundLayers);
        Gdx.gl.glDisable(GL20.GL_BLEND);

        if (Constants.B2D_DEBUGGING)
            b2d_Renderer.render(b2d_world, camera.combined);

        game.batch.begin();
        if (transition_alpha >= 0)
            fade_transition.draw(game.batch, transition_alpha);
        game.batch.end();
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
        for (Entity thwomper : thwompers)
            thwomper.dispose();
        for (Blood blood : blood_list)
            blood.dispose();
        for (Entity saw_blade : saw_blades)
            saw_blade.dispose();
        for (Entity cat1_part: cat1_parts)
            cat1_part.dispose();
        generic_music.dispose();
        purr1.dispose();
        // b2d_world.dispose();
        // b2d_Renderer.dispose();
        // mapRenderer.dispose();
        hud.dispose();
        fade_transition.getTexture().dispose();
    }
}
