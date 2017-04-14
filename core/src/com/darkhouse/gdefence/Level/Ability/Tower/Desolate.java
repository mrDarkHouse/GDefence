package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Ability.Mob.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class Desolate extends Ability{

    private static class ArmorReduction extends Effect {
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

    private int armor;
    private float duration;

    public Desolate(int armor, float duration) {
        super(UseType.onHit);
        this.armor = armor;
        this.duration = duration;
    }

    @Override
    public void use(Mob target) {
        if(target != null) {
            target.addEffect(new ArmorReduction(armor, duration).setOwner(target));
        }
    }


}
