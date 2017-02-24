package com.darkhouse.gdefence.InventorySystem.inventory.Tooltip;


import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Loader.MapLoader;
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
        GDefence.getInstance().getCampainMap().getStage().addActor(this);//do stage.addActor(this)
        //levelButton.getStage().addActor(this);
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
        ml.loadProperties(ml.getSpawnersNumber(), false);
        String waves = "Waves: " + ml.getNumberWaves();
        String exp = "Exp: " + ml.getExpFromLvl();
        String gold = "Gold: " + ml.getGoldFromLvl();
        String hp = "Start health: " + (int)(ml.getStartHpPercent() * 100) + "%";
        String en = "Start energy: " + (int)(ml.getStartEnergyPercent() * 100) + "%";




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
