package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;

public class Castle extends MapTile implements Walkable{
    public Castle() {
//        initTexture();
    }

    @Override
    public void initTexture() {
        setRegion(GDefence.getInstance().assetLoader.get("Path/castle.png", Texture.class));
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
    public TargetType getApplyMobs() {
        return TargetType.ALL;
    }

    @Override
    public Way manipulatePath(Mob.MoveType enterMobType, Way currentWay) {
        return null;//
    }
}
