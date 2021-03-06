package com.sandra.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.sandra.game.Cat_game;
import com.sandra.game.handlers.ButtonListener;
import com.sandra.game.utils.Assets;
import com.sandra.game.utils.Constants;
import com.sandra.game.utils.Utils;

public class Menu implements Screen {

    private Cat_game game;
    private boolean pause = false;
    private OrthographicCamera camera;

    private Stage stage;
    private Table table;

    private TextureRegion menu_title_tex;
    private TextureRegion animation_region;
    private float animation_start_time;
    private Vector2 animation_pos;
    private float animation_scale;
    
    private Sprite background1;
    private float background1ScrollTimer;
	private float background1ScrollSpeed;
    private Sprite background2;
    private float background2ScrollTimer;
    private float background2ScrollSpeed;
    private boolean should_scale;
    private Sprite fade_transition;
    private float transition_alpha;
    private boolean[] is_level_selected;
    private boolean is_level_button_pressed;
    
    private Button level_1_1_btn;
    private ButtonListener level_1_1_btn_listener;
    private Button level_1_2_btn;
    private ButtonListener level_1_2_btn_listener;
    private Button level_1_3_btn;
    private ButtonListener level_1_3_btn_listener;
    private Button level_1_4_btn;
    private ButtonListener level_1_4_btn_listener;
    private Button level_2_1_btn;
    private ButtonListener level_2_1_btn_listener;
    private Button level_2_2_btn;
    private ButtonListener level_2_2_btn_listener;
    private Button level_2_3_btn;
    private ButtonListener level_2_3_btn_listener;
    private Button level_2_4_btn;
    private ButtonListener level_2_4_btn_listener;
    private Button level_3_1_btn;
    private ButtonListener level_3_1_btn_listener;
    private Button level_3_2_btn;
    private ButtonListener level_3_2_btn_listener;
    private Button level_3_3_btn;
    private ButtonListener level_3_3_btn_listener;
    private Button level_3_4_btn;
    private ButtonListener level_3_4_btn_listener;
    private Button level_4_1_btn;
    private ButtonListener level_4_1_btn_listener;
    private Button level_4_2_btn;
    private ButtonListener level_4_2_btn_listener;
    private Button level_4_3_btn;
    private ButtonListener level_4_3_btn_listener;
    private Button level_4_4_btn;
    private ButtonListener level_4_4_btn_listener;
	private Music generic_music;

    public Menu(Cat_game game, boolean should_scale) {
        this.game = game;
        this.should_scale = should_scale;
    }

    @Override
    public void show() {
        // music
        generic_music = Gdx.audio.newMusic(Gdx.files.internal("music/Dash Runner.wav"));
        generic_music.setLooping(true);
        generic_music.setVolume(.5f);
        generic_music.play();

        AssetManager assetManager = new AssetManager();
        Assets.instance.init(assetManager);
        
		camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        game.batch.setProjectionMatrix(camera.combined);

        // Stage
        stage = new Stage(new StretchViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT, camera));
        Gdx.input.setInputProcessor(stage);

        // Title
        menu_title_tex = Assets.instance.menuAssets.menu_title;
        Image menu_title_image = new Image(menu_title_tex);        
        animation_start_time = TimeUtils.nanoTime();
        animation_pos = new Vector2(550, 465);
        animation_scale = .25f;
        
        // Background
        background1 = new Sprite(new Texture("images/star_background1.png"));
        background1.getTexture().setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
        background1ScrollTimer = 0;
        background1ScrollSpeed = .04f;
        background2 = new Sprite(new Texture("images/star_background2.png"));
        background2.getTexture().setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
        background2ScrollTimer = 0;
        background2ScrollSpeed = .1f;

        // Buttons
        init_btns();

        // Table
        table = new Table();
        stage.addActor(table);
        table.setFillParent(true);
        // table.debug();
        table.add(menu_title_image).colspan(4).width(425*1.5f).height(107*1.5f);
        table.row();
        table.defaults().width(75).height(50);
        table.add(level_1_1_btn);
        table.add(level_1_2_btn);
        table.add(level_1_3_btn);
        table.add(level_1_4_btn);
        table.row();
        table.add(level_2_1_btn);
        table.add(level_2_2_btn);
        table.add(level_2_3_btn);
        table.add(level_2_4_btn);
        table.row();
        table.add(level_3_1_btn);
        table.add(level_3_2_btn);
        table.add(level_3_3_btn);
        table.add(level_3_4_btn);
        table.row();
        table.add(level_4_1_btn);
        table.add(level_4_2_btn);
        table.add(level_4_3_btn);
        table.add(level_4_4_btn);

