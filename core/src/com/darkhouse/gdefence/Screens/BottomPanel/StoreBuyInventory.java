package com.darkhouse.gdefence.Screens.BottomPanel;


import com.darkhouse.gdefence.InventorySystem.inventory.Inventory;
import com.darkhouse.gdefence.Objects.TowerObject;

public class StoreBuyInventory extends Inventory{

    public StoreBuyInventory() {
        super(TowerObject.class, 5);
    }

}
