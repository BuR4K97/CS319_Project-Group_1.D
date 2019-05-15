package ModelClasses;

import Controller.GameController;
import Controller.GameInteractions;

public class Turn {
	
	public static enum TURN_PHASE { DRAFT, ATTACK, FORTIFY };
	public static enum GAME_STATE {	
		INITIAL(new boolean[] {false, true, true}), NORMAL(new boolean[] {true, false, false});
		
		private TURN_PHASE activePhase;
		private boolean[] suspends = new boolean[TURN_PHASE.values().length];
		
		private GAME_STATE(boolean[] suspends) {
			this.suspends = suspends;
			for(int i = 0; i < TURN_PHASE.values().length; i++) {
				if(suspends[i]) continue;
				activePhase = TURN_PHASE.values()[i];
				break;
			}
		}
		
		//You should not make all suspends true; it will try to execute an infinite loop.
		//Return whether cycle is recycled
		private boolean reversePhase() {
			boolean recycled = false;
			int initialIndex = activePhase.ordinal() - 1;
			if(initialIndex == -1) initialIndex = TURN_PHASE.values().length - 1;
			for(int i = initialIndex; i > -1; i--) {
				if(i == TURN_PHASE.values().length - 1) recycled = true;
				if(suspends[i])  {
					if(i - 1 == -1)
						i = TURN_PHASE.values().length;
					continue;
				}
				activePhase = TURN_PHASE.values()[i];
				break;
			}
			return recycled;
		}
		
		//You should not make all suspends true; it will try to execute an infinite loop.
		//Return whether cycle is recycled
		private boolean nextPhase() {
			boolean recycled = false;
			int initialIndex = activePhase.ordinal() + 1;
			if(initialIndex == TURN_PHASE.values().length) initialIndex = 0;
			for(int i = initialIndex; i < TURN_PHASE.values().length; i++) {
				if(i == 0) recycled = true;
				if(suspends[i])  {
					if(i + 1 == TURN_PHASE.values().length)
						i = -1;
					continue;
				}
				activePhase = TURN_PHASE.values()[i];
				break;
			}
			return recycled;
		}
		
		//Return whether cycle is recycled
		private boolean resetSuspend(int index, boolean suspend) {
			GAME_STATE.NORMAL.reversePhase();
			GAME_STATE.NORMAL.suspends[index] = suspend;
			return GAME_STATE.NORMAL.nextPhase();
		}
		
	};
	
	private static GAME_STATE gameState;
	public static TURN_PHASE activePhase;
	public static Player activePlayer;
	
	public static GameState prevState;
	
	public static void initialize() {
		gameState = GAME_STATE.INITIAL;
		activePhase = TURN_PHASE.DRAFT;
		activePlayer = Game.players.get(0);
		prevState = GameState.extractGameState();
	}
	
	public static void nextPhase() {
		GameState currState = GameState.extractGameState();
		if(gameState == GAME_STATE.NORMAL) {
			if(activePhase == TURN_PHASE.ATTACK) {
				GameState.checkStates(prevState, currState);
				GameController.interactions.requestTextualPanelUpdateRequest();
				GameController.interactions.requestVisualCardPanelUpdateRequest();
			}
		}
		prevState = currState;
		
		if(gameState.nextPhase()) nextPlayer();
		activePhase = gameState.activePhase;
		if(activePhase == TURN_PHASE.ATTACK) GameInteractions.requestAttackButtonState(true); 
		else GameInteractions.requestAttackButtonState(false);
		GameController.interactions.requestTextualPanelUpdateRequest();
	}
	
	private static void nextPlayer() {
		int currIndex;
		for(currIndex = 0; currIndex < Game.players.size(); currIndex++)
			if (Game.players.get(currIndex) == activePlayer) break;
		if(currIndex == Game.players.size() - 1) {
			currIndex = 0;
			if(gameState == GAME_STATE.NORMAL && gameState.suspends[TURN_PHASE.DRAFT.ordinal()]) {
				if(!gameState.resetSuspend(TURN_PHASE.DRAFT.ordinal(), false)) return;
			}
			else gameState = GAME_STATE.NORMAL;
		}
		else currIndex++;
		activePlayer = Game.players.get(currIndex); 
		GameController.interactions.requestVisualCardPanelUpdateRequest();
	}

	public static void destroy() {
		activePlayer = null;
		prevState = null;
	}
	
}//endClass
