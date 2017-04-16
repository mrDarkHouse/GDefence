package com.darkhouse.gdefence.Level.Ability.Tower;


import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Tools.Chance;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class Bash extends Ability implements Ability.IOnHit{

    private class BashEffect extends Effect<Mob>{
        private float startSpeed;

        public BashEffect(float duration) {
            super(false, true, duration, "swimSpeed");
        }

        @Override
        public void apply() {
            startSpeed = owner.getSpeed();//bad idea
            owner.changeSpeed(0);
        }

        @Override
        public void dispell() {
            owner.changeSpeed(startSpeed);
            super.dispell();
        }
    }
    public static class P extends Ability.AblityPrototype{
        private float chance;
        private float duration;
        private int bonusDmg;

        public P(float chance, float duration, int bonusDmg) {
            super("Bash");
            this.chance = chance;
            this.duration = duration;
            this.bonusDmg = bonusDmg;
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
