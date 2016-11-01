package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.Helpers.AssetLoader;
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
    private String mobSpeedS;
    private String mobDmgS;
    private String mobBountyS;

    private Label currentWave;
    private Label mobsNumber;
    private Label mobName;
    private Label mobHealth;
    private Label mobSpeed;
    private Label mobDmg;
    private Label mobBounty;





    public NextWaveInfoPanel() {
        init();
    }

    private void init(){
        nextWaveTimer = new NextWaveTimer();
        setBackground(new TextureRegionDrawable(new TextureRegion(AssetLoader.infoPanelFone)));
        //setFillParent(false);
        initString(Level.getMap().getSpawner().size());

        currentWave = new Label(currentWaveS, AssetLoader.getInfoPanelSkin());
        mobsNumber = new Label(mobsNumberS, AssetLoader.getInfoPanelSkin());
        mobName = new Label(mobNameS, AssetLoader.getInfoPanelSkin());
        mobHealth = new Label(mobHealthS, AssetLoader.getInfoPanelSkin());
        mobSpeed = new Label(mobSpeedS, AssetLoader.getInfoPanelSkin());
        mobDmg = new Label(mobDmgS, AssetLoader.getInfoPanelSkin());
        mobBounty = new Label(mobBountyS, AssetLoader.getInfoPanelSkin());




        add(currentWave).align(Align.left).row();
        add(mobsNumber).align(Align.left).row();
        add(mobName).align(Align.left).row();
        add(mobHealth).align(Align.left).row();
        add(mobSpeed).align(Align.left).row();
        add(mobDmg).align(Align.left).row();
        add(mobBounty).align(Align.left).padBottom(10).row();

        add(nextWaveTimer).align(Align.left).padLeft(35);//need rework
        pack();

        //setBackground(new TextureRegionDrawable(new TextureRegion(AssetLoader.infoPanelFone)));
    }
    private void initString(int spawners){
        Level lvl = LevelMap.getLevel();
        switch (spawners) {//it bad, but i dont know another way
            case 1:
                currentWaveS = "Prepare Wave: " + (lvl.currentWave + 1);
                mobsNumberS = "Mobs number: " + lvl.getCurrentWave().getNumberMobs();
                mobNameS = "Name: " + Mob.getMobById(lvl.getCurrentWave().getMobID()).getName();
                mobHealthS = "Health: " + Mob.getMobById(lvl.getCurrentWave().getMobID()).getHealth();
                mobSpeedS = "Speed: " + Mob.getMobById(lvl.getCurrentWave().getMobID()).getSpeed();
                mobDmgS = "Dmg: " + Mob.getMobById(lvl.getCurrentWave().getMobID()).getDmg();
                mobBountyS = "Bounty: " + Mob.getMobById(lvl.getCurrentWave().getMobID()).getBounty();
                break;
            case 2:
                currentWaveS = "Prepare Wave: " + (lvl.currentWave + 1) + " + " + (lvl.currentWave + 2);
                mobsNumberS = "Mobs number: " + lvl.getCurrentWave().getNumberMobs() + " + " + lvl.getWave(lvl.currentWave + 2).getNumberMobs();
                if(lvl.getCurrentWave().getMobID() == lvl.getWave(lvl.currentWave + 2).getMobID()) {
                    mobNameS = "Name: " + Mob.getMobById(lvl.getCurrentWave().getMobID()).getName();
                    mobHealthS = "Health: " + Mob.getMobById(lvl.getCurrentWave().getMobID()).getHealth();
                    mobSpeedS = "Speed: " + Mob.getMobById(lvl.getCurrentWave().getMobID()).getSpeed();
                    mobDmgS = "Dmg: " + Mob.getMobById(lvl.getCurrentWave().getMobID()).getDmg();
                    mobBountyS = "Bounty: " + Mob.getMobById(lvl.getCurrentWave().getMobID()).getBounty();
                }
                break;
        }
    }
    private void hasChanged(){
        initString(Level.getMap().getSpawner().size());
        currentWave.setText(currentWaveS);
        mobsNumber.setText(mobsNumberS);
        mobName.setText(mobNameS);
        mobHealth.setText(mobHealthS);
        mobSpeed.setText(mobSpeedS);
        mobDmg.setText(mobDmgS);
        mobBounty.setText(mobBountyS);
    }


    public void draw(Batch batch, float parentAlpha) {
        if(nextWaveTimer.getTime() > 0) {
            super.draw(batch, parentAlpha);
        }
    }



    @Override
    public void act(float delta) {
        super.act(delta);
        if(LevelMap.getLevel().getCurrentWave() != null) {//hotfix
            hasChanged();
        }
    }
}
