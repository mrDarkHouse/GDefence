package com.darkhouse.gdefence.Level.Ability.Tower;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.DamageType;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Tools.Chance;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Bash extends Ability implements Ability.IOnHit{

    private class BashEffect extends Effect<Mob>{
//        private float startSpeed;

        public BashEffect(float duration) {
            super(false, true, duration, "bash");
        }

        @Override
        public void apply() {
//            startSpeed = owner.getSpeed();//bad idea
//            owner.changeSpeed(0);
            owner.setState(Mob.State.stunned);
        }

        @Override
        public void dispell() {
//            owner.changeSpeed(startSpeed);
            if(owner.getState() == Mob.State.stunned) owner.setState(Mob.State.normal); //dont dispell other effects, but dispell stuns
            super.dispell();
        }
    }
    public static class P extends AbilityPrototype {
        private G g;
        private S s;
        private AtomicReference<Float> chance;
        private AtomicReference<Float> duration;
        private AtomicReference<Integer> bonusDmg;

        public P(float chance, float duration, int bonusDmg, G grader) {
            super(0, "bash", grader.gemCap, IOnHit.class);
            this.chance = new AtomicReference<Float>(chance);
            this.duration = new AtomicReference<Float>(duration);
            this.bonusDmg = new AtomicReference<Integer>(bonusDmg);
            this.g = grader;
            s = new S(chance, duration, bonusDmg);
        }


//        USE IT FOR NEW ABILITY PROTOTYPE
        public P copy(){
            AssetLoader l = GDefence.getInstance().assetLoader;
            P p = new P(chance.get(), duration.get(), bonusDmg.get(), g);
            p.gemBoost[0] = new BoostInteger(p.bonusDmg, g.bonusDmgUp, l.getWord("bashGrade3"),
                    true, BoostInteger.IntegerGradeFieldType.NONE);
            p.gemBoost[1] = new BoostFloat(p.duration,   g.durationUp, l.getWord("bashGrade2"),
                    true, BoostFloat.FloatGradeFieldType.TIME);
            p.gemBoost[2] = new BoostFloat(p.chance,     g.chanceUp,   l.getWord("bashGrade1"),
                    true, BoostFloat.FloatGradeFieldType.PERCENT);
            p.s = new S(chance.get(), duration.get(), bonusDmg.get());
            return p;
        }
        @Override
        public void flush() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            this.bonusDmg = new AtomicReference<Integer>(s.bonusDmg);
            this.duration = new AtomicReference<Float>(s.duration);
            this.chance = new AtomicReference<Float>(s.chance);
            gemBoost[0] = new BoostInteger(bonusDmg, g.bonusDmgUp, l.getWord("bashGrade3"),
                    true, BoostInteger.IntegerGradeFieldType.NONE);
            gemBoost[1] = new BoostFloat(duration,   g.durationUp, l.getWord("bashGrade2"),
                    true, BoostFloat.FloatGradeFieldType.TIME);
            gemBoost[2] = new BoostFloat(chance,     g.chanceUp,   l.getWord("bashGrade1"),
                    true, BoostFloat.FloatGradeFieldType.PERCENT);
        }

        @Override
        public Ability getAbility() {
            return new Bash(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader; ///*FontLoader.colorCode(0) + chance.get()*100 + "%[] "*/
            return l.getWord("bashTooltip1") + " " + FontLoader.colorString(chance.get()*100 + "%", User.GEM_TYPE.WHITE) + " " + l.getWord("bashTooltip2") + System.getProperty("line.separator") +
                   l.getWord("bashTooltip3") + " " + FontLoader.colorString(duration.get().toString(), User.GEM_TYPE.GREEN) + " " +
                   l.getWord("bashTooltip4") + " " + FontLoader.colorString(bonusDmg.get().toString(), User.GEM_TYPE.BLACK) + " " + l.getWord("bashTooltip5");
        }



        @Override
        public Array<Class<? extends AbilityPrototype>> getAbilitiesToSaveOnCraft() {
            return new Array<Class<? extends AbilityPrototype>>();
        }

//        @Override
//        public String getSaveCode() {
//            return super.getSaveCode() + "z" + chance + ";" + duration + ";" + bonusDmg + ";" + g.chanceUp + ";" + g.durationUp + ";" + g.bonusDmgUp;
//        }

//        protected String getBoostName(User.GEM_TYPE gemType){
//            switch (gemType) {
//                case BLACK: return "bash chance";
//                case GREEN: return "stun duration";
//                case WHITE: return "bonus stun damage";
//                default: return "error";
//            }
//        }
//        protected String getBoostValue(User.GEM_TYPE gemType){
//            G g = ((G) grader);//
//            switch (gemType) {
//                case BLACK: return "+ " + /*new DecimalFormat("##").format*/(g.chanceUp * 100) + "%";
//                case GREEN: return "+ " + new DecimalFormat("##.#").format(g.durationUp) + "s";
//                case WHITE: return "+ " + g.bonusDmgUp + "";
//                default: return "error";
//            }
//        }
//        protected String getCurrentValue(User.GEM_TYPE gemType){
//            switch (gemType) {
//                case BLACK: return new DecimalFormat("##").format(chance * 100) + "%";
//                case GREEN: return new DecimalFormat("##.#").format(duration) + "s";
//                case WHITE: return bonusDmg + "";
//                default: return "error";
//            }
//        }
//        protected String getBoostedValue(User.GEM_TYPE gemType){
//            G g = ((G) grader);//
//            switch (gemType) {
//                case BLACK: return new DecimalFormat("##").format(chance * 100 + g.chanceUp * 100) + "%";
//                case GREEN: return new DecimalFormat("##.#").format(duration + g.durationUp);
//                case WHITE: return (bonusDmg + g.bonusDmgUp) + "";
//                default: return "error";
//            }
//        }

    }
    public static class G extends AbilityGrader{
        protected float chanceUp;
        protected float durationUp;
        protected int bonusDmgUp;

        public G(float chanceUp, float durationUp, int bonusDmgUp, int[] gemCap) {
            super(gemCap);
            this.chanceUp = chanceUp;
            this.durationUp = durationUp;
            this.bonusDmgUp = bonusDmgUp;
        }
    }
    public static class S extends AbilitySaverStat{
        private float chance;
        private float duration;
        private int bonusDmg;

        public S(float chance, float duration, int bonusDmg) {
            this.chance = chance;
            this.duration = duration;
            this.bonusDmg = bonusDmg;
        }
    }

    private float chance;
    private float duration;
    private int bonusDmg;

    public Bash(P prototype) {
        this.chance = prototype.chance.get();
        this.duration = prototype.duration.get();
        this.bonusDmg = prototype.bonusDmg.get();
    }

    @Override
    public int getDmg(Mob target, int startDmg) {
        if(Chance.proc(chance)) {
            target.addEffect(new BashEffect(duration).setOwner(target));
            target.hit(bonusDmg, DamageType.Magic, owner);
        }
        return startDmg;
    }

    @Override
    protected void init(Map map) {

    }
}
