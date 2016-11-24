package com.darkhouse.gdefence.InventorySystem.inventory.Tooltip;


import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Screens.AbstractCampainScreen;
import com.darkhouse.gdefence.User;

public class GemGradeTooltip extends Window {

    private Skin skin;
    private User.GEM_TYPE gemType;
    private Label upLabel;
    private String s = "";

    public GemGradeTooltip(User.GEM_TYPE gemType, Skin skin) {
        super("Grade...", skin);
        this.gemType = gemType;
        this.skin = skin;
        init();
        setVisible(false);
        ((AbstractCampainScreen) GDefence.getInstance().getScreen()).getStage().addActor(this);
    }

    private void init(){
        getTitleLabel().setText("Upgrade " + gemType.name());
        getTitleLabel().setAlignment(Align.center);
        setText();
        upLabel = new Label(s, skin);
        add(upLabel);
        pack();
    }
    private void setText(){
        switch (gemType){
            case RED:
                s = "+ " + User.GEM_TYPE.getBoost(gemType) + " dmg";
                break;
            case YELLOW:
                s = "+ " + User.GEM_TYPE.getBoost(gemType) + " as";
                break;
            case BLUE:
                s = "+ " + User.GEM_TYPE.getBoost(gemType) + " range";
                break;
            case BLACK:
                //
            case GREEN:
                //
            case WHITE:
                //
        }
    }

    public void hasChanged(){
        //when level need write this it's update
    }


}
