package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.AbilityTooltip;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.TooltipListener;
import com.darkhouse.gdefence.Level.Ability.Mob.MobAbility;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Model.Level.NextWaveTimer;
import com.darkhouse.gdefence.Screens.LevelMap;

public class NextWaveInfoPanel extends Table{
    private NextWaveTimer nextWaveTimer;

    public NextWaveTimer getNextWaveTimer() {
        return nextWaveTimer;
    }




    private String currentWaveS;
    private String mobsNumberS;
    private String mobNameS;
    private String mobHealthS;
    private String mobArmorS;
    private String mobSpeedS;
    private String mobDmgS;
    private String mobBountyS;
    private String abilitiesS;

    private Label currentWave;
    private Label mobsNumber;
    private Label mobName;
    private Label mobHealth;
    private Label mobArmor;
    private Label mobSpeed;
    private Label mobDmg;
    private Label mobBounty;
    private Array<Label> abilities;





    public NextWaveInfoPanel() {
        /*init();*/
    }

    public void init(){
        nextWaveTimer = new NextWaveTimer();
        setBackground(GDefence.getInstance().assetLoader.getSkin().getDrawable("info-panel"));
        pad(10);
        //setFillParent(false);
        abilities = new Array<Label>();
        abilities.add(new Label(abilitiesS, FontLoader.generateStyle(20, null)));
        abilities.get(0).getStyle().font.getData().markupEnabled = true;
        Array<Label> abilitiesLabels = initString(Level.getMap().getSpawner().size());
        for (Label l:abilitiesLabels){
            abilities.add(l);
        }
        abilities.get(0).setText(abilitiesS);

        currentWave = new Label(currentWaveS, GDefence.getInstance().assetLoader.getInfoPanelSkin());
        mobsNumber = new Label(mobsNumberS, GDefence.getInstance().assetLoader.getInfoPanelSkin());
        mobName = new Label(mobNameS, GDefence.getInstance().assetLoader.getInfoPanelSkin());
        mobName = new Label(mobNameS, FontLoader.generateStyle(20, null));//without color
        mobName.getStyle().font.getData().markupEnabled = true;
        mobHealth = new Label(mobHealthS, GDefence.getInstance().assetLoader.getInfoPanelSkin());
        mobArmor = new Label(mobArmorS, GDefence.getInstance().assetLoader.getInfoPanelSkin());
        mobSpeed = new Label(mobSpeedS, GDefence.getInstance().assetLoader.getInfoPanelSkin());
        mobDmg = new Label(mobDmgS, GDefence.getInstance().assetLoader.getInfoPanelSkin());
        mobBounty = new Label(mobBountyS, GDefence.getInstance().assetLoader.getInfoPanelSkin());





        add(currentWave).align(Align.left).padTop(10).row();
        add(mobsNumber).align(Align.left).row();
        add(mobName).align(Align.left).row();
        add(mobHealth).align(Align.left).row();
        add(mobArmor).align(Align.left).row();
        add(mobSpeed).align(Align.left).row();
        add(mobDmg).align(Align.left).row();
        add(mobBounty).align(Align.left).row();



        //for (Label l:abilities){
        for (int i = 0; i < abilities.size; i++){
            add(abilities.get(i)).align(Align.left).row();
//            if(i!= abilities.size - 1)row();
        }

//        add(abilities.get(0)).align(Align.left).row();//.padBottom(10).row();



        add(nextWaveTimer).align(Align.left).padLeft(35);//.padBottom(10);//need rework
        padBottom(10);
        pack();

        //setBackground(new TextureRegionDrawable(new TextureRegion(AssetLoader.infoPanelFone)));
    }
    private Array<Label> initString(int spawners){
        Level lvl = LevelMap.getLevel();//
//        Mob m = Mob.getMobById(lvl.getCurrentWave().getMobID());
        currentWaveS = "Prepare Wave: ";
        mobsNumberS = "Mobs number: ";
        mobHealthS = "Health: " ;//+ m.getHealth();
        mobArmorS = "Armor: " ;//+ m.getArmor();
        mobSpeedS = "Speed: ";// + m.getSpeed();
        mobDmgS = "Dmg: " ;//+ m.getDmg();
        mobBountyS = "Bounty: " ;//+ m.getBounty();
        mobNameS = "[#000000ff]Name: ";
        abilitiesS = "[#000000ff]Abilities: ";// + System.getProperty("line.separator");

        int ground = 0;
        int water = 0;
        Array<Label> tmp = new Array<Label>();

        for (int i = 0; i < spawners; i++) {
            currentWaveS += lvl.currentWave + 1 + i;
            mobsNumberS += lvl.getWave(lvl.currentWave + 1 + i).getNumberMobs();

            Mob.Prototype m = Mob.getMobById(lvl.getWave(lvl.currentWave + 1 + i).getMobID());

            if(m.getMoveType() == Mob.MoveType.ground){
                if(ground == 0) mobNameS += FontLoader.getOneColorButtonString(0, m.getName(), Color.FIREBRICK, Color.BLACK);
                if(ground == 1) mobNameS += FontLoader.getOneColorButtonString(0, m.getName(), Color.FOREST, Color.BLACK);
                if(ground == 2) mobNameS += FontLoader.getOneColorButtonString(0, m.getName(), Color.BROWN, Color.BLACK);
                ground++;
            }else if(m.getMoveType() == Mob.MoveType.water){
                if(water == 0) mobNameS += FontLoader.getOneColorButtonString(0, m.getName(), Color.BLUE, Color.BLACK);
                if(water == 1) mobNameS += FontLoader.getOneColorButtonString(0, m.getName(), Color.CYAN, Color.BLACK);
                if(water == 2) mobNameS += FontLoader.getOneColorButtonString(0, m.getName(), Color.CORAL, Color.BLACK);
                water++;
            }


            if(/*i + 1 != spawners &&*/ spawners == 1 || Mob.getMobById(lvl.getWave(lvl.currentWave + 2 + i).getMobID()).getName() != m.getName()) {
                mobHealthS += m.getHealth();
                mobArmorS += m.getArmor();
                mobSpeedS += m.getSpeed();
                mobDmgS += m.getDmg();
                mobBountyS += m.getBounty();

                boolean empty = true;
                for (MobAbility.AbilityPrototype ab:m.getAbilities()){
                    if(!ab.isHidden()) {
//                        if(!empty) row();
                        //abilitiesS += System.getProperty("line.separator");
                        //abilitiesS += ab.getName();

                        Label label = new Label(ab.getName(), GDefence.getInstance().assetLoader.getInfoPanelSkin());
                        label.setAlignment(Align.center);
                        AbilityTooltip aTooltip = new AbilityTooltip(this, ab, GDefence.getInstance().assetLoader.getSkin());
                        TooltipListener tListener = new TooltipListener(aTooltip, true);
                        tListener.setOffset(-aTooltip.getWidth()/2, 15/*-20, 10*/);//center
                        label.addListener(tListener);

                        tmp.add(label);
                        empty = false;
                    }
                }
                if(empty)tmp.add(new Label("none", GDefence.getInstance().assetLoader.getInfoPanelSkin()));
                    //abilitiesS += "none";
                if(i + 1 != spawners){
                    mobHealthS += " + ";
                    mobArmorS += " + ";
                    mobSpeedS += " + ";
                    mobDmgS += " + ";
                    mobBountyS += " + ";
                }
            }
            if(i + 1 != spawners) {
                mobNameS += " + ";
                currentWaveS += " + ";
                mobsNumberS += " + ";
                Label l = new Label("       +", GDefence.getInstance().assetLoader.getInfoPanelSkin());
                l.setAlignment(Align.center);
                tmp.add(l);
//                abilitiesS += " + ";
            }
        }
        return tmp;
    }
    public void hasChanged(){
//        for (Label l:abilities){
//            l.clearListeners();
//        }
//        System.out.println(getPadBottom());
//        padTop(-10);
//        System.out.println(getPadBottom());
        removeActor(nextWaveTimer);
//        System.out.println(abilities);
        for (int i = abilities.size - 1; i >= 0; i--){
            if(i!= 0) {
                removeActor(abilities.get(i));
                abilities.removeIndex(i);
            }
        }
//        System.out.println(abilities);
//        abilities.getListeners().clear();
        Array<Label> abilitiesLabels = initString(Level.getMap().getSpawner().size());
//        for (Label l:abilitiesLabels){
//            abilities.add(l);
//            add(abilities.peek()).align(Align.left).row();
//        }
        for (int i = 0; i < abilitiesLabels.size; i++){
            abilities.add(abilitiesLabels.get(i));
            add(abilitiesLabels.get(i)).align(Align.left).row();
//            if(i!= abilitiesLabels.size - 1)row();
        }

        currentWave.setText(currentWaveS);
        mobsNumber.setText(mobsNumberS);
        mobName.setText(mobNameS);
        mobHealth.setText(mobHealthS);
        mobArmor.setText(mobArmorS);
        mobSpeed.setText(mobSpeedS);
        mobDmg.setText(mobDmgS);
        mobBounty.setText(mobBountyS);
        add(nextWaveTimer).align(Align.left).padLeft(35);//.padBottom(10);
//        abilities.setText(abilitiesS);

//        if(nextWaveTimer.getTime() > 0){
//            setVisible(true);
//        }else setVisible(false);
    }


//    public void draw(Batch batch, float parentAlpha) {
//        if(nextWaveTimer.getTime() > 0) {
//            super.draw(batch, parentAlpha);
//        }
//    }



//    @Override
//    public void act(float delta) {
//        super.act(delta);
////        if(LevelMap.getLevel().getCurrentWave() != null) {//hotfix
////            hasChanged();
////        }
//    }
}
