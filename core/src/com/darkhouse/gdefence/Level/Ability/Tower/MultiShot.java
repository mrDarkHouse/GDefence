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
        private AtomicReference<Integer> bonusTarget;
        private AtomicReference<Float> dmgPercent;//not work yet
        private G g;
        private S s;

        public P(int bonusTarget, float dmgPercent, G grader) {
            super(6, "multiShot", grader.gemCap, IPreShot.class);
            this.bonusTarget = new AtomicReference<Integer>(bonusTarget);
            this.dmgPercent = new AtomicReference<Float>(dmgPercent);
            this.g = grader;
            this.s = new S(bonusTarget, dmgPercent);
        }

        @Override
        public Array<Class<? extends AbilityPrototype>> getAbilitiesToSaveOnCraft() {
            Array<Class<? extends AbilityPrototype>> a = new Array<Class<? extends AbilityPrototype>>();
//            a.add(BuckShot.P.class);
            return a;
        }

        @Override
        public AbilityPrototype copy() {
            P p = new P(bonusTarget.get(), dmgPercent.get(), g);
            p.initBoosts(GDefence.getInstance().assetLoader);
            p.s = s;
            return p;
        }

        @Override
        public void flush() {
            bonusTarget = new AtomicReference<Integer>(s.bonusTarget);
            dmgPercent = new AtomicReference<Float>(s.dmgPercent);
            initBoosts(GDefence.getInstance().assetLoader);
        }

        @Override
        protected void initBoosts(AssetLoader l) {
            gemBoost[0] = new BoostFloat(dmgPercent, g.dmgPercentUp, l.getWord("multiShotGrade2"),
                    true, BoostFloat.FloatGradeFieldType.PERCENT);
            gemBoost[2] = new BoostInteger(bonusTarget, g.bonusTargetUp, l.getWord("multiShotGrade1"),
                    true, BoostInteger.IntegerGradeFieldType.NONE);
        }

        @Override
        public Ability getAbility() {
            return new MultiShot(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("multiShotTooltip1") + " " + FontLoader.colorString(bonusTarget.get().toString(), User.GEM_TYPE.WHITE) + " " +
                    l.getWord("multiShotTooltip2") + System.getProperty("line.separator") +
                    l.getWord("multiShotTooltip3") + " " + FontLoader.colorString(dmgPercent.get()*100 + "%", User.GEM_TYPE.BLACK) + " " +
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
    private static class S extends AbilitySaverStat{
        private int bonusTarget;
        private float dmgPercent;

        public S(int bonusTarget, float dmgPercent) {
            this.bonusTarget = bonusTarget;
            this.dmgPercent = dmgPercent;
        }
    }

    private int bonusTarget;
    private float dmgPercent;
    private Array <Mob> targets;

    public MultiShot(P prototype) {
        this.bonusTarget = prototype.bonusTarget.get();
        this.dmgPercent = prototype.bonusTarget.get();
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
            p.setDmgMultiplayer(dmgPercent);//0.8 //TODO why i say dont work
            Map.projectiles.add(p);
        }
    }
    private void dispose(){
        targets.clear();
    }

    @Override
    protected void init(Map map) {

    }


}
