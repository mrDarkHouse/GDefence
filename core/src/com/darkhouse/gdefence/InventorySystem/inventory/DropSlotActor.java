package com.darkhouse.gdefence.InventorySystem.inventory;


import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

public class DropSlotActor extends ImageButton{

    private DropSlot slot;
    public DropSlot getSlot() {
        return slot;
    }

    //    private Skin skin;
    private Amount amount;

    public DropSlotActor(DropSlot slot, Skin skin) {
        super(SlotActor.createStyle(skin, slot.getPrototype()));

        this.slot = slot;
        amount = new Amount(skin, slot);
        addActor(amount);
        amount.setAlignment(Align.center);

    }
}
