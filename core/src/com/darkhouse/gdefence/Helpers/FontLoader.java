package com.darkhouse.gdefence.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class FontLoader {
    private static FreeTypeFontGenerator generator;

    final static String font_chars_ru =
            "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";


    public static BitmapFont impact12;//refactor
    public static BitmapFont impact14;
    public static BitmapFont impact16;
    public static BitmapFont impact18;
    public static BitmapFont impact20;
    public static BitmapFont impact22;
    public static BitmapFont impact24;
    public static BitmapFont impact26;
    public static BitmapFont impact28;
    public static BitmapFont impact36;

    public static BitmapFont impact_Ru;

    public static final Color RED =   new Color(0xff0000ff);
    public static final Color YELLOW= new Color(0xffff00ff);
    public static final Color BLUE =  new Color(0x0000ffff);
    public static final Color BLACK = new Color(0x00000000);
    public static final Color GREEN = new Color(0x009900ff);
    public static final Color WHITE = new Color(0x66ffffff);


    public static String firstCapitalLetter(String s){
        return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    }
    public static String firstLowerLetter(String s){
        return s.substring(0, 1).toLowerCase() + s.substring(1).replaceAll(" ", "");
    }

    public static String colorCode(int id){
        switch (id){
            case 0:return "[#000000ff]";
            case 1:return "[#0ffe00ff]";//green
            case 2:return "[#00ffffff]";
            case 3:return "[#64A619ff]";
            case 4:return "[#CD6600ff]";
            case 5:return "[#8B0000ff]";
            case 6:return "[#4169E1ff]";//royalBlue
            case 7:return "[#CCCC00ff]";//darkYellow
            case 8:return "[#00886Bff]";//observatory green
            case 9:return "[#F09135ff]";//orange
            case 10:return "[#000033ff]";//Obsidian
            case 11:return "[#009900ff]";//Emerald
            case 12:return "[#66ffffff]";//Diamond
            default:return "";
        }
    }
    public static String colorCode(Color c){
        return "[#" + c.toString() + "]";
    }
    public static String colorString(String s, int id){
        return colorCode(id) + s + "[]";
    }






    public FontLoader() {
    }

    public static void load(){
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Impact.ttf"));


        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 26;
        impact_Ru = generator.generateFont(parameter);

        parameter.size = 12;
        impact12 = generator.generateFont(parameter);
        parameter.size = 14;
        impact14 = generator.generateFont(parameter);
        parameter.size = 16;
        impact16 = generator.generateFont(parameter);
        parameter.size = 18;
        impact18 = generator.generateFont(parameter);
        parameter.size = 20;
        impact20 = generator.generateFont(parameter);
        parameter.size = 22;
        impact22 = generator.generateFont(parameter);
        parameter.size = 24;
        impact24 = generator.generateFont(parameter);
        parameter.size = 26;
        impact26 = generator.generateFont(parameter);
        parameter.size = 28;
        impact28 = generator.generateFont(parameter);
        parameter.size = 36;
        impact36 = generator.generateFont(parameter);



    }
    public static Label.LabelStyle generateStyle(int type, int size, Color fontColor){
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = generateFont(type, size, Color.WHITE);
        style.fontColor = fontColor;
        return style;
    }
    public static Label.LabelStyle generateStyle(int type, int size, Color fontColor, int borderSize, Color borderColor){
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = generateFont(type, size, Color.WHITE, borderSize, borderColor);
        style.fontColor = fontColor;
        return style;
    }
//    public static Label.LabelStyle generateSecondaryStyle(int size, Color fontColor){
//        Label.LabelStyle style = new Label.LabelStyle();
//        style.font = generateSecondaryFont(size, Color.WHITE);
//        style.fontColor = fontColor;
//        return style;
//    }
//    public static Label.LabelStyle generateRobotoStyle(int size, Color fontColor){
//        Label.LabelStyle style = new Label.LabelStyle();
//        style.font = generateSecondaryFont(size, Color.WHITE);
//        style.fontColor = fontColor;
//        return style;
//    }

//    public static Label.LabelStyle generateIskoolaFont(int size, Color color, int borderSize, Color borderColor){
//
//    }


    private static BitmapFont generateDefaultBorderFont(int size, Color color, int borderSize, Color borderColor, FileHandle f){
        generator = new FreeTypeFontGenerator(f);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = font_chars_ru;
        parameter.size = size;
        parameter.color = color;
        parameter.borderWidth = borderSize;
        parameter.borderColor = borderColor;
        return generator.generateFont(parameter);
    }

    public static BitmapFont generateFont(int type, int size, Color color, int borderSize, Color borderColor){
        FileHandle f;
        switch (type){
            case 0:f = Gdx.files.internal("Fonts/Impact.ttf");
                break;
            case 1:f = Gdx.files.internal("Fonts/roboto-regular.ttf");
                break;
            case 2:f = Gdx.files.internal("Fonts/Roboto-Medium.ttf");
                break;
            case 3:f = Gdx.files.internal("Fonts/11434.ttf");
                break;
            default:throw new IllegalArgumentException("cant find font with type " + type);
        }
        return generateDefaultBorderFont(size, color, borderSize, borderColor, f);
    }
//    public static BitmapFont generateSecondaryFont(int size, Color color, int borderSize, Color borderColor){
//        return generateDefaultBorderFont(size, color, borderSize, borderColor, Gdx.files.internal("Fonts/roboto-regular.ttf"));
//    }
//    public static BitmapFont generateRobotoMedium(int size, Color color, int borderSize, Color borderColor){
//        return generateDefaultBorderFont(size, color, borderSize, borderColor, Gdx.files.internal("Fonts/Roboto-Medium.ttf"));
//    }

    private static BitmapFont generateDefaultFont(int size, Color color, FileHandle f){
        generator = new FreeTypeFontGenerator(f);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = font_chars_ru;
        parameter.size = size;
        parameter.color = color;
        return generator.generateFont(parameter);
    }

    public static BitmapFont generateFont(int type, int size, Color color){
        FileHandle f;
        switch (type){
            case 0:f = Gdx.files.internal("Fonts/Impact.ttf");
                break;
            case 1:f = Gdx.files.internal("Fonts/roboto-regular.ttf");
                break;
            case 2:f = Gdx.files.internal("Fonts/Roboto-Medium.ttf");
                break;
            default:throw new IllegalArgumentException("cant find font with type " + type);
        }
        return generateDefaultFont(size, color, f);
    }
//    public static BitmapFont generateSecondaryFont(int size, Color color){
//
//        return generateDefaultFont(size, color, Gdx.files.internal("Fonts/roboto-regular.ttf"));
//    }
//    public static BitmapFont generateRobotoMedium(int size, Color color){
//
//        return generateDefaultFont(size, color, Gdx.files.internal("Fonts/Roboto-Medium.ttf"));
//    }

    public static String getOneColorButtonString(int index, String s, Color first, Color other){
//        BitmapFont b1 = generateFont(size);
//        b1.getData().markupEnabled = true;
//        Label.LabelStyle style = new Label.LabelStyle(b1, null);
//        style.fontColor = other;
        return ("[#" + other.toString() + "]" + s.substring(0, index) + "[#" + first.toString() + "]" +
                s.substring(index, index + 1) + "[#" + other.toString() + "]" + s.substring(index + 1));
    }
    public static String getOneColorButtonString(int index, String s, int first, int other){
//        BitmapFont b1 = generateFont(size);
//        b1.getData().markupEnabled = true;
//        Label.LabelStyle style = new Label.LabelStyle(b1, null);
//        style.fontColor = other;
        return (colorCode(other) + s.substring(0, index)  + colorCode(first) +
                s.substring(index, index + 1) + colorCode(other) +  s.substring(index + 1));
    }

//    public static BitmapFont generateMenuFont(int size, Color color){
//        generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Iscoola.ttf"));
//        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//        parameter.size = size;
//        parameter.color = color;
//        return  generator.generateFont(parameter);
//    }




    public static void dispose(){
        generator.dispose();
    }
}
