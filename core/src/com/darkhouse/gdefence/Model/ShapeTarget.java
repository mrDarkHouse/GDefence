package com.darkhouse.gdefence.Model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ShapeTarget extends Actor {

    private ShapeRenderer shape;
    private float radius;

    public ShapeTarget(ShapeRenderer shape, float radius) {
        this.shape = shape;
        this.radius = radius;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        Gdx.gl.glLineWidth(2);
        shape.setColor(Color.WHITE);
        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.circle(getX() + radius/2, getY() + radius/2, radius, 256);
        shape.point(getX() + radius/2, getY() + radius/2, 0);
        shape.end();
        Gdx.gl.glLineWidth(2);
        batch.begin();
    }
}
