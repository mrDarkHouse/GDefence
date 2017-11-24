package com.darkhouse.gdefence.Level.Ability.Spell;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.Item;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Model.Effectable;
import com.darkhouse.gdefence.Objects.SpellObject;

import java.util.concurrent.atomic.AtomicReference;

public class SuddenDeath extends Spell{

    public static class P extends SpellObject implements Spell.ITarget {

        public P(int energyCost, int cooldown) {
            super(20, "suddenDeath", energyCost, cooldown, new int[]{0, 0, 0}, Mob.class);
        }

        @Override
        protected String getChildTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("suddenDeathTooltip1") + System.getProperty("line.separator") +
                   l.getWord("suddenDeathTooltip2");
        }

        @Override
        public Spell createSpell() {
            return new SuddenDeath(this);
        }

        @Override
        public Ability.AbilityPrototype copy() {
            P p = new P(energyCost, cooldown);
            //p.gemsBoost[0]
            return p;
        }

        @Override
        public int[] exp2nextLevel() {
            return new int[]{20, 30, 40, 50, 60};
        }

        @Override
        public Item getPrototype() {
            return ItemEnum.Spell.SuddenDeath;
        }
    }


    public SuddenDeath(SpellObject prototype) {
        super(prototype);
    }

    @Override
    public void use(Array<? extends Effectable> targets) {
        Mob m = ((Mob) targets.get(0));

        float dmg = m.hit(9000, this);//infinity max hp value
        if(dmg != 0) {
            addKill(m);
//            getPrototype().addExp((float)m.getMaxHealth()/10);For what, it non gradable
        }
    }
}
