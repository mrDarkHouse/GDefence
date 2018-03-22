package com.darkhouse.gdefence.Objects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.InventorySystem.inventory.Item;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.GemGradable;

import java.util.HashMap;

public interface GameObject extends GemGradable{



//    private Item item;

    public abstract String getName(); //{
//        return texturePath;
//    }
//    public Texture getTexture() {
//        return texture;
//    }
    //public int getID(){
    //    return ID;
    //}
    //private Item prototype;

//    public Item getPrototype() {
//        return prototype;
//    }
    /*public abstract */Item getPrototype();
    /*public abstract */String getTooltip();

    /*public abstract */String getSaveCode();
    /*public abstract */int getGlobalCost();
//    public static GameObject loadSaveCode(String s){return null;}

//    public void setPrototype(Item prototype) {
//        this.prototype = prototype;
//    }

//    protected String texturePath;
//    protected Texture texture;
    //protected int ID;

//    public GameObject(/*Item prototype*/) {
//        //this.prototype = prototype;
//    }

//    public void generateStartObjects(Item item, int amount){
//        Array<GameObject> tmp = new Array<GameObject>();
//        tmp.add();
//    }



}
