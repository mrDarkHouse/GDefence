package com.darkhouse.gdefence.Screens;


import com.darkhouse.gdefence.GDefence;

public class StartingLoadScreen extends AbstractLoadingScreen{

    public StartingLoadScreen() {
        super();
    }

    @Override
    protected void onLoad() {
        GDefence.getInstance().setScreen(new MainMenu());
    }
}
