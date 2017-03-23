package com.darkhouse.gdefence.Level.Ability.Mob.Effects.Buff;


import com.darkhouse.gdefence.Level.Ability.Tower.Debuff;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class SwimSpeed extends Debuff{


    public SwimSpeed(Mob owner, float duration, float percent) {
        super(owner, duration);
    }

    @Override
    public void apply() {
        owner.changeSpeed(20);
    }

    @Override
    public void dispell() {
        owner.changeSpeed(-20);
    }
}
