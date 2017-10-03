package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;

import java.util.concurrent.atomic.AtomicReference;

public class Desolate extends Ability implements Ability.IOnHit {

    private static class ArmorReduction extends Effect<Mob> {
        private int armor;

        public ArmorReduction(int armor, float duration) {
            super(false, true, duration, "armorReduction");
            this.armor = armor;
        }

        @Override
        public void apply() {
            owner.changeArmor(-armor);
        }

        @Override
        public void dispell() {
            owner.changeArmor(armor);
            super.dispell();
        }
    }
    public static class P extends AbilityPrototype {
        private G grader;
        private AtomicReference<Integer> armorReduction;
        private AtomicReference<Float> duration;

        public P(int armorReduction, float duration, G grader) {
            super(3, "Desolate", "desolate", grader.gemCap);
            this.armorReduction = new AtomicReference<Integer>(armorReduction);
            this.duration = new AtomicReference<Float>(duration);
            this.grader = grader;
        }

        @Override
        public String getSaveCode() {
            return null;
        }

        @Override
        public AbilityPrototype copy() {
            P p = new P(armorReduction.get(), duration.get(), grader);
            p.gemBoost[0] = new BoostInteger(p.armorReduction, grader.armorReductionUp, "armor reduction",
                    true, BoostInteger.IntegerGradeFieldType.NONE);
            p.gemBoost[1] = new BoostFloat(p.duration, grader.durationUp, "effect duration",
                    true, BoostFloat.FloatGradeFieldType.TIME);
            return p;
        }

        @Override
        public String getTooltip() {
            return "Down attacked enemies armorReduction by [#000000ff]" + armorReduction + System.getProperty("line.separator")
                    + "[] for [#0ffe00ff]" + duration + "[] seconds";
        }

        @Override
        public Ability getAbility() {
            return new Desolate(this);
        }


    }
    public static class G extends AbilityGrader{
        private int armorReductionUp;
        private float durationUp;

        public G(int armorReductionUp, float durationUp, int[] gemCap) {
            super(gemCap);
            this.armorReductionUp = armorReductionUp;
            this.durationUp = durationUp;
        }
    }

    private int armor;
    private float duration;

    public Desolate(P prototype) {
        armor = prototype.armorReduction.get();
        duration = prototype.duration.get();
        setWorkOnAdditionalProjectiles();
    }

    //    public Desolate(int armorReduction, float duration) {
//        super(UseType.onHit);
//        this.armorReduction = armorReduction;
//        this.duration = duration;
//    }

//    @Override
//    public void use(Mob target) {
//        if(target != null) {
//            target.addEffect(new ArmorReduction(armorReduction, duration).setOwner(target));
//        }
//    }

    @Override
    public int getDmg(Mob target, int startDmg) {
        if(target != null) {
            target.addEffect(new ArmorReduction(armor, duration).setOwner(target));
        }
        return startDmg;
    }

    @Override
    protected void init() {

    }


}
