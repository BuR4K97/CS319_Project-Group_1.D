package ModelClasses;

import Controller.GameController;
import Controller.GameInteractions;

public class Turn {
	
	public static enum GAME_STATE {	INITIAL, NORMAL };
	public static enum TURN_PHASE { DRAFT, ATTACK, FORTIFY };
	private static GAME_STATE gameState;
	public static TURN_PHASE activePhase;
	public static Player activePlayer;
	
	private static GameState prevState;
	private static GameState currState;
	
	public static void initialize() {
		gameState = GAME_STATE.INITIAL;
		activePhase = TURN_PHASE.DRAFT;
		activePlayer = Game.players.get(0);
		prevState = GameState.extractGameState();
	}
	
	public static void nextPhase() {
		if(activePhase == TURN_PHASE.DRAFT) {
			if(gameState == GAME_STATE.INITIAL) nextPlayer();
			else {
				activePhase = TURN_PHASE.ATTACK;
				GameInteractions.requestAttackButtonState(true); 
			}
		}
		else if(activePhase == TURN_PHASE.ATTACK) {
			currState = GameState.extractGameState();
			GameState.checkStates(prevState, currState);
			GameController.interactions.requestTextualPanelUpdateRequest();
			GameController.interactions.requestVisualCardPanelUpdateRequest();
			prevState = currState;
			activePhase = TURN_PHASE.FORTIFY;
			GameInteractions.requestAttackButtonState(false);
		}
		else {
			nextPlayer();
			activePhase = TURN_PHASE.DRAFT;
		}
		GameController.interactions.requestTextualPanelUpdateRequest();
	}
	
	private static void nextPlayer() {
		int currIndex;
		for(currIndex = 0; currIndex < Game.players.size(); currIndex++)
			if (Game.players.get(currIndex) == activePlayer) break;
		if(currIndex == Game.players.size() - 1) {
			currIndex = 0;
			gameState = GAME_STATE.NORMAL;
		}
		else currIndex++;
		activePlayer = Game.players.get(currIndex); 
		GameController.interactions.requestVisualCardPanelUpdateRequest();
	}

	public static void destroy() {
		activePlayer = null;
		prevState = null;
		currState = null;
	}
	
}//endClass
