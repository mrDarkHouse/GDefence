package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.User;

public class GemPanel extends AbstractPanel{
    private int borderSize;

    //private GDefence mainClass;
    //private ShapeRenderer shape;

    public GemPanel(int x, int y, int width, int height) {
        super(x, y, width, height);
        //this.mainClass = mainClass;
        //shape = new ShapeRenderer();

        load();




    }

    private void load(){
        borderSize = 3;
        int width = (int)getWidth();
        int height = (int)getHeight();
        int horizontal = width/3 - borderSize*4;
        int vertical = height/2 - borderSize*3;
        int verticalImg = vertical/5*4;
        int verticalTxt = vertical/5;

        //setBounds(x, y, widthN, heightN);

        //Image fillImage = new Image(AssetLoader.mainMenuBg);
        //fillImage.setSize(width, height);
        //fillImage.setPosition(x, y);
        //setFillParent(true);
        //setClip(false);
        //add(fillImage);
        //fillTable.setBackground(new NinePatchDrawable(new NinePatch(AssetLoader.mainMenuBg, borderSize, borderSize, borderSize, borderSize)));


        //setBackground(new TextureRegionDrawable(new TextureRegion(t)));
        add(new Image(AssetLoader.gemTexture[0])).width(horizontal).height(verticalImg).spaceRight(borderSize);
        add(new Image(AssetLoader.gemTexture[1])).width(horizontal).height(verticalImg).spaceRight(borderSize);
        add(new Image(AssetLoader.gemTexture[2])).width(horizontal).height(verticalImg).row();
        Label l = new Label(GDefence.getInstance().user.getGemNumber(User.GEM_TYPE.RED) + "", AssetLoader.getSkin());
        l.setAlignment(Align.center);
        add(l).width(horizontal).height(verticalTxt).spaceBottom(borderSize).spaceRight(borderSize);
        l = new Label(GDefence.getInstance().user.getGemNumber(User.GEM_TYPE.YELLOW) + "", AssetLoader.getSkin());
        l.setAlignment(Align.center);
        add(l).width(horizontal).height(verticalTxt).spaceBottom(borderSize).spaceRight(borderSize);
        l = new Label(GDefence.getInstance().user.getGemNumber(User.GEM_TYPE.BLUE) + "", AssetLoader.getSkin());
        l.setAlignment(Align.center);
        add(l).width(horizontal).height(verticalTxt).spaceBottom(borderSize).spaceRight(borderSize).row();
        add(new Image(AssetLoader.gemTexture[3])).width(horizontal).height(verticalImg).spaceRight(borderSize);
        add(new Image(AssetLoader.gemTexture[4])).width(horizontal).height(verticalImg).spaceRight(borderSize);
        add(new Image(AssetLoader.gemTexture[5])).width(horizontal).height(verticalImg).spaceRight(borderSize).row();
        l = new Label(GDefence.getInstance().user.getGemNumber(User.GEM_TYPE.BLACK) + "", AssetLoader.getSkin());
        l.setAlignment(Align.center);
        add(l).width(horizontal).height(verticalTxt).spaceRight(borderSize);
        l = new Label(GDefence.getInstance().user.getGemNumber(User.GEM_TYPE.GREEN) + "", AssetLoader.getSkin());
        l.setAlignment(Align.center);
        add(l).width(horizontal).height(verticalTxt).spaceRight(borderSize);
        l = new Label(GDefence.getInstance().user.getGemNumber(User.GEM_TYPE.WHITE) + "", AssetLoader.getSkin());
        l.setAlignment(Align.center);
        add(l).width(horizontal).height(verticalTxt);


    }

    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);
        /*
        Gdx.gl.glLineWidth(borderSize);//KOSTIL'
        shape.begin(ShapeRenderer.ShapeType.Line);
        //shape.setProjectionMatrix(batch.getProjectionMatrix());
        shape.setColor(0, 0, 0, 1);

        int horizontal = (int)getWidth()/3 - borderSize*4;
        int vertical = (int)getHeight()/2 - borderSize*3;

        shape.line(getX(), getY() + getHeight(), getX() + getWidth(), getY() + getHeight());
        shape.line(getX(), getY(), getX() + getWidth(), getY());

        shape.line(getX(), getY() + getHeight(), getX(), getY());
        shape.line(getX() + getWidth(), getY() + getHeight(), getX() + getWidth(), getY());

        shape.line(getX() + horizontal, getY() + getHeight(), getX() + horizontal, getY());
        shape.line(getX() + horizontal*2, getY() + getHeight(), getX() + horizontal*2, getY());

        shape.line(getX(), getY() + getHeight()/2 - borderSize, getX() + getWidth(), getY() + getHeight()/2 - borderSize);

        shape.end();//
        */
    }




}
