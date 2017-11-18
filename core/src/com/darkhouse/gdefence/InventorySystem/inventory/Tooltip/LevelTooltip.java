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
import com.darkhouse.gdefence.User;

import java.util.Random;

public class LevelTooltip extends AbstractTooltip{
    private LevelButton levelButton;
    private Skin skin;

    private Label label;

    private String waves;
    private String exp;
    private int expValue;
    private String gold;
    private int goldValue;
    private String hp;
    private String en;
    private String drop1;
    private String drop2;

    public LevelTooltip(LevelButton levelButton, Skin skin) {
        super("Tooltip...", skin);
        this.levelButton = levelButton;
        this.skin = skin;
        init();
        setVisible(false);
        GDefence.getInstance().getCampainMap().getStage().addActor(this);//do stage.addActor(this)
        //levelButton.getStage().addActor(this);
    }
    private void init(){
        I18NBundle b = GDefence.getInstance().assetLoader.get("Language/text", I18NBundle.class);
        getTitleLabel().setText(b.get("level") + " " + levelButton.getNumber());
        getTitleLabel().setAlignment(Align.center);
        clear();
        MapLoader ml = new MapLoader(levelButton.getNumber());
        ml.loadMap();
        ml.loadProperties(ml.getSpawnersNumber(), false);
        User u = GDefence.getInstance().user;

        waves = b.get("waves") + ": " + ml.getNumberWaves();
        exp = b.get("exp") + " : ";// + ml.getExpFromLvl();
        expValue = u.getLevelCompleted(levelButton.getNumber()) ?  ml.getExpFromLvl()/4: ml.getExpFromLvl();
        gold = b.get("gold") + ": ";// + ml.getGoldFromLvl();
        goldValue = u.getLevelCompleted(levelButton.getNumber()) ?  ml.getGoldFromLvl()/4: ml.getGoldFromLvl();
        hp = b.get("sHealth") + ": " + (int)(ml.getStartHpPercent() * 100) + "%";
        en = b.get("sEnergy") + ": " + (int)(ml.getStartEnergyPercent() * 100) + "%";

//        drop1 = "";
//        drop2 = "";
        drop1 = getDropTooltip(ml.getDropList());
        drop2 = getDropTooltip(ml.getPenaltyDropList());
//        drop += GDefence.getInstance().user.getLevelCompleted(levelButton.getNumber()) ? getDropTooltip(ml.getPenaltyDropList()) : getDropTooltip(ml.getDropList());



//        String drop = GDefence.getInstance().user.getLevelCompleted(levelButton.getNumber()) ? drop2 : drop1;

        label = new Label(""/*waves + System.getProperty("line.separator") +
                exp + System.getProperty("line.separator") +
                gold + System.getProperty("line.separator") +
                hp + System.getProperty("line.separator") +
                en +
                drop*/, skin, "description");
        hasChanged();
        add(label);
//        pack();
    }

    public void hasChanged() {
        User u = GDefence.getInstance().user;
        String drop = GDefence.getInstance().user.getLevelCompleted(levelButton.getNumber()) ? drop2 : drop1;
//        expValue = u.getLevelCompleted(levelButton.getNumber()) ?  ml.getExpFromLvl()/4: ml.getExpFromLvl();
//        goldValue = u.getLevelCompleted(levelButton.getNumber()) ?  ml.getGoldFromLvl()/4: ml.getGoldFromLvl();
        String expS = exp + (u.getLevelCompleted(levelButton.getNumber()) ? expValue/4: expValue);
        String goldS = gold + (u.getLevelCompleted(levelButton.getNumber()) ? goldValue/4: goldValue);
        label.setText(waves + System.getProperty("line.separator") +
                expS + System.getProperty("line.separator") +
                goldS + System.getProperty("line.separator") +
                hp + System.getProperty("line.separator") +
                en +
                drop);
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
