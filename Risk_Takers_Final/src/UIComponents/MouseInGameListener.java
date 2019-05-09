package UIComponents;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import Controller.GameController;

public class MouseInGameListener implements MouseMotionListener, MouseListener {

	public Coordinate mousePosition;
	
	public boolean initialize() {
		if(GameController.activeMode == null) return false;
		
		mousePosition = new Coordinate(0, 0);
		pixelMap = GameController.activeMode.pixelMap;
		visualTerritories = GameController.activeMode.visualTerritories;
		return true;
	}
	
	private PixelMap pixelMap;
	private ArrayList<VisualTerritory> visualTerritories;
	public VisualTerritory getFocusTerritory() {
		Color focusColor = pixelMap.getPixelColor(mousePosition.xCoord, mousePosition.yCoord);
		if(focusColor == null) return null;
		
		for(VisualTerritory currElement : visualTerritories) 
			if(currElement.color.equals(focusColor)) return currElement;
		return null;
	}
	
	public boolean mousePressed;
	public boolean mouseReleased;
	public boolean mouseClicked;
	public boolean leftButtonClicked;
	public boolean rightButtonClicked;
	
	@Override
	public void mouseDragged(MouseEvent e) {}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mousePosition.xCoord = e.getX();
		mousePosition.yCoord = e.getY();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)) {
			leftButtonClicked = true;
			rightButtonClicked = false;
		}
		else {
			leftButtonClicked = false;
			rightButtonClicked = true;
		}
		mouseClicked = true;
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mousePressed(MouseEvent e) {
		mousePressed = true;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		mouseReleased = true;
	}
	
}
