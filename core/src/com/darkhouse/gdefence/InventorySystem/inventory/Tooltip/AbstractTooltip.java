package com.darkhouse.gdefence.InventorySystem.inventory.Tooltip;


import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;

public abstract class AbstractTooltip extends Window{
    public AbstractTooltip(String title, Skin skin) {
        super(title, skin, "description");
        getTitleLabel().setAlignment(Align.center);
    }

    public AbstractTooltip(String title, Skin skin, String styleName) {
        super(title, skin, styleName);
        getTitleLabel().setAlignment(Align.center);
    }

    public abstract void hasChanged();
    public void init(Stage stage){}
//    public abstract void init();//adding to stage
}
