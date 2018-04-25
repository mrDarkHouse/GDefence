package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Stage.LoadingStage;

public abstract class AbstractLoadingScreen extends AbstractScreen {

    protected int progress;
    private SpriteBatch batch;
    private Texture background;
    protected LoadingStage stage;

    public AbstractLoadingScreen() {
        super();
    }
    public void init(){
        stage = new LoadingStage();
        batch = new SpriteBatch();
        background = GDefence.getInstance().assetLoader.get("MainMenuBg.png", Texture.class);
    }


    @Override
    public void show() {

//        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        GDefence.getInstance().assetLoader.loadAll();


        //super.show();
        //GDefence.getInstance().assetLoader.finishLoading();



    }



    @Override
    public void render(float delta) {
        super.render(delta);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
//        bg.draw(batch, 255f);
        batch.draw(background, 0, 0, getStage().getWidth(), getStage().getHeight());
        batch.end();
        progress = (int)(GDefence.getInstance().assetLoader.getProgress() * 100);
        stage.update(progress);
        if(GDefence.getInstance().assetLoader.update()){
            //if (Gdx.input.isTouched()) {
                onLoad();
            //}

        }

        stage.act();
        stage.draw();
    }

    protected abstract void onLoad();

//    @Override
//    public void resize(int width, int height) {
//
//    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        progress = 0;
    }

    @Override
    public void dispose() {

    }
}
