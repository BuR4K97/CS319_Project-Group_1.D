package ModelClasses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import UIComponents.VisualTerritory;

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
	public void setUnitNumber(int unitNumber) { this.unitNumber = unitNumber; }
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
		if(this == target) return true; //Special case for implementing isSelectable() function in Game; 
		//Calling ter1.isCompatibleWith(ter1) should return true to select a territory if any not selected
		
		Territory targetTerritory = ((Territory)target);
		if(playerCaptured == targetTerritory.playerCaptured) 
			return false;
		return graphConnected.checkConnect(this, targetTerritory);
	}
	
	public abstract boolean checkItsCorresponding(VisualTerritory check);
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
