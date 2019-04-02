package GameAssets.DefaultRiskMode;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Controller.GameMode;
import HelperTools.FileHandler;
import HelperTools.ImageHandler;
import ModelClasses.TerritoryGraph;
import UIComponents.PixelMap;
import UIComponents.VisualTerritory;

public class DefaultRiskMode extends GameMode {
	
	public static final String VISUAL_DATA_FILENAME = "DefaultRiskVisualTerritories.so";
	public static final String MODEL_DATA_FILENAME = "DefaultRiskTerritoryGraph.so";
	public static final String PIXEL_MAP_FILENAME = "maps\\Risk.png";
	
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
		FileHandler fileHandler = new FileHandler(MODEL_DATA_FILENAME);
		ArrayList<Serializable> objects = fileHandler.loadDataFromFile();
		
		for(Serializable currElement : objects)
			territoryGraph = (TerritoryGraph)currElement;
	}

	public void loadVisualTerritories() {
		FileHandler fileHandler = new FileHandler(VISUAL_DATA_FILENAME);
		ArrayList<Serializable> objects = fileHandler.loadDataFromFile();
		
		visualTerritories = new ArrayList<VisualTerritory>();
		for(Serializable currElement : objects)
			visualTerritories.add((VisualTerritory)currElement);
	}
	
	public void loadPixelMap() {
		ImageHandler imageBuffer = new ImageHandler(PIXEL_MAP_FILENAME);
		imageBuffer.constructData();
		pixelMap = new PixelMap();
		pixelMap.imageBuffer = imageBuffer.getBufferedData();
	}
	
}
