package HelperTools;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageHandler {
	
	private BufferedImage bufferedData;
	private String fileName;
	
	public ImageHandler(String fileName) { 
		this.fileName = fileName; 
	}
	
	public boolean constructData() {
		try {
			File imageFile = new File(fileName);
			bufferedData = ImageIO.read(imageFile);
			return true;
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
			return false;
		}
	}
	
	public BufferedImage getBufferedData() { 
		return this.bufferedData; 
	}

}
