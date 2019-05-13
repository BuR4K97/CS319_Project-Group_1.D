package ModelClasses;

import java.util.ArrayList;

import ArtificialIntelligenceComponents.ArtificialIntelligenceHandler;
import Controller.GameController;
import Controller.GameInteractions;
import GameAssets.GameConstants;
import GameAssets.DefaultRiskMode.DefaultRiskMode;
import ModelClasses.Card.CARD_ACTIVATION;
import ModelClasses.Turn.TURN_PHASE;
import UIComponents.VisualTerritory;

/**
 * Statically constructed Game class since there will be only one active Game among the program.
 * Manipulates the model data related with active game.
 * Provides its functionality through initialize(), update() and destroy().
 * Only GameController calls such functions to control model data.
 * But, there is also methods as isSelectable() and findCorresponding() for specific data matching and transfer.
**/
public class Game {
	
	public static final int MIN_PLAYER_NUMBER = 2;
	public static final int MAX_PLAYER_NUMBER = 4;
	public static enum PLAYER_MODE { 
		SINGLEPLAYER(1), MULTIPLAYER(2);
		
		public int playerNumber;
		
		private PLAYER_MODE(int playerNumber) {
			this.playerNumber = playerNumber;
		}
		
		private void setPlayerNumber(int playerNumber) {
			if(this == MULTIPLAYER && playerNumber >= MIN_PLAYER_NUMBER && playerNumber <= MAX_PLAYER_NUMBER)
				this.playerNumber = playerNumber;
		}
		
	};
	public static ArrayList<Player> players;
	public static ArrayList<Territory> territories;
	
	/**
	 * Extracting incoming request signals from UI components through GameInteractions class. See GameInteractions class.
	 * Regarding with such incoming signals, it performs the required action.
	 * While performing action, it checks activePlayer and activePhase in Turn class. See Turn class.
	 * All actions performed over player objects not territory objects. See Player class.
	 * Only GameController should call this update() function. See GameController.update().
	 */
	public static void update() {
		int actionAmount = GameController.interactions.getActionAmount();
		Territory[] focusTerritory = GameController.interactions.getFocusTerritories();
		if(GameController.interactions.getCombatActive()) {
			boolean finished = false;
			if(GameController.interactions.getAttackPerRoll())
				finished = !GameController.interactions.getActiveCombat().combatPerRoll();
			else if(GameController.interactions.getAttackTillCapture())
				finished = !GameController.interactions.getActiveCombat().combatTillCapture();
			
			if(finished) {
				GameState.checkStateChange(Turn.prevState, GameController.interactions.getActiveCombat()
						.getCombatTerritories()[1]);
				GameController.interactions.requestVisualCardPanelUpdateRequest();
				GameController.interactions.requestTextualPanelUpdateRequest();
				GameController.interactions.terminateCombat();
			}
		}
		else if(GameController.interactions.getActionRequest()) {
			if(Turn.activePhase == TURN_PHASE.DRAFT) {
				if(focusTerritory[0] != null) {
					Turn.activePlayer.addUnitsToTerritory(focusTerritory[0], actionAmount);
					GameController.interactions.requestTextualPanelUpdateRequest();
				}
			}
			else if(Turn.activePhase == TURN_PHASE.ATTACK) {
				if(Turn.activePlayer.getCardSet().size() < UnitPocket.MAX_CARD) {
					if(focusTerritory[0] != null && focusTerritory[1] != null) {
						GameController.interactions.activateCombat(Turn.activePlayer.attackTerritory(focusTerritory[0]
								, focusTerritory[1]));
					} 
				}
				else {System.out.println("You cannot attack since you have too many cards!!!");}
			}
			else {
				if(focusTerritory[0] != null && focusTerritory[1] != null) {
					Turn.activePlayer.moveUnits(focusTerritory[0], focusTerritory[1], actionAmount); 
				}
			}
		}
		if(GameController.interactions.getNextPhaseRequest()) Turn.nextPhase();
	}
	
