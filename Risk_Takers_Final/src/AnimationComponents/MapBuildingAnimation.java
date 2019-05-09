package AnimationComponents;

import java.util.ArrayList;

import UIComponents.VisualTerritory;

public class MapBuildingAnimation extends Animation{

	ArrayList<VisualTerritory> visualTerritories = null;
	ArrayList<VisualTerritory> visualTerritoriesCopy = new ArrayList<VisualTerritory>();
	private int coordChangerUpperBound = 10;
	private int coordChangerLowerBound = 6;
	private int coordChanger = coordChangerUpperBound;
	
	public MapBuildingAnimation(ArrayList<VisualTerritory> visualTerritories) {
		this.visualTerritories = visualTerritories;
		
		for(VisualTerritory vt : visualTerritories)
			visualTerritoriesCopy.add(vt.copy());
		
		initializeForBeginning();
		
	}

	@Override
	public boolean execute() {
		boolean executing = false;
		for(int i = 0; i < this.visualTerritories.size(); i++) {
			for(int j = 0; j < this.visualTerritories.get(i).coordinates.size(); j++) {
				if(visualTerritories.get(i).coordinates.get(j).approachCoordinate(visualTerritoriesCopy.get(i).coordinates.get(j), coordChanger))
					executing = true;
			}
		}
		if(coordChanger > coordChangerLowerBound && Math.random() * 100 > 95) {
			coordChanger--;
		}
		if(!executing) {
			terminate();
			return false;
		}
		return true;
		
	}

	private void initializeForBeginning() {
		for(int i = 0; i < this.visualTerritories.size(); i++) {
			for(int j = 0; j < this.visualTerritories.get(i).coordinates.size(); j++) {
				int xCoord = (int) (Math.random() * 1920);
				int yCoord = (int) (Math.random() * 1080);
				
				if (visualTerritories.get(i).coordinates.get(j).xCoord > 1920 / 2)
					xCoord = 0;
				else
					xCoord = 1920;
				if (visualTerritories.get(i).coordinates.get(j).yCoord > 1080 / 2)
					yCoord = 0;
				else
					yCoord = 1080;
					
				
				visualTerritories.get(i).coordinates.get(j).setCoord(xCoord, yCoord);
			}
		}
	}
	
	protected void terminate() {}
}
