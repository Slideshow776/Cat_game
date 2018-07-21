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
	private FitViewport viewport;

    public Menu(Cat_game game) {
        this.game = game;
    }

    @Override
    public void show() {
        AssetManager assetManager = new AssetManager();
        Assets.instance.init(assetManager);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.GAME_WIDTH / Constants.PPM, Constants.GAME_HEIGHT / Constants.PPM);
        viewport = new FitViewport(Constants.GAME_WIDTH / Constants.PPM, Constants.GAME_HEIGHT / Constants.PPM, camera);

        // Stage        
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        level_1_1_btn = new Button();
        level_1_1_btn_sprite = new Sprite(Assets.instance.buttonsAssets.button);
        level_1_1_btn.setSize(100 / Constants.PPM, 100 / Constants.PPM);
        level_1_1_btn.setPosition(750 / Constants.PPM, 550 / Constants.PPM);
        level_1_1_btn_listener = new MenuInputListener();
        level_1_1_btn.addListener(level_1_1_btn_listener);

        table = new Table();
        table.setFillParent(true);
        table.debug();
        table.add(level_1_1_btn);

        stage.addActor(table);
        stage.setViewport(viewport);

        System.out.println("button: " + level_1_1_btn.getX() + ", " + level_1_1_btn.getY());
        System.out.println("game: " + Gdx.graphics.getWidth() + ", " + Gdx.graphics.getHeight());
        System.out.println("viewport: " + viewport.getWorldWidth() + ", " + viewport.getWorldHeight());
    }

    @Override
    public void render(float delta) {
        if (!pause) {update(delta);}
        camera.update();

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
    public void dispose() {}

    private void update(float delta) {
        game.batch.setProjectionMatrix(camera.combined);

        stage.act(delta);
        stage.getViewport().update(Gdx.graphics.getWidth()+310, Gdx.graphics.getHeight()); // 310 offset, for some reason

		if(level_1_1_btn_listener.getTouched()) {
            ((Game) Gdx.app.getApplicationListener()).setScreen(new Level_1_1(game));
            dispose();
		}    
    }
}
