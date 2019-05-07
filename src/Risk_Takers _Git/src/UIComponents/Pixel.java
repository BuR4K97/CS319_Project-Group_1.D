package UIComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import GameAssets.DefaultRiskMode.DefaultRiskMode;
import HelperTools.FileHandler;

public class Pixel{
	int red = 255;
	int green= 0;
	int blue = 0;
	int counterForSea = 0;
	int mouseX = 0;
	int mouseY = 0;
	ArrayList<SimpleCoordinate> list = new ArrayList<SimpleCoordinate>();
	ArrayList<SimpleCoordinate> listCopy = new ArrayList<SimpleCoordinate>();
	ArrayList<SimpleCoordinate> listSea = new ArrayList<SimpleCoordinate>();
	ArrayList<Circle> listCircle = new ArrayList<Circle>();
	int x = 9;
	int y = x;
	int jump = 12;  // 12
	Color c = new Color(0,0,0);
	public static Timer seaMouse;
	public Pixel() {
		// file
		BufferedImage image = null;
		FileHandler mapFile = new FileHandler(DefaultRiskMode.VISUAL_DATA_FILENAME);
		ArrayList<Serializable> objects = mapFile.loadDataFromFile();
		ArrayList<VisualTerritory> visualTerritories = new ArrayList<VisualTerritory>();
		for(Serializable currElement : objects)
			visualTerritories.add((VisualTerritory)currElement);
		
		for(VisualTerritory vt : visualTerritories) {
			if(vt.checkItsCorresponding(DefaultRiskMode.TERRITORIES.SEA.toString()))
				for(Coordinate c : vt.coordinates)
					listSea.add(new SimpleCoordinate(c.xCoord, c.yCoord));
			else 
				for(Coordinate c : vt.coordinates) {
					list.add(new SimpleCoordinate(c.xCoord, c.yCoord));
					listCopy.add(new SimpleCoordinate(c.xCoord, c.yCoord));
				}
		}
		
		// timer for circle and sea
		Timer seaUpdater = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < listCircle.size(); i++) {
					listCircle.get(i).update();
					if(listCircle.get(i).done)
						listCircle.remove(i);
				}
				if(Math.random()* 50 < 1)
					listCircle.add(new Circle( (int)(Math.random()* 1920), (int)(Math.random()* 1080)));
			}
		});
		seaUpdater.start();
		// timer for draw sea using mouse
		seaMouse = new Timer(50, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listCircle.add(new Circle( mouseX, mouseY));
			}
		});
		// timer for diassemble
		Timer di = new Timer(15, new ActionListener() {
			int movement = 10;
			public void actionPerformed(ActionEvent e) {
				// random
				if((int)(Math.random() * 3) == 0) {
					list.get((int)(Math.random() * list.size())).move = true;
				}
				for(int i = 0;i < list.size(); i++) {
					if(list.get(i).move) {
						list.get(i).y = list.get(i).y + movement;
						if(list.get(i).y >= 1080) {
							list.remove(i);
							if(list.size() == 0)
								list = getCopyList(listCopy);
						}
					}
				}
			}
		});
		di.start();
		// timer for territories
		Timer t = new Timer(15, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(red == 255 && green == 0 && blue >= 0 && blue < 255) {
					blue++;
				} else if(red > 0 && red <= 255 && green == 0 && blue == 255) {
					red--;
				} else if(red == 0 && green >= 0 && green < 255 && blue == 255) {
					green++;
				} else if(red == 0 && green == 255 && blue > 0 && blue <= 255) {
					blue--;
				} else if(red >= 0 && red < 255 && green == 255 && blue == 0) {
					red++;
				} else if(red == 255 && green > 0 && green <= 255 && blue == 0) {
					green--;
				}
			}
		});
		t.start();
	}
	public void startDraggedMouse(int mouseX, int mouseY) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		seaMouse.start();
	}
	public void addCircle(int mouseX, int mouseY) {
		listCircle.add(new Circle( mouseX, mouseY));
	}
	public void restart() {
		list = getCopyList(listCopy);
	}
	// draw
	public void paint(Graphics g) {
		for(int i = 0;i < list.size(); i++) { // - or +
			g.setColor(new Color(  Math.abs(red - list.get(i).x/ jump) % 255, 
					Math.abs(green - list.get(i).x/ jump) % 255, Math.abs(blue - list.get(i).x/ jump) % 255));
			g.fillRect(list.get(i).x, list.get(i).y, x, y);
		}
		for(int i = 0;i < listSea.size(); i++) {
			for(int j = 0;j < listCircle.size(); j++)
				if(listCircle.get(j).isInCircle(listSea.get(i))) {
					g.setColor(new Color(50, 170, 250,listCircle.get(j).seaOpacity ));
					//g.setColor(new Color(255, 255, 255,listCircle.get(j).seaOpacity ));
					g.drawOval(listSea.get(i).x, listSea.get(i).y, x, y);
				}
		}
	}
	public static ArrayList<SimpleCoordinate> getCopyList(ArrayList<SimpleCoordinate> list) {
		ArrayList<SimpleCoordinate> newl = new ArrayList<SimpleCoordinate>();
		for(int i = 0; i < list.size(); i++) {
			newl.add(new SimpleCoordinate(list.get(i).x, list.get(i).y));
		}
		return newl;
	}
}