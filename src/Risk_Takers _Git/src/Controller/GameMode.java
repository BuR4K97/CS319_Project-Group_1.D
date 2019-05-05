package Controller;

import java.util.ArrayList;

import ModelClasses.Card;
import ModelClasses.GameState;
import ModelClasses.TerritoryGraph;
import UIComponents.PixelMap;
import UIComponents.VisualTerritory;

public abstract class GameMode {
	
	public TerritoryGraph territoryGraph;
	public ArrayList<VisualTerritory> visualTerritories;
	public ArrayList<Card> cardSet;
	public PixelMap pixelMap;
	
	public void destroyGameMode() { 
		territoryGraph = null;
		visualTerritories = null;
		pixelMap = null;
		cardSet = null;
	}
	
	public void loadGameMode() {
		loadTerritoryGraph();
		loadVisualTerritories();
		loadPixelMap();
		loadCardSet();
	}
	
	public abstract void loadTerritoryGraph();
	public abstract void loadVisualTerritories();
	public abstract void loadPixelMap();
	public abstract void loadCardSet();
	public abstract void checkStates(GameState prevState, GameState currState);

}
