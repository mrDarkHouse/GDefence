package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Buff.WaterBonusArmor;
import com.darkhouse.gdefence.Level.Path.MapTile;

public class WaterDefend extends MobAbility implements MobAbility.IMove{


    private WaterBonusArmor buff;

    public static class P extends AblityPrototype{
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

    public WaterDefend(P prototype) {
        buff = new WaterBonusArmor(-1, prototype.armor);
    }

    //    public WaterDefend(int armor) {
//        super("Water Defend", false);
//        this.armor = armor;
//        buff = new WaterBonusArmor(-1, armor);
//    }



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
