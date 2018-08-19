package com.darkhouse.gdefence.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.User;
//import android.graphics.drawable;

import java.util.HashMap;
import java.util.Map;


public class MainMenu extends AbstractMenuScreen{

    Dialog exitDialog;


    public MainMenu() {
        super(false);
    }
    public void init(){
        loadButtons();
        exitDialog();
    }

    @Override
    public void show() {
        super.show();
//        loadButtons();

    }

    private void loadButtons(){

        //Gdx.input.setInputProcessor(stage);//change
        //texturePath  = new Texture(Gdx.files.internal("MainMenuAtlas.png"));
        //TextureAtlas mainMenuAtlas = new TextureAtlas("MainMenuAtlas.png");
//        Skin buttonsSkin = new Skin(mainMenuAtlas);
//        ImageButton.ImageButtonStyle connectToHost = new ImageButton.ImageButtonStyle();
//        connectToHost.up = buttonsSkin.getDrawable("menu-connect-btn");
//        connectToHost.over = buttonsSkin.getDrawable("menu-connect-btn");
//        connectToHost.down = buttonsSkin.getDrawable("menu-connect-btn-down");
        int buttonsSize[] = {250, 90};
        int startBorder = Gdx.graphics.getHeight()/16;
        int sizeBetween = Gdx.graphics.getHeight()/24;
        //TextureRegion tmp[][] = TextureRegion.split(texturePath, texturePath.getWidth() / 2, texturePath.getHeight() / 10);
//        textureRegions.put("campain", AssetLoader.mainMenuButtons[0][0]);
//        textureRegions.put("campainPressed", AssetLoader.mainMenuButtons[0][1]);
//        textureRegions.put("options", AssetLoader.mainMenuButtons[1][0]);
//        textureRegions.put("optionsPressed", AssetLoader.mainMenuButtons[1][1]);
//        textureRegions.put("exit", AssetLoader.mainMenuButtons[2][0]);
//        textureRegions.put("exitPressed", AssetLoader.mainMenuButtons[2][1]);
//        ImageButton.ImageButtonStyle campainStyle = new ImageButton.ImageButtonStyle();
//        campainStyle.up = new TextureRegionDrawable(textureRegions.get("campain"));
//        campainStyle.over = new TextureRegionDrawable(textureRegions.get("campainPressed"));
//        campainStyle.down = new TextureRegionDrawable(textureRegions.get("campainPressed"));
//        ImageButton.ImageButtonStyle optionsStyle = new ImageButton.ImageButtonStyle();
//        optionsStyle.up = new TextureRegionDrawable(textureRegions.get("options"));
//        optionsStyle.over = new TextureRegionDrawable(textureRegions.get("optionsPressed"));
//        optionsStyle.down = new TextureRegionDrawable(textureRegions.get("optionsPressed"));
//        ImageButton.ImageButtonStyle exitStyle = new ImageButton.ImageButtonStyle();
//        exitStyle.up = new TextureRegionDrawable(textureRegions.get("exit"));
//        exitStyle.over = new TextureRegionDrawable(textureRegions.get("exitPressed"));
//        exitStyle.down = new TextureRegionDrawable(textureRegions.get("exitPressed"));

//        ImageButton campainButton = new ImageButton(GDefence.getInstance().assetLoader.getMainMenuButtons(1));
//        campainButton.setSize(buttonsSize[0], buttonsSize[1]);
//        campainButton.setPosition(Gdx.graphics.getWidth()/2 - buttonsSize[0]/2, Gdx.graphics.getHeight() - buttonsSize[1] - startBorder);
//        campainButton.addListener(new InputListener(){
//            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                GDefence.getInstance().switchScreen(GDefence.getInstance().getCampainChoose());
//                //GDefence.getInstance().setScreen(new CampainChoose());
//                //mainclass.setScreen(new CampainMap(mainclass));
//                return true;
//            }
//        });
//
//        ImageButton optionsButton = new ImageButton(GDefence.getInstance().assetLoader.getMainMenuButtons(2));
//        optionsButton.setSize(buttonsSize[0], buttonsSize[1]);
//        optionsButton.setPosition(Gdx.graphics.getWidth()/2 - buttonsSize[0]/2, campainButton.getY() - buttonsSize[1] - sizeBetween);
//        optionsButton.addListener(new InputListener(){
//            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                GDefence.getInstance().switchScreen(/*GDefence.getInstance().getOptionScreen()*/new OptionScreen());
//                //GDefence.getInstance().setScreen(new OptionScreen());
//                return true;
//            }
//        });
//
//        ImageButton exitButton = new ImageButton(GDefence.getInstance().assetLoader.getMainMenuButtons(3));
//        exitButton.setSize(buttonsSize[0], buttonsSize[1]);
//        exitButton.setPosition(Gdx.graphics.getWidth()/2 - buttonsSize[0]/2, optionsButton.getY() - buttonsSize[1] - sizeBetween);
//        exitButton.addListener(new InputListener(){
//            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                exitDialog.show(stage);
//                //Gdx.app.exit();
//                return true;
//            }
//        });
        AssetLoader a = GDefence.getInstance().assetLoader;
//        I18NBundle b = GDefence.getInstance().assetLoader.get("Language/text", I18NBundle.class);
        TextButton campainButton = new TextButton(a.getWord("campain"), GDefence.getInstance().assetLoader.getSkin(), "main-menu");
        campainButton.setSize(buttonsSize[0], buttonsSize[1]);
        campainButton.setPosition(Gdx.graphics.getWidth()/2 - buttonsSize[0]/2, Gdx.graphics.getHeight() - buttonsSize[1] - startBorder);
        campainButton.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                GDefence.getInstance().switchScreen(GDefence.getInstance().getCampainChoose());
                //GDefence.getInstance().setScreen(new CampainChoose());
                //mainclass.setScreen(new CampainMap(mainclass));
                return true;
            }
        });
        TextButton optionsButton = new TextButton(a.getWord("options"), GDefence.getInstance().assetLoader.getSkin(), "main-menu");
        optionsButton.setSize(buttonsSize[0], buttonsSize[1]);
        optionsButton.setPosition(Gdx.graphics.getWidth()/2 - buttonsSize[0]/2, campainButton.getY() - buttonsSize[1] - sizeBetween);
        optionsButton.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                GDefence.getInstance().switchScreen(/*GDefence.getInstance().getOptionScreen()*/new OptionScreen());
                //GDefence.getInstance().setScreen(new OptionScreen());
                return true;
            }
        });
        TextButton exitButton = new TextButton(a.getWord("exit"), GDefence.getInstance().assetLoader.getSkin(), "main-menu");
        exitButton.setSize(buttonsSize[0], buttonsSize[1]);
        exitButton.setPosition(Gdx.graphics.getWidth()/2 - buttonsSize[0]/2, optionsButton.getY() - buttonsSize[1] - sizeBetween);
        exitButton.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                exitDialog.show(stage);
                //Gdx.app.exit();
                return true;
            }
        });



        stage.addActor(campainButton);
        stage.addActor(optionsButton);
        stage.addActor(exitButton);


        //TextureRegion tmp[][] = TextureRegion.split();


    }
    private void exitDialog(){

//        Window.WindowStyle ws = new Window.WindowStyle();
//        ws.titleFont = new BitmapFont();
//        ws.titleFontColor = Color.BLACK;
//        ws.background = createDrawable(Color.RED);
//        ws.stageBackground = createDrawable(Color.BLUE);
        //alpha color



        exitDialog = new Dialog("", GDefence.getInstance().assetLoader.getSkin(), "dialog"){

            {
                setMovable(false);
//                I18NBundle b = GDefence.getInstance().assetLoader.get("Language/text", I18NBundle.class);
                AssetLoader a = GDefence.getInstance().assetLoader;
                text(a.getWord("exit_sure")).padTop(250);
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
                cell.height(40);
                //cell.padBottom(Gdx.graphics.getHeight()/2);

            }

            @Override
            protected void result(Object object) {
                if(object.equals(true)){
                    Gdx.app.exit();
                }else{
                    hide();
                }
            }
        };



        //d.getButtonTable().center().top();
        //d.getButtonTable().defaults().width(200);
    }






    @Override
    public void render(float delta) {
        //camera.update();
        super.render(delta);




        //batch.begin();
        //batch.draw(AssetLoader.mainMenuBg, 0, 0);
        //batch.end();

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
        //Gdx.input.setInputProcessor(null);
        //this.dispose();
    }

//    @Override
//    public void dispose() {
//        super.dispose();
//        batch.dispose();
//        //menuBg.dispose();
//        stage.dispose();
//
//    }
}
