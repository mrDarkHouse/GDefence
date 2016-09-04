package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Model.Level.Map;

public class LevelMap implements Screen {
    private SpriteBatch batch;
    //private Stage stage;
    private ShapeRenderer shape;
    //private Map map;

    private static Level level;

    public static Level getLevel() {
        return level;
    }


    public LevelMap(int number) {
        batch = new SpriteBatch();
        level = new Level(number);

        level.start();

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
        level.render(delta, batch);
        drawTimer(delta, batch);
        batch.end();
    }

    private void drawTimer(float delta, SpriteBatch batch){
        if(!level.isInWave()) {
            float time = level.timeBetweenWaves[level.currentWave];

            Label l = new Label("" + time, AssetLoader.getSkin());
            l.draw(batch, 1);
        }

    }



    @Override
    public void dispose() {

    }
}
