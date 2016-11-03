package com.darkhouse.gdefence.Level.Mob;


import com.darkhouse.gdefence.Helpers.AssetLoader;

public class JungleBat extends Mob{
    public JungleBat() {
        super();
        setName("Jungle Bat");
        setHealth(70);
        setArmor(2);
        setDmg(3);
        setBounty(5);
        setSpeed(110);
        setTextureDrawable(AssetLoader.jungleBat);
    }
}
