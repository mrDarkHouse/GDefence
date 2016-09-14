package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Model.Panels.ResultPanel;

public class LevelEndScreen implements Screen{
    private SpriteBatch batch;
    private Stage stage;
    private TextButton continueButton;
    private boolean isWin;
    private Texture backGround;

    public LevelEndScreen(boolean isWin) {
        this.isWin = isWin;
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
            backGround = AssetLoader.campainBg;
        }else {
            backGround = AssetLoader.campainBg;
        }

        Table table = new Table();
        table.setWidth(stage.getWidth());
        table.setHeight(stage.getHeight());
        table.align(Align.center|Align.top);
        table.setPosition(0, 0);//Gdx.graphics.getHeight());


        continueButton = new TextButton("Continue", AssetLoader.getSkin());
        continueButton.setSize(200, 40);
        //continueButton.align(Align.center);
        //continueButton.align(Align.bottom);
        //continueButton.pack();

        ResultPanel rPanel = new ResultPanel(isWin);

        //table.add(rPanel).spaceBottom(100).row();
        table.add(continueButton).align(Align.bottom).align(Align.center);


        //stage.addActor(continueButton);
        //stage.addActor(rPanel);

        stage.addActor(table);

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
        dispose();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(backGround, 0, 0);
        batch.end();
        stage.getActors().get(0).debug();

        stage.act(delta);
        stage.draw();
    }


}
