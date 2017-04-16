package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Tools.Cooldown;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class Sprint extends MobAbility implements MobAbility.ISpawn{
    private class SprintBuff extends Effect<Mob>{
        private int value;

        public SprintBuff(float cdCap, float duration, int value) {
            super(true, true, duration, "swimSpeed");
            this.value = value;
            setCooldownable(new Cooldown(cdCap));
        }

        @Override
        public void apply() {
//            owner.changeSpeed(value);
            getCooldownObject().resetCooldown();
        }

        public void act(float delta){
            getCooldownObject().act(delta);
            if(getCooldownObject().isReady()){
                owner.addEffect(new SprintSpeed(duration, value).setOwner(owner));
                getCooldownObject().resetCooldown();
            }
        }

        @Override
        public void dispell() {
//            owner.changeSpeed(-value);
//            owner.deleteEffect(this.getClass());
        }
    }
    private class SprintSpeed extends Effect<Mob>{
        private int value;

        public SprintSpeed(float duration, int value) {
            super(true, true, duration);
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
    public static class P extends AblityPrototype{
        private float cdCap;
        private float duration;
        private int speedBoost;

        public P(float cdCap, float duration, int speedBoost) {
            super("Sprint", false);
            this.cdCap = cdCap;
            this.duration = duration;
            this.speedBoost = speedBoost;
        }
        public MobAbility getAbility(){
            return new Sprint(this);
        }

        @Override
        public String getTooltip() {
            return "Increase move speed by [#64A619ff]" + speedBoost +  System.getProperty("line.separator") +
                    "[] for [#64A619ff]" + duration + "[] seconds" +  System.getProperty("line.separator") +
                    "every [#64A619ff]" + cdCap + "[] seconds";
        }
    }

    private SprintBuff effect;

    public Sprint(P prototype) {
        effect = new SprintBuff(prototype.cdCap, prototype.duration, prototype.speedBoost);
    }

    @Override
    public void init() {
        effect.setOwner(owner);
    }

    @Override
    public void spawned() {
        owner.addEffect(effect);
    }

}
