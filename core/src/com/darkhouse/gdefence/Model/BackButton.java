package com.darkhouse.gdefence.Model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;

public class BackButton extends ImageButton{

    public BackButton(boolean ownPosition) {
        super(GDefence.getInstance().assetLoader.getBackButtonSkin());
        //super(mainClass.getSkin());
        int backButtonsSize[] = {64, 64};

        setSize(backButtonsSize[0], backButtonsSize[1]);
        if(ownPosition) {
            setPosition(0, Gdx.graphics.getHeight() - backButtonsSize[1]);
        }
        addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                //mainClass.setScreen(new MainMenu(mainClass));
                GDefence.getInstance().setPreviousScreen();
                return true;
            }
        });

    }



}
