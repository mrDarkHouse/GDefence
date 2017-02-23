package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Mob.Way;

public class Grass extends MapTile{

    public Grass() {
        initTexture();
    }

    @Override
    protected void initTexture() {
        setRegion(GDefence.getInstance().assetLoader.get("Path/grass.png", Texture.class));
    }

    @Override
    public boolean isBuildable() {
        return true;
    }

    @Override
    public boolean isSwimmable() {
        return false;
    }



}
