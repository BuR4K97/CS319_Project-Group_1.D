package UIComponents;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class VisualTerritory implements Serializable {

	public ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
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
	
	public abstract void print();
	
}
