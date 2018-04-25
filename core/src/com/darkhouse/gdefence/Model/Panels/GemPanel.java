package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
import com.darkhouse.gdefence.Helpers.FontLoader;
import com.darkhouse.gdefence.User;

public class GemPanel extends AbstractPanel{
    private int borderSize;
    private Label[] gemsLabel;

    //private GDefence mainClass;
    //private ShapeRenderer shape;

    public GemPanel(int x, int y, int width, int height) {
        super(x, y, width, height);
        //this.mainClass = mainClass;
        //shape = new ShapeRenderer();
        setBackground(GDefence.getInstance().assetLoader.getSkin().getDrawable("bar-bg"));
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

        gemsLabel = new Label[6];
        for (int i = 0; i < gemsLabel.length; i++) {
            gemsLabel[i] = new Label(GDefence.getInstance().user.getGemNumber(User.GEM_TYPE.values()[i]) + "", GDefence.getInstance().assetLoader.getSkin()/*FontLoader.generateStyle(0, 26, Color.BLACK)*/, "default");
        }
//        gemsLabel[0] = new Label(GDefence.getInstance().user.getGemNumber(User.GEM_TYPE.RED) + "", AssetLoader.getSkin());
//        gemsLabel[1] = new Label(GDefence.getInstance().user.getGemNumber(User.GEM_TYPE.YELLOW) + "", AssetLoader.getSkin());
//        gemsLabel[2] = new Label(GDefence.getInstance().user.getGemNumber(User.GEM_TYPE.BLUE) + "", AssetLoader.getSkin());
//        gemsLabel[3] = new Label(GDefence.getInstance().user.getGemNumber(User.GEM_TYPE.BLACK) + "", AssetLoader.getSkin());
//        gemsLabel[4] = new Label(GDefence.getInstance().user.getGemNumber(User.GEM_TYPE.GREEN) + "", AssetLoader.getSkin());
//        gemsLabel[5] = new Label(GDefence.getInstance().user.getGemNumber(User.GEM_TYPE.WHITE) + "", AssetLoader.getSkin());


        //setBackground(new TextureRegionDrawable(new TextureRegion(t)));
        add(new Image(GDefence.getInstance().assetLoader.get("Gems/redGem.png", Texture.class))).width(horizontal).height(verticalImg).spaceRight(borderSize);
        add(new Image(GDefence.getInstance().assetLoader.get("Gems/yellowGem.png", Texture.class))).width(horizontal).height(verticalImg).spaceRight(borderSize);
        add(new Image(GDefence.getInstance().assetLoader.get("Gems/blueGem.png", Texture.class))).width(horizontal).height(verticalImg).row();
        gemsLabel[0].setAlignment(Align.center);
        add(gemsLabel[0]).width(horizontal).height(verticalTxt).spaceBottom(borderSize).spaceRight(borderSize);
        gemsLabel[1].setAlignment(Align.center);
        add(gemsLabel[1]).width(horizontal).height(verticalTxt).spaceBottom(borderSize).spaceRight(borderSize);
        gemsLabel[2].setAlignment(Align.center);
        add(gemsLabel[2]).width(horizontal).height(verticalTxt).spaceBottom(borderSize).spaceRight(borderSize).row();
        add(new Image(GDefence.getInstance().assetLoader.get("Gems/blackGem.png", Texture.class))).width(horizontal).height(verticalImg).spaceRight(borderSize);
        add(new Image(GDefence.getInstance().assetLoader.get("Gems/greenGem.png", Texture.class))).width(horizontal).height(verticalImg).spaceRight(borderSize);
        add(new Image(GDefence.getInstance().assetLoader.get("Gems/whiteGem.png", Texture.class))).width(horizontal).height(verticalImg).spaceRight(borderSize).row();
        gemsLabel[3].setAlignment(Align.center);
        add(gemsLabel[3]).width(horizontal).height(verticalTxt).spaceRight(borderSize);
        gemsLabel[4].setAlignment(Align.center);
        add(gemsLabel[4]).width(horizontal).height(verticalTxt).spaceRight(borderSize);
        gemsLabel[5].setAlignment(Align.center);
        add(gemsLabel[5]).width(horizontal).height(verticalTxt);


    }
    public void updateText(){
        for (int i = 0; i < gemsLabel.length; i++) {
            gemsLabel[i].setText(GDefence.getInstance().user.getGemNumber(User.GEM_TYPE.values()[i]) + "");
        }
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

    @Override
    public void act(float delta) {//optimization: update only when gems add/delete
        super.act(delta);
        updateText();
    }
}
