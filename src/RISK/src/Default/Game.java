package Default;
import java.util.ArrayList;

public class Game {
	
	public static int playerNumber;
	public static ArrayList<Player> players = new ArrayList<Player>();
	public static ArrayList<Territory> territories = new ArrayList<Territory>();
	
	public static void update() {
		boolean actionRequest = true, actionInStages = true ; int actionAmount = 0;
		Territory sourceTerritory = new Territory(""), targetTerritory = new Territory("");
		if(actionRequest) {
			if(Turn.activePhase == Turn.TURN_PHASE.DRAFT) {
				Turn.activePlayer.addUnitsToTerritory(targetTerritory, actionAmount);
			}
			else if(Turn.activePhase == Turn.TURN_PHASE.ATTACK) {
				if(actionInStages) Turn.activePlayer.attackTerritoryPerRoll(sourceTerritory, targetTerritory);
				else Turn.activePlayer.attackTerritory(sourceTerritory, targetTerritory);
			}
			else {
				Turn.activePlayer.moveUnits(sourceTerritory, targetTerritory, actionAmount);
			}
			actionRequest = false;
		}
		boolean nextPhaseRequest = true;
		if(nextPhaseRequest) Turn.nextPhase();
	}
	
	public static void loadGame() {
		for(int i = 0; i < playerNumber; i++)
			players.add(new Player(("Player" + (i + 1)), Player.randColor()
					, Player.randColor(), Player.randColor()));
		
		GameLoader.loadTerritoryGraph(GameLoader.GAME_MODE.DEFAULT);
		territories = GameLoader.DEFAULT_RISK_TERRITORY_GRAPH.getTerritories();
		
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
		
		for(int i = 0; i < territories.size(); i++) {
			territories.get(i).print(); System.out.println();
		}
	}
	
}//endClass