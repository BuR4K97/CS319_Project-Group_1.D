package ModelClasses;

import java.util.ArrayList;

import ModelClasses.Card.CARD_ACTIVATION;
import ModelClasses.Card.CARD_TYPES;

public class UnitPocket {

	public static final int MAX_CARD = 9;
	
	private int unitAmount = 0;
	private ArrayList<Card> cardSet = new ArrayList<Card>();
	
	public ArrayList<Card> getCardSet() {
		return cardSet;
	}
	
	public void removeUnit(int amount) {
		unitAmount -= amount;
		if(unitAmount < 0)
			unitAmount = 0;
	}
	
	public int getUnitAmount() {
		return unitAmount;
	}
	
	public boolean activateCards(ArrayList<Card> cardSet) {
		if(cardSet.size() == CARD_ACTIVATION.INSTANT.activation) {
			if(cardSet.get(0).getActivationType() != CARD_ACTIVATION.INSTANT)
				return false;
			
			ArrayList<Card> removes = new ArrayList<Card>();
			int addUnit = 0;
			for(int i = 0; i < CARD_ACTIVATION.INSTANT.activation; i++) {
				if(this.cardSet.remove(cardSet.get(i))) {
					removes.add(cardSet.get(i));
					addUnit += cardSet.get(i).getUnitBuff();
					continue;
				}
				for(Card card : removes)
					this.cardSet.add(card);
				return false;
			}
			unitAmount += addUnit / CARD_ACTIVATION.INSTANT.activation;
			return true;	
		}
		else if(cardSet.size() == CARD_ACTIVATION.COMBINATIONAL.activation) {
			for(Card card : cardSet)
				if(card.getActivationType() != CARD_ACTIVATION.COMBINATIONAL)
					return false;
			
			boolean activated = true;
			for(int i = 0; i < CARD_ACTIVATION.COMBINATIONAL.activation - 1; i++) {
				if(cardSet.get(i).cardType != cardSet.get(i + 1).cardType) {
					activated = false;
					break; 
				}
			}
			if(!activated) {
				CARD_TYPES[] combinationalCase = {CARD_TYPES.EASY_UNIT, CARD_TYPES.MODERATE_UNIT, CARD_TYPES.HARD_UNIT};
				typeCheck:for(CARD_TYPES type : combinationalCase) {
					for(Card card : cardSet)
						if(card.cardType == type)
							continue typeCheck;
					return false;
				}
			}
			
			ArrayList<Card> removes = new ArrayList<Card>();
			int addUnit = 0;
			for(int i = 0; i < CARD_ACTIVATION.COMBINATIONAL.activation; i++) {
				if(this.cardSet.remove(cardSet.get(i))) {
					removes.add(cardSet.get(i));
					addUnit += cardSet.get(i).getUnitBuff();
					continue;
				}
				for(Card card : removes)
					this.cardSet.add(card);
				return false;
			}
			unitAmount += addUnit / CARD_ACTIVATION.COMBINATIONAL.activation;
			return true;
		}
		return false;
	}
	
}
