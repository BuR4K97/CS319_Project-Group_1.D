package GameAssets.DefaultRiskMode;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ModelClasses.Card;
import ModelClasses.GameState;
import Controller.GameMode;
import HelperTools.FileHandler;
import HelperTools.ImageHandler;
import ModelClasses.TerritoryGraph;
import UIComponents.PixelMap;
import UIComponents.VisualCard;
import UIComponents.VisualTerritory;

public class DefaultRiskMode extends GameMode {
	
	public static final String VISUAL_DATA_FILENAME = "GameAssets\\DefaultRiskMode\\DefaultRiskVisualTerritories.so";
	public static final String MODEL_DATA_FILENAME = "GameAssets\\DefaultRiskMode\\DefaultRiskTerritoryGraph.so";
	public static final String PIXEL_MAP_FILENAME = "GameAssets\\DefaultRiskMode\\Risk.png";
	public static final String CARD_SET_FILENAME = "GameAssets\\DefaultRiskMode\\DefaultRiskCardSet.so";
	public static final String VISUAL_CARDS_FILENAME = "GameAssets\\DefaultRiskMode\\DefaultRiskVisualCards.so";
	public static final String[] CARD_SYMBOLS_FILENAME = new String[Card.COMBINATIONAL_VARIANTS];
	static {
		CARD_SYMBOLS_FILENAME[0] = "GameAssets\\DefaultRiskMode\\CardSymbols\\Soldier.jpg";
		CARD_SYMBOLS_FILENAME[1] = "GameAssets\\DefaultRiskMode\\CardSymbols\\Tank.jpg";
		CARD_SYMBOLS_FILENAME[2] = "GameAssets\\DefaultRiskMode\\CardSymbols\\Plane.jpg";
		CARD_SYMBOLS_FILENAME[3] = "GameAssets\\DefaultRiskMode\\CardSymbols\\Medal.jpg";
	}
	
	public final BufferedImage[] CARD_SYMBOLS = new BufferedImage[Card.COMBINATIONAL_VARIANTS]; {
		ImageHandler cardSymbol;
		for(int i = 0; i < Card.COMBINATIONAL_VARIANTS; i++) {
			cardSymbol = new ImageHandler(CARD_SYMBOLS_FILENAME[i]);
			cardSymbol.constructData();
			CARD_SYMBOLS[i] = cardSymbol.getBufferedData();
		}
	}
	
	public static enum TERRITORIES { ALASKA, NORTH_WEST_TERRITORY, GREENLAND, ALBERIA, ONTARIO
		, EASTERN_CANADA, WESTERN_UNITED_STATES, EASTERN_UNITED_STATES, CENTRAL_AMERICA, VENEZUELA
		, PERU, BRAZIL, ARGENTINA, ICELAND, GREAT_BRITAIN, SCANDINAVIA, RUSIA, NOTHERN_EUROPE, SOUTHERN_EUROPE
		, WESTERN_EUROPE, NORTH_AFRICA, EGYPT, EAST_AFRICA, CENTRAL_AFRICA, SOUTH_AFRICA, MADAGASCAR
		, MIDDLE_EAST, AFGHANISTAN, URAL, SIBERIA, YAKUTSK, IRKUTSK, MONGOLA, CHINA, INDIA, SOUTHEAST_ASIA
		, JAPAN, KAMCHATKA, NEW_GUINESS, INDONESIA, WESTERN_AUSTRALIA, EASTERN_AUSTRALIA, SEA };
	
	public static enum CONTINENTS { NORTH_AMERICA, SOUTH_AMERICA, EUROPE, AFRICA, ASIA, AUSTRALIA };
	
	public VisualTerritory getVisualTerritory(TERRITORIES territory) {
		for(int i = 0; i < TERRITORIES.values().length; i++) {
			if(TERRITORIES.values()[i] == territory)
				return visualTerritories.get(i);
		}
		return null;
	}
	
	public void checkStates(GameState prevState, GameState currState) {
		//Implement this
		
	}
	
	@Override
	protected void loadTerritoryGraph() {
		FileHandler fileHandler = new FileHandler(MODEL_DATA_FILENAME);
		ArrayList<Serializable> objects = fileHandler.loadDataFromFile();
		
		for(Serializable currElement : objects)
			territoryGraph = (TerritoryGraph)currElement;
	}
	
	@Override
	protected void loadVisualTerritories() {
		FileHandler fileHandler = new FileHandler(VISUAL_DATA_FILENAME);
		ArrayList<Serializable> objects = fileHandler.loadDataFromFile();
		
		visualTerritories = new ArrayList<VisualTerritory>();
		for(Serializable currElement : objects)
			visualTerritories.add((VisualTerritory)currElement);
	}
	
	@Override
	protected void loadPixelMap() {
		ImageHandler imageBuffer = new ImageHandler(PIXEL_MAP_FILENAME);
		imageBuffer.constructData();
		pixelMap = new PixelMap();
		pixelMap.imageBuffer = imageBuffer.getBufferedData();
	}

	@Override
	protected void loadCardSet() {
		FileHandler fileHandler = new FileHandler(CARD_SET_FILENAME);
		ArrayList<Serializable> objects = fileHandler.loadDataFromFile();
		
		cardSet = new ArrayList<Card>();
		for(Serializable currElement : objects)
			cardSet.add((Card)currElement);
	}

	@Override
	protected void loadVisualCards() {
		FileHandler fileHandler = new FileHandler(VISUAL_CARDS_FILENAME);
		ArrayList<Serializable> objects = fileHandler.loadDataFromFile();
		
		visualCards = new ArrayList<VisualCard>();
		for(Serializable currElement : objects)
			visualCards.add((VisualCard)currElement);
	}
	
}
