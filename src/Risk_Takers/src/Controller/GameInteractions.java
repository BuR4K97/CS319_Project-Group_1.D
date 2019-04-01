package Controller;

import ModelClasses.Game;
import ModelClasses.Territory;
import UIComponents.VisualTerritory;

public class GameInteractions {
	
	public static final int MAX_OPARABLE_ELEMENT = 2;
	
	private boolean actionRequest = false; 
	private boolean actionInStages = false; 
	private int actionAmount = 0; 
	private Territory focusTerritories[] = new Territory[MAX_OPARABLE_ELEMENT];
	private boolean nextPhaseRequest = false;
	
	public void requestNextPhase() {
		nextPhaseRequest = true;
	}
	
	public void requestAction(int actionAmount) {
		this.actionAmount = actionAmount;
		actionRequest = true;
	}
	
	public void requestActionInStages(int actionAmount) {
		this.actionAmount = actionAmount;
		actionInStages = true;
		actionRequest = true;
	}
	
	public void synchronizeFocusTerritories(VisualTerritory source, VisualTerritory target) {
		Territory sourceTerritory = Game.findCorrespondingTerritory(source);
		focusTerritories[0] = sourceTerritory;
		Territory targetTerritory = Game.findCorrespondingTerritory(target);
		focusTerritories[1] = targetTerritory;
	}
	
	public Territory[] getFocusTerritories() {
		return focusTerritories;
	}
	
	public int getActionAmount() { return this.actionAmount; }
	public boolean getActionInStages() { 
		if(actionInStages) {
			actionInStages = false;
			return true;
		}
		return false;
	}
	
	public boolean getActionRequest() { 
		if(actionRequest) {
			actionRequest = false;
			return true;
		}
		return false;
	}
	
	public boolean getNextPhaseRequest() { 
		if(nextPhaseRequest) {
			nextPhaseRequest = false;
			return true;
		}
		return false;
	}
	
	public static Territory findCorrespondingTerritory(VisualTerritory visualTerritory) {
		return Game.findCorrespondingTerritory(visualTerritory);
	}
	
	public static boolean isSelectable(VisualTerritory source, VisualTerritory target) {
		Territory sourceTerritory = Game.findCorrespondingTerritory(source);
		if(sourceTerritory == null) return false;
		Territory targetTerritory = Game.findCorrespondingTerritory(target);
		if(targetTerritory == null) return false;
		
		return Game.isSelectable(sourceTerritory, targetTerritory);
	}
}
