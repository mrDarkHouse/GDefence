package com.darkhouse.gdefence.Objects;


import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Objects.TowerObject;
import com.darkhouse.gdefence.User;

public class Recipe extends DetailObject{
    private ItemEnum.Tower tower;

    public ItemEnum.Tower getTower() {
        return tower;
    }

    private Array <TowerObject> components;
    private int globalCost;

    public int getGlobalCost() {
        return globalCost;
    }

    public Array<TowerObject> getComponents() {
        return components;
    }

    public Recipe(ItemEnum.Tower tower) {
        super(ItemEnum.Detail.Recipe);
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
                globalCost = 80;
                break;
            case Arrow:
                o = new TowerObject(ItemEnum.Tower.Basic);
                o.addGems(User.GEM_TYPE.RED, 1);
                o.addGems(User.GEM_TYPE.YELLOW, 3);
                o.addGems(User.GEM_TYPE.BLUE, 1);
                components.add(o);//1 3 1
                globalCost = 100;
                break;
            case Range:
                o = new TowerObject(ItemEnum.Tower.Basic);
                o.addGems(User.GEM_TYPE.RED, 1);
                o.addGems(User.GEM_TYPE.YELLOW, 1);
                o.addGems(User.GEM_TYPE.BLUE, 3);
                components.add(o);//1 1 3
                globalCost = 120;
                break;
            case Ballista:
                o = new TowerObject(ItemEnum.Tower.Arrow);
                o.addGems(User.GEM_TYPE.YELLOW, 2);
                o.addGems(User.GEM_TYPE.BLUE, 1);
                components.add(o);//0 2 1
                o = new TowerObject(ItemEnum.Tower.Range);
                o.addGems(User.GEM_TYPE.YELLOW, 2);
                o.addGems(User.GEM_TYPE.BLUE, 1);
                components.add(o);//0 2 1
                globalCost = 200;
                break;
            case Catapult:
                o = new TowerObject(ItemEnum.Tower.Rock);
                o.addGems(User.GEM_TYPE.RED, 2);
                o.addGems(User.GEM_TYPE.BLUE, 1);
                components.add(o);//2 0 1
                o = new TowerObject(ItemEnum.Tower.Range);
                o.addGems(User.GEM_TYPE.RED, 2);
                o.addGems(User.GEM_TYPE.BLUE, 1);
                components.add(o);//2 0 1
                globalCost = 200;
                break;

        }
    }

    @Override
    public String getTooltip() {
        String s = new String();
        if(getComponents().size > 0) {//
            for (int i = 0; i < getComponents().size; i++){
                s += getComponents().get(i).getPrototype().getName() + " " + getComponents().get(0).getSimplyGemStatString();
                if(i + 1 < getComponents().size) {//
                    s += System.getProperty("line.separator");
                }
            }
        }

        return s;

    }

    @Override
    public String getName() {
        return "Recipe: " + getTower().getName();
    }
}
