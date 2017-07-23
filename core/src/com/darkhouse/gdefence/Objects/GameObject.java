package com.darkhouse.gdefence.Objects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.InventorySystem.inventory.Item;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;

import java.util.HashMap;

public abstract class GameObject {

    public static boolean isMatches(Class<? extends GameObject> a, Class<? extends GameObject> b){
        if(a == b) return true;
        if(a.getSuperclass() == b) return true;//a Recipe b Detail
        if(a == b.getSuperclass()) return true;
        return false;
    }



//    private Item item;

    public abstract String getName(); //{
//        return texturePath;
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

    public abstract String getSaveCode();
//    public static GameObject loadSaveCode(String s){return null;}

//    public void setPrototype(Item prototype) {
//        this.prototype = prototype;
//    }

//    protected String texturePath;
    protected Texture texture;
    //protected int ID;

    public GameObject(/*Item prototype*/) {
        //this.prototype = prototype;
    }

//    public void generateStartObjects(Item item, int amount){
//        Array<GameObject> tmp = new Array<GameObject>();
//        tmp.add();
//    }

    public static Array<? extends GameObject> generateStartObjects(Item item, int amount){
        if(item instanceof ItemEnum.Tower){
            Array<TowerObject> tmp = new Array<TowerObject>();
            for (int i = 0; i < amount; i++) {
                tmp.add(new TowerObject(((ItemEnum.Tower) item)));
            }
            return tmp;
        }else if(item instanceof ItemEnum.Spell){
            Array<SpellObject> tmp = new Array<SpellObject>();
            for (int i = 0; i < amount; i++) {
                tmp.add(new SpellObject(((ItemEnum.Spell) item)));
            }
            return tmp;
        }else if(item instanceof ItemEnum.Detail){
            Array<DetailObject> tmp = new Array<DetailObject>();
            for (int i = 0; i < amount; i++) {
                tmp.add(new DetailObject(((ItemEnum.Detail) item)));
            }
            return tmp;
        }
        return null;
    }

}
