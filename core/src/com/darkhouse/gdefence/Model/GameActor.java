package com.darkhouse.gdefence.Model;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GameActor extends Actor{
    private Texture texture;
    protected Image f;

    public GameActor(Texture texture) {
        this.texture = texture;
        f = new Image(getTexture());
    }

    public Texture getTexture() {
        return texture;
    }
//    protected void setTexture(Texture texture) {
//        this.texture = texture;
//    }

    public Vector2 getCenter(){
        return new Vector2(getX() + getWidth()/2, getY() + getHeight()/2);
    }


    public boolean contains(Vector2 point){
//        System.out.println("st " + point.x + " " + point.y);
//        System.out.println("en " + getX() + " " + getY());

        System.out.println("x "  + (point.x >= getX() && point.x <= getX() + getWidth()));
        System.out.println("y "  + (point.y >= getY() && point.y <= getY() + getHeight()));

        return point.x >= getX() && point.x <= getX() + getWidth() && point.y >= getY() && point.y <= getY() + getHeight();
    }

    public void hit(Rectangle r){

    }



    @Override
    public void draw(Batch batch, float parentAlpha) {//need only rotation from Image
        //batch.draw(texturePath, getX(), getY());
//        Image f = new Image(getTexture());      //kakaya to hueta
        f.setPosition(getX(), getY());          //nado peredelat'
        f.setSize(getWidth(), getHeight());
        f.draw(batch, 1);
    }
}
