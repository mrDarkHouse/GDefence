package com.darkhouse.gdefence.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;


public abstract class AbstractScreen implements Screen {
    protected Stage stage;

    public Stage getStage() {
        return stage;
    }

    @Override
    public void show() {
        stage = new Stage();
    }
}
