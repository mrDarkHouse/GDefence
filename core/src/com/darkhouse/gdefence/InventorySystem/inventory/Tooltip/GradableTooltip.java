package com.darkhouse.gdefence.InventorySystem.inventory.Tooltip;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.User;

public class GradableTooltip extends AbstractTooltip {
    private Skin skin;
    private User.Gradable gradable;

    public GradableTooltip(Stage stage, User.Gradable gradable, Skin skin) {
        super("Tooltip...", skin);
        this.skin = skin;
        this.gradable = gradable;
        hasChanged();
        setVisible(false);
        //((AbstractCampainScreen) GDefence.getInstance().getScreen()).getStage().addActor(this);
        stage.addActor(this);
//        GDefence.getInstance().getSmith().getStage().addActor(this);//haven't direct access to stage
    }

    public void hasChanged() {
        getTitleLabel().setText(gradable.getName());
        getTitleLabel().setAlignment(Align.center);
        clear();
        String currentLvl;
        String cost;
        String value;

        if(!gradable.isMaxLevel()) {
            currentLvl = "Level: " + gradable.getCurrentLevel()
                    + System.getProperty("line.separator");
            value = "Value: " + gradable.getCurrentValue() + "(" + gradable.getNextValue() + ")"
                    + System.getProperty("line.separator") ;
            cost = "Cost: " + gradable.getNextCost();
        }else {
            currentLvl = "MAX" + System.getProperty("line.separator");
            value = "Value: " + gradable.getCurrentValue();
            cost = "";
        }
        Label label = new Label(currentLvl  + value + cost, skin);
        add(label);
        pack();
    }


}
