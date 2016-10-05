package com.darkhouse.gdefence.Level.Mob;


import com.darkhouse.gdefence.Helpers.AssetLoader;

public class Slime extends Mob{
    public Slime() {
        setName("Slime");
        setHealth(100);
        setDmg(1);
        setBounty(2);
        setSpeed(50);
        setTextureDrawable(AssetLoader.slime);

    }


}
