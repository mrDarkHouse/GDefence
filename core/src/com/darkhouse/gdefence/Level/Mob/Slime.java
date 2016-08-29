package com.darkhouse.gdefence.Level.Mob;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.darkhouse.gdefence.Helpers.AssetLoader;

public class Slime extends Mob{


    public Slime() {
        setName("Slime");
        setHealth(100);
        setDmg(1);
        setBounty(2);
        setSpeed(1);
        setTexture(new TextureRegionDrawable(new TextureRegion(AssetLoader.slime)));

    }


}
