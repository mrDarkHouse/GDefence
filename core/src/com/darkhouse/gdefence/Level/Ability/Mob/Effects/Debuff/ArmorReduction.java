package com.darkhouse.gdefence.Level.Ability.Mob.Effects.Debuff;


import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class ArmorReduction extends Effect {
    private int armor;

    public ArmorReduction(Mob owner, int armor, float duration) {
        super(false, false, false, owner, duration, "armorReduction");
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
