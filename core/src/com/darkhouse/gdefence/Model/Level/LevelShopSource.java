package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.InventorySystem.inventory.*;
import com.darkhouse.gdefence.Level.MapTile;

public class LevelShopSource extends SlotSource {
    public LevelShopSource(SlotActor actor) {
        super(actor);
    }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
        Slot payloadSlot = (Slot) payload.getObject();
        if(target == null){
            sourceSlot.add(payloadSlot.getItem(), payloadSlot.getAmount());
            return;
        }
        if (target.getClass() == TileTarget.class) {
            Item item = payloadSlot.getItem();
            int amount = payloadSlot.getAmount();
            if (item != null) {
                // Build
                MapTile targetTile = ((MapTileActor) target.getActor()).getMapTile();
                if(targetTile.isBuildable()) {
                    targetTile.build((ItemEnum.Tower) item);
                    if (amount > 1) {
                        sourceSlot.add(item, amount - 1);
                    }
                }else {
                    sourceSlot.add(payloadSlot.getItem(), payloadSlot.getAmount());
                    return;
                }
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
