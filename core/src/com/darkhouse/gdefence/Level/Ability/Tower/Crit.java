package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Ability.Tools.Chance;

public class Crit extends Ability {

    public static class P extends Ability.AblityPrototype{
        private float chance;
        private float multiplayer;

        public P(float chance, float multipalyer) {
            super("Crit");
            this.chance = chance;
            this.multiplayer = multipalyer;
        }

        @Override
        public Ability getAbility() {
            return new Crit(this);
        }

        @Override
        public String getTooltip() {
            return "Have " + chance*100 + " % chance to increase dmg by " + multiplayer;
        }
    }

    private float chance;
    private float multiplayer;

    public Crit(P prototype) {
        this.chance = prototype.chance;
        this.multiplayer = prototype.multiplayer;
    }

    public int getDmg(int dmgStart){
        if(Chance.proc(chance)) {
            //GraphicManager.applyAnimation("Crit");
            return (int) (dmgStart * multiplayer);
        }
        else return dmgStart;
    }

    @Override
    protected void init() {

    }
}
