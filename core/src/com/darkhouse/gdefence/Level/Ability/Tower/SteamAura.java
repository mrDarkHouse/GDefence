package com.darkhouse.gdefence.Level.Ability.Tower;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.Aura;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Model.Level.Map;

public class SteamAura extends Ability implements Ability.IOnBuild{




    public static class P extends AbilityPrototype{

//        private ItemEnum.Tower owner;

        public P() {
            super(13, "steamAura", new int[]{0, 0, 0}, IOnBuild.class);
        }

        @Override
        public Array<Class<? extends AbilityPrototype>> getAbilitiesToSaveOnCraft() {
            return new Array<Class<? extends AbilityPrototype>>();
        }

        @Override
        public AbilityPrototype copy() {
            P p = new P();
            //p.
            return p;
        }

        @Override
        public Ability getAbility() {
            return new SteamAura(/*this*/);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return(l.getWord("steamAuraTooltip1") + System.getProperty("line.separator") +
                    l.getWord("steamAuraTooltip2") + System.getProperty("line.separator") +
                    l.getWord("steamAuraTooltip3"));
        }
    }
//    public static class G extends AbilityGrader{
//
//        public G(int[] gemCap) {
//            super(gemCap);
//        }
//    }

    private class ExpTether extends Effect<Tower> implements IOnGetExp{

        private Tower tethered;
        private float multiple;

        public ExpTether(float duration, Tower tethered, float multiple) {
            super(true, false, duration);
            this.tethered = tethered;
            this.multiple = multiple;
        }

        @Override
        public void apply() {

        }

        @Override
        public float addExp(float exp) {
            tethered.addExp(exp*multiple);
            return exp;
        }
    }

    private class SteamAuraAura extends Effect<Tower> implements Ability.IBuildedOnMap{

        public SteamAuraAura(/*int range, float duration*/) {
            super(true, true, -1, "slow");
        }

        @Override
        public Effect setOwner(Tower owner) {
            super.setOwner(owner);
            setAura(new Aura(owner.getTowerPrototype().getRange()));
            getAuraObject().init(owner);
            return this;
        }
        @Override
        public void buildedOnMap(Tower builded) {
            if(builded == owner) return;//cant buff self
            if(getAuraObject().isInRange(builded)){
                builded.addEffect(new SteamAuraBuff(-1, owner.getTowerPrototype().getDmg(),
                        owner.getTowerPrototype().getSpeed()).setOwner(builded));
                builded.addEffect(new ExpTether(-1, owner, 0.3f));//30% of buffed towers earned exp get as own
            }
        }

        @Override
        public void apply() {
            Array<Tower> ts = Level.getMap().getTowersInRange(owner.getCenter(), getAuraObject().getRangeCircle().radius);
            for (int i = 0; i < ts.size; i++){
                if(ts.get(i) == owner) continue;
                ts.get(i).addEffect(new SteamAuraBuff(-1, owner.getTowerPrototype().getDmg(),
                        owner.getTowerPrototype().getSpeed()).setOwner(ts.get(i)));
                ts.get(i).addEffect(new ExpTether(-1, owner, 0.3f));
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


    private SteamAuraAura effect;


    public SteamAura(/*P prototype*/) {
//        effect = new SteamAuraAura();
    }

    @Override
    protected void init(Map map) {
        effect = new SteamAuraAura();
        effect.setOwner(owner);
    }

    @Override
    public void builded(MapTile tile) {
        owner.addEffect(effect);
    }



}
