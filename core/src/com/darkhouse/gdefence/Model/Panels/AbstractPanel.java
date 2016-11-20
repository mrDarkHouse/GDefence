package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;

public abstract class AbstractPanel extends Table{

    public AbstractPanel(int x, int y, int width, int height) {
        setSkin(GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));
        setPosition(x, y);
        setSize(width, height);
    }

    public AbstractPanel() {

    }
}
