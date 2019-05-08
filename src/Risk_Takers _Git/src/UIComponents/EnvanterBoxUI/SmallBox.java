package UIComponents.EnvanterBoxUI;

import java.awt.Rectangle;

import HelperTools.PixelString.Rect;

public class SmallBox {
	int x, xTarget;
	int y, yTarget;
	int length = 15;
	int movementInBox = 3;
	int movementOnMouse = (int)(Math.random() * movementInBox* 4) + movementInBox* 3;
	int xMove = (int)(Math.random() *movementInBox) - movementInBox/2;
	int yMove = (int)(Math.random() *movementInBox) - movementInBox/2;
	public SmallBox(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public boolean isInRectangle(Rectangle rect) {
		if(y >= rect.getY() && x + length <= rect.getX() + rect.getWidth() && y + length <= rect.getY() + rect.getHeight() && x >= rect.getX())
			return true;
		return false;
	}
	public void goTarget(int xTarget, int yTarget) {
		this.xTarget = xTarget;
		this.yTarget = yTarget;
		
		if(Math.abs(x - xTarget) <= movementOnMouse) {
			x = xTarget;
		} else if(x > xTarget) {
			x -= movementOnMouse;
		}else if(x < xTarget) {
			x += movementOnMouse;
		}
		if(Math.abs(y - yTarget) <= movementOnMouse) {
			y = yTarget;
		} else if(y > yTarget) {
			y -= movementOnMouse;
		}else if(y < yTarget) {
			y += movementOnMouse;
		}
		
	}
	public void move(Rectangle rect) {
		// top
		if(y <= rect.getY()) {
			yMove = (int)(Math.random() *movementInBox);
			xMove = - (int)(Math.random() *movementInBox/2) - movementInBox/4;
		} else if(x + length >= rect.getX() + rect.getWidth()) { // right
			xMove = - (int)(Math.random() *movementInBox);
			yMove = (int)(Math.random() *movementInBox/2) - movementInBox/4;
		}else if(y + length >= rect.getY() + rect.getHeight()) { // bottom
			yMove =  - (int)(Math.random() *movementInBox);
			xMove = - (int)(Math.random() *movementInBox/2) - movementInBox/4;
		}else if(x <= rect.getX()) { // left
			xMove = (int)(Math.random() *movementInBox);
			yMove = (int)(Math.random() *movementInBox/2) - movementInBox/4;
		}
		x += xMove;
		y += yMove;
	}
	
}
