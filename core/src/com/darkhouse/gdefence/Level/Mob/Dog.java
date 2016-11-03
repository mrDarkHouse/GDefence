package com.darkhouse.gdefence.Level.Mob;


import com.darkhouse.gdefence.Helpers.AssetLoader;

public class Dog extends Mob{
    public Dog() {
        super();
        setName("Dog");
        setHealth(50);
        setArmor(1);
        setDmg(2);
        setBounty(4);
        setSpeed(100);
        setTextureDrawable(AssetLoader.dog);

    }
}
