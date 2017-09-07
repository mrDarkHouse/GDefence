package com.darkhouse.gdefence.InventorySystem.inventory.Tooltip;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
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
        AssetLoader l = GDefence.getInstance().assetLoader;
        getTitleLabel().setText(l.getWord(gradable.getName().replaceAll(" ", "").toLowerCase()));
        getTitleLabel().setAlignment(Align.center);
        clear();
        String currentLvl;
        String cost;
        String value;

        if(!gradable.isMaxLevel()) {
            currentLvl = l.getWord("level") + ": " + gradable.getCurrentLevel()
                    + System.getProperty("line.separator");
            value = l.getWord("value") + ": " + gradable.getCurrentValue() + "(" + gradable.getNextValue() + ")"
                    + System.getProperty("line.separator") ;
            cost = l.getWord("cost") + ": " + gradable.getNextCost();
        }else {
            currentLvl = l.getWord("max") + System.getProperty("line.separator");
            value = l.getWord("value") + ": " + gradable.getCurrentValue();
            cost = "";
        }
        Label label = new Label(currentLvl  + value + cost, skin, "description");
        add(label);
        pack();
    }


}
