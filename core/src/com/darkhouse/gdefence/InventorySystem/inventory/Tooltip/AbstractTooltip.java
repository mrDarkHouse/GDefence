package com.darkhouse.gdefence.InventorySystem.inventory.Tooltip;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public abstract class AbstractTooltip extends Window{
    public AbstractTooltip(String title, Skin skin) {
        super(title, skin);
    }

    public abstract void hasChanged();
}
