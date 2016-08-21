package com.darkhouse.gdefence.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontLoader {
    private static FreeTypeFontGenerator generator;

    public static BitmapFont impact12;
    public static BitmapFont impact14;
    public static BitmapFont impact16;
    public static BitmapFont impact18;
    public static BitmapFont impact20;
    public static BitmapFont impact22;
    public static BitmapFont impact24;
    public static BitmapFont impact26;
    public static BitmapFont impact28;





    public FontLoader() {
    }

    public static void load(){
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Impact.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
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



    }



    public static void dispose(){
        generator.dispose();
    }
}
