package com.darkhouse.gdefence.Level.Ability.Spell;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.Item;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Model.Effectable;
import com.darkhouse.gdefence.Objects.SpellObject;
import com.darkhouse.gdefence.Screens.LevelMap;
import com.darkhouse.gdefence.User;

import java.util.concurrent.atomic.AtomicReference;

public class EmergencyRepair extends Spell{

    public static class P extends SpellObject implements Spell.INonTarget {
        private AtomicReference<Integer> healAmount;
        private G g;
        private S s;

        public P(int energyCost, float cooldown, int healAmount, G grader) {
            super(152, "emergencyRepair", energyCost, cooldown, grader.gemCap);
            this.healAmount = new AtomicReference<Integer>(healAmount);
            this.g = grader;
            this.s = new S(healAmount);

            AssetLoader l = GDefence.getInstance().assetLoader;
            initBoosts(l);
//            gemBoost[0] = new Ability.AbilityPrototype.BoostInteger(this.healAmount, grader.healAmountUp, l.getWord("emergencyRepairGrade1"),
//                    true, Ability.AbilityPrototype.BoostInteger.IntegerGradeFieldType.NONE);
        }
        @Override
        public void flush() {
            this.healAmount = new AtomicReference<Integer>(s.healAmount);
            initBoosts(GDefence.getInstance().assetLoader);
        }

        @Override
        protected void initBoosts(AssetLoader l) {
            gemBoost[0] = new Ability.AbilityPrototype.BoostInteger(this.healAmount, g.healAmountUp, l.getWord("emergencyRepairGrade1"),
                    true, Ability.AbilityPrototype.BoostInteger.IntegerGradeFieldType.NONE);
        }

        @Override
        protected String getChildTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("emergencyRepairTooltip1") + " " + FontLoader.colorString(healAmount.get().toString(), User.GEM_TYPE.BLACK) + " " + l.getWord("emergencyRepairTooltip2");
        }

        @Override
        public Array<Class<? extends Ability.AbilityPrototype>> getAbilitiesToSaveOnCraft() {
            return null;
        }

//        @Override
//        public String getSaveCode() {
//            return super.getSaveCode() + ";" + healAmount + ";" + g.healAmountUp;
//        }

        @Override
        public Spell createSpell() {
            return new EmergencyRepair(this);
        }

        @Override
        public Ability.AbilityPrototype copy() {
//            AssetLoader l = GDefence.getInstance().assetLoader;
//            P p = new P(energyCost, cooldown, healAmount.get(), g);
//            p.gemBoost[0] = new Ability.AbilityPrototype.BoostInteger(this.healAmount, g.healAmountUp, l.getWord("emergencyRepairGrade1"),
//                    true, Ability.AbilityPrototype.BoostInteger.IntegerGradeFieldType.NONE);
//            return p;
            return null;
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
    private static class S extends Ability.AbilitySaverStat{
        private int healAmount;

        public S(int healAmount) {
            this.healAmount = healAmount;
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
