package com.darkhouse.gdefence.InventorySystem.inventory.Source;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.darkhouse.gdefence.InventorySystem.inventory.SlotActor;
import com.darkhouse.gdefence.InventorySystem.inventory.Target.TileTarget;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Model.Level.MapTileActor;
import com.darkhouse.gdefence.Objects.TowerObject;

public class LevelShopSource extends SlotSource {
    private DragAndDrop dragAndDrop;

    public LevelShopSource(SlotActor actor, DragAndDrop dragAndDrop) {
        super(actor);
        this.dragAndDrop = dragAndDrop;
    }

    @Override
    public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
        TowerObject tower = (TowerObject) sourceSlot.getLast();


        DragAndDrop.Payload payload = super.dragStart(event, x, y, pointer);
        Level.getMap().setBuild(true, tower, payload);   //start drawing build grid

        Image i = ((Image)payload.getValidDragActor());
        Image inv = ((Image)payload.getDragActor());
        TextureRegionDrawable t = new TextureRegionDrawable(new TextureRegion(tower.getPrototype().getTowerTexture()));
        i.setDrawable(t);
        inv.setDrawable(t);
        dragAndDrop.setDragActorPosition(-i.getWidth()/2, i.getHeight()/2);
        return payload;
    }

    @Override
    protected void takeSlot() {
        payloadSlot.add(sourceSlot.take(1));
    }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
        Level.getMap().setBuild(false, null, null);// end drawing build grid
        super.dragStop(event, x, y, pointer, payload, target);
        if (target instanceof TileTarget) {
            TowerObject tower = ((TowerObject) payloadSlot.getLast());
            if (tower != null) {
                // Build
                MapTile targetTile = ((MapTileActor) target.getActor()).getMapTile();
                if(targetTile.build(tower)) {
//                    if (amount > 1) {
//                        sourceSlot.add(item, amount - 1);
//                    }
                }else {
                    sourceSlot.add(payloadSlot.takeAll());
                    return;
                }
            }
        }
    }



}
