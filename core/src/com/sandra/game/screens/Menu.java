package com.sandra.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
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
import com.sandra.game.screens.Level_1_2;

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

    private Button level_1_1_btn;
    private ButtonListener level_1_1_btn_listener;

    private Button level_1_2_btn;
    private ButtonListener level_1_2_btn_listener;

    private Sprite background1;
    private float background1ScrollTimer;
	private float background1ScrollSpeed;
    private Sprite background2;
    private float background2ScrollTimer;
	private float background2ScrollSpeed;

    public Menu(Cat_game game) { this.game = game; }

    @Override
    public void show() {
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
        animation_pos = new Vector2(550, 395);
        animation_scale = .25f;
        
        // Background
        background1 = new Sprite(new Texture("images/star_background1.png"));
        background1.setSize(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        background1.getTexture().setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
        background1ScrollTimer = 0;
        background1ScrollSpeed = .04f;
        background2 = new Sprite(new Texture("images/star_background2.png"));
        background2.setSize(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
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
        table.add(menu_title_image).colspan(2).width(425*1.5f).height(107*1.5f);
        table.row();
        table.add(level_1_1_btn).width(75).height(50);
        table.add(level_1_2_btn).width(75).height(50);
    }

    @Override
    public void render(float delta) {
        if (!pause) {update(delta);}
        
        float animation_time_seconds = Utils.secondsSince(animation_start_time);
        animation_region = Assets.instance.cat1Assets.cat1_sleeping_animation.getKeyFrame(animation_time_seconds);

        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1); // black
        
        game.batch.begin();
        game.batch.draw(background1, 0, 0, 800, 600);
        game.batch.draw(background2, 0, 0, 800, 600);
        game.batch.end();

        stage.draw();

        game.batch.begin();        
        Utils.drawTextureRegion(game.batch, animation_region, animation_pos.x, animation_pos.y, animation_scale, true);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() { stage.dispose(); }

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

		if(level_1_1_btn_listener.getTouched()) {
            dispose();
            ((Game) Gdx.app.getApplicationListener()).setScreen(new Splash_how_to_play(game));
        }
        
		if(level_1_2_btn_listener.getTouched()) {
            dispose();
            ((Game) Gdx.app.getApplicationListener()).setScreen(new Level_1_2(game));
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
    }
}
