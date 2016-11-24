package com.darkhouse.gdefence.InventorySystem.inventory;


import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Level.MapTile;
import com.darkhouse.gdefence.Screens.LevelMap;

public class LevelShopPanel extends InventoryActor {


    public LevelShopPanel(Inventory inventory) {
        super(new LevelShop(inventory), new DragAndDrop(), GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class));
        getTitleLabel().setText("");
    }



    @Override
    protected void setDefaults() {
        setPosition(90, 10);
        defaults().space(8);
        defaults().size(60, 60);
        row().fill().expandX();
        setRowNumber(8);//infinity
        setRows(1);
    }

    @Override
    protected void addSourceTarget(SlotActor slotActor) {
        dragAndDrop.addSource(new LevelShopSource(slotActor, dragAndDrop));
        dragAndDrop.addTarget(new SlotTarget(slotActor));
    }

    @Override
    public void addSlots(InventoryActor inventory) {
        //
    }
    public void addTarget(MapTile[][] tiles){
        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles[0].length; j++){
                dragAndDrop.addTarget(new TileTarget(LevelMap.levelMap.getTiles()[i][j], dragAndDrop));
            }
        }

    }

}
