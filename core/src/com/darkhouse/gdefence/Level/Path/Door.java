package com.darkhouse.gdefence.Level.Path;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.darkhouse.gdefence.Level.Mob.Mob;
import com.darkhouse.gdefence.Level.Mob.Way;

public class Door extends Bridge{
    private InputListener listener;

    public Door(Way inputWay, Way endWay1, Way endWay2, TargetType applyMobs) {
        super(inputWay, endWay1, endWay2, applyMobs, 1);

//        listener = new InputListener(){
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
////                System.out.println("clicked " + button);
//                switchTurn();
//                return true;
//            }
//        };
//        addListener(listener);
//        System.out.println(getListeners() + " " + getListeners().size);
    }

//    public void initTexture(){
//        super.initTexture();
//
//    }
//    public void init(Stage stage){
//        addListener(listener);
//        System.out.println(getListeners() + " " + getListeners().size);
//    }



    @Override
    public Way manipulatePath(Mob.MoveType enterMobType, Way currentWay) {
        return calculateWay();
    }
}
