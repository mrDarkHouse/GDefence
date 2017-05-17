package com.darkhouse.gdefence.Level.Ability.Tower;


import com.badlogic.gdx.math.Vector2;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Model.Level.Map;

public class BuckShot extends Ability implements Ability.IPreShot {

    public static class P extends Ability.AblityPrototype{
        private int maxTargets;
//        private int range;
        private int projectiles;
        private float angle;

        public P(int projectiles, float angle, int maxTargets/*, int range*/) {
            super("Steel Arrow");
            this.projectiles = projectiles;
            this. angle = angle;
            this.maxTargets = maxTargets;
//            this.range = range;
        }

        @Override
        public Ability getAbility() {
            return new BuckShot(this);
        }

        @Override
        public String getTooltip() {
            return "After hitting projectile continue fly, hitting " + /*can do changeable */100 + "% dmg from base damage to each target " + System.getProperty("line.separator") +
                    "After hitting max targets projectile disappear" + System.getProperty("line.separator") +
                    "Max targets " + maxTargets;
        }
    }

    private int maxTargets;
//    private int range;
    private int projectiles;
    private float angle;


    public BuckShot(P prototype) {
        this.projectiles = prototype.projectiles;
        this.angle = prototype.angle;
        this.maxTargets = prototype.maxTargets;
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
    protected void init() {

    }
}
