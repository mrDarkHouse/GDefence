package com.darkhouse.gdefence.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Model.LevelButton;
import com.darkhouse.gdefence.Model.Panels.ExpBar;
import com.darkhouse.gdefence.Model.Panels.GemPanel;
import com.darkhouse.gdefence.Model.Panels.UserPanel;
import com.darkhouse.gdefence.Screens.BottomPanel.Arsenal;
import com.darkhouse.gdefence.Screens.BottomPanel.Smith;
import com.darkhouse.gdefence.Screens.BottomPanel.Store;

public class CampainMap extends AbstractCampainScreen {
    //private GDefence mainClass;

    //private SpriteBatch batch;
    //private ShapeRenderer shape;

    //private Stage stage;


    public CampainMap(GDefence mainClass) {
        super("Campain", mainClass);
        //batch = new SpriteBatch();
        //shape = new ShapeRenderer();
        //this.mainClass = mainClass;
        loadButtons();
        loadFrames();
    }

    private void loadButtons(){

        //Viewport viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //stage = new Stage(viewport,batch);
        //Gdx.input.setInputProcessor(stage);

        //ImageButton backButton;
        ImageButton[] bottomPanel = new ImageButton[5];
        //ImageButton[] levels = new ImageButton[5];

        //int backButtonsSize[] = {64, 64};
        int bottomButtonsSize[] = {Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/5};

        int buttons = 5;


//        ImageButton.ImageButtonStyle backButtonStyle = new ImageButton.ImageButtonStyle();
//        backButtonStyle.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.backButton));
//        backButtonStyle.over = new TextureRegionDrawable(new TextureRegion(AssetLoader.backButton));
//        backButtonStyle.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.backButton));
//        backButton = new ImageButton(backButtonStyle);
//        backButton.setSize(backButtonsSize[0], backButtonsSize[1]);
//        backButton.setPosition(20, Gdx.graphics.getHeight() - backButtonsSize[1] - 20);
//        backButton.addListener(new InputListener(){
//            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                mainClass.setScreen(new MainMenu(mainClass));
//                return true;
//            }
//        });


        ImageButton.ImageButtonStyle arsenalButtonStyle = new ImageButton.ImageButtonStyle();
        arsenalButtonStyle.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainMap[0]));
        arsenalButtonStyle.over = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainMap[0]));
        arsenalButtonStyle.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainMap[0]));
        bottomPanel[0] = new ImageButton(arsenalButtonStyle);
        bottomPanel[0].setSize(bottomButtonsSize[0], bottomButtonsSize[1]);
        bottomPanel[0].setPosition(0, 0);
        bottomPanel[0].addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                mainClass.setScreen(new Arsenal(mainClass));
                return true;
            }
        });

        ImageButton.ImageButtonStyle storeButtonStyle = new ImageButton.ImageButtonStyle();
        storeButtonStyle.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainMap[1]));
        storeButtonStyle.over = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainMap[1]));
        storeButtonStyle.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainMap[1]));
        bottomPanel[1] = new ImageButton(storeButtonStyle);
        bottomPanel[1].setSize(bottomButtonsSize[0], bottomButtonsSize[1]);
        bottomPanel[1].setPosition(bottomButtonsSize[0], 0);
        bottomPanel[1].addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                mainClass.setScreen(new Store(mainClass));
                return true;
            }
        });

        ImageButton.ImageButtonStyle smithButtonStyle = new ImageButton.ImageButtonStyle();
        smithButtonStyle.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainMap[2]));
        smithButtonStyle.over = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainMap[2]));
        smithButtonStyle.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainMap[2]));
        bottomPanel[2] = new ImageButton(smithButtonStyle);
        bottomPanel[2].setSize(bottomButtonsSize[0], bottomButtonsSize[1]);
        bottomPanel[2].setPosition(bottomButtonsSize[0]*2, 0);
        bottomPanel[2].addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                mainClass.setScreen(new Smith(mainClass));
                return true;
            }
        });
