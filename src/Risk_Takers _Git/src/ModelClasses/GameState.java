package ModelClasses;
import java.util.ArrayList;

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

}
