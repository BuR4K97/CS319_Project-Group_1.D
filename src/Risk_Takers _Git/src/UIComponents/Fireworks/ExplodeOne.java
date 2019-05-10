package UIComponents.Fireworks;

import java.awt.Color;
import java.awt.Graphics;

public class ExplodeOne {
	int borderLength;
	int xMovement, yMovement, xCenter, yCenter;
	int yMovementChanges = 1;
	Color c;
	int opacity = 200;
	int opacityChanges = 3;
	boolean done = false;
	public ExplodeOne(int xCenter, int yCenter, int xMovement, int yMovement, int borderLength, Color c) {
		this.xCenter = xCenter;
		this.yCenter = yCenter;
		this.xMovement = xMovement;
		this.yMovement = yMovement;
		this.borderLength = borderLength;
		this.c = new Color(c.getRed(), c.getGreen(), c.getBlue(), opacity);
	}
	public void paint(Graphics g) {
		g.setColor(c);
		g.fillRect(xCenter - (borderLength/2), yCenter - (borderLength/2), borderLength, borderLength);
	}
	public void update() {
		xCenter += xMovement;
		yCenter += yMovement;
		if(opacity >= opacityChanges * 2 )
			opacity -= opacityChanges;
		else
			done = true;
		if(Math.random()*10 < 4)
			yMovement+= yMovementChanges;
		borderLength -= borderLength / (opacity / opacityChanges);
		//borderLength -= borderLength / (25);
		c = new Color(c.getRed(), c.getGreen(), c.getBlue(), opacity);
	}
}
