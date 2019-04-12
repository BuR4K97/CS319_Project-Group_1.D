package UIComponents;

import java.io.Serializable;

public class Coordinate implements Serializable {
	
	public static final Coordinate ORIGIN_POINT = new Coordinate(0, 0);
	
	public int xCoord;
	public int yCoord;
	public int visualBufferXCoordIndex, visualBufferYCoordIndex;
	
	public Coordinate(int xCoord, int yCoord){
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
	public void setCoord(int x, int y) {
		xCoord = x;
		yCoord = y;
	}
	
	public boolean approachCoordinate(Coordinate target, int amount) { // alretation true
		if(target == null) return false;
		
		int distanceX = Math.abs(  xCoord - target.xCoord );
		int distanceY = Math.abs(  yCoord - target.yCoord );
		if( distanceX <= amount * 2 )
			xCoord = target.xCoord;
		else if(xCoord < target.xCoord)
			xCoord += amount * 2;
		else if(xCoord > target.xCoord)
			xCoord -= amount * 2;
		
		if( distanceY <= amount )
			yCoord = target.yCoord;
		else if(yCoord < target.yCoord)
			yCoord += amount;
		else if(yCoord > target.yCoord)
			yCoord -= amount;
		
		if(distanceX == 0 && distanceY == 0)
			return false;
		return true;
	} 

	
	public int findDistance(Coordinate other) {
		return (int)(Math.pow(other.xCoord - xCoord, 2) + Math.pow(other.yCoord - yCoord, 2));
	}
	
	public String toString() {
		return "<" + xCoord + ", " + yCoord + ">";
	}
	
}
