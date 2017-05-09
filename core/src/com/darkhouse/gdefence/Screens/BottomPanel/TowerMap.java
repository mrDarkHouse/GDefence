package com.darkhouse.gdefence.Screens.BottomPanel;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
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
    private Table table;

    public TowerMap(Skin skin) {
        super("Tower Map", skin);
        getTitleLabel().setAlignment(Align.center);
        setMovable(false);
        setResizable(false);
//        setModal(true);
//        setTransform(false);

        table = new Table();
//        setClip(true);

//        setKeepWithinStage(false);


        sr = new ShapeRenderer();
        sr.setColor(Color.BLACK);
//        Gdx.gl.glLineWidth(1);
//        sr.setAutoShapeType(true);



        setPosition(700, 50);

        table.defaults().space(50);
        table.defaults().spaceBottom(10);
//        table.defaults().spaceRight(0);
        table.padLeft(50);
        table.padRight(50);
        table.defaults().size(60, 60);
        table.row().fill().expandX();

        initButtons();
        add(table);

        table.debug();

        TextButton closeButton = new TextButton("X", skin);
        closeButton.addListener(new HidingClickListener(this));
//        add(closeButton).height(getPadTop());//
        getTitleTable().add(closeButton).height(getPadTop());


//        table.setTransform(true);
//        table.setY(-600);


//        table.pack();

        pack();



        setHeight(630);
//        table.setPosition(20, -500);
//        table.setY(getY() + getHeight() - table.getHeight() - 20);


//        System.out.println(table.getY() + " " + table.getHeight() + " " + getY());

//        debugCell();


//        System.out.println(getTitleLabel().getY());
//        moveBy(0, get);





        addListener(new DragListener(){
            private float offsetX;
            private float offsetY;

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                super.drag(event, x, y, pointer);
                table.setPosition(offsetX + x, offsetY + y);
            }


            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {
                super.dragStart(event, x, y, pointer);
                offsetX = table.getX() - x;
                offsetY = table.getY() - y;
//                System.out.println(offsetX + " " + offsetY);
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                super.dragStop(event, x, y, pointer);
                offsetX = 0;
                offsetY = 0;
            }
        });

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
        /*12*/buttons.add(new RecipeButton(ItemEnum.Tower.SteamMachine));
        /*13*/buttons.add(new RecipeButton(ItemEnum.Tower.MachineGun));
        /*14*/buttons.add(new RecipeButton(ItemEnum.Tower.Cannon));
        /*15*/buttons.add(new RecipeButton(ItemEnum.Tower.Glaive));
        /*16*/buttons.add(new RecipeButton(ItemEnum.Tower.Rocket));
        /*17*/buttons.add(new RecipeButton(ItemEnum.Tower.MultiShot));
        /*18*/buttons.add(new RecipeButton(ItemEnum.Tower.Sniper));
        /*19*/buttons.add(new RecipeButton(ItemEnum.Tower.Shotgun));
        /*20*/buttons.add(new RecipeButton(ItemEnum.Tower.Missle));


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

        table.add(buttons.get(0))/*.align(Align.center)*/.colspan(6).row();
//        add(new Actor()).prefWidth(defaults().getPrefWidth()/2);
        table.add(buttons.get(1));//.colspan(2);
        table.add(buttons.get(2));//.colspan(2);
        table.add(buttons.get(3)).colspan(2).row();
        table.add(buttons.get(4)).colspan(1);
        table.add(buttons.get(5)).colspan(1);
        table.add(buttons.get(6)).colspan(1);
        table.add(buttons.get(7)).colspan(1).row();
        table.add(buttons.get(8)).colspan(2);
        table.add(buttons.get(12)).colspan(2).row();
        table.add(buttons.get(9));
        table.add(buttons.get(10)).colspan(2).right();
        table.add(buttons.get(11)).colspan(2).row();
        table.add(buttons.get(13)).colspan(1);
        table.add(buttons.get(14)).colspan(2).right();
        table.add(buttons.get(15)).colspan(2).row();
        table.add(new Actor());
        table.add(buttons.get(16)).colspan(2).right();
        table.add(buttons.get(17)).colspan(1).row();
        table.add(buttons.get(18)).colspan(1);
        table.add(buttons.get(19)).colspan(1);
        table.add(buttons.get(20)).colspan(1);




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
//        Gdx.gl.glEnable(GL20.GL_BLEND);
//        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
//        sr.setProjectionMatrix(batch.getProjectionMatrix());
//        Color tmp = sr.getColor();
//        sr.setColor(tmp.r, tmp.g, tmp.b, parentAlpha);

        sr.begin(ShapeRenderer.ShapeType.Filled);
//        sr.setTransformMatrix(batch.getTransformMatrix());

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

        linkTowers(sr, buttons.get(12), buttons.get(10));

        linkTowers(sr, buttons.get(12), buttons.get(11));

        linkTowers(sr, buttons.get(9), buttons.get(13));
        linkTowers(sr, buttons.get(12), buttons.get(13));

        linkTowers(sr, buttons.get(8), buttons.get(14));
        linkTowers(sr, buttons.get(10), buttons.get(14));

        linkTowers(sr, buttons.get(5), buttons.get(15));
        linkTowers(sr, buttons.get(11), buttons.get(15));

        linkTowers(sr, buttons.get(14), buttons.get(16));

        linkTowers(sr, buttons.get(15), buttons.get(17));

        linkTowers(sr, buttons.get(13), buttons.get(18));

        linkTowers(sr, buttons.get(8), buttons.get(19));
        linkTowers(sr, buttons.get(17), buttons.get(19));

        linkTowers(sr, buttons.get(16), buttons.get(20));






//        linkTowers(sr, buttons.get(1), buttons.get(7));
//        linkTowers(sr, buttons.get(2), buttons.get(6));
//        linkTowers(sr, buttons.get(2), buttons.get(8));
//        linkTowers(sr, buttons.get(3), buttons.get(7));
//        linkTowers(sr, buttons.get(3), buttons.get(8));

        sr.end();
//        Gdx.gl.glDisable(GL20.GL_BLEND);
        batch.begin();
    }

    private void linkTowers(ShapeRenderer sh, RecipeButton r1, RecipeButton r2){
        sh.rectLine(getX() + table.getX() + r1.getX() + r1.getWidth()/2, getY() + table.getY() + r1.getY(),
                getX() + table.getX() + r2.getX() + r2.getWidth()/2, getY() + table.getY() + r2.getY() + r2.getHeight(), 3);
    }
}
