package com.darkhouse.gdefence.Model;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.awt.Point;

import static com.badlogic.gdx.graphics.g2d.Batch.*;
import static com.badlogic.gdx.graphics.g2d.Batch.Y4;


public class GDSprite extends Actor{
    private Sprite texture;

    public void setRegion(Texture texture) {
        this.texture = new Sprite(texture);
    }

    public Texture getTexture() {
        return texture.getTexture();
    }

//    public void setTexture(Sprite texture) {
//        this.texture = new ;
//    }

    public Rectangle getBoundingRectangle () {
//        final float[] vertices = getVertices();
//
//        float minx = vertices[X1];
//        float miny = vertices[Y1];
//        float maxx = vertices[X1];
//        float maxy = vertices[Y1];
//
//        minx = minx > vertices[X2] ? vertices[X2] : minx;
//        minx = minx > vertices[X3] ? vertices[X3] : minx;
//        minx = minx > vertices[X4] ? vertices[X4] : minx;
//
//        maxx = maxx < vertices[X2] ? vertices[X2] : maxx;
//        maxx = maxx < vertices[X3] ? vertices[X3] : maxx;
//        maxx = maxx < vertices[X4] ? vertices[X4] : maxx;
//
//        miny = miny > vertices[Y2] ? vertices[Y2] : miny;
//        miny = miny > vertices[Y3] ? vertices[Y3] : miny;
//        miny = miny > vertices[Y4] ? vertices[Y4] : miny;
//
//        maxy = maxy < vertices[Y2] ? vertices[Y2] : maxy;
//        maxy = maxy < vertices[Y3] ? vertices[Y3] : maxy;
//        maxy = maxy < vertices[Y4] ? vertices[Y4] : maxy;

        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public Vector2 getCenter(){
        return new Vector2(getX() + getWidth()/2, getY() + getHeight()/2);
    }

    public Vector2 getPosition(){
        return new Vector2((int)getX(), (int)getY());
    }

    public boolean contains(Vector2 point){
        return getBoundingRectangle().contains(point);
    }

    public boolean contains(float x, float y){
        return getBoundingRectangle().contains(new Vector2(x, y));
    }

    public boolean contains(Rectangle rectangle){
        Rectangle r = new Rectangle(rectangle.getX() + 1, rectangle.getY() + 1, rectangle.getWidth() - 2, rectangle.getHeight() - 2);
        //hotfix
        return getBoundingRectangle().contains(r);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
//        texture.draw(batch, parentAlpha);
    }
}
