package com.darkhouse.gdefence.InventorySystem.inventory.Source;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.*;
import com.darkhouse.gdefence.InventorySystem.inventory.Source.SlotSource;
import com.darkhouse.gdefence.Objects.GameObject;

public class BuySlotSource extends SlotSource {

    public BuySlotSource(SlotActor actor) {
        super(actor);
    }



//    @Override
//    public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
////        Slot payloadSlot = (Slot) payload.getObject();
//        super.dragStop(event, x, y, pointer, payload, target);
//    }

    @Override
    protected void takeSlot() {
        //dont take from shop
        payloadSlot.add(GameObject.generateStartObjects(sourceSlot.getPrototype(), 1));
    }

    @Override
    protected void ifSlotTarget(DragAndDrop.Target target) {
        Slot targetSlot = ((SlotActor) target.getActor()).getSlot();
        if (targetSlot.matches(payloadSlot) || targetSlot.getPrototype() == null) {
            Item item = sourceSlot.getPrototype();
            if(GDefence.getInstance().user.deleteGold(item.getGlobalCost())){
                targetSlot.add(payloadSlot.takeAll());
            } else {
//                System.out.println("No enought money");
            }
        } else {
            //dont swap items
        }
    }

    @Override
    protected void ifNullTarget() {
        payloadSlot.takeAll();
        //no return back
    }
}
