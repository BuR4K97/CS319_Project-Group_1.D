package ModelClasses;
import java.awt.Color;
import java.util.ArrayList;

public class Player {
	
	private String playerName;
	private Color playerColor;
	private ArrayList<Territory> territories = new ArrayList<Territory>();
	private UnitPocket unitPocket = new UnitPocket();
	
	public Player(String playerName, Color playerColor) {
		this.playerName = playerName;
		this.playerColor = playerColor;
		territories = new ArrayList<Territory>();
	}
	
	public void setName(String playerName) { this.playerName = playerName; }
	public String getName() { return this.playerName; }
	public Color getColor() { return this.playerColor; }
	public int getAvailableUnitAmount() { return this.unitPocket.getUnitAmount(); }
	public ArrayList<Card> getCardSet() { return this.unitPocket.getCardSet(); }
	
	public void insertCard(Card insert) {
		unitPocket.getCardSet().add(insert);
	}
	
	public boolean activateCards(ArrayList<Card> activates) {
		return unitPocket.activateCards(activates);
	}
	
	public boolean captureTerritory(Territory targetTerritory) {
		for(Territory currTerritory : territories)
			if(currTerritory == targetTerritory) return false;
		if(targetTerritory.playerCaptured != null)
			targetTerritory.playerCaptured.removeTerritory(targetTerritory);
		targetTerritory.setPlayer(this);
		territories.add(targetTerritory);
		return true;
	}
	
	private void removeTerritory(Territory remove) {
		territories.remove(remove);
	}
	
	public void addUnitsToTerritory(Territory targetTerritory, int unitToAdd) {
		if(targetTerritory.getPlayer() != this) return;
		targetTerritory.addUnits(unitToAdd);
		unitPocket.removeUnit(unitToAdd);
	}
	
	public boolean moveUnits(Territory sourceTerritory, Territory targetTerritory, int unitToMove) {
		if(sourceTerritory.playerCaptured != this && targetTerritory.playerCaptured != this)
			return false;
		if(sourceTerritory.getUnitNumber() - unitToMove < Combat.MIN_DEFENSE_UNIT) return false;
		sourceTerritory.removeUnits(unitToMove);
		targetTerritory.addUnits(unitToMove);
		return true;
	}
	
	public Combat attackTerritory(Territory sourceTerritory, Territory targetTerritory) {
		if(sourceTerritory.getPlayer() != this) return null;
		if(Combat.combatable(sourceTerritory, targetTerritory))
			return new Combat(sourceTerritory, targetTerritory);
		return null;
	}
	
	public boolean captured(Territory sourceTerritory) {
		for(Territory currTerritory : territories)
			if(currTerritory == sourceTerritory) return true;
		return false;
	}
	
	public String toString() {
		return playerName;
	}
	
}//endClass
