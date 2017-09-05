package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;

public class SellButton extends ImageButton{
    public SellButton() {
        super(GDefence.getInstance().assetLoader.getStoreSellButtonStyle());
    }

    @Override
    public float getPrefWidth() {
        return 140;
    }

    @Override
    public float getPrefHeight() {
        return 80;
    }
}
