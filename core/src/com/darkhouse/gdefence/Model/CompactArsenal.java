package com.darkhouse.gdefence.Model;


import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.darkhouse.gdefence.GDefence;
import com.darkhouse.gdefence.Level.Tower.Tower;
import com.darkhouse.gdefence.Model.Cells.ObjectCell;
import com.darkhouse.gdefence.Model.Cells.TowerCell;

import java.lang.reflect.Array;

public class CompactArsenal extends Table{
    private TowerCell[][] cells;

    private int vertical;
    private int horizontal;

    public CompactArsenal(int vertical, int horizontal) {
        this.vertical = vertical;
        this.horizontal = horizontal;
        cells = new TowerCell[vertical][horizontal];
        initCells();
    }

    private void initCells(){
        for(int i = 0; i < vertical; i++){
            for(int j = 0; j < horizontal; j++){
                cells[i][j] = new TowerCell();
            }
        }
    }








}
