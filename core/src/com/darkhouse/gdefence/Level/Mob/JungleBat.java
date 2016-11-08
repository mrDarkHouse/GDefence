package com.darkhouse.gdefence.Level.Mob;


import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Level.MapTile;

public class JungleBat extends Mob{
    public JungleBat() {
        super();
        setName("Jungle Bat");
        setHealth(85);
        setArmor(2);
        setDmg(3);
        setBounty(3);
        setMoveType(MapTile.TileType.ground);
        setSpeed(110);
        setTextureDrawable(AssetLoader.jungleBat);
    }
}
