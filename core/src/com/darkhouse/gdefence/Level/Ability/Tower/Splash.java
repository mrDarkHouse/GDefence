package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Wave;
import com.darkhouse.gdefence.Model.Level.Map;

public class Splash extends Ability implements Ability.IOnHit{
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
    public int getDmg(Mob target, int startDmg) {
        for (Mob m: Wave.mobs){
//            if(Map)
        }

        return 0;
    }

    @Override
    protected void init() {

    }
}