	/**
	 * It will initialize the territory and player attributes.
	 * As many player as given playerNumber will be created. 
	 * Territories will be extracted from GameController.activeMode and then players are randomly distributed.
	 * That is, GameController.activeMode should be initialized and loaded as GameMode before calling this function. See GameMode.loadGameMode().
	 * Only GameController should call this initialize() function. See GameController.initializeGame().
	 * Also, it evokes the Turn.initialize(). See Turn.initialize().
	 * In a sense, it will bind the Game class to the constant and loaded data of GameMode.
	 * After game terminated, such binding also should be terminated for deallocation of memory from loaded data of GameMode.
	 * See GameMode.destroyGameMode() and Game.destroy().
	**/
	public static boolean initialize(PLAYER_MODE playerMode, int playerNumber) {
		if(playerNumber < MIN_PLAYER_NUMBER) return false;
		if(playerNumber > MAX_PLAYER_NUMBER) return false;
		if(GameController.activeMode == null) return false;
		
		players = new ArrayList<Player>();
		for(int i = 0; i < playerNumber; i++)
			players.add(new Player(("Player " + (i + 1)), GameConstants.PLAYER_COLORS[i]));
		
		if(playerMode == PLAYER_MODE.SINGLEPLAYER) {
			for(int i = PLAYER_MODE.SINGLEPLAYER.playerNumber; i < playerNumber; i++) {
				ArtificialIntelligenceHandler.requestArtificialIntelligenceBinding(players.get(i)); 
				players.get(i).setName("AI " + Integer.toString(i + 1 - PLAYER_MODE.SINGLEPLAYER.playerNumber));
			}	
		}
		else {
			for(int i = PLAYER_MODE.MULTIPLAYER.playerNumber; i < playerNumber; i++) {
				ArtificialIntelligenceHandler.requestArtificialIntelligenceBinding(players.get(i)); 
				players.get(i).setName("AI " + Integer.toString(i + 1 - PLAYER_MODE.MULTIPLAYER.playerNumber));
			}
		}
		
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
		
		final double INITIAL_UNIT_MODIFIER = 3;
		ArrayList<Card> cardSet = GameController.activeMode.cardSet;
		ArrayList<Card> activates = new ArrayList<Card>();
		for(Player player : players) {
			for(int i = 0; i < cardSet.size() / (playerNumber * INITIAL_UNIT_MODIFIER); i++) {
				for(int n = 0; n < cardSet.get(i).cardType.activationType.activation; n++) {
					player.insertCard(cardSet.get(i));
					activates.add(cardSet.get(i));
				}
				player.activateCards(activates);
				activates.clear();
			}
		}
		Turn.initialize();
		GameController.interactions.requestTextualPanelUpdateRequest();
		GameController.interactions.requestVisualCardPanelUpdateRequest();
		
		return true;
	}
	
	/**
	 * The bindings of initialized data in Game.initialize() is terminated. That is, it will provide memory deallocation.
	 * Calls Turn.destroy() for deallocation of Turn class' data. See Turn.destroy()
	 * In the matter of territories' bindings, it is binded to the GameController.activeMode's territories in Game.initialize().
	 * After game terminated, such binding also should be terminated for deallocation of memory from loaded data of GameMode.
	 * See GameMode.destroyGameMode().
	 * Only GameController should call this destroy function() in GameController.destroyGame(). See GameController.destroy()
	 */
	public static void destroy() {
		Turn.destroy();
		players = null;
		territories = null;
	}

	public static boolean isSelectable(Territory sourceTerritory, Territory targetTerritory, int selectAmount) {
		if(sourceTerritory == null) return false;
		
		if(Turn.activePhase == TURN_PHASE.DRAFT) {
			if(!Turn.activePlayer.captured(sourceTerritory)) return false;
			if(selectAmount > 0) return selectAmount <= GameState.getChangeAmount(Turn.prevState, sourceTerritory);
			else return true;
		}
		else if(Turn.activePhase == TURN_PHASE.ATTACK) {
			if(!Turn.activePlayer.captured(sourceTerritory)) return false;
			if(targetTerritory == null) return sourceTerritory.getUnitNumber() >= Combat.MIN_ATTACK_UNIT;
			return Combat.combatable(sourceTerritory, targetTerritory);
		}
		else {
			if(!Turn.activePlayer.captured(sourceTerritory)) return false;
			
			Territory[] focusTerritories = GameController.interactions.getFocusTerritories();
			if(targetTerritory == null) {
				if(focusTerritories[0] != sourceTerritory) { //Maybe focusTerritories[0] is null
					if(focusTerritories[1] == sourceTerritory)
						return sourceTerritory.getUnitNumber() - selectAmount >= Combat.MIN_DEFENSE_UNIT;
					else if(GameState.getChangeAmount(Turn.prevState, focusTerritories[0]) == 0)
						return sourceTerritory.getUnitNumber() - selectAmount >= Combat.MIN_DEFENSE_UNIT;
				}
				else return sourceTerritory.getUnitNumber() - selectAmount >= Combat.MIN_DEFENSE_UNIT;
			}
			else if(GameController.activeMode.territoryGraph.checkOverConnect(sourceTerritory, targetTerritory)) {
				if(!Turn.activePlayer.captured(targetTerritory)) return false;
				
				if(GameState.getChangeAmount(Turn.prevState, focusTerritories[0]) == 0) return true;
				if(targetTerritory == focusTerritories[0] || targetTerritory == focusTerritories[1]) return true;
				return GameState.getChangeAmount(Turn.prevState, sourceTerritory) == selectAmount;
			}
		}
		return false;
	}
	
	public static boolean activateCards(ArrayList<Card> activates) {
		if(Turn.activePlayer.activateCards(activates)) {
			GameController.interactions.requestVisualCardPanelUpdateRequest();
			GameController.interactions.requestTextualPanelUpdateRequest();
			return true;
		}
		return false;
	}

	public static ArrayList<Card> extractActivePlayerCards() {
		return Turn.activePlayer.getCardSet();
	}

	public static void requestResetMultiplayerMode(int playerNumber) {
		PLAYER_MODE.MULTIPLAYER.setPlayerNumber(playerNumber);
	}
	
}//endClass
