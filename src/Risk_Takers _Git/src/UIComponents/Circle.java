package UIComponents;

import java.awt.Graphics;

public class Circle {
	int xCenter, yCenter;
	int maxRadius = 150;
	public boolean done = false;
	int currentRadius = 0;
	int radiusDifference = 20;
	public int seaOpacity = 255;
	int seaOpacityDecrement = 2;
	public Circle(int xCenter, int yCenter) {
		this.xCenter = xCenter;
		this.yCenter = yCenter;
	}
	public void update() {
		currentRadius++;
		seaOpacity-= seaOpacityDecrement;
		if(seaOpacity <= seaOpacityDecrement)
			done = true;
	}
	public boolean isInCircle(SimpleCoordinate c) {
		if (Math.sqrt(Math.pow(c.x - xCenter, 2) + Math.pow(c.y - yCenter, 2)) <= currentRadius) {	
			if(currentRadius >= radiusDifference) {
				if (Math.sqrt(Math.pow(c.x - xCenter, 2) + Math.pow(c.y - yCenter, 2)) > currentRadius - radiusDifference) {
					return true;
				}
				else {
					return false;
					//return true;
				}
			} else {
				return true;
			}
		}
		return false;
	}
	public void paint(Graphics g) {
		g.drawOval(xCenter - (currentRadius ), yCenter - (currentRadius ), currentRadius* 2, currentRadius* 2);
	}
}
