package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Buff.SwimSpeed;
import com.darkhouse.gdefence.Level.Path.MapTile;

public class WaterFeel extends MobAbility implements MobAbility.IMove{
    private float speedPercent;

    public WaterFeel(float speedPercent) {
        super("Water Feel", false);
        this.speedPercent = speedPercent;
    }

    @Override
    public String getTooltip() {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public void move(MapTile currentTile) {
        if(currentTile.isSwimmable()){
            if(!owner.haveDebuff(SwimSpeed.class)) owner.addDebuff(new SwimSpeed(owner, -1, speedPercent));
        }
        else owner.deleteDebuff(SwimSpeed.class);
    }
}
