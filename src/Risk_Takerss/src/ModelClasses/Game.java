package ModelClasses;

import java.util.ArrayList;

import Controller.GameController;
import ModelClasses.Turn.TURN_PHASE;
import UIComponents.VisualTerritory;

public class Game {
	
	public static final int MIN_PLAYER_NUMBER = 2;
	public static ArrayList<Player> players;
	public static ArrayList<Territory> territories;
	
	public static void update() {
		int actionAmount = GameController.interactions.getActionAmount();
		Territory[] focusTerritory = GameController.interactions.getFocusTerritories();
		if(GameController.interactions.getActionRequest()) {
			if(Turn.activePhase == TURN_PHASE.DRAFT) {
				if(focusTerritory[0] != null)
					Turn.activePlayer.addUnitsToTerritory(focusTerritory[0], actionAmount);
			}
			else if(Turn.activePhase == TURN_PHASE.ATTACK) {
				if(focusTerritory[0] != null && focusTerritory[1] != null) {
					if(GameController.interactions.getActionInStages()) 
						Turn.activePlayer.attackTerritoryPerRoll(focusTerritory[0], focusTerritory[1]);
					else 
						Turn.activePlayer.attackTerritory(focusTerritory[0], focusTerritory[1]);
				}
			}
			else {
				if(focusTerritory[0] != null && focusTerritory[1] != null) {
					Turn.activePlayer.moveUnits(focusTerritory[0], focusTerritory[1], actionAmount); 
				}
			}
		}
		if(GameController.interactions.getNextPhaseRequest()) Turn.nextPhase();
	}
	
	public static boolean initialize(int playerNumber) {
		if(playerNumber < MIN_PLAYER_NUMBER) return false;
		if(GameController.activeMode == null) return false;
		
		players = new ArrayList<Player>();
		for(int i = 0; i < playerNumber; i++)
			players.add(new Player(("Player" + (i + 1)), Player.randColor()
					, Player.randColor(), Player.randColor()));
		
		territories = GameController.activeMode.territoryGraph.getTerritories();
		
		int distributionCount = territories.size();
		boolean distrubuted[] = new boolean[distributionCount]; int randomIndex, playerIndex = 0;
		for(int i = 0; i < distributionCount; i++) {
			randomIndex = (int)(Math.random() * distributionCount);
			if(!distrubuted[randomIndex]) {
				players.get(playerIndex).captureTerritory(territories.get(randomIndex));
				players.get(playerIndex).addUnitsToTerritory(territories.get(randomIndex), Combat.MIN_DEFENSE_UNIT);
				distrubuted[randomIndex] = true;
				
				playerIndex++; if(playerIndex == playerNumber) playerIndex = 0;
			}
			else i--;
		}
		Turn.initialize();
		
		return true;
	}
	
	public static void destroy() {
		Turn.destroy();
		players = null;
		territories = null;
	}
	
	public static Territory findCorrespondingTerritory(VisualTerritory visualTerritory) {
		for(Territory currTerritory : territories)
			if(currTerritory.checkItsCorresponding(visualTerritory)) return currTerritory;
		return null;
	}

	public static boolean isSelectable(Territory sourceTerritory, Territory targetTerritory) {
		if(Turn.activePhase == TURN_PHASE.DRAFT) {
			if(targetTerritory != sourceTerritory) return false;
			return Turn.activePlayer.captured(sourceTerritory);
		}
		else if(Turn.activePhase == TURN_PHASE.ATTACK) {
			if(!Turn.activePlayer.captured(sourceTerritory)) return false;
			return sourceTerritory.isCombatableWith(targetTerritory);
		}
		else {
			if(!Turn.activePlayer.captured(sourceTerritory)) return false;
			if(!Turn.activePlayer.captured(targetTerritory)) return false;
			return true;
		}
	}
	
}//endClass
