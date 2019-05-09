package GameAssets.DefaultRiskMode;

import ModelClasses.Card;

public class DefaultRiskCard extends Card {

	DefaultRiskMode.TERRITORIES territory;
	DefaultRiskMode.CONTINENTS continent;
	
	public DefaultRiskCard(DefaultRiskMode.TERRITORIES territory, CARD_TYPES cardtype) {
		super(cardtype);
		this.territory = territory;
	}
	
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

	@Override
	public boolean checkItsCorresponding(String checkTag) {
		try {
			DefaultRiskMode.TERRITORIES check = DefaultRiskMode.TERRITORIES.valueOf(checkTag);
			if(check != null)
				return territory == check;
		}
		catch(IllegalArgumentException exception) {}
		
		try {
			DefaultRiskMode.CONTINENTS check = DefaultRiskMode.CONTINENTS.valueOf(checkTag);
			if(check != null)
				return continent == check;
		}
		catch(IllegalArgumentException exception) {}
		return false;
	}

	@Override
	public String getCorrespondingTag() {
		if(territory != null) 
			return territory.toString();
		else 
			return continent.toString();
	}
	 
}
