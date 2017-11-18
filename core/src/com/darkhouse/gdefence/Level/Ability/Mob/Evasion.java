package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.Cooldown;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Objects.DamageSource;

public class Evasion extends MobAbility implements MobAbility.ISpawn{

    private static class EvasionBuff extends Effect<Mob> implements IGetDmg{
        private com.darkhouse.gdefence.Level.Ability.Mob.Modifiers.Evasion evasion;

        public EvasionBuff(float chance) {
            super(true, false, -1);
            evasion = new com.darkhouse.gdefence.Level.Ability.Mob.Modifiers.Evasion(chance);
        }

        @Override
        public void apply() {

        }

        @Override
        public float getDmg(DamageSource source, float dmg) {
            return evasion.getDmg(source, dmg);
        }

//        @Override
//        public void act(float delta) {
//            //super.act(delta);
//            getCooldownObject().act(delta);
//        }
    }
    public static class P extends AbilityPrototype {
        private float chance;

        public P(float chance) {
            super("greatEvasion", false);
            this.chance = chance;

        }
        public MobAbility getAbility(){
            return new Evasion(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return "";
//            return l.getWord("greatEvasionTooltip1") + System.getProperty("line.separator") +
//                    l.getWord("greatEvasionTooltip2") + " " + FontLoader.colorString(Float.toString(cdCap), 3) + " " +
//                    l.getWord("greatEvasionTooltip3");
        }
    }

    private EvasionBuff buff;

    public Evasion(Evasion.P prototype) {
//        super("Great Evasion", false);
        buff = new EvasionBuff(prototype.chance);
    }

    @Override
    public void init() {
//        evasion = new Evasion(1);
        buff.setOwner(owner);
    }

    @Override
    public void spawned() {
        owner.addEffect(buff);
    }

}
