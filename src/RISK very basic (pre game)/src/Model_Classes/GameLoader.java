import java.util.ArrayList;

public class GameLoader {
	
	public static enum GAME_MODE{ DEFAULT, ANY_TYPE; }
	public static GAME_MODE activeMode;
	public static TerritoryGraph DEFAULT_RISK_TERRITORY_GRAPH;
	
	private static ArrayList<Territory> territories;
	
	public static void loadTerritoryGraph(GAME_MODE loadMode) {
		if(loadMode == GAME_MODE.DEFAULT) loadDefaultRiskTerritoryGraph();
	}
	
	public static void destroyTerritoryGraph(GAME_MODE destroyMode) {
		if(destroyMode == GAME_MODE.DEFAULT) DEFAULT_RISK_TERRITORY_GRAPH = null;
		activeMode = null;
	}
	
	private static void loadDefaultRiskTerritoryGraph() {
		
		DEFAULT_RISK_TERRITORY_GRAPH = new TerritoryGraph();
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Alaska"));					//0
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("North West Territory"));	//1
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Greenland"));				//2
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Alberia"));				//3
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Ontario"));				//4
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Eastern Canada"));			//5
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Western United States"));	//6
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Eastern United States"));	//7
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Central America"));		//8
		
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Venezuela"));				//9
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Peru"));					//10
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Brazil"));					//11
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Argentina"));				//12
	
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Iceland"));				//13
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Great Britain"));			//14
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Scandinavia"));			//15
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Rusia"));					//16
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Northern Europe"));		//17
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Southern Europe"));		//18
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Western Europe"));			//19
		
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("North Africa"));			//20
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Egypt"));					//21
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("East Africa"));			//22
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Central Africa"));			//23
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("South Africa"));			//24
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Madagascar"));				//25
		
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Middle East"));			//26
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Afghanistan"));			//27
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Ural"));					//28
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Siberia"));				//29
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Yakutsk"));				//30
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Irkutsk"));				//31
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Mongola"));				//32
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("China"));					//33
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("India"));					//34
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Southeast Asia"));			//35
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Japan"));					//36
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Kamchatka"));				//37
		
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("New Guiness"));			//38
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Indonesia"));				//39
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Western Australia"));		//40
		DEFAULT_RISK_TERRITORY_GRAPH.addTerritory(new Territory("Eastern Australia"));		//41
		
		territories = DEFAULT_RISK_TERRITORY_GRAPH.getTerritories();
		
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(0), territories.get(1));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(0), territories.get(3));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(0), territories.get(37));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(1), territories.get(2));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(1), territories.get(3));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(1), territories.get(4));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(2), territories.get(4));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(2), territories.get(5));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(2), territories.get(13));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(3), territories.get(4));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(3), territories.get(6));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(4), territories.get(5));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(4), territories.get(6));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(4), territories.get(7));

		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(5), territories.get(7));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(6), territories.get(7));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(6), territories.get(8));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(7), territories.get(8));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(8), territories.get(9));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(9), territories.get(10));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(9), territories.get(11));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(10), territories.get(11));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(10), territories.get(12));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(10), territories.get(12));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(10), territories.get(20));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(13), territories.get(14));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(13), territories.get(15));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(14), territories.get(15));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(14), territories.get(17));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(14), territories.get(19));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(15), territories.get(16));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(15), territories.get(17));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(16), territories.get(17));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(16), territories.get(18));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(16), territories.get(26));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(16), territories.get(27));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(16), territories.get(28));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(17), territories.get(18));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(17), territories.get(19));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(18), territories.get(19));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(18), territories.get(20));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(18), territories.get(21));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(18), territories.get(26));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(20), territories.get(21));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(20), territories.get(22));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(20), territories.get(23));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(21), territories.get(22));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(21), territories.get(26));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(22), territories.get(23));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(22), territories.get(24));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(22), territories.get(25));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(22), territories.get(26));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(23), territories.get(24));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(24), territories.get(25));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(26), territories.get(27));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(26), territories.get(34));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(27), territories.get(28));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(27), territories.get(33));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(27), territories.get(34));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(28), territories.get(29));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(28), territories.get(33));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(29), territories.get(30));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(29), territories.get(31));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(29), territories.get(32));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(29), territories.get(33));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(30), territories.get(31));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(30), territories.get(37));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(31), territories.get(32));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(31), territories.get(37));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(32), territories.get(33));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(32), territories.get(36));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(32), territories.get(37));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(33), territories.get(34));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(33), territories.get(35));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(34), territories.get(35));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(35), territories.get(39));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(36), territories.get(37));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(38), territories.get(39));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(38), territories.get(40));
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(38), territories.get(41));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(39), territories.get(40));
		
		DEFAULT_RISK_TERRITORY_GRAPH.connectTerritory(territories.get(38), territories.get(41));
		
		activeMode = GAME_MODE.DEFAULT;
	}


}
