package HelperTools;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Scanner;

import UIComponents.Coordinate;
import UIComponents.VisualTerritory;

public class TerritorialImageAnalyzer {
	
	public static BufferedImage image;
	
	public static boolean constructTerritorialData(ArrayList<VisualTerritory> visualTerritories) {
		if(image == null) return false;
		
		final int COMBINATIONAL_RANGE_FOR_DIRECTION = 9;
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
		Scanner scan = new Scanner(System.in);
		for(VisualTerritory currElement : visualTerritories) {
			//scan.nextLine();
			currElement.print();
			currElement.border = extractBorderPath(currElement.border);
			currElement.print();
		}
		return true;
	}
	
	public static ArrayList<Coordinate> extractBorderPath(ArrayList<Coordinate> borderCoordinates) {
		ArrayList<Coordinate> borderPath = new ArrayList<Coordinate>();
		borderPath.add(borderCoordinates.get(0));
		
		final int MAX_DISTANCE = 1000;
		Coordinate currCoordinate, closestCoordinate, possibleCoordinate; 
		int borderLength = borderCoordinates.size(), currDistance, closestDistance; 
		for(int i = 0; i < borderLength - 1; i++) {
			currCoordinate = borderPath.get(i);
			closestCoordinate = null;
			closestDistance = MAX_DISTANCE;
			findClosest:for(int n = 0; n < borderCoordinates.size(); n++) {
				possibleCoordinate = borderCoordinates.get(n);
				for(int k = 0; k < borderPath.size(); k++)
					if(possibleCoordinate == borderPath.get(k)) continue findClosest;
				currDistance = currCoordinate.findDistance(possibleCoordinate);
				if(currDistance < closestDistance) {
					closestCoordinate = possibleCoordinate;
					closestDistance = currDistance;
				}
			}
			if(closestCoordinate != null)
				borderPath.add(closestCoordinate);
			else {
				i--;
				borderLength--;
			}
		}
		return borderPath;
	}

}
