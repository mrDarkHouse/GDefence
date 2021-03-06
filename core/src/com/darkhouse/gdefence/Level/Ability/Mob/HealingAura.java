package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Tools.Aura;
import com.darkhouse.gdefence.Level.Ability.Tools.Cooldown;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Wave;

public class HealingAura extends MobAbility implements MobAbility.ISpawn{

    private class HealingAuraBuff extends Effect<Mob> implements IListener{
//        private int range;
        private int healEmount;

        public HealingAuraBuff(int range, float healDelay, int healEmount) {
            super(true, false, -1);
            setAura(new Aura(range));
            setCooldownable(new Cooldown(healDelay));
//            this.range = range;
            this.healEmount = healEmount;
        }

        @Override
        public Effect setOwner(Mob owner) {
            super.setOwner(owner);
            getAuraObject().init(owner);
            return this;
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
                    m.addEffect(new HealingAuraHeal(getAuraObject()).setOwner(m));//if m !haveEffect already in addEffect
                }
            }

            if(getCooldownObject().isReady()){
                for (int i = 0; i < Wave.mobs.size; i++){
                    Mob m = Wave.mobs.get(i);
                    if(m.haveEffect(HealingAuraHeal.class)) m.heal(healEmount);
                }
                getCooldownObject().resetCooldown();
            }
        }

        @Override
        public void apply() {
            getCooldownObject().resetCooldown();
//            getAuraObject().init(owner, range);//may work in this place
        }
    }
    private class HealingAuraHeal extends Effect<Mob>{
        private Aura centerAura;


        public HealingAuraHeal(Aura aura) {
            super(true, false, -1, "heal");
            this.centerAura = aura;
        }

        @Override
        public void apply() {

        }

        @Override
        public void act(float delta) {
//            super.act(delta);
            if(!centerAura.isInRange(owner)) dispell();
        }
    }
    public static class P extends AbilityPrototype {
        private int range;
        private float healDelay;
        private int healEmount;

        public P(int range, float healDelay, int healEmount) {
            super("healingAura", false, ISpawn.class);
            this.range = range;
            this.healDelay = healDelay;
            this.healEmount = healEmount;
        }
        public MobAbility getAbility(){
            return new HealingAura(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("healingAuraTooltip1") + " " + FontLoader.colorString(Integer.toString(healEmount), 10) + " " +
                    l.getWord("healingAuraTooltip2") + " " + FontLoader.colorString(Integer.toString(range), 10) + " " +
                    l.getWord("healingAuraTooltip3") + System.getProperty("line.separator") +
                    l.getWord("healingAuraTooltip4") + " " + FontLoader.colorString(Float.toString(healDelay), 10) + " " +
                    l.getWord("healingAuraTooltip5");
        }
    }

    private HealingAuraBuff buff;

    public HealingAura(P prototype) {
        super(prototype);
        buff = new HealingAuraBuff(prototype.range, prototype.healDelay, prototype.healEmount);
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
