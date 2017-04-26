package com.darkhouse.gdefence.Level.Ability.Tower;



import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Model.Level.Map;

public class Glaive extends Ability implements Ability.IAfterHit{

    public static class P extends Ability.AblityPrototype{
        private int bounces;
        private float resDmgFromEachBounce;
        private int maxRange;

        public P(int bounces, float resDmgFromEachBounce, int maxRange) {
            super("Glaive");
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

    public Glaive(int bounces, float resDmgFromEachBounce, int maxRange){
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
            Glaive g = new Glaive(bounces - 1, 1 - ((float) Math.pow(1 - resDmgFromEachBounce, 2)), maxRange);//
            g.setOwner(owner);
            p.addAbilities(g);
            Map.projectiles.add(p);
        }
    }

    @Override
    protected void init() {

    }


}
