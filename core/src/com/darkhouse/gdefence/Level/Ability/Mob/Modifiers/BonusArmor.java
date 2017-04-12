package com.darkhouse.gdefence.Level.Ability.Mob.Modifiers;


public class BonusArmor extends Modifier{
    private int armor;

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public BonusArmor(int armor) {
        this.armor = armor;
    }
}
