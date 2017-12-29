package com.darkhouse.gdefence.InventorySystem.inventory;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.Objects.GameObject;
import com.darkhouse.gdefence.Objects.TowerObject;

public class DoubleClickListener extends ClickListener{

    protected Inventory toInventory;
    protected SlotActor owner;

    public DoubleClickListener(Inventory toInventory, SlotActor owner) {
        this.toInventory = toInventory;
        this.owner = owner;
        setTapCountInterval(0.3f);
//        setButton(0);
    }

//    public DoubleClickListener setOwner(SlotActor owner) {
//        this.owner = owner;
//        return this;
//    }

//    @Override
//    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//        super.touchDown(event, x, y, pointer, button);
//
//    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        if(/*button == 0 && */getTapCount() % 2 == 0) {//2, 4, 6, 8... etc
            move();
        }
    }
    protected void move(){
        Slot s;// = toInventory.firstSlotWithItem(owner.getSlot().getPrototype());
//        if(toInventory.firstSlotWithItem(null) == null) {
////            s = toInventory.firstSlotWithItem(owner.getSlot().getPrototype());
//            if(s == null) return;
//        }
//
        while (!owner.getSlot().isEmpty()) {
            s = toInventory.firstSlotWithItem(owner.getSlot().getPrototype());
            if (s == null) s = toInventory.firstSlotWithItem(null);
            if (s == null) return;//toInventory full (no free slots and all slots with this prototype are full)

            int toMove = s.getMaxItems() - s.getAmount();
            if(owner.getSlot().getAmount() > toMove) {
                toInventory.store(owner.getSlot().take(toMove));
            }else {
                toInventory.store(owner.getSlot().takeAll());
            }
        }

    }
}
