package Controller;

import java.util.ArrayList;

import AnimationComponents.AnimationHandler;
import ModelClasses.Card;
import ModelClasses.Combat;
import ModelClasses.Game;
import ModelClasses.Player;
import ModelClasses.Territory;
import ModelClasses.Turn;
import ModelClasses.Turn.TURN_PHASE;
import UIComponents.GamePanel;
import UIComponents.VisualTerritory;

public class GameInteractions {
	
	public static final int MAX_OPARABLE_ELEMENT = 2;
	
	private boolean nextPhaseRequest = false;
	private boolean actionRequest = false; 
	private int actionAmount = 0; 
	private Territory focusTerritories[] = new Territory[MAX_OPARABLE_ELEMENT];
	private boolean combatActive;
	private boolean attackPerRoll = false;
	private boolean attackTillCapture = false;
	private Combat activeCombat;
	
	public void requestNextPhase() {
		nextPhaseRequest = true;
	}
	
	public void requestAction(int actionAmount) {
		this.actionAmount = actionAmount;
		actionRequest = true;
	}
	
	public void requestAttackPerRoll() {
		if(combatActive)
			attackPerRoll = true;
	}
	
	public void requestAttackTillCapture() {
		if(combatActive)
			attackTillCapture = true;
	}
	
	public void activateCombat(Combat activated) {
		if(activated != null) {
			activeCombat = activated;
			combatActive = true;
			((GamePanel)MainApplication.frame.focusPanel).initializeAttackScenario(activeCombat.getCombatTerritories());
		}
	}
	
	public void terminateCombat() {
		activeCombat = null;
		combatActive = false;
		((GamePanel)MainApplication.frame.focusPanel).terminateAttackScenario();
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
	
	public int getActionAmount() { 
		return this.actionAmount;
	}
	
	public boolean getNextPhaseRequest() { 
		if(nextPhaseRequest) {
			nextPhaseRequest = false;
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
	
	public boolean getAttackPerRoll() { 
		if(attackPerRoll) {
			attackPerRoll = false;
			return true;
		}
		return false;
	}
	
	public boolean getAttackTillCapture() { 
		if(attackTillCapture) {
			attackTillCapture = false;
			return true;
		}
		return false;
	}
	
	public boolean getCombatActive() {
		return combatActive;
	}
	
	public Combat getActiveCombat() {
		return activeCombat;
	}
	
	public TURN_PHASE getActivePhase() {
		return Turn.activePhase;
	}
	
	public Player getActivePlayer() {
		return Turn.activePlayer;
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
	
	public static boolean requestCardActivation(ArrayList<Card> activates) {
		return Game.activateCards(activates);
	}
}
