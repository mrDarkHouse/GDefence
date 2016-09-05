package com.darkhouse.gdefence.Level.Mob;


import com.darkhouse.gdefence.Helpers.AssetLoader;

public class Slime extends Mob{
    public Slime() {
        setName("Slime");
        setHealth(30);
        setDmg(1);
        setBounty(2);
        setSpeed(180);
        setTextureDrawable(AssetLoader.slime);

    }


}
