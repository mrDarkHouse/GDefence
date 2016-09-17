package com.darkhouse.gdefence.Model.Cells;


import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.darkhouse.gdefence.Objects.GameObject;

public abstract class ObjectCell extends Cell {
    protected ImageButton object;
    protected GameObject type;

    public GameObject getType() {
        return type;
    }

    protected int number;


    public ObjectCell() {
        init();
    }

    private void init(){

    }

//    public boolean addObject(GameObject type){
//        if(this.type == null){
//            this.type = type;
//            number = 1;
//            return true;
//        }else if(this.type == type){
//            number++;
//            return true;
//        }else {
//            return false;
//        }
//    }
    //abstract public boolean addObject(GameObject type);

    protected void setObject(){

        setActor(object);
    }





}
