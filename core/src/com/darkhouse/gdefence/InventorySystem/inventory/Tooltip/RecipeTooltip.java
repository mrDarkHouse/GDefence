package com.darkhouse.gdefence.InventorySystem.inventory.Tooltip;


import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Objects.Recipe;

public class RecipeTooltip extends AbstractTooltip{
    private Skin skin;
    private Recipe recipe;
    private boolean isLocked = true;

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public RecipeTooltip(Recipe recipe, Skin skin) {
        super("Recipe", skin);

        this.recipe = recipe;
        this.skin = skin;
        init();
        setVisible(false);
//        ((AbstractCampainScreen) GDefence.getInstance().getScreen()).getStage().addActor(this);
        GDefence.getInstance().getArsenal().getStage().addActor(this);//haven't direct access to stage
    }


    private void init(){
        getTitleLabel().setText(GDefence.getInstance().assetLoader.getWord("buy") + " " + recipe.getName());
        getTitleLabel().setAlignment(Align.center);

        AssetLoader l = GDefence.getInstance().assetLoader;


        String c = FontLoader.colorString(l.getWord("cost") + ":", 9) + System.getProperty("line.separator") + recipe.getGlobalCost() + " " + GDefence.getInstance().assetLoader.getWord("gold2");
        Label cost = new Label(c, skin, "description");
        add(cost).align(Align.left).row();
        add(new Label(recipe.getTooltip(), skin, "description"));
        //setText();
//        if(recipe.getComponents().size > 0) {
//            if(recipe.getComponents().size == 1) {
//                Label components = new Label(recipe.getComponents().get(0).getPrototype().getName() + " " + recipe.getComponents().get(0).getSimplyGemStatString(), skin);
//                add(components).row();
//            }else if(recipe.getComponents().size == 2) {
//                Label components = new Label(recipe.getComponents().get(0).getPrototype().getName() + " " + recipe.getComponents().get(0).getSimplyGemStatString() +
//                      " + " + System.getProperty("line.separator") + recipe.getComponents().get(1).getPrototype().getName() + " " +
//                      recipe.getComponents().get(1).getSimplyGemStatString(), skin);
//                add(components).row();
//            }
//        }


        pack();
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if(isLocked){
            super.setVisible(false);
        }
    }

    @Override
    public void hasChanged() {

    }
}
