package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.*;

public class BuySlotSource extends SlotSource{

    public BuySlotSource(SlotActor actor) {
        super(actor);
    }

    @Override
    protected void takeSlot() {
        //dont take from shop
    }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
        Slot payloadSlot = (Slot) payload.getObject();
        if (target != null) {
            Slot targetSlot = ((SlotActor) target.getActor()).getSlot();
            if (targetSlot.getItem() == payloadSlot.getItem() || targetSlot.getItem() == null) {
                Item item = sourceSlot.getItem();
                if(GDefence.getInstance().user.deleteGold(item.getGlobalCost())){
                    targetSlot.add(payloadSlot.getItem(), payloadSlot.getAmount());
                } else {
                    System.out.println("No enought money");
                }
            } else {
                //dont swap items
            }
        } else {
            //sourceSlot.add(payloadSlot.getItem(), payloadSlot.getAmount());
        }
    }


}
