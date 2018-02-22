package com.darkhouse.gdefence.Model.Level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.Level.Mob.Mob;


public class BossHealth extends HealthBar{
    private Mob boss;

    public BossHealth(int width, int height, int x, int y, Mob boss) {
        super(width, height, x, y);
        this.boss = boss;
    }
    protected void initBar(){
//        System.out.println(boss);
        healthBar = new ProgressBar(0, boss.getMaxHealth(), 0.5f, false,/*GDefence.getInstance().assetLoader.getExpBarSkin()*/GDefence.getInstance().assetLoader.getSkin(), "health-bar");
    }

    protected void initLabel(){
        text = new Label(boss.getHealth() + "/" + boss.getMaxHealth(),
                FontLoader.generateStyle(0, 30, Color.BLACK));
        text.setPosition(healthBar.getX() + healthBar.getWidth()/2 - text.getWidth()/2,
                healthBar.getY() + healthBar.getHeight()/2 - text.getHeight()/2);
        addActor(text);
    }
    public void update(){
        healthBar.setValue(boss.getHealth());
        text.setText(boss.getHealth() + "/" + boss.getMaxHealth());
    }

}
