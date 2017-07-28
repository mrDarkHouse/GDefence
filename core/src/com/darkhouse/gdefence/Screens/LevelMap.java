package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.*;
import com.darkhouse.gdefence.Level.Ability.Spell.Spell;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Model.Level.*;
import com.darkhouse.gdefence.Objects.SpellObject;
import com.darkhouse.gdefence.Objects.TowerObject;

import java.util.Arrays;

public class LevelMap extends AbstractScreen {
    private SpriteBatch batch;
    private ShapeRenderer shape;
    //private Map map;
    private int number;
    private Inventory towers;

    private NextWaveInfoPanel nWPanel;
    private CurrentWaveInfoPanel cWpanel;
    private PathSigner pathSigner;

    private MapTileActor[][] tileActors;

    public MapTileActor[][] getTiles() {
        return tileActors;
    }

    private static Level level;
    public static LevelMap levelMap;//debug
    private SpellPanel spellPanel;

    public static Level getLevel() {
        return level;
    }


    public LevelMap(int number, Inventory towers, Inventory spells) {
        this.number = number;
        this.towers = towers;

        batch = new SpriteBatch();
        stage = new Stage();
        levelMap = this;
        level = new Level(number, this);
        initHpMpBar();
        initWavePanel();
        initMapTileActors();
        initTextures();//after loading textures
        initShop(towers);
        initSpellPanel(spells);
    }

    private void initTextures(){
        Level.getMap().initBaseTextures();
        Level.getMap().normalizeBlocks();
    }

//    public void init(int number, Inventory inventory){
//        this.number = number;
//        this.inventory = inventory;
//    }

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
//        batch = new SpriteBatch();
//        stage = new Stage();
//        levelMap = this;
        Gdx.input.setInputProcessor(stage);
//        initTextures();

//        level = new Level(number, this);
//        initHpMpBar();
//        initWavePanel();
//        initMapTileActors();
//        initShop(inventory);




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
        nWPanel.init();

        pathSigner = new PathSigner(nWPanel.getNextWaveTimer(), level.getMap().getPaths());//static
        stage.addActor(pathSigner);

        cWpanel = new CurrentWaveInfoPanel(nWPanel.getNextWaveTimer());
        cWpanel.setVisible(false);
        cWpanel.setPosition(Gdx.graphics.getWidth() - 280, 140);
        stage.addActor(cWpanel);

    }
    private void initShop(Inventory towers){
        LevelShopPanel shop = new LevelShopPanel(towers);

        shop.addTarget(Level.getMap().getTiles());
        shop.setPosition(90, 10);
        stage.addActor(shop);
        shop.init();

    }
    private void initSpellPanel(Inventory spells){
        Array<SpellObject> a = new Array<SpellObject>();
        for (Slot s:spells.getSlots()){
            if(!s.isEmpty() && TowerObject.isMatches(s.getLast().getClass(), SpellObject.class)){
                a.add(((SpellObject) s.getLast()));
            }
        }


        spellPanel = new SpellPanel(a);

        spellPanel.setPosition(1000, 500);
        stage.addActor(spellPanel);
        spellPanel.init();

    }
    public void actSpellPanel(float delta){
        spellPanel.act(delta);
    }
    public void updateEnd(){//when new wave is ended
        nWPanel.hasChanged();
        cWpanel.setVisible(false);
        nWPanel.setVisible(true);
        pathSigner.initTextures();
    }
    public void updateStart(){//when new wave is started
//        nWPanel.hasChanged();
        nWPanel.setVisible(false);
        cWpanel.setVisible(true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        //stage.dispose();
        //level = null;

    }
}
