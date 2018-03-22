package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.Inventory;
import com.darkhouse.gdefence.InventorySystem.inventory.InventoryActor;
import com.darkhouse.gdefence.InventorySystem.inventory.OverallInventory;
import com.darkhouse.gdefence.InventorySystem.inventory.SlotActor;
import com.darkhouse.gdefence.InventorySystem.inventory.Source.SellSlotSource;
import com.darkhouse.gdefence.InventorySystem.inventory.Target.SlotTarget;
import com.darkhouse.gdefence.Model.Panels.GoldPanel;
import com.darkhouse.gdefence.Model.Panels.StoreBuyPanel;
import com.darkhouse.gdefence.Screens.AbstractCampainScreen;
import com.darkhouse.gdefence.User;

public class Store extends AbstractCampainScreen{
//    private InventoryActor inventoryActor;
    private OverallInventory inventory;
    private StoreBuyPanel storeBuyPanel;

    public class SellInventoryActor extends InventoryActor{
        public SellInventoryActor(Inventory inventory, DragAndDrop dragAndDrop, Skin skin) {
            super(inventory, dragAndDrop, skin);
        }

        @Override
        protected void addSourceTarget(SlotActor slotActor) {
            dragAndDrop.addSource(new SellSlotSource(slotActor));
            dragAndDrop.addTarget(new SlotTarget(slotActor));
        }
    }

    public StoreBuyPanel getStoreBuyPanel() {
        return storeBuyPanel;
    }

    public Store() {
        super("store");
        //init();
    }

    @Override
    public void show() {
        super.show();
        inventory.notifyListeners();
//        inventoryActor.notifyListeners();
////        init();
//
    }
    public void init(){
//        inventoryActor = new InventoryActor(User.getTowerInventory(), new DragAndDrop(),
//                GDefence.getInstance().assetLoader.getSkin()){
//            @Override
//            protected void addSourceTarget(SlotActor slotActor) {
//                dragAndDrop.addSource(new SellSlotSource(slotActor));
//                dragAndDrop.addTarget(new SlotTarget(slotActor));
//            }
//
//            @Override
//            protected void setDefaults() {
//                setPosition(100, 250);
//                defaults().space(8);
//                defaults().size(60, 60);
//                row().fill().expandX();
//                setRowNumber(7);
//                setRows(4);
//            }
//        };
        inventory = new OverallInventory(){//TODO
            @Override
            protected void initActors(AssetLoader l, Inventory[] customInventories) {
                actors = new InventoryActor[3];
                actors[0] = new SellInventoryActor(customInventories[0], dragAndDrop,
                        l.getSkin());
                actors[0].getTitleLabel().setText(l.getWord("towers"));
                actors[1] = new SellInventoryActor(customInventories[1], dragAndDrop,
                        l.getSkin());
                actors[1].getTitleLabel().setText(l.getWord("spells"));
                actors[2] = new SellInventoryActor(customInventories[2], dragAndDrop,
                        l.getSkin());
                actors[2].getTitleLabel().setText(l.getWord("details"));
            }
        };
        inventory.setPosition(100, 250);




//        storeBuyPanel = new StoreBuyPanel(new StoreBuyInventory());
//        storeBuyPanel.store(ItemEnum.Tower.Basic, 1);
//        storeBuyPanel.store(ItemEnum.Tower.Rock, 1);
//        storeBuyPanel.store(ItemEnum.Tower.Arrow, 1);
//        storeBuyPanel.store(ItemEnum.Tower.Range, 1);

        //

        storeBuyPanel = new StoreBuyPanel(inventory.getDragAndDrop());
//        storeBuyPanel = new StoreBuyPanel(inventoryActor.getDragAndDrop());
//        User.setStorePanel(storeBuyPanel);
//        storeBuyPanel.addSlots(inventoryActor);

        storeBuyPanel.setPosition(740, 200);

//        stage.addActor(inventoryActor);
//        inventoryActor.init();
        stage.addActor(inventory);
        inventory.init();
        stage.addActor(storeBuyPanel);
        storeBuyPanel.init();
        storeBuyPanel.addFastMove(inventory.getInventory(0));
//        storeBuyPanel.addFastMove(inventory.getInventory(1));
//        storeBuyPanel.addFastMove(inventory.getInventory(2));
        stage.addActor(new GoldPanel(1100, 500, 100, 90));
    }

//    @Override
//    public void resize(int width, int height) {
//
//    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
