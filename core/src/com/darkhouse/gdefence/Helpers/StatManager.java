package com.darkhouse.gdefence.Helpers;


public class StatManager {
    private int mobsKilled;
    private int hpLoose;
    private int energySpend;


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


    public StatManager() {

    }
}
