import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;

public class RisingOne {
	final int length = (int)(Math.random() * 50) + 1;
	int yStart = 1080;
	int xStart, boomYlocation, initialSpeed, currentSpeed;
	Color c;
	boolean boom = false;
	public RisingOne(int xStart, int boomYlocation, int initialSpeed, Color c) {
		this.xStart = xStart;
		this.boomYlocation = boomYlocation;
		this.initialSpeed = initialSpeed;
		currentSpeed = initialSpeed;
		this.c = c;
	}
	public void paint(Graphics g) {
		g.setColor(c);
		g.fillRect(xStart, yStart - (length/2), 5, length);
	}
	
	public void update() {
		if( yStart <= boomYlocation)
			boom = true;
		yStart -=currentSpeed;
	}
	
}
