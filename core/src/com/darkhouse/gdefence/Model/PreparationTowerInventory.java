package com.darkhouse.gdefence.Model;

import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.InventorySystem.inventory.Inventory;
import com.darkhouse.gdefence.InventorySystem.inventory.Slot;


public class PreparationTowerInventory extends Inventory{

    public PreparationTowerInventory() {
        super();
    }

    @Override
    protected void initSlots() {
        slots = new Array<Slot>(4);
        for (int i = 0; i < 4; i++) {
            slots.add(new Slot(null, 0));
        }
    }
}
