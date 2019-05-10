package UIComponents.Fireworks;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;

public class RisingOne {
	int length;
	int yStart = 1080;
	int xStart, boomYlocation, initialSpeed, currentSpeed;
	Color c;
	boolean boom = false;
	public RisingOne(int xStart, int boomYlocation, int initialSpeed, Color c) {
		length = (int)(Math.random() * 15) + 15;
		this.xStart = xStart;
		this.boomYlocation = boomYlocation;
		this.initialSpeed = initialSpeed;
		currentSpeed = initialSpeed;
		this.c = new Color(c.getRed(), c.getGreen(), c.getBlue(), 200);
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
