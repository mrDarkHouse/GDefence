package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;

public class Spawn extends MapTile implements Walkable{
    private Way startWay;
    private TargetType applyMobs;

    @Override
    public TargetType getApplyMobs() {
        return applyMobs;
    }

    public Spawn(Way startWay, TargetType applyMobs) {
        this.startWay = startWay;
        this.applyMobs = applyMobs;
//        initTexture();
    }

    @Override
    public void initTexture() {
        if(applyMobs.isConsist(Mob.MoveType.ground)) {
            if (startWay == Way.LEFT || startWay == Way.RIGHT)
                setRegion(GDefence.getInstance().assetLoader.get("Path/roadHorizontal.png", Texture.class));
            else setRegion(GDefence.getInstance().assetLoader.get("Path/roadVertical.png", Texture.class));
        }else if(applyMobs == TargetType.WATER_ONLY) {
            if (startWay == Way.LEFT || startWay == Way.RIGHT)
                setRegion(GDefence.getInstance().assetLoader.get("Path/waterHorizontal.png", Texture.class));
            else setRegion(GDefence.getInstance().assetLoader.get("Path/waterVertical.png", Texture.class));
        }
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
    public Way manipulatePath(Mob.MoveType enterMobType) {
        return startWay;
    }
}
