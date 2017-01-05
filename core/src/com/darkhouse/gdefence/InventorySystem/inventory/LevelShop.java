package com.darkhouse.gdefence.InventorySystem.inventory;


import com.darkhouse.gdefence.InventorySystem.inventory.Inventory;
import com.darkhouse.gdefence.Objects.TowerObject;

public class LevelShop extends Inventory{

    public LevelShop() {
        super(TowerObject.class, 8);
    }

    public LevelShop(Inventory copyInventory) {
        this();
        copy(copyInventory);
    }
}
