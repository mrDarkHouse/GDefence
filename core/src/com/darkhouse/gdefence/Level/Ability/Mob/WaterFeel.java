package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Path.MapTile;

public class WaterFeel extends MobAbility implements MobAbility.IMove{

    private class SwimSpeed extends Effect<Mob> {
        private int value;

        public SwimSpeed(float duration, int value) {
            super(true, false, duration, "swimSpeed");
            this.value = value;
        }

        @Override
        public void apply() {
            owner.changeSpeed(value);
        }

        @Override
        public void dispell() {
            owner.changeSpeed(-value);
            super.dispell();
        }
    }
    public static class P extends AbilityPrototype {
        private int speedValue;

        public P(int speedValue) {
            super("Water Feel", false);
            this.speedValue = speedValue;

        }
        public MobAbility getAbility(){
            return new WaterFeel(this);
        }

        @Override
        public String getTooltip() {
            return "Add [#64A619ff]" + speedValue + "[] move speed" + System.getProperty("line.separator") +
                    "when swimming";
        }
    }


//    private float speedValue;
    private SwimSpeed buff;

//    public WaterFeel(float speedValue) {
//        super("Water Feel", false);
//        this.speedValue = speedValue;
//        buff = new SwimSpeed(-1, speedValue);
//    }


    public WaterFeel(P prototype) {
        buff = new SwimSpeed(-1, prototype.speedValue);
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
