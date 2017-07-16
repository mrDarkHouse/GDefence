package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Tools.Chance;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.User;

public class Bash extends Ability implements Ability.IOnHit{

    private class BashEffect extends Effect<Mob>{
//        private float startSpeed;

        public BashEffect(float duration) {
            super(false, true, duration, "bash");
        }

        @Override
        public void apply() {
//            startSpeed = owner.getSpeed();//bad idea
//            owner.changeSpeed(0);
            owner.setState(Mob.State.stunned);
        }

        @Override
        public void dispell() {
//            owner.changeSpeed(startSpeed);
            if(owner.getState() == Mob.State.stunned) owner.setState(Mob.State.normal); //dont dispell other effects, but dispell stuns
            super.dispell();
        }
    }
    public static class P extends Ability.AblityPrototype{
//        private G grader;
        private float chance;
        private float duration;
        private int bonusDmg;

        public P(float chance, float duration, int bonusDmg, G grader) {
            super("Bash");
            this.chance = chance;
            this.duration = duration;
            this.bonusDmg = bonusDmg;
            this.grader = grader;
            gemsMax = new int[]{3, 3, 3};
        }

        @Override
        protected boolean grade(User.GEM_TYPE t) {
            G g = ((G) grader);
            switch (t){
                case BLACK:
                    chance += g.chanceUp;
                    return true;
                case GREEN:
                    duration += g.durationUp;
                    return true;
                case WHITE:
                    bonusDmg += g.bonusDmgUp;
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public Ability getAbility() {
            return new Bash(this);
        }

        @Override
        public String getTooltip() {
            return "Have " + chance*100 + " % chance to bash attacked enemy for " + duration +
                    " seconds and do " + bonusDmg + " bonus damage";
        }
    }
    public static class G extends AbilityGrader{
        protected float chanceUp;
        protected float durationUp;
        protected int bonusDmgUp;

        public G(float chanceUp, float durationUp, int bonusDmgUp) {
            this.chanceUp = chanceUp;
            this.durationUp = durationUp;
            this.bonusDmgUp = bonusDmgUp;
        }
    }

    private float chance;
    private float duration;
    private int bonusDmg;

    public Bash(P prototype) {
        this.chance = prototype.chance;
        this.duration = prototype.duration;
        this.bonusDmg = prototype.bonusDmg;
    }

    @Override
    public int getDmg(Mob target, int startDmg) {
        if(Chance.proc(chance)) {
            target.addEffect(new BashEffect(duration).setOwner(target));
            target.hit(bonusDmg, owner);
        }
        return startDmg;
    }

    @Override
    protected void init() {

    }
}
