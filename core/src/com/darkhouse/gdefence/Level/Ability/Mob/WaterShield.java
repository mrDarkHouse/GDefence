package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Mob.Modifiers.PseudoRandom;
import com.darkhouse.gdefence.Level.Ability.Tools.DamageType;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Objects.DamageSource;

import java.util.Random;

public class WaterShield extends MobAbility implements MobAbility.IGetDmg{

    private class WaterShieldEffect extends Effect<Mob> implements IGetDmg{
        private float dmgReduction;

        private WaterShieldEffect(float duration, float dmgReduction) {
            super(true, true, duration, "waterShield");
            this.dmgReduction = dmgReduction;
        }

        @Override
        public void apply() {

        }

        @Override
        public float getDmg(DamageSource source, DamageType type, float dmg) {
//            if(type == DamageType.Physic) {
                return dmg * (1 - dmgReduction);
//            }
        }
    }
    public static class P extends AbilityPrototype{
        private float chance;
        private float dmgAbsorb;
        private float duration;

        public P(float chance, float dmgAbsorb, float duration) {
            super("waterShield", false, IGetDmg.class);
            this.chance = chance;
            this.dmgAbsorb = dmgAbsorb;
            this.duration = duration;
        }

        @Override
        public MobAbility getAbility() {
            return new WaterShield(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("waterShieldTooltip1") + " " + FontLoader.colorString(Float.toString(chance*100), 10) + "% " +
                    l.getWord("waterShieldTooltip2") + " " + System.getProperty("line.separator") +
                    l.getWord("waterShieldTooltip3") + " " + FontLoader.colorString(Float.toString(dmgAbsorb), 10) + " " +
                    l.getWord("waterShieldTooltip4") + " " + FontLoader.colorString(Float.toString(duration), 10) + " " +
                    l.getWord("waterShieldTooltip5");
        }
    }
//    private boolean active;
    private PseudoRandom r;
    private float duration;
    private float dmgAbsorb;

    public WaterShield(P prototype) {
        super(prototype);
        this.duration = prototype.duration;
        this.dmgAbsorb = prototype.dmgAbsorb;
        r = new PseudoRandom(prototype.chance);
    }

    @Override
    public void init() {

    }

    @Override
    public float getDmg(DamageSource source, DamageType type, float dmg) {
        if (owner.haveEffect(WaterShield.class)) return dmg;//dont refresh effect
        if (type == DamageType.Physic) {
            if (r.proc()) owner.addEffect(new WaterShieldEffect(duration, dmgAbsorb).setOwner(owner));
        }
        return dmg;
    }
}
