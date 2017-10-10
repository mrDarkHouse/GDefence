package com.darkhouse.gdefence.Level.Ability.Mob;


import com.badlogic.gdx.math.Circle;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Level.Wave;

public class CommandFaith extends MobAbility implements MobAbility.IDie{

    private class CommandFaithBuff extends Effect<Mob>{
        private int echoRange;
        private int speedBoost;

        public CommandFaithBuff(int echoRange, int speedBoost, float duration) {
            super(true, true, duration, "swimSpeed");
            this.echoRange = echoRange;
            this.speedBoost = speedBoost;
        }

        @Override
        public void apply() {
            owner.changeSpeed(speedBoost);
            Circle c = new Circle(owner.getX(), owner.getY(), echoRange);
            for (int i = 0; i < Wave.mobs.size; i++){
                Mob m = Wave.mobs.get(i);
                if(c.contains(m.getPosition())){
                    m.addEffect(new CommandFaithBuff(aoeRange, speedBoost, duration).setOwner(m));
                }
            }
            //
        }

        @Override
        public void dispell() {
            owner.changeSpeed(-speedBoost);
            super.dispell();
        }
    }
    public static class P extends AbilityPrototype {
        private int range;
        private int speedBoost;
        private float duration;

        public P(int range, int speedBoost, float duration) {
            super("commandFaith", false);
            this.range = range;
            this.speedBoost = speedBoost;
            this.duration = duration;
        }
        public MobAbility getAbility(){
            return new CommandFaith(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("commandFaithTooltip1") + " " + FontLoader.colorString(Integer.toString(range), 3) + " " +
                    l.getWord("commandFaithTooltip2") + " " + FontLoader.colorString(Integer.toString(speedBoost), 3) + " " +
                    l.getWord("commandFaithTooltip3") + " " + FontLoader.colorString(Float.toString(duration), 3) + " " +
                    l.getWord("commandFaithTooltip4") + System.getProperty("line.separator") +
                    l.getWord("commandFaithTooltip5");
        }
    }


    private int aoeRange;
    private int speedBoost;
    private float duration;

    public CommandFaith(P prototype) {
        this.aoeRange = prototype.range;
        this.speedBoost = prototype.speedBoost;
        this.duration = prototype.duration;
    }

    @Override
    public void init() {

    }

    @Override
    public boolean die(/*Tower source*/) {
        Circle dieAoe = new Circle(owner.getX(), owner.getY(), aoeRange);
        for (int i = 0; i < Wave.mobs.size; i++){
                Mob m = Wave.mobs.get(i);
            if(dieAoe.contains(m.getPosition())){
                m.addEffect(new CommandFaithBuff(aoeRange, speedBoost, duration).setOwner(m));
            }
        }
        return false;
    }
}
