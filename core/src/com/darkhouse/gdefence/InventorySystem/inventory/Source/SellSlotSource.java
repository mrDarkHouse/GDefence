package com.darkhouse.gdefence.InventorySystem.inventory.Source;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.*;
import com.darkhouse.gdefence.InventorySystem.inventory.Target.SellTarget;
import com.darkhouse.gdefence.Objects.GameObject;

public class SellSlotSource extends SlotSource {
    public SellSlotSource(SlotActor actor) {
        super(actor);
    }


    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
        if (target instanceof SellTarget) {
            if(payloadSlot.getLast().getGlobalCost() == 0) {
                ifNullTarget();//not sell unsellable items (spells)
                return;
            }

//            Item item = payloadSlot.getPrototype();
            GameObject o = payloadSlot.getLast();
            int amount = payloadSlot.getAmount();
            if (o != null) {
                GDefence.getInstance().user.addGold(o.getGlobalCost() * amount);
            } else {
                GDefence.getInstance().log("SellSlotSource: Item - null");
            }
            return;
        }

        super.dragStop(event, x, y, pointer, payload, target);

         /*else if(target instanceof GemGradeTarget){
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
        } else if(target instanceof TowerCraftTarget){
            //
        }*/
    }



}
