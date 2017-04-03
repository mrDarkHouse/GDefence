package com.darkhouse.gdefence.Objects;


import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.User;

import java.util.ArrayList;

public class TowerObject extends GameObject{
    public static int[] exp2nextLvl = {30, 70, 130, 190, 260, 340, 430, 530};

    private ItemEnum.Tower prototype;
    private int level;
    private float totalExp;
    private float currentExp;

    private int range;
    private int dmg;
    private int speed;
    private int cost;
    private int globalCost;
    protected ArrayList<Ability> abilities;




    @Override
    public String getSaveCode() {
        String gemsCode = "";
        for (int i = 0; i < gemsNumber.length; i++){
            gemsCode+= gemsNumber[i];
            if(i!= gemsNumber.length - 1){//may be unnecessary
                gemsCode+= ";";
            }
        }
        return prototype.name() + "-" + totalExp + "-" + gemsCode;
    }


    public static TowerObject loadSaveCode(String save) {
        String[] info = save.split("-");
        String[] gemsString = info[2].split(";");

        TowerObject t = new TowerObject(ItemEnum.Tower.getTower(info[0]));
        t.addExp(Float.parseFloat(info[1]));
        t.updateExp();//adding level

        for (int i = 0; i < gemsString.length; i++){
            t.addGems(User.GEM_TYPE.values()[i], Integer.parseInt(gemsString[i]));
        }


        return t;
    }

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
    public int getSpeed() {
        return speed;
    }

    //    private HashMap<User.GEM_TYPE, Integer> gemsNumber;
    private int[] gemsNumber;



    public void addExp(float value){
        totalExp += value;
        updateExp();
    }
    public void addGems(User.GEM_TYPE type, int value){
        gemsNumber[type.ordinal()] += value;
        updateGemStat(type.ordinal(), value);
    }

    public int getPrimaryGemsNumber(){
        return gemsNumber[0] + gemsNumber[1] + gemsNumber[2];
    }

    public int getLevel() {
        return level;
    }
    public float getTotalExp() {
        return totalExp;
    }
    public float getCurrentExp() {
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
//        super(prototype);
        this.prototype = prototype;
        level = 1;
        totalExp = 0;
        updateExp();

        dmg = prototype.getDmg();
        range = prototype.getRange();
        speed = prototype.getSpeed();
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
                + "Speed: " + getSpeed() + "(" + Tower.getAttackSpeedDelay(getSpeed()) + ")" + System.getProperty("line.separator")
                + "Cost: " + getCost();//+ System.getProperty("line.separator")
//                + "Level: " + getLevel() + System.getProperty("line.separator")
//                + "Exp:" + getCurrentExp();
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
                    speed += 10;
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
        for(int i = level - 1; currentExp >= exp2nextLvl[i]; i ++){//if max lvl throws exeption
            currentExp -= exp2nextLvl[i];
            level++;
        }
    }

    public boolean equalsOrHigher(TowerObject anotherTower){
        int[] first = getSimplyGemStat();
        int[] second = anotherTower.getSimplyGemStat();
        boolean b = second[0] >= first[0] && second[1] >= first[1] && second[2] >= first[2];
        return (getPrototype() == anotherTower.getPrototype() && b);
    }

}
