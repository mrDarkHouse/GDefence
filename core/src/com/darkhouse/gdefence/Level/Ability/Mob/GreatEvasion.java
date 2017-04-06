package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Buff.GreatEvasionBuff;
import com.darkhouse.gdefence.Level.Ability.Mob.Modifiers.Evasion;
import com.darkhouse.gdefence.Level.Tower.Tower;

public class GreatEvasion extends MobAbility implements MobAbility.IGetDmg{
//    private Evasion evasion;
//    private Cooldown cooldown;
    private GreatEvasionBuff buff;
    private float cdCap;

    public GreatEvasion(float cdCap) {
        super("Great Evasion", false);
        this.cdCap = cdCap;
//        cooldown = new Cooldown(cdCap);
    }

    @Override
    public String getTooltip() {
        return "Block any dmg when attacked" + System.getProperty("line.separator") +
                "every [#64A619ff]" + buff.getCooldown().getCdCap() + "[] seconds";
    }

    @Override
    public void init() {
//        evasion = new Evasion(1);
        buff = new GreatEvasionBuff(owner, cdCap);
    }

    @Override
    public float getDmg(Tower source, float dmg) {
        if (buff.getCooldown().isReady() && buff.getDmg(source, dmg) == 0){
            buff.getCooldown().resetCooldown();
            return 0;
        }else return dmg;
    }
}
