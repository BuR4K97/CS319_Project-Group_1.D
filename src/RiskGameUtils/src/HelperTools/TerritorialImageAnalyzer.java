package HelperTools;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import VisualComponents.Coordinate;
import VisualComponents.VisualTerritory;

public class TerritorialImageAnalyzer {
	
	public static BufferedImage image;
	
	public static boolean constructTerritorialData(ArrayList<VisualTerritory> visualTerritories) {
		if(image == null) return false;
		
		final int COMBINATIONAL_RANGE_FOR_DIRECTION = 1;
		Color pixelColor, tempColor; int width = 0, height = 0; 
		boolean border;
		while(width < image.getWidth()) {
			while (height < image.getHeight()) {
				pixelColor = new Color(image.getRGB(width, height));
				border = false;
				
				for(int i = 0; i < visualTerritories.size(); i++) {
					if(visualTerritories.get(i).color.equals(pixelColor)) {
						lookBorder:for(int k = -1 * COMBINATIONAL_RANGE_FOR_DIRECTION; k < COMBINATIONAL_RANGE_FOR_DIRECTION; k++) {
							for(int n = -1 * COMBINATIONAL_RANGE_FOR_DIRECTION; n < COMBINATIONAL_RANGE_FOR_DIRECTION; n++) {
								tempColor = new Color(image.getRGB(width + k, height + n));
								if(!tempColor.equals(pixelColor)) { border = true; break lookBorder; }
							}
						}
						if(border) visualTerritories.get(i).border.add(new Coordinate(width, height));
						else visualTerritories.get(i).coordinates.add(new Coordinate(width, height));
						
						break;
					}
				}
				height++;
			}
			width++;
			height = 0;
		}
		
		return true;
	}

}
