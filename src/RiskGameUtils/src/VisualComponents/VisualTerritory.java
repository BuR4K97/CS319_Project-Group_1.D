package VisualComponents;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class VisualTerritory implements Serializable {

	public ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
	public ArrayList<Coordinate> border = new ArrayList<Coordinate>();
	public Coordinate mainCoordinate;
	public Color color;
	
	public abstract void print();
	
}
