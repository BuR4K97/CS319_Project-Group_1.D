package ModelClasses;

import java.io.Serializable;

public abstract class Card implements Serializable {

	public CARD_TYPES cardType;
	
	public static final int UNIT_ACTIVATION = 5;
	public static final int CONSTANT_ACTIVATION = 2;
	public static enum CARD_ACTIVATION { 
		INSTANT(1), COMBINATIONAL(3); 
		
		public int activation;
		
		private CARD_ACTIVATION(int activation) {
			this.activation = activation;
		}
	};
	
	public static final int COMBINATIONAL_VARIANTS = 4;
	public static enum CARD_TYPES {
		
		EASY_UNIT(CARD_ACTIVATION.COMBINATIONAL), MODERATE_UNIT(CARD_ACTIVATION.COMBINATIONAL)
		, HARD_UNIT(CARD_ACTIVATION.COMBINATIONAL), EXTREME_UNIT(CARD_ACTIVATION.COMBINATIONAL)
		, EASY_CONTINENT(CARD_ACTIVATION.INSTANT), MODERATE_CONTINENT(CARD_ACTIVATION.INSTANT)
		, HARD_CONTINENT(CARD_ACTIVATION.INSTANT), EXTREME_CONTINENT(CARD_ACTIVATION.INSTANT);
		
		CARD_ACTIVATION activationType;
		
		private CARD_TYPES(CARD_ACTIVATION activationType) {
			this.activationType = activationType;
		}
		
		public static int getUnitBuff(CARD_TYPES type) {
			if(type == EASY_UNIT) return 1;
			if(type == EASY_CONTINENT) return 2;
			if(type == MODERATE_UNIT) return 3;
			if(type == MODERATE_CONTINENT) return 3;
			if(type == HARD_UNIT) return 5;
			if(type == HARD_CONTINENT) return 4;
			if(type == EXTREME_UNIT) return 7;
			if(type == EXTREME_CONTINENT) return 5;
			return 0;
		}
	};
	
	public Card(CARD_TYPES type) {
		this.cardType = type;		
	}
	
	public int getUnitBuff() {
		return CARD_TYPES.getUnitBuff(cardType);
	}
	
	public CARD_ACTIVATION getActivationType() {
		return cardType.activationType;
	}
	
	public abstract void print();
	public abstract boolean checkItsCorresponding(String checkTag);
	public abstract String getCorrespondingTag();
	
}
