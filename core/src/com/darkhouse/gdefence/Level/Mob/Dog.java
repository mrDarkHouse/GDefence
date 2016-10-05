package com.darkhouse.gdefence.Level.Mob;


import com.darkhouse.gdefence.Helpers.AssetLoader;

public class Dog extends Mob{
    public Dog() {
        setName("Dog");
        setHealth(70);
        setDmg(1);
        setBounty(3);
        setSpeed(120);
        setTextureDrawable(AssetLoader.dog);

    }
}
