package com.darkhouse.gdefence.Level.Ability.Tower;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.User;

import java.util.concurrent.atomic.AtomicReference;

public class ShotDelay extends Ability implements Ability.IPreAttack {

    public static class P extends AbilityPrototype {
        private G g;
        private AtomicReference<Float> delay;
        private boolean onlyFirstAttack;

        public P(float delay, boolean onlyFirstAttack, G grader) {
            super(9, "shotDelay", grader.gemCap, IPreAttack.class);
            this.delay = new AtomicReference<Float>(delay);
            this.onlyFirstAttack = onlyFirstAttack;
            this.g = grader;
        }

        @Override
        public Array<Class<? extends AbilityPrototype>> getAbilitiesToSaveOnCraft() {
            Array<Class<? extends AbilityPrototype>> a = new Array<Class<? extends AbilityPrototype>>();
            a.add(ShotDelay.P.class);
            return a;
        }

//        @Override
//        public String getSaveCode() {
//            return super.getSaveCode() + "z" + delay.get() + ";" + onlyFirstAttack + ";" + g.delayDown;
//        }

        @Override
        public AbilityPrototype copy() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            P p = new P(delay.get(), onlyFirstAttack, g);
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
            String s = l.getWord("shotDelayTooltip1") + " " + FontLoader.colorString(delay.get().toString(), User.GEM_TYPE.BLACK) + " " +
                    l.getWord("shotDelayTooltip2") + System.getProperty("line.separator");
            if(onlyFirstAttack) s += l.getWord("shotDelayTooltip3");
            else                s += l.getWord("shotDelayTooltip4");
            return s;
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
    private boolean onlyFirstAttack;
    private float heatOffTime = 5f;
    private float currentTime;
    private boolean heat;

    public ShotDelay(P prototype) {
        this.delay = prototype.delay.get();
        this.onlyFirstAttack = prototype.onlyFirstAttack;
    }

    @Override
    public boolean use(float delta) {
        if(onlyFirstAttack) {
            if (!heat) {
                currentTime += delta;
                if (currentTime >= delay) {
                    currentTime = 0;
                    heat = true;
                    owner.addEffect(new HeatChecker(heatOffTime).setOwner(owner));
                    return true;
                } else {
                    return false;
                }
            } else {
                owner.addEffect(new HeatChecker(heatOffTime).setOwner(owner));
                return true;
            }
        }else {
            currentTime += delta;
            if (currentTime >= delay) {
                currentTime = 0;
                return true;
            } else return false;
//            owner.addEffect(new AttackBlocker(delay).setOwner(owner));
//            return true;
        }
    }

    @Override
    protected void init(Map map) {

    }
}
