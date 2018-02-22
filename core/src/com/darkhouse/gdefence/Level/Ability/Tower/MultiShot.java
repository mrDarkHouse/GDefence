package com.darkhouse.gdefence.Level.Ability.Tower;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
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
            super(6, "multiShot", grader.gemCap);
            this.bonusTarget = new AtomicReference<Integer>(bonusTarget);
            this.dmgPercent = new AtomicReference<Float>(dmgPercent);
            this.grader = grader;
        }

        @Override
        public Array<Class<? extends AbilityPrototype>> getAbilitiesToSaveOnCraft() {
            return new Array<Class<? extends AbilityPrototype>>();
        }

//        @Override
//        public String getSaveCode() {
//            return null;
//        }

        @Override
        public AbilityPrototype copy() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            P p = new P(bonusTarget.get(), dmgPercent.get(), grader);
            p.gemBoost[0] = new BoostInteger(p.bonusTarget, grader.bonusTargetUp, l.getWord("multiShotGrade1"),
                    true, BoostInteger.IntegerGradeFieldType.NONE);
            p.gemBoost[1] = new BoostFloat(p.dmgPercent, grader.dmgPercentUp, l.getWord("multiShotGrade2"),
                    true, BoostFloat.FloatGradeFieldType.PERCENT);
            return p;
        }

        @Override
        public Ability getAbility() {
            return new MultiShot(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("multiShotTooltip1") + " " + FontLoader.colorString(bonusTarget.get().toString(), 0) + " " +
                    l.getWord("multiShotTooltip2") + System.getProperty("line.separator") +
                    l.getWord("multiShotTooltip3") + " " + FontLoader.colorString(dmgPercent.get()*100 + "%", 1) + " " +
                    l.getWord("multiShotTooltip4");
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
