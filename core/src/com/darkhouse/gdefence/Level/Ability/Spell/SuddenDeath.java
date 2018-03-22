package com.darkhouse.gdefence.Level.Ability.Spell;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.Item;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Level.Ability.Mob.BossResist;
import com.darkhouse.gdefence.Level.Ability.Tools.DamageType;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Model.Effectable;
import com.darkhouse.gdefence.Objects.SpellObject;
import com.darkhouse.gdefence.Screens.LevelMap;
import com.darkhouse.gdefence.User;

import java.util.concurrent.atomic.AtomicReference;

public class SuddenDeath extends Spell{

    public static class P extends SpellObject implements Spell.ITarget {

        private AtomicReference<Float> cooldownDown;
        private AtomicReference<Float> bountyMultiplayer;
        private AtomicReference<Float> bossDamage;
        private G g;
        private S s;

        public P(int energyCost, final float cooldown, float bountyMultiplayer, final float cooldownDown, float bossDamage, final G grader) {
            super(151, "suddenDeath", energyCost, cooldown, grader.gemCap, Mob.class);
            this.cooldownDown = new AtomicReference<Float>(cooldownDown);
            this.bountyMultiplayer = new AtomicReference<Float>(bountyMultiplayer);
            this.bossDamage = new AtomicReference<Float>(bossDamage);

            this.g = grader;
            this.s = new S(cooldownDown, bountyMultiplayer, bossDamage);

            initBoosts(GDefence.getInstance().assetLoader);

        }

        @Override
        public void flush() {
            this.cooldownDown = new AtomicReference<Float>(s.cooldownDown);
            this.bountyMultiplayer = new AtomicReference<Float>(s.bountyMultiplayer);
            this.bossDamage = new AtomicReference<Float>(s.bossDamage);
            initBoosts(GDefence.getInstance().assetLoader);
        }

        @Override
        protected void initBoosts(AssetLoader l) {
            gemBoost[0] = new BoostFloat(this.bountyMultiplayer, g.bountyMultiplayerUp, l.getWord("suddenDeathGrade1"),
                    true, BoostFloat.FloatGradeFieldType.MULTIPLAYER);
            gemBoost[1] = new BoostFloat(this.cooldownDown, g.cooldownDown, l.getWord("suddenDeathGrade2"),
                    true, BoostFloat.FloatGradeFieldType.TIME){
                @Override
                public String boostField() {
//                    return super.boostField();
                    return (getCooldown()) + "s";
                }

                @Override
                public String concate() {
//                    return super.concate();
                    return (getCooldown() - g.cooldownDown) + "s";
                }

                @Override
                public void grade() {
//                    super.grade();
                    P.super.setCooldown(P.super.cooldown - g.cooldownDown);
                }
            };
            gemBoost[2] = new BoostFloat(this.bossDamage, g.bossDamageUp, l.getWord("suddenDeathGrade3"),
                    true, BoostFloat.FloatGradeFieldType.PERCENT);
        }

        @Override
        public Array<Class<? extends Ability.AbilityPrototype>> getAbilitiesToSaveOnCraft() {
            return null;
        }

//        @Override
//        public String getSaveCode() {
//            return super.getSaveCode() + ";" + bountyMultiplayer.get() + ";" + cooldownDown.get() + ";" + bossDamage.get() + ";" +
//                    g.bountyMultiplayerUp + ";" + g.cooldownDown + ";" + g.bossDamageUp;
//        }

        @Override
        protected String getChildTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("suddenDeathTooltip1") + System.getProperty("line.separator") +
                   l.getWord("suddenDeathTooltip2") + " " + FontLoader.colorString(bountyMultiplayer.get() + "x", User.GEM_TYPE.BLACK) + " " +
                   l.getWord("suddenDeathTooltip3") + System.getProperty("line.separator") +
                   l.getWord("suddenDeathTooltip4") + " " + FontLoader.colorString((int)(bossDamage.get()*100) + "%" , User.GEM_TYPE.WHITE) + " " +
                   l.getWord("suddenDeathTooltip5") + System.getProperty("line.separator") +
                   l.getWord("suddenDeathTooltip6");
        }

        @Override
        public float getCooldown() {
            return super.getCooldown();
        }

        @Override
        public Spell createSpell() {
            return new SuddenDeath(this);
        }

        @Override
        public Ability.AbilityPrototype copy() {
//            P p = new P(energyCost, cooldown, bountyMultiplayer);
            //p.gemsBoost[0]
            return null;
        }

        @Override
        public int[] exp2nextLevel() {
            return new int[]{10, 20, 40, 50, 60, 75, 90};
        }

        @Override
        public Item getPrototype() {
            return ItemEnum.Spell.SuddenDeath;
        }
    }

    public static class G extends Ability.AbilityGrader{
        private float bountyMultiplayerUp;
        private float cooldownDown;
        private float bossDamageUp;

        public G(float bountyMultiplayerUp, float cooldownDown, float bossDamageUp, int[] gemCap) {
            super(gemCap);
            this.bountyMultiplayerUp = bountyMultiplayerUp;
            this.cooldownDown = cooldownDown;
            this.bossDamageUp = bossDamageUp;
        }
    }
    private static class S extends Ability.AbilitySaverStat{
        private float cooldownDown;
        private float bountyMultiplayer;
        private float bossDamage;

        public S(float cooldownDown, float bountyMultiplayer, float bossDamage) {
            this.cooldownDown = cooldownDown;
            this.bountyMultiplayer = bountyMultiplayer;
            this.bossDamage = bossDamage;
        }
    }

    private float bountyMultiplier;
    private float bossDamage;

    public SuddenDeath(P prototype) {
        super(prototype);
        bountyMultiplier = prototype.bountyMultiplayer.get();
        bossDamage = prototype.bossDamage.get();
    }

    @Override
    public void use(Array<? extends Effectable> targets) {
        Mob m = ((Mob) targets.get(0));

        if(m.haveAbility(BossResist.class)){
//            hitMob(m, )//dont use it because no need to get exp
            m.hit(m.getHealth()*bossDamage, DamageType.Pure, this);
        }else {
            m.setDie(this, false);
            LevelMap.getLevel().addEnergy((int) (m.getBounty()*bountyMultiplier));
            getPrototype().addExp(1f);

        }


//        float dmg = m.hit(9000, this);//infinity max hp value
//        if(dmg != 0) {
//            addKill(m);
////            getPrototype().addExp((float)m.getMaxHealth()/10);For what, it non gradable
//        }
    }
}
