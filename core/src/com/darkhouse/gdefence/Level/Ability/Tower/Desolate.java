package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;

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
    public static class P extends Ability.AblityPrototype{
        private int armor;
        private float duration;

        public P(int armor, float duration) {
            super("Desolate");
            this.armor = armor;
            this.duration = duration;
        }

        @Override
        public Ability getAbility() {
            return new Desolate(this);
        }

        @Override
        public String getTooltip() {
            return "Down attacked enemies armor by " + armor + " for" + duration + " seconds";
        }
    }

    private int armor;
    private float duration;

    public Desolate(P prototype) {
        armor = prototype.armor;
        duration = prototype.duration;
        setWorkOnAdditionalProjectiles();
    }

    //    public Desolate(int armor, float duration) {
//        super(UseType.onHit);
//        this.armor = armor;
//        this.duration = duration;
//    }

//    @Override
//    public void use(Mob target) {
//        if(target != null) {
//            target.addEffect(new ArmorReduction(armor, duration).setOwner(target));
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
