package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.User;

import java.util.concurrent.atomic.AtomicReference;

public class ShotDelay extends Ability implements Ability.IPreAttack {

    public static class P extends AbilityPrototype {
        private G g;
        private AtomicReference<Float> delay;

        public P(float delay, G grader) {
            super(9, "shotDelay", grader.gemCap);
            this.delay = new AtomicReference<Float>(delay);
            this.g = grader;
        }

        @Override
        public String getSaveCode() {
            return super.getSaveCode() + "z" + delay.get() + ";" + g.delayDown;
        }

        @Override
        public AbilityPrototype copy() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            P p = new P(delay.get(), g);
            p.gemBoost[0] = new BoostFloat(p.delay, g.delayDown, l.getWord("shotDelayGrade1"),
                    false, BoostFloat.FloatGradeFieldType.TIME);
            return p;
        }

        @Override
        public Ability getAbility() {
            return new ShotDelay(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("shotDelayTooltip1") + " " + FontLoader.colorString(delay.get().toString(), 0) + " " +
                    l.getWord("shotDelayTooltip2");
        }
    }
    public static class G extends AbilityGrader{
        private float delayDown;

        public G(float delayDown, int[] gemCap) {
            super(gemCap);
            this.delayDown = delayDown;
        }
    }

    private class HeatChecker extends Effect<Tower>{
        public HeatChecker(float duration) {
            super(true, false, duration, "swimSpeed");
        }

        @Override
        public void apply() {
//            owner.setCanAttack(false);
        }

        @Override
        public void dispell() {
            heat = false;
//            if(!owner.isCanAttack()) owner.setCanAttack(true);//this dispell other disarm
            super.dispell();
        }
    }

    private float delay;
    private float heatOffTime = 5f;
    private float currentTime;
    private boolean heat;

    public ShotDelay(P prototype) {
        this.delay = prototype.delay.get();
    }

    @Override
    public boolean use(float delta) {
        if(!heat){
            currentTime += delta;
            if(currentTime >= delay) {
                currentTime = 0;
                heat = true;
                owner.addEffect(new HeatChecker(heatOffTime).setOwner(owner));
                return true;
            }else {
                return false;
            }
        }else {
            owner.addEffect(new HeatChecker(heatOffTime).setOwner(owner));
            return true;
        }
//        currentTime += delta;
//        if(currentTime >= delay) {
//            currentTime = 0;
//            return true;
//        }else return false;
//        owner.addEffect(new AttackBlocker(delay).setOwner(owner));
//        return true;
    }

    @Override
    protected void init() {

    }
}
