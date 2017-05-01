package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Ability.Tools.Cooldown;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Projectile;
import com.darkhouse.gdefence.Level.Tower.Tower;

public class FireArrow extends Ability implements Ability.IAfterHit{

    public static class P extends Ability.AblityPrototype{
        private int damage;
        private float delay;
        private float duration;

        public P(int damage, float delay, float duration) {
            super("Fire Arrow");
            this.damage = damage;
            this.delay = delay;
            this.duration = duration;
        }

        @Override
        public Ability getAbility() {
            return new FireArrow(this);
        }

        @Override
        public String getTooltip() {
            return "After hitting burn enemy" + System.getProperty("line.separator") +
                    "Fire deal " + damage*(1/delay) + " for " + duration + " seconds";
        }
    }
    private class FireEffect extends Effect<Mob>{
        private int damage;
        private Tower source;

        public FireEffect(int damage, float delay, float duration, Tower source) {
            super(false, true, duration, "fireArrow");
            setCooldownable(new Cooldown(delay));
            getCooldownObject().setHidden();
            this.damage = damage;
            this.source = source;
        }

        @Override
        public void apply() {

        }

        @Override
        public void act(float delta) {
            super.act(delta);

            if(getCooldownObject().isReady()){
                owner.hit(damage, source);
                getCooldownObject().resetCooldown();
            }

        }
    }

    private int damage;
    private float delay;
    private float duration;


    public FireArrow(P prototype) {
        this.damage = prototype.damage;
        this.delay = prototype.delay;
        this.duration = prototype.duration;
    }

    @Override
    protected void init() {

    }

    @Override
    public void hit(Mob target, int dmg, Projectile hittingProjectile) {
        target.addEffect(new FireEffect(damage, delay, duration, owner).setOwner(target));
    }
}
