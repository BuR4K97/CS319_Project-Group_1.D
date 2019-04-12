package HelperTools;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import GameAssets.DefaultRiskMode.DefaultRiskMode;
import GameAssets.DefaultRiskMode.DefaultRiskTerritory;
import GameAssets.DefaultRiskMode.DefaultRiskVisualTerritory;
import ModelClasses.Territory;
import ModelClasses.TerritoryGraph;
import UIComponents.Coordinate;
import UIComponents.VisualTerritory;

public class Test {

	public static void main(String[] args) {
		
		processDefaultRiskTerritoryGraph(DefaultRiskMode.MODEL_DATA_FILENAME);
		processDefaultRiskVisualTerritories(DefaultRiskMode.PIXEL_MAP_FILENAME, DefaultRiskMode.VISUAL_DATA_FILENAME);
		System.out.println("Terminated");
		
	}
	
	public static void processDefaultRiskTerritoryGraph(String dataFile) {
		
		TerritoryGraph territoryGraph = new TerritoryGraph();
		for(int i = 0; i < 9; i++)
			territoryGraph.addTerritory(new DefaultRiskTerritory(DefaultRiskMode.TERRITORIES.values()[i]
					, DefaultRiskMode.CONTINENTS.NORTH_AMERICA));
		for(int i = 9; i < 13; i++)
			territoryGraph.addTerritory(new DefaultRiskTerritory(DefaultRiskMode.TERRITORIES.values()[i]
					, DefaultRiskMode.CONTINENTS.SOUTH_AMERICA));
		for(int i = 13; i < 20; i++)
			territoryGraph.addTerritory(new DefaultRiskTerritory(DefaultRiskMode.TERRITORIES.values()[i]
					, DefaultRiskMode.CONTINENTS.EUROPE));
		for(int i = 20; i < 26; i++)
			territoryGraph.addTerritory(new DefaultRiskTerritory(DefaultRiskMode.TERRITORIES.values()[i]
					, DefaultRiskMode.CONTINENTS.AFRICA));
		for(int i = 26; i < 38; i++)
			territoryGraph.addTerritory(new DefaultRiskTerritory(DefaultRiskMode.TERRITORIES.values()[i]
					, DefaultRiskMode.CONTINENTS.ASIA));
		for(int i = 38; i < 42; i++)
			territoryGraph.addTerritory(new DefaultRiskTerritory(DefaultRiskMode.TERRITORIES.values()[i]
					, DefaultRiskMode.CONTINENTS.AUSTRALIA));
		
		ArrayList<Territory> territories = territoryGraph.getTerritories();
		
		territoryGraph.connectTerritory(territories.get(0), territories.get(1));
		territoryGraph.connectTerritory(territories.get(0), territories.get(3));
		territoryGraph.connectTerritory(territories.get(0), territories.get(37));
		
		territoryGraph.connectTerritory(territories.get(1), territories.get(2));
		territoryGraph.connectTerritory(territories.get(1), territories.get(3));
		territoryGraph.connectTerritory(territories.get(1), territories.get(4));
		
		territoryGraph.connectTerritory(territories.get(2), territories.get(4));
		territoryGraph.connectTerritory(territories.get(2), territories.get(5));
		territoryGraph.connectTerritory(territories.get(2), territories.get(13));
		
		territoryGraph.connectTerritory(territories.get(3), territories.get(4));
		territoryGraph.connectTerritory(territories.get(3), territories.get(6));
		
		territoryGraph.connectTerritory(territories.get(4), territories.get(5));
		territoryGraph.connectTerritory(territories.get(4), territories.get(6));
		territoryGraph.connectTerritory(territories.get(4), territories.get(7));

		territoryGraph.connectTerritory(territories.get(5), territories.get(7));
		
		territoryGraph.connectTerritory(territories.get(6), territories.get(7));
		territoryGraph.connectTerritory(territories.get(6), territories.get(8));
		
		territoryGraph.connectTerritory(territories.get(7), territories.get(8));
		
		territoryGraph.connectTerritory(territories.get(8), territories.get(9));
		
		territoryGraph.connectTerritory(territories.get(9), territories.get(10));
		territoryGraph.connectTerritory(territories.get(9), territories.get(11));
		
		territoryGraph.connectTerritory(territories.get(10), territories.get(11));
		territoryGraph.connectTerritory(territories.get(10), territories.get(12));
		
		territoryGraph.connectTerritory(territories.get(10), territories.get(12));
		territoryGraph.connectTerritory(territories.get(10), territories.get(20));
		
		territoryGraph.connectTerritory(territories.get(13), territories.get(14));
		territoryGraph.connectTerritory(territories.get(13), territories.get(15));
		
		territoryGraph.connectTerritory(territories.get(14), territories.get(15));
		territoryGraph.connectTerritory(territories.get(14), territories.get(17));
		territoryGraph.connectTerritory(territories.get(14), territories.get(19));
		
		territoryGraph.connectTerritory(territories.get(15), territories.get(16));
		territoryGraph.connectTerritory(territories.get(15), territories.get(17));
		
		territoryGraph.connectTerritory(territories.get(16), territories.get(17));
		territoryGraph.connectTerritory(territories.get(16), territories.get(18));
		territoryGraph.connectTerritory(territories.get(16), territories.get(26));
		territoryGraph.connectTerritory(territories.get(16), territories.get(27));
		territoryGraph.connectTerritory(territories.get(16), territories.get(28));
		
		territoryGraph.connectTerritory(territories.get(17), territories.get(18));
		territoryGraph.connectTerritory(territories.get(17), territories.get(19));
		
		territoryGraph.connectTerritory(territories.get(18), territories.get(19));
		territoryGraph.connectTerritory(territories.get(18), territories.get(20));
		territoryGraph.connectTerritory(territories.get(18), territories.get(21));
		territoryGraph.connectTerritory(territories.get(18), territories.get(26));
		
		territoryGraph.connectTerritory(territories.get(20), territories.get(21));
		territoryGraph.connectTerritory(territories.get(20), territories.get(22));
		territoryGraph.connectTerritory(territories.get(20), territories.get(23));
		
		territoryGraph.connectTerritory(territories.get(21), territories.get(22));
		territoryGraph.connectTerritory(territories.get(21), territories.get(26));
		
		territoryGraph.connectTerritory(territories.get(22), territories.get(23));
		territoryGraph.connectTerritory(territories.get(22), territories.get(24));
		territoryGraph.connectTerritory(territories.get(22), territories.get(25));
		territoryGraph.connectTerritory(territories.get(22), territories.get(26));
		
		territoryGraph.connectTerritory(territories.get(23), territories.get(24));
		
		territoryGraph.connectTerritory(territories.get(24), territories.get(25));
		
		territoryGraph.connectTerritory(territories.get(26), territories.get(27));
		territoryGraph.connectTerritory(territories.get(26), territories.get(34));
		
		territoryGraph.connectTerritory(territories.get(27), territories.get(28));
		territoryGraph.connectTerritory(territories.get(27), territories.get(33));
		territoryGraph.connectTerritory(territories.get(27), territories.get(34));
		
		territoryGraph.connectTerritory(territories.get(28), territories.get(29));
		territoryGraph.connectTerritory(territories.get(28), territories.get(33));
		
		territoryGraph.connectTerritory(territories.get(29), territories.get(30));
		territoryGraph.connectTerritory(territories.get(29), territories.get(31));
		territoryGraph.connectTerritory(territories.get(29), territories.get(32));
		territoryGraph.connectTerritory(territories.get(29), territories.get(33));
		
		territoryGraph.connectTerritory(territories.get(30), territories.get(31));
		territoryGraph.connectTerritory(territories.get(30), territories.get(37));
		
		territoryGraph.connectTerritory(territories.get(31), territories.get(32));
		territoryGraph.connectTerritory(territories.get(31), territories.get(37));
		
		territoryGraph.connectTerritory(territories.get(32), territories.get(33));
		territoryGraph.connectTerritory(territories.get(32), territories.get(36));
		territoryGraph.connectTerritory(territories.get(32), territories.get(37));
		
		territoryGraph.connectTerritory(territories.get(33), territories.get(34));
		territoryGraph.connectTerritory(territories.get(33), territories.get(35));
		
		territoryGraph.connectTerritory(territories.get(34), territories.get(35));
		
		territoryGraph.connectTerritory(territories.get(35), territories.get(39));
		
		territoryGraph.connectTerritory(territories.get(36), territories.get(37));
		
		territoryGraph.connectTerritory(territories.get(38), territories.get(39));
		territoryGraph.connectTerritory(territories.get(38), territories.get(40));
		territoryGraph.connectTerritory(territories.get(38), territories.get(41));
		
		territoryGraph.connectTerritory(territories.get(39), territories.get(40));
		
		territoryGraph.connectTerritory(territories.get(38), territories.get(41));
		System.out.println(territoryGraph.checkConnect(territories.get(0), territories.get(1)));
		
		ArrayList<Serializable> objects = new ArrayList<Serializable>();
		objects.add(territoryGraph);
		
		FileHandler fileHandler = new FileHandler(dataFile);
		fileHandler.saveDataToFile(objects);
		objects = fileHandler.loadDataFromFile();
		
		for(Serializable currElement : objects)
			territoryGraph = (TerritoryGraph)currElement;
		
		territories = territoryGraph.getTerritories();
		Scanner scan = new Scanner(System.in);
		for(Territory currElement : territories) {
			currElement.print();
			scan.nextLine();
		}
		System.out.println(territoryGraph.checkConnect(territories.get(0), territories.get(1)));
		
	}
	
