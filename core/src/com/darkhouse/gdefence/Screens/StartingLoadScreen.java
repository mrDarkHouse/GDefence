package com.darkhouse.gdefence.Screens;


import com.darkhouse.gdefence.GDefence;

public class StartingLoadScreen extends AbstractLoadingScreen{

    public StartingLoadScreen() {
        super();
    }

    @Override
    protected void onLoad() {
        GDefence.getInstance().initScreens();
        GDefence.getInstance().switchScreen(GDefence.getInstance().getMainMenu());
    }
}
