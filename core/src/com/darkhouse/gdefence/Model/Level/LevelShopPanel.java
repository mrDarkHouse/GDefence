package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.*;
import com.darkhouse.gdefence.Level.LevelShop;
import com.darkhouse.gdefence.Model.Panels.AbstractPanel;

public class LevelShopPanel extends InventoryActor {


    public LevelShopPanel(Inventory inventory) {
        super(new LevelShop(inventory), new DragAndDrop(), AssetLoader.cellSkin);
        getTitleLabel().setText("");
    }



    @Override
    protected void setDefaults() {
        setPosition(90, 10);
        defaults().space(8);
        defaults().size(70, 70);
        row().fill().expandX();
        setRowNumber(8);//infinity
    }





}
