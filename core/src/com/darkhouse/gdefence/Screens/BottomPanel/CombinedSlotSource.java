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
            sourceSlot.add(payloadSlot.getItem(), payloadSlot.getAmount());
            return;
        }
        if (target.getClass() == SellTarget.class) {
            Item item = payloadSlot.getItem();
            int amount = payloadSlot.getAmount();
            if (item != null) {
                GDefence.getInstance().user.addGold(item.getGlobalCost() * amount);
            } else {
                System.out.println("Item - null");
            }
        } else if(target.getClass() == SlotTarget.class){
            Slot targetSlot = ((SlotActor) target.getActor()).getSlot();
            if (targetSlot.getItem() == payloadSlot.getItem() || targetSlot.getItem() == null) {
                targetSlot.add(payloadSlot.getItem(), payloadSlot.getAmount());
            } else {
                Item targetType = targetSlot.getItem();
                int targetAmount = targetSlot.getAmount();
                targetSlot.take(targetAmount);
                targetSlot.add(payloadSlot.getItem(), payloadSlot.getAmount());
                sourceSlot.add(targetType, targetAmount);
            }
        } else {

        }
    }




}
