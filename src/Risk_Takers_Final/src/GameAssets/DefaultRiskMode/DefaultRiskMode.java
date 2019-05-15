package GameAssets.DefaultRiskMode;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import GameAssets.GameMode;
import ModelClasses.Card;
import ModelClasses.GameState;
import ModelClasses.Player;
import ModelClasses.Territory;
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
	
	public static enum CONTINENTS {
		NORTH_AMERICA, SOUTH_AMERICA, EUROPE, AFRICA, ASIA, AUSTRALIA;
		static { initializeContinents(); }
		
		public TERRITORIES[] territories;
		
		public boolean contains(TERRITORIES territory) {
			for(TERRITORIES subTerr : territories)
				if(territory == subTerr) return true;
			return false;
		}
		
		private static void initializeContinents() {
			int territoryIndex = 0;
			
			NORTH_AMERICA.territories = new TERRITORIES[9];
			for(int i = 0; i < 9; i++)
				NORTH_AMERICA.territories[i] = TERRITORIES.values()[territoryIndex++];
			
			SOUTH_AMERICA.territories = new TERRITORIES[4];
			for(int i = 0; i < 4; i++)
				SOUTH_AMERICA.territories[i] = TERRITORIES.values()[territoryIndex++];
			
			EUROPE.territories = new TERRITORIES[7];
			for(int i = 0; i < 7; i++)
				EUROPE.territories[i] = TERRITORIES.values()[territoryIndex++];
			
			AFRICA.territories = new TERRITORIES[6];
			for(int i = 0; i < 6; i++)
				AFRICA.territories[i] = TERRITORIES.values()[territoryIndex++];
			
			ASIA.territories = new TERRITORIES[12];
			for(int i = 0; i < 12; i++)
				ASIA.territories[i] = TERRITORIES.values()[territoryIndex++];
			
			AUSTRALIA.territories = new TERRITORIES[4];
			for(int i = 0; i < 4; i++)
				AUSTRALIA.territories[i] = TERRITORIES.values()[territoryIndex++];
		}

	};
	
	public VisualTerritory getVisualTerritory(TERRITORIES territory) {
		for(int i = 0; i < TERRITORIES.values().length; i++) {
			if(TERRITORIES.values()[i] == territory)
				return visualTerritories.get(i);
		}
		return null;
	}
	
	@Override
	public Card findItsModeSpecificCardCorresponding(String findTag) {
		try {
			TERRITORIES corresponding = TERRITORIES.valueOf(findTag);
			if(corresponding == null) return null;
			
			CONTINENTS continent = null;
			for(CONTINENTS check : CONTINENTS.values())
				if(check.contains(corresponding)) {
					continent = check;
					break;
				}
			return super.findItsCardCorresponding(continent.toString());
		}
		catch(IllegalArgumentException exception) {}
		return null;
	}
	
	public boolean checkStateChangeModeSpecific(GameState prevState, GameState currState, Territory focusTerritory, Player recentPlayer) {
		if(focusTerritory.getPlayer() != recentPlayer) return false;
		
		CONTINENTS corresponding = ((DefaultRiskTerritory)focusTerritory).continent;
		boolean [] continentCaptured = new boolean[corresponding.territories.length];
		
		for(Territory territory : currState.getTerritoriesState()) {
			DefaultRiskTerritory normalizedTerritory = ((DefaultRiskTerritory)territory);
			if(normalizedTerritory.continent != corresponding) continue;
			if(normalizedTerritory.getPlayer() == recentPlayer) {
				int subIndex = -1;
				for(int i = 0; i < normalizedTerritory.continent.territories.length; i++)
					if(normalizedTerritory.territory == normalizedTerritory.continent.territories[i]) {	
						subIndex = i; break;
					}
				
				if(subIndex != -1) continentCaptured[subIndex] = true;
			}
		}
		
		for(boolean territoryCaptured : continentCaptured) 
			if(!territoryCaptured) return false;
		return true;
	}
	
	@Override
	public int extractModeSpecificScore(ArrayList<Territory> list) {
		ArrayList<CONTINENTS> continents = new ArrayList<CONTINENTS>();
		CONTINENTS corresponding;
		for(Territory terr : list) {
			corresponding = ((DefaultRiskTerritory)terr).continent;
			if(!continents.contains(corresponding)) continents.add(corresponding);
		}
		
		int modeSpecificScore = 0;
		continentCheck:for(CONTINENTS check : continents) {
			for(TERRITORIES terr : check.territories) {
				boolean includes = false;
				for(Territory element : list) {
					if(element.checkItsCorresponding(terr.toString())) {
						includes = true;
						break;
					}
				}
				if(!includes) continue continentCheck;
			}
			modeSpecificScore += super.findItsCardCorresponding(check.toString()).getUnitBuff();
		}
		return modeSpecificScore;
	}
	
	//Not used anymore
	public void checkStatesModeSpecific(GameState prevState, GameState currState, Player recentPlayer) {
		boolean [][] continentsCaptured = new boolean[CONTINENTS.values().length][];
		for(int i = 0; i < CONTINENTS.values().length; i++)
				continentsCaptured[i] = new boolean[CONTINENTS.values()[i].territories.length];
		
		for(Territory territory : currState.getTerritoriesState()) {
			DefaultRiskTerritory normalizedTerritory = ((DefaultRiskTerritory)territory);
			if(territory.getPlayer() == recentPlayer) {
				int subIndex = -1;
				for(int i = 0; i < normalizedTerritory.continent.territories.length; i++)
					if(normalizedTerritory.territory == normalizedTerritory.continent.territories[i]) {	
						subIndex = i; break;
					}
				
				if(subIndex != -1) continentsCaptured[normalizedTerritory.continent.ordinal()][subIndex] = true;
			}
		}
		
		continentCheck:for(int i = 0; i < continentsCaptured.length; i++) {
			for(boolean territoryCaptured : continentsCaptured[i])
				if(!territoryCaptured) continue continentCheck;
			Card insert = super.findItsCardCorresponding(CONTINENTS.values()[i].toString());
			ArrayList<Card> inserted = new ArrayList<Card>(); inserted.add(insert);
			recentPlayer.insertCard(insert);
			recentPlayer.activateCards(inserted);
		}
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
