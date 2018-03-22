package com.darkhouse.gdefence.Level.Ability.Tower;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.User;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

public class BuckShot extends Ability implements Ability.IPreShot {

    public static class P extends AbilityPrototype {
//        private int range;
        private AtomicReference<Integer> projectiles;
        private AtomicReference<Float> angle;
        private AtomicReference<Integer> maxTargets;//
        private G g;
        private S s;

        @Override
        public Array<Class<? extends AbilityPrototype>> getAbilitiesToSaveOnCraft() {
            Array<Class<? extends AbilityPrototype>> a = new Array<Class<? extends AbilityPrototype>>();
            a.add(BuckShot.P.class);
            return a;
        }

        public P(int projectiles, float angle/*, int maxTargets*//*, int range*/, G grader) {
            super(1, "buckShot", grader.gemCap, IPreShot.class);
            this.projectiles = new AtomicReference<Integer>(projectiles);
            this.angle = new AtomicReference<Float>(angle);
            this.maxTargets = new AtomicReference<Integer>(1);
            this.g = grader;
            this.s = new S(projectiles, angle);
//            this.range = range;
        }

        @Override
        public AbilityPrototype copy() {
            P p = new P(projectiles.get(), angle.get(), g);
            p.initBoosts(GDefence.getInstance().assetLoader);
            p.s = s;
            return p;
        }
        @Override
        public void flush() {
            this.projectiles = new AtomicReference<Integer>(s.projectiles);
            this.angle = new AtomicReference<Float>(s.angle);
            initBoosts(GDefence.getInstance().assetLoader);
        }

        @Override
        protected void initBoosts(AssetLoader l) {
            gemBoost[1] = new BoostFloat(angle, g.angleDown, l.getWord("buckShotGrade2"),
                    false, BoostFloat.FloatGradeFieldType.ANGLE);
            gemBoost[2] = new BoostInteger(projectiles, g.projectilesUp, l.getWord("buckShotGrade1"),
                    true, BoostInteger.IntegerGradeFieldType.NONE);
        }

        @Override
        public Ability getAbility() {
            return new BuckShot(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("buckShotTooltip1") + " " + FontLoader.colorString(projectiles.get().toString(), User.GEM_TYPE.WHITE) + " " + l.getWord("buckShotTooltip2") + System.getProperty("line.separator") +
                    l.getWord("buckShotTooltip3") + " " + FontLoader.colorString(angle.get().toString(), User.GEM_TYPE.GREEN) + " " + l.getWord("buckShotTooltip4");
        }
    }
    public static class G extends AbilityGrader{
        private int projectilesUp;
        private float angleDown;
//        private int maxTargetsUp;

        public G(int projectilesUp, float angleDown/*, int maxTargetsUp*/, int[] gemMax) {
            super(gemMax);
            this.projectilesUp = projectilesUp;
            this.angleDown = angleDown;
//            this.maxTargetsUp = maxTargetsUp;
        }
    }
    private static class S extends AbilitySaverStat{
        private int projectiles;
        private float angle;

        public S(int projectiles, float angle) {
            this.projectiles = projectiles;
            this.angle = angle;
        }
    }

    private int maxTargets;
//    private int range;
    private int projectiles;
    private float angle;


    public BuckShot(P prototype) {
        this.projectiles = prototype.projectiles.get();
        this.angle = prototype.angle.get();
        this.maxTargets = prototype.maxTargets.get();
//        this.range = prototype.range;
    }

    @Override
    public boolean use(Mob target) {
        double startDegree = Math.toDegrees(Math.atan2(target.getY() - owner.getY(), target.getX() - owner.getX()));

        if(projectiles%2 == 0) startDegree -= angle*(projectiles/2 - 1) + angle/2;
        else startDegree -= angle*((projectiles - 1)/2);

        for (int i = 0; i < projectiles; i++) {
            Vector2 endPosition = new Vector2();
            endPosition.x = owner.getCenter().x;
            endPosition.y = owner.getCenter().y;
            endPosition.y += owner.getTowerPrototype().getRange()*Math.sin(Math.toRadians(startDegree + angle*i));//
            endPosition.x += owner.getTowerPrototype().getRange()*Math.cos(Math.toRadians(startDegree + angle*i));

            SteelArrow.SteelProjectile throwingProj = new SteelArrow.SteelProjectile(owner, owner.getCenter(), endPosition, false);
            throwingProj.addAbilities(new SteelArrow.targetChecher(throwingProj, maxTargets));
            Map.projectiles.add(throwingProj);

//            startDegree += angle;
        }

        return false;//disable main target projectile
    }

    @Override
    protected void init(Map map) {

    }
}
