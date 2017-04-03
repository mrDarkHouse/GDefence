package com.darkhouse.gdefence.Level.Ability.Mob.Effects.Buff;


import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class SwimSpeed extends Effect {

    public SwimSpeed(Mob owner, float duration, float percent) {
        super(true, false, owner, duration, "swimSpeed");
    }

    @Override
    public void apply() {
        owner.changeSpeed(20);
    }

    @Override
    public void dispell() {
        owner.changeSpeed(-20);
        owner.deleteDebuff(this.getClass());
    }
}
