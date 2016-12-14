package com.darkhouse.gdefence.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Model.LevelButton;
import com.darkhouse.gdefence.Model.Panels.GemPanel;
import com.darkhouse.gdefence.Model.Panels.GoldPanel;
import com.darkhouse.gdefence.Model.Panels.UserPanel;
import com.darkhouse.gdefence.Screens.BottomPanel.BottomPanel;

public class CampainMap extends AbstractCampainScreen {
    private class PagedMap extends WidgetGroup{
        private Page[] pages;
        private int currentPage;

        private ImageButton next;
        private ImageButton prev;

        public boolean hasPrevous(){
            if(currentPage == 0) return false;
            //if locked prevous
            return true;
        }

        public boolean hasNext(){
            if(pages.length - 1 == currentPage) return false;
            if(pages[currentPage + 1].isLocked) return false;
            return true;
        }

        public PagedMap(int number) {
            currentPage = 0;
            pages = new Page[number];
            pages[0] = new Page(5, 1);
            pages[1] = new Page(4, pages[0].getLastButtonsInt());
            pages[2] = new Page(5, pages[1].getLastButtonsInt());
            addActor(pages[0]);
            addActor(pages[1]);
            addActor(pages[2]);

            next = new ImageButton(GDefence.getInstance().assetLoader.getNextButtonSkin());
            prev = new ImageButton(GDefence.getInstance().assetLoader.getPrevButtonSkin());
            next.setPosition(900, 200);
            next.addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    nextPage();
                    return true;
                }
            });
            prev.setPosition(120, 200);
            prev.addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    prevPage();
                    return true;
                }
            });

            addActor(next);
            addActor(prev);

//            next.setVisible(false);
//            prev.setVisible(false);
//
//            pages[0].setVisible(false);
//            pages[1].setVisible(false);
//            pages[2].setVisible(false);



            update();







        }
        private void nextPage(){
            if(currentPage == pages.length - 1) return;
            currentPage++;
            update();
        }
        private void prevPage(){
            if(currentPage == 0) return;
            currentPage--;
            update();
        }

        private void update(){
            for (int i = 0; i < pages.length; i++){
                if(i == currentPage) pages[i].setVisible(true);
                else pages[i].setVisible(false);
            }
            if(hasNext()) next.setVisible(true);
            else next.setVisible(false);
            if(hasPrevous()) prev.setVisible(true);
            else prev.setVisible(false);

            for (Page p:pages){//
                p.updateLockedLevels();
            }
        }
    }
    private class Page extends WidgetGroup{
        private boolean isLocked;

        private int buttons;
        private int firstButtonInt;

        public int getLastButtonsInt() {
            return firstButtonInt + buttons;
        }

        private LevelButton[] levels;

        public Page(int buttons, int firstButton) {
            this.buttons = buttons;
            this.firstButtonInt = firstButton;

            levels = new LevelButton[buttons];
            int borderSize = Gdx.graphics.getWidth()/4;
            int sizeBetween = Gdx.graphics.getWidth()/42;
            int levelButtonsSize[] = new int[2];
            levelButtonsSize[0] = (Gdx.graphics.getWidth() - (borderSize * 2 + sizeBetween * (buttons - 1))) / buttons;
            levelButtonsSize[1] = levelButtonsSize[0];

            for (int i = 0; i < buttons; i++){
                levels[i] = new LevelButton(firstButton + i);

//                if(!GDefence.getInstance().user.getLevelAvailable(firstButton + i)){
//                    levels[i].lock();
//                }
                levels[i].setSize(levelButtonsSize[0], levelButtonsSize[1]);
                levels[i].setPosition(borderSize + (levelButtonsSize[0] + sizeBetween)*i,
                        Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/8);

                addActor(levels[i]);
            }
            updateLockedLevels();

            updateLock();
        }
        public void updateLock(){
            isLocked = levels[0].isLocked;
        }

        public void updateLockedLevels(){//calling 2 times
//            updateLock();
            for (int i = 0; i < buttons; i++) {
                //levels[i] = new LevelButton(firstButtonInt + i);
                if (GDefence.getInstance().user.getLevelAvailable(firstButtonInt + i)) {
                    levels[i].unLock();
                }else {
                    levels[i].lock();
                }
            }
        }






    }
    private PagedMap pagedMap;


    //private GDefence mainClass;

    //private SpriteBatch batch;
    //private ShapeRenderer shape;

    //private Stage stage;


    public CampainMap() {
        super("Campain");
//        loadButtons();
        //batch = new SpriteBatch();
        //shape = new ShapeRenderer();
        //this.mainClass = mainClass;

    }

    public void init(){
        loadButtons();
    }

    @Override
    public void show() {
        super.show();
        update();
        //init();
        //loadFrames();
    }

    private void loadButtons(){
        pagedMap = new PagedMap(3);//
        stage.addActor(pagedMap);


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
    private void update(){
        pagedMap.update();
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
//        //batch.dispose();
//    }
}
