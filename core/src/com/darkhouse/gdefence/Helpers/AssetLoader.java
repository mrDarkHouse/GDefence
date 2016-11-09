package com.darkhouse.gdefence.Helpers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Objects.GameObject;

import static com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum.Tower.*;

public class AssetLoader extends AssetManager{

    public static Texture mainMenuButtonsAtlas;
    public static Texture mainMenuBg, campainBg;
    public static TextureRegion[][] mainMenuButtons;
    public static Texture backButton;
    public static Texture[] campainMap = new Texture[3];
    public static Texture campainLevelsAtlas;
    public static TextureRegion[][] campainLevels;
    //public static Texture campainLevelSolo;
    public static Texture cell;
    public static Texture nameButtonFone;
    public static Texture dialogSkin;
    public static Skin uiSkin;
    public static Texture transparent;
    public static Texture barFone;
    public static Texture barTop;
    public static Texture levelLock/* = new TextureRegion[10][1]*/;
    public static Texture levelMapBg;
    public static Texture[] gemTexture = new Texture[6];
    public static Texture coin;
    public static Texture castle;
    public static Texture ground;
    public static Texture grass;
    public static Texture tree;
    public static Texture rock;
    public static Texture water;

    public static Texture slime;
    public static Texture dog;
    public static Texture worm;
    public static Texture jungleBat;
    public static Texture boar;

    public static Texture basicTower;
    public static Texture rockTower;
    public static Texture arrowTower;
    public static Texture rangeTower;

    public static Texture infoPanelFone;
    public static Texture levelEndFoneWin;
    public static Texture levelEndFoneLoose;

    public static Skin cellSkin;

    public static Texture sellButton;
    public static Texture buildGridLinePixel;
    public static Texture attackRangeTexture;

    public static Texture prevButton;
    public static Texture nextButton;

    public static Texture maxHpGrade;
    public static Texture maxEnergyGrade;


    public void loadAll() {
        loadMainMenu();
        loadLevelMap();
        loadShop();
        loadSmith();
    }

