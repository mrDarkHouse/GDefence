package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.GDefence;

public class Decor extends MapTile{
    private int decorId;

    public Decor(int decorId) {
        this.decorId = decorId;
    }

    @Override
    public void initTexture() {
        setRegion(GDefence.getInstance().assetLoader.get("decor" + decorId + ".png", Texture.class));
    }

    @Override
    public boolean isBuildable() {
        return false;
    }

    @Override
    public boolean isSwimmable() {
        return false;
    }
}
