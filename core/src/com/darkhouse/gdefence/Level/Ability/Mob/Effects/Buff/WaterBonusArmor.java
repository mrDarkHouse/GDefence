package com.darkhouse.gdefence.Level.Ability.Mob.Effects.Buff;

import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;

public class WaterBonusArmor extends Effect {
    private int bonusArmor;

    public WaterBonusArmor(float duration, int armor) {
        super(true, false, false, duration, "waterBonusArmor");
        this.bonusArmor = armor;
    }

    @Override
    public void apply() {
        owner.changeArmor(bonusArmor);
    }

    @Override
    public void dispell() {
        owner.changeArmor(-bonusArmor);
        owner.deleteEffect(this.getClass());
    }
}
