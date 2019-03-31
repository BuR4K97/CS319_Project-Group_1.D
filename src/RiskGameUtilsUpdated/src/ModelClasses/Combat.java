package ModelClasses;
import java.util.ArrayList;

public class Combat {

	public static final int MAX_ATTACK_UNIT = 3;
	public static final int MIN_ATTACK_UNIT = 2;
	public static final int MAX_DEFENSE_UNIT = 2;
	public static final int MIN_DEFENSE_UNIT = 1;
	public static final int UNIT_LOSS_PER_DIE_ROLL = 1;
	
	private Territory sourceTerritory;
	private Territory targetTerritory;
	private Dice sourceDice;
	private Dice targetDice;
	
	
	//Should check Combat.combatable(sourceTerritory, targetTerritory) of two territories before construct Combat
	public Combat(Territory sourceTerritory, Territory targetTerritory) {
		this.sourceTerritory = sourceTerritory;
		this.targetTerritory = targetTerritory;
	}
	
	//Returns whether another combat is possible after combat 
	public boolean combatPerRoll() {
		if(sourceTerritory.getUnitNumber() > MAX_ATTACK_UNIT) sourceDice = new Dice(MAX_ATTACK_UNIT);
		else sourceDice = new Dice(sourceTerritory.getUnitNumber());
		if(targetTerritory.getUnitNumber() > MAX_DEFENSE_UNIT) targetDice = new Dice(MAX_DEFENSE_UNIT);
		else targetDice = new Dice(sourceTerritory.getUnitNumber());
		sourceDice.rollDice();
		targetDice.rollDice();
		
		ArrayList<Integer> sourceDiceNumbers, targetDiceNumbers; int lowerDieBound;
		sourceDiceNumbers = sourceDice.getFaceNumbers();
		targetDiceNumbers = targetDice.getFaceNumbers();
		lowerDieBound = sourceDiceNumbers.size();
		if(targetDiceNumbers.size() < lowerDieBound) lowerDieBound = targetDiceNumbers.size();
			
		for(int i = 0; i < lowerDieBound; i++) {
			if(sourceDiceNumbers.get(sourceDiceNumbers.size() - 1 - i) > targetDiceNumbers
						.get(targetDiceNumbers.size() - 1 - i))
				targetTerritory.removeUnits(UNIT_LOSS_PER_DIE_ROLL);
			else
				sourceTerritory.removeUnits(UNIT_LOSS_PER_DIE_ROLL);
		}
		if(Combat.combatable(sourceTerritory, targetTerritory)) return true;
		
		if(targetTerritory.getUnitNumber() == 0) {
			sourceTerritory.getPlayer().captureTerritory(targetTerritory);
			sourceTerritory.getPlayer().moveUnits(sourceTerritory, targetTerritory, MIN_DEFENSE_UNIT);
		}
		return false;
	}
	
	public void combatTillCapture() {
		while(combatPerRoll()) {}
	}
	
	public static boolean combatable(Territory sourceTerritory, Territory targetTerritory) {
		if(!sourceTerritory.isCombatableWith(targetTerritory)) return false;
		if(sourceTerritory.getUnitNumber() < MIN_ATTACK_UNIT) return false;
		if(targetTerritory.getUnitNumber() < MIN_DEFENSE_UNIT) return false;
		return true;
	}
	
}//endClass
