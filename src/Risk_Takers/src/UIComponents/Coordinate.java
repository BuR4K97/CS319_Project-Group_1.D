package UIComponents;

import java.io.Serializable;

public class Coordinate implements Serializable {
	
	public int xCoord;
	public int yCoord;
	
	public Coordinate(int xCoord, int yCoord){
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
	public int findDistance(Coordinate other) {
		return (int)(Math.pow(other.xCoord - xCoord, 2) + Math.pow(other.yCoord - yCoord, 2));
	}
	
}
