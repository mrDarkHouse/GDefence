package com.darkhouse.gdefence.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.darkhouse.gdefence.GDefence;

public class LevelPreparationScreen extends AbstractCampainScreen{
    public LevelPreparationScreen(int level, GDefence mainClass) {
        super("" + level, mainClass);
        load(level);
    }

    private void load(final int level){
        TextButton startButton = new TextButton("Start", mainClass.getSkin());
        startButton.setSize(150, 70);
        startButton.setPosition(Gdx.graphics.getWidth() - 200, 30);
        startButton.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                mainClass.setScreen(new LevelMap(level));
                return true;
            }
        });
        stage.addActor(startButton);
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
}
