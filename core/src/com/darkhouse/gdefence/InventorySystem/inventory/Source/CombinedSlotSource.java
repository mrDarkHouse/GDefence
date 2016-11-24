package com.darkhouse.gdefence.InventorySystem.inventory.Source;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.*;
import com.darkhouse.gdefence.InventorySystem.inventory.Target.SlotTarget;
import com.darkhouse.gdefence.InventorySystem.inventory.Target.GemGradeTarget;
import com.darkhouse.gdefence.InventorySystem.inventory.Target.SellTarget;

public class CombinedSlotSource extends SlotSource {
    public CombinedSlotSource(SlotActor actor) {
        super(actor);
    }


    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
        super.dragStop(event, x, y, pointer, payload, target);
        if (target instanceof SellTarget) {
            Item item = payloadSlot.getPrototype();
            int amount = payloadSlot.getAmount();
            if (item != null) {
                GDefence.getInstance().user.addGold(item.getGlobalCost() * amount);
            } else {
                System.out.println("Item - null");
            }
        } else if(target instanceof GemGradeTarget){
            Slot targetSlot = ((SlotActor) target.getActor()).getSlot();
            if(targetSlot.isEmpty()){//targetSlot.getPrototype() == null
                int noNeed = 0;
                if (payloadSlot.getAmount() > 1){
                    noNeed = payloadSlot.getAmount() - 1;
                }
                targetSlot.add(payloadSlot.take(1));
                if(noNeed > 0) {
                    sourceSlot.add(payloadSlot.takeAll());//all remaining
                }
            }
        }
    }



}
