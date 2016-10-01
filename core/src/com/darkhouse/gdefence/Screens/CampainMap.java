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
import com.darkhouse.gdefence.Model.Panels.GoldPanel;
import com.darkhouse.gdefence.Model.Panels.UserPanel;
import com.darkhouse.gdefence.Screens.BottomPanel.Arsenal;
import com.darkhouse.gdefence.Screens.BottomPanel.BottomPanel;
import com.darkhouse.gdefence.Screens.BottomPanel.Smith;
import com.darkhouse.gdefence.Screens.BottomPanel.Store;

public class CampainMap extends AbstractCampainScreen {
    //private GDefence mainClass;

    //private SpriteBatch batch;
    //private ShapeRenderer shape;

    //private Stage stage;


    public CampainMap() {
        super("Campain");
        //batch = new SpriteBatch();
        //shape = new ShapeRenderer();
        //this.mainClass = mainClass;

    }

    @Override
    public void show() {
        super.show();
        loadButtons();
        loadFrames();
    }

    private void loadButtons(){

        //Viewport viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //stage = new Stage(viewport,batch);
        //Gdx.input.setInputProcessor(stage);
        int buttons = 5;

        final LevelButton[] levels = new LevelButton[5];
        int borderSize = Gdx.graphics.getWidth()/4;
        int sizeBetween = Gdx.graphics.getWidth()/42;
        int levelButtonsSize[] = new int[2];
        levelButtonsSize[0] = (Gdx.graphics.getWidth() - (borderSize * 2 + sizeBetween * (buttons - 1))) / buttons;
        levelButtonsSize[1] = levelButtonsSize[0];

        for (int i = 0; i < 5; i++){
            levels[i] = new LevelButton(i + 1);
            if(!GDefence.getInstance().user.getLevelsAvailable(i)){
                levels[i].lock();
            }
            levels[i].setSize(levelButtonsSize[0], levelButtonsSize[1]);
            levels[i].setPosition(borderSize + (levelButtonsSize[0] + sizeBetween)*i, Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/8);

            stage.addActor(levels[i]);
        }


        stage.addActor(new BottomPanel());






    }


    private void loadFrames(){
        //Gdx.gl.glLineWidth(2);
        //ShapeRenderer r = new ShapeRenderer();
        //r.line();

    }



    @Override
    public void render(float delta) {
        super.render(delta);

        //drawLines();
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
