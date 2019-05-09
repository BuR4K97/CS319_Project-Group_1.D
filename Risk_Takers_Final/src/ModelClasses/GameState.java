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
		for(int i = 0; i < prevState.territoriesState.size(); i++) {
			if(prevState.territoriesState.get(i).getPlayer() != currState.territoriesState.get(i).getPlayer())
				currState.territoriesState.get(i).getPlayer().insertCard(GameInteractions
						.findCorrespondingCard(currState.territoriesState.get(i)));
		}
		GameController.activeMode.checkStatesModeSpecific(prevState, currState, Turn.activePlayer);
	}

}
