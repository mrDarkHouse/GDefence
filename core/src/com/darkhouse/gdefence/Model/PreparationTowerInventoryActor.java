package com.darkhouse.gdefence.Model;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.InventorySystem.inventory.*;
import com.darkhouse.gdefence.InventorySystem.inventory.Source.SlotSource;
import com.darkhouse.gdefence.InventorySystem.inventory.Target.SlotTarget;
import com.darkhouse.gdefence.Objects.TowerObject;

public class PreparationTowerInventoryActor extends InventoryActor{

    public static class PreparationTowerInventory extends Inventory{

        public PreparationTowerInventory() {
            super(TowerObject.class, 4);
        }

    }

    public PreparationTowerInventoryActor(DragAndDrop dragAndDrop, Skin skin) {
        super(new PreparationTowerInventory(), dragAndDrop, skin);
        getTitleLabel().setText("Level towers");
    }


//    @Override
//    protected void initCells(DragAndDrop dragAndDrop, Skin skin, Inventory inventory) {
//        actorArray = new Array<SlotActor>();
//        int i = 0;
//        for (Slot slot : inventory.getSlots()) {
//            SlotActor slotActor = new SlotActor(skin, slot);
//            dragAndDrop.addSource(new SlotSource(slotActor));
//            dragAndDrop.addTarget(new SlotTarget(slotActor));
//            actorArray.add(slotActor);
//            add(slotActor);
//
//            i++;
//            if (i % 4 == 0) {
//                row();
//            }
//        }
//    }

    @Override
    protected void setDefaults() {
//        setPosition(700, 250);
        defaults().space(8);
        defaults().size(60, 60);
        row().fill().expandX();
        setRowNumber(4);
        setRows(4);
    }

    @Override
    protected void addSourceTarget(SlotActor slotActor) {
        dragAndDrop.addSource(new SlotSource(slotActor));
        dragAndDrop.addTarget(new SlotTarget(slotActor));
    }


}
