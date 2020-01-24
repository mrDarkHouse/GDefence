package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.*;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Wave;
import com.darkhouse.gdefence.Model.Level.*;
import com.darkhouse.gdefence.Model.Panels.AbstractPanel;
import com.darkhouse.gdefence.Model.Panels.ModPanels.BossStatusPanel;
import com.darkhouse.gdefence.Model.Panels.ModPanels.InvasionPanel;
import com.darkhouse.gdefence.Model.Panels.ModPanels.KillMadnessPanel;
import com.darkhouse.gdefence.Model.Panels.NextWaveInfoPanel;
import com.darkhouse.gdefence.Model.Panels.SpellPanel;
import com.darkhouse.gdefence.Model.Panels.ModPanels.TimeRushPanel;
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
    private Dialog guideDialog;



    private InputMultiplexer input;

    public boolean isPaused() {
        return isPaused;
    }

    private NextWaveInfoPanel nWPanel;
    private CurrentWaveInfoPanel cWpanel;
    private PathSigner pathSigner;
    private HealthBar healthBar;
    public void updateHealth(){
        healthBar.update();
    }
    private EnegryBar enegryBar;
//    private AbstractPanel
    private InvasionPanel invasionPanel;
    private TimeRushPanel timeRushPanel;
    private KillMadnessPanel killMadnessPanel;
    private BossStatusPanel bossStatusPanel;

//    private AlphaAction fadeOut;
//    private AlphaAction fadeIn;

    public BossStatusPanel getBossStatusPanel() {
        return bossStatusPanel;
    }

    private MapTileActor[][] tileActors;

    public MapTileActor[][] getTiles() {
        return tileActors;
    }

    private static Level level;
//    FPSLogger logger;

