package UIComponents;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import Controller.GameController;

public class MouseInGameListener implements MouseMotionListener, MouseListener {

	private Coordinate mousePosition;
	private PixelMap pixelMap;
	private ArrayList<VisualTerritory> visualTerritories;
	
	public boolean initialize() {
		if(GameController.activeMode == null) return false;
		
		mousePosition = new Coordinate(0, 0);
		pixelMap = GameController.activeMode.pixelMap;
		visualTerritories = GameController.activeMode.visualTerritories;
		return true;
	}
	
	public VisualTerritory getFocusTerritory() {
		Color focusColor = pixelMap.getPixelColor(mousePosition.xCoord, mousePosition.yCoord);
		if(focusColor == null) return null;
		
		for(VisualTerritory currElement : visualTerritories) 
			if(currElement.color.equals(focusColor)) return currElement;
		return null;
	}
	
	public boolean mousePressed;
	public boolean mouseReleased;
	
	@Override
	public void mouseDragged(MouseEvent e) {}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mousePosition.xCoord = e.getX();
		mousePosition.yCoord = e.getY();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	
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
