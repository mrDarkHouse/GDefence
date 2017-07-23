package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Path.MapTile;

public class WaterDefend extends MobAbility implements MobAbility.IMove{

    private class WaterBonusArmor extends Effect<Mob> {
        private int bonusArmor;

        public WaterBonusArmor(float duration, int armor) {
            super(true, false, duration, "waterBonusArmor");
            this.bonusArmor = armor;
        }

        @Override
        public void apply() {
            owner.changeArmor(bonusArmor);
        }

        @Override
        public void dispell() {
            owner.changeArmor(-bonusArmor);
            super.dispell();
        }
    }
    public static class P extends AbilityPrototype {
        private int armor;

        public P(int armor) {
            super("Water Defend", false);
            this.armor = armor;

        }
        public MobAbility getAbility(){
            return new WaterDefend(this);
        }

        @Override
        public String getTooltip() {
            return "Add [#64A619ff]" + armor + "[] armor" + System.getProperty("line.separator") +
                    "when swimming";
        }
    }

    private WaterBonusArmor buff;

    public WaterDefend(P prototype) {
        buff = new WaterBonusArmor(-1, prototype.armor);
    }

    @Override
    public void init() {
        buff.setOwner(owner);
    }

    @Override
    public void move(MapTile currentTile) {
        if(currentTile.isSwimmable()){
            if(!owner.haveEffect(WaterBonusArmor.class)) owner.addEffect(buff);
        } else {
            if(owner.haveEffect(WaterBonusArmor.class)) buff.dispell();
        }
    }


}
