package ModelClasses;
import java.util.ArrayList;

import UIComponents.VisualTerritory;

/**
 * Represents the actions occured during an attack from source territory to target territory
 * Such actions are dice rolling, unit diminishing and whether attacking player captured target territory or not
 * Through combatable(), it provides such functionality that it checks whether two territory is appropriate for an attack action
 * Also, it determines the constants for attack actions. Such constant are MAX and MIN DEFENSE or ATTACK UNIT.
 */
public class Combat {

	public static enum COMBAT_TYPE { EFFECTIVE, SIMULATIVE };
	
	public static final int MAX_DEFENSE_UNIT = 2;
	public static final int MAX_ATTACK_UNIT = 3;
	public static final int MIN_DEFENSE_UNIT = 1;
	public static final int MIN_ATTACK_UNIT = MIN_DEFENSE_UNIT + 1;
	public static final int UNIT_LOSS_PER_DIE_ROLL = 1;
	
	public COMBAT_TYPE combatType = COMBAT_TYPE.EFFECTIVE;
	private Territory sourceTerritory;
	private Territory targetTerritory;
	private Dice sourceDice;
	private Dice targetDice;
	
	/**
	 * Should check Combat.combatable(sourceTerritory, targetTerritory) of two territories before construct Combat
	 * Avoid creating combat for the case source and target territory being same. Other cases, combatable() handles it.
	**/
	public Combat(Territory sourceTerritory, Territory targetTerritory) {
		this.sourceTerritory = sourceTerritory;
		this.targetTerritory = targetTerritory;
	}
	
	/**
	 * Returns whether another combat is possible after combat.
	 * Performs the attack action between two territory only for one dice roll.
	 * Sets up the dice of territories to determine how attackPerRoll will be resulted.
	 * While setting up dice, it will consider the unit number in territories and MIN&MAX ATTACK&DEFENSE UNIT.
	 * If another combat is not possible, then looks for whether a capturing is happened or not. 
	 * If captured, then transfer all possible units to the target territory for advance.
	**/
	public boolean combatPerRoll() {
		if(sourceTerritory.getUnitNumber() >= MAX_ATTACK_UNIT + MIN_DEFENSE_UNIT) sourceDice = new Dice(MAX_ATTACK_UNIT);
		else sourceDice = new Dice(sourceTerritory.getUnitNumber() - MIN_DEFENSE_UNIT);
		if(targetTerritory.getUnitNumber() > MAX_DEFENSE_UNIT) targetDice = new Dice(MAX_DEFENSE_UNIT);
		else targetDice = new Dice(targetTerritory.getUnitNumber());
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
		
		if(targetTerritory.getUnitNumber() < MIN_DEFENSE_UNIT && combatType == COMBAT_TYPE.EFFECTIVE) {
			sourceTerritory.getPlayer().captureTerritory(targetTerritory);
			sourceTerritory.getPlayer().moveUnits(sourceTerritory, targetTerritory
					, sourceTerritory.getUnitNumber() - MIN_DEFENSE_UNIT);
		}
		return false;
	}
	
	/**
	 * Till a capture happened or possible attackPerRoll trial finished, performs the combat action between source&target territory.
	 * Returns false since there is no remaining possible attack trial or a capture happened.
	**/
	public boolean combatTillCapture() {
		while(combatPerRoll()) {}
		return false;
	}
	
	public Territory[] getCombatTerritories() {
		Territory[] combatTerritories = new Territory[2];
		combatTerritories[0] = sourceTerritory;
		combatTerritories[1] = targetTerritory;
		return combatTerritories;
	}
	
	/**
	 * Checks whether two territory is appropriate for an attack action
	 * Also, returns true if source and target territory is same. It is used for UI purpose to visually select a territory. 
	**/
	public static boolean combatable(Territory sourceTerritory, Territory targetTerritory) {
		if(!sourceTerritory.isCombatableWith(targetTerritory)) return false;
		if(sourceTerritory.getUnitNumber() < MIN_ATTACK_UNIT) return false;
		if(targetTerritory.getUnitNumber() < MIN_DEFENSE_UNIT) return false;
		return true;
	}
	
}//endClass
