package com.darkhouse.gdefence.Level.Mob;


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
        setTextureDrawable(AssetLoader.boar);
    }
}
