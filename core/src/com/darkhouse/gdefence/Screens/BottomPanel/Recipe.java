package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;

public class Recipe {
    private ItemEnum.Tower tower;

    public ItemEnum.Tower getTower() {
        return tower;
    }

    private Array <ItemEnum.Tower> components;

    public Array<ItemEnum.Tower> getComponents() {
        return components;
    }

    public Recipe(ItemEnum.Tower tower) {
        this.tower = tower;
        initComponents(tower);
    }




    private void initComponents(ItemEnum.Tower t){
        components = new Array<ItemEnum.Tower>();
        switch (t){
            case Rock:
                components.add(ItemEnum.Tower.Basic);//4 1 1
                break;
            case Arrow:
                components.add(ItemEnum.Tower.Basic);//1 4 1
                break;
            case Range:
                components.add(ItemEnum.Tower.Basic);//1 1 4
                break;
        }
    }
}
