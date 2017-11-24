package com.darkhouse.gdefence.InventorySystem.inventory;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

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
        if(/*button == 0 && */getTapCount() == 2) {
            move();
        }
    }
    protected void move(){
        toInventory.store(owner.getSlot().takeAll());
    }
}
