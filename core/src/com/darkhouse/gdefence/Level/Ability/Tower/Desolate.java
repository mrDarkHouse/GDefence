package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Debuff.ArmorReduction;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class Desolate extends Ability{
    private int armor;
    private float duration;

    public Desolate(int armor, float duration) {
        super(UseType.onHit);
        this.armor = armor;
        this.duration = duration;
    }

    @Override
    public void use(Mob target) {
        if(target != null) {
            target.addEffect(new ArmorReduction(armor, duration).setOwner(target));
        }
    }
}