    private void loadMainMenu(){
        load("MainMenuBg.png", Texture.class);
        mainMenuBg = new Texture("MainMenuBg.png");
        campainBg = new Texture("CampainBg.png");
        backButton = new Texture("backButton.png");
        mainMenuButtonsAtlas = new Texture(Gdx.files.internal("MainMenuAtlas.png"));
        mainMenuButtons = TextureRegion.split(mainMenuButtonsAtlas, mainMenuButtonsAtlas.getWidth() / 2, mainMenuButtonsAtlas.getHeight() / 10);
        campainMap[0] = new Texture("arsenal.png");
        campainMap[1] = new Texture("store.png");
        campainMap[2] = new Texture("smith.png");
        campainLevelsAtlas = new Texture(Gdx.files.internal("levelsButtonsAtlas.png"));
        campainLevels = TextureRegion.split(campainLevelsAtlas, campainLevelsAtlas.getWidth() / 2, campainLevelsAtlas.getHeight() / 10);
        cell = new Texture("cell.png");
        nameButtonFone = new Texture("nameButtonFone.png");
        dialogSkin = new Texture("dialogSkin.png");
        uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
        transparent = new Texture("MainMenuTranparent.png");
        barFone = new Texture("expBar1.png");
        barTop = new Texture("expBar2.png");
        levelLock = new Texture("levelLock.png");



        levelMapBg = new Texture("LevelMapBg.png");
        gemTexture[0] = new Texture("Gems/redGem.png");
        gemTexture[1] = new Texture("Gems/yellowGem.png");
        gemTexture[2] = new Texture("Gems/blueGem.png");
        gemTexture[3] = new Texture("Gems/blackGem.png");
        gemTexture[4] = new Texture("Gems/greenGem.png");
        gemTexture[5] = new Texture("Gems/whiteGem.png");
        coin = new Texture("coin.png");
        castle = new Texture("castle.png");
        Texture groundTileSet = new Texture("tileset_ground.png");
        TextureRegion[][] tileSet = TextureRegion.split(groundTileSet, groundTileSet.getWidth(), groundTileSet.getHeight()/10);
        //grass = tileSet[0][0];
        //ground = tileSet[1][0];
        grass = new Texture("grass.png");
        ground = new Texture("ground.png");

        slime = new Texture("Mobs/mob.png");
        dog = new Texture("Mobs/mob2.png");
        worm = new Texture("Mobs/mob3.png");
        jungleBat = new Texture("Mobs/mob4.png");
        boar = new Texture("Mobs/mob5.png");

        basicTower = new Texture("Tower/basicTower.png");
        rockTower = new Texture("Tower/rockTower.png");
        arrowTower = new Texture("Tower/arrowTower.png");
        rangeTower = new Texture("Tower/rangeTower.png");

        infoPanelFone = new Texture("infoPanelFone.png");

        levelEndFoneLoose = new Texture("levelLooseBg.png");
        levelEndFoneWin = new Texture("levelWinBg.png");

        cellSkin = new Skin(Gdx.files.internal("skins/uiskin.json"));

        sellButton = new Texture("sellButton.png");
        buildGridLinePixel = new Texture("buildGridLinePixel.png");
        attackRangeTexture = new Texture("towerRangeTexture.png");

        prevButton = new Texture("prevButton.png");
        nextButton = new Texture("nextButton.png");

        maxHpGrade = new Texture("maxHpGrade.png");
        maxEnergyGrade = new Texture("maxEnergyGrade.png");


    }
    private void loadTextures(){




    }
    private void loadLevelMap(){

        load("grass.png", Texture.class);
        load("ground.png", Texture.class);
        load("Mobs/mob.png", Texture.class);
        load("Mobs/mob2.png", Texture.class);
        load("Mobs/mob3.png", Texture.class);
        load("Mobs/mob4.png", Texture.class);
        load("Mobs/mob5.png", Texture.class);
        load("Tower/basicTower.png", Texture.class);
        load("Tower/rockTower.png", Texture.class);
        load("Tower/arrowTower.png", Texture.class);
        load("Tower/rangeTower.png", Texture.class);
        load("levelLooseBg.png", Texture.class);
        load("levelWinBg.png", Texture.class);
        load("buildGridLinePixel.png", Texture.class);
        load("towerRangeTexture.png", Texture.class);





    }
    private void loadShop(){
        load("sellButton.png", Texture.class);



    }
    private void loadSmith(){
        load("maxHpGrade.png", Texture.class);
        load("maxEnergyGrade.png", Texture.class);
        load("prevButton.png", Texture.class);
        load("nextButton.png", Texture.class);




    }



    public static ImageButton.ImageButtonStyle generateImageButtonSkin(Texture t){
        ImageButton.ImageButtonStyle s = new ImageButton.ImageButtonStyle();
        s.up = new TextureRegionDrawable(new TextureRegion(t));
        s.over = new TextureRegionDrawable(new TextureRegion(t));
        s.down = new TextureRegionDrawable(new TextureRegion(t));

        return s;

    }




