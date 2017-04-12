package com.darkhouse.gdefence.Level.Ability.Mob.Effects.Debuff;


import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class Slow extends Effect {
    private float percent;
    private float changeSpeed;

    public Slow(float percent, float duration) {
        super(false, false, false, duration, "slow");
        this.percent = percent;
    }

    public void apply(){
        changeSpeed = owner.getSpeed()*percent;
        owner.changeSpeed(-changeSpeed);
    }

    @Override
    public void dispell() {
        owner.changeSpeed(changeSpeed);
        owner.deleteEffect(this.getClass());
    }
}
