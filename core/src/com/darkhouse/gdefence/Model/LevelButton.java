package com.darkhouse.gdefence.Model;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Screens.LevelPreparationScreen;

public class LevelButton extends ImageButton {
    private int number;
    private boolean isLocked = false;

    public LevelButton(int number, GDefence mainClass) {
        super(AssetLoader.getCampainLevelSkin(number));
        this.number = number;
        load(mainClass);
    }

    private void load(final GDefence mainClass){
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!isLocked) {
                    mainClass.setScreen(new LevelPreparationScreen(number, mainClass));
                }
                return true;
            }
        });


    }






    public void lock(){
        isLocked = true;
        getStyle().up = new TextureRegionDrawable(new TextureRegion(AssetLoader.levelLock));
        getStyle().over = new TextureRegionDrawable(new TextureRegion(AssetLoader.levelLock));
        getStyle().down = new TextureRegionDrawable(new TextureRegion(AssetLoader.levelLock));
    }
}
