package com.darkhouse.gdefence.Objects;


import com.darkhouse.gdefence.InventorySystem.inventory.Item;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;

public class DetailObject extends GameObject{
    public DetailObject(ItemEnum.Detail prototype) {

    }

    @Override
    public Item getPrototype() {
        return null;
    }

    @Override
    public String getTooltip() {
        return null;
    }
}
