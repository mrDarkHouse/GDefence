package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Model.BackButton;

public abstract class AbstractCampainScreen extends AbstractScreen {

    protected TextButton nameButton;
    private String name;
    protected final int topPadSize = 64;

    public void setName(String name) {
        this.name = name;
        I18NBundle b = GDefence.getInstance().assetLoader.get("Language/text", I18NBundle.class);
        nameButton.setText(b.get(name));
    }
    public void setName(String name, String addictionalName) {
        this.name = name;
        I18NBundle b = GDefence.getInstance().assetLoader.get("Language/text", I18NBundle.class);
        nameButton.setText(b.get(name) + " " + addictionalName);
    }

    protected SpriteBatch batch;


    protected ShapeRenderer shape;

//    private Viewport viewport;

    private Table table;


    public AbstractCampainScreen(String name) {
        super();
        I18NBundle b = GDefence.getInstance().assetLoader.get("Language/text", I18NBundle.class);
        this.name = b.get(name);
        batch = new SpriteBatch();
        //stage = new Stage();
        //shape = new ShapeRenderer();

//        Gdx.input.setInputProcessor(stage);
        define();
    }

    @Override
    public void show() {
//        batch = new SpriteBatch();
//        //stage = new Stage();
//        shape = new ShapeRenderer();
//
        Gdx.input.setInputProcessor(stage);
//        define();
    }

    protected void define(){
        //OrthographicCamera cam = new OrthographicCamera();
        //cam.setToOrtho(false);
        //stage.setViewport(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam));

//        OrthographicCamera camera = new OrthographicCamera();
//        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

        table = new Table();

        int backButtonsSize[] = {64, topPadSize};
        int nameButtonSize[] = {Gdx.graphics.getWidth() - backButtonsSize[0]*2, backButtonsSize[1]};



        nameButton = new TextButton(name, /*GDefence.getInstance().assetLoader.getNameButtonStyle()*/GDefence.getInstance().assetLoader.getSkin(), "campainTop");
        nameButton.setSize(Gdx.graphics.getWidth()/*nameButtonSize[0]*/, nameButtonSize[1]);
        //nameButton.setPosition(0/*backButton.getX() + backButtonsSize[0]*/, Gdx.graphics.getHeight() - backButtonsSize[1]);


        table.setPosition(0, stage.getHeight() - topPadSize);
        table.setSize(stage.getWidth(), topPadSize);
        BackButton backButton = new BackButton(true);
        //table.add(backButton);//do nameButton from 0 to getWidth
        table.add(nameButton).height(topPadSize).width(Gdx.graphics.getWidth()).align(Align.center);//do without .height .width

        table.pack();
        stage.addActor(table);
        stage.addActor(backButton);




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

//    @Override
//    public void resize(int width, int height) {
////        viewport.update(width, height);
//    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(GDefence.getInstance().assetLoader.get("CampainBg.png", Texture.class), 0, 0);
        batch.end();

        stage.act(delta);
        stage.draw();
        //drawLines();
    }



    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        //dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        //shape.dispose();
    }
}
