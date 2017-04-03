package com.darkhouse.gdefence.Level.Ability.Mob.Effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.darkhouse.gdefence.Model.GDSprite;

public class EffectIcon extends Actor{

    private Effect effect;
    private Color color;
    private Texture icon;
    public Texture getIcon() {
        return icon;
    }
    ShapeRenderer sp;
    private int borderSize = 1;

    public EffectIcon(Effect effect) {
        this.effect = effect;
        icon = effect.getIcon();
        if(effect.isBuff())color = Color.GREEN;
        else color = Color.FIREBRICK;
        sp = new ShapeRenderer();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        sp.setAutoShapeType(true);
        sp.begin();
        sp.set(ShapeRenderer.ShapeType.Line);
        sp.setColor(color);
        Gdx.gl.glLineWidth(borderSize + 1);
        sp.rect(getX(), getY(), getWidth(), getHeight());
        sp.end();
        batch.begin();
        batch.draw(icon, getX() + borderSize, getY() + borderSize, getWidth() - borderSize*2, getHeight() - borderSize*2);
    }

//    @Override
//    public void draw(Batch batch) {
//        batch.end();
//        sp.begin();
//        sp.setColor(color);
//        sp.rectLine(getX(), getY(), getX() + getWidth(), getY() + getHeight(), borderSize);
//        sp.end();
//        batch.begin();
//        batch.draw(icon, getX() + borderSize, getY() + borderSize, getWidth() - borderSize*2, getHeight() - borderSize*2);
//    }
}
