package Default;
public class Territory implements Combatable {

	private String territoryName;
	private int unitNumber;
	private Player playerCaptured;
	private TerritoryGraph graphConnected;
	
	public Territory(String territoryName) {
		this.territoryName = territoryName;
		unitNumber = 0;
		playerCaptured = null;
		graphConnected = null;
	}
	
	public String getName() { return this.territoryName; }
	public void setPlayer(Player playerCaptured) { this.playerCaptured = playerCaptured; }
	public Player getPlayer() { return this.playerCaptured; }
	public int getUnitNumber() { return this.unitNumber; }
	public void connectToGraph(TerritoryGraph graphToConnect) { graphConnected = graphToConnect; }
	
	public void addUnits(int unitToAdd) {
		unitNumber += unitToAdd;
	}
	
	public boolean removeUnits(int unitToRemove) {
		if(unitNumber < unitToRemove) return false;
		unitNumber -= unitToRemove;
		return true;
	}

	@Override
	public boolean isCombatableWith(Combatable target) {
		Territory targetTerritory = ((Territory)target);
		graphConnected.checkConnect(this, targetTerritory);
		return !(playerCaptured == targetTerritory.playerCaptured);
	}
	
	public void print() { 
		System.out.print(territoryName + ":\t");
		if(playerCaptured != null)
			System.out.print("The player captured: " + playerCaptured.getName() + "\t<->\tThe unit: " + unitNumber);
		else 
			System.out.print("The unit: " + unitNumber);
	}
	
}//endClass