package com.darkhouse.gdefence.InventorySystem.inventory.Target;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.darkhouse.gdefence.InventorySystem.inventory.SlotActor;
import com.darkhouse.gdefence.InventorySystem.inventory.Target.SlotTarget;

public class GemGradeTarget extends DragAndDrop.Target{
    public GemGradeTarget(SlotActor actor) {
        super(actor);
    }

    @Override
    public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        getActor().setColor(Color.WHITE);
        return true;
    }

    @Override
    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {

    }
}
