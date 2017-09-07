package com.darkhouse.gdefence.InventorySystem.inventory.Tooltip;


import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Level.Ability.Tower.Ability;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Objects.TowerObject;
import com.darkhouse.gdefence.Screens.LevelMap;

public class TowerTooltip extends AbstractTooltip{

    private Tower tower;
    private Skin skin;

    private Label info;
    private Label level;
    private ProgressBar expBar;

    public TowerTooltip(Tower tower, Skin skin) {
        super("Tower...", skin);
        this.tower = tower;
        this.skin = skin;

        init();
    }

    public void init(){
        getTitleLabel().setText(tower.getTowerPrototype().getName());

        info = new Label("", skin, "description");
        level = new Label("", skin, "description");


        final float width = getWidth();
        expBar = new ProgressBar(0, GDefence.getInstance().user.getMaxExpThisLvl(), 0.2f, false,
                GDefence.getInstance().assetLoader.getExpBarSkin()) {
            @Override
            public float getPrefWidth() {
                return width - 10;
            }
        };//add text inside
        expBar.getStyle().background.setMinHeight(20);
        expBar.getStyle().knob.setMinHeight(20);
//        expBar.getStyle().background.setMinWidth(width - 10);
        expBar.getStyle().knob.setMinWidth(0.1f);
//                expBar.setSize(80, 20);//dont work first argument
        expBar.setValue(tower.getTowerPrototype().getCurrentExp());


        add(info).row();
        add(level).align(Align.center).row();
        add(expBar).align(Align.center);


        hasChanged();

        pack();

        tower.getStage().addActor(this);

        setVisible(false);
    }

    @Override
    public void hasChanged() {
        String s = "";
//        s += tower.getName() + "/n";
        AssetLoader l = GDefence.getInstance().assetLoader;
        s += l.getWord("dmg") + ": " + tower.getDmg() + System.getProperty("line.separator");
        s += l.getWord("range") + ": " + tower.getTowerPrototype().getRange() + System.getProperty("line.separator");
        s += l.getWord("speed") + ": " + tower.getSpeed() + "(" + Tower.getAttackSpeedDelay(tower.getSpeed()) + ")" + System.getProperty("line.separator");
        for (int i = 0; i < tower.getTowerPrototype().getAbilities().size; i++){
            s += System.getProperty("line.separator");
            Ability.AbilityPrototype a = tower.getTowerPrototype().getAbilities().get(i);
            s += a.getName() + " ";
            s += a.getGemStat();
        }
//        s += System.getProperty("line.separator");


        info.setText(s);
        level.setText(String.valueOf(tower.getTowerPrototype().getLevel()));
        expBar.setValue(tower.getTowerPrototype().getCurrentExp());
    }

}
