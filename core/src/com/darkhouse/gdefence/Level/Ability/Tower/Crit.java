package com.darkhouse.gdefence.Level.Ability.Tower;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
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
            super(2, "crit", grader.gemCap);
            this.chance = new AtomicReference<Float>(chance);
            this.multiplayer = new AtomicReference<Float>(multiplayer);
            this.grader = grader;
        }

        @Override
        public Array<Class<? extends AbilityPrototype>> getAbilitiesToSaveOnCraft() {
            return new Array<Class<? extends AbilityPrototype>>();
        }

//        @Override
//        public String getSaveCode() {
//            return null;
//        }

        @Override
        public AbilityPrototype copy() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            P p = new P(chance.get(), multiplayer.get(), grader);
            p.gemBoost[0] = new BoostFloat(p.chance, grader.chanceUp, l.getWord("critGrade1"),
                    true, BoostFloat.FloatGradeFieldType.PERCENT);
            p.gemBoost[1] = new BoostFloat(p.multiplayer, grader.multiplayerUp, l.getWord("critGrade2"),
                    true, BoostFloat.FloatGradeFieldType.MULTIPLAYER);
            return p;
        }

        @Override
        public Ability getAbility() {
            return new Crit(this);
        }
        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("critTooltip1") + " " + FontLoader.colorCode(0) + chance.get()*100 + " % []" + l.getWord("critTooltip2") + System.getProperty("line.separator")
                    + l.getWord("critTooltip3") + " " + FontLoader.colorCode(1) + multiplayer.get().intValue() + "x []";
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
