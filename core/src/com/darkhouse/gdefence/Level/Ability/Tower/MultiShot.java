package com.darkhouse.gdefence.Level.Ability.Tower;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Level.Wave;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.User;

import java.util.concurrent.atomic.AtomicReference;

public class MultiShot extends Ability implements Ability.IPreShot {

    public static class P extends AbilityPrototype {
        private G grader;
        private AtomicReference<Integer> bonusTarget;
        private AtomicReference<Float> dmgPercent;//not work yet

        public P(int bonusTarget, float dmgPercent, G grader) {
            super("MultiShot", "multiShot", grader.gemCap);
            this.bonusTarget = new AtomicReference<Integer>(bonusTarget);
            this.dmgPercent = new AtomicReference<Float>(dmgPercent);
            this.grader = grader;
        }

        @Override
        public AbilityPrototype copy() {
            P p = new P(bonusTarget.get(), dmgPercent.get(), grader);
            p.gemBoost[0] = new BoostInteger(p.bonusTarget, grader.bonusTargetUp, "bonus target",
                    true, BoostInteger.IntegerGradeFieldType.NONE);
            p.gemBoost[1] = new BoostFloat(p.dmgPercent, grader.dmgPercentUp, "damage from additional projectiles",
                    true, BoostFloat.FloatGradeFieldType.PERCENT);
            return p;
        }

        @Override
        public Ability getAbility() {
            return new MultiShot(this);
        }

        @Override
        public String getTooltip() {
            return "Can shot to [#000000ff]" + bonusTarget + "[] bonus targets" + System.getProperty("line.separator") +
                    "Additional projectiles deal [#0ffe00ff]" + dmgPercent.get()*100 + "%[] damage";
        }

    }
    public static class G extends AbilityGrader{
        private int bonusTargetUp;
        private float dmgPercentUp;

        public G(int bonusTargetUp, float dmgPercentUp, int[] gemCap) {
            super(gemCap);
            this.bonusTargetUp = bonusTargetUp;
            this.dmgPercentUp = dmgPercentUp;
        }
    }

    private int bonusTarget;
    private Array <Mob> targets;

    public MultiShot(P prototype) {
        this.bonusTarget = prototype.bonusTarget.get();
        targets = new Array<Mob>(/*bonusTarget*/);
    }

    @Override
    public boolean use(Mob target) {
        for (int i = 0; i < bonusTarget; i++){
//            adding:
            for (Mob m: Wave.mobs){
                if(owner.isInRange(m.getCenter())){
                    if(m != target){
                        if(!targets.contains(m, false)) {
                            targets.add(m);
                            break; //adding;
                        }
                    }
                }
            }
        }
        shot();
        dispose();
        return true;
    }

    private void shot(){
        for (Mob m: targets){
            Projectile p = new Projectile(owner, owner.getCenter(), m, false);
            p.setDmgMultiplayer(0.8f);
            Map.projectiles.add(p);
        }
    }
    private void dispose(){
        targets.clear();
    }

    @Override
    protected void init() {

    }


}
