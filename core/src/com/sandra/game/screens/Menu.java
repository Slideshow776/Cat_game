package com.sandra.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sandra.game.Cat_game;
import com.sandra.game.handlers.MenuInputListener;
import com.sandra.game.utils.Assets;
import com.sandra.game.utils.Constants;

public class Menu implements Screen {

    private Cat_game game;
    private boolean pause = false;
    private OrthographicCamera camera;

    private Stage stage;
    private Table table;

    private Button level_1_1_btn;
	private Sprite level_1_1_btn_sprite;
    private MenuInputListener level_1_1_btn_listener;

    public Menu(Cat_game game) {
        this.game = game;
    }

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

        level_1_1_btn = new Button();
        level_1_1_btn_sprite = new Sprite(Assets.instance.buttonsAssets.button);
        level_1_1_btn.setSize(100, 100);
        level_1_1_btn.setPosition(550, 450);
        level_1_1_btn_listener = new MenuInputListener();
        level_1_1_btn.addListener(level_1_1_btn_listener);

        table = new Table();
        table.setFillParent(true);
        table.debug();
        table.add(level_1_1_btn);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        if (!pause) {update(delta);}

        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 0, 1, 1); // magenta
        
        game.batch.begin();
        game.batch.draw(
            level_1_1_btn_sprite,
            level_1_1_btn.getX(),
            level_1_1_btn.getY(),
            level_1_1_btn.getWidth(),
            level_1_1_btn.getHeight()
        );
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
    public void dispose() {
        stage.dispose();
    }

    private void update(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        
        stage.act(delta);
        //stage.getViewport().update(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);

		if(level_1_1_btn_listener.getTouched()) {
            dispose();
            ((Game) Gdx.app.getApplicationListener()).setScreen(new Level_1_1(game));
		}    
    }
}
