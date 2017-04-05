package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Ability.Mob.Modifiers.Evasion;
import com.darkhouse.gdefence.Level.Tower.Tower;

public class GreatEvasion extends MobAbility implements MobAbility.IGetDmg{
    private Evasion evasion;
    private Cooldown cooldown;

    public GreatEvasion(float cdCap) {
        super("Great Evasion", false);
        cooldown = new Cooldown(cdCap);
    }

    @Override
    public String getTooltip() {
        return "Block any dmg when attacked" + System.getProperty("line.separator") +
                "every [#64A619ff]" + cooldown.getCdCap() + "[] seconds";
    }

    @Override
    public void init() {
        evasion = new Evasion(1);
    }

    @Override
    public float getDmg(Tower source, float dmg) {
        if (cooldown.isReady() && evasion.getDmg(source, dmg) == 0){
            cooldown.resetCooldown();
            return 0;
        }else return dmg;
    }
}
