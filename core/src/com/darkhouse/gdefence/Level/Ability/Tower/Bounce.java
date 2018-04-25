package com.darkhouse.gdefence.Level.Ability.Tower;



import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicReference;

public class Bounce extends Ability implements Ability.IAfterHit{

    public static class P extends AbilityPrototype {
        private AtomicReference<Integer> bounces;
        private AtomicReference<Float> resDmgFromEachBounce;
        private AtomicReference<Integer> maxRange;
        private G g;
        private S s;

        public P(int bounces, float resDmgFromEachBounce, int maxRange, G grader) {
            super(7, "bounce", grader.gemCap, IAfterHit.class);
            this.bounces = new AtomicReference<Integer>(bounces);
            this.resDmgFromEachBounce = new AtomicReference<Float>(resDmgFromEachBounce);
            this.maxRange = new AtomicReference<Integer>(maxRange);
            this.g = grader;
            this.s = new S(bounces, resDmgFromEachBounce, maxRange);
        }

        @Override
        public Array<Class<? extends AbilityPrototype>> getAbilitiesToSaveOnCraft() {
            Array<Class<? extends AbilityPrototype>> a = new Array<Class<? extends AbilityPrototype>>();
            a.add(MultiShot.P.class);
            return a;
        }

        @Override
        public AbilityPrototype copy() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            P p = new P(bounces.get(), resDmgFromEachBounce.get(), maxRange.get(), g);
            p.gemBoost[2] = new BoostInteger(p.bounces, g.bouncesUp, l.getWord("bounceGrade1"),
                    true, BoostInteger.IntegerGradeFieldType.NONE);
            p.gemBoost[0] = new BoostFloat(p.resDmgFromEachBounce, g.resDmgFromEachBounceDown, l.getWord("bounceGrade2"),
                    false, BoostFloat.FloatGradeFieldType.PERCENT);
            p.gemBoost[1] = new BoostInteger(p.maxRange, g.maxRangeUp, l.getWord("bounceGrade3"),
                    true, BoostInteger.IntegerGradeFieldType.NONE);
            p.s = s;
            return p;
        }
        @Override
        public void flush() {
            this.bounces = new AtomicReference<Integer>(s.bounces);
            this.resDmgFromEachBounce = new AtomicReference<Float>(s.resDmgFromEachBounce);
            this.maxRange = new AtomicReference<Integer>(s.maxRange);
        }


        @Override
        public Ability getAbility() {
            return new Bounce(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("bounceTooltip1") + " " + FontLoader.colorString(bounces.get().toString(), User.GEM_TYPE.WHITE) + " "+ l.getWord("bounceTooltip2")
                    + System.getProperty("line.separator") +
                   l.getWord("bounceTooltip3") + " " + FontLoader.colorString(new BigDecimal(resDmgFromEachBounce.get()*100).setScale(2, RoundingMode.HALF_UP).floatValue() + "%", User.GEM_TYPE.GREEN) + " " + l.getWord("bounceTooltip4")
                    + System.getProperty("line.separator") +
                   l.getWord("bounceTooltip5") + " " + FontLoader.colorString(maxRange.get().toString(), User.GEM_TYPE.BLACK) + " " + l.getWord("bounceTooltip6");
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
    private static class S extends AbilitySaverStat{
        private int bounces;
        private float resDmgFromEachBounce;
        private int maxRange;

        public S(int bounces, float resDmgFromEachBounce, int maxRange) {
            this.bounces = bounces;
            this.resDmgFromEachBounce = resDmgFromEachBounce;
            this.maxRange = maxRange;
        }
    }

    private int bounces;
    private float resDmgFromEachBounce;
    private int maxRange;

    private Map map;

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
            g.setOwner(owner, map);
            p.addAbilities(g);
            Map.projectiles.add(p);
        }
    }

    @Override
    protected void init(Map map) {
        this.map = map;
    }


}
