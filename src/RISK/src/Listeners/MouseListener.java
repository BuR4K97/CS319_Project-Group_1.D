package Listeners;

import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener{
	int x;
	int y;
	boolean mouseClicked = false;
	boolean mousePressed = false;
	boolean mouseEntered = false;
	public void mouseClicked(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
		mousePressed = true;
		x = e.getX();
		y = e.getY();
	}
	public void mouseReleased(MouseEvent e) {	
		mousePressed = false;
	}
	public void mouseEntered(MouseEvent e) {
		mouseEntered = true;
	}
	public void mouseExited(MouseEvent e) {
		mouseEntered = false;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public boolean getMousePressed() {
		return mousePressed;
	}
	
}
