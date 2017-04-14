package com.darkhouse.gdefence.Model;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.awt.Point;


public class GDSprite extends Sprite{



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



}
