package ModelClasses;
import java.util.ArrayList;

public class GameLoader {
	
	public static enum GAME_MODE{ DEFAULT, ANY_TYPE }
	public static GameMode activeMode;
	public static ArrayList<Territory> territories;
	
	public static void loadTerritoryGraph(GAME_MODE loadMode) {
		if(loadMode == GAME_MODE.DEFAULT) activeMode = new DefaultRiskMode();
		
		activeMode.loadTerritoryGraph(); 
		territories = activeMode.territoryGraph.getTerritories();
	}
	
	public static void destroyTerritoryGraph() {
		activeMode.destroyTerritoryGraph();
		activeMode = null;
	}
	
}//endClass
