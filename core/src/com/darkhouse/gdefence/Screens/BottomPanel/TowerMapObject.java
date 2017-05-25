package com.darkhouse.gdefence.Screens.BottomPanel;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public abstract class TowerMapObject extends ImageButton{
    protected TowerMap owner;

    public void setOwner(TowerMap owner) {
        this.owner = owner;
    }

    public TowerMapObject(ImageButtonStyle style) {
        super(style);
    }

    abstract public void updateType();


}
