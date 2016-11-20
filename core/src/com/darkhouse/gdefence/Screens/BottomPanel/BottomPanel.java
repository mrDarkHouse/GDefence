package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Model.LevelButton;
import com.darkhouse.gdefence.Model.Panels.GemPanel;
import com.darkhouse.gdefence.Model.Panels.GoldPanel;
import com.darkhouse.gdefence.Model.Panels.UserPanel;

public class BottomPanel extends Table {

    public BottomPanel() {
        super(AssetLoader.getSkin());

        init();
    }

    private void init(){
        ImageButton[] bottomPanel = new ImageButton[5];


        int bottomButtonsSize[] = {Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/5};
        defaults().size(bottomButtonsSize[0], bottomButtonsSize[1]);
        setPosition(0, 0);




        bottomPanel[0] = new ImageButton(GDefence.getInstance().assetLoader.getBottomPanelSkin(1));
        //bottomPanel[0].setSize(bottomButtonsSize[0], bottomButtonsSize[1]);
        //bottomPanel[0].setPosition(0, 0);
        bottomPanel[0].addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                GDefence.getInstance().setScreen(new Arsenal());
                return true;
            }
        });


        bottomPanel[1] = new ImageButton(GDefence.getInstance().assetLoader.getBottomPanelSkin(2));
        //bottomPanel[1].setSize(bottomButtonsSize[0], bottomButtonsSize[1]);
        //bottomPanel[1].setPosition(bottomButtonsSize[0], 0);
        bottomPanel[1].addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                GDefence.getInstance().setScreen(new Store());
                return true;
            }
        });


        bottomPanel[2] = new ImageButton(GDefence.getInstance().assetLoader.getBottomPanelSkin(3));
        //bottomPanel[2].setSize(bottomButtonsSize[0], bottomButtonsSize[1]);
        //bottomPanel[2].setPosition(bottomButtonsSize[0]*2, 0);
        bottomPanel[2].addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                GDefence.getInstance().setScreen(new Smith());
                return true;
            }
        });

        //stage.addActor(backButton);
        add(bottomPanel[0]);
        add(bottomPanel[1]);
        add(bottomPanel[2]);
        pack();
    }
}
