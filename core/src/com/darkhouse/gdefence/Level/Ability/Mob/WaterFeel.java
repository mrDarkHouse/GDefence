package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Buff.SwimSpeed;
import com.darkhouse.gdefence.Level.Path.MapTile;

public class WaterFeel extends MobAbility implements MobAbility.IMove{
    private float speedPercent;
    private SwimSpeed effect;

    public WaterFeel(float speedPercent) {
        super("Water Feel", false);
        this.speedPercent = speedPercent;
    }

    @Override
    public String getTooltip() {
        return "Add [#64A619ff]" + speedPercent * 100 + "%[] move speed" + System.getProperty("line.separator") +
                "when swimming";
    }

    @Override
    public void init() {
        effect = new SwimSpeed(owner, -1, speedPercent);
    }

    @Override
    public void move(MapTile currentTile) {
        if(currentTile.isSwimmable()){
            if(!owner.haveDebuff(SwimSpeed.class)) owner.addDebuff(effect);
        } else {
            if(owner.haveDebuff(SwimSpeed.class)) effect.dispell();
        }
        //owner.deleteDebuff(SwimSpeed.class);
    }
}
