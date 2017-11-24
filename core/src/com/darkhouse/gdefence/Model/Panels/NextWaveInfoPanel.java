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
import com.darkhouse.gdefence.Helpers.AssetLoader;
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
        AssetLoader l = GDefence.getInstance().assetLoader;
        nextWaveTimer = new NextWaveTimer();
        setBackground(l.getSkin().getDrawable("info-panel"));
        pad(10);
        //setFillParent(false);
        abilities = new Array<Label>();
        abilities.add(new Label(abilitiesS, FontLoader.generateStyle(16, null)));
        abilities.get(0).getStyle().font.getData().markupEnabled = true;
        Array<Label> abilitiesLabels = initString(Level.getMap().getSpawner().size());
        for (Label label:abilitiesLabels){
            abilities.add(label);
        }
        abilities.get(0).setText(abilitiesS);

        currentWave = new Label(currentWaveS, l.getInfoPanelSkin());
        mobsNumber = new Label(mobsNumberS, l.getInfoPanelSkin());
//        mobName = new Label(mobNameS, l.getInfoPanelSkin());
        mobName = new Label(mobNameS, FontLoader.generateStyle(16, null));//without color
        mobName.getStyle().font.getData().markupEnabled = true;
        mobHealth = new Label(mobHealthS, l.getInfoPanelSkin());
        mobArmor = new Label(mobArmorS, l.getInfoPanelSkin());
        mobSpeed = new Label(mobSpeedS, l.getInfoPanelSkin());
        mobDmg = new Label(mobDmgS, l.getInfoPanelSkin());
        mobBounty = new Label(mobBountyS, l.getInfoPanelSkin());





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
        Level lvl = LevelMap.getLevel();//TODO
        AssetLoader l = GDefence.getInstance().assetLoader;
//        Mob m = Mob.getMobById(lvl.getCurrentWave().getMobID());
        currentWaveS = l.getWord("prepareWave") + ": ";//"Prepare Wave: ";
        mobsNumberS = l.getWord("mobsNumber") + ": ";//"Mobs number: ";
        mobHealthS = l.getWord("mobHealth") + ": ";//"Health: " ;//+ m.getHealth();
        mobArmorS = l.getWord("mobArmor") + ": ";//"Armor: " ;//+ m.getArmor();
        mobSpeedS = l.getWord("mobSpeed") + ": ";//"Speed: ";// + m.getSpeed();
        mobDmgS = l.getWord("mobDmg") + ": ";//"Dmg: " ;//+ m.getDmg();
        mobBountyS = l.getWord("mobBounty") + ": ";//"Bounty: " ;//+ m.getBounty();
        mobNameS = "[#000000ff]" + l.getWord("mobName") + ": ";//Name: ";
        abilitiesS = "[#000000ff]" + l.getWord("mobAbilities") + ": ";// + System.getProperty("line.separator");//Abilities: ";// + System.getProperty("line.separator");

        int ground = 0;
        int water = 0;
        Array<Label> tmp = new Array<Label>();

        for (int i = 0; i < spawners; i++) {
            currentWaveS += lvl.currentWave + 1 + i;
            mobsNumberS += lvl.getWave(lvl.currentWave + 1 + i).getNumberMobs();


            Mob.Prototype m = /*i == 0 ? null :*/ Mob.getMobById(lvl.getWave(lvl.currentWave + 1 + i).getMobID());//
//            System.out.println(m.getName());

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


//            System.out.println(lvl.currentWave + 2 + i);
//            System.out.println(lvl.getWave(lvl.currentWave + 2 + i).getMobID());

            if(/*i + 1 != spawners &&*/ i == 0 || !Mob.getMobById(lvl.getWave(lvl.currentWave + /*2 +*/ i).getMobID()).getName().equals(m.getName())) {
//                System.out.println(Mob.getMobById(lvl.getWave(lvl.currentWave + 2 + i).getMobID()).getName() + " " + m.getName());
                if(i != 0/*i + 1 != spawners*/){
                    mobHealthS += " + ";
                    mobArmorS += " + ";
                    mobSpeedS += " + ";
                    mobDmgS += " + ";
                    mobBountyS += " + ";
                }

                mobHealthS += m.getHealth();
                mobArmorS += m.getArmor();
                mobSpeedS += m.getSpeed();
                mobDmgS += m.getDmg();
                mobBountyS += m.getBounty();

                if(i != 0) {
                    Label space = new Label("       +", GDefence.getInstance().assetLoader.getInfoPanelSkin());
                    space.setAlignment(Align.center);
                    tmp.add(space);
                    //                abilitiesS += " + ";
                }

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
                if(empty)tmp.add(new Label(l.getWord("none"), GDefence.getInstance().assetLoader.getInfoPanelSkin()));
                    //abilitiesS += "none";

            }

            if(i + 1 != spawners) {
                mobNameS += " + ";
                currentWaveS += " + ";
                mobsNumberS += " + ";
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

        pack();//fix small window for big table
//        abilities.setText(abilitiesS);

//        if(nextWaveTimer.getTimeLimit() > 0){
//            setVisible(true);
//        }else setVisible(false);
    }


//    public void draw(Batch batch, float parentAlpha) {
//        if(nextWaveTimer.getTimeLimit() > 0) {
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
