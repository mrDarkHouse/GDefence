package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Model.Level.*;

import java.math.BigDecimal;

public class LevelMap implements Screen {
    private SpriteBatch batch;
    private Stage stage;
    private ShapeRenderer shape;
    //private Map map;
    private int number;

    private static Level level;
    public static LevelMap levelMap;//debug

    public static Level getLevel() {
        return level;
    }


    public LevelMap(int number) {
        this.number = number;
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
        System.out.println("hide");
        dispose();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();
        levelMap = this;
        level = new Level(number);
        initHpMpBar();
        initWavePanel();
        initShop();

        Gdx.input.setInputProcessor(stage);


        level.start();


    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(AssetLoader.levelMapBg, 0, 0);
        level.render(delta, batch);
        //drawNextWavePanel(delta, batch);
        //drawHpMpBar();
        batch.end();
        stage.act(delta);
        stage.draw();

    }
    private void initHpMpBar(){
        stage.addActor(new HealthBar(Gdx.graphics.getWidth(), 60, 0, Gdx.graphics.getHeight() - 60));
        stage.addActor(new EnegryBar(60, Gdx.graphics.getHeight() - 60, 0, 0));
    }
    private void initWavePanel(){
        NextWaveInfoPanel panel = new NextWaveInfoPanel();
        panel.setPosition(Gdx.graphics.getWidth() - 280, 140);
        stage.addActor(panel);

        CurrentWaveInfoPanel cpanel = new CurrentWaveInfoPanel(panel.getNextWaveTimer());
        cpanel.setPosition(Gdx.graphics.getWidth() - 280, 140);
        stage.addActor(cpanel);

    }
    private void initShop(){
        LevelShopPanel shop = new LevelShopPanel();

    }



    //private void drawNextWavePanel(float delta, SpriteBatch batch){
    //    drawTimer(delta, batch);
        //NextWaveInfoPanel npanel = new NextWaveInfoPanel();
        //npanel.draw(batch, 1);
    //}



    //private void drawTimer(float delta, SpriteBatch batch){
    //    if(!level.isInWave()) {
//            float time = level.timeBetweenWaves[level.currentWave];
//            time = new BigDecimal(time).setScale(2, BigDecimal.ROUND_UP).floatValue();

            //NextWaveInfoPanel npanel = new NextWaveInfoPanel();
            //npanel.draw(batch, 1);

            //Label l = new Label("" + time, AssetLoader.getTimerSkin());
            //l.setPosition(Gdx.graphics.getWidth() - 240, 30);
            //l.draw(batch, 1);
    //    }

    //}



    @Override
    public void dispose() {
        batch.dispose();
        //stage.dispose();
        //level = null;

    }
}
