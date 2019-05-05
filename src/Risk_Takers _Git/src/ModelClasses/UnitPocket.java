package ModelClasses;

import java.util.ArrayList;

import ModelClasses.Card.CARD_ACTIVATION;
import ModelClasses.Card.CARD_TYPES;

public class UnitPocket {

	private int unitAmount = 0;
	private ArrayList<Card> cardSet = new ArrayList<Card>();
	
	public ArrayList<Card> getCardSet() {
		return cardSet;
	}
	
	public boolean activateCards(ArrayList<Card> cardSet) {
		if(cardSet.size() == 1) {
			if(cardSet.get(0).getActivationType() != CARD_ACTIVATION.INSTANT)
				return false;
			
			unitAmount += cardSet.get(0).getUnitBuff();
			this.cardSet.remove(cardSet.get(0));
			return true;
		}
		else if(cardSet.size() == 3) {
			for(Card card : cardSet)
				if(card.getActivationType() != CARD_ACTIVATION.COMBINATIONAL)
					return false;
			
			CARD_TYPES cardType = cardSet.get(0).cardType;
			if(cardSet.get(1).cardType == cardType && cardSet.get(2).cardType == cardType) {
				unitAmount += cardSet.get(0).getUnitBuff();
				for(Card card : cardSet) 
					this.cardSet.remove(card);
				return true;
			}
			
			CARD_TYPES[] combinationalCase = {CARD_TYPES.EASY_UNIT, CARD_TYPES.MODERATE_UNIT, CARD_TYPES.HARD_UNIT};
			Card activateCard = null;
			typeCheck:for(CARD_TYPES type : combinationalCase) {
				for(Card card : cardSet)
					if(card.cardType == type) {
						if(type == combinationalCase[1])
							activateCard = card; 
						continue typeCheck;
					}
				return false;
			}
			unitAmount += activateCard.getUnitBuff();
			for(Card card : cardSet)
				this.cardSet.remove(card); 
			return true;	
		}
		else return false;
	}
	
	public int getUnitAmount() {
		return unitAmount;
	}
	
}
