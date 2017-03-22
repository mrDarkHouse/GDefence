package com.darkhouse.gdefence.Level.Mob;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.darkhouse.gdefence.GDefence;

//public class Amphibia extends Mob{
//    private boolean state;//false - ground, true - water
//    private Texture texture1;
//    private Texture texture2;
//
//    public Amphibia() {
//        super();
//        setName("Amphibia");
//        setHealth(150);
//        setArmor(2);
//        setDmg(2);
//        setBounty(5);
//        setMoveType(MoveType.water);
//        setSpeed(50);
//        texture1 = GDefence.getInstance().assetLoader.get("Mobs/mob6walk.png", Texture.class);
//        texture2 = GDefence.getInstance().assetLoader.get("Mobs/mob6swim.png", Texture.class);
//    }
//    public void update(){
//        if(currentTile.isSwimmable()){
//            setRegion(texture2);
//            state = true;//useless yet
//        }else {
//            setRegion(texture1);
//            state = false;
//        }
//    }
//}
