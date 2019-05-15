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
		
		for(VisualTerritory vt : visualTerritories)
			effectAmounts.add(0);
		
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
	
	public void update() {
		flushSelectableTerritory();
		if(!suspendMouseEventUpdate()) { 
			mouseEventUpdate();
			GameController.interactions.synchronizeFocusTerritories(focusTerritories[0], focusTerritories[1]); 
		}
	}
	
	public void flushPrevState() {
		popOutFocusTerritory(focusTerritories[0]);
		flushSelectableTerritory();
	}
	
	private void flushSelectableTerritory() {
		if(selectableTerritory != focusTerritories[0] && selectableTerritory != focusTerritories[1])
			AnimationHandler.terminateMouseOnTerritoryAnimation(selectableTerritory);
		selectableTerritory = null;
	}
	
	public void destroy() {
		visualTerritories = null;
	}
	
	ArrayList<Integer> effectAmounts = new ArrayList<>();
	public void requestVisualDeviationEffect(VisualTerritory focusTerritory, int effectAmount) {
		effectAmounts.set(visualTerritories.indexOf(focusTerritory), effectAmount);
	}
	
	public void paint(Graphics painter) {
		AnimationHandler.visualBuffer.paint(painter);
		int defaultFontSize = 16;
		
		Territory corresponding;
		for(int i = 0; i < visualTerritories.size(); i++) {
			painter.setColor(Color.WHITE);
			corresponding = GameInteractions.findCorrespondingTerritory(visualTerritories.get(i));
			if(corresponding != null) {
				if(corresponding.getPlayer() != null)
					painter.setColor(corresponding.getPlayer().getColor());
			}
			boolean selected = false;
			if(focusTerritories[0] == visualTerritories.get(i)) { painter.setColor(colorDarkener(painter.getColor())); selected = true;}
			else if(focusTerritories[1] == visualTerritories.get(i)) { painter.setColor(colorDarkener(painter.getColor())); selected = true; }
			else if(selectableTerritory == visualTerritories.get(i)) { painter.setColor(colorLightener(painter.getColor())); selected = true; }
			visualTerritories.get(i).paint(painter, selected);
			
			if(!AnimationHandler.suspendVisualTerritoryPanel()) {
				Coordinate mainCoord = visualTerritories.get(i).mainCoordinate;
				if(visualTerritories.get(i).mainCoordinate != null) {
					VisualTerritory.applyPixelJumpHierarchy(mainCoord);
					painter.setColor(Color.WHITE);
					int pixelGap = VisualTerritory.PIXEL_JUMP - visualTerritories.get(i).drawSize;
					int fontSize = (2 - pixelGap) + defaultFontSize;
					painter.setFont(new Font("pixel", Font.BOLD, fontSize));
					int xCoordOffset = 1;
					if(corresponding.getUnitNumber() - effectAmounts.get(i) < 10)
						painter.fillRect(mainCoord.xCoord, mainCoord.yCoord, VisualTerritory.PIXEL_JUMP - pixelGap
								,  2*VisualTerritory.PIXEL_JUMP - pixelGap);
					else {
						painter.fillRect(mainCoord.xCoord, mainCoord.yCoord, 2*VisualTerritory.PIXEL_JUMP - pixelGap
								,  2*VisualTerritory.PIXEL_JUMP - pixelGap);
						xCoordOffset += 1;
					}
					painter.setColor(Color.BLACK);
					painter.drawString(Integer.toString(corresponding.getUnitNumber() - effectAmounts.get(i))
							, mainCoord.xCoord + xCoordOffset + (2 - pixelGap) / 2, mainCoord.yCoord - (2 - pixelGap) / 2 
							+ VisualTerritory.PIXEL_JUMP - pixelGap + fontSize / 2);
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
		
		if(focusTerritory == null)  return;
		if(!pushIntoSelectableTerritory(focusTerritory)) return;
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
			if(GameInteractions.isSelectable(push, null, 0)) {
				AnimationHandler.requestMouseOnTerritoryAnimation(push);
				focusTerritories[0] = push;
				return true;
			}
			return false;
		}
		if(GameInteractions.isSelectable(focusTerritories[0], push, 1)) {
			AnimationHandler.terminateMouseOnTerritoryAnimation(focusTerritories[1]);
			AnimationHandler.requestMouseOnTerritoryAnimation(push);
			focusTerritories[1] = push;
			return true;
		}
		else if(GameInteractions.isSelectable(push, null, 0)) {
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
			if(GameInteractions.isSelectable(push, null, 0)) {
				AnimationHandler.requestMouseOnTerritoryAnimation(push);
				selectableTerritory = push;
				return true;
			}
			return false;
		}
		if(GameInteractions.isSelectable(focusTerritories[0], push, 1)) {
			AnimationHandler.requestMouseOnTerritoryAnimation(push);
			selectableTerritory = push;
			return true;
		}
		else if(GameInteractions.isSelectable(push, null, 0)) {
			AnimationHandler.requestMouseOnTerritoryAnimation(push);
			selectableTerritory = push;
			return true;
		}
		return false;
	}
	
	private boolean suspendMouseEventUpdate() {
		return GameInteractions.getActivePhase() != TURN_PHASE.ATTACK;
	}
	
	public void requestPushIntoSelectableTerritory(VisualTerritory push) {
		AnimationHandler.requestMouseOnTerritoryAnimation(push);
		selectableTerritory = push;
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
