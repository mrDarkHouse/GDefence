package com.darkhouse.gdefence.Level;


import com.badlogic.gdx.Gdx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MapLoader {
    private File loadFile;

    public MapLoader(int map) {
        try{
            loadFile = new File("Maps/Map" + map + ".gdm");
        }catch (Exception e){
            Gdx.app.log("Error", e.toString());
        }

    }

    public MapTile[][] loadMap(){
        try {
            MapTile mapTile[][];
            int sizeX, sizeY;
            Scanner loadScanner = new Scanner(loadFile);
            sizeX = loadScanner.nextInt();
            sizeY = loadScanner.nextInt();
            mapTile = new MapTile[sizeX][sizeY];
            while(loadScanner.hasNext()){
                for (int x = 0; x < sizeX; x++){
                    for (int y = 0; y < sizeY; y++){
                        mapTile[x][y] = new MapTile(MapTile.getTypeById(loadScanner.nextInt()));
                    }
                }
            }


            loadScanner.close();
            return mapTile;


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
