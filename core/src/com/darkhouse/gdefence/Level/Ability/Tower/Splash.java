package com.darkhouse.gdefence.Level.Ability.Tower;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Level.Wave;
import com.darkhouse.gdefence.Model.Level.Map;

public class Splash extends Ability implements Ability.IAfterHit{


    public static class P extends Ability.AblityPrototype{
        private float aoeDmg;
        private int aoe;

        public P(int aoe, float aoeDmg) {
            super("Splash");
            this.aoe = aoe;
            this.aoeDmg = aoeDmg;
        }

        @Override
        public Ability getAbility() {
            return new Splash(this);
        }

        @Override
        public String getTooltip() {
            return "Dealing " + aoeDmg*100 + " dmg in " + aoe + " range";
        }
    }

    private float aoeDmg;
    private int aoe;

    public Splash(P prototype) {
        this.aoe = prototype.aoe;
        this.aoeDmg = prototype.aoeDmg;
    }

    @Override
    public void hit(Mob target, int dmg, Projectile hittingProjectile) {
//        for (Mob m: Wave.mobs){
//
//        }
        for (Mob m:Map.getMobsInRange(target, aoe)){
            owner.hitTarget(m, (int) (dmg*aoeDmg));
        }
    }

    @Override
    protected void init() {

    }
}
