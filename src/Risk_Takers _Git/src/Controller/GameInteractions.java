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
import UIComponents.VisualCard;
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
	private boolean textualPanelUpdateRequest = false;
	private boolean visualCardPanelUpdateRequest = false;

	
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
	
	public void requestTextualPanelUpdateRequest() {
		textualPanelUpdateRequest  = true;
	}
	
	public void requestVisualCardPanelUpdateRequest() {
		visualCardPanelUpdateRequest  = true;
	}
	
	public static void requestAttackButtonState(boolean activate) {
		((GamePanel)MainApplication.frame.focusPanel).requestAttackButtonState(activate);
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
		if(source != null) {
			Territory sourceTerritory = GameController.activeMode.findItsTerritoryCorresponding(source.getCorrespondingTag());
			focusTerritories[0] = sourceTerritory;
		}
		else { focusTerritories[0] = null; }
		if(target != null) {
			Territory targetTerritory = GameController.activeMode.findItsTerritoryCorresponding(target.getCorrespondingTag());
			focusTerritories[1] = targetTerritory;
		}
		else { focusTerritories[1] = null; }
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
	
	public boolean getTextualPanelUpdateRequest() {
		if(textualPanelUpdateRequest) {
			textualPanelUpdateRequest  = false;
			return true;
		}
		return false;
	}
	
	public boolean getVisualCardPanelUpdateRequest() {
		if(visualCardPanelUpdateRequest) {
			visualCardPanelUpdateRequest  = false;
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
	
	public static TURN_PHASE getActivePhase() {
		return Turn.activePhase;
	}
	
	public static Player getActivePlayer() {
		return Turn.activePlayer;
	}
	
	public static ArrayList<VisualCard> extractActivePlayerVisualCards() {
		ArrayList<Card> cards = Game.extractActivePlayerCards();
		ArrayList<VisualCard> visualCards = new ArrayList<VisualCard>();
		
		VisualCard insert;
		for(Card card : cards) {
			insert = findCorrespondingVisualCard(card);
			if(insert != null)
				visualCards.add(insert);
		}
		return visualCards;
	}
	
	public static Territory findCorrespondingTerritory(VisualTerritory visualTerritory) {
		return GameController.activeMode.findItsTerritoryCorresponding(visualTerritory.getCorrespondingTag());
	}
	
	public static Territory findCorrespondingTerritory(Card card) {
		return GameController.activeMode.findItsTerritoryCorresponding(card.getCorrespondingTag());
	}
	
	public static VisualTerritory findCorrespondingVisualTerritory(Territory territory) {
		return GameController.activeMode.findItsVisualTerritoryCorresponding(territory.getCorrespondingTag());
	}
	
	public static VisualCard findCorrespondingVisualCard(Card card) {
		return GameController.activeMode.findItsVisualCardCorresponding(card.getCorrespondingTag());
	}
	
	public static Card findCorrespondingCard(VisualCard card) {
		return GameController.activeMode.findItsCardCorresponding(card.getCorrespondingTag());
	}
	
	public static Card findCorrespondingCard(Territory territory) {
		return GameController.activeMode.findItsCardCorresponding(territory.getCorrespondingTag());
	}
	
	public static boolean isSelectable(VisualTerritory source, VisualTerritory target, int selectAmount) {
		if(source == null) return false;
		if(target != null) {
			Territory sourceTerritory = GameController.activeMode.findItsTerritoryCorresponding(source.getCorrespondingTag());
			if(sourceTerritory == null) return false;
			Territory targetTerritory = GameController.activeMode.findItsTerritoryCorresponding(target.getCorrespondingTag());
			if(targetTerritory == null) return false;
			return Game.isSelectable(sourceTerritory, targetTerritory, selectAmount);
		}
		else {
			Territory sourceTerritory = GameController.activeMode.findItsTerritoryCorresponding(source.getCorrespondingTag());
			if(sourceTerritory == null) return false;
			return Game.isSelectable(sourceTerritory, null, selectAmount);
		}
	}
	
	public static boolean requestCardActivation(ArrayList<VisualCard> activates) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for(VisualCard activate : activates)
			cards.add(findCorrespondingCard(activate));
		return Game.activateCards(cards);
	}
	
	public static void requestNotification(String message) {
		((GamePanel)MainApplication.frame.focusPanel).requestNotification(message);
	}

	public static void requestManualGameUpdate() {
		GameController.requestManualGameUpdate();
	}
	
	public static void requestResetMultiplayerMode(int playerNumber) {
		Game.requestResetMultiplayerMode(playerNumber);
	}
	
}
