package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.darkhouse.gdefence.GDefence;

public abstract class AbstractPanel extends Table{
    protected GDefence mainClass;

    public AbstractPanel(int x, int y, int width, int height, GDefence mainClass) {
        this.mainClass = mainClass;
        setPosition(x, y);
        setSize(width, height);
    }

    public AbstractPanel(GDefence mainClass) {
        this.mainClass = mainClass;
    }
}
