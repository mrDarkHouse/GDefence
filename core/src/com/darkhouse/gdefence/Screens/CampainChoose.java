package com.darkhouse.gdefence.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.I18NBundle;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.User;

import java.io.File;

public class CampainChoose extends AbstractMenuScreen{
    //private GDefence mainclass;
    //private TextButton newCamp;
    //private TextButton loadCamp;
    private Dialog d;

    public CampainChoose() {
        super(true);
        loadButtons();
        newCampDialog();
        //this.mainclass = mainclass;

    }

    @Override
    public void show() {
        super.show();

//        loadButtons();
    }

    private void loadButtons(){
        Table table = new Table();
        //ImageButton backButton = new ImageButton();
//        I18NBundle b = GDefence.getInstance().assetLoader.get("Language/text", I18NBundle.class);
        AssetLoader a = GDefence.getInstance().assetLoader;

        TextButton newCamp = new TextButton(a.getWord("new_game"), GDefence.getInstance().assetLoader.getSkin(), "rect");
        newCamp.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                showDialog();
                //mainclass.setScreen(new CampainMap(mainclass));
                return true;
            }
        });
        //newCamp.setSize();
        TextButton loadCamp = new TextButton(a.getWord("load_game"), GDefence.getInstance().assetLoader.getSkin(), "rect");
        File f = new File("Save/UserSave.properties");
        if(!f.exists()) {
            loadCamp.setTouchable(Touchable.disabled);
            loadCamp.setColor(255, 255, 255, 100);
        }

        loadCamp.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                //mainClass.user = new User();
                //if(/*GDefence.getInstance().user.load()*//*GDefence.getInstance().user != null*/) {
                GDefence.getInstance().user = new User();
                GDefence.getInstance().flushCampainScreens();
//                GDefence.getInstance().user.initNewUser();
                GDefence.getInstance().user.load();

                GDefence.getInstance().switchScreen(GDefence.getInstance().getCampainMap());

                GDefence.getInstance().flushCampainChoose();//delete hide effects


//                GDefence.getInstance().initCampainMap();//do it
//                GDefence.getInstance().setScreen(GDefence.getInstance().getCampainMap());
                //}
                return true;
            }
        });

        table.setWidth(stage.getWidth());
        table.align(Align.center|Align.top);
        table.setPosition(0, GDefence.HEIGHT);


        table.padTop(200);
        table.add(newCamp).width(260).height(50).spaceBottom(30).row();
        table.add(loadCamp).width(260).height(50).row();
        //table.bottom();


        stage.addActor(table);
    }

    private void newCampDialog(){
        d = new Dialog("", GDefence.getInstance().assetLoader.getSkin(), "dialog"){

            {
//                I18NBundle b = GDefence.getInstance().assetLoader.get("Language/text", I18NBundle.class);
                AssetLoader a = GDefence.getInstance().assetLoader;
                text(a.getWord("campain_sure")).padTop(250);
                button(a.getWord("accept"), true);
                button(a.getWord("decline"), false);
                //getButtonTable();//defaults().width(200);
                Array<Cell> cells = getButtonTable().getCells();
                Cell cell = cells.get(0);
                cell.width(50).padBottom(360);
                cell.height(40);
                //cell.padBottom(Gdx.graphics.getHeight()/2);
                //cell.center().top();

                cell = cells.get(1);
                cell.width(50).padBottom(360);
                cell.height(40).row();
                //cell.padBottom(Gdx.graphics.getHeight()/2);

            }

            @Override
            protected void result(Object object) {
                if(object.equals(true)){
//                    GDefence.getInstance().user = new User();
//                    GDefence.getInstance().user.init();
//                    GDefence.getInstance().user.save();
//                    if(GDefence.getInstance().user != null) {///
//                        GDefence.getInstance().user = null;
//                    }
                    GDefence.getInstance().user = new User();
                    GDefence.getInstance().flushCampainScreens();
                    GDefence.getInstance().user.initNewUser();

                    GDefence.getInstance().switchScreen(GDefence.getInstance().getCampainMap());

                    GDefence.getInstance().flushCampainChoose();//delete hide effects
                    //GDefence.getInstance().setScreen(new CampainMap());
                }else{
                    hide();
                }
            }
        };
    }
    private void showDialog(){
        d.show(stage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        //stage.act(delta);
        //stage.draw();
    }



//    @Override
//    public void resize(int width, int height) {
//
//    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        super.hide();
    }

//    @Override
//    public void dispose() {
//
//    }
}
