package com.darkhouse.gdefence.InventorySystem.inventory;


import com.badlogic.gdx.utils.Array;

public abstract class AbstractSlot {
    private Array<SlotListener> slotListeners = new Array<SlotListener>();

    public void addListener(SlotListener slotListener) {
        slotListeners.add(slotListener);
    }

    public void removeListener(SlotListener slotListener) {
        slotListeners.removeValue(slotListener, true);
    }

    public void notifyListeners() {
        for (SlotListener slotListener : slotListeners) {
            slotListener.hasChanged(this);
        }
    }


    public abstract Item getPrototype();
    public abstract int getAmount();
    public abstract String getTitle();
    public abstract String getTooltip();
    public abstract boolean isEmpty();
}
