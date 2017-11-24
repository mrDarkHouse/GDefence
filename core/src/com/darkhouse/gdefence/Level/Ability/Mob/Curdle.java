package com.darkhouse.gdefence.Level.Ability.Mob;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Ability.Tools.Effect;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Objects.DamageSource;

public class Curdle extends MobAbility implements MobAbility.ISpawn{


    public static class P extends AbilityPrototype{
        private float duration;
        private float threshold;
        private String texturePath;

        public P(float duration, float threshold, String texturePath) {
            super("curdle", false);
            this.duration = duration;
            this.threshold = threshold;
            this.texturePath = texturePath;
        }

        @Override
        public MobAbility getAbility() {
            return new Curdle(this);
        }

        @Override
        public String getTooltip() {
            AssetLoader l = GDefence.getInstance().assetLoader;
            return l.getWord("curdleTooltip1") + " " + FontLoader.colorString(threshold*100 + "%", 3) + " " +
                    l.getWord("curdleTooltip2") + System.getProperty("line.separator") +
                    l.getWord("curdleTooltip3") + " " + FontLoader.colorString(Float.toString(duration), 3) + " " +
                    l.getWord("curdleTooltip4") + System.getProperty("line.separator") +
                    l.getWord("curdleTooltip5");
        }
    }

    private class CurdleInvulnerable extends Effect<Mob> implements IGetDmg{
        private Texture[] curdleTexture;

        public CurdleInvulnerable(float duration, Texture[] curdleTexture) {
            super(true, true, duration);
            this.curdleTexture = curdleTexture;
        }

        @Override
        public float getDmg(DamageSource source, float dmg) {
            return 0;
        }

        @Override
        public void apply() {
            owner.setRegion(curdleTexture[owner.getWay().ordinal()]);
            owner.setState(Mob.State.stunned);
        }

        @Override
        public void dispell() {
            owner.setWay(owner.getWay());
            owner.setState(Mob.State.normal);
            super.dispell();
        }
    }
    private class CurdleBuff extends Effect<Mob> implements IGetDmg{
        private float duration;
        private float threshold;
        private Texture[] curdleTexture;

        public CurdleBuff(float duration, float threshold, Texture[] curdleTexture) {
            super(true, false, -1);
            this.duration = duration;
            this.threshold = threshold;
            this.curdleTexture = curdleTexture;
        }

        @Override
        public void apply() {

        }

        @Override
        public float getDmg(DamageSource source, float dmg) {
            if(owner.getHealthPercent() < threshold) {
                owner.addEffect(new CurdleInvulnerable(duration, curdleTexture).setOwner(owner));
                owner.deleteEffect(CurdleBuff.class);//once usable
                return 0;
            }else return dmg;
        }
    }



    private CurdleBuff effect;

    public Curdle(P prototype) {
        Texture[] curdleTexture = new Texture[4];
        for (int i = 0; i < 4; i++){
            curdleTexture[i] = GDefence.getInstance().assetLoader.get(prototype.texturePath + i + ".png");
        }
        effect = new CurdleBuff(prototype.duration, prototype.threshold, curdleTexture);
    }

    @Override
    public void init() {
        effect.setOwner(owner);
    }
    @Override
    public void spawned() {
        owner.addEffect(effect);
    }


}
