package com.darkhouse.gdefence.InventorySystem.inventory;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.*;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.MapTile;
import com.darkhouse.gdefence.Model.Level.MapTileActor;

public class LevelShopSource extends SlotSource {
    private DragAndDrop dragAndDrop;

    public LevelShopSource(SlotActor actor, DragAndDrop dragAndDrop) {
        super(actor);
        this.dragAndDrop = dragAndDrop;
    }

    @Override
    public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
        ItemEnum.Tower tower = (ItemEnum.Tower) sourceSlot.getItem();


        DragAndDrop.Payload payload = super.dragStart(event, x, y, pointer);
        Level.getMap().setBuild(true, tower, payload);   //start drawing build grid

        Image i = ((Image)payload.getValidDragActor());
        Image inv = ((Image)payload.getDragActor());
        TextureRegionDrawable t = new TextureRegionDrawable(new TextureRegion(GDefence.getInstance().assetLoader.getTowerTexture(tower)));
        i.setDrawable(t);
        inv.setDrawable(t);
        dragAndDrop.setDragActorPosition(-i.getWidth()/2, i.getHeight()/2);
        return payload;
    }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
        Level.getMap().setBuild(false, null, null);// end drawing build grid
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
                if(targetTile.build((ItemEnum.Tower) item)) {
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
