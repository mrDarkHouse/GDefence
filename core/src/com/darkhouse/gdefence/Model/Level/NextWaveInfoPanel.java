package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.InventorySystem.inventory.Tooltip.AbilityTooltip;
import com.darkhouse.gdefence.InventorySystem.inventory.TooltipListener;
import com.darkhouse.gdefence.Level.Ability.Mob.MobAbility;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Mob.Mob;
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
    private Label abilities;





    public NextWaveInfoPanel() {
        /*init();*/
    }

    public void init(){
        nextWaveTimer = new NextWaveTimer();
        setBackground(new TextureRegionDrawable(new TextureRegion(GDefence.getInstance().assetLoader.get("infoPanelFone.png", Texture.class))));
        //setFillParent(false);
        abilities = new Label(abilitiesS, FontLoader.generateStyle(12, null));
        abilities.getStyle().font.getData().markupEnabled = true;
        initString(Level.getMap().getSpawner().size());
        abilities.setText(abilitiesS);

        currentWave = new Label(currentWaveS, GDefence.getInstance().assetLoader.getInfoPanelSkin());
        mobsNumber = new Label(mobsNumberS, GDefence.getInstance().assetLoader.getInfoPanelSkin());
        mobName = new Label(mobNameS, GDefence.getInstance().assetLoader.getInfoPanelSkin());
        mobName = new Label(mobNameS, FontLoader.generateStyle(16, null));//without color
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
        add(abilities).align(Align.left).padBottom(10).row();

        add(nextWaveTimer).align(Align.left).padLeft(35).padBottom(10);//need rework
        pack();

        //setBackground(new TextureRegionDrawable(new TextureRegion(AssetLoader.infoPanelFone)));
    }
    private void initString(int spawners){
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
        abilitiesS = "[#000000ff]Abilities: ";

        int ground = 0;
        int water = 0;

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
                if(m.getAbilities().size == 0)abilitiesS += "no abi";
                else for (MobAbility ab:m.getAbilities()){
                    if(!ab.isHidden()) {
                        abilitiesS += ab.getName();
                        abilities.addListener(new TooltipListener(new AbilityTooltip(this, ab, GDefence.getInstance().assetLoader.get("skins/uiskin.json", Skin.class)), true));
                    }
                }
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
                abilitiesS += " + ";
            }
        }
    }
    public void hasChanged(){
        initString(Level.getMap().getSpawner().size());
        currentWave.setText(currentWaveS);
        mobsNumber.setText(mobsNumberS);
        mobName.setText(mobNameS);
        mobHealth.setText(mobHealthS);
        mobArmor.setText(mobArmorS);
        mobSpeed.setText(mobSpeedS);
        mobDmg.setText(mobDmgS);
        mobBounty.setText(mobBountyS);
        abilities.setText(abilitiesS);

//        if(nextWaveTimer.getTime() > 0){
//            setVisible(true);
//        }else setVisible(false);
    }


//    public void draw(Batch batch, float parentAlpha) {
//        if(nextWaveTimer.getTime() > 0) {
//            super.draw(batch, parentAlpha);
//        }
//    }



    @Override
    public void act(float delta) {
        super.act(delta);
//        if(LevelMap.getLevel().getCurrentWave() != null) {//hotfix
//            hasChanged();
//        }
    }
}
