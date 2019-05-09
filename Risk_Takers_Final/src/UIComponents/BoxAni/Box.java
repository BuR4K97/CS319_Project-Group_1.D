package UIComponents.BoxAni;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Box {
	final int yMoveRand = 6;
	final int xMoveRand = 6;
	// single
	int yMove;
	int length = 15;
	int x;

	double currMoveY;
	int boxXcurr;
	double boxYcurr;
	double gravity = 0.4;

	int xMove;
	Color color;
	boolean allowedToJump = true;
	boolean deleteThis = false;

	public Box(int xCoor, Color color) {
		this.color = color;
		xMove = xMoveRand - 2 * (int)(Math.random() * xMoveRand);
		while(Math.abs(xMove) < 3)
			xMove = xMoveRand - 2 * (int)(Math.random() * xMoveRand);
		yMove = -yMoveRand - (int)(Math.random() * yMoveRand);
		x = xCoor;
		currMoveY = yMove;
		boxXcurr = x;
		boxYcurr = JumpingBox.y - length*5 - (int)(Math.random() * yMoveRand);
	}
	public void update() {
		boxYcurr += currMoveY;
		if(allowedToJump) {
			if(boxYcurr + length> JumpingBox.y) {
				boxYcurr = JumpingBox.y - length;
				currMoveY = yMove;
			}
		} else if(boxYcurr > 1080){
			deleteThis = true;
		}
		currMoveY += gravity;
		// x move for single cube
		if(boxXcurr >= JumpingBox.leftLineX && boxXcurr + length <= JumpingBox.leftLineX + JumpingBox.lineLength) {
			boxXcurr += xMove;
			if(boxXcurr < JumpingBox.leftLineX) {
				boxXcurr = JumpingBox.leftLineX;
				xMove = (int)(Math.random() * xMoveRand) +1;
				yMove = -yMoveRand - (int)(Math.random() * yMoveRand);

			} else if(boxXcurr + length> JumpingBox.leftLineX + JumpingBox.lineLength) {
				boxXcurr = JumpingBox.leftLineX + JumpingBox.lineLength - length;
				xMove = -(int)(Math.random() * xMoveRand)-1;
				yMove = -yMoveRand - (int)(Math.random() * yMoveRand);

			}
		}
		// x move for multi cube
		if(boxXcurr >= JumpingBox.rightLineX && boxXcurr + length <= JumpingBox.rightLineX + JumpingBox.lineLength) {
			boxXcurr += xMove;
			if(boxXcurr < JumpingBox.rightLineX) {
				boxXcurr = JumpingBox.rightLineX;
				xMove = (int)(Math.random() * xMoveRand) +1;
				yMove = -yMoveRand - (int)(Math.random() * yMoveRand);

			} else if(boxXcurr + length > JumpingBox.rightLineX + JumpingBox.lineLength) {
				boxXcurr = JumpingBox.rightLineX + JumpingBox.lineLength - length;
				xMove = -(int)(Math.random() * xMoveRand)-1;
				yMove = -yMoveRand - (int)(Math.random() * yMoveRand);

			}
		}
	}
	public void paint(Graphics g) {
		// single cube
		g.setColor(color);
		g.fillRect(boxXcurr, (int)boxYcurr, length, length);
	}
}
