package ModelClasses;

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
		copy.playerCaptured = playerCaptured;
		copy.unitNumber = unitNumber;
		return copy;
	}

	@Override
	public void print() {
		System.out.println("Territory: " + territory);
		if(playerCaptured != null)
			System.out.print("The player captured: " + playerCaptured.getName() + "\t<->\tThe unit: " + unitNumber);
		else 
			System.out.print("The unit: " + unitNumber);
		
	}
	
	
	
}
