package com.darkhouse.gdefence.Level.Ability.Mob.Effects.Buff;


import com.darkhouse.gdefence.Level.Ability.Mob.Cooldown;
import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Effect;
import com.darkhouse.gdefence.Level.Ability.Mob.MobAbility;
import com.darkhouse.gdefence.Level.Ability.Mob.Modifiers.Evasion;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Tower;

public class GreatEvasionBuff extends Effect implements MobAbility.IGetDmg{
    private Evasion evasion;
    private Cooldown cooldown;

    public Cooldown getCooldown() {
        return cooldown;
    }

    public GreatEvasionBuff(Mob owner, float cdCap) {
        super(true, false, false, owner, -1, "slow");
        evasion = new Evasion(1);
        cooldown = new Cooldown(cdCap);
    }

    @Override
    public void apply() {

    }

    @Override
    public void dispell() {

    }

    @Override
    public float getDmg(Tower source, float dmg) {
        return 0;
    }

    @Override
    public void act(float delta) {
        //super.act(delta);
        cooldown.act(delta);
    }
}
