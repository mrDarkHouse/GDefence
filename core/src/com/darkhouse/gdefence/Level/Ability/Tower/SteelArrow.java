package com.darkhouse.gdefence.Level.Ability.Tower;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Level.Wave;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.User;

import java.util.concurrent.atomic.AtomicReference;

public class SteelArrow extends Ability implements Ability.IAfterHit{

    public static class P extends AbilityPrototype {
        private AtomicReference<Integer> maxTargets;
        private AtomicReference<Integer> range;
        private G g;
        private S s;

        public P(int maxTargets, int range, G grader) {
            super(12, "steelArrow", grader.gemCap, IAfterHit.class);
            this.maxTargets = new AtomicReference<Integer>(maxTargets);
            this.range = new AtomicReference<Integer>(range);
            this.g = grader;
            this.s = new S(maxTargets, range);
        }

        @Override
        public Array<Class<? extends AbilityPrototype>> getAbilitiesToSaveOnCraft() {
            Array<Class<? extends AbilityPrototype>> a = new Array<Class<? extends AbilityPrototype>>();
            a.add(Bounce.P.class);
            return a;
        }

        @Override
        public AbilityPrototype copy() {
            P p = new P(maxTargets.get(), range.get(), g);
            p.initBoosts(GDefence.getInstance().assetLoader);
            p.s = s;
            return p;
        }

        @Override
        public void flush() {
            maxTargets = new AtomicReference<Integer>(s.maxTargets);
            range = new AtomicReference<Integer>(s.range);
            initBoosts(GDefence.getInstance().assetLoader);
        }

        @Override
        protected void initBoosts(AssetLoader l) {
            gemBoost[0] = new BoostInteger(maxTargets, g.maxTargetsUp, l.getWord("steelArrowGrade1"),
                    true, BoostInteger.IntegerGradeFieldType.NONE);
            gemBoost[1] = new BoostInteger(range, g.rangeUp, l.getWord("steelArrowGrade2"),
                    true, BoostInteger.IntegerGradeFieldType.NONE);
        }

        @Override
        public Ability getAbility() {
            return new SteelArrow(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("steelArrowTooltip1") + " " + System.getProperty("line.separator") +
                    l.getWord("steelArrowTooltip2") + " " + /*can do changeable */100 + "%" + " " +
                    l.getWord("steelArrowTooltip3") + System.getProperty("line.separator") +
                    l.getWord("steelArrowTooltip4") + " " + FontLoader.colorString(maxTargets.get().toString(), User.GEM_TYPE.BLACK) + " " +
                    l.getWord("steelArrowTooltip5") + System.getProperty("line.separator") +
                    l.getWord("steelArrowTooltip6") + " " + FontLoader.colorString(range.get().toString(), User.GEM_TYPE.GREEN);
        }
    }
    public static class G extends AbilityGrader{
        private int maxTargetsUp;
        private int rangeUp;

        public G(int maxTargetsUp, int rangeUp, int[] gemCap) {
            super(gemCap);
            this.maxTargetsUp = maxTargetsUp;
            this.rangeUp = rangeUp;
        }
    }
    private static class S extends AbilitySaverStat{
        private int maxTargets;
        private int range;

        public S(int maxTargets, int range) {
            this.maxTargets = maxTargets;
            this.range = range;
        }
    }

    public static class SteelProjectile extends Projectile{
        private Vector2 endLocation;

        private Array<Mob> hittedMobs;

        public SteelProjectile(Tower tower, Vector2 startLocation, Vector2 endLocation, boolean isMain) {
            super(tower, startLocation, isMain);
            this.endLocation = endLocation;
            hittedMobs = new Array<Mob>();
        }

        @Override
        protected void move(float delta) {
            position.set(getX(), getY());
            targetV.set(endLocation.x, endLocation.y);
            dir.set(targetV).sub(position).nor();
            velocity.set(dir).scl(speed);
            movement.set(velocity).scl(delta);
            if(position.dst2(targetV) > movement.len2()){
                position.add(movement);
            }else {
                position.set(endLocation);
                Map.projectiles.remove(this);//in maxRange
            }
//            if(getX() == endLocation.x && getY() == endLocation.y){
//                Map.projectiles.remove(this);
//            }

            for (Mob m: Wave.mobs){
                if(/*getX() == m.getX() && getY() == m.getY()*/m.contains(this.getCenter())){
//                    System.out.println("hitted");
                    if(!hittedMobs.contains(m, true)) {
                        tower.hitTarget(m, ((int) (tower.getDmg(m, isMainProjectile) * dmgMultiplayer)));
                        hittedMobs.add(m);
                        afterHit();
                    }
                    for (Mob hitMob:hittedMobs){
                        if(!hitMob.contains(this.getCenter())) hittedMobs.removeValue(hitMob, true);
                    }
                }
            }

            setRotation(dir.angle());
            setX(position.x);
            setY(position.y);

        }
    }
    public static class targetChecher extends Ability implements IAfterHit{
        private Projectile owner;
        private int maxTargets;
        private int currentTargets;

        public targetChecher(Projectile owner, int maxTargets) {
            this.owner = owner;
            this.maxTargets = maxTargets;
        }

        @Override
        protected void init(Map map) {

        }

        @Override
        public void hit(Mob target, int dmg, Projectile hittingProjectile) {
            currentTargets++;
            if(currentTargets >= maxTargets){
                Map.projectiles.remove(owner);
            }
        }
    }


    private int maxTargets;
    private int range;
//    private float dmg; //i think it not need

    public SteelArrow(P prototype) {
        this.maxTargets = prototype.maxTargets.get();
        this.range = prototype.range.get();
//        this.dmg = prototype.dmg;
    }

    @Override
    protected void init(Map map) {

    }

    @Override
    public void hit(Mob target, int dmg, Projectile hittingProjectile) {
        Vector2 endPosition = new Vector2();
        endPosition.x = target.getCenter().x;
        endPosition.y = target.getCenter().y;

//        System.out.println("started " + endPosition.x + " " + endPosition.y);


        float angle = hittingProjectile.getRotation();

//        Vector2 added = new Vector2();
//        added.setLength(range);
//        added.setAngle(hittingProjectile.getRotation());
        endPosition.y += range*Math.sin(Math.toRadians(angle));
        endPosition.x += range*Math.cos(Math.toRadians(angle));


//        System.out.println("ended " + endPosition.x + " " + endPosition.y);

//        endPosition.x += range/8;
//        endPosition.y += range/8;
        ////

        Vector2 startPosition = target.getCenter();
//        startPosition.rotate(hittingProjectile.getRotation());

//        throwingProj.setRotation(hittingProjectile.getRotation());

        SteelProjectile throwingProj = new SteelProjectile(owner, startPosition, endPosition, false);
        throwingProj.addAbilities(new targetChecher(throwingProj, maxTargets));
        Map.projectiles.add(throwingProj);


    }
}
