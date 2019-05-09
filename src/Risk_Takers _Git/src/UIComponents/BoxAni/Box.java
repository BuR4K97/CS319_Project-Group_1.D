package UIComponents.BoxAni;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Box {
	final int yMoveRand = 10;
	final int xMoveRand = 10;
	// single
	int yMove;
	int length = 15;
	int x;

	int currMoveY;
	int boxXcurr;
	int boxYcurr;
	int gravity = 1;

	int xMove;
	Color color;

	public Box(int xCoor, Color color) {
		this.color = color;
		xMove = xMoveRand - 2 * (int)(Math.random() * xMoveRand);
		while(Math.abs(xMove) < 3)
			xMove = xMoveRand - 2 * (int)(Math.random() * xMoveRand);
		yMove = -yMoveRand - (int)(Math.random() * yMoveRand);
		x = xCoor;
		currMoveY = yMove;
		boxXcurr = x;
		boxYcurr = JumpingBox.y - length - (int)(Math.random() * yMoveRand);
	}
	public void update() {
		boxYcurr += currMoveY;
		if(boxYcurr + length> JumpingBox.y) {
			boxYcurr = JumpingBox.y - length;
			currMoveY = yMove;
			//yMove = -yMoveRand - (int)(Math.random() * yMoveRand);
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
		g.fillRect(boxXcurr, boxYcurr, length, length);
	}
}
