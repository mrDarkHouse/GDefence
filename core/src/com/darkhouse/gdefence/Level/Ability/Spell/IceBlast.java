package com.darkhouse.gdefence.Level.Ability.Spell;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.InventorySystem.inventory.Item;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Model.Effectable;
import com.darkhouse.gdefence.Objects.SpellObject;

import java.util.concurrent.atomic.AtomicReference;

public class IceBlast extends Spell/* implements Spell.IAoe*/{

    public static class P extends SpellObject implements IAoe{
        private AtomicReference<Integer> dmg;
        private AtomicReference<Float> slowPercent;
        private AtomicReference<Float> duration;

        private AtomicReference<Integer> aoe;//feature aoe grade

        public P(int energyCost, int cooldown, int dmg, float slowPercent, float duration, int aoe, G grader) {
            super("Ice blast", "globalSlow", energyCost, cooldown, grader.gemCap, Mob.class);
            this.dmg = new AtomicReference<Integer>(dmg);
            this.slowPercent = new AtomicReference<Float>(slowPercent);
            this.duration = new AtomicReference<Float>(duration);
            this.aoe = new AtomicReference<Integer>(aoe);

            gemBoost[0] = new BoostInteger(this.dmg, grader.dmgUp, "Blast damage",
                    true, BoostInteger.IntegerGradeFieldType.NONE);
            gemBoost[1] = new BoostFloat(this.slowPercent, grader.slowPercentUp, "Slow percent",
                    true, BoostFloat.FloatGradeFieldType.PERCENT);
            gemBoost[2] = new BoostFloat(this.duration, grader.durationUp, "Slow duration",
                    true, BoostFloat.FloatGradeFieldType.TIME);
        }

        @Override
        protected String getChildTooltip() {
            return "Damage targeted mobs by " + dmg + System.getProperty("line.separator") +
                    "and slow them by " + (int)(slowPercent.get()*100) + "%" + System.getProperty("line.separator") +
                    "for " + duration.get().intValue() + " seconds";
        }

        @Override
        public Spell createSpell() {
            return new IceBlast(this);
        }

        @Override
        public Item getPrototype() {
            return ItemEnum.Spell.IceBlast;
        }

        @Override
        public int getAoe() {
            return aoe.get();
        }
    }
    public static class G extends Ability.AbilityGrader{
        private int dmgUp;
        private float slowPercentUp;
        private float durationUp;

        public G(int dmgUp, float slowPercentUp, float durationUp, int[] gemCap) {
            super(gemCap);
            this.dmgUp = dmgUp;
            this.slowPercentUp = slowPercentUp;
            this.durationUp = durationUp;
        }
    }

    public class IceBlastEffect extends Effect<Mob> {
        private float slowPercent;
        private float changeSpeed;

        public IceBlastEffect(float slowPercent, float duration) {
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

//    private int aoe;
    private int dmg;
    private float slowPercent;
    private float duration;



    public IceBlast(P prototype) {
        super(prototype);
//        this.aoe = prototype.aoe;
        this.dmg = prototype.dmg.get();
        this.slowPercent = prototype.slowPercent.get();
        this.duration = prototype.duration.get();
    }

//    @Override
//    public int getAoe() {
//        return aoe;
//    }

    @Override
    public void use(Array<? extends Effectable> targets) {
        for (Effectable m:targets){
            hitMob(((Mob) m), dmg);
            m.addEffect(new IceBlastEffect(slowPercent, duration).setOwner(((Mob) m)));
        }
    }


}
