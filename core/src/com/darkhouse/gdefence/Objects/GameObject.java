package com.darkhouse.gdefence.Objects;


import com.badlogic.gdx.graphics.Texture;

public abstract class GameObject {
    public String getName() {
        return name;
    }
    public Texture getTexture() {
        return texture;
    }
    //public int getID(){
    //    return ID;
    //}

    protected String name;
    protected Texture texture;
    //protected int ID;

    public GameObject() {
    }
}
