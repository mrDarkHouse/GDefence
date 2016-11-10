package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Stage.LoadingStage;

public class AbstractLoadingScreen extends AbstractScreen {


    protected int progress;

    public AbstractLoadingScreen() {
        super();
    }

    @Override
    public void show() {
        stage = new LoadingStage();
        GDefence.getInstance().assetLoader.loadAll();


        //super.show();
        //GDefence.getInstance().assetLoader.finishLoading();



    }

    @Override
    public void render(float delta) {
        progress = (int)GDefence.getInstance().assetLoader.getProgress() * 100;
        System.out.println(progress);
        if(GDefence.getInstance().assetLoader.update()){
            //if (Gdx.input.isTouched()) {
                onLoad();
            //}

        }

        stage.act();
        stage.draw();
    }

    protected void onLoad(){
        //
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
        progress = 0;
    }

    @Override
    public void dispose() {

    }
}
