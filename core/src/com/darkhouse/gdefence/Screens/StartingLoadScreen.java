package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.GDefence;

public class StartingLoadScreen extends AbstractLoadingScreen{

    public StartingLoadScreen() {
        super();
        GDefence.getInstance().assetLoader.load("MainMenuBg.png", Texture.class);//TODO
        GDefence.getInstance().assetLoader.load("mobHpBarBg.png", Texture.class);
        GDefence.getInstance().assetLoader.load("mobHpBarKnob.png", Texture.class);
        Preferences pref = Gdx.app.getPreferences("config");
        String locale = pref.getString("locale", "en");
        GDefence.getInstance().assetLoader.initLang(/*"en"*/locale);
        init();
    }

    @Override
    public void show() {
//        super.show();
//        GDefence.getInstance().assetLoader.loadAll();
        GDefence.getInstance().assetLoader.loadTextures();
        GDefence.getInstance().assetLoader.loadShop();
        GDefence.getInstance().assetLoader.loadSmith();
    }

    @Override
    protected void onLoad() {
//        GDefence.getInstance().initScreens();
        GDefence.getInstance().assetLoader.setFilters();
        GDefence.getInstance().initAll();
        GDefence.getInstance().switchScreen(GDefence.getInstance().getMainMenu());
    }
}
