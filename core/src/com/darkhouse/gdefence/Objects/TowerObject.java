package com.darkhouse.gdefence.Objects;


import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Level.Ability.Ability;
import com.darkhouse.gdefence.Level.Tower.AttackType;
import com.darkhouse.gdefence.User;

import java.util.ArrayList;
import java.util.HashMap;

public class TowerObject extends GameObject{
    public static int[] exp2nextLvl = {30, 70, 130, 190, 260, 340, 430, 530};

    private ItemEnum.Tower prototype;
    private int level;
    private int totalExp;
    private int currentExp;

    private int range;
    private int dmg;
    //protected int speed;
    private float speedDelay;
    private int cost;
    private int globalCost;
    protected ArrayList<Ability> abilities;

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }
//    public AttackType getAttackType() {
//        return attackType;
//    }

    public ItemEnum.Tower getPrototype() {
        return prototype;
    }

    public int getRange() {
        return range;
    }
    public int getCost() {
        return cost;
    }
    public int getGlobalCost() {
        return globalCost;
    }
    public int getDmg() {
        return dmg;
    }
    public String getName() {
        return prototype.getName() + " " + getSimplyGemStatString();
    }
    public float getSpeedDelay() {
        return speedDelay;
    }

//    private HashMap<User.GEM_TYPE, Integer> gemsNumber;
    private int[] gemsNumber;



    public void addExp(int value){
        totalExp += value;
        updateExp();
    }
    public void addGems(User.GEM_TYPE type, int value){
        gemsNumber[type.ordinal()] += value;
        updateGemStat(type.ordinal(), value);
    }

    public int getLevel() {
        return level;
    }
    public int getTotalExp() {
        return totalExp;
    }
    public int getCurrentExp() {
        return currentExp;
    }
    public int[] getSimplyGemStat(){
        return new int[]{gemsNumber[0], gemsNumber[1], gemsNumber[2]};
    }
    public String getSimplyGemStatString(){
        int[] gems = getSimplyGemStat();
        String s = "{";
        for (int i = 0; i < gems.length; i++){
            s += gems[i];
            if(i != gems.length - 1){
                s += ", ";
            }
        }
        s += "}";
        return s;
    }


    public TowerObject(ItemEnum.Tower prototype) {
        this.prototype = prototype;
        level = 1;
        totalExp = 0;
        updateExp();

        dmg = prototype.getDmg();
        range = prototype.getRange();
        speedDelay = prototype.getSpeedDelay();
        cost = prototype.getCost();
        globalCost = prototype.getGlobalCost();
        abilities = new ArrayList<Ability>(prototype.getAbilities());


        gemsNumber = new int[]{0, 0, 0, 0, 0, 0};
//        gemsNumber = new HashMap<User.GEM_TYPE, Integer>();
//        gemsNumber.put(User.GEM_TYPE.RED, 0);
    }

    @Override
    public String getTooltip() {
        return "Dmg: " + getDmg() + System.getProperty("line.separator")
                + "Range: " + getRange() + System.getProperty("line.separator")
                + "Speed: " + getSpeedDelay() + System.getProperty("line.separator") + "Cost: " + getCost();
    }

    public void updateGemStat(int gemType, int value){
        switch (gemType){
            case 0:
                for (int i = 0; i < value; i++){
                    dmg += 5;
                }
                break;
            case 1:
                for (int i = 0; i < value; i++){
                    speedDelay -= 0.2f;
                }
                break;
            case 2:
                for (int i = 0; i < value; i++){
                    range += 20;
                }
                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
        }
    }

    public void updateExp(){
        currentExp = getTotalExp();
        for(int i = level - 1; currentExp >= exp2nextLvl[i]; i ++){
            currentExp -= exp2nextLvl[i];
            level++;
        }
    }
}
