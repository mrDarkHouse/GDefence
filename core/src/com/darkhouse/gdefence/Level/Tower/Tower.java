package com.darkhouse.gdefence.Level.Tower;


import com.darkhouse.gdefence.Level.Ability.Ability;
import com.darkhouse.gdefence.Objects.GameObject;

import java.util.ArrayList;

public abstract class Tower extends GameObject{
    protected TowerType ID;
    protected String name;
    protected AttackType attackType;
    protected int dmg;
    //protected int speed;
    protected float speedDelay;
    protected int cost;
    protected ArrayList<Ability> abilities;

    public TowerType getID() {
        return ID;
    }
    public void setID(TowerType ID) {
        this.ID = ID;
    }
    public ArrayList<Ability> getAbilities() {
        return abilities;
    }
    public void setAbilities(ArrayList<Ability> abilities) {
        this.abilities = abilities;
    }
    public AttackType getAttackType() {
        return attackType;
    }
    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public int getDmg() {
        return dmg;
    }
    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getSpeedDelay() {
        return speedDelay;
    }
    public void setSpeedDelay(float speedDelay) {
        this.speedDelay = speedDelay;
    }

//    public static Tower getTowerByID(int ID){
//        switch (ID){
//            case 0:
//                return new BasicTower();
//            case 1:
//                return new RockTower();
//            case 2:
//                return new ArrowTower();
//            case 3:
//                return new RangeTower();
//            default:
//                return null;
//        }
//    }













}
