package com.darkhouse.gdefence.Level.Ability.Tower;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Level.Wave;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.User;

import java.util.concurrent.atomic.AtomicReference;

public class SteelArrow extends Ability implements Ability.IAfterHit{

    public static class P extends AbilityPrototype {
        private G grader;
        private AtomicReference<Integer> maxTargets;
        private AtomicReference<Integer> range;

        public P(int maxTargets, int range, G grader) {
            super(12, "Steel Arrow", "steelArrow", grader.gemCap);
            this.maxTargets = new AtomicReference<Integer>(maxTargets);
            this.range = new AtomicReference<Integer>(range);
            this.grader = grader;
        }

        @Override
        public String getSaveCode() {
            return null;
        }

        @Override
        public AbilityPrototype copy() {
            P p = new P(maxTargets.get(), range.get(), grader);
            p.gemBoost[0] = new BoostInteger(p.maxTargets, grader.maxTargetsUp, "max targets",
                    true, BoostInteger.IntegerGradeFieldType.NONE);
            p.gemBoost[1] = new BoostInteger(p.range, grader.rangeUp, "fly range",
                    true, BoostInteger.IntegerGradeFieldType.NONE);
            return p;
        }

        @Override
        public Ability getAbility() {
            return new SteelArrow(this);
        }

        @Override
        public String getTooltip() {
            return "After hitting projectile continue fly, " + System.getProperty("line.separator") +
                    "hitting " + /*can do changeable */100 + "% dmg from base damage to each target "
                    + System.getProperty("line.separator") + "After hitting max targets projectile disappear"
                    + System.getProperty("line.separator") + "Fly range [#0ffe00ff]" + range + "[]"
                    + System.getProperty("line.separator") + "Max targets [#000000ff]" + maxTargets + "[]";
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
        protected void init() {

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
    protected void init() {

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
