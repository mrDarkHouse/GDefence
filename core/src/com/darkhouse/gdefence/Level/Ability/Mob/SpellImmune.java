package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.DamageType;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Objects.DamageSource;

public class SpellImmune extends MobAbility implements MobAbility.ISpawn{

    public static class P extends AbilityPrototype{

        public P() {
            super("spellImmune", false, ISpawn.class);
        }

        @Override
        public MobAbility getAbility() {
            return new SpellImmune(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("spellImmuneTooltip1");
        }
    }
    public static class SpellImmuneBuff extends Effect<Mob> implements IGetDmg{

        public SpellImmuneBuff() {
            super(true, false, -1, "spellImmune", IGetDmg.class);
        }

        @Override
        public void apply() {

        }

        @Override
        public float getDmg(DamageSource source, DamageType type, float dmg) {
            if(type == DamageType.Magic) return 0;
            else return dmg;
        }

    }

    private SpellImmuneBuff buff;

    public SpellImmune(P prototype) {
        super(prototype);
        buff = new SpellImmuneBuff();
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
