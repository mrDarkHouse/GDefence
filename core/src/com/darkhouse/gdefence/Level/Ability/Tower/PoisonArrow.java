package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;

import java.util.concurrent.atomic.AtomicReference;

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
    public static class P extends AbilityPrototype {
        private G grader;
        private AtomicReference<Float> slowPercent;
        private AtomicReference<Float> duration;

        public P(float slowPercent, float duration, G grader) {
            super("Poison Arrow", "poisonArrow", grader.gemCap);
            this.slowPercent = new AtomicReference<Float>(slowPercent);
            this.duration = new AtomicReference<Float>(duration);
            this.grader = grader;
        }
        @Override
        public AbilityPrototype copy() {
            P p = new P(slowPercent.get(), duration.get(), grader);
            p.gemBoost[0] = new BoostFloat(p.slowPercent, grader.slowPercentUp, "slow percent",
                    true, BoostFloat.FloatGradeFieldType.PERCENT);
            p.gemBoost[1] = new BoostFloat(p.duration, grader.durationUp, "slow duration",
                    true, BoostFloat.FloatGradeFieldType.TIME);
            return p;
        }

        @Override
        public Ability getAbility() {
            return new PoisonArrow(this);
        }

        @Override
        public String getTooltip() {
            return "Slow enemies by [#000000ff]" + slowPercent.get()*100 + "%[] for [#0ffe00ff]" + duration + "[] seconds";
        }

    }
    public static class G extends AbilityGrader{
        private float slowPercentUp;
        private float durationUp;

        public G(float slowPercentUp, float durationUp, int[] gemCap) {
            super(gemCap);
            this.slowPercentUp = slowPercentUp;
            this.durationUp = durationUp;
        }
    }

//    private Slow slow;

    private float percent;
    private float duration;

    public PoisonArrow(P prototype) {
        this.percent = prototype.slowPercent.get();
        this.duration = prototype.duration.get();
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
