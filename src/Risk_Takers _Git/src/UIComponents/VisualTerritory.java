package UIComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class VisualTerritory implements Serializable {

	public ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
	public Coordinate[][] visualBuffer;
	public ArrayList<Coordinate> border = new ArrayList<Coordinate>();
	public Coordinate mainCoordinate;
	public Color color;
	
	public int[] extractXCoords() {
		int[] xCoords = new int[border.size()];
		for(int i = 0; i < border.size(); i++)
			xCoords[i] = border.get(i).xCoord;
		return xCoords;
	}
	
	public int[] extractYCoords() {
		int[] yCoords = new int[border.size()];
		for(int i = 0; i < border.size(); i++)
			yCoords[i] = border.get(i).yCoord;
		return yCoords;
	}
	
	//private boolean even = true; int changeIndex = 0;
	public static final int PIXEL_JUMP = 12;
	public static final int DEFAULT_DRAW_SIZE = PIXEL_JUMP * 7 / 10;
	public int drawSize = DEFAULT_DRAW_SIZE;
	public void paint(Graphics painter, boolean selected) {
		for(Coordinate a: coordinates)
			painter.fillRect(a.xCoord , a.yCoord, drawSize, drawSize);
	}
	
	public static Coordinate getIndexedCoordinate(double xRate, double yRate) {
		int xBound = (int)(xRate * ApplicationFrame.width);
		int yBound =  (int)(yRate * ApplicationFrame.height);
		
		int currX = 0, currY = 0;
		while(currX < xBound) 
			currX += PIXEL_JUMP;
		while(currY < yBound) 
			currY += PIXEL_JUMP;
		return new Coordinate(currX, currY);
	}
	
	public static boolean checkPixelJumpHierarchy(Coordinate check) {
		return check.xCoord % 12 == 0 && check.yCoord % 12 == 0;
	}

	
	public abstract VisualTerritory copy();
	public abstract void print();
	
}