//    private Level level;//must be //TODO
//    private Map map;

    public static LevelMap levelMap;//debug
    private SpellPanel spellPanel;


    private LevelShopPanel shop;

    public static Level getLevel() {
        return level;
    }

    private class PauseDialog extends Dialog{

        public PauseDialog() {
            super(GDefence.getInstance().assetLoader.getWord("pause"), GDefence.getInstance().assetLoader.getSkin(), "pause-menu");

//            getTitleLabel().getStyle().font = FontLoader.generateFont(26, Color.WHITE);


            padTop(40);
            getButtonTable().defaults().width(190).height(40).space(10);
//            defaults().space(50);
            getTitleLabel().setAlignment(Align.center);
            getButtonTable().add(new Actor()).row();

//            Label title = new Label("Pause", getSkin(), "description");
//
//            add(title).row();
            /*add*/button(GDefence.getInstance().assetLoader.getWord("continue"), 0);//.padBottom(100).row();
            getButtonTable().row();
            /*add*/button(GDefence.getInstance().assetLoader.getWord("exitToMenu"), 1);//.padBottom(100);
        }


        @Override
        protected void result (Object object){
            if (object.equals(0)) {
                hide();
                offPause();
            } else if (object.equals(1)){
                Wave.mobs.clear();
                GDefence.getInstance().switchScreen(GDefence.getInstance().getCampainMap());
            }
        }


    }
    private class InvasionDialog extends Dialog{

        public InvasionDialog() {
            super(GDefence.getInstance().assetLoader.getWord("invasionInfo"), GDefence.getInstance().assetLoader.getSkin(), "pause-menu");
            getTitleLabel().setAlignment(Align.center);
            getTitleLabel().setStyle(FontLoader.generateStyle(0, 44, Color.WHITE, 2, Color.BLACK));
            padTop(40);
//            getButtonTable().defaults().align(Align.center);
            Label l = new Label(GDefence.getInstance().assetLoader.getWord("invasionGuide1") + System.getProperty("line.separator") +
                    GDefence.getInstance().assetLoader.getWord("invasionGuide2"), FontLoader.generateStyle(1, 34, Color.WHITE));
            l.setAlignment(Align.center);
            getButtonTable().add(l).row();
//            text(GDefence.getInstance().assetLoader.getWord("invasionGuide1") + System.getProperty("line.separator") +
//                    GDefence.getInstance().assetLoader.getWord("invasionGuide2"), FontLoader.generateSecondaryStyle(34, Color.WHITE));
//            row();
            button(GDefence.getInstance().assetLoader.getWord("ok"), 0, getSkin().get("rect", TextButton.TextButtonStyle.class));
//            getButtonTable().row();

        }
        @Override
        protected void result (Object object){
            if (object.equals(0)) {
                hide();
                offPause();
            }
        }
    }
    private class TimeRushDialog extends Dialog{

        public TimeRushDialog() {
            super(GDefence.getInstance().assetLoader.getWord("timeRushInfo"), GDefence.getInstance().assetLoader.getSkin(), "pause-menu");
            getTitleLabel().setAlignment(Align.center);
            getTitleLabel().setStyle(FontLoader.generateStyle(0, 44, Color.WHITE, 2, Color.BLACK));
            padTop(40);
            Label l = new Label(GDefence.getInstance().assetLoader.getWord("timeRushGuide") , FontLoader.generateStyle(1, 34, Color.WHITE));
            l.setAlignment(Align.center);
            getButtonTable().add(l).row();
            button(GDefence.getInstance().assetLoader.getWord("ok"), 0, getSkin().get("rect", TextButton.TextButtonStyle.class));
            pack();

        }
        @Override
        protected void result (Object object){
            if (object.equals(0)) {
                hide();
                offPause();
            }
        }
    }
    private class KillMadnessDialog extends Dialog{

        public KillMadnessDialog() {
            super(GDefence.getInstance().assetLoader.getWord("killMadnessInfo"), GDefence.getInstance().assetLoader.getSkin(), "pause-menu");
            getTitleLabel().setAlignment(Align.center);
            getTitleLabel().setStyle(FontLoader.generateStyle(0, 44, Color.WHITE, 2, Color.BLACK));
            padTop(40);
            Label l = new Label(GDefence.getInstance().assetLoader.getWord("killMadnessGuide1") + System.getProperty("line.separator") +
                    GDefence.getInstance().assetLoader.getWord("killMadnessGuide2"), FontLoader.generateStyle(1, 34, Color.WHITE));
            l.setAlignment(Align.center);
            getButtonTable().add(l).row();
            button(GDefence.getInstance().assetLoader.getWord("ok"), 0, getSkin().get("rect", TextButton.TextButtonStyle.class));
            pack();
        }
        @Override
        protected void result (Object object){
            if (object.equals(0)) {
                hide();
                offPause();
            }
        }
    }

    public LevelMap(int number, Inventory towers, Inventory spells) {
        super();
        this.number = number;
        this.towers = towers;

        batch = new SpriteBatch();
//        stage = new Stage();
        levelMap = this;
        level = new Level(number, this);
        initHpMpBar();
        initWavePanel();
        initMapTileActors();
        initTextures();//after loading textures
        initShop(towers);
        initSpellPanel(spells);
        initPauseDialog();
        initExtraEventPanels();
        initActions();




//        logger = new FPSLogger();
    }

    public void init(){
        LevelInput levelInput = new LevelInput(this);
        input = new InputMultiplexer();
        input.addProcessor(levelInput);
        input.addProcessor(stage);
    }


    public void mobDieEvent(){
        healthBar.update();
        if(/*invasionPanel != null*/level.getType() == Map.MapType.INVASION) invasionPanel.update();
//        if(level.getType() == Map.MapType.KILLMADNESS) ;
    }
    public void mobSpawnEvent(Mob m){
        if(level.getType() == Map.MapType.INVASION){
            invasionPanel.update();
            if(Wave.mobs.size >= level.getMobLimit()) level.looseLevel();
        }
//        if(level.getType() == Map.MapType.TIME){
//            if(m.getPrototype() == Mob.Prototype.SpaceLord){
//                bossStatusPanel.init(m);
//            }
//        }
    }
    public void energyChangeEvent(){
        enegryBar.update();
    }
    public void hpChangeEvent(){
        healthBar.update();
    }

    private void initTextures(){
//        Level.getMap().initBaseTextures();
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
        if(guideDialog != null) {
            guideDialog.show(stage);
            isPaused = true;
        }

//        System.out.println(getStage().getWidth() + " " + getStage().getHeight());

        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
//        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        System.out.println(Gdx.graphics.getWidth() + " " + Gdx.graphics.getHeight());
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
        super.render(delta);
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(GDefence.getInstance().assetLoader.get("LevelMapBg.png", Texture.class), 0, 0);
        if(!isPaused) {
//            if (delta < 5) {
            level.physic(delta);
            spellPanel.physic(delta);
//            stage.act(delta);
//            }
        }
//        logger.log();
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
        if(level.getType() == Map.MapType.TIME) {
            healthBar = new BossHealth(GDefence.WIDTH, 55, 0, GDefence.HEIGHT - 55, level.getCurrentWave().getBoss());
        }else {
            healthBar = new HealthBar(GDefence.WIDTH, 55, 0, GDefence.HEIGHT - 55);
        }
        healthBar.init();
        healthBar.update();
        enegryBar = new EnegryBar(55, GDefence.HEIGHT - 55, 0, 0);
        stage.addActor(healthBar);
        stage.addActor(enegryBar);

    }
    private void initWavePanel(){
        nWPanel = new NextWaveInfoPanel();
        stage.addActor(nWPanel);
        nWPanel.init();
        nWPanel.setPosition(GDefence.WIDTH - /*205*/nWPanel.getWidth() - 5, 5);


        if(level.getType() == Map.MapType.CLASSIC) {
            pathSigner = new PathSigner(nWPanel.getNextWaveTimer(), level.getMap().getPaths());//static
            stage.addActor(pathSigner);

            cWpanel = new CurrentWaveInfoPanel(nWPanel.getNextWaveTimer());
            cWpanel.setVisible(false);
            cWpanel.setPosition(GDefence.WIDTH - cWpanel.getWidth() - 5, 5);
            stage.addActor(cWpanel);
        }
    }
    private void initExtraEventPanels(){
        switch (level.getType()){
            case CLASSIC: return;
            case INVASION:
                invasionPanel = new InvasionPanel(level.getMobLimit(), Wave.mobs);
                invasionPanel.setVisible(false);
                invasionPanel.setPosition(GDefence.WIDTH - invasionPanel.getWidth() - 5, 5);
                stage.addActor(invasionPanel);
                guideDialog = new InvasionDialog();
                break;
            case KILLMADNESS:
                killMadnessPanel = new KillMadnessPanel(level.getTimeLimit(), level);
                killMadnessPanel.setVisible(false);
                killMadnessPanel.setPosition(GDefence.WIDTH - killMadnessPanel.getWidth() - 5, 5);
                stage.addActor(killMadnessPanel);
                guideDialog = new KillMadnessDialog();
                break;
            case TIME:
                Table t = new Table();
                timeRushPanel = new TimeRushPanel(level.getTimeLimit(), level);
                timeRushPanel.setVisible(false);
//                timeRushPanel.setPosition(GDefence.WIDTH - timeRushPanel.getWidth() - 5, 5);
//                stage.addActor(timeRushPanel);
                guideDialog = new TimeRushDialog();
                bossStatusPanel = new BossStatusPanel(timeRushPanel.getWidth());
                bossStatusPanel.setVisible(false);
//                bossStatusPanel.setPosition(GDefence.WIDTH - timeRushPanel.getWidth() - 5, timeRushPanel.getHeight() + 5);
//                stage.addActor(bossStatusPanel);
                t.add(bossStatusPanel).row();
                t.add(timeRushPanel);
                t.setPosition(GDefence.WIDTH - timeRushPanel.getWidth() - 5, timeRushPanel.getHeight()/2 + 10);
                t.pack();
                stage.addActor(t);
                bossStatusPanel.init(level.getCurrentWave().getBoss());//must be after add (tooltip adding)
                break;
        }
    }
    private void initShop(Inventory towers){
        shop = new LevelShopPanel(towers);

        shop.addTarget(Level.getMap().getTiles());
        shop.setPosition(90, 10);
        stage.addActor(shop);
        shop.init();

    }
    private void initActions(){
//        fadeIn = new AlphaAction();
//        fadeIn.setDuration(2f);
//        fadeIn.setAlpha(1f);
//        fadeOut = new AlphaAction();
//        fadeOut.setDuration(2f);
//        fadeOut.setAlpha(0.2f);
    }
    private void initSpellPanel(Inventory spells){
        Array<SpellObject> a = new Array<SpellObject>();
//        SpellObject[] ar = new SpellObject[4];
//        for (int i = 0; i < spells.getSlots().size; i++){
//            if(!spells.getSlots().get(i).isEmpty() && TowerObject.isMatches(spells.getSlots().get(i).getLast().getClass(), SpellObject.class)){
//                ar[i] = ((SpellObject) spells.getSlots().get(i).getLast());
//            }
//        }
        for (Slot s:spells.getSlots()){
            if(!s.isEmpty() && TowerObject.isMatches(s.getLast().getClass(), SpellObject.class)){
                a.add(((SpellObject) s.getLast()));
            }else {
                a.add(null);
            }
        }


        spellPanel = new SpellPanel(a);

        spellPanel.setPosition(1080, 330);//1180, 330
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


    public void updateEnd(){//when new wave ends
        nWPanel.hasChanged();
        switch (level.getType()){
            case CLASSIC:cWpanel.setVisible(false); break;
            case INVASION:invasionPanel.setVisible(false); break;
            case KILLMADNESS:killMadnessPanel.setVisible(false); break;
            case TIME:timeRushPanel.setVisible(false);
        }
        nWPanel.setVisible(true);
//        fadeOut.reset();
        AlphaAction a = new AlphaAction();
        a.setDuration(2f);
        a.setAlpha(0.2f);
//        a.setActor(spellPanel);
//        spellPanel.clearActions();
//        spellPanel.clearActions();
//        spellPanel.addAction(a);
//        System.out.println(fadeOut.getActor());
//        spellPanel.setVisible(false);
        if(pathSigner != null) pathSigner.update(getLevel().currentWave);//== null in KillMadness mod
        /*pathSigner.initTextures();*///slow
    }
    public void updateStart(){//when new wave starts
//        nWPanel.hasChanged();
        nWPanel.setVisible(false);
//        fadeIn.reset();
        AlphaAction a = new AlphaAction();
        a.setDuration(2f);
        a.setAlpha(1f);
//        a.setActor(spellPanel);
//        spellPanel.clearActions();
//        System.out.println(fadeIn.getActor());
//        spellPanel.clearActions();
//        spellPanel.addAction(a);
//        System.out.println(fadeIn.getActor());
//        spellPanel.setVisible(true);
        switch (level.getType()){
            case CLASSIC:cWpanel.setVisible(true); break;
            case INVASION:invasionPanel.setVisible(true); break;
            case KILLMADNESS:killMadnessPanel.setVisible(true);break;
            case TIME:{
                timeRushPanel.setVisible(true);
                bossStatusPanel.setVisible(true);
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        GDefence.getInstance().assetLoader.disposeLevelMap();
        //stage.dispose();
        //level = null;

    }
}
