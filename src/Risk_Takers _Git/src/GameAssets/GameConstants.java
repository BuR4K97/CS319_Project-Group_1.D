package GameAssets;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

import HelperTools.ImageHandler;

public class GameConstants {
	
	private static final String PIXEL_FONT_FILENAME = "GameAssets\\pixel.ttf";
	public static Color[] PLAYER_COLORS = { new Color(0, 128, 0), new Color(148, 0, 211), new Color(255, 255, 0)
			, new Color(255, 140, 0)};
	
	private static Font pixelFont;
	
	public static void loadGameConstants() {
		try {
			pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File(PIXEL_FONT_FILENAME));
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(pixelFont);
		} 
		catch (FontFormatException | IOException e) { 
			e.printStackTrace();
		}
	}
	
	public static void destroyGameConstants() {
		pixelFont = null;
	}

}
