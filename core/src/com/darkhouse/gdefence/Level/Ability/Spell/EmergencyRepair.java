package com.darkhouse.gdefence.Level.Ability.Spell;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.Item;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Model.Effectable;
import com.darkhouse.gdefence.Objects.SpellObject;
import com.darkhouse.gdefence.Screens.LevelMap;

import java.util.concurrent.atomic.AtomicReference;

public class EmergencyRepair extends Spell{

    public static class P extends SpellObject implements Spell.INonTarget {
        private AtomicReference<Integer> healAmount;
        private G g;

        public P(int energyCost, float cooldown, int healAmount, G grader) {
            super(22, "emergencyRepair", energyCost, cooldown, grader.gemCap);
            this.healAmount = new AtomicReference<Integer>(healAmount);
            this.g = grader;

            AssetLoader l = GDefence.getInstance().assetLoader;
            gemBoost[0] = new Ability.AbilityPrototype.BoostInteger(this.healAmount, grader.healAmountUp, l.getWord("emergencyRepairGrade1"),
                    true, Ability.AbilityPrototype.BoostInteger.IntegerGradeFieldType.NONE);
        }

        @Override
        protected String getChildTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("emergencyRepairTooltip1") + " " + healAmount.get() + " " + l.getWord("emergencyRepairTooltip2");
        }

        @Override
        public Array<Class<? extends Ability.AbilityPrototype>> getAbilitiesToSaveOnCraft() {
            return null;
        }

        @Override
        public String getSaveCode() {
            return super.getSaveCode() + ";" + healAmount + ";" + g.healAmountUp;
        }

        @Override
        public Spell createSpell() {
            return new EmergencyRepair(this);
        }

        @Override
        public Ability.AbilityPrototype copy() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            P p = new P(energyCost, cooldown, healAmount.get(), g);
            p.gemBoost[0] = new Ability.AbilityPrototype.BoostInteger(this.healAmount, g.healAmountUp, l.getWord("emergencyRepairGrade1"),
                    true, Ability.AbilityPrototype.BoostInteger.IntegerGradeFieldType.NONE);
            return p;
        }

        @Override
        public int[] exp2nextLevel() {
            return new int[]{20, 30, 40, 50, 60};
        }

        @Override
        public Item getPrototype() {
            return ItemEnum.Spell.EmergencyRepair;
        }
    }
    public static class G extends Ability.AbilityGrader{
        private int healAmountUp;

        public G(int healAmountUp, int[] gemMax) {
            super(gemMax);
            this.healAmountUp = healAmountUp;
        }
    }



    private int healEmount;

    public EmergencyRepair(P prototype) {
        super(prototype);
        this.healEmount = prototype.healAmount.get();
    }
    @Override
    public void use(Array<? extends Effectable> targets) {
        LevelMap.getLevel().heal(healEmount);
    }

}
