package com.darkhouse.gdefence.InventorySystem.inventory.Tooltip;


import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Level.Loader.MapLoader;
import com.darkhouse.gdefence.Model.Level.Map;
import com.darkhouse.gdefence.Model.LevelButton;

import java.util.Random;

public class LevelToolip extends AbstractTooltip{
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
//        if (levelButton.isLocked()) {
//            setVisible(false);
//            return;
//        }
        I18NBundle b = GDefence.getInstance().assetLoader.get("Language/text", I18NBundle.class);
        getTitleLabel().setText(b.get("level") + " " + levelButton.getNumber());
        getTitleLabel().setAlignment(Align.center);
        clear();
        MapLoader ml = new MapLoader(levelButton.getNumber());
        ml.loadMap();
        ml.loadProperties(ml.getSpawnersNumber(), false);
        String waves = b.get("waves") + ": " + ml.getNumberWaves();
        String exp = b.get("exp") + " : " + ml.getExpFromLvl();
        String gold = b.get("gold") + ": " + ml.getGoldFromLvl();
        String hp = b.get("sHealth") + ": " + (int)(ml.getStartHpPercent() * 100) + "%";
        String en = b.get("sEnergy") + ": " + (int)(ml.getStartEnergyPercent() * 100) + "%";

        String drop = "";
        drop += GDefence.getInstance().user.getLevelCompleted(levelButton.getNumber()) ? getDropTooltip(ml.getPenaltyDropList()) : getDropTooltip(ml.getDropList());




        Label label = new Label(waves + System.getProperty("line.separator") +
                                exp + System.getProperty("line.separator") +
                                gold + System.getProperty("line.separator") +
                                hp + System.getProperty("line.separator") +
                                en +
                                drop, skin, "description");
        add(label);
        pack();
    }

    private String getDropTooltip(String dropCode){
        String tooltip = "";
        if(dropCode == null)return tooltip;
        String[] objects = dropCode.split("/");

        for (String s:objects){
            tooltip += System.getProperty("line.separator");
            String[] curr = s.split(":");
            tooltip += ItemEnum.getItemById(Integer.parseInt(curr[0])).getName();
//            tooltip += ItemEnum.getItemNameById(Integer.parseInt(curr[0]));
            switch (curr.length) {
                case 2:
                    tooltip += "(" + curr[1] + ")";
                    break;
                case 3:
                    if(Integer.parseInt(curr[1]) != 1) tooltip += " - " + curr[1];
                    if(Float.parseFloat(curr[2]) != 1f) tooltip += "(" + (int)(Float.parseFloat(curr[2])*100) + "%)";
                    break;
//                    if(Integer.parseInt(curr[1]) == 1) tooltip += "(" + (int)(Float.parseFloat(curr[2])*100) + "%)";
//                    else tooltip += " - " + curr[1] + " (" + Float.parseFloat(curr[2])*100 + "%)";
//                    break;
            }

        }
        return tooltip;
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (levelButton.isLocked()) {
            super.setVisible(false);
        }
    }






}
