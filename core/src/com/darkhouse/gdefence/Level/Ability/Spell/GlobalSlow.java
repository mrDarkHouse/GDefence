package com.darkhouse.gdefence.Level.Ability.Spell;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
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
        private AtomicReference<Float> duration;//Float
        private AtomicReference<Float> cooldownDown;
        private G g;

        public P(int energyCost, float cooldown, float slowPercent, float duration, final float cooldownDown, final G grader) {
            super(154, "globalSlow", energyCost, cooldown, grader.gemCap, Mob.class);
            this.cooldownDown = new AtomicReference<Float>(cooldownDown);
            this.slowPercent = new AtomicReference<Float>(slowPercent);
            this.duration = new AtomicReference<Float>(duration);
            this.g = grader;


            AssetLoader l = GDefence.getInstance().assetLoader;
            gemBoost[0] = new Ability.AbilityPrototype.BoostFloat(this.slowPercent, grader.slowPercentUp, l.getWord("globalSlowGrade1"),
                    true, Ability.AbilityPrototype.BoostFloat.FloatGradeFieldType.PERCENT);
            gemBoost[1] = new Ability.AbilityPrototype.BoostFloat(this.duration, grader.durationUp, l.getWord("globalSlowGrade2"),
                    true, BoostFloat.FloatGradeFieldType.TIME);
            gemBoost[2] = new BoostFloat(this.cooldownDown, grader.cooldownDown, l.getWord("globalSlowGrade3"),
                    false, BoostFloat.FloatGradeFieldType.TIME){
                @Override
                public String boostField() {
//                    return super.boostField();
                    return (getCooldown()) + "s";
                }

                @Override
                public String concate() {
//                    return super.concate();
                    return (getCooldown() - grader.cooldownDown) + "s";
                }

                @Override
                public void grade() {
//                    super.grade();
                    P.super.setCooldown(P.super.cooldown - grader.cooldownDown);
                }
            };
        }

        @Override
        public Spell createSpell() {
            return new GlobalSlow(this);
        }

        @Override
        protected String getChildTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("globalSlowTooltip1") + " " + System.getProperty("line.separator") +
                    l.getWord("globalSlowTooltip2") + " " + FontLoader.colorString(Integer.toString((int)(slowPercent.get()*100)) + "%", 10) +
                    System.getProperty("line.separator") + l.getWord("globalSlowTooltip3") + " " +
                    FontLoader.colorString(duration + "", 11) + " " + l.getWord("globalSlowTooltip4");
        }

        @Override
        public Array<Class<? extends Ability.AbilityPrototype>> getAbilitiesToSaveOnCraft() {
            return null;
        }

        @Override
        public String getSaveCode() {
            return super.getSaveCode() + ";" + slowPercent + ";" + duration + ";" + cooldownDown + ";" + g.slowPercentUp + ";" + g.durationUp + ";" + g.cooldownDown;
        }

        @Override
        public Ability.AbilityPrototype copy() {
            return null;
//            AssetLoader l = GDefence.getInstance().assetLoader;
//            P p = new P(energyCost, cooldown, slowPercent.get(), duration.get(), g);
////            p.gemBoost[0] = new BoostFloat();
//            p.gemBoost[0] = new Ability.AbilityPrototype.BoostFloat(this.slowPercent, g.slowPercentUp, l.getWord("globalSlowGrade1"),
//                    true, Ability.AbilityPrototype.BoostFloat.FloatGradeFieldType.PERCENT);
//            p.gemBoost[1] = new Ability.AbilityPrototype.BoostInteger(this.duration, g.durationUp, l.getWord("globalSlowGrade2"),
//                    true, Ability.AbilityPrototype.BoostInteger.IntegerGradeFieldType.NONE);
//            return p;
        }


        @Override
        public Item getPrototype() {
            return ItemEnum.Spell.GlobalSlow;
        }

        @Override
        public int[] exp2nextLevel() {
                return new int[]{150, 350, 500, 800, 1000, 1400, 2000};
        }
    }
    public static class G extends Ability.AbilityGrader{
        private float slowPercentUp;
        private float durationUp;
        private float cooldownDown;

        public G(float slowPercentUp, float durationUp, float cooldownDown, int[] gemCap) {
            super(gemCap);
            this.slowPercentUp = slowPercentUp;
            this.durationUp = durationUp;
            this.cooldownDown = cooldownDown;
        }
    }



    public class GlobalSlowEffect extends Effect<Mob>{
        private float slowPercent;
        private float changeSpeed;

        public GlobalSlowEffect(float slowPercent, float duration) {
            super(false, true, duration, "slow");
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

        @Override
        public void act(float delta) {
            super.act(delta);
            getPrototype().addExp(slowPercent/10);
//            System.out.println(getPrototype().getTotalExp());
        }
    }



//    private G grader;
    private float slowPercent;
    private float duration;



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
