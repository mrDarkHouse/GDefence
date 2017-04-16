package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Tools.Stackable;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Level.Tower.Tower;

public class HunterSpeed extends Ability implements Ability.IOnBuild{

    private class HunterSpeedBuff extends Effect<Tower> implements IOnKilled{
        private int speedUp;
        private float duration;

        public HunterSpeedBuff(int kills, int speedUp, float duration) {
            super(true, true, -1, "swimSpeed");
            this.speedUp = speedUp;
            this.duration = duration;
            setStackable(new Stackable(kills));
        }

        @Override
        public void apply() {

        }

        @Override
        public void killed(Mob killedMob) {
            getStackableObject().addStack();
            if(getStackableObject().isMaxStacks()){
                owner.addEffect(new HunterSpeedSpeed(speedUp, duration).setOwner(owner));
                getStackableObject().setCurrentStacks(0);
            }
        }
    }
    private class HunterSpeedSpeed extends Effect<Tower>{
        private int speedUp;

        public HunterSpeedSpeed(int speedUp, float duration) {
            super(true, true, duration, "swimSpeed");
            this.speedUp = speedUp;
        }

        @Override
        public void apply() {
            owner.changeAttackSpeed(speedUp);
        }

        @Override
        public void dispell() {
            owner.changeAttackSpeed(-speedUp);
            super.dispell();
        }
    }
    public static class P extends Ability.AblityPrototype{
        private int kills;
        private int speedUp;
        private float duration;

        public P(int kills, int speedUp, float duration) {
            super("Hunter Speed");
            this.kills = kills;
            this.speedUp = speedUp;
            this.duration = duration;
        }

        @Override
        public Ability getAbility() {
            return new HunterSpeed(this);
        }

        @Override
        public String getTooltip() {
            return "After killing " + kills + " mobs attack speed increased by " + speedUp + "for " + duration + " seconds";
        }
    }



    private HunterSpeedBuff buff;

    public HunterSpeed(P prototype) {
        buff = new HunterSpeedBuff(prototype.kills, prototype.speedUp, prototype.duration);
    }

    @Override
    protected void init() {
        buff.setOwner(owner);
    }

//    @Override
//    public void killed(Mob killedMob) {
//
//    }
    @Override
    public void builded(MapTile tile) {
        owner.addEffect(buff);
    }


}
