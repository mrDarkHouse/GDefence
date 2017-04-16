package com.darkhouse.gdefence.Model;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
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

    @Override
    public void draw(Batch batch, float parentAlpha) {//need only rotation from Image
        //batch.draw(texturePath, getX(), getY());
//        Image f = new Image(getTexture());      //kakaya to hueta
        f.setPosition(getX(), getY());          //nado peredelat'
        f.setSize(getWidth(), getHeight());
        f.draw(batch, 1);
    }
}
