package com.darkhouse.gdefence.Level.Ability.Tower;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.Chance;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.User;

import java.util.concurrent.atomic.AtomicReference;

public class Crit extends Ability implements Ability.IOnHit{



    public static class P extends AbilityPrototype {
        private AtomicReference<Float> chance;
        private AtomicReference<Float> multiplayer;
        private G g;
        private S s;

        public P(float chance, float multiplayer, G grader) {
            super(2, "crit", grader.gemCap, IOnHit.class);
            this.chance = new AtomicReference<Float>(chance);
            this.multiplayer = new AtomicReference<Float>(multiplayer);
            this.g = grader;
            this.s = new S(chance, multiplayer);
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
            P p = new P(chance.get(), multiplayer.get(), g);
            p.initBoosts(l);
            p.s = s;
            return p;
        }
        @Override
        public void flush() {
            this.chance = new AtomicReference<Float>(s.chance);
            this.multiplayer = new AtomicReference<Float>(s.multiplayer);
            initBoosts(GDefence.getInstance().assetLoader);
        }

        @Override
        protected void initBoosts(AssetLoader l) {
            gemBoost[0] = new BoostFloat(chance, g.chanceUp, l.getWord("critGrade1"),
                    true, BoostFloat.FloatGradeFieldType.PERCENT);
            gemBoost[1] = new BoostFloat(multiplayer, g.multiplayerUp, l.getWord("critGrade2"),
                    true, BoostFloat.FloatGradeFieldType.MULTIPLAYER);
        }

        @Override
        public Ability getAbility() {
            return new Crit(this);
        }
        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("critTooltip1") + " " + FontLoader.colorString(chance.get()*100 + "", User.GEM_TYPE.BLACK) + " " + l.getWord("critTooltip2") + System.getProperty("line.separator")
                    + l.getWord("critTooltip3") + " " + FontLoader.colorString(multiplayer.get() + "x", User.GEM_TYPE.GREEN);
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
    private static class S extends AbilitySaverStat{
        private float chance;
        private float multiplayer;

        public S(float chance, float multiplayer) {
            this.chance = chance;
            this.multiplayer = multiplayer;
        }
    }
    private float chance;
    private float multiplayer;

    public Crit(P prototype) {
        this.chance = prototype.chance.get();
        this.multiplayer = prototype.multiplayer.get();
    }

    @Override
    public int getDmg(Mob target, int startDmg) {
        if(Chance.proc(chance)) {
            //GraphicManager.applyAnimation("Crit");
            return (int) (startDmg * multiplayer);
        }
        else return startDmg;
    }

    @Override
    protected void init(Map map) {

    }
}
