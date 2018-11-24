package com.darkhouse.gdefence.Model.Panels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Level;


public class TimeChangePanel extends AbstractPanel{
    private float currMode;
    private Label timeLabel;
    private String timeString;

    public TimeChangePanel() {
//        init();
    }

    public void init(){
        AssetLoader l = GDefence.getInstance().assetLoader;
//        currMode = Level.getTimeMultiplayer();
        timeString = l.getWord("speed") + " ";
        setBackground(l.getSkin().getDrawable("info-panel"));
        timeLabel = new Label(/*timeString + (int)currMode + "x"*/"",
                FontLoader.generateStyle(0, 20, Color.BLACK));
        hasChanged();

        pad(15f);
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                super.clicked(event, x, y);
                changeSpeed();
                hasChanged();
            }
        });
        add(timeLabel);
        pack();
    }
    private void changeSpeed(){
        if(Level.getTimeMultiplayer() == 3f){
            Level.setTimeMultiplayer(1f);
        }else Level.setTimeMultiplayer(Level.getTimeMultiplayer() + 1);
    }

    public void hasChanged(){
        currMode = Level.getTimeMultiplayer();
        timeLabel.setText(timeString + (int) currMode + "x");
    }
}
