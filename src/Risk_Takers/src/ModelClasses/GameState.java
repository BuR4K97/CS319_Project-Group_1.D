package ModelClasses;
import java.util.ArrayList;

public class GameState {
	
	private ArrayList<Territory> territoriesState;
	
	public ArrayList<Territory> getTerritoriesState() { return this.territoriesState; }
	
	public static void checkStates(GameState prevState, GameState currState) {
		//int territoryNumber = prevState.territoriesState.size();
		//if(GameLoader.activeMode == GameLoader.GAME_MODE.DEFAULT) DefaultRiskMode.
	}
	
	public static GameState extractGameState() {
		GameState state = new GameState();
		state.territoriesState = new ArrayList<Territory>();
		for(Territory currTerritory : Game.territories)
			state.territoriesState.add(currTerritory.stateCopy());
		return state;
	}

}
