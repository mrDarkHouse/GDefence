package com.darkhouse.gdefence.Objects;


import com.darkhouse.gdefence.InventorySystem.inventory.Item;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;

public class DetailObject implements GameObject{
    private ItemEnum.Detail prototype;

    public static DetailObject loadSaveCode(String savecode) {
        String[] info = savecode.split("-", 2);

        if(info[0].equals("Recipe")){
            return Recipe.loadSaveCode(info[1]);
//        }else if(){
//            //
        }else {
           throw new IllegalArgumentException("wrong detail className");
        }
    }

    public DetailObject(ItemEnum.Detail prototype) {
        this.prototype = prototype;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Item getPrototype() {
        return prototype;
    }

    @Override
    public String getTooltip() {
        return null;
    }

    @Override
    public String getSaveCode() {
        return null;
    }



}
