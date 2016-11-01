package com.darkhouse.gdefence.InventorySystem.inventory;


import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Loader.MapLoader;
import com.darkhouse.gdefence.Level.Loader.PropertiesLoader;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.Model.LevelButton;

public class LevelToolip extends Window{
    private LevelButton levelButton;
    private Skin skin;

    public LevelToolip(LevelButton levelButton, Skin skin) {
        super("Tooltip...", skin);
        this.levelButton = levelButton;
        this.skin = skin;
        hasChanged();
        setVisible(false);
    }

    public void hasChanged() {
        if (levelButton.isLocked) {
            setVisible(false);
            return;
        }
        getTitleLabel().setText("Level " + levelButton.getNumber());
        getTitleLabel().setAlignment(Align.center);
        clear();
        MapLoader ml = new MapLoader(levelButton.getNumber());
        ml.loadMap();
        PropertiesLoader pl = new PropertiesLoader(levelButton.getNumber());
        pl.loadProperties(ml.getSpawnersNumber(), false);
        String waves = "Waves: " + pl.getNumberWaves();
        String exp = "Exp: " + pl.getExpFromLvl();
        String gold = "Gold: " + pl.getGoldFromLvl();
        String hp = "Start health: " + (int)(pl.getStartHpPercent() * 100) + "%";
        String en = "Start energy: " + pl.getStartEnergy();




        Label label = new Label(waves + System.getProperty("line.separator") +
                                exp + System.getProperty("line.separator") +
                                gold + System.getProperty("line.separator") +
                                hp + System.getProperty("line.separator") +
                                en, skin);
        add(label);
        pack();
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (levelButton.isLocked) {
            super.setVisible(false);
        }
    }






}
