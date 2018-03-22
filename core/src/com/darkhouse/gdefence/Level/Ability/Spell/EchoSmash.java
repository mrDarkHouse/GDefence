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

public class EchoSmash extends Spell{

    public static class P extends SpellObject implements IAoe{
        private AtomicReference<Integer> dmg;
        private AtomicReference<Float> stunDuration;
        private G g;
        private S s;

        private AtomicReference<Integer> aoe;

        public P(int energyCost, float cooldown, int dmg, float stunDuration, int aoe, G grader) {
            super(150, "echoSmash", energyCost, cooldown, grader.gemCap, Mob.class);
            this.dmg = new AtomicReference<Integer>(dmg);
            this.stunDuration = new AtomicReference<Float>(stunDuration);
            this.aoe = new AtomicReference<Integer>(aoe);
            this.g = grader;
            this.s = new S(dmg, stunDuration, aoe);

//            AssetLoader l = GDefence.getInstance().assetLoader;
//            gemBoost[0] = new BoostInteger(this.dmg, grader.dmgUp, l.getWord("echoSmashGrade1"),
//                    true, BoostInteger.IntegerGradeFieldType.NONE);
//            gemBoost[1] = new BoostFloat(this.stunDuration, grader.stunDurationUp, l.getWord("echoSmashGrade2"),
//                    true, BoostFloat.FloatGradeFieldType.TIME);
//            gemBoost[2] = new BoostInteger(this.aoe, grader.aoeUp, l.getWord("echoSmashGrade3"),
//                    true, BoostInteger.IntegerGradeFieldType.NONE);
            initBoosts(GDefence.getInstance().assetLoader);
        }

        @Override
        public void flush() {
            this.dmg = new AtomicReference<Integer>(s.dmg);
            this.stunDuration = new AtomicReference<Float>(s.stunDuration);
            this.aoe = new AtomicReference<Integer>(s.aoe);
            initBoosts(GDefence.getInstance().assetLoader);
        }

        @Override
        protected void initBoosts(AssetLoader l) {
            gemBoost[0] = new BoostInteger(this.dmg, g.dmgUp, l.getWord("echoSmashGrade1"),
                    true, BoostInteger.IntegerGradeFieldType.NONE);
            gemBoost[1] = new BoostFloat(this.stunDuration, g.stunDurationUp, l.getWord("echoSmashGrade2"),
                    true, BoostFloat.FloatGradeFieldType.TIME);
            gemBoost[2] = new BoostInteger(this.aoe, g.aoeUp, l.getWord("echoSmashGrade3"),
                    true, BoostInteger.IntegerGradeFieldType.NONE);
        }


        @Override
        public int getAoe() {
            return aoe.get();
        }

        @Override
        protected String getChildTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("echoSmashTooltip1") + System.getProperty("line.separator") +
                    l.getWord("echoSmashTooltip2") + System.getProperty("line.separator") +
                    "(" + (FontLoader.colorString(dmg.get().toString(), User.GEM_TYPE.BLACK) + l.getWord("echoSmashTooltip3")) + " " +
                    l.getWord("echoSmashTooltip4") + ") " + System.getProperty("line.separator") +
                    l.getWord("echoSmashTooltip5") + " " + FontLoader.colorString(stunDuration.get().toString(), User.GEM_TYPE.GREEN) + " " +
                    l.getWord("echoSmashTooltip6");
        }



        @Override
        public Array<Class<? extends Ability.AbilityPrototype>> getAbilitiesToSaveOnCraft() {
            return null;
        }

//        @Override
//        public String getSaveCode() {
//            return super.getSaveCode() + ";" + dmg + ";" + stunDuration + ";" + g.dmgUp + ";" + g.stunDurationUp + ";" + g.aoeUp;
//        }

        @Override
        public Spell createSpell() {
            return new EchoSmash(this);
        }

        @Override
        public Ability.AbilityPrototype copy() {
//            AssetLoader l = GDefence.getInstance().assetLoader;
//            gemBoost[0] = new BoostInteger(this.dmg, g.dmgUp, l.getWord("echoSmashGrade1"),
//                    true, BoostInteger.IntegerGradeFieldType.NONE);
//            gemBoost[1] = new BoostFloat(this.stunDuration, g.stunDurationUp, l.getWord("echoSmashGrade2"),
//                    true, BoostFloat.FloatGradeFieldType.TIME);
//            gemBoost[2] = new BoostInteger(this.aoe, g.aoeUp, l.getWord("echoSmashGrade3"),
//                    true, BoostInteger.IntegerGradeFieldType.NONE);
            return this;
        }

        @Override
        public int[] exp2nextLevel() {
            return new int[]{230, 780, 1550, 2250, 6000, 8000};
        }

        @Override
        public Item getPrototype() {
            return ItemEnum.Spell.EchoSmash;
        }
    }
    public static class G extends Ability.AbilityGrader{
        private int dmgUp;
        private float stunDurationUp;
        private int aoeUp;

        public G(int dmgUp, float stunDurationUp, int aoeUp, int[] gemCap) {
            super(gemCap);
            this.dmgUp = dmgUp;
            this.stunDurationUp = stunDurationUp;
            this.aoeUp = aoeUp;
        }
    }
    private static class S extends Ability.AbilitySaverStat{
        private int dmg;
        private float stunDuration;
        private int aoe;

        public S(int dmg, float stunDuration, int aoe) {
            this.dmg = dmg;
            this.stunDuration = stunDuration;
            this.aoe = aoe;
        }
    }


    public class EchoSmashStun extends Effect<Mob>{

        public EchoSmashStun(float duration) {
            super(false, true, duration, "bash");
        }

        @Override
        public void apply() {
            owner.setState(Mob.State.stunned);
        }

        @Override
        public void dispell() {
            if(owner.getState() == Mob.State.stunned) owner.setState(Mob.State.normal);
            super.dispell();
        }

        @Override
        public void act(float delta) {
            super.act(delta);
            getPrototype().addExp(delta * 2f);
        }
    }
    private int dmg;
    private float duration;


    public EchoSmash(P prototype) {
        super(prototype);
        this.dmg = prototype.dmg.get();
        this.duration = prototype.stunDuration.get();
    }

    @Override
    public void use(Array<? extends Effectable> targets) {
        int d = dmg*targets.size;
        for (Effectable m:targets){
            hitMob(((Mob) m), DamageType.Magic, d);
//            getPrototype().addExp(d/2f);
            m.addEffect(new EchoSmashStun(duration).setOwner(((Mob) m)));
        }
    }
}
