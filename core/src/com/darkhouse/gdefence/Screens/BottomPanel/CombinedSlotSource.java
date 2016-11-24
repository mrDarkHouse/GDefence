package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.*;

public class CombinedSlotSource extends SlotSource{
    public CombinedSlotSource(SlotActor actor) {
        super(actor);
    }


    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
        Slot payloadSlot = (Slot) payload.getObject();
        if(target == null){
            sourceSlot.add(payloadSlot.getPrototype(), payloadSlot.getAmount());
            return;
        }
        if (target.getClass() == SellTarget.class) {
            Item item = payloadSlot.getPrototype();
            int amount = payloadSlot.getAmount();
            if (item != null) {
                GDefence.getInstance().user.addGold(item.getGlobalCost() * amount);
            } else {
                System.out.println("Item - null");
            }
        } else if(target.getClass() == SlotTarget.class){
            Slot targetSlot = ((SlotActor) target.getActor()).getSlot();
            if (targetSlot.getPrototype() == payloadSlot.getPrototype() || targetSlot.getPrototype() == null) {
                targetSlot.add(payloadSlot.getPrototype(), payloadSlot.getAmount());
            } else {
                Item targetType = targetSlot.getPrototype();
                int targetAmount = targetSlot.getAmount();
                targetSlot.take(targetAmount);
                targetSlot.add(payloadSlot.getPrototype(), payloadSlot.getAmount());
                sourceSlot.add(targetType, targetAmount);
            }
        } else if(target instanceof GemGradeTarget){
            Slot targetSlot = ((SlotActor) target.getActor()).getSlot();
            if(targetSlot.getPrototype() == null){
                int noNeed = 0;
                if (payloadSlot.getAmount() > 1){
                    noNeed = payloadSlot.getAmount() - 1;
                }
                targetSlot.add(payloadSlot.getPrototype(), 1);
                if(noNeed > 0) {
                    sourceSlot.add(payloadSlot.getPrototype(), noNeed);
                }


            }
        }
    }




}
