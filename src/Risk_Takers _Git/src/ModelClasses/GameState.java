package ModelClasses;
import java.util.ArrayList;

import Controller.GameController;
import Controller.GameInteractions;
import Controller.GameMode;

public class GameState {
	
	private ArrayList<Territory> territoriesState;
	
	public ArrayList<Territory> getTerritoriesState() { 
		return this.territoriesState;
	}
	
	public static GameState extractGameState() {
		GameState state = new GameState();
		state.territoriesState = new ArrayList<Territory>();
		for(Territory currTerritory : Game.territories)
			state.territoriesState.add(currTerritory.stateCopy());
		return state;
	}

	public static void checkStates(GameState prevState, GameState currState) {
		int territoriesCaptured = 0;
		for(int i = 0; i < prevState.territoriesState.size(); i++)
			if(currState.territoriesState.get(i).getPlayer() == Turn.activePlayer)
				territoriesCaptured += 1;
		Turn.activePlayer.insertUnit(territoriesCaptured);
		GameController.activeMode.checkStatesModeSpecific(prevState, currState, Turn.activePlayer);
	}
	
	public static void checkStateChange(GameState prevState, Territory focusTerritory) {
		if(focusTerritory.getPlayer() != Turn.activePlayer) return;
		
		if(prevState.territoriesState.get(Game.territories.indexOf(focusTerritory)).getPlayer() != Turn.activePlayer)
			Turn.activePlayer.insertCard(GameInteractions.findCorrespondingCard(focusTerritory));
		
	}
	
	public static int getChangeAmount(GameState prevState, Territory focusTerritory) {
		if(focusTerritory == null) return 0;
		int territoryIndex = Game.territories.indexOf(focusTerritory);
		return Game.territories.get(territoryIndex).getUnitNumber() - prevState.territoriesState.get(territoryIndex).getUnitNumber();
	}

}
