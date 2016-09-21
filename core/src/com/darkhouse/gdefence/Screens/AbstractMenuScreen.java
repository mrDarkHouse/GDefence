package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.darkhouse.gdefence.Model.BackButton;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;

public abstract class AbstractMenuScreen implements Screen {

    protected SpriteBatch batch;
    protected Stage stage;
    private boolean enableBackButton;



    public AbstractMenuScreen(boolean enableBackButton) {
        this.enableBackButton = enableBackButton;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
       // stage = new Stage();

        Viewport viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(viewport,batch);

        Gdx.input.setInputProcessor(stage);



        loadButtons(enableBackButton);
    }

    private void loadButtons(boolean enableBackButton){
        if(enableBackButton){
            stage.addActor(new BackButton());
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
