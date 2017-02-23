package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;

public class Road extends MapTile implements Walkable{
    private Way startWay;

    public Road(Way startWay) {
        this.startWay = startWay;
        initTexture();
    }

    @Override
    protected void initTexture() {
        if (startWay == Way.LEFT || startWay == Way.RIGHT) setRegion(GDefence.getInstance().assetLoader.get("Path/roadHorizontal.png", Texture.class));
        else setRegion(GDefence.getInstance().assetLoader.get("Path/roadVertical.png", Texture.class));
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
        return startWay;//no manipulate
    }
}
