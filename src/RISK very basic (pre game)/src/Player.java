import java.awt.Color;
import java.util.ArrayList;

public class Player {
	
	private String playerName;
	private Color playerColor;
	private ArrayList<Territory> territories = new ArrayList<Territory>();

	public Player(String playerName, Color c) {
		this.playerName = playerName;
		playerColor = c;
		territories = new ArrayList<Territory>();
	}
	
	public String getName() { return this.playerName; }
	public Color getColor() { return this.playerColor; }
	
	public boolean captureTerritory(Territory targetTerritory) {
		for(Territory currTerritory : territories)
			if(currTerritory == targetTerritory) return false;
		targetTerritory.setPlayer(this);
		territories.add(targetTerritory);
		return true;
	}
	
	public void addUnitsToTerritory(Territory targetTerritory, int unitToAdd) {
		if(targetTerritory.getPlayer() != this) return;
		targetTerritory.addUnits(unitToAdd);
	}
	
	public boolean moveUnits(Territory sourceTerritory, Territory targetTerritory, int unitToMove) {
		if(sourceTerritory.getUnitNumber() - unitToMove < 1) return false;
		sourceTerritory.removeUnits(unitToMove);
		targetTerritory.addUnits(unitToMove);
		return true;
	}
	
	//Returns whether another combat is possible after combat 
	public boolean attackTerritoryPerRoll(Territory sourceTerritory, Territory targetTerritory) {
		if(sourceTerritory.getPlayer() != this) return false;
		if(Combat.combatable(sourceTerritory, targetTerritory)) {
			Combat attackCombat = new Combat(sourceTerritory, targetTerritory);
			return attackCombat.combatPerRoll();
		}
		return false;
	}
	
	public void attackTerritory(Territory sourceTerritory, Territory targetTerritory) {
		if(sourceTerritory.getPlayer() != this) return;
		if(Combat.combatable(sourceTerritory, targetTerritory)) {
			Combat attackCombat = new Combat(sourceTerritory, targetTerritory);
			attackCombat.combatTillCapture();
		}
	}
	
}//endClass
