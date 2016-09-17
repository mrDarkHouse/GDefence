package com.darkhouse.gdefence.Model.Cells;


import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.darkhouse.gdefence.Helpers.AssetLoader;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Objects.GameObject;
import com.darkhouse.gdefence.Objects.TowerObject;

public class TowerCell extends ObjectCell{
    private Tower tower;

    public TowerCell(Tower tower) {
        super();
        this.tower = tower;
        addObject(tower);
        setObject();
    }

//    public TowerCell() {
//        super();
//    }

//    @Override
//    public boolean addObject(GameObject type) {
//        boolean b = super.addObject(type);
//
//    }



    public boolean addObject(Tower type) {
        if(this.type == null){
            this.type = type;
            number = 1;
            return true;
        }else if(this.type == type){
            number++;
            return true;
        }else {
            return false;
        }
    }

    @Override
    protected void setObject() {
        object = new ImageButton(AssetLoader.getTowerCellSkin(tower));


        super.setObject();
    }
}
