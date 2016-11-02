package com.darkhouse.gdefence.Level.Mob;


import com.darkhouse.gdefence.Helpers.AssetLoader;

public class Boar extends Mob{
    public Boar() {
        super();
        setName("Boar");
        setHealth(250);
        setDmg(2);
        setBounty(7);
        setSpeed(60);
        setTextureDrawable(AssetLoader.boar);
    }
}
