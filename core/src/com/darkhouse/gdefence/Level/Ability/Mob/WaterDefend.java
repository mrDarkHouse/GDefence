package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Level.Path.WalkableMapTile;

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
            super("waterDefend", false, IMove.class);
            this.armor = armor;

        }
        public MobAbility getAbility(){
            return new WaterDefend(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("waterDefendTooltip1") + " " + FontLoader.colorString(Integer.toString(armor), 3) + " " +
                    l.getWord("waterDefendTooltip2") + System.getProperty("line.separator") +
                    l.getWord("waterDefendTooltip3");
        }
    }

    private WaterBonusArmor buff;

    public WaterDefend(P prototype) {
        super(prototype);
        buff = new WaterBonusArmor(-1, prototype.armor);
    }

    @Override
    public void init() {
        buff.setOwner(owner);
    }

    @Override
    public void move(WalkableMapTile currentTile) {
        if(currentTile.isSwimmable()){
            if(!owner.haveEffect(WaterBonusArmor.class)) owner.addEffect(buff);
        } else {
            if(owner.haveEffect(WaterBonusArmor.class)) buff.dispell();
        }
    }


}
