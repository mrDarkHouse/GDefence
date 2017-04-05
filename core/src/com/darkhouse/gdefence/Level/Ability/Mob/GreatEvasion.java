package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Tower.Tower;

public class GreatEvasion extends MobAbility implements MobAbility.IGetDmg, MobAbility.ICooldown{
    private float cooldown;


    public GreatEvasion() {
        super("Great Evasion", false);
    }

    @Override
    public String getTooltip() {
        return "Block any dmg when attacked" + System.getProperty("line.separator") +
                "every [#64A619ff]" + cooldown + "[] seconds";
    }

    @Override
    public void init() {

    }

    @Override
    public float getDmg(Tower source, float dmg) {
        return 0;
    }
}
