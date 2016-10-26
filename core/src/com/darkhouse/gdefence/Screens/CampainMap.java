package com.darkhouse.gdefence.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Model.LevelButton;
import com.darkhouse.gdefence.Model.Panels.GemPanel;
import com.darkhouse.gdefence.Model.Panels.GoldPanel;
import com.darkhouse.gdefence.Model.Panels.UserPanel;
import com.darkhouse.gdefence.Screens.BottomPanel.BottomPanel;

public class CampainMap extends AbstractCampainScreen {
    private class PagedMap{
        private Page[] pages;
        private int currentPage;
        public boolean isPrevous(){
            if(currentPage == 1) return false;
            //if locked prevous
            return true;
        }

        public boolean isNext(){
            if(pages.length == currentPage) return false;
            if(pages[currentPage + 1].isLocked) return false;
            return true;
        }

        public PagedMap(int number) {
            pages = new Page[number];
            pages[0] = new Page(5);
            pages[1] = new Page(6);
            pages[2] = new Page(7);


            currentPage = 0;


        }






    }




    private class Page extends WidgetGroup{
        private boolean isLocked;

        private LevelButton[] levels;

        public Page(int buttons) {
            levels = new LevelButton[buttons];
            int borderSize = Gdx.graphics.getWidth()/4;
            int sizeBetween = Gdx.graphics.getWidth()/42;
            int levelButtonsSize[] = new int[2];
            levelButtonsSize[0] = (Gdx.graphics.getWidth() - (borderSize * 2 + sizeBetween * (buttons - 1))) / buttons;
            levelButtonsSize[1] = levelButtonsSize[0];

            for (int i = 0; i < buttons; i++){
                levels[i] = new LevelButton(i + 1);
                if(!GDefence.getInstance().user.getLevelAvailable(i)){
                    levels[i].lock();
                }
                levels[i].setSize(levelButtonsSize[0], levelButtonsSize[1]);
                levels[i].setPosition(borderSize + (levelButtonsSize[0] + sizeBetween)*i, Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/8);

                stage.addActor(levels[i]);
            }

            updateLock();
        }
        public void updateLock(){
            isLocked = levels[levels.length - 1].isLocked;
        }




    }
    private PagedMap pagedMap;


    //private GDefence mainClass;

    //private SpriteBatch batch;
    //private ShapeRenderer shape;

    //private Stage stage;


    public CampainMap() {
        super("Campain");
        //batch = new SpriteBatch();
        //shape = new ShapeRenderer();
        //this.mainClass = mainClass;

    }

    @Override
    public void show() {
        super.show();
        loadButtons();
        loadFrames();
    }

    private void loadButtons(){
        pagedMap = new PagedMap(3);//


        //Viewport viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //stage = new Stage(viewport,batch);
        //Gdx.input.setInputProcessor(stage);

        int userPanelSize[] = {240, 120};
        int goldPanelSize[] = {60, userPanelSize[1]};
        stage.addActor(new GoldPanel(Gdx.graphics.getWidth() - userPanelSize[0] - goldPanelSize[0], Gdx.graphics.getHeight() - userPanelSize[1] -
                topPadSize, goldPanelSize[0], goldPanelSize[1]));
        stage.addActor(new UserPanel(Gdx.graphics.getWidth() - userPanelSize[0], Gdx.graphics.getHeight() - userPanelSize[1] - topPadSize,
                userPanelSize[0], userPanelSize[1]));
        //System.out.println(Gdx.graphics.getHeight() - userPanelSize[1] - topPadSize);
        stage.addActor(new GemPanel(30, 460, 250, 180));


        stage.addActor(new BottomPanel());






    }


    private void loadFrames(){
        //Gdx.gl.glLineWidth(2);
        //ShapeRenderer r = new ShapeRenderer();
        //r.line();

    }



    @Override
    public void render(float delta) {
        super.render(delta);

        //drawLines();
    }
    private void drawLines(){
        int lineWidth = 6;      //KOSTIl'
        Gdx.gl.glLineWidth(lineWidth);
        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.setProjectionMatrix(batch.getProjectionMatrix());
        shape.setColor(0, 0, 0, 1);
        shape.line(0, Gdx.graphics.getHeight()/5 + lineWidth, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/5 + lineWidth);
        shape.line(Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/5 + lineWidth, Gdx.graphics.getWidth()/5 , 0);
        shape.line(Gdx.graphics.getWidth()/5 * 2, Gdx.graphics.getHeight()/5 + lineWidth, Gdx.graphics.getWidth()/5 * 2 , 0);
        shape.line(Gdx.graphics.getWidth()/5 * 3, Gdx.graphics.getHeight()/5 + lineWidth, Gdx.graphics.getWidth()/5 * 3, 0);
        shape.line(Gdx.graphics.getWidth() / 5 * 4, Gdx.graphics.getHeight() / 5 + lineWidth, Gdx.graphics.getWidth() / 5 * 4, 0);

        shape.end();
    }




    @Override
    public void resize(int width, int height) {

    }

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

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }
}
