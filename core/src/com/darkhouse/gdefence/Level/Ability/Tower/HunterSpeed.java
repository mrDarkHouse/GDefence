package com.darkhouse.gdefence.Level.Ability.Tower;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Tools.Stackable;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Level.Tower.Tower;

import java.util.concurrent.atomic.AtomicReference;

public class HunterSpeed extends Ability implements Ability.IOnBuild{

    public static class P extends AbilityPrototype {
        private G grader;
        private AtomicReference<Integer> kills;
        private AtomicReference<Integer> speed;
        private AtomicReference<Float> duration;

        public P(int kills, int speed, float duration, G grader) {
            super(5, "hunterSpeed", grader.gemCap);
            this.kills = new AtomicReference<Integer>(kills);
            this.speed = new AtomicReference<Integer>(speed);
            this.duration = new AtomicReference<Float>(duration);
            this.grader = grader;
        }

        @Override
        public Array<Class<? extends AbilityPrototype>> getAbilitiesToSaveOnCraft() {
            return new Array<Class<? extends AbilityPrototype>>();
        }

//        @Override
//        public String getSaveCode() {
//            return null;
//        }

        @Override
        public AbilityPrototype copy() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            P p = new P(kills.get(), speed.get(), duration.get(), grader);
            p.gemBoost[0] = new BoostInteger(p.speed, grader.speedUp, l.getWord("hunterSpeedGrade1"),
                    true, BoostInteger.IntegerGradeFieldType.NONE);
            p.gemBoost[1] = new BoostFloat(p.duration, grader.durationUp, l.getWord("hunterSpeedGrade2"),
                    true, BoostFloat.FloatGradeFieldType.TIME);
            p.gemBoost[2] = new BoostInteger(p.kills, grader.killsDown, l.getWord("hunterSpeedGrade3"),
                    false, BoostInteger.IntegerGradeFieldType.NONE);
            return p;
        }

        @Override
        public Ability getAbility() {
            return new HunterSpeed(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("hunterSpeedTooltip1") + " " + FontLoader.colorString(kills.get().toString(), 2) + " " +
                    l.getWord("hunterSpeedTooltip2") + System.getProperty("line.separator") +
                    l.getWord("hunterSpeedTooltip3") + " " + FontLoader.colorString(speed.get().toString(), 0) + " " +
                    l.getWord("hunterSpeedTooltip4") + " " + FontLoader.colorString(duration.get().toString(), 1) + " " +
                    l.getWord("hunterSpeedTooltip5");
        }

    }
    public static class G extends AbilityGrader{
        private int speedUp;
        private float durationUp;
        private int killsDown;

        public G(int speedUp, float durationUp, int killsDown, int[] gemCap) {
            super(gemCap);
            this.speedUp = speedUp;
            this.durationUp = durationUp;
            this.killsDown = killsDown;
        }
    }



    private class HunterSpeedBuff extends Effect<Tower> implements IOnKilled{
        private int speedUp;
        private float duration;

        public HunterSpeedBuff(int kills, int speedUp, float duration) {
            super(true, true, -1, "bonusArmor");
            this.speedUp = speedUp;
            this.duration = duration;
            setStackable(new Stackable(kills));
        }

        @Override
        public void apply() {

        }

        @Override
        public void dispell() {
            super.dispell();
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

    private HunterSpeedBuff buff;

    public HunterSpeed(P prototype) {
        buff = new HunterSpeedBuff(prototype.kills.get(), prototype.speed.get(), prototype.duration.get());
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
