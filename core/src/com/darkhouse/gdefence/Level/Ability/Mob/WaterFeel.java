package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Buff.SwimSpeed;
import com.darkhouse.gdefence.Level.Path.MapTile;

public class WaterFeel extends MobAbility implements MobAbility.IMove{

    public static class P extends AblityPrototype{
        private float speedPercent;

        public P(float speedPercent) {
            super("Water Feel", false);
            this.speedPercent = speedPercent;

        }
        public MobAbility getAbility(){
            return new WaterFeel(this);
        }

        @Override
        public String getTooltip() {
            return "Add [#64A619ff]" + speedPercent * 100 + "%[] move speed" + System.getProperty("line.separator") +
                    "when swimming";
        }
    }


//    private float speedPercent;
    private SwimSpeed buff;

//    public WaterFeel(float speedPercent) {
//        super("Water Feel", false);
//        this.speedPercent = speedPercent;
//        buff = new SwimSpeed(-1, speedPercent);
//    }


    public WaterFeel(P prototype) {
        buff = new SwimSpeed(-1, prototype.speedPercent);
    }

    @Override
    public void init() {
        buff.setOwner(owner);
    }

    @Override
    public void move(MapTile currentTile) {
        if(currentTile.isSwimmable()){
            if(!owner.haveEffect(SwimSpeed.class)) owner.addEffect(buff);
        } else {
            if(owner.haveEffect(SwimSpeed.class)) buff.dispell();
        }
        //owner.deleteEffect(SwimSpeed.class);
    }
}
