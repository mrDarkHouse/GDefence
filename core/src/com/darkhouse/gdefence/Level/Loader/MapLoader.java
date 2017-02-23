package com.darkhouse.gdefence.Level.Loader;


import com.badlogic.gdx.Gdx;
import com.darkhouse.gdefence.Level.Path.MapTile;
import com.darkhouse.gdefence.Level.Path.Spawn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;


public class MapLoader {
    private File loadFile;

    private int spawnersNumber;

    public int getSpawnersNumber() {
        return spawnersNumber;
    }

    public MapLoader(int map) {
        try{
            loadFile = new File("Maps/Map" + map + ".properties");
        }catch (Exception e){
            Gdx.app.log("Error", e.toString());
        }

    }

    public MapTile[][] loadMap(){
        try {
            Properties prop = new Properties();
            FileInputStream fs = new FileInputStream(loadFile);
            prop.load(fs);

            String[] sizeStr = prop.getProperty("mapSize").split(":");
            int size[] = {Integer.parseInt(sizeStr[0]), Integer.parseInt(sizeStr[1])};
            MapTile mapTile[][] = new MapTile[size[0]][size[1]];



            String mapCode = prop.getProperty("blocks");
            String[] blocksY = mapCode.split("/");
            String[][] blocks = new String[size[0]][size[1]];

            for(int i = 0; i < blocksY.length; i++){
                String[] tmp = blocksY[i].split("-");
                for (int j = 0; j < tmp.length; j++){
                    blocks[j][i] = tmp[j];
                }
            }
            //
//

//            for (int y = 0; y < mapTile[0].length; y++){
//                for (int x = 0; x < mapTile.length; x++){
//                    blocks[x][y] = blocksY[x].split("-");
//                }
//            }
            for (int y = 0; y < mapTile[0].length; y++){//.lenght
                for (int x = 0; x < mapTile.length; x++){
                    mapTile[x][y] = MapTile.generateTile(blocks[x][y]);
                    if(mapTile[x][y] instanceof Spawn) spawnersNumber++;
                }
            }



//            int sizeX, sizeY;
//            Scanner loadScanner = new Scanner(loadFile);
//            sizeX = loadScanner.nextInt();
//            sizeY = loadScanner.nextInt();
//            mapTile = new MapTile[sizeX][sizeY];


            //while(loadScanner.hasNext()){
//            for (int y = 0; y < sizeY; y++){
//                for (int x = 0; x < sizeX; x++){
//                    mapTile[x][y] = new MapTile(MapTile.getTypeById(loadScanner.nextInt()));
//                    //System.out.println(MapTile.getTypeById(loadScanner.nextInt()));
//                }
//            }
//            for (int y = 0; y < sizeY; y++){
//                for (int x = 0; x < sizeX; x++){
//                    mapTile[x][y].setLogic(MapTile.getLogicById(loadScanner.nextInt()));
//
//                    if(mapTile[x][y].getLogic() == MapTile.TileLogic.spawnerR ||
//                       mapTile[x][y].getLogic() == MapTile.TileLogic.spawnerL ||
//                       mapTile[x][y].getLogic() == MapTile.TileLogic.spawnerU ||
//                       mapTile[x][y].getLogic() == MapTile.TileLogic.spawnerD){
//                        spawnersNumber++;
//                    }
//                }
//            }

            //System.out.println(mapTile[4][9].getLogic());

            //}


//            loadScanner.close();
            return mapTile;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
