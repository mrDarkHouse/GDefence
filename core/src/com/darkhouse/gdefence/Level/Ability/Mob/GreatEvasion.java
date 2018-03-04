package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.DamageType;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Mob.Modifiers.Evasion;
import com.darkhouse.gdefence.Level.Ability.Tools.Cooldown;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Objects.DamageSource;

public class GreatEvasion extends MobAbility implements MobAbility.ISpawn{

    private static class GreatEvasionBuff extends Effect<Mob> implements IGetDmg, IListener{
        private Evasion evasion;

        public GreatEvasionBuff(float cdCap) {
            super(true, false, -1, "greatEvasion", IGetDmg.class);
            evasion = new Evasion(1);
            setCooldownable(new Cooldown(cdCap));
        }

        @Override
        public void apply() {

        }

        @Override
        public float getDmg(DamageSource source, DamageType type, float dmg) {
            if (getCooldownObject().isReady() && type == DamageType.Physic && evasion.getDmg(source, type,  dmg) == 0){
                getCooldownObject().resetCooldown();
                return 0;
            }else return dmg;
        }

//        @Override
//        public void act(float delta) {
//            //super.act(delta);
//            getCooldownObject().act(delta);
//        }
    }
    public static class P extends AbilityPrototype {
        private float cdCap;

        public P(float cdCap) {
            super("greatEvasion", false, ISpawn.class);
            this.cdCap = cdCap;

        }
        public MobAbility getAbility(){
            return new GreatEvasion(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
             return l.getWord("greatEvasionTooltip1") + System.getProperty("line.separator") +
                     l.getWord("greatEvasionTooltip2") + " " + FontLoader.colorString(Float.toString(cdCap), 3) + " " +
                     l.getWord("greatEvasionTooltip3");
        }
    }

    private GreatEvasionBuff buff;

    public GreatEvasion(P prototype) {
        super(prototype);
//        super("Great Evasion", false);
        buff = new GreatEvasionBuff(prototype.cdCap);
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
