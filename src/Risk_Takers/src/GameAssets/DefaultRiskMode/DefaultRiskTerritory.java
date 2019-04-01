package GameAssets.DefaultRiskMode;

import ModelClasses.Territory;
import UIComponents.VisualTerritory;

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
	public boolean checkItsCorresponding(VisualTerritory check) {
		if(!(check instanceof DefaultRiskVisualTerritory)) return false;
		
		DefaultRiskVisualTerritory corresponding = (DefaultRiskVisualTerritory)check;
		if(corresponding.territory == territory) return true;
		return false;
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
