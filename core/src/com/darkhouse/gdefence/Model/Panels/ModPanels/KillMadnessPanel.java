package com.darkhouse.gdefence.Model.Panels.ModPanels;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Level;
import com.darkhouse.gdefence.Model.Panels.AbstractPanel;

import java.text.DecimalFormat;

public class KillMadnessPanel extends AbstractPanel{
    private float timeLimit;
//    private float timeCounter;
    private boolean start;
    public void start() {
        this.start = true;
    }

    private Level owner;
    private Label info;
    private Label time;

    public KillMadnessPanel(float timeLimit, Level owner) {
        this.timeLimit = timeLimit;
        this.owner = owner;
        init();
    }

    public void init(){
        AssetLoader l = GDefence.getInstance().assetLoader;
        pad(10);
        setBackground(l.getSkin().getDrawable("info-panel"));
        info = new Label(l.getWord("killMadnessInfo"), FontLoader.generateStyle(0, 28, Color.BLACK));
        time = new Label("0.00" + "/" + timeLimit, FontLoader.generateStyle(0, 24, Color.BLACK));
        add(info).row();
        add(time);
        pack();
    }
    @Override
    public void act(float delta) {
        super.act(delta);
//        if(start) {
            time.setText(new DecimalFormat("0.00").format(timeLimit - owner.getTimeWithoutKills()));
//        time.setText(new DecimalFormat("0.00").format(timeLimit - owner.getRoundTime()));
//        }
    }


}
