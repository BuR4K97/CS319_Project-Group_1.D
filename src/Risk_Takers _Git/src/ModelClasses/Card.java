package ModelClasses;

public abstract class Card {

	protected CARD_TYPES cardType;
	
	public static enum CARD_TYPES {
		CANNON,HORSE,SOLDIER,CONTINENT;
		
		public static int getUnitBuff(CARD_TYPES type) {
			if(type == CANNON)return 5;
			if(type == HORSE)return 3;
			if(type == SOLDIER)return 1;
			return 0;
		}
		};

		
	public Card(CARD_TYPES type) {
		this.cardType=type;		
	}
}
