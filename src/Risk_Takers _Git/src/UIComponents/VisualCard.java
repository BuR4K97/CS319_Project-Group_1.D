package UIComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import GameAssets.DefaultRiskMode.DefaultRiskMode;
import HelperTools.PixelString;
import ModelClasses.Card;

public class VisualCard {
	int x;
	int y;
	int oneCoorMovement = 5;
	public static int width = 200;
	public static int height = 275;
	public static int numberY = 25;
	public DefaultRiskMode.TERRITORIES territory;
	public String name;
	public int unitNumber;
	public int level; // 1 2 3 4
	public Color color;
	PixelString pixelString, pixelUnit;
	Timer t;
	ArrayList<Coordinate> list = new ArrayList<Coordinate>();
	BufferedImage plane;
	
	public VisualCard(DefaultRiskMode.TERRITORIES territory, int x, int y, int level) {
		try {
			plane = ImageIO.read(new File("GameAssets//DefaultRiskMode//CardSymbols//PixelizedPlane.jpg"));
			for(int i = 0; i < plane.getWidth();i++) {
				for(int j = 0; j < plane.getHeight();j++) {
					if(plane.getRGB(i, j) == Color.BLACK.getRGB())
						plane.setRGB(i, j, Color.WHITE.getRGB());
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.x = x;
		this.y = y;

		this.territory = territory;

		name = territory.toString();
		unitNumber = 5;   // TO BE CHANGED !!!!!!!!!!!!!!
		this.level = level;
		// COLOR
		if(level == 1) {
			color = Color.GREEN;
		}else if (level == 2) {
			color = Color.BLUE;
		}else if (level == 3) {
			color = new Color(102, 0, 53);
		}else if(level == 4) {
			color = Color.YELLOW;
		}else {
			color = Color.WHITE;
		}

		pixelString = new PixelString(x+10, y+10, 3, name, Color.BLACK);
		pixelUnit = new PixelString(x+100, y+10 + numberY, 15, Integer.toString(unitNumber), Color.BLACK);
		t = new Timer(8, new ActionListener() {
			int a = 0;
			public void actionPerformed(ActionEvent e) {
				if(list.size() < 82 && a > 10) {
					list.add(new Coordinate(x, y));
					a = 0;
				}
				a++;
				for(int i = 0; i < list.size(); i++) {
					if(list.get(i).xCoord < x + width && list.get(i).yCoord == y)
						list.get(i).xCoord++;
					else if(list.get(i).xCoord >= x + width && list.get(i).yCoord < y + height)
						list.get(i).yCoord++;
					else if(list.get(i).xCoord > x && list.get(i).yCoord >= y + height)
						list.get(i).xCoord--;
					else if(list.get(i).xCoord <= x && list.get(i).yCoord > y)
						list.get(i).yCoord--;
				}
			}
		});
		t.start();

	}

	public void paint(Graphics g) {
		
		// stroke
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(level*2));
		// COLOR
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(x, y, width, height);
		g.drawImage(plane, x + level*4, (int)(y + (5.0/6.0) * height) - level*4, x + width/4 + level*4, y + height - level*4, 0, 0, plane.getWidth(), plane.getHeight(), null);
		g2d.setColor(color);
		// border
		g2d.drawRect(x, y, width, height);
		for(int i = 0; i < list.size(); i++) {
			g2d.fillRect(list.get(i).xCoord, list.get(i).yCoord, 5, 5);
		}
		
		g2d.setColor(Color.WHITE);
		//g2d.drawRect(x, y, width, numberY);
		pixelString.paint(g);
		pixelUnit.paint(g);
		
	}
}