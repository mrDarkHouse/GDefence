package com.darkhouse.gdefence.InventorySystem.inventory;


import com.darkhouse.gdefence.Objects.GameObject;

//show not existing item
//used for show prototypes


public class DropSlot extends AbstractSlot{
    private Item prototype;
    private int amount;

    public Item getPrototype() {
        return prototype;
    }
    public int getAmount() {
        return amount;
    }

    @Override
    public String getTitle() {
        return prototype.getName();
    }

    @Override
    public String getTooltip() {
        return prototype.getTooltip();
    }

    @Override
    public boolean isEmpty() {
        return amount == 0;
    }

    public DropSlot(Item prototype, int amount) {
        this.prototype = prototype;
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "DropSlot[" + prototype + ":" + amount + "]";
    }

}
