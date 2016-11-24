package com.darkhouse.gdefence.InventorySystem.inventory.Source;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.InventorySystem.inventory.Slot;
import com.darkhouse.gdefence.InventorySystem.inventory.SlotActor;
import com.darkhouse.gdefence.InventorySystem.inventory.Source.SlotSource;

public class GemGradeSource extends SlotSource{

    public GemGradeSource(SlotActor actor) {
        super(actor);

    }

//    @Override
//    public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
//        //payloadSlot = (Slot) payload.getObject();
//        super.dragStop(event, x, y, pointer, payload, target);
//    }

    @Override
    protected void ifSlotTarget(DragAndDrop.Target target) {
        Slot targetSlot = ((SlotActor) target.getActor()).getSlot();
        if (targetSlot.getPrototype() == payloadSlot.getPrototype() || targetSlot.getPrototype() == null) {
            targetSlot.add(payloadSlot.takeAll());
        }
    }
}
