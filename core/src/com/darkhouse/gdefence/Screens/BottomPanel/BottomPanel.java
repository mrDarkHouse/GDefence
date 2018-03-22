package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Model.LevelButton;
import com.darkhouse.gdefence.Model.Panels.GemPanel;
import com.darkhouse.gdefence.Model.Panels.GoldPanel;
import com.darkhouse.gdefence.Model.Panels.UserPanel;

public class BottomPanel extends Table {
    private ShapeRenderer sr;
    private final int lineWidth = 4;

    public BottomPanel() {
        super(GDefence.getInstance().assetLoader.getSkin());
        sr = new ShapeRenderer();
//        debug();

        init();
    }

    private void init(){
        ImageButton[] bottomPanel = new ImageButton[5];


        int bottomButtonsSize[] = {GDefence.WIDTH/5, GDefence.HEIGHT/5};
        defaults().size(bottomButtonsSize[0], bottomButtonsSize[1]);
        setPosition(bottomButtonsSize[0] - lineWidth, 0);




        bottomPanel[0] = new ImageButton(GDefence.getInstance().assetLoader.getBottomPanelSkin(1));
        //bottomPanel[0].setSize(bottomButtonsSize[0], bottomButtonsSize[1]);
        //bottomPanel[0].setPosition(0, 0);
        bottomPanel[0].addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                GDefence.getInstance().switchScreen(GDefence.getInstance().getArsenal());
                return true;
            }
        });


        bottomPanel[1] = new ImageButton(GDefence.getInstance().assetLoader.getBottomPanelSkin(2));
        //bottomPanel[1].setSize(bottomButtonsSize[0], bottomButtonsSize[1]);
        //bottomPanel[1].setPosition(bottomButtonsSize[0], 0);
        bottomPanel[1].addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                GDefence.getInstance().switchScreen(GDefence.getInstance().getStore());
                return true;
            }
        });


        bottomPanel[2] = new ImageButton(GDefence.getInstance().assetLoader.getBottomPanelSkin(3));
        //bottomPanel[2].setSize(bottomButtonsSize[0], bottomButtonsSize[1]);
        //bottomPanel[2].setPosition(bottomButtonsSize[0]*2, 0);
        bottomPanel[2].addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                GDefence.getInstance().switchScreen(GDefence.getInstance().getSmith());
                return true;
            }
        });

        //stage.addActor(backButton);
//        align(Align.center);
        add(bottomPanel[0]);
        add(bottomPanel[1]);
        add(bottomPanel[2]);
        pack();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
//        batch.end();
//        sr.begin(ShapeRenderer.ShapeType.Filled);
//        sr.setProjectionMatrix(batch.getProjectionMatrix());
//        sr.setColor(0, 0, 0, 1);
//        sr.rectLine(GDefence.WIDTH/5 - lineWidth*1.5f,  GDefence.HEIGHT/5 + lineWidth,   GDefence.WIDTH/5*4 - lineWidth * 0.5f,   GDefence.HEIGHT/5 + lineWidth, lineWidth);
//        sr.rectLine(GDefence.WIDTH/5 - lineWidth,       GDefence.HEIGHT/5 + lineWidth,   GDefence.WIDTH/5 - lineWidth,            0, lineWidth);
//        sr.rectLine(GDefence.WIDTH/5 * 2 - lineWidth,   GDefence.HEIGHT/5 + lineWidth,   GDefence.WIDTH/5 * 2 - lineWidth,        0, lineWidth);
//        sr.rectLine(GDefence.WIDTH/5 * 3 - lineWidth,   GDefence.HEIGHT/5 + lineWidth,   GDefence.WIDTH/5 * 3 - lineWidth,        0, lineWidth);
//        sr.rectLine(GDefence.WIDTH / 5 * 4 - lineWidth, GDefence.HEIGHT / 5 + lineWidth, GDefence.WIDTH / 5 * 4 - lineWidth,      0, lineWidth);
//        sr.end();
//        batch.begin();
    }
}
