package com.darkhouse.gdefence.Model.Level;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Screens.LevelMap;

public class NextWaveInfoPanel extends Table{
    private NextWaveTimer nextWaveTimer;
    private Label currentWave = new Label("Prepare Wave: " + (LevelMap.getLevel().currentWave + 1) , AssetLoader.getInfoPanelSkin());
    private Label mobsNumber = new Label("Mobs number: " + LevelMap.getLevel().getCurrentWave().getNumberMobs(), AssetLoader.getInfoPanelSkin());
    private Label mobName = new Label("Name: " + Mob.getMobById(LevelMap.getLevel().getCurrentWave().getMobID()).getName(), AssetLoader.getInfoPanelSkin());
    private Label mobHealth = new Label("Health: " + Mob.getMobById(LevelMap.getLevel().getCurrentWave().getMobID()).getHealth(), AssetLoader.getInfoPanelSkin());
    private Label mobSpeed = new Label("Speed: " + Mob.getMobById(LevelMap.getLevel().getCurrentWave().getMobID()).getSpeed(), AssetLoader.getInfoPanelSkin());
    private Label mobDmg = new Label("Dmg: " + Mob.getMobById(LevelMap.getLevel().getCurrentWave().getMobID()).getDmg(), AssetLoader.getInfoPanelSkin());
    private Label mobBounty = new Label("Bounty: " + Mob.getMobById(LevelMap.getLevel().getCurrentWave().getMobID()).getBounty(), AssetLoader.getInfoPanelSkin());


    public NextWaveInfoPanel() {
        init();
    }

    private void init(){
        nextWaveTimer = new NextWaveTimer();


        add(currentWave).row();
        add(mobsNumber).row();
        add(mobName).row();
        add(mobHealth).row();
        add(mobSpeed).row();
        add(mobDmg).row();
        add(mobBounty).row();

        add(nextWaveTimer);
        setBackground(new TextureRegionDrawable(new TextureRegion(AssetLoader.infoPanelFone)));
    }

    public void draw(Batch batch, float parentAlpha) {
        if(nextWaveTimer.getTime() > 0) {
            super.draw(batch, parentAlpha);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        currentWave.setText("Prepare Wave: " + (LevelMap.getLevel().currentWave + 1));
        mobsNumber.setText("Mobs number: " + LevelMap.getLevel().getCurrentWave().getNumberMobs());
        mobName.setText("Name: " + Mob.getMobById(LevelMap.getLevel().getCurrentWave().getMobID()).getName());
        mobHealth.setText("Health: " + Mob.getMobById(LevelMap.getLevel().getCurrentWave().getMobID()).getHealth());
        mobSpeed.setText("Speed: " + Mob.getMobById(LevelMap.getLevel().getCurrentWave().getMobID()).getSpeed());
        mobDmg.setText("Dmg: " + Mob.getMobById(LevelMap.getLevel().getCurrentWave().getMobID()).getDmg());
        mobBounty.setText("Bounty: " + Mob.getMobById(LevelMap.getLevel().getCurrentWave().getMobID()).getBounty());

    }
}
