package com.darkhouse.gdefence.Level.Loader;


import com.badlogic.gdx.Gdx;
import com.darkhouse.gdefence.Level.MapTile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MapLoader {
    private File loadFile;

    private int spawnersNumber;

    public int getSpawnersNumber() {
        return spawnersNumber;
    }

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
            //while(loadScanner.hasNext()){
            for (int y = 0; y < sizeY; y++){
                for (int x = 0; x < sizeX; x++){
                    mapTile[x][y] = new MapTile(MapTile.getTypeById(loadScanner.nextInt()));
                    //System.out.println(MapTile.getTypeById(loadScanner.nextInt()));
                }
            }
            for (int y = 0; y < sizeY; y++){
                for (int x = 0; x < sizeX; x++){
                    mapTile[x][y].setLogic(MapTile.getLogicById(loadScanner.nextInt()));

                    if(mapTile[x][y].getLogic() == MapTile.TileLogic.spawnerR ||
                       mapTile[x][y].getLogic() == MapTile.TileLogic.spawnerL ||
                       mapTile[x][y].getLogic() == MapTile.TileLogic.spawnerU ||
                       mapTile[x][y].getLogic() == MapTile.TileLogic.spawnerD){
                        spawnersNumber++;
                    }
                }
            }

            //System.out.println(mapTile[4][9].getLogic());

            //}


            loadScanner.close();
            return mapTile;


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
