package com.darkhouse.gdefence.Level.Ability.Tower;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.User;

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
        private AtomicReference<Integer> armorReduction;
        private AtomicReference<Float> duration;
        private G g;
        private S s;

        public P(int armorReduction, float duration, G grader) {
            super(3, "desolate", grader.gemCap, IOnHit.class);
            this.armorReduction = new AtomicReference<Integer>(armorReduction);
            this.duration = new AtomicReference<Float>(duration);
            this.g = grader;
            s = new S(armorReduction, duration);
        }

        @Override
        public Array<Class<? extends AbilityPrototype>> getAbilitiesToSaveOnCraft() {
            return new Array<Class<? extends AbilityPrototype>>();
        }

        @Override
        public P copy() {
            P p = new P(armorReduction.get(), duration.get(), g);
            p.initBoosts(GDefence.getInstance().assetLoader);
            p.s = s;
            return p;
        }

        @Override
        public void flush() {
            this.armorReduction = new AtomicReference<Integer>(s.armorReduction);
            this.duration = new AtomicReference<Float>(s.duration);
            initBoosts(GDefence.getInstance().assetLoader);
        }

        @Override
        protected void initBoosts(AssetLoader l) {
            gemBoost[0] = new BoostInteger(armorReduction, g.armorReductionUp, l.getWord("desolateGrade1"),
                    true, BoostInteger.IntegerGradeFieldType.NONE);
            gemBoost[1] = new BoostFloat(duration, g.durationUp, l.getWord("desolateGrade2"),
                    true, BoostFloat.FloatGradeFieldType.TIME);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("desolateTooltip1") + " " + FontLoader.colorString(armorReduction.get().toString(), User.GEM_TYPE.BLACK) + System.getProperty("line.separator") +
                    l.getWord("desolateTooltip2") + " " + FontLoader.colorString(duration.get().toString(), User.GEM_TYPE.GREEN) + " " + l.getWord("desolateTooltip3");
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
    private static class S extends AbilitySaverStat{
        private int armorReduction;
        private float duration;

        public S(int armorReduction, float duration) {
            this.armorReduction = armorReduction;
            this.duration = duration;
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
