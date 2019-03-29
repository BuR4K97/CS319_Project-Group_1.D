package ModelClasses;

public class DefaultRiskTerritory extends Territory {

	DefaultRiskMode.CONTINENTS continent;
	DefaultRiskMode.TERRITORIES territory;
	//DefaultRiskCard 
	
	public DefaultRiskTerritory(String territoryName, DefaultRiskMode.TERRITORIES territory
			, DefaultRiskMode.CONTINENTS continent) {
		super(territoryName);
		this.continent = continent;
		this.territory = territory;
	}
	
}
