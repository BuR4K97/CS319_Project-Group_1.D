package HelperTools;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import ModelClasses.DefaultRiskMode;
import VisualComponents.DefaultRiskVisualTerritory;
import VisualComponents.VisualTerritory;

public class Test {

	public static void main(String[] args) {
		
		ImageHandler imageHandler = new ImageHandler("maps\\Risk.png");
		imageHandler.constructData();
		TerritorialImageAnalyzer.image = imageHandler.getBufferedData();
		
		int territoryNumber = DefaultRiskMode.TERRITORIES.values().length;
		ArrayList<VisualTerritory> visualTerritories = new ArrayList<VisualTerritory>();
		for(int i = 0; i < territoryNumber; i++)
			visualTerritories.add(new DefaultRiskVisualTerritory(DefaultRiskMode.TERRITORIES.values()[i]));
		
		visualTerritories.get(0).color = new Color(128, 128, 0);
		visualTerritories.get(1).color = new Color(80, 80, 39);
		visualTerritories.get(2).color = new Color(255, 255, 0);
		visualTerritories.get(3).color = new Color(255, 200, 0);
		visualTerritories.get(4).color = new Color(148, 148, 73);
		visualTerritories.get(5).color = new Color(255, 255, 128);
		visualTerritories.get(6).color = new Color(80, 80, 30);
		visualTerritories.get(7).color = new Color(255, 180, 0);
		visualTerritories.get(8).color = new Color(255, 220, 0);
		visualTerritories.get(9).color = new Color(255, 128, 128);
		visualTerritories.get(10).color = new Color(128, 0, 0);
		visualTerritories.get(11).color = new Color(128, 64, 64);
		visualTerritories.get(12).color = new Color(255, 0, 0);
		visualTerritories.get(13).color = new Color(0, 0, 255);
		visualTerritories.get(14).color = new Color(0, 64, 128);
		visualTerritories.get(15).color = new Color(0, 128, 255);
		visualTerritories.get(16).color = new Color(0, 0, 128);
		visualTerritories.get(17).color = new Color(0, 0, 255);
		visualTerritories.get(18).color = new Color(0, 50, 128);
		visualTerritories.get(19).color = new Color(0, 120, 255);
		visualTerritories.get(20).color = new Color(255, 145, 91);
		visualTerritories.get(21).color = new Color(128, 64, 0);
		visualTerritories.get(22).color = new Color(255, 128, 0);
		visualTerritories.get(23).color = new Color(174, 87, 0);
		visualTerritories.get(24).color = new Color(128, 60, 0);
		visualTerritories.get(25).color = new Color(174, 80, 0);
		visualTerritories.get(26).color = new Color(0, 128, 0);
		visualTerritories.get(27).color = new Color(128, 255, 120);
		visualTerritories.get(28).color = new Color(0, 64, 10);
		visualTerritories.get(29).color = new Color(0, 128, 10);
		visualTerritories.get(30).color = new Color(0, 128, 120);
		visualTerritories.get(31).color = new Color(128, 255, 20);
		visualTerritories.get(32).color = new Color(0, 64, 0);
		visualTerritories.get(33).color = new Color(0, 128, 64);
		visualTerritories.get(34).color = new Color(0, 128, 128);
		visualTerritories.get(35).color = new Color(128, 255, 128);
		visualTerritories.get(36).color = new Color(128, 255, 0);
		visualTerritories.get(37).color = new Color(0, 128, 55);
		visualTerritories.get(38).color = new Color(255, 0, 255);
		visualTerritories.get(39).color = new Color(128, 0, 255);
		visualTerritories.get(40).color = new Color(128, 0, 64);
		visualTerritories.get(41).color = new Color(64, 0, 64);
		
		TerritorialImageAnalyzer.constructTerritorialData(visualTerritories);
		
		ArrayList<Serializable> objects = new ArrayList<Serializable>();
		for(VisualTerritory currElement : visualTerritories)
			objects.add(currElement);
		
		FileHandler fileHandler = new FileHandler("VisualTerritories.so");
		fileHandler.saveDataToFile(objects);
		objects = fileHandler.loadDataFromFile();
		
		visualTerritories = new ArrayList<VisualTerritory>();
		for(Serializable currElement : objects)
			visualTerritories.add((VisualTerritory)currElement);
		
		Scanner scan = new Scanner(System.in);
		int pixelCount = 0;
		for(VisualTerritory currElement : visualTerritories) {
			pixelCount += currElement.border.size();
			currElement.print();
			scan.nextLine();
		}
		System.out.println("Total Territory: " + visualTerritories.size() + "\tSurrounded with " + pixelCount + " pixels");
	}

}
