package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Buff.GreatEvasionBuff;

public class GreatEvasion extends MobAbility implements MobAbility.ISpawn{
//    private Evasion evasion;
//    private Cooldown cooldown;
    private GreatEvasionBuff buff;
//    private float cdCap;

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


//    public GreatEvasion(float cdCap) {
//        super("Great Evasion", false);
////        this.cdCap = cdCap;
////        buff = new GreatEvasionBuff(cdCap);
////        cooldown = new Cooldown(cdCap);
//    }

    public GreatEvasion(P prototype) {
//        super("Great Evasion", false);
        buff = new GreatEvasionBuff(prototype.cdCap);
    }

//    @Override
//    public String getTooltip() {
//        return "Block any dmg when attacked" + System.getProperty("line.separator") +
//                "every [#64A619ff]" + buff.getCooldownObject().getCdCap() + "" + "[] seconds";
//    }

    @Override
    public void init() {
//        evasion = new Evasion(1);
        buff.setOwner(owner);
    }

    @Override
    public void spawned() {
        owner.addEffect(buff);
    }

    //    @Override
//    public float getDmg(Tower source, float dmg) {
//        if (buff.getCooldownObject().isReady() && buff.getDmg(source, dmg) == 0){
//            buff.getCooldownObject().resetCooldown();
//            return 0;
//        }else return dmg;
//    }
}
