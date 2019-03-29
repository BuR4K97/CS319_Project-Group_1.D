package ModelClasses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Territory implements Combatable, Serializable {

	protected int unitNumber;
	protected Player playerCaptured;
	protected TerritoryGraph graphConnected;
	
	public Territory() {
		unitNumber = 0;
		playerCaptured = null;
		graphConnected = null;
	}
	
	public void setPlayer(Player playerCaptured) { this.playerCaptured = playerCaptured; }
	public Player getPlayer() { return this.playerCaptured; }
	public int getUnitNumber() { return this.unitNumber; }
	public void connectToGraph(TerritoryGraph graphToConnect) { graphConnected = graphToConnect; }
	
	public abstract Territory stateCopy(); 
	
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
	
	public abstract void print();
	
	/**
	private void writeObject(ObjectOutputStream objectBuffer) throws ClassNotFoundException, IOException {
		objectBuffer.defaultWriteObject();
		
		objectBuffer.
	}
	
	private void readObject(ObjectInputStream objectBuffer) throws ClassNotFoundException, IOException {
		objectBuffer.defaultReadObject();
		
		objectBuffer.readObject()
	}
	**/
	
	
}//endClass
