package com.darkhouse.gdefence.Level.Ability;


import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Tower;

public class PoisonArrow extends Ability{
    public PoisonArrow() {
        super(UseType.onHit);
    }

    @Override
    public void use(Mob target) {
        if(target != null) {
            target.addDebuff(new Slow(target, 0.3f, 3f));
        }
    }
}
