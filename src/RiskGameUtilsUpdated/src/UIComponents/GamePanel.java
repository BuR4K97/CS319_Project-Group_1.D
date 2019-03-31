package UIComponents;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JButton;

import ModelClasses.Territory;
import Controller.GameController;
import Controller.GameInteractions;

public class GamePanel extends DynamicPanel {
	
	private ArrayList<VisualTerritory> visualTerritories;
	private MouseInGameListener mouseTracer;
	private InteractionPanel interactionPanel;
	private VisualTerritory[] focusTerritories = new VisualTerritory[GameInteractions.MAX_OPARABLE_ELEMENT];
	private VisualTerritory selectableTerritory;
	
	public boolean initialize() {
		if(GameController.activeMode == null) return false;
		
		visualTerritories = GameController.activeMode.visualTerritories;
		mouseTracer = new MouseInGameListener();
		mouseTracer.initialize();
		addMouseListener(mouseTracer);
		addMouseMotionListener(mouseTracer);
		interactionPanel = new InteractionPanel();
		interactionPanel.initialize(focusTerritories);
		add(interactionPanel);
		return true;
	}
	
	private VisualTerritory checkClicked; private boolean alreadyFocused = false;
	public void update() {
		VisualTerritory focusTerritory = mouseTracer.getFocusTerritory();
		if(mouseTracer.mouseReleased) { 
			if(checkClicked != null) {
				if(focusTerritory != checkClicked) popOutFocusTerritory(checkClicked);
				if(alreadyFocused) popOutFocusTerritory(focusTerritory);
				alreadyFocused = false;
				checkClicked = null;
			}
			mouseTracer.mouseReleased = false;
		}
		
		selectableTerritory = null;
		if(focusTerritory == null) {
			 GameController.interactions.synchronizeFocusTerritories(focusTerritories[0], focusTerritories[1]);
			 return;
		}
		if(!pushIntoSelectableTerritory(focusTerritory)) {
			 GameController.interactions.synchronizeFocusTerritories(focusTerritories[0], focusTerritories[1]);
			 return;
		}
		if(mouseTracer.mousePressed) {
			if(focusTerritories[0] == focusTerritory || focusTerritories[1] == focusTerritory) 
				alreadyFocused = true;
			else
				pushIntoFocusTerritories(focusTerritory);
			checkClicked = focusTerritory;
			mouseTracer.mousePressed = false; 
		}
		GameController.interactions.synchronizeFocusTerritories(focusTerritories[0], focusTerritories[1]);
	}
	

	public void render() {
		interactionPanel.render();
		repaint();
	}
	
	public void destroy() {
		interactionPanel.destroy();
		visualTerritories = null;
	}

	@Override
	public void paint(Graphics painter) {
		super.paint(painter);
		
		Territory corresponding;
		for(VisualTerritory currElement : visualTerritories) {
			painter.setColor(Color.WHITE);
			corresponding = GameInteractions.findCorrespondingTerritory(currElement);
			if(corresponding != null) {
				if(corresponding.getPlayer() != null)
					painter.setColor(corresponding.getPlayer().getColor());
			}
			if(focusTerritories[0] == currElement) painter.setColor(colorDarkener(painter.getColor()));
			else if(focusTerritories[1] == currElement) painter.setColor(colorDarkener(painter.getColor()));
			else if(selectableTerritory == currElement) painter.setColor(colorLightener(painter.getColor()));
			int xCoords[] = currElement.extractXCoords(), yCoords[] = currElement.extractYCoords();
			painter.fillPolygon(xCoords, yCoords, xCoords.length);
			
			painter.setColor(Color.BLACK);
			if(focusTerritories[0] == currElement) painter.setColor(Color.RED);
			else if(focusTerritories[1] == currElement) painter.setColor(Color.BLUE);
			else if(selectableTerritory == currElement) painter.setColor(Color.GREEN);
			painter.drawPolygon(xCoords, yCoords, xCoords.length); 
		}
	}

	private static Color colorLightener(Color source) { return source.brighter(); }
	
	private static Color colorDarkener(Color source) { return source.darker(); }
	
	private boolean pushIntoFocusTerritories(VisualTerritory push) {
		if(focusTerritories[0] == null) {
			if(GameInteractions.isSelectable(push, push)) {
				focusTerritories[0] = push;
				return true;
			}
			return false;
		}
		if(GameInteractions.isSelectable(focusTerritories[0], push)) {
			focusTerritories[1] = push;
			return true;
		}
		else if(GameInteractions.isSelectable(push, push)) {
			focusTerritories[0] = push;
			focusTerritories[1] = null;
			return true;
		}
		return false;
	}
	
	private boolean pushIntoSelectableTerritory(VisualTerritory push) {
		if(focusTerritories[0] == null) {
			if(GameInteractions.isSelectable(push, push)) {
				selectableTerritory = push;
				return true;
			}
			return false;
		}
		if(GameInteractions.isSelectable(focusTerritories[0], push)) {
			selectableTerritory = push;
			return true;
		}
		else if(GameInteractions.isSelectable(push, push)) {
			selectableTerritory = push;
			return true;
		}
		return false;
	}
	
	private void popOutFocusTerritory(VisualTerritory pop) {
		if(focusTerritories[0] == pop) {
			focusTerritories[0] = null; 
			focusTerritories[1] = null;
		}
		else if(focusTerritories[1] == pop)
			focusTerritories[1] = null;
	}
	
}
