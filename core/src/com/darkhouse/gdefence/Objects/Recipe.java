package com.darkhouse.gdefence.Objects;


import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.User;

public class Recipe extends DetailObject{
    private ItemEnum.Tower tower;

    public ItemEnum.Tower getTower() {
        return tower;
    }

    private Array <TowerObject> components;
    private Array <User.Research> researches;
//    private int recipeCost;

    public static Recipe loadSaveCode(String savecode){
        return new Recipe(ItemEnum.Tower.valueOf(savecode));
    }

    @Override
    public int getGlobalCost() {
        return tower.getRecipeCost();
    }

    public Array<TowerObject> getComponents() {
        return components;
    }
    public Array<User.Research> getResearches() {
        return researches;
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

    private void initComponents(ItemEnum.Tower t){
        components = t.getComponents();
//        recipeCost = t.getRecipeCost();
        researches = t.getResearchNeed();
    }

    @Override
    public String getTooltip() {
        AssetLoader l = GDefence.getInstance().assetLoader;
        String s = "";
        if(getTower().getAbilities().size != 0){
            s += FontLoader.colorString(l.getWord("spells") + ":", 9);
            s += System.getProperty("line.separator");
        }
        for (Ability.AbilityPrototype a:getTower().getAbilities()){
            s += a.getName();
            s += System.getProperty("line.separator");
        }
        s += FontLoader.colorString(l.getWord("need") + ":", 9);
        s += System.getProperty("line.separator");
//        if(getComponents().size > 0) {//
        for (int i = 0; i < getComponents().size; i++){
            s += getComponents().get(i).getPrototype().getName() + " " + getComponents().get(i).getSimplyGemStatString();
            if(i + 1 < getComponents().size) {//
                s += System.getProperty("line.separator");
            }
        }
        if(getResearches().size > 0 && getComponents().size > 0) s += System.getProperty("line.separator");
        for (int i = 0; i < getResearches().size; i++){
            s += researches.get(i).getName() /*+ " (" + l.getWord("research") + ")"*/;
            if(i + 1 < getResearches().size) {
                s += System.getProperty("line.separator");
            }
        }

//        }

        return s;

    }

    @Override
    public String getName() {
        return GDefence.getInstance().assetLoader.getWord("recipe") + ": " + getTower().getName();
    }
}
