package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;

import java.util.IllegalFormatException;

public class Turn extends MapTile implements Walkable{
    private Way startWay;
    private Way resultWay;
    private TargetType applyMobs;

    public Turn(Way startWay, Way resultWay, TargetType applyMobs) {
//        super();
        this.startWay = startWay;
        this.resultWay = resultWay;
        this.applyMobs = applyMobs;
        initTexture();
    }
    @Override
    protected void initTexture() {
        Texture texture;
        if(startWay == Way.LEFT && resultWay == Way.UP || startWay == Way.DOWN && resultWay == Way.RIGHT)
            texture = GDefence.getInstance().assetLoader.get("Path/Turn/turnLU.png", Texture.class);
        else if(startWay == Way.LEFT && resultWay == Way.DOWN || startWay == Way.UP && resultWay == Way.RIGHT)
            texture = GDefence.getInstance().assetLoader.get("Path/Turn/turnLD.png", Texture.class);
        else if(startWay == Way.RIGHT && resultWay == Way.UP || startWay == Way.DOWN && resultWay == Way.LEFT)
            texture = GDefence.getInstance().assetLoader.get("Path/Turn/turnRU.png", Texture.class);
        else if(startWay == Way.RIGHT && resultWay == Way.DOWN || startWay == Way.UP && resultWay == Way.LEFT)
            texture = GDefence.getInstance().assetLoader.get("Path/Turn/turnRD.png", Texture.class);
        else throw new IllegalArgumentException("startWay and resultWay cant be " + startWay + " and " + resultWay);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        setRegion(texture);
    }

    @Override
    public boolean isBuildable() {
        return false;
    }

    @Override
    public boolean isSwimmable() {
        return false;
    }

    @Override
    public Way manipulatePath(Mob enterMob) {
        if(applyMobs.isConsist(enterMob.getMoveType())) return resultWay;
        else return startWay;
    }


}
