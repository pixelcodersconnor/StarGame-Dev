package uk.connorwright.StarGame;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.util.ArrayList;

public class Fonts {

	private static ArrayList<Fonts> fontList = new ArrayList<Fonts>();

	private static String fontPath;

	public Fonts(String filePath){
		// filePath = SpaceBang.ttf
		// Game.FONT_LOCATION = "/font"
		Fonts.fontPath = filePath + Game.FONT_LOCATION;
		registerFont();
	}

	private void registerFont(){
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		try{
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)));
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void addFont(Fonts font){
		fontList.add(font);
	}
}