//        ImageButton.ImageButtonStyle levelsStyle[] = new ImageButton.ImageButtonStyle[5];
//        levelsStyle[0] = new ImageButton.ImageButtonStyle();
//        levelsStyle[0].up = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainLevels[0][0]));
//        levelsStyle[0].over = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainLevels[0][1]));
//        levelsStyle[0].down = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainLevels[0][1]));
//        levelsStyle[1] = new ImageButton.ImageButtonStyle();
//        levelsStyle[1].up = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainLevels[1][0]));
//        levelsStyle[1].over = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainLevels[1][1]));
//        levelsStyle[1].down = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainLevels[1][1]));
//        levelsStyle[2] = new ImageButton.ImageButtonStyle();
//        levelsStyle[2].up = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainLevels[2][0]));
//        levelsStyle[2].over = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainLevels[2][1]));
//        levelsStyle[2].down = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainLevels[2][1]));
//        levelsStyle[3] = new ImageButton.ImageButtonStyle();
//        levelsStyle[3].up = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainLevels[3][0]));
//        levelsStyle[3].over = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainLevels[3][1]));
//        levelsStyle[3].down = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainLevels[3][1]));
//        levelsStyle[4] = new ImageButton.ImageButtonStyle();
//        levelsStyle[4].up = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainLevels[4][0]));
//        levelsStyle[4].over = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainLevels[4][1]));
//        levelsStyle[4].down = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainLevels[4][1]));
//
//
//        for (int i = 0; i < 5; i++){
//            levels[i] = new ImageButton(levelsStyle[i]);
//            levels[i].setSize(levelButtonsSize[0], levelButtonsSize[1]);
//            levels[i].setPosition(borderSize + (levelButtonsSize[0] + sizeBetween)*i, Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/8);
//            levels[i].addListener(new InputListener(){
//                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//
//                    return true;
//                }
//            });
//            stage.addActor(levels[i]);
//        }
        final LevelButton[] levels = new LevelButton[5];
        int borderSize = Gdx.graphics.getWidth()/4;
        int sizeBetween = Gdx.graphics.getWidth()/42;
        int levelButtonsSize[] = new int[2];
        levelButtonsSize[0] = (Gdx.graphics.getWidth() - (borderSize * 2 + sizeBetween * (buttons - 1))) / buttons;
        levelButtonsSize[1] = levelButtonsSize[0];

        for (int i = 0; i < 5; i++){
            levels[i] = new LevelButton(i + 1, mainClass);
            if(!mainClass.user.levelsAvailable[i]){
                levels[i].lock();
            }
            levels[i].setSize(levelButtonsSize[0], levelButtonsSize[1]);
            levels[i].setPosition(borderSize + (levelButtonsSize[0] + sizeBetween)*i, Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/8);

            stage.addActor(levels[i]);
        }


        int userPanelSize[] = {300, 120};
        stage.addActor(new UserPanel(Gdx.graphics.getWidth() - userPanelSize[0], Gdx.graphics.getHeight() - userPanelSize[1] - topPadSize - 2, userPanelSize[0], userPanelSize[1], mainClass));
        //System.out.println(Gdx.graphics.getHeight() - userPanelSize[1] - topPadSize);
        stage.addActor(new GemPanel(30, 460, 250, 180, mainClass));

        //stage.addActor(bar);
        //stage.addActor(userlevelButton);



        //stage.addActor(backButton);
        stage.addActor(bottomPanel[0]);
        stage.addActor(bottomPanel[1]);
        stage.addActor(bottomPanel[2]);






    }


    private void loadFrames(){
        //Gdx.gl.glLineWidth(2);
        //ShapeRenderer r = new ShapeRenderer();
        //r.line();

    }



    @Override
    public void render(float delta) {
        super.render(delta);

        drawLines();
    }
    private void drawLines(){
        int lineWidth = 6;      //KOSTIl'
        Gdx.gl.glLineWidth(lineWidth);
        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.setProjectionMatrix(batch.getProjectionMatrix());
        shape.setColor(0, 0, 0, 1);
        shape.line(0, Gdx.graphics.getHeight()/5 + lineWidth, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/5 + lineWidth);
        shape.line(Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/5 + lineWidth, Gdx.graphics.getWidth()/5 , 0);
        shape.line(Gdx.graphics.getWidth()/5 * 2, Gdx.graphics.getHeight()/5 + lineWidth, Gdx.graphics.getWidth()/5 * 2 , 0);
        shape.line(Gdx.graphics.getWidth()/5 * 3, Gdx.graphics.getHeight()/5 + lineWidth, Gdx.graphics.getWidth()/5 * 3, 0);
        shape.line(Gdx.graphics.getWidth() / 5 * 4, Gdx.graphics.getHeight() / 5 + lineWidth, Gdx.graphics.getWidth() / 5 * 4, 0);

        shape.end();
    }




    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
        //Gdx.input.setInputProcessor(null);
        //this.dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }
}
