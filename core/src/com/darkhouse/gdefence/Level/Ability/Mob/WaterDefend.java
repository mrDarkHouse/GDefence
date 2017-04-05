package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Buff.WaterBonusArmor;
import com.darkhouse.gdefence.Level.Path.MapTile;

public class WaterDefend extends MobAbility implements MobAbility.IMove{

    private int armor;
    private WaterBonusArmor effect;

    public WaterDefend(int armor) {
        super("Water Defend", false);
        this.armor = armor;
    }

    @Override
    public String getTooltip() {
        return "Add [#64A619ff]" + armor + "[] armor" + System.getProperty("line.separator") +
                "when swimming";
    }

    @Override
    public void init() {
        effect = new WaterBonusArmor(owner, -1, armor);
    }

    @Override
    public void move(MapTile currentTile) {
        if(currentTile.isSwimmable()){
            if(!owner.haveDebuff(WaterBonusArmor.class)) owner.addDebuff(effect);
        } else {
            if(owner.haveDebuff(WaterBonusArmor.class)) effect.dispell();
        }
    }
}
