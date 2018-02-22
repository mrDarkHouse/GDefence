package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.Cooldown;
import com.darkhouse.gdefence.Level.Ability.Tools.DamageType;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Objects.DamageSource;

public class Evasion extends MobAbility implements MobAbility.ISpawn{

    private static class EvasionBuff extends Effect<Mob> implements IGetDmg{
        private com.darkhouse.gdefence.Level.Ability.Mob.Modifiers.Evasion evasion;

        public EvasionBuff(float chance) {
            super(true, false, -1, "greatEvasion");
            evasion = new com.darkhouse.gdefence.Level.Ability.Mob.Modifiers.Evasion(chance);
        }

        @Override
        public void apply() {

        }

        @Override
        public float getDmg(DamageSource source, DamageType type, float dmg) {
            if(type == DamageType.Physic) {
                return evasion.getDmg(source, type, dmg);
            }else return dmg;
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
            super("evasion", false);
            this.chance = chance;

        }
        public MobAbility getAbility(){
            return new Evasion(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("evasionTooltip1") + " " + FontLoader.colorString((chance*100 + "%"), 3) +
                    System.getProperty("line.separator") + l.getWord("evasionTooltip2");
//            return l.getWord("greatEvasionTooltip1") + System.getProperty("line.separator") +
//                    l.getWord("greatEvasionTooltip2") + " " + FontLoader.colorString(Float.toString(cdCap), 3) + " " +
//                    l.getWord("greatEvasionTooltip3");
        }
    }

    private EvasionBuff buff;

    public Evasion(Evasion.P prototype) {
        super(prototype);
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
