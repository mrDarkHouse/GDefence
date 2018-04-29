package com.darkhouse.gdefence.Level.Ability.Spell;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.Item;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Level.Ability.Tools.DamageType;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Model.Effectable;
import com.darkhouse.gdefence.Objects.SpellObject;
import com.darkhouse.gdefence.User;

import java.util.concurrent.atomic.AtomicReference;

public class IceBlast extends Spell/* implements Spell.IAoe*/{

    public static class P extends SpellObject implements IAoe{
        private AtomicReference<Integer> dmg;
        private AtomicReference<Float> slowPercent;
        private AtomicReference<Float> duration;

        private AtomicReference<Integer> aoe;//feature aoe grade

        private G g;
        private S s;

        public P(int energyCost, int cooldown, int dmg, float slowPercent, float duration, int aoe, G grader) {
            super(153, "iceBlast", energyCost, cooldown, grader.gemCap, Mob.class);
            this.dmg = new AtomicReference<Integer>(dmg);
            this.slowPercent = new AtomicReference<Float>(slowPercent);
            this.duration = new AtomicReference<Float>(duration);
            this.aoe = new AtomicReference<Integer>(aoe);
            this.g = grader;
            this.s = new S(dmg, slowPercent, duration);

            initBoosts(GDefence.getInstance().assetLoader);
        }

        @Override
        public void flush() {
            this.dmg = new AtomicReference<Integer>(s.dmg);
            this.slowPercent = new AtomicReference<Float>(s.slowPercent);
            this.duration = new AtomicReference<Float>(s.duration);
            initBoosts(GDefence.getInstance().assetLoader);
        }

        @Override
        protected void initBoosts(AssetLoader l) {
            gemBoost[0] = new BoostInteger(this.dmg, g.dmgUp, l.getWord("iceBlastGrade1"),
                    true, BoostInteger.IntegerGradeFieldType.NONE);
            gemBoost[1] = new BoostFloat(this.slowPercent, g.slowPercentUp, l.getWord("iceBlastGrade2"),
                    true, BoostFloat.FloatGradeFieldType.PERCENT);
            gemBoost[2] = new BoostFloat(this.duration, g.durationUp, l.getWord("iceBlastGrade3"),
                    true, BoostFloat.FloatGradeFieldType.TIME);
        }

        @Override
        protected String getChildTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("iceBlastTooltip1") + " " + FontLoader.colorString(dmg.get().toString(), User.GEM_TYPE.BLACK) + System.getProperty("line.separator") +
                    l.getWord("iceBlastTooltip2") + " " + FontLoader.colorString((int)(slowPercent.get()*100) + "%", User.GEM_TYPE.GREEN) + System.getProperty("line.separator") +
                    l.getWord("iceBlastTooltip3") + " " + FontLoader.colorString(duration.get().toString(), User.GEM_TYPE.WHITE) + " " + l.getWord("iceBlastTooltip4");
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

        @Override
        public int[] exp2nextLevel() {
            return new int[]{20, 30, 40, 50, 60};
        }



        @Override
        public Array<Class<? extends Ability.AbilityPrototype>> getAbilitiesToSaveOnCraft() {
            return null;
        }

        @Override
        public Ability.AbilityPrototype copy() {
            return null;
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
    private static class S extends Ability.AbilitySaverStat{
        private int dmg;
        private float slowPercent;
        private float duration;

        public S(int dmg, float slowPercent, float duration) {
            this.dmg = dmg;
            this.slowPercent = slowPercent;
            this.duration = duration;
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
            hitMob(((Mob) m), DamageType.Magic, dmg);
            m.addEffect(new IceBlastEffect(slowPercent, duration).setOwner(((Mob) m)));
        }
    }


}
