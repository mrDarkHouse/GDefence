package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Level.Path.WalkableMapTile;

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
            super("waterFeel", false, IMove.class);
            this.speedValue = speedValue;

        }
        public MobAbility getAbility(){
            return new WaterFeel(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("waterFeelTooltip1") + " " + FontLoader.colorString(Integer.toString(speedValue), 10) + " " +
                    l.getWord("waterFeelTooltip2") + System.getProperty("line.separator") +
                    l.getWord("waterFeelTooltip3");
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
        super(prototype);
        buff = new SwimSpeed(-1, prototype.speedValue);
    }

    @Override
    public void init() {
        buff.setOwner(owner);
    }

    @Override
    public void move(WalkableMapTile currentTile) {
        if(currentTile.isSwimmable()){
            if(!owner.haveEffect(SwimSpeed.class)) owner.addEffect(buff);
        } else {
            if(owner.haveEffect(SwimSpeed.class)) buff.dispell();
        }
        //owner.deleteEffect(SwimSpeed.class);
    }
}
