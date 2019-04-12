package GameAssets;

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
	
	private static final String BACKGROUND_TEXTURE_FILENAME = "constants\\OldMapTexture.jpeg";
	private static final String PIXEL_FONT_FILENAME = "constants\\pixel.ttf";
	
	private static Font pixelFont;
	public static BufferedImage backgroundTexture;
	
	public static void loadGameConstants() {
		ImageHandler imageBuffer = new ImageHandler(BACKGROUND_TEXTURE_FILENAME);
		imageBuffer.constructData();
		backgroundTexture = imageBuffer.getBufferedData();
		
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
		backgroundTexture = null;
	}

}
