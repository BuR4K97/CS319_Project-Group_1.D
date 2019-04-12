package GameAssets.DefaultRiskMode;

import java.awt.Graphics;

import GameAssets.DefaultRiskMode.DefaultRiskMode.TERRITORIES;
import UIComponents.Coordinate;
import UIComponents.VisualTerritory;

public class DefaultRiskVisualTerritory extends VisualTerritory {
	
	public DefaultRiskMode.TERRITORIES territory;
	
	public DefaultRiskVisualTerritory(DefaultRiskMode.TERRITORIES territory) { this.territory = territory; }
	
	@Override
	public void print() {
		System.out.println("Territory: " + territory.toString() + "\tColor: " + color.toString());
		System.out.println("TerritorySize: " + coordinates.size());
		//for(Coordinate c : coordinates) System.out.println("<" + c.xCoord + ", " + c.yCoord +  ">");
	}

	@Override
	public void paint(Graphics painter, boolean selected) {
		if(territory == TERRITORIES.SEA) 
			return;
		super.paint(painter, selected);
	}
	
	@Override
	public VisualTerritory copy() {
		VisualTerritory vt = new DefaultRiskVisualTerritory(territory);
		for(int i = 0; i < coordinates.size(); i++) {
			vt.coordinates.add(new Coordinate(this.coordinates.get(i).xCoord,this.coordinates.get(i).yCoord));
		}
		return vt;
	}
	
}
