package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class PoisonArrow extends Ability implements Ability.IOnHit {


    private static class Slow extends Effect<Mob> {
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
    public static class P extends Ability.AblityPrototype{
        private float percent;
        private float duration;

        public P(float percent, float duration) {
            super("Poison Arrow");
            this.percent = percent;
            this.duration = duration;
        }

        @Override
        public Ability getAbility() {
            return new PoisonArrow(this);
        }

        @Override
        public String getTooltip() {
            return "Slow enemies by" + percent*100 + " % for" + duration + " seconds";
        }
    }

//    private Slow slow;

    private float percent;
    private float duration;

    public PoisonArrow(P prototype) {
        this.percent = prototype.percent;
        this.duration = prototype.duration;
    }

//    @Override
//    public void use(Mob target) {
//        if(target != null) {
////            Slow w = new Slow(0.3f, 3f);
////            w.setOwner(target);
//            target.addEffect(new Slow(0.3f, 3f).setOwner(target));
//        }
//    }

    @Override
    public int getDmg(Mob target, int startDmg) {
        if(target != null) {
            target.addEffect(new Slow(percent, duration).setOwner(target));
        }
        return startDmg;
    }

    @Override
    protected void init() {

    }

}
