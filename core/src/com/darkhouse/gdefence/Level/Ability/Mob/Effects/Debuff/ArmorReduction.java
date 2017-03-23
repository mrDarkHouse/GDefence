package com.darkhouse.gdefence.Level.Ability.Mob.Effects.Debuff;


import com.darkhouse.gdefence.Level.Ability.Tower.Debuff;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class ArmorReduction extends Debuff {
    private int armor;

    public ArmorReduction(Mob owner, int armor, float duration) {
        super(owner, duration);
        this.armor = armor;
    }

    @Override
    public void apply() {
        owner.setArmor(owner.getArmor() - armor);
    }

    @Override
    public void dispell() {
        owner.setArmor(owner.getArmor() + armor);
        owner.deleteDebuff(this.getClass());
    }
}
