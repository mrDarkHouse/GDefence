package com.darkhouse.gdefence.InventorySystem.inventory.Tooltip;


import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Objects.GameObject;
import com.darkhouse.gdefence.Objects.TowerObject;
import com.darkhouse.gdefence.Screens.AbstractCampainScreen;
import com.darkhouse.gdefence.User;

public class GemGradeTooltip extends AbstractTooltip{

    private Skin skin;
    private User.GEM_TYPE gemType;
    private GemGradable g;
    private Label upLabel;
//    private String s = "";

    public GemGradeTooltip(Stage stage, GemGradable g, User.GEM_TYPE gemType, Skin skin) {
        super("Grade", skin);
        this.gemType = gemType;
        this.skin = skin;
        this.g = g;
        init();
        setVisible(false);
        //((AbstractCampainScreen) GDefence.getInstance().getScreen()).getStage().addActor(this);
        //GDefence.getInstance().getSmith().getStage().addActor(this);//haven't direct access to stage
        stage.addActor(this);
    }

    private void init(){
        getTitleLabel().setText(GDefence.getInstance().assetLoader.getWord("upgrade") + " " + gemType.getName() + " " + g.getGradeNumberInfo(gemType));
        getTitleLabel().setAlignment(Align.center);
        upLabel = new Label(g.getGemGradeTooltip(gemType), skin, "description");
        upLabel.setAlignment(Align.center);
        upLabel.getStyle().font.getData().markupEnabled = true;
        add(upLabel);
        pack();
    }

    public void hasChanged(){
        getTitleLabel().setText(GDefence.getInstance().assetLoader.getWord("upgrade") + " " + gemType.getName() + " " + g.getGradeNumberInfo(gemType));
        upLabel.setText(g.getGemGradeTooltip(gemType));
        pack();
    }


}
