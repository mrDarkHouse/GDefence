package com.darkhouse.gdefence.Level.Ability.Mob.Effects.Buff;


import com.darkhouse.gdefence.Level.Ability.Mob.Tools.Cooldown;
import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Effect;
import com.darkhouse.gdefence.Level.Ability.Mob.MobAbility;
import com.darkhouse.gdefence.Level.Ability.Mob.Modifiers.Evasion;
import com.darkhouse.gdefence.Level.Tower.Tower;

public class GreatEvasionBuff extends Effect implements MobAbility.IGetDmg{
    private Evasion evasion;
//    private Cooldown cooldown;
//
//    public Cooldown getCooldownObject() {
//        return cooldown;
//    }

    public GreatEvasionBuff(float cdCap) {
        super(true, false, false, -1, "slow");
        evasion = new Evasion(1);
        setCooldownable(new Cooldown(cdCap));
//        cooldown = ;
    }


    @Override
    public void apply() {

    }

    @Override
    public void dispell() {

    }

    @Override
    public float getDmg(Tower source, float dmg) {
        if (getCooldownObject().isReady() && evasion.getDmg(source, dmg) == 0){
            getCooldownObject().resetCooldown();
            return 0;
        }else return dmg;
    }

    @Override
    public void act(float delta) {
        //super.act(delta);
        getCooldownObject().act(delta);
    }
}
