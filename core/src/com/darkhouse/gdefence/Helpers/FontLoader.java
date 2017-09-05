package com.darkhouse.gdefence.Helpers;

import com.badlogic.gdx.Gdx;
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


    public static String firstCapitalLetter(String s){
        return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
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
    public static Label.LabelStyle generateStyle(int size, Color fontColor){
        Label.LabelStyle style = new Label.LabelStyle();
//        generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Impact.ttf"));
//        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//        parameter.size = size;
        style.font = generateFont(size, Color.WHITE);
                //generator.generateFont(parameter);
        style.fontColor = fontColor;
        return style;
    }

    public static String getOneColorButtonString(int index, String s, Color first, Color other){
//        BitmapFont b1 = generateFont(size);
//        b1.getData().markupEnabled = true;
//        Label.LabelStyle style = new Label.LabelStyle(b1, null);
//        style.fontColor = other;
        return ("[#" + other.toString() + "]" + s.substring(0, index) + "[#" + first.toString() + "]" +
                s.substring(index, index + 1) + "[#" + other.toString() + "]" + s.substring(index + 1));
    }

    public static BitmapFont generateFont(int size, Color color, int borderSize, Color borderColor){
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Impact.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;
        parameter.borderWidth = borderSize;
        parameter.borderColor = borderColor;
        return generator.generateFont(parameter);
    }
    public static BitmapFont generateFont(int size, Color color){
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Impact.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = font_chars_ru;
        parameter.size = size;
        parameter.color = color;
        return generator.generateFont(parameter);
    }
    public static BitmapFont generateSecondaryFont(int size, Color color){
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/roboto-regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = font_chars_ru;
        parameter.size = size;
        parameter.color = color;
        return generator.generateFont(parameter);
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
