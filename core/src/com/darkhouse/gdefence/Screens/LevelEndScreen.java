package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.StatManager;
import com.darkhouse.gdefence.InventorySystem.inventory.*;
import com.darkhouse.gdefence.Model.Panels.ResultPanel;
import com.darkhouse.gdefence.Objects.DetailObject;
import com.darkhouse.gdefence.User;

public class LevelEndScreen implements Screen{
    private SpriteBatch batch;
    private Stage stage;
    private TextButton continueButton;
    private String result;
    private boolean isWin;
    private Texture backGround;

    private StatManager statManager;

    public LevelEndScreen(boolean isWin, StatManager statManager) {
        this.isWin = isWin;
        this.statManager = statManager;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();

        init(isWin);

        Gdx.input.setInputProcessor(stage);
    }

    private void init(boolean isWin){
        if(isWin){
            backGround = GDefence.getInstance().assetLoader.get("levelWinBg.png", Texture.class);
            result = "Win";
        }else {
            backGround = GDefence.getInstance().assetLoader.get("levelLooseBg.png", Texture.class);
            result = "Loose";
        }

        Table table = new Table();
        table.setWidth(stage.getWidth());
        table.setHeight(stage.getHeight());
        table.align(Align.center|Align.top);
        table.setPosition(0, 0);//Gdx.graphics.getHeight());

        Label resultLabel = new Label(result, AssetLoader.getLevelEndResultSkin());
        resultLabel.setText(result);

        table.add(resultLabel).padTop(70).padBottom(100).row();


        continueButton = new TextButton("Continue", GDefence.getInstance().assetLoader.getSkin());
        //continueButton.setSize(200, 40);
        continueButton.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                GDefence.getInstance().switchScreen(GDefence.getInstance().getCampainMap());
                return true;
            }
        });
        //continueButton.align(Align.center);
        continueButton.align(Align.bottom);
        //continueButton.pack();

        ResultPanel rPanel = new ResultPanel(isWin, statManager);

        //table.add(rPanel).spaceBottom(100).row();
        table.add(rPanel).padBottom(20).row();


//        Array<DropSlot> drop = statManager.getDrop();
        DropInventoryActor invActor = null;
        if(isWin) {
             invActor = new DropInventoryActor(statManager.getDrop(),
                    GDefence.getInstance().assetLoader.getSkin());

            table.add(invActor).padBottom(20);


            table.row();
        }

        table.add(continueButton).size(150, 40).align(Align.bottom);


        //stage.addActor(continueButton);
        //stage.addActor(rPanel);

        stage.addActor(table);
        if(isWin) invActor.init();






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
        Gdx.input.setInputProcessor(null);
//        dispose();
    }

    @Override
    public void dispose() {
        GDefence.getInstance().assetLoader.disposeLevelEndScreen();
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(backGround, 0, 0);
        batch.end();
        //stage.getActors().get(0).debug();

        stage.act(delta);
        stage.draw();
    }


}
