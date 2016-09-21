package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Model.BackButton;

public abstract class AbstractCampainScreen implements Screen {

    protected ImageTextButton nameButton;
    protected String name;
    protected final int topPadSize = 64;

    protected SpriteBatch batch;
    protected Stage stage;
    protected ShapeRenderer shape;


    public AbstractCampainScreen(String name) {
        this.name = name;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();
        shape = new ShapeRenderer();

        Gdx.input.setInputProcessor(stage);
        define();
    }

    protected void define(){
        //Camera cam = new PerspectiveCamera();
        //stage.setViewport(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam));

        int backButtonsSize[] = {64, topPadSize};
        int nameButtonSize[] = {Gdx.graphics.getWidth() - backButtonsSize[0]*2, backButtonsSize[1]};


        ImageTextButton.ImageTextButtonStyle nameButtonStyle = new ImageTextButton.ImageTextButtonStyle();
        nameButtonStyle.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.nameButtonFone));
        nameButtonStyle.over = new TextureRegionDrawable(new TextureRegion(AssetLoader.nameButtonFone));
        nameButtonStyle.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.nameButtonFone));
        nameButtonStyle.font = new BitmapFont(Gdx.files.internal("Fonts/MainFont.fnt"));
        nameButton = new ImageTextButton(name, nameButtonStyle);
        nameButton.setSize(Gdx.graphics.getWidth()/*nameButtonSize[0]*/, nameButtonSize[1]);
        nameButton.setPosition(0/*backButton.getX() + backButtonsSize[0]*/, Gdx.graphics.getHeight() - backButtonsSize[1]);

        stage.addActor(nameButton);

//        ImageButton.ImageButtonStyle backButtonStyle = new ImageButton.ImageButtonStyle();
//        backButtonStyle.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.backButton));
//        backButtonStyle.over = new TextureRegionDrawable(new TextureRegion(AssetLoader.backButton));
//        backButtonStyle.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.backButton));
//        backButton = new ImageButton(backButtonStyle);
//        backButton.setSize(backButtonsSize[0], backButtonsSize[1]);
//        backButton.setPosition(0, Gdx.graphics.getHeight() - backButtonsSize[1]);
//        backButton.addListener(new InputListener(){
//            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                //mainClass.setScreen(new MainMenu(mainClass));
//                mainClass.setPreviousScreen();
//                return true;
//            }
//        });
        stage.addActor(new BackButton());




    }
    private void drawLines(){
        int lineWidth = 2;
        Gdx.gl.glLineWidth(lineWidth);
        shape.begin(ShapeRenderer.ShapeType.Line);
        //shape.setProjectionMatrix(batch.getProjectionMatrix());
        shape.setColor(0, 0, 0, 1);
        shape.line(0, Gdx.graphics.getHeight() - topPadSize, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - topPadSize);
        shape.line(0, Gdx.graphics.getHeight() - lineWidth/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - lineWidth/2);
        shape.line(topPadSize, Gdx.graphics.getHeight(), topPadSize, Gdx.graphics.getHeight() - topPadSize);
        shape.line(lineWidth/2, Gdx.graphics.getHeight(), lineWidth/2, Gdx.graphics.getHeight() - topPadSize);
        shape.line(Gdx.graphics.getWidth() - lineWidth/2, Gdx.graphics.getHeight(), Gdx.graphics.getWidth() - lineWidth/2, Gdx.graphics.getHeight() - topPadSize);
        shape.end();
    }

    @Override
    public void resize(int width, int height) {
        //stage.getViewport().update(width, height);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(AssetLoader.campainBg, 0, 0);
        batch.end();

        stage.act(delta);
        stage.draw();
        drawLines();
    }



    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        shape.dispose();
    }
}
