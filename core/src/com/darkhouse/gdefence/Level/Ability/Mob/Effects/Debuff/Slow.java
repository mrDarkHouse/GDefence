package com.darkhouse.gdefence.Level.Ability.Mob.Effects.Debuff;


import com.darkhouse.gdefence.Level.Ability.Tower.Debuff;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class Slow extends Debuff {
    private float percent;
    private float changeSpeed;

    public Slow(Mob owner, float percent, float duration) {
        super(owner, duration);
        this.percent = percent;
    }

    public void apply(){
        changeSpeed = owner.getSpeed()*percent;
        owner.changeSpeed(-changeSpeed);
    }

    @Override
    public void dispell() {
        owner.changeSpeed(changeSpeed);
        owner.deleteDebuff(this.getClass());
    }
}