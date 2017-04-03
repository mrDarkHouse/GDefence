package com.darkhouse.gdefence.Level.Ability.Mob.Effects.Debuff;


import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class Slow extends Effect {
    private float percent;
    private float changeSpeed;

    public Slow(Mob owner, float percent, float duration) {
        super(false, false, owner, duration, "slow");
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
