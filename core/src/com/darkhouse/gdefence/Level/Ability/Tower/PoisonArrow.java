package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Debuff.Slow;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class PoisonArrow extends Ability{
//    private Slow slow;

    public PoisonArrow() {
        super(UseType.onHit);
    }

    @Override
    public void use(Mob target) {
        if(target != null) {
//            Slow w = new Slow(0.3f, 3f);
//            w.setOwner(target);
            target.addEffect(new Slow(0.3f, 3f).setOwner(target));
        }
    }
}
