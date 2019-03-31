package UIComponents;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class PixelMap {

	public BufferedImage imageBuffer;
	
	public Color getPixelColor(int xCoord, int yCoord) { 
		if(xCoord < 0 || xCoord > imageBuffer.getWidth()) return null;
		if(yCoord < 0 || yCoord > imageBuffer.getHeight()) return null;
		return new Color(imageBuffer.getRGB(xCoord, yCoord)); 
	}
	
}
