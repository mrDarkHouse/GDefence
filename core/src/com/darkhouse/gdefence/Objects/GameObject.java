package com.darkhouse.gdefence.Objects;


import com.badlogic.gdx.graphics.Texture;
import com.darkhouse.gdefence.InventorySystem.inventory.Item;

public abstract class GameObject {
    public abstract String getName(); //{
//        return name;
//    }
    public Texture getTexture() {
        return texture;
    }
    //public int getID(){
    //    return ID;
    //}
    //private Item prototype;

//    public Item getPrototype() {
//        return prototype;
//    }
    public abstract Item getPrototype();
    public abstract String getTooltip();

//    public void setPrototype(Item prototype) {
//        this.prototype = prototype;
//    }

//    protected String name;
    protected Texture texture;
    //protected int ID;

    public GameObject(/*Item prototype*/) {
        //this.prototype = prototype;
    }
}
