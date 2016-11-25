package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Objects.TowerObject;
import com.darkhouse.gdefence.User;

public class Recipe {
    private ItemEnum.Tower tower;

    public ItemEnum.Tower getTower() {
        return tower;
    }

    private Array <TowerObject> components;

    public Array<TowerObject> getComponents() {
        return components;
    }

    public Recipe(ItemEnum.Tower tower) {
        this.tower = tower;
        initComponents(tower);
    }




    private void initComponents(ItemEnum.Tower t){
        components = new Array<TowerObject>();
        TowerObject o;//
        switch (t){
            case Rock:
                o = new TowerObject(ItemEnum.Tower.Basic);
                o.addGems(User.GEM_TYPE.RED, 3);
                o.addGems(User.GEM_TYPE.YELLOW, 1);
                o.addGems(User.GEM_TYPE.BLUE, 1);
                components.add(o);//3 1 1
                break;
            case Arrow:
                o = new TowerObject(ItemEnum.Tower.Basic);
                o.addGems(User.GEM_TYPE.RED, 1);
                o.addGems(User.GEM_TYPE.YELLOW, 3);
                o.addGems(User.GEM_TYPE.BLUE, 1);
                components.add(o);//1 3 1
                break;
            case Range:
                o = new TowerObject(ItemEnum.Tower.Basic);
                o.addGems(User.GEM_TYPE.RED, 1);
                o.addGems(User.GEM_TYPE.YELLOW, 1);
                o.addGems(User.GEM_TYPE.BLUE, 3);
                components.add(o);//1 1 3
                break;


        }
    }
}
