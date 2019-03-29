package ModelClasses;
import java.io.Serializable;
import java.util.ArrayList;

import HelperTools.FileHandler;
import VisualComponents.VisualTerritory;

public class DefaultRiskMode extends GameMode {
	
	public static String visualDataFileName = "DefaultRiskVisualTerritories.so";
	public static String modelDataFileName = "DefaultRiskTerritoryGraph.so";
	
	public static enum TERRITORIES { ALASKA, NORTH_WEST_TERRITORY, GREENLAND, ALBERIA, ONTARIO
		, EASTERN_CANADA, WESTERN_UNITED_STATES, EASTERN_UNITED_STATES, CENTRAL_AMERICA, VENEZUELA
		, PERU, BRAZIL, ARGENTINA, ICELAND, GREAT_BRITAIN, SCANDINAVIA, RUSIA, NOTHERN_EUROPE, SOUTHERN_EUROPE
		, WESTERN_EUROPE, NORTH_AFRICA, EGYPT, EAST_AFRICA, CENTRAL_AFRICA, SOUTH_AFRICA, MADAGASCAR
		, MIDDLE_EAST, AFGHANISTAN, URAL, SIBERIA, YAKUTSK, IRKUTSK, MONGOLA, CHINA, INDIA, SOUTHEAST_ASIA
		, JAPAN, KAMCHATKA, NEW_GUINESS, INDONESIA, WESTERN_AUSTRALIA, EASTERN_AUSTRALIA }
	
	public static enum CONTINENTS { NORTH_AMERICA, SOUTH_AMERICA, EUROPE, AFRICA, ASIA, AUSTRALIA }
	
	
	//public static ArrayList<Card> checkStates(GameState prevState, GameState currState) {
		//int territoryNumber = prevState.getTerritoriesState().size();
		//for(int i = 0; i < territoryNumber; i++) {
			//Turn.activePlayer.captured(sourceTerritory)
		//}
	//}
	
	public void loadTerritoryGraph() {
		FileHandler fileHandler = new FileHandler(modelDataFileName);
		ArrayList<Serializable> objects = fileHandler.loadDataFromFile();
		
		for(Serializable currElement : objects)
			territoryGraph = (TerritoryGraph)currElement;
	}

	@Override
	public void loadVisualTerritories() {
		FileHandler fileHandler = new FileHandler(visualDataFileName);
		ArrayList<Serializable> objects = fileHandler.loadDataFromFile();
		
		visualTerritories = new ArrayList<VisualTerritory>();
		for(Serializable currElement : objects)
			visualTerritories.add((VisualTerritory)currElement);
	}
	
}
