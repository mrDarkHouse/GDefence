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
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.User;

import java.util.concurrent.atomic.AtomicReference;

public class HunterSpeed extends Ability implements Ability.IOnBuild{

    public static class P extends AbilityPrototype {
        private AtomicReference<Integer> kills;
        private AtomicReference<Integer> speed;
        private AtomicReference<Float> duration;
        private G g;
        private S s;

        public P(int kills, int speed, float duration, G grader) {
            super(5, "hunterSpeed", grader.gemCap, IOnBuild.class);
            this.kills = new AtomicReference<Integer>(kills);
            this.speed = new AtomicReference<Integer>(speed);
            this.duration = new AtomicReference<Float>(duration);
            this.g = grader;
            this.s = new S(kills, speed, duration);
        }

        @Override
        public Array<Class<? extends AbilityPrototype>> getAbilitiesToSaveOnCraft() {
            return new Array<Class<? extends AbilityPrototype>>();
        }

        @Override
        public AbilityPrototype copy() {
            P p = new P(kills.get(), speed.get(), duration.get(), g);
            p.initBoosts(GDefence.getInstance().assetLoader);
            p.s = s;
            return p;
        }

        @Override
        public void flush() {
            this.kills = new AtomicReference<Integer>(s.kills);
            this.speed = new AtomicReference<Integer>(s.speed);
            this.duration = new AtomicReference<Float>(s.duration);
            initBoosts(GDefence.getInstance().assetLoader);
        }

        @Override
        protected void initBoosts(AssetLoader l) {
            gemBoost[0] = new BoostInteger(speed, g.speedUp, l.getWord("hunterSpeedGrade1"),
                    true, BoostInteger.IntegerGradeFieldType.NONE);
            gemBoost[1] = new BoostFloat(duration, g.durationUp, l.getWord("hunterSpeedGrade2"),
                    true, BoostFloat.FloatGradeFieldType.TIME);
            gemBoost[2] = new BoostInteger(kills, g.killsDown, l.getWord("hunterSpeedGrade3"),
                    false, BoostInteger.IntegerGradeFieldType.NONE);
        }

        @Override
        public Ability getAbility() {
            return new HunterSpeed(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("hunterSpeedTooltip1") + " " + FontLoader.colorString(kills.get().toString(), User.GEM_TYPE.WHITE) + " " +
                    l.getWord("hunterSpeedTooltip2") + System.getProperty("line.separator") +
                    l.getWord("hunterSpeedTooltip3") + " " + FontLoader.colorString(speed.get().toString(), User.GEM_TYPE.BLACK) + " " +
                    l.getWord("hunterSpeedTooltip4") + " " + FontLoader.colorString(duration.get().toString(), User.GEM_TYPE.GREEN) + " " +
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
    private static class S extends AbilitySaverStat{
        private int kills;
        private int speed;
        private float duration;

        public S(int kills, int speed, float duration) {
            this.kills = kills;
            this.speed = speed;
            this.duration = duration;
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
    protected void init(Map map) {
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
