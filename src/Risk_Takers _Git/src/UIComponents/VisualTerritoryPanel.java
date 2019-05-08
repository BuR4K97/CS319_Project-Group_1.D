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
import ModelClasses.Turn.TURN_PHASE;

public class VisualTerritoryPanel {
	
	private ArrayList<VisualTerritory> visualTerritories;
	private MouseInGameListener mouseTracer;
	private VisualTerritory[] focusTerritories = new VisualTerritory[GameInteractions.MAX_OPARABLE_ELEMENT];
	private VisualTerritory selectableTerritory;
	
	
	public boolean initialize(MouseInGameListener mouseTracer) {
		if(GameController.activeMode == null) return false;
		
		visualTerritories = GameController.activeMode.visualTerritories;
		this.mouseTracer = mouseTracer;
		
		AnimationHandler.requestMapBuildingAnimation(visualTerritories);
		return true;
	}

	public void inCombatMode(Territory[] combatTerritories) {
		AnimationHandler.requestAttackAnimation(visualTerritories, GameInteractions
				.findCorrespondingVisualTerritory(combatTerritories[0]), GameInteractions
				.findCorrespondingVisualTerritory(combatTerritories[1]), combatTerritories[0], combatTerritories[1]);
	}
	
	public void outCombatMode() {
		AnimationHandler.terminateAttackAnimation();
	}
	
	public void insertMouseListeners(JPanel target) {
		target.addMouseListener(mouseTracer);
		target.addMouseMotionListener(mouseTracer);
	}
	
	public void update() {
		mouseEventUpdate();
		GameController.interactions.synchronizeFocusTerritories(focusTerritories[0], focusTerritories[1]);
		if(GameInteractions.getActivePhase() != TURN_PHASE.ATTACK)
			flushPrevState();
	}
	
	public void flushPrevState() {
		popOutFocusTerritory(focusTerritories[0]);
	}
	
	public void destroy() {
		visualTerritories = null;
	}
	
	ArrayList<VisualTerritory> effectedTerritories = new ArrayList<>();
	ArrayList<Integer> effectAmounts = new ArrayList<>();
	public void requestFortifyInteractionEffect(VisualTerritory focusTerritory, int effectAmount) {
		effectedTerritories.add(focusTerritory);
		effectAmounts.add(effectAmount);
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
					if(effectedTerritories.contains(currElement)) {
						painter.drawString(Integer.toString(corresponding.getUnitNumber()
								- effectAmounts.get(effectedTerritories.indexOf(currElement)))
								, currElement.mainCoordinate.xCoord, currElement.mainCoordinate.yCoord);
						effectAmounts.remove(effectedTerritories.indexOf(currElement));
						effectedTerritories.remove(currElement);
					}
					else
						painter.drawString(Integer.toString(corresponding.getUnitNumber())
								, currElement.mainCoordinate.xCoord, currElement.mainCoordinate.yCoord);
				}
			}
		}
	}

	private static Color colorLightener(Color source) { return source.brighter(); }
	
	private static Color colorDarkener(Color source) { return source.darker(); }
	
	private VisualTerritory checkClicked; private boolean alreadyFocused = false;
	private void mouseEventUpdate() {
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
		}
		
		if(selectableTerritory != focusTerritories[0] && selectableTerritory != focusTerritories[1])
			AnimationHandler.terminateMouseOnTerritoryAnimation(selectableTerritory);
		selectableTerritory = null;
		
		if(focusTerritory == null)  return;
		if(!pushIntoSelectableTerritories(focusTerritory)) return;
		if(mouseTracer.mousePressed) {
			if(focusTerritories[0] == focusTerritory || focusTerritories[1] == focusTerritory) 
				alreadyFocused = true;
			else
				pushIntoFocusTerritories(focusTerritory);
			checkClicked = focusTerritory; 
		}
	}
	
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
	
	private boolean pushIntoSelectableTerritories(VisualTerritory push) {
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
			if(selectableTerritory != focusTerritories[0])
				AnimationHandler.terminateMouseOnTerritoryAnimation(focusTerritories[0]);
			focusTerritories[0] = null;
			if(selectableTerritory != focusTerritories[1])
				AnimationHandler.terminateMouseOnTerritoryAnimation(focusTerritories[1]);
			focusTerritories[1] = null;
		}
		else if(focusTerritories[1] == pop) {
			if(selectableTerritory != focusTerritories[1])
				AnimationHandler.terminateMouseOnTerritoryAnimation(focusTerritories[1]);
			focusTerritories[1] = null; 
		}
	}
	
	public VisualTerritory[] getFocusTerritories() {
		return focusTerritories;
	}

}
