package com.darkhouse.gdefence.Helpers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.ObjectMap;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Objects.GameObject;
import com.darkhouse.gdefence.User;

import java.util.Locale;

import static com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum.Tower.*;

public class AssetLoader extends AssetManager{

//    public static Texture mainMenuButtonsAtlas;
//    public static Texture mainMenuBg, campainBg;
//    public static TextureRegion[][] mainMenuButtons;
//    public static Texture backButton;
//    public static Texture[] campainMap = new Texture[3];
//    public static Texture campainLevelsAtlas;
//    public static TextureRegion[][] campainLevels;
    //public static Texture campainLevelSolo;
//    public static Texture cell;
//    public static Texture nameButtonFone;
//    public static Texture dialogSkin;
//    public static Skin uiSkin;
//    public static Texture transparent;
//    public static Texture barFone;
//    public static Texture barTop;
//    public static Texture levelLock/* = new TextureRegion[10][1]*/;
//    public static Texture levelMapBg;
//    public static Texture[] gemTexture = new Texture[6];
//    public static Texture coin;
//    public static Texture castle;
//    public static Texture ground;
//    public static Texture grass;
//    public static Texture tree;
//    public static Texture rock;
//    public static Texture water;

//    public static Texture slime;
//    public static Texture dog;
//    public static Texture worm;
//    public static Texture jungleBat;
//    public static Texture boar;
//
//    public static Texture basicTower;
//    public static Texture rockTower;
//    public static Texture arrowTower;
//    public static Texture rangeTower;

    //public static Texture infoPanelFone;
//    public static Texture levelEndFoneWin;
//    public static Texture levelEndFoneLoose;

//    public static Skin cellSkin;

//    public static Texture sellButton;
//    public static Texture buildGridLinePixel;
//    public static Texture attackRangeTexture;

//    public static Texture prevButton;
//    public static Texture nextButton;

//    public static Texture maxHpGrade;
//    public static Texture maxEnergyGrade;

    private I18NBundle b;


    public void loadAll() {
//        param = new I18NBundleLoader.I18NBundleParameter(new Locale("ru"), "UTF-8");

        loadTextures();
        loadLevelMap();
        loadShop();
        loadSmith();
    }

