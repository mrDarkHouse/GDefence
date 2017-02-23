package com.darkhouse.gdefence.Level.Mob;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Path.MapTile;

public class Dog extends Mob{
    public Dog() {
        super();
        setName("Dog");
        setHealth(50);
        setArmor(1);
        setDmg(2);
        setBounty(4);
        setMoveType(MoveType.ground);
        setSpeed(100);
//        setTextureDrawable(GDefence.getInstance().assetLoader.get("Mobs/mob2.png", Texture.class));
        setRegion(GDefence.getInstance().assetLoader.get("Mobs/mob2.png", Texture.class));
    }

    @Override
    public void update() {

    }
}
