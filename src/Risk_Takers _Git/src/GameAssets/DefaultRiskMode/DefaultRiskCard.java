package GameAssets.DefaultRiskMode;

import ModelClasses.Card;

public class DefaultRiskCard extends Card {

	DefaultRiskMode.TERRITORIES territory;
	DefaultRiskMode.CONTINENTS continent;
	
	/**
	 * @param cardtype - you should give SOLDIER or HORSE or CANNON
	**/
	public DefaultRiskCard(DefaultRiskMode.TERRITORIES territory, CARD_TYPES cardtype) {
		super(cardtype);
		this.territory = territory;
	}
	
	/**
	 * @param cardtype - you should give EASY or MODERATE or HARD or EXTREME
	**/
	public DefaultRiskCard(DefaultRiskMode.CONTINENTS continent, CARD_TYPES cardtype) {
		super(cardtype);
		this.continent = continent;
	}

	@Override
	public void print() {
		if(territory != null) 
			System.out.println("The territory: " + territory.toString() + "\tUnit buff: " + getUnitBuff());
		else 
			System.out.println("The continent: " + continent.toString() + "\tUnit buff: " + getUnitBuff());
	}
	 
}
