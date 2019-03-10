package Visual;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Listeners.*;

public class Panel extends JPanel{
	
	BufferedImage colorMap;
	MouseListener mouseListener;
	public Panel() {
		mouseListener = new MouseListener();
		this.addMouseListener(mouseListener);
		try {
			File file = new File("C:\\Users\\USER_NAME\\Desktop\\Risk.png");
			colorMap = ImageIO.read(file);
		} catch(IOException e) {}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	
		g.drawImage(colorMap, 0, 0, this);
		if(mouseListener.getMousePressed())
		System.out.println(colorMap.getRGB(mouseListener.getX(), mouseListener.getY()));
	}
	
}
