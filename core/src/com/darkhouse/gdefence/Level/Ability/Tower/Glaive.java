package com.darkhouse.gdefence.Level.Ability.Tower;



import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Model.Level.Map;

public class Glaive extends Ability implements Ability.IOnHit{

    public static class P extends Ability.AblityPrototype{
        private int bounces;
        private float resDmgFromEachBounce;
        private int maxRange;

        public P(int bounces, float resDmgFromEachBounce, int maxRange) {
            super("MultiShot");
            this.bounces = bounces;
            this.resDmgFromEachBounce = resDmgFromEachBounce;
            this.maxRange = maxRange;
        }

        @Override
        public Ability getAbility() {
            return new Glaive(this);
        }

        @Override
        public String getTooltip() {
            return "After dealing dmg shot bonus attack to nearby mob " + bounces + " times" + System.getProperty("line.separator") +
                    "Each bounce deal " + resDmgFromEachBounce*100 + "% less damage" + System.getProperty("line.separator") +
                    "Bounces work only in " + maxRange + " range";
        }
    }
    private int bounces;
    private float resDmgFromEachBounce;
    private int maxRange;

    public Glaive(P prototype) {
        this.bounces = prototype.bounces;
        this.resDmgFromEachBounce = prototype.resDmgFromEachBounce;
        this.maxRange = prototype.maxRange;
    }

    @Override
    public int getDmg(Mob target, int startDmg) {

        Mob newTarget = Map.getNearestMob(target.getCenter(), maxRange);


        if(newTarget != null) {
            Map.projectiles.add(new Projectile(owner, target.getCenter(), newTarget));

        }



        return startDmg;
    }

    @Override
    protected void init() {

    }


}
