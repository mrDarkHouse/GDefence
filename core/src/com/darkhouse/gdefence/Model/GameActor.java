package com.darkhouse.gdefence.Model;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameActor extends Actor{

    public Vector2 getCenter(){
        return new Vector2(getX() + getWidth()/2, getY() + getHeight()/2);
    }

}
