package com.darkhouse.gdefence.Level.Mob;


import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Level.MapTile;

public class Slime extends Mob{
    public Slime() {
        super();
        setName("Slime");
        setHealth(80);
        setArmor(0);
        setDmg(1);
        setBounty(3);
        setMoveType(MapTile.TileType.ground);
        setSpeed(50);
        setTextureDrawable(AssetLoader.slime);

    }


}
