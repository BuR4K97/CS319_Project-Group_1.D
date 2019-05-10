package ModelClasses;

/**
 * Determines the behaviour of two combatable objects. Also, only Territory objects are considered as Combatable.
 * Basically, defines an isCombatableWith(Combatable target) to check whether two territory is combatable with each other.
 * Not checks the unit number for whether it provides the condition Combat.MIN&MAX_DEFENSE&ATTACK_UNIT.
 * Create Combat between objects is to proceed for further details. Combat handles further stuff.
 * That is, it is an intermediate level function to be used in Combat purposes; not directly use it. See combat class. 
 */
public interface Combatable {

	/**
	 * Defined with the purpose of checking two territory is combatable.
	 * While implementing, check only for players being same.
	 * Also, check territory graph only for the source and target being connected. See TerritoryGraph.checkConnect()
	 * Nothing else will be checked.
	 * Special case, if source and target are same object, then returns true directly for UI purpose.
	 * See Combat.combatable for further details.
	 */
	public abstract boolean isCombatableWith(Combatable target);
	
}
