package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.Inventory;
import com.darkhouse.gdefence.InventorySystem.inventory.LevelShopPanel;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Model.Level.*;

public class LevelMap extends AbstractScreen {
    private SpriteBatch batch;
    private ShapeRenderer shape;
    //private Map map;
    private int number;
    private Inventory inventory;

    private NextWaveInfoPanel nWPanel;
    private PathSigner pathSigner;

    private MapTileActor[][] tileActors;

    public MapTileActor[][] getTiles() {
        return tileActors;
    }

    private static Level level;
    public static LevelMap levelMap;//debug

    public static Level getLevel() {
        return level;
    }


    public LevelMap(int number, Inventory inventory) {
        this.number = number;
        this.inventory = inventory;
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
        dispose();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();
        levelMap = this;
        Gdx.input.setInputProcessor(stage);

        level = new Level(number, this);
        initHpMpBar();
        initWavePanel();
        initMapTileActors();
        initShop(inventory);




        level.start();


    }
    private void initMapTileActors(){
        tileActors = new MapTileActor[Level.getMap().getTiles().length][Level.getMap().getTiles()[0].length];
        for (int i = 0; i < Level.getMap().getTiles().length; i++){
            for (int j = 0; j < Level.getMap().getTiles()[0].length; j++){
                tileActors[i][j] = new MapTileActor(Level.getMap().getTiles()[i][j]);
                stage.addActor(tileActors[i][j]);
            }
        }
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(GDefence.getInstance().assetLoader.get("LevelMapBg.png", Texture.class), 0, 0);
        level.render(delta, batch);
        //drawNextWavePanel(delta, batch);
        //drawHpMpBar();
        batch.end();
        stage.act(delta);
        stage.draw();

    }
    private void initHpMpBar(){
        stage.addActor(new HealthBar(Gdx.graphics.getWidth(), 55, 0, Gdx.graphics.getHeight() - 55));
        stage.addActor(new EnegryBar(55, Gdx.graphics.getHeight() - 55, 0, 0));
    }
    private void initWavePanel(){
        nWPanel = new NextWaveInfoPanel();
        nWPanel.setPosition(Gdx.graphics.getWidth() - 280, 140);
        stage.addActor(nWPanel);

        pathSigner = new PathSigner(nWPanel.getNextWaveTimer(), level.getMap().getPaths());//static
        stage.addActor(pathSigner);

        CurrentWaveInfoPanel cpanel = new CurrentWaveInfoPanel(nWPanel.getNextWaveTimer());
        cpanel.setPosition(Gdx.graphics.getWidth() - 280, 140);
        stage.addActor(cpanel);

    }
    private void initShop(Inventory inventory){
        LevelShopPanel shop = new LevelShopPanel(inventory);

        shop.addTarget(Level.getMap().getTiles());
        stage.addActor(shop);
        shop.init();

    }
    public void update(){//when new wave is started
        nWPanel.hasChanged();
        pathSigner.initTextures();
    }

    @Override
    public void dispose() {
        batch.dispose();
        //stage.dispose();
        //level = null;

    }
}
