package ModelClasses;

import java.util.ArrayList;

import VisualComponents.VisualTerritory;

public abstract class GameMode {
	
	public TerritoryGraph territoryGraph;
	public ArrayList<VisualTerritory> visualTerritories;
	
	public void destroyTerritoryGraph() { territoryGraph = null; }
	public void destroyVisualTerritories() { visualTerritories = null; }
	
	public abstract void loadTerritoryGraph();
	public abstract void loadVisualTerritories();
	//public abstract void checkStates(GameState prevState, GameState currState);

}
