package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.User;

import java.util.concurrent.atomic.AtomicReference;

public class ShotDelay extends Ability implements Ability.IPreAttack {

    public static class P extends AbilityPrototype {
        private G grader;
        private AtomicReference<Float> delay;

        public P(float delay, G grader) {
            super("Attack Delay", "shotDelay", grader.gemCap);
            this.delay = new AtomicReference<Float>(delay);
            this.grader = grader;
        }
        @Override
        public AbilityPrototype copy() {
            P p = new P(delay.get(), grader);
            p.gemBoost[0] = new BoostFloat(p.delay, grader.delayDown, "delay before attack",
                    false, BoostFloat.FloatGradeFieldType.TIME);
            return p;
        }

        @Override
        public Ability getAbility() {
            return new ShotDelay(this);
        }

        @Override
        public String getTooltip() {
            return "Wait [#000000ff]" + delay + "[] seconds before attack";
        }
    }
    public static class G extends AbilityGrader{
        private float delayDown;

        public G(float delayDown, int[] gemCap) {
            super(gemCap);
            this.delayDown = delayDown;
        }
    }

    private class AttackBlocker extends Effect<Tower>{
        public AttackBlocker(float duration) {
            super(true, false, duration, "swimSpeed");
        }

        @Override
        public void apply() {
            owner.setCanAttack(false);
        }

        @Override
        public void dispell() {
            if(!owner.isCanAttack()) owner.setCanAttack(true);//this dispell other disarm
            super.dispell();
        }
    }

    private float delay;
    private float currentTime;

    public ShotDelay(P prototype) {
        this.delay = prototype.delay.get();
    }

    @Override
    public boolean use(float delta) {
        currentTime += delta;
        if(currentTime >= delay) {
            currentTime = 0;
            return true;
        }else return false;
//        owner.addEffect(new AttackBlocker(delay).setOwner(owner));
//        return true;
    }

    @Override
    protected void init() {

    }
}
