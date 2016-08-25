package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Model.Level.Map;

public class LevelMap implements Screen {
    private SpriteBatch batch;
    //private Stage stage;
    private ShapeRenderer shape;
    private Map map;



    public LevelMap(int number) {
        batch = new SpriteBatch();
        map = new Map(number, 30, Gdx.graphics.getHeight() - 30, 50);

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
        map.draw(delta, batch);
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
