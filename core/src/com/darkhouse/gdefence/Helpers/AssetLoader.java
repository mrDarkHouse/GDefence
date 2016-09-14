package com.darkhouse.gdefence.Helpers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AssetLoader {

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
    public static Texture levelLock;
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

    public static Texture infoPanelFone;
    public static Texture levelEndFoneWin;
    public static Texture levelEndFoneLoose;

    //public static Animation birdAnimation;
    //public static TextureRegion bird, birdDown, birdUp;


    public static void load() {
        loadMainMenu();
    }

    private static void loadMainMenu(){
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

        infoPanelFone = new Texture("infoPanelFone.png");

    }

    public static ImageButton.ImageButtonStyle getBackButtonSkin(){
        ImageButton.ImageButtonStyle backButtonStyle = new ImageButton.ImageButtonStyle();
        backButtonStyle.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.backButton));
        backButtonStyle.over = new TextureRegionDrawable(new TextureRegion(AssetLoader.backButton));
        backButtonStyle.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.backButton));

        return backButtonStyle;
    }

    public static ProgressBar.ProgressBarStyle getExpBarSkin(){
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
    public static Texture getLevelLockTexture(){
        Texture t;



        return levelLock;
    }
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



    public static void dispose() {
        mainMenuButtonsAtlas.dispose();
    }



}
