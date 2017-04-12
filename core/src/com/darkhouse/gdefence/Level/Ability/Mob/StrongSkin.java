package com.darkhouse.gdefence.Level.Ability.Mob;


import com.darkhouse.gdefence.Level.Ability.Mob.Effects.Buff.BlockDmg;

public class StrongSkin extends MobAbility implements MobAbility.ISpawn{
    private BlockDmg effect;

    public static class P extends AblityPrototype{
        private int blockEmount;
        private int blockMinLimit;

        public P(int blockEmount, int blockMinLimit) {
            super("Strong Skin", false);
            this.blockEmount = blockEmount;
            this.blockMinLimit = blockMinLimit;

        }
        public MobAbility getAbility(){
            return new StrongSkin(this);
        }

        @Override
        public String getTooltip() {
            return "Block [#64A619ff]" + blockEmount + "[] dmg when attacked" + System.getProperty("line.separator") +
                    "cant block less than [#64A619ff]" + blockMinLimit + "[] dmg";
        }
    }

    public StrongSkin(P prototype) {
        effect = new BlockDmg(-1, prototype.blockEmount, prototype.blockMinLimit);
    }

    //    public StrongSkin(int blockEmount, int blockMinLimit) {
//        super("Block Dmg", false);
//        this.blockEmount = blockEmount;
//        this.blockMinLimit = blockMinLimit;
//        effect = new BlockDmg(-1, blockEmount, blockMinLimit);
//    }

//    @Override
//    public float getDmg(Tower source, float dmg) {
////        if(dmg < blockMinLimit)return dmg;
////        float newDmg = dmg - blockEmount;
////        return newDmg > blockMinLimit ? newDmg:blockMinLimit;
//    }


    @Override
    public void spawned() {
        if(!owner.haveEffect(BlockDmg.class)) owner.addEffect(effect);
    }



    @Override
    public void init() {
        effect.setOwner(owner);
    }


}
