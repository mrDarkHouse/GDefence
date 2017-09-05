package com.darkhouse.gdefence.Helpers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.ObjectMap;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Objects.GameObject;
import com.darkhouse.gdefence.User;

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


    public void loadAll() {
        loadTextures();
        loadLevelMap();
        loadShop();
        loadSmith();
    }

    public void setFilters(){
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
        load("Gems/redGem.png", Texture.class);
        load("Gems/yellowGem.png", Texture.class);
        load("Gems/blueGem.png", Texture.class);
        load("Gems/blackGem.png", Texture.class);
        load("Gems/greenGem.png", Texture.class);
        load("Gems/whiteGem.png", Texture.class);
        load("Gems/transparentGem.png", Texture.class);


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
        load("coin.png", Texture.class);
        load("manaCostLabelFon.png", Texture.class);
        load("spellCdTransparent.png", Texture.class);
//        load("spellPanelFon.png", Texture.class);
        load("AbilityIcons/Spells/globalSlow.png", Texture.class);
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


        SkinLoader.SkinParameter parameter = new SkinLoader.SkinParameter(fontMap);

        load("skins/uiskin.json", Skin.class, parameter);

        load("Tower/basic.png", Texture.class);
        load("Tower/rock.png", Texture.class);
        load("Tower/arrow.png", Texture.class);
        load("Tower/range.png", Texture.class);
        load("Tower/short.png", Texture.class);
        load("Tower/mountain.png", Texture.class);
        load("Tower/steelArrow.png", Texture.class);
        load("Tower/ballista.png", Texture.class);
        load("Tower/catapult.png", Texture.class);

        load("Researches/powder.png", Texture.class);
        load("Researches/steam.png", Texture.class);

        load("AbilityIcons/Abilities/bash.png", Texture.class);
        load("AbilityIcons/Abilities/bounce.png", Texture.class);
        load("AbilityIcons/Abilities/buckShot.png", Texture.class);
        load("AbilityIcons/Abilities/crit.png", Texture.class);
        load("AbilityIcons/Abilities/desolate.png", Texture.class);
        load("AbilityIcons/Abilities/doubleAttack.png", Texture.class);
        load("AbilityIcons/Abilities/fireArrow.png", Texture.class);
        load("AbilityIcons/Abilities/hunterSpeed.png", Texture.class);
        load("AbilityIcons/Abilities/multiShot.png", Texture.class);
        load("AbilityIcons/Abilities/poisonArrow.png", Texture.class);
        load("AbilityIcons/Abilities/shotDelay.png", Texture.class);
        load("AbilityIcons/Abilities/splash.png", Texture.class);
        load("AbilityIcons/Abilities/steelArrow.png", Texture.class);







    }
    public void loadLevelMap(){
        //load("MainMenuBg.png", Texture.class);

//        load("ground.png", Texture.class);
        load("Path/grass.png", Texture.class);
        load("Path/castle.png", Texture.class);
        load("Path/waterHorizontal.png", Texture.class);
        load("Path/waterVertical.png", Texture.class);
        load("Path/roadHorizontal.png", Texture.class);
        load("Path/roadVertical.png", Texture.class);
        load("Path/Turn/turnLU.png", Texture.class);
//        get("Path/Turn/turnLU.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        load("Path/Turn/turnLD.png", Texture.class);
//        get("Path/Turn/turnLD.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        load("Path/Turn/turnRU.png", Texture.class);
//        get("Path/Turn/turnRU.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        load("Path/Turn/turnRD.png", Texture.class);
//        get("Path/Turn/turnRD.png", Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        load("Path/Turn/turnWaterLU.png", Texture.class);
        load("Path/Turn/turnWaterLD.png", Texture.class);
        load("Path/Turn/turnWaterRU.png", Texture.class);
        load("Path/Turn/turnWaterRD.png", Texture.class);
        load("Path/Bridge/bridgeLUD1.png", Texture.class);
        load("Path/Bridge/bridgeLUD2.png", Texture.class);
        load("Path/Bridge/bridgeRUD1.png", Texture.class);
        load("Path/Bridge/bridgeRUD2.png", Texture.class);
        load("Path/Bridge/bridgeULR1.png", Texture.class);
        load("Path/Bridge/bridgeULR2.png", Texture.class);
        load("Path/Bridge/bridgeDLR1.png", Texture.class);
        load("Path/Bridge/bridgeDLR2.png", Texture.class);

        load("Path/Bridge/bridgeUUL1.png", Texture.class);
        load("Path/Bridge/bridgeUUL2.png", Texture.class);
        load("Path/Bridge/bridgeUUR1.png", Texture.class);
        load("Path/Bridge/bridgeUUR2.png", Texture.class);
        load("Path/Bridge/bridgeDDL1.png", Texture.class);
        load("Path/Bridge/bridgeDDL2.png", Texture.class);
        load("Path/Bridge/bridgeDDR1.png", Texture.class);
        load("Path/Bridge/bridgeDDR2.png", Texture.class);

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

        load("AbilityIcons/Effects/swimSpeed.png", Texture.class);
        load("AbilityIcons/Effects/waterBonusArmor.png", Texture.class);
        load("AbilityIcons/Effects/blockDmg.png", Texture.class);
        load("AbilityIcons/Effects/slow.png", Texture.class);
        load("AbilityIcons/Effects/armorReduction.png", Texture.class);
        load("AbilityIcons/Effects/bonusArmor.png", Texture.class);
        load("AbilityIcons/Effects/fireArrow.png", Texture.class);
        load("AbilityIcons/Effects/bash.png", Texture.class);
        load("AbilityIcons/Effects/heal.png", Texture.class);











        load("Mobs/mob.png", Texture.class);
        load("Mobs/mob2.png", Texture.class);
        load("Mobs/mob3.png", Texture.class);
        load("Mobs/mob4.png", Texture.class);
        load("Mobs/mob5.png", Texture.class);
        load("Mobs/mob6walk.png", Texture.class);
        load("Mobs/mob6swim.png", Texture.class);

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
        unload("Path/Bridge/bridgeLUD1.png");
        unload("Path/Bridge/bridgeLUD2.png");
        unload("Path/Bridge/bridgeRUD1.png");
        unload("Path/Bridge/bridgeRUD2.png");
        unload("Path/Bridge/bridgeULR1.png");
        unload("Path/Bridge/bridgeULR2.png");
        unload("Path/Bridge/bridgeDLR1.png");
        unload("Path/Bridge/bridgeDLR2.png");

        unload("Path/Bridge/bridgeUUL1.png");
        unload("Path/Bridge/bridgeUUL2.png");
        unload("Path/Bridge/bridgeUUR1.png");
        unload("Path/Bridge/bridgeUUR2.png");
        unload("Path/Bridge/bridgeDDL1.png");
        unload("Path/Bridge/bridgeDDL2.png");
        unload("Path/Bridge/bridgeDDR1.png");
        unload("Path/Bridge/bridgeDDR2.png");

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


        unload("Mobs/mob.png");
        unload("Mobs/mob2.png");
        unload("Mobs/mob3.png");
        unload("Mobs/mob4.png");
        unload("Mobs/mob5.png");
        unload("Mobs/mob6walk.png");
        unload("Mobs/mob6swim.png");

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
        t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return t;
    }



    public ImageButton.ImageButtonStyle generateImageButtonSkin(Texture t){
        ImageButton.ImageButtonStyle s = new ImageButton.ImageButtonStyle();
        s.up = new TextureRegionDrawable(new TextureRegion(t));
        s.over = new TextureRegionDrawable(new TextureRegion(t));
        s.down = new TextureRegionDrawable(new TextureRegion(t));

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
        return FontLoader.generateStyle(20, Color.BLACK);
    }
    public Label.LabelStyle getCurrentInfoPanelSkin(){
//        Label.LabelStyle l = new Label.LabelStyle();
//        l.font = FontLoader.impact24;
//        l.fontColor = Color.BLACK;
        return FontLoader.generateStyle(24, Color.BLACK);
    }
    public static Label.LabelStyle getLevelEndResultSkin(){
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
