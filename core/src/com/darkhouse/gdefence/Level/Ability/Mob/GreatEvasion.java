package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Mob.Modifiers.Evasion;
import com.darkhouse.gdefence.Level.Ability.Tools.Cooldown;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Tower;

public class GreatEvasion extends MobAbility implements MobAbility.ISpawn{

    private static class GreatEvasionBuff extends Effect<Mob> implements IGetDmg{
        private Evasion evasion;

        public GreatEvasionBuff(float cdCap) {
            super(true, false, -1, "slow");
            evasion = new Evasion(1);
            setCooldownable(new Cooldown(cdCap));
        }

        @Override
        public void apply() {

        }

        @Override
        public float getDmg(Tower source, float dmg) {
            if (getCooldownObject().isReady() && evasion.getDmg(source, dmg) == 0){
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
    public static class P extends AblityPrototype{
        private float cdCap;

        public P(float cdCap) {
            super("Great Evasion", false);
            this.cdCap = cdCap;

        }
        public MobAbility getAbility(){
            return new GreatEvasion(this);
        }

        @Override
        public String getTooltip() {
             return "Block any dmg when attacked" + System.getProperty("line.separator") +
                    "every [#64A619ff]" + cdCap + "" + "[] seconds";
        }
    }

    private GreatEvasionBuff buff;

    public GreatEvasion(P prototype) {
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
