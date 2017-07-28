package com.darkhouse.gdefence.Level.Ability.Spell;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.InventorySystem.inventory.Item;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Wave;
import com.darkhouse.gdefence.Model.Effectable;
import com.darkhouse.gdefence.Objects.SpellObject;

import java.util.concurrent.atomic.AtomicReference;

public class GlobalSlow extends Spell{

    public static class P extends SpellObject implements Spell.INonTarget{
        private AtomicReference<Float> slowPercent;
        private AtomicReference<Integer> duration;//Float

        public P(int energyCost, int cooldown, float slowPercent, int duration, G grader) {
            super("Global Slow", "globalSlow", energyCost, cooldown, grader.gemCap, Mob.class);
            this.slowPercent = new AtomicReference<Float>(slowPercent);
            this.duration = new AtomicReference<Integer>(duration);


            gemBoost[0] = new Ability.AbilityPrototype.BoostFloat(this.slowPercent, grader.slowPercentUp, "Slow Percent",
                    true, Ability.AbilityPrototype.BoostFloat.FloatGradeFieldType.PERCENT);
            gemBoost[1] = new Ability.AbilityPrototype.BoostInteger(this.duration, grader.durationUp, "Slow duration",
                    true, Ability.AbilityPrototype.BoostInteger.IntegerGradeFieldType.NONE);
        }

        @Override
        public Spell createSpell() {
            return new GlobalSlow(this);
        }

        @Override
        protected String getChildTooltip() {
            return "Slows all mobs on map by " + (int)(slowPercent.get()*100) + "%" + System.getProperty("line.separator") +
                    "for " + duration + " seconds";
        }

        @Override
        public Item getPrototype() {
            return ItemEnum.Spell.GlobalSlow;
        }
    }
    public static class G extends Ability.AbilityGrader{
        private float slowPercentUp;
        private int durationUp;

        public G(float slowPercentUp, int durationUp, int[] gemMax) {
            super(gemMax);
            this.slowPercentUp = slowPercentUp;
            this.durationUp = durationUp;
        }
    }



    public class GlobalSlowEffect extends Effect<Mob>{
        private float slowPercent;
        private float changeSpeed;

        public GlobalSlowEffect(float slowPercent, float duration) {
            super(false, true, duration, "bash");
            this.slowPercent = slowPercent;
        }

        public void apply(){
            changeSpeed = owner.getSpeed()*slowPercent;
            owner.changeSpeed(-changeSpeed);
        }

        @Override
        public void dispell() {
            owner.changeSpeed(changeSpeed);
            super.dispell();
        }
    }



//    private G grader;
    private float slowPercent;
    private int duration;



    public GlobalSlow(P prototype) {
        super(prototype);
        this.slowPercent = prototype.slowPercent.get();
        this.duration = prototype.duration.get();

    }

    @Override
    public void use(Array<? extends Effectable> targets) {
        for (Effectable m:targets){
            m.addEffect(new GlobalSlowEffect(slowPercent, duration).setOwner(((Mob) m)));
        }
    }
}
