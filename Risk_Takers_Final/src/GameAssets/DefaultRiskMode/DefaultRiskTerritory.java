package GameAssets.DefaultRiskMode;

import ModelClasses.Territory;

public class DefaultRiskTerritory extends Territory {

	DefaultRiskMode.CONTINENTS continent;
	DefaultRiskMode.TERRITORIES territory;
	//DefaultRiskCard 
	
	public DefaultRiskTerritory(DefaultRiskMode.TERRITORIES territory, DefaultRiskMode.CONTINENTS continent) {
		super();
		this.continent = continent;
		this.territory = territory;
	}

	@Override
	public Territory stateCopy() {
		Territory copy = new DefaultRiskTerritory(territory, continent);
		copy.setPlayer(getPlayer());
		copy.setUnitNumber(getUnitNumber());
		return copy;
	}
	

	@Override
	public boolean checkItsCorresponding(String checkTag) {
		try {
			DefaultRiskMode.TERRITORIES check = DefaultRiskMode.TERRITORIES.valueOf(checkTag);
			if(check == null) return false;
			
			return territory == check;
		}
		catch(IllegalArgumentException exception) {
			return false;
		}
	}

	@Override
	public void print() {
		System.out.println("Territory: " + territory);
		if(playerCaptured != null)
			System.out.print("The player captured: " + playerCaptured.getName() + "\t<->\tThe unit: " + unitNumber);
		else 
			System.out.print("The unit: " + unitNumber);
		
	}

	@Override
	public String getCorrespondingTag() {
		return territory.toString();
	}	
	
}
