package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.DamageType;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Objects.DamageSource;

public class StrongSkin extends MobAbility implements MobAbility.ISpawn{

    private static class BlockDmg extends Effect<Mob> implements IGetDmg/*, IListener*/{
        private int blockEmount;
        private int blockMinLimit;

        public BlockDmg(float duration, int blockEmount, int blockMinLimit) {
            super(true, true, duration, IGetDmg.class);
            this.blockEmount = blockEmount;
            this.blockMinLimit = blockMinLimit;
            setListener();
        }

        @Override
        public float getDmg(DamageSource source, DamageType type, float dmg) {
            if(type == DamageType.Physic) {
                if (dmg < blockMinLimit) return dmg;
                float newDmg = dmg - blockEmount;
                return newDmg > blockMinLimit ? newDmg : blockMinLimit;
            }else return dmg;
        }

        @Override
        public void apply() {

        }
    }
    public static class P extends AbilityPrototype {
        private int blockEmount;
        private int blockMinLimit;

        public P(int blockEmount, int blockMinLimit) {
            super("strongSkin", false, ISpawn.class);
            this.blockEmount = blockEmount;
            this.blockMinLimit = blockMinLimit;

        }
        public MobAbility getAbility(){
            return new StrongSkin(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("strongSkinTooltip1") + " " + FontLoader.colorString(Integer.toString(blockEmount), 10) + " " +
                    l.getWord("strongSkinTooltip2") + System.getProperty("line.separator") +
                    l.getWord("strongSkinTooltip3") + " " + FontLoader.colorString(Integer.toString(blockMinLimit), 10) + " " +
                    l.getWord("strongSkinTooltip4");
        }
    }

    private BlockDmg effect;

    public StrongSkin(P prototype) {
        super(prototype);
        effect = new BlockDmg(-1, prototype.blockEmount, prototype.blockMinLimit);
    }

    @Override
    public void spawned() {
        if(!owner.haveEffect(BlockDmg.class)) owner.addEffect(effect);
    }

    @Override
    public void init() {
        effect.setOwner(owner);
    }



}
