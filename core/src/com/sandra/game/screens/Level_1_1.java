package com.sandra.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.sandra.game.Cat_game;
import com.sandra.game.entities.Cat1;
import com.sandra.game.entities.Entity;
import com.sandra.game.entities.Hole;
import com.sandra.game.handlers.Controls;
import com.sandra.game.utils.Assets;
import com.sandra.game.utils.Constants;
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
    private Music generic_music;
    private DelayedRemovalArray<Entity> cat1s;
    private DelayedRemovalArray<Entity> entities;
    private Entity hole;

    private Controls controls;
    
    public Level_1_1(Cat_game game) {
        this.game = game;
        pause = false;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.GAME_WIDTH / Constants.PPM, Constants.GAME_HEIGHT / Constants.PPM);

        // box2d
        b2d_world = new World(new Vector2(0, 0), true);
        myContactListener = new MyContactListener();
        b2d_world.setContactListener(myContactListener);
        if (Constants.B2D_DEBUGGING) {b2d_Renderer = new Box2DDebugRenderer();}
        box2d = new box2D(b2d_world);
        box2d.set_world_bounds();             

        AssetManager assetManager = new AssetManager();
        Assets.instance.init(assetManager);

        // entities
        cat1s = new DelayedRemovalArray<Entity>();
        cat1s.add(new Cat1(new Vector2(
                        (Constants.GAME_WIDTH / 2 + 150) / Constants.PPM,
                        (Constants.GAME_HEIGHT / 2 - 175) / Constants.PPM),
                    b2d_world));

        cat1s.add(new Cat1(new Vector2(
                        (Constants.GAME_WIDTH / 2 + 200) / Constants.PPM,
                        (Constants.GAME_HEIGHT / 2 + 200) / Constants.PPM),
                    b2d_world));
        cat1s.add(new Cat1(new Vector2(
                        (Constants.GAME_WIDTH / 2 - 100) / Constants.PPM,
                        (Constants.GAME_HEIGHT / 2 - 100) / Constants.PPM),
                    b2d_world));
        cat1s.add(new Cat1(new Vector2(
                        (Constants.GAME_WIDTH / 2 + 150) / Constants.PPM,
                        (Constants.GAME_HEIGHT / 2 - 300) / Constants.PPM),
                    b2d_world));
        cat1s.add(new Cat1(new Vector2(
                        (Constants.GAME_WIDTH / 2 - 300) / Constants.PPM,
                        (Constants.GAME_HEIGHT / 2 + 225) / Constants.PPM),
                    b2d_world));

        hole = new Hole(new Vector2(
                (Constants.GAME_WIDTH / 2) / Constants.PPM,
                (Constants.GAME_HEIGHT / 2) / Constants.PPM),
            b2d_world);

        // controls
        entities = new DelayedRemovalArray<Entity>();
        entities.addAll(cat1s);
        controls = new Controls(entities);

        //sounds
        score1 = Gdx.audio.newSound(Gdx.files.internal("sounds/score1.wav"));
        purr1 = Gdx.audio.newSound(Gdx.files.internal("sounds/purr1.wav"));
        purr1.play();

        //music
        generic_music = Gdx.audio.newMusic(Gdx.files.internal("music/video-game-7.wav"));
        generic_music.setLooping(true);
        generic_music.setVolume(.1f);
    }

    private void update(float delta) {
        b2d_world.step(Constants.B2D_TIMESTEP, Constants.B2D_VELOCITY_ITERATIONS, Constants.B2D_POSITION_ITERATIONS);
        controls.update(delta);
        game.batch.setProjectionMatrix(camera.combined);

        // entities
        hole.update(delta);
        for (Entity cat1:cat1s) {
            cat1.update(delta);
            System.out.println(cat1.get_body().getUserData());
            if (cat1.get_body().getUserData() == "win_condition") {
                System.out.println("TRYING TO REMOVE");
                score1.play();
                cat1.dispose();
                cat1s.removeValue(cat1, false);
            }
        }
    }

    @Override
    public void render(float delta) {	
        if (!pause) {update(delta);}
        //camera.update();
        
        //Gdx.gl.glClearColor(1, .7f, 1, 1); // light pink
        Gdx.gl.glClearColor(0, 0, 0, 1); // black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);                

        game.batch.begin();        
        hole.render(game.batch);
        for (Entity cat1: cat1s) {cat1.render(game.batch);}
        game.batch.end();
        
        if (Constants.B2D_DEBUGGING) {b2d_Renderer.render(b2d_world, camera.combined);}
    }

    @Override
    public void resize(int width, int height) {
        
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
        generic_music.play();		
    }

    @Override
    public void dispose () {
        Assets.instance.dispose();
        for (Entity cat1:cat1s) {
            controls.removeEntity(cat1.getId());
            cat1.dispose();
        }
        hole.dispose();
        generic_music.dispose();
        purr1.dispose();
        b2d_world.dispose();
        b2d_Renderer.dispose();
    }    
}
