package com.darkhouse.gdefence.Objects;


import com.darkhouse.gdefence.InventorySystem.inventory.Item;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;

public class SpellObject extends GameObject{

    public SpellObject(ItemEnum.Spell prototype) {
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
