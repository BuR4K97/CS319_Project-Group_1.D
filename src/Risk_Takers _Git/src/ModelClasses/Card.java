package ModelClasses;

public abstract class Card {

	protected CARD_TYPES cardType;
	
	public static enum CARD_TYPES {
		CANNON, HORSE, SOLDIER, EASY_CONTINENT, MODERATE_CONTINENT, HARD_CONTINENT;
		
		private static int getUnitBuff(CARD_TYPES type) {
			if(type == SOLDIER) return 1;
			if(type == EASY_CONTINENT) return 2;
			if(type == HORSE) return 3;
			if(type == MODERATE_CONTINENT) return 4;
			if(type == CANNON) return 5;
			if(type == HARD_CONTINENT) return 7;
			else return -1;
		}
	};
	
	public Card(CARD_TYPES type) {
		this.cardType = type;		
	}
	
	public int getUnitBuff() {
		return CARD_TYPES.getUnitBuff(cardType);
	}
	
}
