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

    public static Recipe loadSaveCode(String savecode){
        return new Recipe(ItemEnum.Tower.valueOf(savecode));
    }

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

    @Override
    public String getSaveCode() {
        return getClass().getSimpleName() + "-" + getTower().name();
    }

    private void initComponents(ItemEnum.Tower t){//
        components = new Array<TowerObject>();
        globalCost = t.getGlobalCost();
        TowerObject o;//
        switch (t){
            case Rock:
                components.add(new TowerObject(ItemEnum.Tower.Basic, 3, 1, 1));
                break;
            case Arrow:
                components.add(new TowerObject(ItemEnum.Tower.Basic, 1, 3, 1));
                break;
            case Range:
                components.add(new TowerObject(ItemEnum.Tower.Basic, 1, 1, 3));
                break;
            case Short:
                components.add(new TowerObject(ItemEnum.Tower.Rock, 2, 1, 1));
                //+powder
                break;
            case Mountain:
                components.add(new TowerObject(ItemEnum.Tower.Rock, 4, 0, 0));
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
