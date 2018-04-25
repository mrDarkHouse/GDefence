package com.darkhouse.gdefence.Model.Panels;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;

public class UserPanel extends AbstractPanel {
    private ImageTextButton userlevelButton;
    private ExpBar expBar;
    private int expBarSize[];

    public UserPanel(int x, int y, int width, int height) {
        super(x, y, width, height);

        userlevelButton = new ImageTextButton("" + GDefence.getInstance().user.getLevel(), GDefence.getInstance().assetLoader.getUserLevelSkin());
        //userlevelButton.getLabel().setFontScale(1.5f);
        int userLevelSize[] = {width/4, width/4};
        //userlevelButton.setSize(userLevelSize[0], userLevelSize[1]);
        //userlevelButton.setPosition();
//        userlevelButton.addListener(new InputListener() {
//                                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                                            return true;
//                                        }
//                                    }
//        );


        expBarSize = new int[]{width, height - userLevelSize[1]};
        expBar = new ExpBar(expBarSize[0], expBarSize[1]);

        //add(new GoldPanel(mainClass)).width(width/6*2).height(height);
        add(userlevelButton).width(userLevelSize[0]).height(userLevelSize[1]).row();
        add(expBar);

        //System.out.println(getY());

    }
//    public void update(){
//
//    }

    @Override
    public void act(float delta) {
        super.act(delta);
//        userlevelButton.setText("" + GDefence.getInstance().user.getLevel());
    }

    @Override
    public void update() {
        GDefence.getInstance().user.update();
        userlevelButton.setText("" + GDefence.getInstance().user.getLevel());
        Cell c = getCell(expBar);
        expBar = new ExpBar(expBarSize[0], expBarSize[1]);
        c.setActor(expBar);
//        System.out.println(GDefence.getInstance().user.getTotalExp() + "|" + GDefence.getInstance().user.getCurrentExp());
//        System.out.println(expBar.getMaxValue() + "|" + expBar.getValue());
//        add(expBar);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        //debug();
    }



}
