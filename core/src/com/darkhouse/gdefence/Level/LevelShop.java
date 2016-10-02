package com.darkhouse.gdefence.Level;


import com.darkhouse.gdefence.InventorySystem.inventory.Inventory;

public class LevelShop extends Inventory{

    public LevelShop() {
        initSlots(8);
    }

    public LevelShop(Inventory copyInventory) {
        this();
        copy(copyInventory);
    }
}
