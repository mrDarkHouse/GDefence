package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.*;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Model.Level.*;
import com.darkhouse.gdefence.Model.Panels.NextWaveInfoPanel;
import com.darkhouse.gdefence.Model.Panels.SpellPanel;
import com.darkhouse.gdefence.Objects.SpellObject;
import com.darkhouse.gdefence.Objects.TowerObject;

public class LevelMap extends AbstractScreen {
    private SpriteBatch batch;
    private ShapeRenderer shape;
    //private Map map;
    private int number;
    private Inventory towers;

    private boolean isPaused;
    private Dialog pauseDialog;

    private InputMultiplexer input;

    public boolean isPaused() {
        return isPaused;
    }

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


    private LevelShopPanel shop;

    public static Level getLevel() {
        return level;
    }


    private class PauseDialog extends Dialog{

        public PauseDialog() {
            super("Pause", GDefence.getInstance().assetLoader.getSkin(), "pause-menu");

//            getTitleLabel().getStyle().font = FontLoader.generateFont(26, Color.WHITE);


            padTop(40);
            getButtonTable().defaults().width(150).height(40).space(10);
//            defaults().space(50);
            getTitleLabel().setAlignment(Align.center);

//            Label title = new Label("Pause", getSkin(), "description");
//
//            add(title).row();
            /*add*/button("Continue", 0);//.padBottom(100).row();
            getButtonTable().row();
            /*add*/button("Exit to menu", 1);//.padBottom(100);
        }


        @Override
        protected void result (Object object){
            if (object.equals(0)) {
                hide();
                offPause();
            } else if (object.equals(1)){
                GDefence.getInstance().switchScreen(GDefence.getInstance().getCampainMap());
            }
        }


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
        initPauseDialog();
    }

    public void init(){
        LevelInput levelInput = new LevelInput(this);
        input = new InputMultiplexer();
        input.addProcessor(levelInput);
        input.addProcessor(stage);
    }

    private void initTextures(){
        Level.getMap().initBaseTextures();
        Level.getMap().normalizeBlocks();
    }

    public void setPause(){
        if(!isPaused) {
            isPaused = true;
            pauseDialog.show(stage);
        }
    }
    public void offPause(){
        isPaused = false;
    }

    public void showNotificationTooltips(){

    }
    public void offNotificationTooltips(){

    }

//    public void init(int number, Inventory inventory){
//        this.number = number;
//        this.inventory = inventory;
//    }

//    @Override
//    public void resize(int width, int height) {
//
//    }

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
        Gdx.input.setInputProcessor(input);
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
        if(!isPaused) {
            if (delta < 30) {
                level.physic(delta);

            }
        }
        level.render(batch);
        //drawNextWavePanel(delta, batch);
        //drawHpMpBar();
        batch.end();
//        if(!isPaused) {
            stage.act(delta);
//        }
        stage.draw();

    }
    private void initHpMpBar(){
        stage.addActor(new HealthBar(Gdx.graphics.getWidth(), 55, 0, Gdx.graphics.getHeight() - 55));
        stage.addActor(new EnegryBar(55, Gdx.graphics.getHeight() - 55, 0, 0));
    }
    private void initWavePanel(){
        nWPanel = new NextWaveInfoPanel();
        nWPanel.setPosition(Gdx.graphics.getWidth() - 205, 5);
        stage.addActor(nWPanel);
        nWPanel.init();

        pathSigner = new PathSigner(nWPanel.getNextWaveTimer(), level.getMap().getPaths());//static
        stage.addActor(pathSigner);

        cWpanel = new CurrentWaveInfoPanel(nWPanel.getNextWaveTimer());
        cWpanel.setVisible(false);
        cWpanel.setPosition(Gdx.graphics.getWidth() - 205, 5);
        stage.addActor(cWpanel);

    }
    private void initShop(Inventory towers){
        shop = new LevelShopPanel(towers);

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

        spellPanel.setPosition(1150, 300);
        stage.addActor(spellPanel);
        spellPanel.init();

    }
    private void initPauseDialog() {
//        pauseDialog = new Dialog("", GDefence.getInstance().assetLoader.getSkin(), "pause-menu") {
//            {
//                text("Pause");//.padTop(150).align(Align.center).row();
//                button("Continue", 0);//.padBottom(100).row();
//                row();
//                button("Exit to menu", 1);//.padBottom(100);
//                //getButtonTable();//defaults().width(200);
//                Array<Cell> cells = getButtonTable().getCells();
//                Cell cell = cells.get(0);
//                cell/*.width(50)*//*.padBottom(360)*/.row();
//                cell.height(40);
////
//                cell = cells.get(1);
////                cell/*.width(50)*/.padBottom(360);
//                cell.height(40).row();
//                pack();
//
//            }
//            @Override
//            protected void result (Object object){
//                if (object.equals(0)) {
//                    hide();
//                    setPause();//
//                } else if (object.equals(1)){
//                    GDefence.getInstance().switchScreen(GDefence.getInstance().getCampainMap());
//                }
//            }
//        };
        pauseDialog = new PauseDialog();
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
        GDefence.getInstance().assetLoader.disposeLevelMap();
        //stage.dispose();
        //level = null;

    }
}
