package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Mob.Way;

public class Grass extends MapTile{
    private int id;

    public Grass(int id) {
        this.id = id;
//        initTexture();
    }

    @Override
    public void initTexture() {
        switch (id){
            case 0:setRegion(GDefence.getInstance().assetLoader.get("Path/grass.png", Texture.class)); break;
            case 1:setRegion(GDefence.getInstance().assetLoader.get("Path/sand.png", Texture.class)); break;
            case 2:setRegion(GDefence.getInstance().assetLoader.get("Path/sand2.png", Texture.class)); break;
            case 3:setRegion(GDefence.getInstance().assetLoader.get("Path/neotile.png", Texture.class)); break;
        }

    }

    @Override
    public boolean isBuildable() {
        return true;
    }




}
