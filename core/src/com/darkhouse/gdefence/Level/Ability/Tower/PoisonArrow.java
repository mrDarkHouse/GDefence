package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Ability.Mob.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class PoisonArrow extends Ability{

    private static class Slow extends Effect {
        private float percent;
        private float changeSpeed;

        public Slow(float percent, float duration) {
            super(false, true, duration, "slow");
            this.percent = percent;
        }

        public void apply(){
            changeSpeed = owner.getSpeed()*percent;
            owner.changeSpeed(-changeSpeed);
        }

        @Override
        public void dispell() {
            owner.changeSpeed(changeSpeed);
            super.dispell();
        }
    }

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
