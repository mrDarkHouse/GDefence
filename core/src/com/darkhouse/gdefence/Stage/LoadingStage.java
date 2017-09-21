package com.darkhouse.gdefence.Stage;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.utils.I18NBundle;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;

import java.util.Locale;

public class LoadingStage extends Stage{
    protected ProgressBar loadBar;

    public LoadingStage() {
        GDefence.getInstance().assetLoader.load("MainMenuBg.png", Texture.class);//TODO
        GDefence.getInstance().assetLoader.load("mobHpBarBg.png", Texture.class);
        GDefence.getInstance().assetLoader.load("mobHpBarKnob.png", Texture.class);
        I18NBundleLoader.I18NBundleParameter param = new I18NBundleLoader.I18NBundleParameter(new Locale("ru")/*Locale.US*/, "UTF-8");
        GDefence.getInstance().assetLoader.load("Language/text", I18NBundle.class, param);
        GDefence.getInstance().assetLoader.finishLoading();


        Image bg = new Image(GDefence.getInstance().assetLoader.get("MainMenuBg.png", Texture.class));
        addActor(bg);
        loadBar = new ProgressBar(0, 100, 1, false, GDefence.getInstance().assetLoader.getMobHpBarStyle());
        loadBar.setSize(300, 50);
        loadBar.getStyle().background.setMinHeight(loadBar.getHeight());
        loadBar.getStyle().knob.setMinHeight(loadBar.getHeight());
        loadBar.setPosition(Gdx.graphics.getWidth()/2 - loadBar.getWidth()/2, Gdx.graphics.getHeight()/2 - loadBar.getHeight()/2);
        addActor(loadBar);
        I18NBundle b = GDefence.getInstance().assetLoader.get("Language/text", I18NBundle.class);
        Label loading = new Label(b.get("loading"), FontLoader.generateStyle(34, Color.BLACK));//
        loading.setPosition(loadBar.getX() + loadBar.getWidth()/2 - loading.getWidth()/2,
                            loadBar.getY() - loadBar.getHeight());
        addActor(loading);
    }



    public void update(int progress){
        loadBar.setValue(progress);


    }
}