    public void setFilters(){
//        b = get("Language/text", I18NBundle.class);

//        get("skins/uiskin.json", Skin.class).add("default-font", FontLoader.generateFont(20, Color.BLACK), BitmapFont.class);
        get("skins/uiskin.json", Skin.class).getFont("default-font").getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("skins/uiskin.json", Skin.class).getFont("secondaryFont").getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("skins/uiskin.json", Skin.class).getFont("default-font").getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        get("Gems/redGem.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Gems/yellowGem.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Gems/blueGem.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Gems/blackGem.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Gems/greenGem.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Gems/whiteGem.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);





    }
    public void setLevelMapFilters(){
        get("Path/Turn/turnLU.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnLD.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnRU.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnRD.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnWaterLU.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnWaterLD.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnWaterRU.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnWaterRD.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        get("Path/Bridge/bridgeLDL.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Bridge/bridgeLDU.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Bridge/bridgeLUD.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Bridge/bridgeLUL.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Bridge/bridgeRDR.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Bridge/bridgeRDU.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Bridge/bridgeRUD.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Bridge/bridgeRUR.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Bridge/bridgeUUR.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        get("Path/Bridge/bridgeCrossR.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Bridge/bridgeCrossL.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Bridge/bridgeCrossU.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Bridge/bridgeCrossD.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        get("Path/Bridge/waterBridgeDDR1.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Bridge/waterBridgeDDR2.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Bridge/waterBridgeUUR1.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Bridge/waterBridgeUUR2.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        get("Path/Turn/bridgeLnoArrows.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/bridgeRnoArrows.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/bridgeUnoArrows.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/bridgeDnoArrows.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/waterBridgeLnoArrows.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/waterBridgeRnoArrows.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/waterBridgeUnoArrows.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/waterBridgeDnoArrows.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        get("Path/Turn/turnWaterGroundLDL.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnWaterGroundLDU.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnWaterGroundLDL.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnWaterGroundLUD.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnWaterGroundLUL.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnWaterGroundRDR.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnWaterGroundRDU.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnWaterGroundRUD.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnWaterGroundRUR.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        get("Path/Turn/turnGroundWaterLUD.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnGroundWaterLDL.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnGroundWaterLDU.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnGroundWaterLUL.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnGroundWaterRUD.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnGroundWaterRDU.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnGroundWaterRUR.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnGroundWaterRDR.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnGroundWaterRRD.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnGroundWaterRRU.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnGroundWaterUUR.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        get("Path/Turn/turnGroundWaterUUL.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public void loadOld(){
        loadMainMenu();
    }
    private void loadMainMenu(){
//        mainMenuBg = new Texture("MainMenuBg.png");
//        campainBg = new Texture("CampainBg.png");
        //backButton = new Texture("backButton.png");
//        mainMenuButtonsAtlas = new Texture(Gdx.files.internal("MainMenuAtlas.png"));
//        mainMenuButtons = TextureRegion.split(mainMenuButtonsAtlas, mainMenuButtonsAtlas.getWidth() / 2, mainMenuButtonsAtlas.getHeight() / 10);
//        campainMap[0] = new Texture("arsenal.png");
//        campainMap[1] = new Texture("storeNew.png");
//        campainMap[2] = new Texture("smith.png");
//        campainLevelsAtlas = new Texture(Gdx.files.internal("levelsButtonsAtlas.png"));
//        campainLevels = TextureRegion.split(campainLevelsAtlas, campainLevelsAtlas.getWidth() / 2, campainLevelsAtlas.getHeight() / 10);
//        cell = new Texture("cell.png");
//        nameButtonFone = new Texture("nameButtonFone.png");
//        dialogSkin = new Texture("dialogSkin.png");
//        uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
//        transparent = new Texture("MainMenuTranparent.png");
//        barFone = new Texture("expBar1.png");
//        barTop = new Texture("expBar2.png");
//        levelLock = new Texture("levelLock.png");



//        levelMapBg = new Texture("LevelMapBg.png");
//        gemTexture[0] = new Texture("Gems/redGem.png");
//        gemTexture[1] = new Texture("Gems/yellowGem.png");
//        gemTexture[2] = new Texture("Gems/blueGem.png");
//        gemTexture[3] = new Texture("Gems/blackGem.png");
//        gemTexture[4] = new Texture("Gems/greenGem.png");
//        gemTexture[5] = new Texture("Gems/whiteGem.png");
//        coin = new Texture("coin.png");
//        castle = new Texture("castle.png");
//        Texture groundTileSet = new Texture("tileset_ground.png");
//        TextureRegion[][] tileSet = TextureRegion.split(groundTileSet, groundTileSet.getWidth(), groundTileSet.getHeight()/10);
        //grass = tileSet[0][0];
        //ground = tileSet[1][0];
//        grass = new Texture("grass.png");
//        ground = new Texture("ground.png");
//
//        slime = new Texture("Mobs/mob.png");
//        dog = new Texture("Mobs/mob2.png");
//        worm = new Texture("Mobs/mob3.png");
//        jungleBat = new Texture("Mobs/mob4.png");
//        boar = new Texture("Mobs/mob5.png");

//        basicTower = new Texture("Tower/basicTower.png");
//        rockTower = new Texture("Tower/rockTower.png");
//        arrowTower = new Texture("Tower/arrowTower.png");
//        rangeTower = new Texture("Tower/rangeTower.png");

//        infoPanelFone = new Texture("infoPanelFone.png");

//        levelEndFoneLoose = new Texture("levelLooseBg.png");
//        levelEndFoneWin = new Texture("levelWinBg.png");

        //cellSkin = new Skin(Gdx.files.internal("skins/uiskin.json"));

        //sellButton = new Texture("sellButton.png");
        //buildGridLinePixel = new Texture("buildGridLinePixel.png");
        //attackRangeTexture = new Texture("towerRangeTexture.png");

        //prevButton = new Texture("prevButton.png");
        //nextButton = new Texture("nextButton.png");

        //maxHpGrade = new Texture("maxHpGrade.png");
        //maxEnergyGrade = new Texture("maxEnergyGrade.png");


    }

    public void loadTextures(){
        load("MainMenuAtlas.png", Texture.class);

        load("MainMenuBg.png", Texture.class);
        load("CampainBg.png", Texture.class);
        load("LevelMapBg.png", Texture.class);
        load("Backgrounds/forest.png", Texture.class);
        load("Backgrounds/desert.png", Texture.class);
        load("Backgrounds/lake.png", Texture.class);
        load("Backgrounds/space.png", Texture.class);
        load("Gems/redGem.png", Texture.class);
        load("Gems/yellowGem.png", Texture.class);
        load("Gems/blueGem.png", Texture.class);
        load("Gems/blackGem.png", Texture.class);
        load("Gems/greenGem.png", Texture.class);
        load("Gems/whiteGem.png", Texture.class);
        load("Gems/transparentGem.png", Texture.class);

        //
        load("icons/icons.atlas", TextureAtlas.class);


        load("backButton.png", Texture.class);
        load("cell.png", Texture.class);
        load("nameButtonFone.png", Texture.class);
        load("dialogSkin.png", Texture.class);
        load("MainMenuTranparent.png", Texture.class);
        load("expBar1.png", Texture.class);
        load("expBar2.png", Texture.class);
//        load("energyBarBg.png", Texture.class);
//        load("energyBarKnob.png", Texture.class);
//        load("mobHpBarBg.png", Texture.class);
//        load("mobHpBarKnob.png", Texture.class);
        load("levelLock.png", Texture.class);
        load("arsenal.png", Texture.class);
        load("store.png", Texture.class);
        load("smith.png", Texture.class);
        load("Projectiles/basic.png", Texture.class);
        load("Projectiles/rock.png", Texture.class);
        load("Projectiles/arrow.png", Texture.class);
        load("Projectiles/range.png", Texture.class);
        load("Projectiles/short.png", Texture.class);
        load("Projectiles/mountain.png", Texture.class);
        load("Projectiles/steelArrow.png", Texture.class);
        load("Projectiles/ballista.png", Texture.class);
        load("Projectiles/catapult.png", Texture.class);
        load("Projectiles/spear.png", Texture.class);
        load("Projectiles/crossbow.png", Texture.class);
        load("Projectiles/gun.png", Texture.class);
        load("Projectiles/rifle.png", Texture.class);
        load("Projectiles/machinegun.png", Texture.class);
        load("Projectiles/sniper.png", Texture.class);
        load("Projectiles/shotgun.png", Texture.class);
        load("Projectiles/doublebarrel.png", Texture.class);
        load("Projectiles/cannon.png", Texture.class);
        load("Projectiles/rocket.png", Texture.class);
        load("Projectiles/missle.png", Texture.class);
        load("Projectiles/glaive.png", Texture.class);
        load("Projectiles/multishot.png", Texture.class);
        load("Projectiles/steammachine.png", Texture.class);
        load("coin.png", Texture.class);
        load("manaCostLabelFon.png", Texture.class);
        load("spellCdTransparent.png", Texture.class);
//        load("spellPanelFon.png", Texture.class);
        load("AbilityIcons/Spells/globalSlow.png", Texture.class);
        load("AbilityIcons/Spells/iceBlast.png", Texture.class);
        load("AbilityIcons/Spells/emergencyRepair.png", Texture.class);
        load("AbilityIcons/Spells/suddenDeath.png", Texture.class);
        load("AbilityIcons/Spells/echoSmash.png", Texture.class);
        load("AbilityIcons/Spells/nothing.png", Texture.class);





        load("lockedTower.png", Texture.class);
        load("openedTower.png", Texture.class);
        load("canOpenTower.png", Texture.class);

        load("Buttons/towersButton.png", Texture.class);
        load("Buttons/spellsButton.png", Texture.class);
        load("Buttons/detailsButton.png", Texture.class);


        ObjectMap<String, Object> fontMap = new ObjectMap<String, Object>();
        fontMap.put("default-font", FontLoader.generateFont(26, Color.BLACK));
        fontMap.put("default-font48", FontLoader.generateFont(48, Color.BLACK));
        fontMap.put("secondaryFont", FontLoader.generateSecondaryFont(16, Color.WHITE));
        fontMap.put("spellFont", FontLoader.generateSecondaryFont(14, Color.WHITE));


        SkinLoader.SkinParameter parameter = new SkinLoader.SkinParameter(fontMap);

        load("skins/uiskin.json", Skin.class, parameter);

        load("Tower/basic.png", Texture.class);
        load("Tower/rock.png", Texture.class);
        load("Tower/arrow.png", Texture.class);
        load("Tower/range.png", Texture.class);
        load("Tower/short.png", Texture.class);
        load("Tower/mountain.png", Texture.class);
        load("Tower/steelarrow.png", Texture.class);
        load("Tower/ballista.png", Texture.class);
        load("Tower/catapult.png", Texture.class);
        load("Tower/spear.png", Texture.class);
        load("Tower/crossbow.png", Texture.class);
        load("Tower/gun.png", Texture.class);
        load("Tower/rifle.png", Texture.class);
        load("Tower/machinegun.png", Texture.class);
        load("Tower/sniper.png", Texture.class);
        load("Tower/shotgun.png", Texture.class);
        load("Tower/doublebarrel.png", Texture.class);
        load("Tower/cannon.png", Texture.class);
        load("Tower/rocket.png", Texture.class);
        load("Tower/missle.png", Texture.class);
        load("Tower/glaive.png", Texture.class);
        load("Tower/multishot.png", Texture.class);
        load("Tower/steammachine.png", Texture.class);

        load("Researches/powder.png", Texture.class);
        load("Researches/steam.png", Texture.class);

        load("AbilityIcons/Abilities/bash.png", Texture.class);
        load("AbilityIcons/Abilities/bounce.png", Texture.class);
        load("AbilityIcons/Abilities/buckShot.png", Texture.class);
        load("AbilityIcons/Abilities/crit.png", Texture.class);
        load("AbilityIcons/Abilities/desolate.png", Texture.class);
//        load("AbilityIcons/Abilities/doubleAttack.png", Texture.class);
        load("AbilityIcons/Abilities/spreadAttack.png", Texture.class);
        load("AbilityIcons/Abilities/fireArrow.png", Texture.class);
        load("AbilityIcons/Abilities/hunterSpeed.png", Texture.class);
        load("AbilityIcons/Abilities/multiShot.png", Texture.class);
        load("AbilityIcons/Abilities/poisonArrow.png", Texture.class);
        load("AbilityIcons/Abilities/shotDelay.png", Texture.class);
        load("AbilityIcons/Abilities/splash.png", Texture.class);
        load("AbilityIcons/Abilities/steelArrow.png", Texture.class);
        load("AbilityIcons/Abilities/steamAura.png", Texture.class);






    }
    public void loadLevelMap(){
        //load("MainMenuBg.png", Texture.class);

//        load("ground.png", Texture.class);
        load("Path/grass.png", Texture.class);
        load("Path/sand.png", Texture.class);
        load("Path/sand2.png", Texture.class);
        load("Path/neotile.png", Texture.class);
        load("Path/castle.png", Texture.class);
        load("Path/waterHorizontal.png", Texture.class);
        load("Path/waterVertical.png", Texture.class);
        load("Path/roadHorizontal.png", Texture.class);
        load("Path/roadVertical.png", Texture.class);
        load("Path/Turn/turnLU.png", Texture.class);
        load("Path/Turn/turnLD.png", Texture.class);
        load("Path/Turn/turnRU.png", Texture.class);
        load("Path/Turn/turnRD.png", Texture.class);
        load("Path/Turn/turnWaterLU.png", Texture.class);
        load("Path/Turn/turnWaterLD.png", Texture.class);
        load("Path/Turn/turnWaterRU.png", Texture.class);
        load("Path/Turn/turnWaterRD.png", Texture.class);

        load("Path/Bridge/bridgeLDL.png", Texture.class);
        load("Path/Bridge/bridgeLDU.png", Texture.class);
        load("Path/Bridge/bridgeLUD.png", Texture.class);
        load("Path/Bridge/bridgeLUL.png", Texture.class);
        load("Path/Bridge/bridgeRDR.png", Texture.class);
        load("Path/Bridge/bridgeRDU.png", Texture.class);
        load("Path/Bridge/bridgeRUD.png", Texture.class);
        load("Path/Bridge/bridgeRUR.png", Texture.class);
        load("Path/Bridge/bridgeUUR.png", Texture.class);

        load("Path/Bridge/bridgeCrossR.png", Texture.class);
        load("Path/Bridge/bridgeCrossL.png", Texture.class);
        load("Path/Bridge/bridgeCrossU.png", Texture.class);
        load("Path/Bridge/bridgeCrossD.png", Texture.class);

        load("Path/Bridge/waterBridgeDDR1.png", Texture.class);
        load("Path/Bridge/waterBridgeDDR2.png", Texture.class);
        load("Path/Bridge/waterBridgeUUR1.png", Texture.class);
        load("Path/Bridge/waterBridgeUUR2.png", Texture.class);

        load("Path/Turn/bridgeLnoArrows.png", Texture.class);
        load("Path/Turn/bridgeRnoArrows.png", Texture.class);
        load("Path/Turn/bridgeUnoArrows.png", Texture.class);
        load("Path/Turn/bridgeDnoArrows.png", Texture.class);
        load("Path/Turn/waterBridgeLnoArrows.png", Texture.class);
        load("Path/Turn/waterBridgeRnoArrows.png", Texture.class);
        load("Path/Turn/waterBridgeUnoArrows.png", Texture.class);
        load("Path/Turn/waterBridgeDnoArrows.png", Texture.class);

        load("Path/Turn/turnWaterGroundLDL.png", Texture.class);
        load("Path/Turn/turnWaterGroundLDU.png", Texture.class);
        load("Path/Turn/turnWaterGroundLDL.png", Texture.class);
        load("Path/Turn/turnWaterGroundLUD.png", Texture.class);
        load("Path/Turn/turnWaterGroundLUL.png", Texture.class);
        load("Path/Turn/turnWaterGroundRDR.png", Texture.class);
        load("Path/Turn/turnWaterGroundRDU.png", Texture.class);
        load("Path/Turn/turnWaterGroundRUD.png", Texture.class);
        load("Path/Turn/turnWaterGroundRUR.png", Texture.class);

        load("Path/Turn/turnGroundWaterLUD.png", Texture.class);
        load("Path/Turn/turnGroundWaterLDL.png", Texture.class);
        load("Path/Turn/turnGroundWaterLDU.png", Texture.class);
        load("Path/Turn/turnGroundWaterLUL.png", Texture.class);
        load("Path/Turn/turnGroundWaterRUD.png", Texture.class);
        load("Path/Turn/turnGroundWaterRDU.png", Texture.class);
        load("Path/Turn/turnGroundWaterRUR.png", Texture.class);
        load("Path/Turn/turnGroundWaterRDR.png", Texture.class);
        load("Path/Turn/turnGroundWaterRRD.png", Texture.class);
        load("Path/Turn/turnGroundWaterRRU.png", Texture.class);
        load("Path/Turn/turnGroundWaterUUR.png", Texture.class);
        load("Path/Turn/turnGroundWaterUUL.png", Texture.class);

        load("Path/Turn/cross.png", Texture.class);

        load("Path/Portal/portalD1.png", Texture.class);
        load("Path/Portal/portalU1.png", Texture.class);
        load("Path/Portal/portalR1.png", Texture.class);
        load("Path/Portal/portalL1.png", Texture.class);
        load("Path/Portal/portalD2.png", Texture.class);
        load("Path/Portal/portalU2.png", Texture.class);
        load("Path/Portal/portalR2.png", Texture.class);
        load("Path/Portal/portalL2.png", Texture.class);
        load("Path/Portal/portalD3.png", Texture.class);
        load("Path/Portal/portalU3.png", Texture.class);
        load("Path/Portal/portalR3.png", Texture.class);
        load("Path/Portal/portalL3.png", Texture.class);
        load("Path/Portal/portalD4.png", Texture.class);
        load("Path/Portal/portalU4.png", Texture.class);
        load("Path/Portal/portalR4.png", Texture.class);
        load("Path/Portal/portalL4.png", Texture.class);
        load("Path/Portal/portalD5.png", Texture.class);
        load("Path/Portal/portalU5.png", Texture.class);
        load("Path/Portal/portalR5.png", Texture.class);
        load("Path/Portal/portalL5.png", Texture.class);
        load("Path/Portal/portalD6.png", Texture.class);
        load("Path/Portal/portalU6.png", Texture.class);
        load("Path/Portal/portalR6.png", Texture.class);
        load("Path/Portal/portalL6.png", Texture.class);

        load("Path/Signer/signLL.png", Texture.class);
        load("Path/Signer/signRR.png", Texture.class);
        load("Path/Signer/signUU.png", Texture.class);
        load("Path/Signer/signDD.png", Texture.class);
        load("Path/Signer/signLU.png", Texture.class);
        load("Path/Signer/signLD.png", Texture.class);
        load("Path/Signer/signRU.png", Texture.class);
        load("Path/Signer/signRD.png", Texture.class);
        load("Path/Signer/signUL.png", Texture.class);
        load("Path/Signer/signUR.png", Texture.class);
        load("Path/Signer/signDL.png", Texture.class);
        load("Path/Signer/signDR.png", Texture.class);

        load("Path/Signer/signRL.png", Texture.class);
        load("Path/Signer/signLR.png", Texture.class);
//        load("Path/Signer/signUD.png", Texture.class);

        load("AbilityIcons/Effects/swimSpeed.png", Texture.class);
        load("AbilityIcons/Effects/waterBonusArmor.png", Texture.class);
        load("AbilityIcons/Effects/blockDmg.png", Texture.class);
        load("AbilityIcons/Effects/slow.png", Texture.class);
        load("AbilityIcons/Effects/armorReduction.png", Texture.class);
        load("AbilityIcons/Effects/bonusArmor.png", Texture.class);
        load("AbilityIcons/Effects/fireArrow.png", Texture.class);
        load("AbilityIcons/Effects/bash.png", Texture.class);
        load("AbilityIcons/Effects/heal.png", Texture.class);
        load("AbilityIcons/Effects/sprint.png", Texture.class);
        load("AbilityIcons/Effects/scorpionVenom.png", Texture.class);
        load("AbilityIcons/Effects/greatEvasion.png", Texture.class);
        load("AbilityIcons/Effects/waterShield.png", Texture.class);


//        load("Mobs/mob.png", Texture.class);
//        load("Mobs/mob2.png", Texture.class);
        load("Mobs/wolf0.png", Texture.class);
        load("Mobs/wolf1.png", Texture.class);
        load("Mobs/wolf2.png", Texture.class);
        load("Mobs/wolf3.png", Texture.class);
        load("Mobs/eagle0.png", Texture.class);
        load("Mobs/eagle1.png", Texture.class);
        load("Mobs/eagle2.png", Texture.class);
        load("Mobs/eagle3.png", Texture.class);
        load("Mobs/hedgehog0.png", Texture.class);
        load("Mobs/hedgehog1.png", Texture.class);
        load("Mobs/hedgehog2.png", Texture.class);
        load("Mobs/hedgehog3.png", Texture.class);
        load("Mobs/hedgehogBlock0.png", Texture.class);
        load("Mobs/hedgehogBlock1.png", Texture.class);
        load("Mobs/hedgehogBlock2.png", Texture.class);
        load("Mobs/hedgehogBlock3.png", Texture.class);
        load("Mobs/lynx0.png", Texture.class);
        load("Mobs/lynx1.png", Texture.class);
        load("Mobs/lynx2.png", Texture.class);
        load("Mobs/lynx3.png", Texture.class);
        load("Mobs/boar0.png", Texture.class);
        load("Mobs/boar1.png", Texture.class);
        load("Mobs/boar2.png", Texture.class);
        load("Mobs/boar3.png", Texture.class);

        load("Mobs/ant0.png", Texture.class);
        load("Mobs/ant1.png", Texture.class);
        load("Mobs/ant2.png", Texture.class);
        load("Mobs/ant3.png", Texture.class);
        load("Mobs/jerboa0.png", Texture.class);
        load("Mobs/jerboa1.png", Texture.class);
        load("Mobs/jerboa2.png", Texture.class);
        load("Mobs/jerboa3.png", Texture.class);
        load("Mobs/scorpion0.png", Texture.class);
        load("Mobs/scorpion1.png", Texture.class);
        load("Mobs/scorpion2.png", Texture.class);
        load("Mobs/scorpion3.png", Texture.class);
        load("Mobs/snake0.png", Texture.class);
        load("Mobs/snake1.png", Texture.class);
        load("Mobs/snake2.png", Texture.class);
        load("Mobs/snake3.png", Texture.class);

        load("Mobs/crab0.png", Texture.class);
        load("Mobs/crab1.png", Texture.class);
        load("Mobs/crab2.png", Texture.class);
        load("Mobs/crab3.png", Texture.class);
        load("Mobs/frog0.png", Texture.class);
        load("Mobs/frog1.png", Texture.class);
        load("Mobs/frog2.png", Texture.class);
        load("Mobs/frog3.png", Texture.class);
        load("Mobs/turtle0.png", Texture.class);
        load("Mobs/turtle1.png", Texture.class);
        load("Mobs/turtle2.png", Texture.class);
        load("Mobs/turtle3.png", Texture.class);
        load("Mobs/turtleSwim0.png", Texture.class);
        load("Mobs/turtleSwim1.png", Texture.class);
        load("Mobs/turtleSwim2.png", Texture.class);
        load("Mobs/turtleSwim3.png", Texture.class);
        load("Mobs/crocodile0.png", Texture.class);
        load("Mobs/crocodile1.png", Texture.class);
        load("Mobs/crocodile2.png", Texture.class);
        load("Mobs/crocodile3.png", Texture.class);
        load("Mobs/crocodileSwim0.png", Texture.class);
        load("Mobs/crocodileSwim1.png", Texture.class);
        load("Mobs/crocodileSwim2.png", Texture.class);
        load("Mobs/crocodileSwim3.png", Texture.class);

        load("Mobs/ufo0.png", Texture.class);
        load("Mobs/ufo1.png", Texture.class);
        load("Mobs/ufo2.png", Texture.class);
        load("Mobs/ufo3.png", Texture.class);
        load("Mobs/spaceShip0.png", Texture.class);
        load("Mobs/spaceShip1.png", Texture.class);
        load("Mobs/spaceShip2.png", Texture.class);
        load("Mobs/spaceShip3.png", Texture.class);
        load("Mobs/energySphere0.png", Texture.class);
        load("Mobs/energySphere1.png", Texture.class);
        load("Mobs/energySphere2.png", Texture.class);
        load("Mobs/energySphere3.png", Texture.class);
        load("Mobs/galaxyLordFirst0.png", Texture.class);
        load("Mobs/galaxyLordFirst1.png", Texture.class);
        load("Mobs/galaxyLordFirst2.png", Texture.class);
        load("Mobs/galaxyLordFirst3.png", Texture.class);
        load("Mobs/galaxyLordSecond0.png", Texture.class);
        load("Mobs/galaxyLordSecond1.png", Texture.class);
        load("Mobs/galaxyLordSecond2.png", Texture.class);
        load("Mobs/galaxyLordSecond3.png", Texture.class);
        load("Mobs/galaxyLordThird0.png", Texture.class);
        load("Mobs/galaxyLordThird1.png", Texture.class);
        load("Mobs/galaxyLordThird2.png", Texture.class);
        load("Mobs/galaxyLordThird3.png", Texture.class);
        load("Mobs/galaxyLordLast0.png", Texture.class);
        load("Mobs/galaxyLordLast1.png", Texture.class);
        load("Mobs/galaxyLordLast2.png", Texture.class);
        load("Mobs/galaxyLordLast3.png", Texture.class);


//        load("Mobs/mob3.png", Texture.class);
//        load("Mobs/mob4.png", Texture.class);
//        load("Mobs/mob5.png", Texture.class);
//        load("Mobs/mob6walk.png", Texture.class);
//        load("Mobs/mob6swim.png", Texture.class);
//        load("Mobs/turtle.png", Texture.class);

//        load("Mobs/lynx.png", Texture.class);
//        load("Mobs/turtle2.png", Texture.class);

//        load("infoPanelFone.png", Texture.class);
        load("levelLooseBg.png", Texture.class);
        load("levelWinBg.png", Texture.class);
        load("buildGridLinePixel.png", Texture.class);
        load("towerRangeTexture.png", Texture.class);





    }
    public void disposeLevelMap(){
        unload("Path/grass.png");
        unload("Path/castle.png");
        unload("Path/waterHorizontal.png");
        unload("Path/waterVertical.png");
        unload("Path/roadHorizontal.png");
        unload("Path/roadVertical.png");
        unload("Path/Turn/turnLU.png");
        unload("Path/Turn/turnLD.png");
        unload("Path/Turn/turnRU.png");
        unload("Path/Turn/turnRD.png");
        unload("Path/Turn/turnWaterLU.png");
        unload("Path/Turn/turnWaterLD.png");
        unload("Path/Turn/turnWaterRU.png");
        unload("Path/Turn/turnWaterRD.png");

//        unload("Path/Bridge/bridgeLUD1.png");
//        unload("Path/Bridge/bridgeLUD2.png");
//        unload("Path/Bridge/bridgeRUD1.png");
//        unload("Path/Bridge/bridgeRUD2.png");
//        unload("Path/Bridge/bridgeULR1.png");
//        unload("Path/Bridge/bridgeULR2.png");
//        unload("Path/Bridge/bridgeDLR1.png");
//        unload("Path/Bridge/bridgeDLR2.png");
//
//        unload("Path/Bridge/bridgeUUL1.png");
//        unload("Path/Bridge/bridgeUUL2.png");
//        unload("Path/Bridge/bridgeUUR1.png");
//        unload("Path/Bridge/bridgeUUR2.png");
//        unload("Path/Bridge/bridgeDDL1.png");
//        unload("Path/Bridge/bridgeDDL2.png");
//        unload("Path/Bridge/bridgeDDR1.png");
//        unload("Path/Bridge/bridgeDDR2.png");
        unload("Path/Bridge/bridgeLDL.png");
        unload("Path/Bridge/bridgeLDU.png");
        unload("Path/Bridge/bridgeLUD.png");
        unload("Path/Bridge/bridgeLUL.png");
        unload("Path/Bridge/bridgeRDR.png");
        unload("Path/Bridge/bridgeRDU.png");
        unload("Path/Bridge/bridgeRUD.png");
        unload("Path/Bridge/bridgeRUR.png");
        unload("Path/Bridge/bridgeUUR.png");

        unload("Path/Bridge/bridgeCrossR.png");
        unload("Path/Bridge/bridgeCrossL.png");
        unload("Path/Bridge/bridgeCrossU.png");
        unload("Path/Bridge/bridgeCrossD.png");

        unload("Path/Turn/bridgeLnoArrows.png");
        unload("Path/Turn/bridgeRnoArrows.png");
        unload("Path/Turn/bridgeUnoArrows.png");
        unload("Path/Turn/bridgeDnoArrows.png");
        unload("Path/Turn/waterBridgeLnoArrows.png");
        unload("Path/Turn/waterBridgeRnoArrows.png");
        unload("Path/Turn/waterBridgeUnoArrows.png");
        unload("Path/Turn/waterBridgeDnoArrows.png");

        unload("Path/Turn/turnWaterGroundLDL.png");
        unload("Path/Turn/turnWaterGroundLDU.png");
        unload("Path/Turn/turnWaterGroundLDL.png");
        unload("Path/Turn/turnWaterGroundLUD.png");
        unload("Path/Turn/turnWaterGroundLUL.png");
        unload("Path/Turn/turnWaterGroundRDR.png");
        unload("Path/Turn/turnWaterGroundRDU.png");
        unload("Path/Turn/turnWaterGroundRUD.png");
        unload("Path/Turn/turnWaterGroundRUR.png");

        unload("Path/Portal/portalD1.png");
        unload("Path/Portal/portalU1.png");
        unload("Path/Portal/portalR1.png");
        unload("Path/Portal/portalL1.png");
        unload("Path/Portal/portalD2.png");
        unload("Path/Portal/portalU2.png");
        unload("Path/Portal/portalR2.png");
        unload("Path/Portal/portalL2.png");
        unload("Path/Portal/portalD3.png");
        unload("Path/Portal/portalU3.png");
        unload("Path/Portal/portalR3.png");
        unload("Path/Portal/portalL3.png");
        unload("Path/Portal/portalD4.png");
        unload("Path/Portal/portalU4.png");
        unload("Path/Portal/portalR4.png");
        unload("Path/Portal/portalL4.png");
        unload("Path/Portal/portalD5.png");
        unload("Path/Portal/portalU5.png");
        unload("Path/Portal/portalR5.png");
        unload("Path/Portal/portalL5.png");
        unload("Path/Portal/portalD6.png");
        unload("Path/Portal/portalU6.png");
        unload("Path/Portal/portalR6.png");
        unload("Path/Portal/portalL6.png");

        unload("Path/Signer/signLL.png");
        unload("Path/Signer/signRR.png");
        unload("Path/Signer/signUU.png");
        unload("Path/Signer/signDD.png");
        unload("Path/Signer/signLU.png");
        unload("Path/Signer/signLD.png");
        unload("Path/Signer/signRU.png");
        unload("Path/Signer/signRD.png");
        unload("Path/Signer/signUL.png");
        unload("Path/Signer/signUR.png");
        unload("Path/Signer/signDL.png");
        unload("Path/Signer/signDR.png");

        unload("AbilityIcons/Effects/swimSpeed.png");
        unload("AbilityIcons/Effects/waterBonusArmor.png");
        unload("AbilityIcons/Effects/blockDmg.png");
        unload("AbilityIcons/Effects/slow.png");
        unload("AbilityIcons/Effects/armorReduction.png");
        unload("AbilityIcons/Effects/bonusArmor.png");
        unload("AbilityIcons/Effects/fireArrow.png");
        unload("AbilityIcons/Effects/bash.png");
        unload("AbilityIcons/Effects/heal.png");

        unload("Mobs/wolf0.png");
        unload("Mobs/wolf1.png");
        unload("Mobs/wolf2.png");
        unload("Mobs/wolf3.png");
        unload("Mobs/eagle0.png");
        unload("Mobs/eagle1.png");
        unload("Mobs/eagle2.png");
        unload("Mobs/eagle3.png");
        unload("Mobs/hedgehog0.png");
        unload("Mobs/hedgehog1.png");
        unload("Mobs/hedgehog2.png");
        unload("Mobs/hedgehog3.png");
        unload("Mobs/hedgehogBlock0.png");
        unload("Mobs/hedgehogBlock1.png");
        unload("Mobs/hedgehogBlock2.png");
        unload("Mobs/hedgehogBlock3.png");
        unload("Mobs/lynx0.png");
        unload("Mobs/lynx1.png");
        unload("Mobs/lynx2.png");
        unload("Mobs/lynx3.png");
        unload("Mobs/boar0.png");
        unload("Mobs/boar1.png");
        unload("Mobs/boar2.png");
        unload("Mobs/boar3.png");

        unload("Mobs/ant0.png");
        unload("Mobs/ant1.png");
        unload("Mobs/ant2.png");
        unload("Mobs/ant3.png");
        unload("Mobs/jerboa0.png");
        unload("Mobs/jerboa1.png");
        unload("Mobs/jerboa2.png");
        unload("Mobs/jerboa3.png");
        unload("Mobs/scorpion0.png");
        unload("Mobs/scorpion1.png");
        unload("Mobs/scorpion2.png");
        unload("Mobs/scorpion3.png");
        unload("Mobs/snake0.png");
        unload("Mobs/snake1.png");
        unload("Mobs/snake2.png");
        unload("Mobs/snake3.png");

        unload("Mobs/crab0.png");
        unload("Mobs/crab1.png");
        unload("Mobs/crab2.png");
        unload("Mobs/crab3.png");
        unload("Mobs/frog0.png");
        unload("Mobs/frog1.png");
        unload("Mobs/frog2.png");
        unload("Mobs/frog3.png");
        unload("Mobs/turtle0.png");
        unload("Mobs/turtle1.png");
        unload("Mobs/turtle2.png");
        unload("Mobs/turtle3.png");

        unload("Mobs/ufo0.png");
        unload("Mobs/ufo1.png");
        unload("Mobs/ufo2.png");
        unload("Mobs/ufo3.png");
        unload("Mobs/spaceShip0.png");
        unload("Mobs/spaceShip1.png");
        unload("Mobs/spaceShip2.png");
        unload("Mobs/spaceShip3.png");
        unload("Mobs/energySphere0.png");
        unload("Mobs/energySphere1.png");
        unload("Mobs/energySphere2.png");
        unload("Mobs/energySphere3.png");
        unload("Mobs/galaxyLordFirst0.png");
        unload("Mobs/galaxyLordFirst1.png");
        unload("Mobs/galaxyLordFirst2.png");
        unload("Mobs/galaxyLordFirst3.png");
        unload("Mobs/galaxyLordSecond0.png");
        unload("Mobs/galaxyLordSecond1.png");
        unload("Mobs/galaxyLordSecond2.png");
        unload("Mobs/galaxyLordSecond3.png");
        unload("Mobs/galaxyLordThird0.png");
        unload("Mobs/galaxyLordThird1.png");
        unload("Mobs/galaxyLordThird2.png");
        unload("Mobs/galaxyLordThird3.png");
        unload("Mobs/galaxyLordLast0.png");
        unload("Mobs/galaxyLordLast1.png");
        unload("Mobs/galaxyLordLast2.png");
        unload("Mobs/galaxyLordLast3.png");



//        unload("Mobs/mob.png");
////        unload("Mobs/mob2.png");
//        unload("Mobs/mob3.png");
//        unload("Mobs/mob4.png");
//        unload("Mobs/mob5.png");
//        unload("Mobs/mob6walk.png");
//        unload("Mobs/mob6swim.png");

//        unload("infoPanelFone.png");
//        unload("levelLooseBg.png");
//        unload("levelWinBg.png");
        unload("buildGridLinePixel.png");
        unload("towerRangeTexture.png");
    }
    public void disposeLevelEndScreen(){
        unload("infoPanelFone.png");
        unload("levelLooseBg.png");
        unload("levelWinBg.png");
    }

    public void loadShop(){
        load("sellButton.png", Texture.class);



    }
    public void loadSmith(){
        load("maxHpGrade.png", Texture.class);
        load("maxEnergyGrade.png", Texture.class);
        load("prevButton.png", Texture.class);
        load("nextButton.png", Texture.class);




    }

    public Texture getEffectIcon(String effectIconPath){
        return get("AbilityIcons/Effects/" + effectIconPath + ".png", Texture.class);
    }
    public Texture getAbilityIcon(String abilityIconPath){
        return get("AbilityIcons/Abilities/" + abilityIconPath + ".png", Texture.class);
    }

    public Texture getSpellIcon(String path){
        return get("AbilityIcons/Spells/" + path + ".png", Texture.class);//
    }

    public Texture getGemTexure(User.GEM_TYPE type){
        switch (type){
            case RED:   return get("Gems/redGem.png", Texture.class);
            case YELLOW:return get("Gems/yellowGem.png", Texture.class);
            case BLUE:  return get("Gems/blueGem.png", Texture.class);
            case BLACK: return get("Gems/blackGem.png", Texture.class);
            case GREEN: return get("Gems/greenGem.png", Texture.class);
            case WHITE: return get("Gems/whiteGem.png", Texture.class);
            default: throw new IllegalArgumentException("Wrong gem type");
        }
    }

    public Texture getMobTexture(String path){
        return get("Mobs/" + path + ".png", Texture.class);
    }


    public Texture getTurn(String texturePath){//
        Texture t = get(texturePath, Texture.class);
//        t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return t;
    }

    public String getWord(String word){
        return b.get(word);
    }

    public String getLanguage(){
//        if(b.getLocale() == Locale.US) return "us";
        return b.getLocale().getLanguage();
    }
    public void changeLang(String locale){
        unload("Language/text");
        I18NBundleLoader.I18NBundleParameter param = new I18NBundleLoader.I18NBundleParameter(new Locale(locale), "UTF-8");
        load("Language/text", I18NBundle.class, param);
        finishLoading();
        b = get("Language/text", I18NBundle.class);
        GDefence.getInstance().initTips();
        GDefence.getInstance().initScreens();
        GDefence.getInstance().switchScreen(GDefence.getInstance().getOptionScreen());
    }
    public void initLang(String locale){
        I18NBundleLoader.I18NBundleParameter param = new I18NBundleLoader.I18NBundleParameter(new Locale(locale), "UTF-8");
        load("Language/text", I18NBundle.class, param);
        finishLoading();
        b = get("Language/text", I18NBundle.class);
    }


    public ImageButton.ImageButtonStyle generateImageButtonSkin(Texture t){
//        t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);//TODO
        ImageButton.ImageButtonStyle s = new ImageButton.ImageButtonStyle();
//        System.out.println(t.getMinFilter() + " " + t.getMagFilter());
//        System.out.println(new TextureRegionDrawable(new TextureRegion(t)).getRegion().getTexture().getMinFilter());
//        s.up = new TextureRegionDrawable(new TextureRegion(t));
        s.up = new SpriteDrawable(new Sprite(t));
//        s.over = new TextureRegionDrawable(new TextureRegion(t));
//        s.down = new TextureRegionDrawable(new TextureRegion(t));
        return s;

    }

    public ImageButton.ImageButtonStyle generateResearchButtonSkin(String path){
        Texture t = get("Researches/" + path + ".png");

        ImageButton.ImageButtonStyle s = new ImageButton.ImageButtonStyle();
        s.up = new TextureRegionDrawable(new TextureRegion(t));
        s.over = new TextureRegionDrawable(new TextureRegion(t));
        s.down = new TextureRegionDrawable(new TextureRegion(t));

        return s;

    }
    public Texture getLockedTowerTexture(User.RecipeType t){
        switch (t){
            case opened: return get("openedTower.png", Texture.class);
            case canOpen: return get("canOpenTower.png", Texture.class);
            case locked: return get("lockedTower.png", Texture.class);
            default: return null;
        }
    }



    public ImageButton.ImageButtonStyle getMainMenuButtons(int number){
        ImageButton.ImageButtonStyle s = new ImageButton.ImageButtonStyle();
        Texture mainMenuButtonsAtlas = get("MainMenuAtlas.png", Texture.class);
        TextureRegion[][] mainMenuButtons = TextureRegion.split(mainMenuButtonsAtlas, mainMenuButtonsAtlas.getWidth() / 2, mainMenuButtonsAtlas.getHeight() / 10);

        switch (number){
            case 1:
                s.up = new TextureRegionDrawable(mainMenuButtons[0][0]);
                s.over = new TextureRegionDrawable(mainMenuButtons[0][1]);
                s.down = new TextureRegionDrawable(mainMenuButtons[0][1]);
                return s;
            case 2:
                s.up = new TextureRegionDrawable(mainMenuButtons[1][0]);
                s.over = new TextureRegionDrawable(mainMenuButtons[1][1]);
                s.down = new TextureRegionDrawable(mainMenuButtons[1][1]);
                return s;
            case 3:
                s.up = new TextureRegionDrawable(mainMenuButtons[2][0]);
                s.over = new TextureRegionDrawable(mainMenuButtons[2][1]);
                s.down = new TextureRegionDrawable(mainMenuButtons[2][1]);
                return s;
        }
        return null;
    }



    public ImageTextButton.ImageTextButtonStyle getNameButtonStyle(){
        ImageTextButton.ImageTextButtonStyle nameButtonStyle = new ImageTextButton.ImageTextButtonStyle();
        nameButtonStyle.up = new TextureRegionDrawable(new TextureRegion(get("nameButtonFone.png", Texture.class)));
        nameButtonStyle.over = new TextureRegionDrawable(new TextureRegion(get("nameButtonFone.png", Texture.class)));
        nameButtonStyle.down = new TextureRegionDrawable(new TextureRegion(get("nameButtonFone.png", Texture.class)));
        nameButtonStyle.font = new BitmapFont(Gdx.files.internal("Fonts/MainFont.fnt"));

        return nameButtonStyle;
    }
    public ImageTextButton.ImageTextButtonStyle getSpellPanelCdButtonStyle(){
        ImageTextButton.ImageTextButtonStyle s = new ImageTextButton.ImageTextButtonStyle();
        s.up = new TextureRegionDrawable(new TextureRegion(get("spellCdTransparent.png", Texture.class)));
        s.over = new TextureRegionDrawable(new TextureRegion(get("spellCdTransparent.png", Texture.class)));
        s.down = new TextureRegionDrawable(new TextureRegion(get("spellCdTransparent.png", Texture.class)));
        s.font = FontLoader.generateFont(30, Color.WHITE, 4, Color.BLACK);
        return s;
    }


    public ImageButton.ImageButtonStyle getPrevButtonSkin(){
//        ImageButton.ImageButtonStyle s = new ImageButton.ImageButtonStyle();
//        s.up = new TextureRegionDrawable(new TextureRegion(get("prevButton.png", Texture.class)));
//        s.over = new TextureRegionDrawable(new TextureRegion(get("prevButton.png", Texture.class)));
//        s.down = new TextureRegionDrawable(new TextureRegion(get("prevButton.png", Texture.class)));

        return generateImageButtonSkin(get("prevButton.png", Texture.class));
    }
    public ImageButton.ImageButtonStyle getNextButtonSkin(){
//        ImageButton.ImageButtonStyle s = new ImageButton.ImageButtonStyle();
//        s.up = new TextureRegionDrawable(new TextureRegion(get("nextButton.png", Texture.class)));
//        s.over = new TextureRegionDrawable(new TextureRegion(get("nextButton.png", Texture.class)));
//        s.down = new TextureRegionDrawable(new TextureRegion(get("nextButton.png", Texture.class)));

        return generateImageButtonSkin(get("nextButton.png", Texture.class));
    }



    public ImageButton.ImageButtonStyle getBackButtonSkin(){
//        ImageButton.ImageButtonStyle backButtonStyle = new ImageButton.ImageButtonStyle();
//        backButtonStyle.up = new TextureRegionDrawable(new TextureRegion(get("backButton.png", Texture.class)));
//        backButtonStyle.over = new TextureRegionDrawable(new TextureRegion(get("backButton.png", Texture.class)));
//        backButtonStyle.down = new TextureRegionDrawable(new TextureRegion(get("backButton.png", Texture.class)));

        return generateImageButtonSkin(get("backButton.png", Texture.class));
    }

    public ProgressBar.ProgressBarStyle getExpBarSkin(){
        TextureRegionDrawable barFone = new TextureRegionDrawable(new TextureRegion(get("expBar1.png", Texture.class)));
        TextureRegionDrawable barTop = new TextureRegionDrawable(new TextureRegion(get("expBar2.png", Texture.class)));
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle(barFone, barTop);
        progressBarStyle.knobBefore = progressBarStyle.knob;

        return progressBarStyle;


    }
    public ProgressBar.ProgressBarStyle getEnergyBarSkin(){
        TextureRegionDrawable barFone = new TextureRegionDrawable(new TextureRegion(get("energyBarBg.png", Texture.class)));
        TextureRegionDrawable barTop = new TextureRegionDrawable(new TextureRegion(get("energyBarKnob.png", Texture.class)));
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle(barFone, barTop);
        progressBarStyle.knobBefore = progressBarStyle.knob;

        return progressBarStyle;


    }
    public ProgressBar.ProgressBarStyle getMobHpBarStyle(){
//        ProgressBar.ProgressBarStyle st = GDefence.getInstance().assetLoader.getSkin(), "health-bar";
        TextureRegionDrawable barFone = new TextureRegionDrawable(new TextureRegion(get("mobHpBarBg.png", Texture.class)));
        TextureRegionDrawable barTop = new TextureRegionDrawable(new TextureRegion(get("mobHpBarKnob.png", Texture.class)));
        ProgressBar.ProgressBarStyle style = new ProgressBar.ProgressBarStyle(barFone, barTop);
        style.knobBefore = style.knob;
        return style;
    }

    public TextButton.TextButtonStyle getCampainLevelStyle(){
        TextButton.TextButtonStyle s = new TextButton.TextButtonStyle();

        s.up = new TextureRegionDrawable(new TextureRegion(get("cell.png", Texture.class)));
        s.font = FontLoader.generateFont(52, Color.WHITE);
        s.fontColor = Color.BLACK;

        return s;
    }


//    public  ImageButton.ImageButtonStyle getCampainLevelSkin(int number){
//        ImageButton.ImageButtonStyle s = new ImageButton.ImageButtonStyle();
//
//        s.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainLevels[number - 1][0]));
//        s.over = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainLevels[number - 1][1]));
//        s.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.campainLevels[number - 1][0]));
//
//        return s;
//    }
    public ImageTextButton.ImageTextButtonStyle getUserLevelSkin(){
        ImageTextButton.ImageTextButtonStyle userLevelButtonStyle = new ImageTextButton.ImageTextButtonStyle();
        userLevelButtonStyle.up = new TextureRegionDrawable(new TextureRegion(get("cell.png", Texture.class)));
        userLevelButtonStyle.over = new TextureRegionDrawable(new TextureRegion(get("cell.png", Texture.class)));
        userLevelButtonStyle.down = new TextureRegionDrawable(new TextureRegion(get("cell.png", Texture.class)));
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




    public ImageButton.ImageButtonStyle getBottomPanelSkin(int index){
        switch (index){
            case 1:
                return generateImageButtonSkin(get("arsenal.png", Texture.class));
//                ImageButton.ImageButtonStyle arsenalButtonStyle = new ImageButton.ImageButtonStyle();
//                arsenalButtonStyle.up = new TextureRegionDrawable(new TextureRegion(get("arsenal.png", Texture.class)));
//                arsenalButtonStyle.over = new TextureRegionDrawable(new TextureRegion(get("arsenal.png", Texture.class)));
//                arsenalButtonStyle.down = new TextureRegionDrawable(new TextureRegion(get("arsenal.png", Texture.class)));
//                return arsenalButtonStyle;
            case 2:
                return generateImageButtonSkin(get("store.png", Texture.class));
//                ImageButton.ImageButtonStyle storeButtonStyle = new ImageButton.ImageButtonStyle();
//                storeButtonStyle.up = new TextureRegionDrawable(new TextureRegion(get("storeNew.png", Texture.class)));
//                storeButtonStyle.over = new TextureRegionDrawable(new TextureRegion(get("storeNew.png", Texture.class)));
//                storeButtonStyle.down = new TextureRegionDrawable(new TextureRegion(get("storeNew.png", Texture.class)));
//                return storeButtonStyle;
            case 3:
                return generateImageButtonSkin(get("smith.png", Texture.class));
//                ImageButton.ImageButtonStyle smithButtonStyle = new ImageButton.ImageButtonStyle();
//                smithButtonStyle.up = new TextureRegionDrawable(new TextureRegion(get("smith.png", Texture.class)));
//                smithButtonStyle.over = new TextureRegionDrawable(new TextureRegion(get("smith.png", Texture.class)));
//                smithButtonStyle.down = new TextureRegionDrawable(new TextureRegion(get("smith.png", Texture.class)));
//                return smithButtonStyle;
            case 4:
                return null;

            case 5:
                return null;
        }
        return null;
    }
    public Skin getSkin(){
        return get("skins/uiskin.json", Skin.class);
//        return new Skin(Gdx.files.internal("uiskin.json"));
    }

    public Label.LabelStyle getTimerSkin(){
//        Label.LabelStyle l = new Label.LabelStyle();
//        l.font = FontLoader.impact24;
//        l.fontColor = Color.BLACK;
        return FontLoader.generateStyle(24, Color.BLACK);
    }
    public Label.LabelStyle getInfoPanelSkin(){
//        Label.LabelStyle l = new Label.LabelStyle();
//        l.font = FontLoader.impact16;
//        l.fontColor = Color.BLACK;
        return FontLoader.generateStyle(16, Color.BLACK);
    }
    public Label.LabelStyle getCurrentInfoPanelSkin(){
//        Label.LabelStyle l = new Label.LabelStyle();
//        l.font = FontLoader.impact24;
//        l.fontColor = Color.BLACK;
        return FontLoader.generateStyle(20, Color.BLACK);
    }
    public Label.LabelStyle getLevelEndResultSkin(){
//        Label.LabelStyle l = new Label.LabelStyle();
//        l.font = FontLoader.impact36;
//        l.fontColor = Color.BLACK;
        return FontLoader.generateStyle(36, Color.BLACK);
    }

//    public ImageButton.ImageButtonStyle getTowerCellSkin(ItemEnum.Tower tower){//reworked
//        switch (tower){
//            case Basic:
//                return generateImageButtonSkin(get("Tower/basicTower.png", Texture.class));
//            case Rock:
//                return generateImageButtonSkin(get("Tower/rockTower.png", Texture.class));
//            case Arrow:
//                return generateImageButtonSkin(get("Tower/arrowTower.png", Texture.class));
//            case Range:
//                return generateImageButtonSkin(get("Tower/rangeTower.png", Texture.class));
//            default:
//                throw new RuntimeException("No tower found with id: " + tower);
//        }
//    }
//    public Texture getTowerTexture(ItemEnum.Tower tower){//reworked
//        switch (tower){
//            case Basic:
//                return get("Tower/basicTower.png", Texture.class);
//            case Rock:
//                return get("Tower/rockTower.png", Texture.class);
//            case Arrow:
//                return get("Tower/arrowTower.png", Texture.class);
//            case Range:
//                return get("Tower/rangeTower.png", Texture.class);
//            default:
//                throw new RuntimeException("No tower found with id: " + tower);
//        }
//    }
//    public Texture getProjectileTexture(ItemEnum.Tower tower){//reworked
//        switch (tower){
//            case Basic:
//                return get("Projectiles/basic.png", Texture.class);
//            case Rock:
//                return get("Projectiles/rock.png", Texture.class);
//            case Arrow:
//                return get("Projectiles/arrow.png", Texture.class);
//            case Range:
//                return get("Projectiles/range.png", Texture.class);
//            default:
//                throw new RuntimeException("No tower found with id: " + tower);
//        }
//    }


    public ImageButton.ImageButtonStyle getStoreSellButtonStyle(){
//        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
//        style.up = new TextureRegionDrawable((new TextureRegion(sellButton)));
//        style.over = new TextureRegionDrawable((new TextureRegion(sellButton)));
//        style.down = new TextureRegionDrawable((new TextureRegion(sellButton)));

        return generateImageButtonSkin(get("sellButton.png", Texture.class));


    }


    public void dispose() {
//        mainMenuButtonsAtlas.dispose();
    }



}
