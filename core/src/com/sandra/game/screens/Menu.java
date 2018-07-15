package com.sandra.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.sandra.game.Cat_game;
import com.sandra.game.handlers.MenuInputListener;
import com.sandra.game.utils.Assets;
import com.sandra.game.utils.Constants;
import com.sandra.game.utils.Utils;

public class Menu implements Screen {

    private Cat_game game;
    private boolean pause = false;
    private OrthographicCamera camera;

    private Stage stage;
    private Table table;

    private Button level_1_1_btn;
    private Sprite level_1_1_sprite;
    private Sprite level_1_1_sprite_selected;
    private MenuInputListener level_1_1_listener;

    public Menu(Cat_game game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        AssetManager assetManager = new AssetManager();
        Assets.instance.init(assetManager);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.GAME_WIDTH / Constants.PPM, Constants.GAME_HEIGHT / Constants.PPM);

        // buttons
        level_1_1_btn = new Button();
        level_1_1_btn.setSize(400, 400);
        // level_1_1_btn.setPosition(50, 50);

        level_1_1_listener = new MenuInputListener();
        level_1_1_btn.addListener(level_1_1_listener);

        // stage
        table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        table.add(level_1_1_btn);
        stage.addActor(table);    }

    @Override
    public void render(float delta) {
        if (!pause) {
            update(delta);
        }
        camera.update();

        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 0, 1, 1); // magenta

        
        game.batch.begin();
        stage.draw();
        TextureRegion region = Assets.instance.buttonsAssets.button;
        Utils.drawTextureRegion(game.batch, region,
                new Vector2(Constants.GAME_WIDTH / 2 / Constants.PPM, Constants.GAME_HEIGHT / 2 / Constants.PPM),
                new Vector2(0, 0));
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    private void update(float delta) {
        game.batch.setProjectionMatrix(camera.combined);
    }

}