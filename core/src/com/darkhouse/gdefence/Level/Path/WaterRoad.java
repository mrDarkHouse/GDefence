package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;

public class WaterRoad extends MapTile implements Walkable{
    private Way startWay;

    public WaterRoad(Way startWay) {
        this.startWay = startWay;
        initTexture();
    }

    @Override
    protected void initTexture() {
        if (startWay == Way.LEFT || startWay == Way.RIGHT) setRegion(GDefence.getInstance().assetLoader.get("Path/waterHorizontal.png", Texture.class));
        else setRegion(GDefence.getInstance().assetLoader.get("Path/waterVertical.png", Texture.class));
    }

    @Override
    public boolean isBuildable() {
        return false;
    }

    @Override
    public boolean isSwimmable() {//for animation??
        return true;
    }


    @Override
    public Way manipulatePath(Mob enterMob) {
//        if(enterMob.getMoveType() == Mob.MoveType.water) return startWay;
//        else
        return startWay;
    }
}
