package com.darkhouse.gdefence.Level.Mob;


import com.darkhouse.gdefence.Helpers.AssetLoader;

public class Slime extends Mob{
    public Slime() {
        super();
        setName("Slime");
        setHealth(50);
        setDmg(1);
        setBounty(5);
        setSpeed(50);
        setTextureDrawable(AssetLoader.slime);

    }


}
