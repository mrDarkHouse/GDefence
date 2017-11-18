package com.darkhouse.gdefence.Level.Ability.Tower;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.Level.Ability.Mob.HealingAura;
import com.darkhouse.gdefence.Level.Ability.Tools.Aura;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Level.Wave;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.Screens.LevelMap;

public class SteamAura extends Ability {


    private class SteamAuraAura extends Effect<Tower>{

        public SteamAuraAura(int range, float duration) {
            super(true, true, duration, "slow");
            setAura(new Aura(range));
        }

        @Override
        public Effect setOwner(Tower owner) {
            super.setOwner(owner);
            getAuraObject().init(owner);
            return this;
        }

        @Override
        public void apply() {
            Array<Tower> ts = Level.getMap().getTowersInRange(owner.getCenter(), getAuraObject().getRangeCircle().radius);
            for (int i = 0; i < ts.size; i++){
                ts.get(i).addEffect(new SteamAuraBuff(-1, owner.getDmg(), owner.getSpeed()).setOwner(ts.get(i)));
            }
        }
    }

    private class SteamAuraBuff extends Effect<Tower>{
        private int dmg;
        private int as;

        public SteamAuraBuff(float duration, int dmg, int as) {
            super(true, false, duration, "swimSpeed");
            this.dmg = dmg;
            this.as = as;
        }

        @Override
        public void apply() {
            owner.changeDmg(dmg);
            owner.changeAttackSpeed(as);
        }

        @Override
        public void dispell() {
            super.dispell();
            owner.changeDmg(-dmg);
            owner.changeAttackSpeed(-as);
        }
    }







    @Override
    protected void init() {

    }
}
