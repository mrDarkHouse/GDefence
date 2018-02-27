package com.darkhouse.gdefence.Level.Ability.Tower;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Model.Level.Map;

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
            super(3, "desolate", grader.gemCap, IOnHit.class);
            this.armorReduction = new AtomicReference<Integer>(armorReduction);
            this.duration = new AtomicReference<Float>(duration);
            this.grader = grader;
        }
//        public P loadCode(String[] param, int[] gemCap){
//            return new Desolate.P(Integer.parseInt(param[0]), Float.parseFloat(param[1]),
//                    new Desolate.G(Integer.parseInt(param[2]), Float.parseFloat(param[3]), gemCap)).copy();
//        }

        @Override
        public Array<Class<? extends AbilityPrototype>> getAbilitiesToSaveOnCraft() {
            return new Array<Class<? extends AbilityPrototype>>();
        }

//        @Override
//        public String getSaveCode() {
//            return null;
//        }

        @Override
        public P copy() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            P p = new P(armorReduction.get(), duration.get(), grader);
            p.gemBoost[0] = new BoostInteger(p.armorReduction, grader.armorReductionUp, l.getWord("desolateGrade1"),
                    true, BoostInteger.IntegerGradeFieldType.NONE);
            p.gemBoost[1] = new BoostFloat(p.duration, grader.durationUp, l.getWord("desolateGrade2"),
                    true, BoostFloat.FloatGradeFieldType.TIME);
            return p;
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("desolateTooltip1") + " " + FontLoader.colorCode(0) + armorReduction
                    + System.getProperty("line.separator") + "[] " + l.getWord("desolateTooltip2") +
                    " " + FontLoader.colorCode(1) + duration + "[] " + l.getWord("desolateTooltip3");
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
    protected void init(Map map) {

    }


}
