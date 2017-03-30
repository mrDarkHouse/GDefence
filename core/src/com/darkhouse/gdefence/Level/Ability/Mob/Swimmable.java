package com.darkhouse.gdefence.Level.Ability.Mob;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Path.MapTile;

public class Swimmable extends MobAbility implements MobAbility.IMove{

    private String pathTexture;
    private Texture swimTexture;
    private Texture walkTexture;
    private boolean isSwim;

    public Swimmable(String pathTexture) {
        super("Swimmable", true);
        this.pathTexture = pathTexture;

    }
    public void init(){
        this.swimTexture = GDefence.getInstance().assetLoader.get(pathTexture, Texture.class);
        walkTexture = owner.getTexture();
    }

    @Override
    public void move(MapTile currentTile) {
//        System.out.println(owner);
//        System.out.println(owner.getTexture());
        if(currentTile.isSwimmable()){
//            if(!isSwim) {
                owner.setTexture(swimTexture);
                isSwim = true;//useless yet
//            }
        }else {
//            if(isSwim) {
                owner.setTexture(walkTexture);
                isSwim = false;
//            }
        }
    }

    @Override
    public String getTooltip() {//tooltip hidden
        return null;
    }
}
