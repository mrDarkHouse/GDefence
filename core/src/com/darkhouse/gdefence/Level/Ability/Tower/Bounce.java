package com.darkhouse.gdefence.Level.Ability.Tower;



import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.User;

import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicReference;

public class Bounce extends Ability implements Ability.IAfterHit{

    public static class P extends AbilityPrototype {
        private G grader;
        private AtomicReference<Integer> bounces;
        private AtomicReference<Float> resDmgFromEachBounce;
        private AtomicReference<Integer> maxRange;

        public P(int bounces, float resDmgFromEachBounce, int maxRange, G grader) {
            super(7, "bounce", grader.gemCap);
            this.bounces = new AtomicReference<Integer>(bounces);
            this.resDmgFromEachBounce = new AtomicReference<Float>(resDmgFromEachBounce);
            this.maxRange = new AtomicReference<Integer>(maxRange);
            this.grader = grader;
        }

//        @Override
//        public String getSaveCode() {
//            return null;
//        }

        @Override
        public AbilityPrototype copy() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            P p = new P(bounces.get(), resDmgFromEachBounce.get(), maxRange.get(), grader);
            p.gemBoost[0] = new BoostInteger(p.bounces, grader.bouncesUp, l.getWord("bounceGrade1"),
                    true, BoostInteger.IntegerGradeFieldType.NONE);
            p.gemBoost[1] = new BoostFloat(p.resDmgFromEachBounce, grader.resDmgFromEachBounceDown, l.getWord("bounceGrade2"),
                    false, BoostFloat.FloatGradeFieldType.PERCENT);
            p.gemBoost[2] = new BoostInteger(p.maxRange, grader.maxRangeUp, l.getWord("bounceGrade3"),
                    true, BoostInteger.IntegerGradeFieldType.NONE);
            return p;
        }

        @Override
        public Ability getAbility() {
            return new Bounce(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("bounceTooltip1") + " " + FontLoader.colorCode(0) + bounces.get() + "[] " + l.getWord("bounceTooltip2") + System.getProperty("line.separator") +
                   l.getWord("bounceTooltip3") + " " + FontLoader.colorCode(1) + resDmgFromEachBounce.get()*100 + "%[] " + l.getWord("bounceTooltip4") + System.getProperty("line.separator") +
                   l.getWord("bounceTooltip5") + " " + FontLoader.colorCode(2) + "[] " + l.getWord("bounceTooltip6");
        }



    }
    public static class G extends AbilityGrader{
        private int bouncesUp;
        private float resDmgFromEachBounceDown;
        private int maxRangeUp;

        public G(int bouncesUp, float resDmgFromEachBounceDown, int maxRangeUp, int[] gemCap) {
            super(gemCap);
            this.bouncesUp = bouncesUp;
            this.resDmgFromEachBounceDown = resDmgFromEachBounceDown;
            this.maxRangeUp = maxRangeUp;
        }
    }

    private int bounces;
    private float resDmgFromEachBounce;
    private int maxRange;

    public Bounce(P prototype) {
        this.bounces = prototype.bounces.get();
        this.resDmgFromEachBounce = prototype.resDmgFromEachBounce.get();
        this.maxRange = prototype.maxRange.get();
    }

    public Bounce(int bounces, float resDmgFromEachBounce, int maxRange){
        this.bounces = bounces;
        this.resDmgFromEachBounce = resDmgFromEachBounce;
        this.maxRange = maxRange;
    }

    @Override
    public void hit(Mob target, int dmg, Projectile hittingProjectile) {
        if(bounces == 0) return;

        Mob newTarget = Map.getNearestMob(target, maxRange);

        if(newTarget != null) {
            Projectile p = new Projectile(owner, target.getCenter(), newTarget, false);
            p.setDmgMultiplayer(1 - resDmgFromEachBounce);
            Bounce g = new Bounce(bounces - 1, 1 - ((float) Math.pow(1 - resDmgFromEachBounce, 2)), maxRange);//
            g.setOwner(owner);
            p.addAbilities(g);
            Map.projectiles.add(p);
        }
    }

    @Override
    protected void init() {

    }


}
