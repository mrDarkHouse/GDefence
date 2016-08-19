package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.darkhouse.gdefence.Model.BackButton;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;

public abstract class AbstractMenuScreen implements Screen {

    protected SpriteBatch batch;
    protected Stage stage;
    protected GDefence mainClass;



    public AbstractMenuScreen(GDefence mainClass, boolean enableBackButton) {
        this.mainClass = mainClass;
        batch = new SpriteBatch();
        stage = new Stage();

        loadButtons(enableBackButton);
    }

    private void loadButtons(boolean enableBackButton){
        if(enableBackButton){
            stage.addActor(new BackButton(mainClass));
        }
    }


    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(AssetLoader.mainMenuBg, 0, 0);
        batch.end();

        stage.act(delta);
        stage.draw();
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
    }
}
