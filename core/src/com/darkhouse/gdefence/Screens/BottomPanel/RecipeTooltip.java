package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Screens.AbstractCampainScreen;

public class RecipeTooltip extends Window{
    private Skin skin;
    private Recipe recipe;
    private boolean isLocked = true;

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public RecipeTooltip(Recipe recipe, Skin skin) {
        super("Recipe...", skin);

        this.recipe = recipe;
        this.skin = skin;
        init();
        setVisible(false);
        ((AbstractCampainScreen) GDefence.getInstance().getScreen()).getStage().addActor(this);
    }


    private void init(){
        getTitleLabel().setText("Buy " + recipe.getTower().getName() + " recipe");
        getTitleLabel().setAlignment(Align.center);
        //setText();
        if(recipe.getComponents().size > 0) {
            Label components = new Label(recipe.getComponents().get(0).getName() + " ", skin);
            add(components).row();
        }
        Label cost = new Label("500 gold", skin);
        add(cost);
        pack();
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if(isLocked){
            super.setVisible(false);
        }
    }
}
