package com.sandra.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();

    public Cat1Assets cat1Assets;

    private AssetManager assetManager;

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);
        cat1Assets = new Cat1Assets(atlas);
    }

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error(TAG, "Could not load asset: " + asset.fileName, throwable);
	}

	@Override
	public void dispose() {
		assetManager.dispose();
	}

    public class Cat1Assets {
        public final AtlasRegion cat1;

        public Cat1Assets(TextureAtlas atlas) {
            cat1 = atlas.findRegion(Constants.CAT1_SPRITE);
        }
    }

}