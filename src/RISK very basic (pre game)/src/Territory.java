
public class Territory implements Combatable {

	private int unitNumber;
	private Player playerCaptured;
	
	public Territory() {
		unitNumber = 0;
		playerCaptured = null;
	}
	
	public void setPlayer(Player playerCaptured) { this.playerCaptured = playerCaptured; }
	public Player getPlayer() { return this.playerCaptured; }
	public int getUnitNumber() { return this.unitNumber; }
	
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
		return !(playerCaptured == ((Territory)target).playerCaptured);
	}
	
	public void print() { 
		System.out.print("The player captured: " + playerCaptured.getName() + "\t<->\tThe unit: " + unitNumber);
		}
	
}//endClass