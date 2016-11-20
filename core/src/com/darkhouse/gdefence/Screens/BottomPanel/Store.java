package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.InventoryActor;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Model.Panels.GoldPanel;
import com.darkhouse.gdefence.Screens.AbstractCampainScreen;
import com.darkhouse.gdefence.User;

public class Store extends AbstractCampainScreen{
    private InventoryActor inventoryActor;
    private StoreBuyPanel storeBuyPanel;


    public Store() {
        super("Store");
    }

    @Override
    public void show() {
        super.show();

        inventoryActor = new InventoryActor(User.getInventory(), new DragAndDrop(), GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class)){
            @Override
            protected void setDefaults() {
                setPosition(100, 250);
                defaults().space(8);
                defaults().size(60, 60);
                row().fill().expandX();
                setRowNumber(7);
            }
        };
        storeBuyPanel = new StoreBuyPanel(new StoreBuyInventory());
        storeBuyPanel.store(ItemEnum.Tower.Basic, 1);
        storeBuyPanel.store(ItemEnum.Tower.Rock, 1);
        storeBuyPanel.store(ItemEnum.Tower.Arrow, 1);
        storeBuyPanel.store(ItemEnum.Tower.Range, 1);

        storeBuyPanel.addSlots(inventoryActor);





        stage.addActor(inventoryActor);
        stage.addActor(storeBuyPanel);
        stage.addActor(new GoldPanel(1100, 500, 100, 90));


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
