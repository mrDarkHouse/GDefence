package com.darkhouse.gdefence.Model;

import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.InventorySystem.inventory.Inventory;
import com.darkhouse.gdefence.InventorySystem.inventory.Slot;
import com.darkhouse.gdefence.Objects.TowerObject;


public class PreparationTowerInventory extends Inventory{

    public PreparationTowerInventory() {
        super(TowerObject.class, 4);
    }


}
