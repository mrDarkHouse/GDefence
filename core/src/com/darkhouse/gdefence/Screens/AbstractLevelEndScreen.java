package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.darkhouse.gdefence.Helpers.AssetLoader;

public abstract class AbstractLevelEndScreen implements Screen{
    protected SpriteBatch batch;
    protected Stage stage;

//    public AbstractLevelEndScreen() {
//
//    }

    @Override
    public void show() {
        init();
    }

    private void init(){
        batch = new SpriteBatch();
        stage = new Stage();
    }


    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(AssetLoader.campainBg, 0, 0);
        batch.end();

        stage.act(delta);
        stage.draw();
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
}
