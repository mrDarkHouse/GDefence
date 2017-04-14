package com.darkhouse.gdefence.Level.Ability.Mob.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.Helpers.FontLoader;

public class EffectIcon extends Actor{

    private Effect effect;
    private Color color;
    private Texture icon;
    public Texture getIcon() {
        return icon;
    }
    ShapeRenderer sp;
    private int borderSize = 1;

    private Label other;

    public EffectIcon(Effect effect) {
        this.effect = effect;
        icon = effect.getIcon();
        if(effect.isBuff())color = Color.GREEN;
        else color = Color.FIREBRICK;
        sp = new ShapeRenderer();

        if(effect.isCooldownable()){
            other = new Label(((int) effect.getCooldownObject().getCooldown()) + "", FontLoader.generateStyle(14, Color.BLACK));
        }
        if(effect.isStackable()){
            other = new Label(effect.getStackableObject().getStacks() + "", FontLoader.generateStyle(15, Color.BLACK));
        }
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

        if(effect.isCooldownable()){//if cooldownable and stackable in one time ability must declare own EffectIcon
            other.setAlignment(Align.center);
            int cd = (int)  Math.ceil(effect.getCooldownObject().getCooldown());
            if(cd == 0)other.setText("");
            else other.setText(cd + "");
            other.setBounds(getX() + borderSize, getY() + borderSize, getWidth() - borderSize*2, getHeight() - borderSize*2);
            other.draw(batch, parentAlpha);
        }
        if(effect.isStackable()){
            other.setAlignment(Align.center);
            int stacks = effect.getStackableObject().getStacks();
            if(stacks == 0)other.setText("");
            else other.setText(stacks + "");
            other.setBounds(getX() + borderSize, getY() + borderSize, getWidth() - borderSize*2, getHeight() - borderSize*2);
            other.draw(batch, parentAlpha);
        }
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
