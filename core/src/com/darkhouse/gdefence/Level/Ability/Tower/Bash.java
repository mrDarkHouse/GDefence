package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Tools.Chance;
import com.darkhouse.gdefence.Level.Mob.Mob;
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
        private AtomicReference<Float> chance;
        private AtomicReference<Float> duration;
        private AtomicReference<Integer> bonusDmg;

        public P(float chance, float duration, int bonusDmg, G grader) {
            super(0, "bash", grader.gemCap);
            this.chance = new AtomicReference<Float>(chance);
            this.duration = new AtomicReference<Float>(duration);
            this.bonusDmg = new AtomicReference<Integer>(bonusDmg);
            this.g = grader;
        }


//        USE IT FOR NEW ABILITY PROTOTYPE
        public P copy(){
            AssetLoader l = GDefence.getInstance().assetLoader;
            P p = new P(chance.get(), duration.get(), bonusDmg.get(), g);
            p.gemBoost[0] = new BoostFloat(p.chance,     g.chanceUp,   l.getWord("bashGrade1"),
                    true, BoostFloat.FloatGradeFieldType.PERCENT);
            p.gemBoost[1] = new BoostFloat(p.duration,   g.durationUp, l.getWord("bashGrade2"),
                    true, BoostFloat.FloatGradeFieldType.TIME);
            p.gemBoost[2] = new BoostInteger(p.bonusDmg, g.bonusDmgUp, l.getWord("bashGrade3"),
                    true, BoostInteger.IntegerGradeFieldType.NONE);
            return p;
        }

        @Override
        public Ability getAbility() {
            return new Bash(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("bashTooltip1") + " " + FontLoader.colorCode(0) + chance.get()*100 + "%[] " + l.getWord("bashTooltip2") + System.getProperty("line.separator") +
                   l.getWord("bashTooltip3") + " " + FontLoader.colorCode(1) + duration + "[] " + l.getWord("bashTooltip4")
                    + " " + FontLoader.colorCode(2) + bonusDmg + "[] " + l.getWord("bashTooltip5");
        }
        @Override
        public String getSaveCode() {
            return super.getSaveCode() + "z" + chance + ";" + duration + ";" + bonusDmg + ";" + g.chanceUp + ";" + g.durationUp + ";" + g.bonusDmgUp;
        }

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
            target.hit(bonusDmg, owner);
        }
        return startDmg;
    }

    @Override
    protected void init() {

    }
}