        // Transition effect
        fade_transition = new Sprite(new Texture("images/black_screen.png"));
        fade_transition .setSize(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        fade_transition .setPosition(0, 0);
        transition_alpha = 1f;
        is_level_selected = new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
        is_level_button_pressed = false;
    }

    @Override
    public void render(float delta) {
        if (!pause) {update(delta);}
        
        float animation_time_seconds = Utils.secondsSince(animation_start_time);
        animation_region = Assets.instance.cat1Assets.cat1_sleeping_animation.getKeyFrame(animation_time_seconds);

        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1); // black
        
        game.batch.begin();
        if(should_scale) {
            game.batch.draw(background1, 0, 0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
            game.batch.draw(background2, 0, 0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        } else {
            game.batch.draw(background1, 0, 0, Constants.GAME_WIDTH / Constants.PPM, Constants.GAME_HEIGHT / Constants.PPM);
            game.batch.draw(background2, 0, 0, Constants.GAME_WIDTH / Constants.PPM, Constants.GAME_HEIGHT / Constants.PPM);
        }
        game.batch.end();

        stage.draw();

        game.batch.begin();        
        Utils.drawTextureRegion(game.batch, animation_region, animation_pos.x, animation_pos.y, animation_scale, true);
        if (transition_alpha >= 0)
            fade_transition.draw(game.batch, transition_alpha);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {
        generic_music.pause();
    }

    @Override
    public void resume() {
        generic_music.play();
    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        fade_transition.getTexture().dispose();
        generic_music.dispose();
        menu_title_tex.getTexture().dispose();
        animation_region.getTexture().dispose();
        background1.getTexture().dispose();
        background2.getTexture().dispose();
        fade_transition.getTexture().dispose();
        generic_music.dispose();
        Assets.instance.dispose();
        menu_title_tex.getTexture().dispose();
        level_1_1_btn.clear();
        level_1_2_btn.clear();
        level_1_3_btn.clear();
        level_1_4_btn.clear();
        level_2_1_btn.clear();
        level_2_2_btn.clear();
        level_2_3_btn.clear();
        level_2_4_btn.clear();
        level_3_1_btn.clear();
        level_3_2_btn.clear();
        level_3_3_btn.clear();
        level_3_4_btn.clear();
        level_4_1_btn.clear();
        level_4_2_btn.clear();
        level_4_3_btn.clear();
        level_4_4_btn.clear();
    }

    private void groundWrapScrolling1() {
		background1ScrollTimer += Gdx.graphics.getDeltaTime()*background1ScrollSpeed;
		if(background1ScrollTimer>1.0f) background1ScrollTimer = 0.0f;
		background1.setU(background1ScrollTimer);
		background1.setU2(background1ScrollTimer+1);	
    }
    
    private void groundWrapScrolling2() {
		background2ScrollTimer += Gdx.graphics.getDeltaTime()*background2ScrollSpeed;
		if(background2ScrollTimer>1.0f) background2ScrollTimer = 0.0f;
		background2.setU(background2ScrollTimer);
		background2.setU2(background2ScrollTimer+1);	
	}

    private void update(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        stage.act(delta);
        groundWrapScrolling1();
        groundWrapScrolling2();
        check_if_buttons_was_touched();
        transition();
    }

    private void check_if_buttons_was_touched() {
		if (level_1_1_btn_listener.getTouched() || is_level_selected[0]) {
            is_level_selected[0] = true;
            is_level_button_pressed = true;
            if (transition_alpha >= 1f) {
                transition_alpha = 1f;
                dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Splash_how_to_play(game));
            }
        }
		else if(level_1_2_btn_listener.getTouched() || is_level_selected[1]) {
            is_level_selected[1] = true;
            is_level_button_pressed = true;
            if (transition_alpha >= 1f) {
                transition_alpha = 1f;
                dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Level_loader(game, "level1-2"));
            }
        } else if(level_1_3_btn_listener.getTouched() || is_level_selected[2]) {
            is_level_selected[2] = true;
            is_level_button_pressed = true;
            if (transition_alpha >= 1f) {
                transition_alpha = 1f;
                dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Level_loader(game, "level1-3"));
            }
        } else if(level_1_4_btn_listener.getTouched() || is_level_selected[3]) {
            is_level_selected[3] = true;
            is_level_button_pressed = true;
            if (transition_alpha >= 1f) {
                transition_alpha = 1f;
                dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Level_loader(game, "level1-4"));
            }
        } else if(level_2_1_btn_listener.getTouched() || is_level_selected[4]) {
            is_level_selected[4] = true;
            is_level_button_pressed = true;
            if (transition_alpha >= 1f) {
                transition_alpha = 1f;
                dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Level_loader(game, "level2-1"));
            }
        } else if(level_2_2_btn_listener.getTouched() || is_level_selected[5]) {
            is_level_selected[5] = true;
            is_level_button_pressed = true;
            if (transition_alpha >= 1f) {
                transition_alpha = 1f;
                dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Level_loader(game, "level2-2"));
            }
        } else if(level_2_3_btn_listener.getTouched() || is_level_selected[6]) {
            is_level_selected[6] = true;
            is_level_button_pressed = true;
            if (transition_alpha >= 1f) {
                transition_alpha = 1f;
                dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Level_loader(game, "level2-3"));
            }
        } else if(level_2_4_btn_listener.getTouched() || is_level_selected[7]) {
            is_level_selected[7] = true;
            is_level_button_pressed = true;
            if (transition_alpha >= 1f) {
                transition_alpha = 1f;
                dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Level_loader(game, "level2-4"));
            }
        } else if(level_3_1_btn_listener.getTouched() || is_level_selected[8]) {
            is_level_selected[8] = true;
            is_level_button_pressed = true;
            if (transition_alpha >= 1f) {
                transition_alpha = 1f;
                dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Level_loader(game, "level3-1"));
            }
        } else if(level_3_2_btn_listener.getTouched() || is_level_selected[9]) {
            is_level_selected[9] = true;
            is_level_button_pressed = true;
            if (transition_alpha >= 1f) {
                transition_alpha = 1f;
                dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Level_loader(game, "level3-2"));
            }
        } else if(level_3_3_btn_listener.getTouched() || is_level_selected[10]) {
            is_level_selected[10] = true;
            is_level_button_pressed = true;
            if (transition_alpha >= 1f) {
                transition_alpha = 1f;
                dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Level_loader(game, "level3-3"));
            }
        } else if(level_3_4_btn_listener.getTouched() || is_level_selected[11]) {
            is_level_selected[11] = true;
            is_level_button_pressed = true;
            if (transition_alpha >= 1f) {
                transition_alpha = 1f;
                dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Level_loader(game, "level3-4"));
            }
        } else if(level_4_1_btn_listener.getTouched() || is_level_selected[12]) {
            is_level_selected[12] = true;
            is_level_button_pressed = true;
            if (transition_alpha >= 1f) {
                transition_alpha = 1f;
                dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Level_loader(game, "level4-1"));
            }
        } else if(level_4_2_btn_listener.getTouched() || is_level_selected[13]) {
            is_level_selected[13] = true;
            is_level_button_pressed = true;
            if (transition_alpha >= 1f) {
                transition_alpha = 1f;
                dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Level_loader(game, "level4-2"));
            }
        } else if(level_4_3_btn_listener.getTouched() || is_level_selected[14]) {
            is_level_selected[14] = true;
            is_level_button_pressed = true;
            if (transition_alpha >= 1f) {
                transition_alpha = 1f;
                dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Level_loader(game, "level4-3"));
            }
        } else if(level_4_4_btn_listener.getTouched() || is_level_selected[15]) {
            is_level_selected[15] = true;
            is_level_button_pressed = true;
            if (transition_alpha >= 1f) {
                transition_alpha = 1f;
                dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Level_loader(game, "level4-4"));
            }
        }
    }

    private void transition() {        
        if (transition_alpha < 1f && is_level_button_pressed) {
            transition_alpha += .09f;
            if (transition_alpha > 1f) {
                transition_alpha = 1f;
            }
        }
        else if(!is_level_button_pressed) {
            if (transition_alpha > 0) { transition_alpha -= .03f; }
            else if (transition_alpha <= 0) { transition_alpha = 0; }
        }
    }

    private void init_btns() {
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(Assets.instance.menuAssets.button_1_1);
        level_1_1_btn = new Button(buttonStyle);
        level_1_1_btn_listener = new ButtonListener();
        level_1_1_btn.addListener(level_1_1_btn_listener);
        
        buttonStyle = new ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(Assets.instance.menuAssets.button_1_2);
        level_1_2_btn = new Button(buttonStyle);
        level_1_2_btn_listener = new ButtonListener();
        level_1_2_btn.addListener(level_1_2_btn_listener);

        buttonStyle = new ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(Assets.instance.menuAssets.button_1_3);
        level_1_3_btn = new Button(buttonStyle);
        level_1_3_btn_listener = new ButtonListener();
        level_1_3_btn.addListener(level_1_3_btn_listener);

        buttonStyle = new ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(Assets.instance.menuAssets.button_1_4);
        level_1_4_btn = new Button(buttonStyle);
        level_1_4_btn_listener = new ButtonListener();
        level_1_4_btn.addListener(level_1_4_btn_listener);

        buttonStyle = new ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(Assets.instance.menuAssets.button_2_1);
        level_2_1_btn = new Button(buttonStyle);
        level_2_1_btn_listener = new ButtonListener();
        level_2_1_btn.addListener(level_2_1_btn_listener);

        buttonStyle = new ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(Assets.instance.menuAssets.button_2_2);
        level_2_2_btn = new Button(buttonStyle);
        level_2_2_btn_listener = new ButtonListener();
        level_2_2_btn.addListener(level_2_2_btn_listener);

        buttonStyle = new ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(Assets.instance.menuAssets.button_2_3);
        level_2_3_btn = new Button(buttonStyle);
        level_2_3_btn_listener = new ButtonListener();
        level_2_3_btn.addListener(level_2_3_btn_listener);

        buttonStyle = new ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(Assets.instance.menuAssets.button_2_4);
        level_2_4_btn = new Button(buttonStyle);
        level_2_4_btn_listener = new ButtonListener();
        level_2_4_btn.addListener(level_2_4_btn_listener);

        buttonStyle = new ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(Assets.instance.menuAssets.button_3_1);
        level_3_1_btn = new Button(buttonStyle);
        level_3_1_btn_listener = new ButtonListener();
        level_3_1_btn.addListener(level_3_1_btn_listener);

        buttonStyle = new ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(Assets.instance.menuAssets.button_3_2);
        level_3_2_btn = new Button(buttonStyle);
        level_3_2_btn_listener = new ButtonListener();
        level_3_2_btn.addListener(level_3_2_btn_listener);

        buttonStyle = new ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(Assets.instance.menuAssets.button_3_3);
        level_3_3_btn = new Button(buttonStyle);
        level_3_3_btn_listener = new ButtonListener();
        level_3_3_btn.addListener(level_3_3_btn_listener);

        buttonStyle = new ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(Assets.instance.menuAssets.button_3_4);
        level_3_4_btn = new Button(buttonStyle);
        level_3_4_btn_listener = new ButtonListener();
        level_3_4_btn.addListener(level_3_4_btn_listener);

        buttonStyle = new ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(Assets.instance.menuAssets.button_4_1);
        level_4_1_btn = new Button(buttonStyle);
        level_4_1_btn_listener = new ButtonListener();
        level_4_1_btn.addListener(level_4_1_btn_listener);

        buttonStyle = new ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(Assets.instance.menuAssets.button_4_2);
        level_4_2_btn = new Button(buttonStyle);
        level_4_2_btn_listener = new ButtonListener();
        level_4_2_btn.addListener(level_4_2_btn_listener);

        buttonStyle = new ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(Assets.instance.menuAssets.button_4_3);
        level_4_3_btn = new Button(buttonStyle);
        level_4_3_btn_listener = new ButtonListener();
        level_4_3_btn.addListener(level_4_3_btn_listener);

        buttonStyle = new ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(Assets.instance.menuAssets.button_4_4);
        level_4_4_btn = new Button(buttonStyle);
        level_4_4_btn_listener = new ButtonListener();
        level_4_4_btn.addListener(level_4_4_btn_listener);
    }
}
