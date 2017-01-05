package com.darkhouse.gdefence.Objects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.InventorySystem.inventory.Item;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;

public abstract class GameObject {



//    private Item item;

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
