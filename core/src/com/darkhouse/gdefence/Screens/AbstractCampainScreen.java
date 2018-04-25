package com.darkhouse.gdefence.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
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
    private Texture background;

    public void setBackground(Texture background) {
        this.background = background;
//        this.bg = background.getTextureData().consumePixmap();
    }

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

    private Table table;


    public AbstractCampainScreen(String name) {
        super();
        setBackground(GDefence.getInstance().assetLoader.get("CampainBg.png", Texture.class));
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
        //stage.setViewport(new ExtendViewport(GDefence.WIDTH, GDefence.HEIGHT, cam));

//        OrthographicCamera camera = new OrthographicCamera();
//        viewport = new ExtendViewport(GDefence.WIDTH, GDefence.HEIGHT, camera);

        table = new Table();

        int backButtonsSize[] = {64, topPadSize};
        int nameButtonSize[] = {GDefence.WIDTH - backButtonsSize[0]*2, backButtonsSize[1]};



        nameButton = new TextButton(name, /*GDefence.getInstance().assetLoader.getNameButtonStyle()*/GDefence.getInstance().assetLoader.getSkin(), "campainTop");
        nameButton.setSize(GDefence.WIDTH/*nameButtonSize[0]*/, nameButtonSize[1]);
        //nameButton.setPosition(0/*backButton.getX() + backButtonsSize[0]*/, GDefence.HEIGHT - backButtonsSize[1]);


        table.setPosition(0, stage.getHeight() - topPadSize);
        table.setSize(stage.getWidth(), topPadSize);
        BackButton backButton = new BackButton(true);
        //table.add(backButton);//do nameButton from 0 to getWidth
        table.add(nameButton).height(topPadSize).width(GDefence.WIDTH).align(Align.center);//do without .height .width

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
        shape.line(0, GDefence.HEIGHT - topPadSize, GDefence.WIDTH, GDefence.HEIGHT - topPadSize);
        shape.line(0, GDefence.HEIGHT - lineWidth/2, GDefence.WIDTH, GDefence.HEIGHT - lineWidth/2);
        shape.line(topPadSize, GDefence.HEIGHT, topPadSize, GDefence.HEIGHT - topPadSize);
        shape.line(lineWidth/2, GDefence.HEIGHT, lineWidth/2, GDefence.HEIGHT - topPadSize);
        shape.line(GDefence.WIDTH - lineWidth/2, GDefence.HEIGHT, GDefence.WIDTH - lineWidth/2, GDefence.HEIGHT - topPadSize);
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
//        bg.draw(batch, 255f);
        batch.draw(background, 0, 0, getStage().getWidth(), getStage().getHeight());
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
