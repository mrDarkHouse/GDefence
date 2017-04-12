package com.darkhouse.gdefence.Level.Ability.Mob.Effects.Buff;


import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class SwimSpeed extends Effect {

    public SwimSpeed(float duration, float percent) {
        super(true, false, false, duration, "swimSpeed");
    }


    @Override
    public void apply() {
        owner.changeSpeed(20);
    }

    @Override
    public void dispell() {
        owner.changeSpeed(-20);
        owner.deleteEffect(this.getClass());
    }
}
