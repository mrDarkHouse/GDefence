package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Ability.Tools.Chance;
import com.darkhouse.gdefence.User;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicReference;

public class Crit extends Ability {

    public static class P extends AbilityPrototype {
        private G grader;
        private AtomicReference<Float> chance;
        private AtomicReference<Float> multiplayer;

        public P(float chance, float multiplayer, G grader) {
            super("Crit", "crit", grader.gemCap);
            this.chance = new AtomicReference<Float>(chance);
            this.multiplayer = new AtomicReference<Float>(multiplayer);
            this.grader = grader;
        }
        @Override
        public AbilityPrototype copy() {
            P p = new P(chance.get(), multiplayer.get(), grader);
            p.gemBoost[0] = new BoostFloat(p.chance, grader.chanceUp, "crit chance", true, BoostFloat.FloatGradeFieldType.PERCENT);
            p.gemBoost[1] = new BoostFloat(p.multiplayer, grader.multiplayerUp, "crit multiplayer", true, BoostFloat.FloatGradeFieldType.MULTIPLAYER);
            return p;
        }

        @Override
        public Ability getAbility() {
            return new Crit(this);
        }
        @Override
        public String getTooltip() {
            return "Have [#000000ff]" + chance.get()*100 + " % []chance " + System.getProperty("line.separator")
                    + "to increase dmg by for[#0ffe00ff]" + multiplayer.get().intValue() + "x []";
        }
    }
    public static class G extends AbilityGrader {
        protected float chanceUp;
        protected float multiplayerUp;

        public G(float chanceUp, float multiplayerUp, int[] gemCap) {
            super(gemCap);
            this.chanceUp = chanceUp;
            this.multiplayerUp = multiplayerUp;
        }
    }

    private float chance;
    private float multiplayer;

    public Crit(P prototype) {
        this.chance = prototype.chance.get();
        this.multiplayer = prototype.multiplayer.get();
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
