package com.darkhouse.gdefence.Level.Ability.Mob;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Level.Path.WalkableMapTile;

public class Swimmable extends MobAbility implements MobAbility.IMove{

    private String pathTexture;
    private Texture[] swimTexture;
//    private Texture walkTexture;
    private boolean isSwim;

    public static class P extends AbilityPrototype {
        private String path;

        public P(String path) {
            super("Swimmable", true);
            this.path = path;
        }
        public MobAbility getAbility(){
            return new Swimmable(this);
        }

        @Override
        public String getTooltip() {//tooltip hidden
            return null;
        }
    }

    public Swimmable(P prototype) {
        super(prototype);
        this.pathTexture = prototype.path;
    }

    //    public Swimmable(String pathTexture) {
//        super("Swimmable", true);
//        this.pathTexture = pathTexture;
//
//    }
    public void init(){
        this.swimTexture = new Texture[4];
        for (int i = 0; i < 4; i++){
            swimTexture[i] = GDefence.getInstance().assetLoader.get(pathTexture + i + ".png");
        }
//        this.swimTexture = GDefence.getInstance().assetLoader.get(pathTexture, Texture.class);
//        walkTexture = owner.getTexture();
    }

    @Override
    public void move(WalkableMapTile currentTile) {
        if(currentTile.isSwimmable()){
//            if(!isSwim) {
                owner.setRegion(swimTexture[owner.getWay().ordinal()]);
                isSwim = true;//useless yet
//            }
        }else {
//            if(isSwim) {
//                owner.setRegion(walkTexture);
                owner.setWay(owner.getWay());
                isSwim = false;
//            }
        }
    }


}
