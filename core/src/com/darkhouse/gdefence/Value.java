package com.darkhouse.gdefence;

import javax.swing.*;
import java.awt.*;

public class Value {
//    public static boolean isDebug = false;
//
//    public static int groundGrass = 0;
//
//    public static int groundRoad = 1;
//
//    public static int airAir = -1;
//    //public static int airCloud = 0;
//    public static int airCastle = 15;
//    public static int airCancel = 16;
//    //public static int towerBasic = 3;

    public static int[] needExp2Lvl = {50,100,150,200,250,300,350,400,450,500,550,600,900,1200,2400,5000,20000,1000000};

//    public static int mobAir = -1;
    //public static int mobSlime = 0;


//    public static class Towers{
//        //public static TowerType[] towersID = {TowerType.Basic, TowerType.Rocky, TowerType.Arrow, TowerType.Range};
//
//
//
////        public static enum Class{
////            BASIC(Basic), ROCKY;
////        }
//
//
//
//        public static class Basic{
//            public static String name = "Basic";
//            public static int Dmg = 1;
//            public static int Range = 60;
//            public static int As = 300;
//            public static int ID = 0;
//            public static int Cost = 10;
//            public static Image texture = new ImageIcon("src/ru/res/Tower/basicTower.png").getImage();
//            public static Image projectile = new ImageIcon("src/ru/res/bullet.png").getImage();
//        }
//
//        public static class Rocky{
//            public static String name = "Rocky";
//            public static int Dmg = 3;
//            public static int Range = 75;
//            public static int As = 600;
//            public static int ID = 1;
//            public static int Cost = 25;
//            public static Image texture = new ImageIcon("src/ru/res/Tower/rockTower.png").getImage();
//            public static Image projectile = new ImageIcon("src/ru/res/bullet.png").getImage();
//        }
//
//        public static class Arrow{
//            public static String name = "Arrow";
//            public static int Dmg = 1;
//            public static int Range = 75;
//            public static int As = 200;
//            public static int ID = 2;
//            public static int Cost = 30;
//            public static Image texture = new ImageIcon("src/ru/res/Tower/arrowTower.png").getImage();
//            public static Image projectile = new ImageIcon("src/ru/res/bullet.png").getImage();
//        }
//
//        public static class Range{
//            public static String name = "Range";
//            public static int Dmg = 1;
//            public static int Range = 140;
//            public static int As = 200;
//            public static int ID = 3;
//            public static int Cost = 45;
//            public static Image texture = new ImageIcon("src/ru/res/Tower/rangeTower.png").getImage();
//            public static Image projectile = new ImageIcon("src/ru/res/bullet.png").getImage();
//        }
//
//    }

//    public static class Enemies{
//        public static Class[] enemiesID = {Slime.class, Dog.class, KingSlime.class};
//
//
//
//
//        public static class Slime{
//            public static String name = "Slime";
//            public static int dmg = 1;
//            public static int HP = 10;
//            public static int speed = 3;//offset
//            public static int bounty = 1;
//            public static int ID = 0;
//        }
//
//        public static class Dog{
//            public static String name = "Dog";
//            public static int dmg = 2;
//            public static int HP = 5;
//            public static int speed = 3;
//            public static int bounty = 3;
//            public static int ID = 1;
//        }
//
//        public static class KingSlime{
//            public static String name = "King Slime";
//            public static int dmg = 5;
//            public static int HP = 25;
//            public static int speed = 1;
//            public static int bounty = 10;
//            public static int ID = 2;
//
//        }
//
//
//
//    }
//
//    public static class Levels{
//        public static class lvl1{
//            public static int numberWaves = 5;
//            public static int timeBetweenWaves = 5000;
//            public static int startCoins = 80;//50
//            public static int startHP = 20;
//            public static int expFromLvl = 20;
//            public static int goldFromLvl = 200;
//
//
//
//            public static class Waves{
//
//                public static class wave1 {
//                    public static int numberMobs = 2;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 0;
//                }
//
//                public static class wave2 {
//                    public static int numberMobs = 3;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 1;
//                }
//
//                public static class wave3 {
//                    public static int numberMobs = 4;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 0;
//                }
//
//                public static class wave4 {
//                    public static int numberMobs = 6;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 0;
//                }
//
//                public static class wave5 {
//                    public static int numberMobs = 1;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 2;
//                }
//
//            }
//        }
//
//        public static class lvl2{
//            public static int numberWaves = 5;
//            public static int timeBetweenWaves = 5000;
//            public static int startCoins = 70;
//            public static int startHP = 25;
//            public static int expFromLvl = 25;
//            public static int goldFromLvl = 300;
//
//            public static class Waves{
//
//                public static class wave1 {
//                    public static int numberMobs = 2;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 1;
//                }
//
//                public static class wave2 {
//                    public static int numberMobs = 3;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 1;
//                }
//
//                public static class wave3 {
//                    public static int numberMobs = 4;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 0;
//                }
//
//                public static class wave4 {
//                    public static int numberMobs = 6;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 0;
//                }
//
//                public static class wave5 {
//                    public static int numberMobs = 1;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 0;
//                }
//
//            }
//        }
//
//        public static class lvl3{
//            public static int numberWaves = 5;
//            public static int timeBetweenWaves = 5000;
//            public static int startCoins = 50;
//            public static int startHP = 20;
//            public static int expFromLvl = 30;
//            public static int goldFromLvl = 40;
//
//            public static class Waves{
//
//                public static class wave1 {
//                    public static int numberMobs = 3;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 1;
//                }
//
//                public static class wave2 {
//                    public static int numberMobs = 3;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 1;
//                }
//
//                public static class wave3 {
//                    public static int numberMobs = 8;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 0;
//                }
//
//                public static class wave4 {
//                    public static int numberMobs = 5;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 1;
//                }
//
//                public static class wave5 {
//                    public static int numberMobs = 15;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 0;
//                }
//
//            }
//        }
//
//        public static class lvl4{
//            public static int numberWaves = 5;
//            public static int timeBetweenWaves = 5000;
//            public static int startCoins = 50;
//            public static int startHP = 20;
//            public static int expFromLvl = 35;
//            public static int goldFromLvl = 45;
//
//            public static class Waves{
//
//                public static class wave1 {
//                    public static int numberMobs = 2;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 0;
//                }
//
//                public static class wave2 {
//                    public static int numberMobs = 3;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 1;
//                }
//
//                public static class wave3 {
//                    public static int numberMobs = 4;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 0;
//                }
//
//                public static class wave4 {
//                    public static int numberMobs = 6;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 0;
//                }
//
//                public static class wave5 {
//                    public static int numberMobs = 1;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 0;
//                }
//
//            }
//        }
//
//        public static class lvl5{
//            public static int numberWaves = 5;
//            public static int timeBetweenWaves = 5000;
//            public static int startCoins = 50;
//            public static int startHP = 20;
//            public static int expFromLvl = 50;
//            public static int goldFromLvl = 75;
//
//            public static class Waves{
//
//                public static class wave1 {
//                    public static int numberMobs = 2;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 0;
//                }
//
//                public static class wave2 {
//                    public static int numberMobs = 3;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 1;
//                }
//
//                public static class wave3 {
//                    public static int numberMobs = 4;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 0;
//                }
//
//                public static class wave4 {
//                    public static int numberMobs = 6;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 0;
//                }
//
//                public static class wave5 {
//                    public static int numberMobs = 1;
//                    public static int timeSpawn = 500;
//                    public static int mobID = 0;
//                }
//
//            }
//        }
//
//    }
//
//    public static class Campains{
//        public static int levelsNumber[] = {5, 10, 15};
//    }
//
}
