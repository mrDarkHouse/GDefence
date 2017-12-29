package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class LevelInput implements InputProcessor {

    private LevelMap screen;

    public LevelInput(LevelMap screen) {
        this.screen = screen;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.ALT_LEFT){
            screen.showNotificationTooltips();
        }
        if(keycode == Input.Keys.ESCAPE){
            if(!screen.isPaused()) screen.setPause();
            else screen.offPause();
        }
        return true;//false if need other listeners
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.ALT_LEFT){
            screen.offNotificationTooltips();
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
