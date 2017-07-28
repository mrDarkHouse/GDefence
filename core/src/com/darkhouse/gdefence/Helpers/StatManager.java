package com.darkhouse.gdefence.Helpers;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.InventorySystem.inventory.DropSlot;
import com.darkhouse.gdefence.InventorySystem.inventory.Slot;

public class StatManager {
    private int mobsKilled;
    private int hpLoose;
    private int energySpend;
    private Array<DropSlot> drop;


    public int getMobsKilled() {
        return mobsKilled;
    }
    public void MobsKilledAdd(int mobsKilled) {
        this.mobsKilled += mobsKilled;
    }
    public int getHpLoose() {
        return hpLoose;
    }
    public void HpLooseAdd(int hpLoose) {
        this.hpLoose += hpLoose;
    }
    public int getEnergySpend() {
        return energySpend;
    }
    public void energySpendAdd(int energySpend) {
        this.energySpend += energySpend;
    }

    public Array<DropSlot> getDrop() {
        return drop;
    }
    public void setDrop(Array<DropSlot> drop) {
        this.drop = drop;
    }

    public StatManager() {
        drop = new Array<DropSlot>();//set empty slots for level loose
    }
}
