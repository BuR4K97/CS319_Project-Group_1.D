package GameAssets.DefaultRiskMode;

import UIComponents.Coordinate;
import UIComponents.VisualTerritory;

public class DefaultRiskVisualTerritory extends VisualTerritory {
	
	public DefaultRiskMode.TERRITORIES territory;
	
	public DefaultRiskVisualTerritory(DefaultRiskMode.TERRITORIES territory) { this.territory = territory; }
	
	@Override
	public void print() {
		System.out.println("Territory: " + territory.toString() + "\tColor: " + color.toString());
		System.out.println("TerritorSize: " + border.size());
		//for(Coordinate c : border) System.out.println("<" + c.xCoord + ", " + c.yCoord +  ">");
	}

}
