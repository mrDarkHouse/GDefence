package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Model.Level.Map;

import java.util.concurrent.atomic.AtomicReference;

public class Splash extends Ability implements Ability.IAfterHit{

    public static class P extends AbilityPrototype {
        private G grader;
        private AtomicReference<Float> aoeDmg;
        private AtomicReference<Integer> aoe;

        public P(int aoe, float aoeDmg, G grader) {
            super(10, "Splash", "splash", grader.gemCap);
            this.aoe = new AtomicReference<Integer>(aoe);
            this.aoeDmg = new AtomicReference<Float>(aoeDmg);
            this.grader = grader;
        }

        @Override
        public String getSaveCode() {
            return null;
        }

        @Override
        public AbilityPrototype copy() {
            P p = new P(aoe.get(), aoeDmg.get(), grader);
            p.gemBoost[0] = new BoostInteger(p.aoe, grader.aoeUp, "splash radius",
                    true, BoostInteger.IntegerGradeFieldType.NONE);
            p.gemBoost[1] = new BoostFloat(p.aoeDmg, grader.aoeDmgUp, "splash damage",
                    true, BoostFloat.FloatGradeFieldType.PERCENT);
            return p;
        }

        @Override
        public Ability getAbility() {
            return new Splash(this);
        }

        @Override
        public String getTooltip() {
            return "Additionaly dealing [#0ffe00ff]" + aoeDmg.get()*100 + "[] dmg" + System.getProperty("line.separator")
                    + " in [#000000ff]" + aoe + "[] range";
        }

    }
    public static class G extends AbilityGrader{
        private int aoeUp;
        private float aoeDmgUp;

        public G(int aoeUp, float aoeDmgUp, int[] gemCap) {
            super(gemCap);
            this.aoeUp = aoeUp;
            this.aoeDmgUp = aoeDmgUp;
        }
    }

    private float aoeDmg;
    private int aoe;

    public Splash(P prototype) {
        this.aoe = prototype.aoe.get();
        this.aoeDmg = prototype.aoeDmg.get();
    }

    @Override
    public void hit(Mob target, int dmg, Projectile hittingProjectile) {
//        for (Mob m: Wave.mobs){
//
//        }
        for (Mob m:Map.getMobsInRange(target, aoe)){
            owner.hitTarget(m, (int) (dmg*aoeDmg));
        }
    }

    @Override
    protected void init() {

    }
}
