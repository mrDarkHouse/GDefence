package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;

public abstract class AbstractPanel extends Table{

    public AbstractPanel(int x, int y, int width, int height) {
        setSkin(AssetLoader.cellSkin);
        setPosition(x, y);
        setSize(width, height);
    }

    public AbstractPanel() {

    }
}
