package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Ability.Tools.Aura;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Wave;

public class FrozenAura extends MobAbility implements MobAbility.ISpawn{

    public class FrozenAuraBuff extends Effect<Mob> implements IListener{

        public FrozenAuraBuff(int range, float duration) {
            super(true, false, -1);
            setAura(new Aura(range));
        }
        @Override
        public void act(float delta) {
            super.act(delta);
            getAuraObject().update();
//            getCooldownObject().act(delta);
//            CopyOnWriteArrayList<Mob> tmpMobs = new CopyOnWriteArrayList<Mob>((Collection) Wave.mobs);
            for (int i = 0; i < Wave.mobs.size; i++){
                Mob m = Wave.mobs.get(i);
                if(getAuraObject().isInRange(m)){
                    m.addEffect(new FrozenAuraStun(duration, getAuraObject()).setOwner(m));//if m !haveEffect already in addEffect
                }
            }

        }

        @Override
        public void apply() {

        }
    }
    public class FrozenAuraStun extends Effect<Mob>{
        private Aura centerAura;

        public FrozenAuraStun(float duration, Aura centerAura) {
            super(false, true, duration, "freeze");
            this.centerAura = centerAura;
        }

        @Override
        public void apply() {
            owner.setState(Mob.State.stunned);
        }

        @Override
        public void dispell() {
            if(owner.getState() == Mob.State.stunned) owner.setState(Mob.State.normal);
            super.dispell();
        }

        @Override
        public void act(float delta) {
//            super.act(delta);
            if(!centerAura.isInRange(owner)) dispell();
        }
    }

    public static class P extends AbilityPrototype{
        private int range;
        private float duration;


        public P(int range, float duration) {
            super("frozenAura", false, ISpawn.class);
            this.range = range;
            this.duration = duration;
        }

        @Override
        public MobAbility getAbility() {
            return new FrozenAura(this);
        }

        @Override
        public String getTooltip() {
            return "";
        }
    }

    private FrozenAuraBuff buff;

    public FrozenAura(P prototype) {
        super(prototype);
        buff = new FrozenAuraBuff(prototype.range, prototype.duration);
    }

    @Override
    public void init() {
        buff.setOwner(owner);
    }

    @Override
    public void spawned() {
        owner.addEffect(buff);
    }
}
