package com.darkhouse.gdefence.InventorySystem.inventory.Source;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.*;
import com.darkhouse.gdefence.Objects.TowerObject;

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
    protected boolean takeSlot() {
        //dont take from shop
        if(Gdx.input.isButtonPressed(0)) {
            payloadSlot.add(TowerObject.generateStartObjects(sourceSlot.getPrototype(), 1));
            return true;
        }else return false;
    }

    @Override
    protected void ifSlotTarget(DragAndDrop.Target target) {
        Slot targetSlot = ((SlotActor) target.getActor()).getSlot();
        if (!targetSlot.isFull() && (targetSlot.matches(payloadSlot) || targetSlot.getPrototype() == null)) {
            Item item = sourceSlot.getPrototype();
            if(GDefence.getInstance().user.deleteGold(item.getGlobalCost())){
                targetSlot.add(payloadSlot.takeAll());
            } else {
//                System.out.println("No enough money");
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
