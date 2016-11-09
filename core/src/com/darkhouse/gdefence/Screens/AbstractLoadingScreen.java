package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.darkhouse.gdefence.GDefence;

public class AbstractLoadingScreen extends AbstractScreen {


    protected int progress;
    protected ProgressBar loadBar;






    @Override
    public void show() {
        super.show();
        GDefence.getInstance().assetLoader.finishLoading();

        Image bg = new Image(GDefence.getInstance().assetLoader.get("MainMenuBg.png", Texture.class));
        stage.addActor(bg);
        loadBar = new ProgressBar(0, 100, 1, false, GDefence.getInstance().assetLoader.getExpBarSkin());
        stage.addActor(loadBar);




    }

    @Override
    public void render(float delta) {

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