    public static ImageButton.ImageButtonStyle getPrevButtonSkin(){
        ImageButton.ImageButtonStyle s = new ImageButton.ImageButtonStyle();
        s.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.prevButton));
        s.over = new TextureRegionDrawable(new TextureRegion(AssetLoader.prevButton));
        s.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.prevButton));

        return s;
    }
    public static ImageButton.ImageButtonStyle getNextButtonSkin(){
        ImageButton.ImageButtonStyle s = new ImageButton.ImageButtonStyle();
        s.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.nextButton));
        s.over = new TextureRegionDrawable(new TextureRegion(AssetLoader.nextButton));
        s.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.nextButton));

        return s;
    }



    public static ImageButton.ImageButtonStyle getBackButtonSkin(){
        ImageButton.ImageButtonStyle backButtonStyle = new ImageButton.ImageButtonStyle();
        backButtonStyle.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.backButton));
        backButtonStyle.over = new TextureRegionDrawable(new TextureRegion(AssetLoader.backButton));
        backButtonStyle.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.backButton));

        return backButtonStyle;
    }

    public ProgressBar.ProgressBarStyle getExpBarSkin(){
        TextureRegionDrawable barFone = new TextureRegionDrawable(new TextureRegion(AssetLoader.barFone));
        TextureRegionDrawable barTop = new TextureRegionDrawable(new TextureRegion(AssetLoader.barTop));
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle(barFone, barTop);
        progressBarStyle.knobBefore = progressBarStyle.knob;

        return progressBarStyle;


    }
    public static ProgressBar.ProgressBarStyle getEnergyBarSkin(){
        TextureRegionDrawable barFone = new TextureRegionDrawable(new TextureRegion(new Texture("energyBarBg.png")));
        TextureRegionDrawable barTop = new TextureRegionDrawable(new TextureRegion(new Texture("energyBarKnob.png")));
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle(barFone, barTop);
        progressBarStyle.knobBefore = progressBarStyle.knob;

        return progressBarStyle;


    }
    public static ProgressBar.ProgressBarStyle getMobHpBarStyle(){
        TextureRegionDrawable barFone = new TextureRegionDrawable(new TextureRegion(new Texture("mobHpBarBg.png")));
        TextureRegionDrawable barTop = new TextureRegionDrawable(new TextureRegion(new Texture("mobHpBarKnob.png")));
        ProgressBar.ProgressBarStyle style = new ProgressBar.ProgressBarStyle(barFone, barTop);
        style.knobBefore = style.knob;
        return style;
    }

    public static TextButton.TextButtonStyle getCampainLevelStyle(){
        TextButton.TextButtonStyle s = new TextButton.TextButtonStyle();

        s.up = new TextureRegionDrawable(new TextureRegion(cell));
        s.font = FontLoader.generateFont(52);
        s.fontColor = Color.BLACK;

        return s;
    }


    public static ImageButton.ImageButtonStyle getCampainLevelSkin(int number){
        ImageButton.ImageButtonStyle s = new ImageButton.ImageButtonStyle();

        s.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainLevels[number - 1][0]));
        s.over = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainLevels[number - 1][1]));
        s.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainLevels[number - 1][0]));

        return s;
    }
    public static ImageTextButton.ImageTextButtonStyle getUserLevelSkin(){
        ImageTextButton.ImageTextButtonStyle userLevelButtonStyle = new ImageTextButton.ImageTextButtonStyle();
        userLevelButtonStyle.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.cell));
        userLevelButtonStyle.over = new TextureRegionDrawable(new TextureRegion(AssetLoader.cell));
        userLevelButtonStyle.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.cell));
        userLevelButtonStyle.font = FontLoader.impact28;
        //userLevelButtonStyle

        userLevelButtonStyle.fontColor = new Color(0, 0, 0, 255);


        return userLevelButtonStyle;
    }
    //public static TextureRegion getLevelLockTexture(int number){
        //Texture t;
        //return levelLock;
        //Texture atlas = new Texture(Gdx.files.internal("LevelsLockAtlas.png"));
        //levelLock =  TextureRegion.split(atlas, atlas.getWidth(),
        //        atlas.getHeight() / 10);
       // return levelLock[number - 1][0];
    //}




    public static ImageButton.ImageButtonStyle getBottomPanelSkin(int index){
        switch (index){
            case 1:
                ImageButton.ImageButtonStyle arsenalButtonStyle = new ImageButton.ImageButtonStyle();
                arsenalButtonStyle.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainMap[0]));
                arsenalButtonStyle.over = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainMap[0]));
                arsenalButtonStyle.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainMap[0]));
                return arsenalButtonStyle;
            case 2:
                ImageButton.ImageButtonStyle storeButtonStyle = new ImageButton.ImageButtonStyle();
                storeButtonStyle.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainMap[1]));
                storeButtonStyle.over = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainMap[1]));
                storeButtonStyle.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainMap[1]));
                return storeButtonStyle;
            case 3:
                ImageButton.ImageButtonStyle smithButtonStyle = new ImageButton.ImageButtonStyle();
                smithButtonStyle.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainMap[2]));
                smithButtonStyle.over = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainMap[2]));
                smithButtonStyle.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainMap[2]));
                return smithButtonStyle;
            case 4:
                return null;

            case 5:
                return null;
        }
        return null;
    }
    public static Skin getSkin(){
        return new Skin(Gdx.files.internal("uiskin.json"));
    }

    public static Label.LabelStyle getTimerSkin(){
        Label.LabelStyle l = new Label.LabelStyle();
        l.font = FontLoader.impact24;
        l.fontColor = Color.BLACK;


        return l;
    }
    public static Label.LabelStyle getInfoPanelSkin(){
        Label.LabelStyle l = new Label.LabelStyle();
        l.font = FontLoader.impact16;
        l.fontColor = Color.BLACK;


        return l;
    }
    public static Label.LabelStyle getCurrentInfoPanelSkin(){
        Label.LabelStyle l = new Label.LabelStyle();
        l.font = FontLoader.impact24;
        l.fontColor = Color.BLACK;


        return l;


    }
    public static Label.LabelStyle getLevelEndResultSkin(){
        Label.LabelStyle l = new Label.LabelStyle();
        l.font = FontLoader.impact36;
        l.fontColor = Color.BLACK;
        return l;
    }

    public static ImageButton.ImageButtonStyle getTowerCellSkin(ItemEnum.Tower tower){
        ImageButton.ImageButtonStyle towerCellStyle = new ImageButton.ImageButtonStyle();
        switch (tower){
            case Basic:
                towerCellStyle.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.basicTower));
                towerCellStyle.over = new TextureRegionDrawable(new TextureRegion(AssetLoader.basicTower));
                towerCellStyle.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.basicTower));
                break;
            case Rock:
                towerCellStyle.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.rockTower));
                towerCellStyle.over = new TextureRegionDrawable(new TextureRegion(AssetLoader.rockTower));
                towerCellStyle.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.rockTower));
                break;
            case Arrow:
                towerCellStyle.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.arrowTower));
                towerCellStyle.over = new TextureRegionDrawable(new TextureRegion(AssetLoader.arrowTower));
                towerCellStyle.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.arrowTower));
                break;
            case Range:
                towerCellStyle.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.rangeTower));
                towerCellStyle.over = new TextureRegionDrawable(new TextureRegion(AssetLoader.rangeTower));
                towerCellStyle.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.rangeTower));
                break;
            default:
                throw new RuntimeException("No tower found with id: " + tower);
        }
        return towerCellStyle;
    }
    public static Texture getTowerTexture(ItemEnum.Tower tower){
        switch (tower){
            case Basic:
                return basicTower;
            case Rock:
                return rockTower;
            case Arrow:
                return arrowTower;
            case Range:
                return rangeTower;
            default:
                throw new RuntimeException("No tower found with id: " + tower);
        }
    }
    public static Texture getProjectileTexture(ItemEnum.Tower tower){
        switch (tower){
            case Basic:
                return new Texture("Projectiles/basic.png");
            case Rock:
                return new Texture("Projectiles/rock.png");
            case Arrow:
                return new Texture("Projectiles/arrow.png");
            case Range:
                return new Texture("Projectiles/range.png");
            default:
                throw new RuntimeException("No tower found with id: " + tower);
        }
    }


    public static ImageButton.ImageButtonStyle getStoreSellButtonStyle(){
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = new TextureRegionDrawable((new TextureRegion(sellButton)));
        style.over = new TextureRegionDrawable((new TextureRegion(sellButton)));
        style.down = new TextureRegionDrawable((new TextureRegion(sellButton)));

        return style;


    }


    public void dispose() {
        mainMenuButtonsAtlas.dispose();
    }



}
