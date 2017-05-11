package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Mob.Mob;

public class AttackDelay extends Ability implements Ability.IPreAttack{

    public static class P extends Ability.AblityPrototype{
        private float delay;

        public P(float delay) {
            super("Attack Delay");
            this.delay = delay;
        }

        @Override
        public Ability getAbility() {
            return new AttackDelay(this);
        }

        @Override
        public String getTooltip() {
            return "Wait " + delay + " seconds before attack";
        }
    }

    private float delay;
    private float currentTime;

    public AttackDelay(P prototype) {
        this.delay = prototype.delay;
    }

    @Override
    public boolean use(Mob target, float delta) {
        currentTime += delta;
        if(currentTime >= delay) {
            currentTime = 0;
            return true;
        }else return false;
    }

    @Override
    protected void init() {

    }
}
