package com.darkhouse.gdefence.Level.Ability.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.FontLoader;

public class EffectIcon extends Actor{

    private Effect effect;
    private Color color;
    private Texture icon;
    public Texture getIcon() {
        return icon;
    }
    ShapeRenderer sp;
    private int borderSize = 0;

    private Label other;

    public EffectIcon(Effect effect) {
        this.effect = effect;
        icon = GDefence.getInstance().assetLoader.getEffectIcon(effect.getIconPath());
        if(effect.isBuff())color = Color.FOREST;
        else               color = Color.FIREBRICK;
        sp = new ShapeRenderer();

        if(effect.isCooldownable()){
            other = new Label(((int) effect.getCooldownObject().getCooldown()) + "", FontLoader.generateStyle(0, 14, Color.WHITE, 1, Color.BLACK));
        }
        if(effect.isStackable()){
            other = new Label(effect.getStackableObject().getStacks() + "", FontLoader.generateStyle(0, 12, Color.WHITE, 1, Color.BLACK));
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        sp.begin(ShapeRenderer.ShapeType.Line);
        sp.setColor(color);
        Gdx.gl.glLineWidth(borderSize + 3);
        Vector2 a = localToStageCoordinates(new Vector2(getX(), getY()));
        sp.rect(a.x - /*getX()*/ + 1 - getX(), a.y/*getY()*/ - 1, getWidth() + 2, getHeight() + 2);
//        sp.rectLine(getX(), getY(), getX() + getWidth(), getY() + getHeight(), 8);
        sp.end();
        Gdx.gl.glLineWidth(1);



//        super.draw(batch, parentAlpha);




        batch.begin();
//        batch.setColor(this.getColor());
//        batch.getColor().a *= 0.4f;
//        Color tmp = batch.getColor();
//        batch.setColor(tmp.r, tmp.g, tmp.b, tmp.a *= 0.8);

        batch.draw(icon, getX() + borderSize, getY() + borderSize, getWidth() - borderSize*2, getHeight() - borderSize*2);
//
//
//        batch.setColor(tmp);

        if(effect.isCooldownable() && !effect.getCooldownObject().isHidden()){//if cooldownable and stackable in one time ability must declare own EffectIcon
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