	public static void processDefaultRiskVisualTerritories(String imageFile, String dataFile) {
		
		ImageHandler imageHandler = new ImageHandler(imageFile);
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
		visualTerritories.get(17).color = new Color(10, 0, 255);
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
		visualTerritories.get(42).color = new Color(0, 0, 0);
		
		visualTerritories.get(0).mainCoordinate = new Coordinate(131, 131);
		visualTerritories.get(1).mainCoordinate = new Coordinate(286, 138);
		visualTerritories.get(2).mainCoordinate = new Coordinate(716, 73);
		visualTerritories.get(3).mainCoordinate = new Coordinate(244, 208);
		visualTerritories.get(4).mainCoordinate = new Coordinate(371, 215);
		visualTerritories.get(5).mainCoordinate = new Coordinate(501, 208);
		visualTerritories.get(6).mainCoordinate = new Coordinate(211, 313);
		visualTerritories.get(7).mainCoordinate = new Coordinate(341, 345);
		visualTerritories.get(8).mainCoordinate = new Coordinate(221, 451);
		visualTerritories.get(9).mainCoordinate = new Coordinate(414, 588);
		visualTerritories.get(10).mainCoordinate = new Coordinate(424, 740);
		visualTerritories.get(11).mainCoordinate = new Coordinate(530, 709);
		visualTerritories.get(12).mainCoordinate = new Coordinate(472, 894);
		visualTerritories.get(13).mainCoordinate = new Coordinate(795, 129);
		visualTerritories.get(14).mainCoordinate = new Coordinate(852, 212);
		visualTerritories.get(15).mainCoordinate = new Coordinate(967, 131);
		visualTerritories.get(16).mainCoordinate = new Coordinate(1092, 194);
		visualTerritories.get(17).mainCoordinate = new Coordinate(962, 227);
		visualTerritories.get(18).mainCoordinate = new Coordinate(980, 293);
		visualTerritories.get(19).mainCoordinate = new Coordinate(867, 289);
		visualTerritories.get(20).mainCoordinate = new Coordinate(877, 481);
		visualTerritories.get(21).mainCoordinate = new Coordinate(1012, 424);
		visualTerritories.get(22).mainCoordinate = new Coordinate(1110, 559);
		visualTerritories.get(23).mainCoordinate = new Coordinate(1013, 633);
		visualTerritories.get(24).mainCoordinate = new Coordinate(1028, 791);
		visualTerritories.get(25).mainCoordinate = new Coordinate(1175, 778);
		visualTerritories.get(26).mainCoordinate = new Coordinate(1147, 370);
		visualTerritories.get(27).mainCoordinate = new Coordinate(1263, 271);
		visualTerritories.get(28).mainCoordinate = new Coordinate(1255, 166);
		visualTerritories.get(29).mainCoordinate = new Coordinate(1360, 126);
		visualTerritories.get(30).mainCoordinate = new Coordinate(1521, 131);
		visualTerritories.get(31).mainCoordinate = new Coordinate(1489, 206);
		visualTerritories.get(32).mainCoordinate = new Coordinate(1534, 279);
		visualTerritories.get(33).mainCoordinate = new Coordinate(1484, 366);
		visualTerritories.get(34).mainCoordinate = new Coordinate(1366, 435);
		visualTerritories.get(35).mainCoordinate = new Coordinate(1522, 495);
		visualTerritories.get(36).mainCoordinate = new Coordinate(1716, 333);
		visualTerritories.get(37).mainCoordinate = new Coordinate(1691, 139);
		visualTerritories.get(38).mainCoordinate = new Coordinate(1797, 671);
		visualTerritories.get(39).mainCoordinate = new Coordinate(1611, 625);
		visualTerritories.get(40).mainCoordinate = new Coordinate(1654, 836);
		visualTerritories.get(41).mainCoordinate = new Coordinate(1779, 813);
		
		TerritorialImageAnalyzer.constructTerritorialData(visualTerritories);
		
		ArrayList<Serializable> objects = new ArrayList<Serializable>();
		for(VisualTerritory currElement : visualTerritories)
			objects.add(currElement);
		
		FileHandler fileHandler = new FileHandler(dataFile);
		fileHandler.saveDataToFile(objects);
		objects = fileHandler.loadDataFromFile();
		
		visualTerritories = new ArrayList<VisualTerritory>();
		for(Serializable currElement : objects)
			visualTerritories.add((VisualTerritory)currElement);
		
		Scanner scan = new Scanner(System.in);
		int pixelCount = 0;
		for(VisualTerritory currElement : visualTerritories) {
			pixelCount += currElement.coordinates.size();
			currElement.print();
			scan.nextLine();
		}
		System.out.println("Total Territory: " + visualTerritories.size() + "\tSurrounded with " + pixelCount + " pixels");
	}

}
