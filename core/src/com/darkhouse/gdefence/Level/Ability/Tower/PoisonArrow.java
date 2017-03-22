package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Mob.Mob;

public class PoisonArrow extends Ability{
//    private Slow slow;

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
