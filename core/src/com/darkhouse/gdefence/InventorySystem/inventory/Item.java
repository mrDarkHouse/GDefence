package com.darkhouse.gdefence.InventorySystem.inventory;


import com.badlogic.gdx.scenes.scene2d.ui.Label;

public interface Item {

    String getTextureRegion();
    //String getTooltip();
    int getGlobalCost();
    int getID();
    String getName();
    String getTooltip();

}
