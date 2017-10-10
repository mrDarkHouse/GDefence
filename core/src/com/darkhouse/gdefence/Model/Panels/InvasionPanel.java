package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Mob.Mob;

import java.util.ArrayList;

public class InvasionPanel extends AbstractPanel{
    private int mobLimit;
    private Array<Mob> mobs;

    private Label info;
    private Label num;

    public InvasionPanel(int mobLimit, Array<Mob> mobs) {
        this.mobLimit = mobLimit;
        this.mobs = mobs;
        init();
    }

    public void init(){
        AssetLoader l = GDefence.getInstance().assetLoader;
        pad(10);
        setBackground(l.getSkin().getDrawable("info-panel"));
        info = new Label(l.getWord("invasionInfo"), FontLoader.generateStyle(28, Color.BLACK));
        num = new Label(mobs.size + "/" + mobLimit, FontLoader.generateStyle(24, Color.BLACK));
        add(info).row();
        add(num);
        pack();
    }
    public void update(){
        num.setText(mobs.size + "/" + mobLimit);
    }

//    @Override
//    public void act(float delta) {//update when mob die
////        super.act(delta);
////        num.setText(mobs.size() + "/" + mobLimit);
//    }
}
