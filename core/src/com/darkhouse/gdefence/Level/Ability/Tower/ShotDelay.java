package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Tower;

public class ShotDelay extends Ability implements Ability.IPreAttack {

    public static class P extends Ability.AblityPrototype{
        private float delay;

        public P(float delay) {
            super("Attack Delay");
            this.delay = delay;
        }

        @Override
        public Ability getAbility() {
            return new ShotDelay(this);
        }

        @Override
        public String getTooltip() {
            return "Wait " + delay + " seconds before attack";
        }
    }
    private class AttackBlocker extends Effect<Tower>{
        public AttackBlocker(float duration) {
            super(true, false, duration, "swimSpeed");
        }

        @Override
        public void apply() {
            owner.setCanAttack(false);
        }

        @Override
        public void dispell() {
            if(!owner.isCanAttack()) owner.setCanAttack(true);//this dispell other disarm
            super.dispell();
        }
    }

    private float delay;
    private float currentTime;

    public ShotDelay(P prototype) {
        this.delay = prototype.delay;
    }

    @Override
    public boolean use(float delta) {
        currentTime += delta;
        if(currentTime >= delay) {
            currentTime = 0;
            return true;
        }else return false;
//        owner.addEffect(new AttackBlocker(delay).setOwner(owner));
//        return true;
    }

    @Override
    protected void init() {

    }
}
