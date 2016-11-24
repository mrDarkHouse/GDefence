package com.darkhouse.gdefence.InventorySystem.inventory.Target;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.InventorySystem.inventory.Slot;

public class SellTarget extends DragAndDrop.Target{
    private ImageButton targetButton;

    public SellTarget(ImageButton actor) {
        super(actor);
        this.targetButton = actor;
        getActor().setColor(Color.LIGHT_GRAY);
    }

    @Override
    public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        Slot payloadSlot = (Slot) payload.getObject();
        // if (targetSlot.getPrototype() == payloadSlot.getPrototype() ||
        // targetSlot.getPrototype() == null) {
        getActor().setColor(Color.WHITE);
        return true;
        // } else {
        // getActor().setColor(Color.DARK_GRAY);
        // return false;
        // }
    }

    @Override
    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {



    }

    @Override
    public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload) {
        getActor().setColor(Color.GREEN);
    }
}
