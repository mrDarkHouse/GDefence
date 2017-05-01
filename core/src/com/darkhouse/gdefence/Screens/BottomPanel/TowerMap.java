package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.InventorySystem.inventory.HidingClickListener;
import com.darkhouse.gdefence.InventorySystem.inventory.ItemEnum;
import com.darkhouse.gdefence.User;
import com.darkhouse.gdefence.Value;

import java.util.HashMap;

public class TowerMap extends Window{
    private Array <RecipeButton> buttons;
    private ShapeRenderer sr;

    public TowerMap(Skin skin) {
        super("Tower Map", skin);
        getTitleLabel().setAlignment(Align.center);
        setMovable(false);
        setResizable(false);
//        setModal(true);
//        setClip(true);

        sr = new ShapeRenderer();
        sr.setColor(Color.BLACK);
//        Gdx.gl.glLineWidth(1);
//        sr.setAutoShapeType(true);

        setPosition(700, 350);
        defaults().space(25);
        defaults().spaceBottom(50);
        defaults().size(60, 60);
        row().fill().expandX();

        initButtons();

        TextButton closeButton = new TextButton("X", skin);
        closeButton.addListener(new HidingClickListener(this));
//        add(closeButton).height(getPadTop());//
        getTitleTable().add(closeButton).height(getPadTop());

        pack();

//        debug();

        setVisible(false);
    }



    private void initButtons(){
        buttons = new Array<RecipeButton>();
        /*0*/buttons.add(new RecipeButton(ItemEnum.Tower.Basic));
        /*1*/buttons.add(new RecipeButton(ItemEnum.Tower.Rock));
        /*2*/buttons.add(new RecipeButton(ItemEnum.Tower.Arrow));
        /*3*/buttons.add(new RecipeButton(ItemEnum.Tower.Range));
        /*4*/buttons.add(new RecipeButton(ItemEnum.Tower.Short));
        /*5*/buttons.add(new RecipeButton(ItemEnum.Tower.Spear));
        /*6*/buttons.add(new RecipeButton(ItemEnum.Tower.Mountain));
        /*7*/buttons.add(new RecipeButton(ItemEnum.Tower.CrossBow));
//        buttons.add(new RecipeButton(ItemEnum.Tower.SteelArrow));
        /*8*/buttons.add(new RecipeButton(ItemEnum.Tower.Gun));
        /*9*/buttons.add(new RecipeButton(ItemEnum.Tower.Rifle));
        /*10*/buttons.add(new RecipeButton(ItemEnum.Tower.Catapult));
        /*11*/buttons.add(new RecipeButton(ItemEnum.Tower.Ballista));


        for(RecipeButton b:buttons){
            b.setOwner(this);
        }

        //buttons.get(0).setPosition(200, 200);
        //buttons.get(0).setSize(40, 40);
        //buttons.get(1).setPosition(40, 120);
        //buttons.get(2).setPosition(100, 120);
        //buttons.get(3).setPosition(160, 120);

//        for (RecipeButton b:buttons){
//            add(b);
//        }
//        defaults().align(Align.center);

        add(buttons.get(0))/*.align(Align.center)*/.colspan(6).row();
        add(buttons.get(1));//.colspan(2);
        add(buttons.get(2));//.colspan(2);
        add(buttons.get(3)).colspan(2).row();
        add(buttons.get(4)).colspan(1);
        add(buttons.get(5)).colspan(1);
        add(buttons.get(6)).colspan(1);
        add(buttons.get(7)).colspan(1).row();
        add(buttons.get(8)).colspan(2).row();
        add(buttons.get(9));
        add(buttons.get(10)).colspan(2);
        add(buttons.get(11)).colspan(2);


//        add(buttons.get(8));



    }
    public void updateTypes(){
        for (RecipeButton b:buttons){
            b.updateType();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
//        Vector2 line = new Vector2(buttons.get(0).getX(), buttons.get(0).getY()).sub(new Vector2(buttons.get(1).getX(), buttons.get(1).getY()));
//        float lenght = line.len();
//        for (int i = 0; i < lenght; i++){
//            //batch.draw(GDefence.getInstance().assetLoader.get("buildGridLinePixel.png", Texture.class), ((float) (line.x + i * Math.cos(line.angle()))), ((float) (line.y + i * Math.sin(line.angle()))));
//        }
        batch.end();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 1; i <= 3; i++){
            linkTowers(sr, buttons.get(0), buttons.get(i));
        }
        linkTowers(sr, buttons.get(1), buttons.get(4));
//        linkTowers(sr, buttons.get(1), buttons.get(5));
        linkTowers(sr, buttons.get(2), buttons.get(5));

        linkTowers(sr, buttons.get(1), buttons.get(6));
        linkTowers(sr, buttons.get(3), buttons.get(6));

        linkTowers(sr, buttons.get(2), buttons.get(7));
        linkTowers(sr, buttons.get(3), buttons.get(7));

        linkTowers(sr, buttons.get(4), buttons.get(8));
        linkTowers(sr, buttons.get(5), buttons.get(8));
//
        linkTowers(sr, buttons.get(6), buttons.get(10));
//
        linkTowers(sr, buttons.get(7), buttons.get(11));

        linkTowers(sr, buttons.get(8), buttons.get(9));




//        linkTowers(sr, buttons.get(1), buttons.get(7));
//        linkTowers(sr, buttons.get(2), buttons.get(6));
//        linkTowers(sr, buttons.get(2), buttons.get(8));
//        linkTowers(sr, buttons.get(3), buttons.get(7));
//        linkTowers(sr, buttons.get(3), buttons.get(8));

        sr.end();
        batch.begin();
    }

    private void linkTowers(ShapeRenderer sh, RecipeButton r1, RecipeButton r2){
        sh.rectLine(getX() + r1.getX() + r1.getWidth()/2, getY() + r1.getY(),
                getX() + r2.getX() + r2.getWidth()/2, getY() + r2.getY() + r2.getHeight(), 3);

    }
}
