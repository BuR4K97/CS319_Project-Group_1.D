package UIComponents;

import java.io.Serializable;

public class Coordinate implements Serializable {
	
	public int xCoord;
	public int yCoord;
	
	public Coordinate(int xCoord, int yCoord){
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
}
