package com.darkhouse.gdefence.Objects;


import com.darkhouse.gdefence.InventorySystem.inventory.Item;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;

public class DetailObject extends GameObject{
    private ItemEnum.Detail prototype;

    public DetailObject(ItemEnum.Detail prototype) {
        this.prototype = prototype;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Item getPrototype() {
        return prototype;
    }

    @Override
    public String getTooltip() {
        return null;
    }
}
