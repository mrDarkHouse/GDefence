package com.darkhouse.gdefence.Level.Mob;


import com.darkhouse.gdefence.Helpers.AssetLoader;

public class Worm extends Mob{
    public Worm() {
        super();
        setName("Worm");
        setHealth(100);
        setArmor(2);
        setDmg(2);
        setBounty(6);
        setSpeed(80);
        setTextureDrawable(AssetLoader.worm);
    }
}
