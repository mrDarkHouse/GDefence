package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.darkhouse.gdefence.Model.BackButton;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;

public abstract class AbstractMenuScreen extends AbstractScreen {

    protected SpriteBatch batch;
//    private boolean enableBackButton;

    public AbstractMenuScreen(boolean enableBackButton) {
        super();
//        this.enableBackButton = enableBackButton;
        batch = new SpriteBatch();
        // stage = new Stage();

//        Viewport viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        stage = new Stage(viewport,batch);

        //Gdx.input.setInputProcessor(stage);



        loadButtons(enableBackButton);
    }

    @Override
    public void show() {
//        batch = new SpriteBatch();
//       // stage = new Stage();
//
//        Viewport viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        stage = new Stage(viewport,batch);

//
        Gdx.input.setInputProcessor(stage);
//
//
//
//        loadButtons(enableBackButton);
    }

    private void loadButtons(boolean enableBackButton){
        if(enableBackButton){
            stage.addActor(new BackButton(true));
        }
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        batch.setProjectionMatrix(camera.combined);
//        batch.setTransformMatrix(camera.view);
//        batch.setProjectionMatrix(camera.projection);


        batch.begin();
        batch.draw(GDefence.getInstance().assetLoader.get("MainMenuBg.png", Texture.class), 0, 0);
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        //dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
    }
}
