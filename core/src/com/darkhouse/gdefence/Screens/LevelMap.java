package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.darkhouse.gdefence.Helpers.AssetLoader;

public class LevelMap implements Screen {
    private SpriteBatch batch;
    //private Stage stage;
    private ShapeRenderer shape;
    public LevelMap() {
        batch = new SpriteBatch();
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
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(AssetLoader.levelMapBg, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
