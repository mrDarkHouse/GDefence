package com.darkhouse.gdefence.Level.Mob;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Level.MapTile;

public class Boar extends Mob{
    public Boar() {
        super();
        setName("Boar");
        setHealth(250);
        setArmor(4);
        setDmg(2);
        setBounty(7);
        setMoveType(MapTile.TileType.ground);
        setSpeed(60);
        setTextureDrawable(GDefence.getInstance().assetLoader.get("Mobs/mob5.png", Texture.class));
    }
}
