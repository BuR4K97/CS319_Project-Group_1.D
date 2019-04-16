package UIComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import AnimationComponents.AnimationHandler;
import Controller.GameController;
import Controller.GameInteractions;
import ModelClasses.Territory;

public class VisualTerritoryVisualization {
	
	private ArrayList<VisualTerritory> visualTerritories;
	private MouseInGameListener mouseTracer;
	private VisualTerritory[] focusTerritories = new VisualTerritory[GameInteractions.MAX_OPARABLE_ELEMENT];
	private VisualTerritory selectableTerritory;
	
	public boolean initialize() {
		if(GameController.activeMode == null) return false;
		
		visualTerritories = GameController.activeMode.visualTerritories;
		mouseTracer = new MouseInGameListener();
		mouseTracer.initialize();
		
		AnimationHandler.requestMapBuildingAnimation(visualTerritories);
		return true;
	}

	public void inCombatMode(Territory[] combatTerritories) {
		AnimationHandler.requestAttackAnimation(visualTerritories, findItsCorresponding(combatTerritories[0])
				, findItsCorresponding(combatTerritories[1]), combatTerritories[0], combatTerritories[1]);
	}
	
	public void outCombatMode() {
		AnimationHandler.terminateAttackAnimation();
	}
	
	public void insertMouseListeners(JPanel target) {
		target.addMouseListener(mouseTracer);
		target.addMouseMotionListener(mouseTracer);
	}
	
	private VisualTerritory checkClicked; private boolean alreadyFocused = false;
	public void update() {
		if(AnimationHandler.suspendVisualTerritoryPanel()) {
			mouseTracer.mouseReleased = false;
			mouseTracer.mousePressed = false;
			return;
		}
		
		VisualTerritory focusTerritory = mouseTracer.getFocusTerritory();
		if(mouseTracer.mouseReleased) { 
			if(checkClicked != null) {
				if(focusTerritory != checkClicked)
					popOutFocusTerritory(checkClicked);
				if(alreadyFocused)
					popOutFocusTerritory(focusTerritory);
				alreadyFocused = false;
				checkClicked = null;
			}
			mouseTracer.mouseReleased = false;
		}
		
		if(selectableTerritory != focusTerritories[0] && selectableTerritory != focusTerritories[1])
			AnimationHandler.terminateMouseOnTerritoryAnimation(selectableTerritory);
		selectableTerritory = null;
		if(focusTerritory == null) {
			mouseTracer.mousePressed = false;
			GameController.interactions.synchronizeFocusTerritories(focusTerritories[0], focusTerritories[1]);
			return;
		}
		if(!pushIntoSelectableTerritory(focusTerritory)) {
			mouseTracer.mousePressed = false; 
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
	
	public void destroy() {
		visualTerritories = null;
	}
	
	public void paint(Graphics painter) {
		AnimationHandler.visualBuffer.paint(painter);
		
		painter.setFont(new Font("pixel", Font.BOLD, 20));
		Territory corresponding;
		for(VisualTerritory currElement : visualTerritories) {
			painter.setColor(Color.WHITE);
			corresponding = GameInteractions.findCorrespondingTerritory(currElement);
			if(corresponding != null) {
				if(corresponding.getPlayer() != null)
					painter.setColor(corresponding.getPlayer().getColor());
			}
			boolean selected = false;
			if(focusTerritories[0] == currElement) { painter.setColor(colorDarkener(painter.getColor())); selected = true;}
			else if(focusTerritories[1] == currElement) { painter.setColor(colorDarkener(painter.getColor())); selected = true; }
			else if(selectableTerritory == currElement) { painter.setColor(colorLightener(painter.getColor())); selected = true; }
			currElement.paint(painter, selected);
			
			if(!AnimationHandler.suspendVisualTerritoryPanel()) {
				if(currElement.mainCoordinate != null) { 
					painter.setColor(Color.WHITE);
					painter.fillRect(currElement.mainCoordinate.xCoord - 1, currElement.mainCoordinate.yCoord - VisualTerritory.PIXEL_JUMP
							- 6, 1 * VisualTerritory.PIXEL_JUMP,  2 *VisualTerritory.PIXEL_JUMP);
					painter.setColor(Color.BLACK);
					painter.drawString("" + corresponding.getUnitNumber(), currElement.mainCoordinate.xCoord
							, currElement.mainCoordinate.yCoord);
				}
			}
		}
	}

	private static Color colorLightener(Color source) { return source.brighter(); }
	
	private static Color colorDarkener(Color source) { return source.darker(); }
	
	private boolean pushIntoFocusTerritories(VisualTerritory push) {
		if(focusTerritories[0] == null) {
			if(GameInteractions.isSelectable(push, push)) {
				AnimationHandler.requestMouseOnTerritoryAnimation(push);
				focusTerritories[0] = push;
				return true;
			}
			return false;
		}
		if(GameInteractions.isSelectable(focusTerritories[0], push)) {
			AnimationHandler.terminateMouseOnTerritoryAnimation(focusTerritories[1]);
			AnimationHandler.requestMouseOnTerritoryAnimation(push);
			focusTerritories[1] = push;
			return true;
		}
		else if(GameInteractions.isSelectable(push, push)) {
			AnimationHandler.terminateMouseOnTerritoryAnimation(focusTerritories[0]);
			AnimationHandler.requestMouseOnTerritoryAnimation(push);
			focusTerritories[0] = push;
			AnimationHandler.terminateMouseOnTerritoryAnimation(focusTerritories[1]);
			focusTerritories[1] = null;
			return true;
		}
		return false;
	}
	
	private boolean pushIntoSelectableTerritory(VisualTerritory push) {
		if(focusTerritories[0] == null) {
			if(GameInteractions.isSelectable(push, push)) {
				AnimationHandler.requestMouseOnTerritoryAnimation(push);
				selectableTerritory = push;
				return true;
			}
			return false;
		}
		if(GameInteractions.isSelectable(focusTerritories[0], push)) {
			AnimationHandler.requestMouseOnTerritoryAnimation(push);
			selectableTerritory = push;
			return true;
		}
		else if(GameInteractions.isSelectable(push, push)) {
			AnimationHandler.requestMouseOnTerritoryAnimation(push);
			selectableTerritory = push;
			return true;
		}
		return false;
	}
	
	private void popOutFocusTerritory(VisualTerritory pop) {
		if(focusTerritories[0] == pop) {
			AnimationHandler.terminateMouseOnTerritoryAnimation(focusTerritories[0]);
			focusTerritories[0] = null; 
			AnimationHandler.terminateMouseOnTerritoryAnimation(focusTerritories[1]);
			focusTerritories[1] = null;
		}
		else if(focusTerritories[1] == pop) {
			AnimationHandler.terminateMouseOnTerritoryAnimation(focusTerritories[1]);
			focusTerritories[1] = null; 
		}
	}
	
	private VisualTerritory findItsCorresponding(Territory territory) {
		for(VisualTerritory visualTerritory : visualTerritories)
			if(territory.checkItsCorresponding(visualTerritory))
				return visualTerritory;
		return null;
	}
	
	public VisualTerritory[] getFocusTerritories() {
		return focusTerritories;
	}

}
