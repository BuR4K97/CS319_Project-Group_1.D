package ModelClasses;
import java.util.ArrayList;

public abstract class GameMode {
	
	public TerritoryGraph territoryGraph;
	
	public void destroyTerritoryGraph() { territoryGraph = null; }
	
	public abstract void loadTerritoryGraph();
	//public abstract void checkStates(GameState prevState, GameState currState);

}
