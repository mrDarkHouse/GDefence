package com.darkhouse.gdefence.Helpers;


public class StatManager {
    private int mobsKilled;
    private int hpLoose;
    private int moneySpend;


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
    public int getMoneySpend() {
        return moneySpend;
    }
    public void MoneySpendAdd(int moneySpend) {
        this.moneySpend += moneySpend;
    }


    public StatManager() {
    }
}
