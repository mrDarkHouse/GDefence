package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.InventoryActor;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.InventorySystem.inventory.SlotActor;
import com.darkhouse.gdefence.InventorySystem.inventory.Source.SellSlotSource;
import com.darkhouse.gdefence.InventorySystem.inventory.Source.SlotSource;
import com.darkhouse.gdefence.InventorySystem.inventory.Target.SlotTarget;
import com.darkhouse.gdefence.Model.Panels.GoldPanel;
import com.darkhouse.gdefence.Model.Panels.StoreBuyPanel;
import com.darkhouse.gdefence.Screens.AbstractCampainScreen;
import com.darkhouse.gdefence.User;

public class Store extends AbstractCampainScreen{
    private InventoryActor inventoryActor;
    private StoreBuyPanel storeBuyPanel;


    public Store() {
        super("Store");
        //init();
    }

    @Override
    public void show() {
        super.show();
//        init();

    }
    public void init(){
        inventoryActor = new InventoryActor(User.getTowerInventory(), new DragAndDrop(), GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class)){
            @Override
            protected void addSourceTarget(SlotActor slotActor) {
                dragAndDrop.addSource(new SellSlotSource(slotActor));
                dragAndDrop.addTarget(new SlotTarget(slotActor));
            }

            @Override
            protected void setDefaults() {
                setPosition(100, 250);
                defaults().space(8);
                defaults().size(60, 60);
                row().fill().expandX();
                setRowNumber(7);
                setRows(4);
            }
        };
        storeBuyPanel = new StoreBuyPanel(new StoreBuyInventory());
        storeBuyPanel.store(ItemEnum.Tower.Basic, 1);
        storeBuyPanel.store(ItemEnum.Tower.Rock, 1);
        storeBuyPanel.store(ItemEnum.Tower.Arrow, 1);
        storeBuyPanel.store(ItemEnum.Tower.Range, 1);

        storeBuyPanel.addSlots(inventoryActor);

        stage.addActor(inventoryActor);
        inventoryActor.init();
        stage.addActor(storeBuyPanel);
        storeBuyPanel.init();
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
