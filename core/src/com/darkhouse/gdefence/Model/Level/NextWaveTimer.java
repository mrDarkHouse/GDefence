package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.darkhouse.gdefence.Helpers.AssetLoader;

public class NextWaveTimer extends Label{
    public NextWaveTimer(CharSequence text) {
        super(text, AssetLoader.getTimerSkin());
    }
}
