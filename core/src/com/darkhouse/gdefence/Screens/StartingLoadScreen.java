package com.darkhouse.gdefence.Screens;


import com.darkhouse.gdefence.GDefence;

public class StartingLoadScreen extends AbstractLoadingScreen{

    public StartingLoadScreen() {
        super();
    }

    @Override
    public void show() {
        super.show();
//        GDefence.getInstance().assetLoader.loadAll();
        GDefence.getInstance().assetLoader.loadTextures();
        GDefence.getInstance().assetLoader.loadShop();
        GDefence.getInstance().assetLoader.loadSmith();
    }

    @Override
    protected void onLoad() {
//        GDefence.getInstance().initScreens();
        GDefence.getInstance().initAll();
        GDefence.getInstance().switchScreen(GDefence.getInstance().getMainMenu());
    }
}
