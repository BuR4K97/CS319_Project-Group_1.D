package Controller;

import java.util.ArrayList;
import ModelClasses.Card;
import ModelClasses.GameState;
import ModelClasses.Player;
import ModelClasses.Territory;
import ModelClasses.TerritoryGraph;
import UIComponents.PixelMap;
import UIComponents.VisualCard;
import UIComponents.VisualTerritory;

public abstract class GameMode {
	
	public TerritoryGraph territoryGraph;
	public ArrayList<VisualTerritory> visualTerritories;
	public ArrayList<Card> cardSet;
	public ArrayList<VisualCard> visualCards;
	public PixelMap pixelMap;
	
	public void destroyGameMode() { 
		territoryGraph = null;
		visualTerritories = null;
		pixelMap = null;
		cardSet = null;
		visualCards = null;
	}
	
	public void loadGameMode() {
		loadTerritoryGraph();
		loadVisualTerritories();
		loadPixelMap();
		loadCardSet();
		loadVisualCards();
	}
	
	public Territory findItsTerritoryCorresponding(String findTag) {
		ArrayList<Territory> territories = territoryGraph.getTerritories();
		for(Territory territory : territories)
			if(territory.checkItsCorresponding(findTag))
				return territory;
		return null;
	}
	
	public VisualTerritory findItsVisualTerritoryCorresponding(String findTag) {
		for(VisualTerritory visualTerritory : visualTerritories)
			if(visualTerritory.checkItsCorresponding(findTag))
				return visualTerritory;
		return null;
	}
	
	public Card findItsCardCorresponding(String findTag) {
		for(Card card : cardSet)
			if(card.checkItsCorresponding(findTag))
				return card;
		return null;
	}
	
	public VisualCard findItsVisualCardCorresponding(String findTag) {
		for(VisualCard visualCard : visualCards)
			if(visualCard.checkItsCorresponding(findTag))
				return visualCard;
		return null;
	}
	
	protected abstract void loadTerritoryGraph();
	protected abstract void loadVisualTerritories();
	protected abstract void loadPixelMap();
	protected abstract void loadCardSet();
	protected abstract void loadVisualCards();
	public abstract void checkStatesModeSpecific(GameState prevState, GameState currState, Player recentPlayer);

}
