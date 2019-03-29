package ModelClasses;
import java.util.ArrayList;

public class DefaultRiskMode extends GameMode {
	
	public static enum TERRITORIES { ALASKA, NORTH_WEST_TERRITORY, GREENLAND, ALBERIA, ONTARIO
		, EASTERN_CANADA, WESTERN_UNITED_STATES, EASTERN_UNITED_STATES, CENTRAL_AMERICA, VENEZUELA
		, PERU, BRAZIL, ARGENTINA, ICELAND, GREAT_BRITAIN, SCANDINAVIA, RUSIA, NOTHERN_EUROPE, SOUTHERN_EUROPE, WESTERN_EUROPE
		, NORTH_AFRICA, EGYPT, EAST_AFRICA, CENTRAL_AFRICA, SOUTH_AFRICA, MADAGASCAR, MIDDLE_EAST
		, AFGHANISTAN, URAL, SIBERIA, YAKUTSK, IRKUTSK, MONGOLA, CHINA, INDIA, SOUTHEAST_ASIA, JAPAN
		, KAMCHATKA, NEW_GUINESS, INDONESIA, WESTERN_AUSTRALIA, EASTERN_AUSTRALIA }
	public static enum CONTINENTS { NORTH_AMERICA, SOUTH_AMERICA, EUROPE, AFRICA, ASIA, AUSTRALIA }
	
	//public static ArrayList<Card> checkStates(GameState prevState, GameState currState) {
		//int territoryNumber = prevState.getTerritoriesState().size();
		//for(int i = 0; i < territoryNumber; i++) {
			//Turn.activePlayer.captured(sourceTerritory)
		//}
	//}
	
	public void loadTerritoryGraph() {
		
		super.territoryGraph = new TerritoryGraph();
		super.territoryGraph.addTerritory(new Territory("Alaska"));					//0
		super.territoryGraph.addTerritory(new Territory("North West Territory"));	//1
		super.territoryGraph.addTerritory(new Territory("Greenland"));				//2
		super.territoryGraph.addTerritory(new Territory("Alberia"));				//3
		super.territoryGraph.addTerritory(new Territory("Ontario"));				//4
		super.territoryGraph.addTerritory(new Territory("Eastern Canada"));			//5
		super.territoryGraph.addTerritory(new Territory("Western United States"));	//6
		super.territoryGraph.addTerritory(new Territory("Eastern United States"));	//7
		super.territoryGraph.addTerritory(new Territory("Central America"));		//8
		
		super.territoryGraph.addTerritory(new Territory("Venezuela"));				//9
		super.territoryGraph.addTerritory(new Territory("Peru"));					//10
		super.territoryGraph.addTerritory(new Territory("Brazil"));					//11
		super.territoryGraph.addTerritory(new Territory("Argentina"));				//12
	
		super.territoryGraph.addTerritory(new Territory("Iceland"));				//13
		super.territoryGraph.addTerritory(new Territory("Great Britain"));			//14
		super.territoryGraph.addTerritory(new Territory("Scandinavia"));			//15
		super.territoryGraph.addTerritory(new Territory("Rusia"));					//16
		super.territoryGraph.addTerritory(new Territory("Northern Europe"));		//17
		super.territoryGraph.addTerritory(new Territory("Southern Europe"));		//18
		super.territoryGraph.addTerritory(new Territory("Western Europe"));			//19
		
		super.territoryGraph.addTerritory(new Territory("North Africa"));			//20
		super.territoryGraph.addTerritory(new Territory("Egypt"));					//21
		super.territoryGraph.addTerritory(new Territory("East Africa"));			//22
		super.territoryGraph.addTerritory(new Territory("Central Africa"));			//23
		super.territoryGraph.addTerritory(new Territory("South Africa"));			//24
		super.territoryGraph.addTerritory(new Territory("Madagascar"));				//25
		
		super.territoryGraph.addTerritory(new Territory("Middle East"));			//26
		super.territoryGraph.addTerritory(new Territory("Afghanistan"));			//27
		super.territoryGraph.addTerritory(new Territory("Ural"));					//28
		super.territoryGraph.addTerritory(new Territory("Siberia"));				//29
		super.territoryGraph.addTerritory(new Territory("Yakutsk"));				//30
		super.territoryGraph.addTerritory(new Territory("Irkutsk"));				//31
		super.territoryGraph.addTerritory(new Territory("Mongola"));				//32
		super.territoryGraph.addTerritory(new Territory("China"));					//33
		super.territoryGraph.addTerritory(new Territory("India"));					//34
		super.territoryGraph.addTerritory(new Territory("Southeast Asia"));			//35
		super.territoryGraph.addTerritory(new Territory("Japan"));					//36
		super.territoryGraph.addTerritory(new Territory("Kamchatka"));				//37
		
		super.territoryGraph.addTerritory(new Territory("New Guiness"));			//38
		super.territoryGraph.addTerritory(new Territory("Indonesia"));				//39
		super.territoryGraph.addTerritory(new Territory("Western Australia"));		//40
		super.territoryGraph.addTerritory(new Territory("Eastern Australia"));		//41
		
		ArrayList<Territory> territories = super.territoryGraph.getTerritories();
		
		
		super.territoryGraph.connectTerritory(territories.get(0), territories.get(1));
		super.territoryGraph.connectTerritory(territories.get(0), territories.get(3));
		super.territoryGraph.connectTerritory(territories.get(0), territories.get(37));
		
		super.territoryGraph.connectTerritory(territories.get(1), territories.get(2));
		super.territoryGraph.connectTerritory(territories.get(1), territories.get(3));
		super.territoryGraph.connectTerritory(territories.get(1), territories.get(4));
		
		super.territoryGraph.connectTerritory(territories.get(2), territories.get(4));
		super.territoryGraph.connectTerritory(territories.get(2), territories.get(5));
		super.territoryGraph.connectTerritory(territories.get(2), territories.get(13));
		
		super.territoryGraph.connectTerritory(territories.get(3), territories.get(4));
		super.territoryGraph.connectTerritory(territories.get(3), territories.get(6));
		
		super.territoryGraph.connectTerritory(territories.get(4), territories.get(5));
		super.territoryGraph.connectTerritory(territories.get(4), territories.get(6));
		super.territoryGraph.connectTerritory(territories.get(4), territories.get(7));

		super.territoryGraph.connectTerritory(territories.get(5), territories.get(7));
		
		super.territoryGraph.connectTerritory(territories.get(6), territories.get(7));
		super.territoryGraph.connectTerritory(territories.get(6), territories.get(8));
		
		super.territoryGraph.connectTerritory(territories.get(7), territories.get(8));
		
		super.territoryGraph.connectTerritory(territories.get(8), territories.get(9));
		
		super.territoryGraph.connectTerritory(territories.get(9), territories.get(10));
		super.territoryGraph.connectTerritory(territories.get(9), territories.get(11));
		
		super.territoryGraph.connectTerritory(territories.get(10), territories.get(11));
		super.territoryGraph.connectTerritory(territories.get(10), territories.get(12));
		
		super.territoryGraph.connectTerritory(territories.get(10), territories.get(12));
		super.territoryGraph.connectTerritory(territories.get(10), territories.get(20));
		
		super.territoryGraph.connectTerritory(territories.get(13), territories.get(14));
		super.territoryGraph.connectTerritory(territories.get(13), territories.get(15));
		
		super.territoryGraph.connectTerritory(territories.get(14), territories.get(15));
		super.territoryGraph.connectTerritory(territories.get(14), territories.get(17));
		super.territoryGraph.connectTerritory(territories.get(14), territories.get(19));
		
		super.territoryGraph.connectTerritory(territories.get(15), territories.get(16));
		super.territoryGraph.connectTerritory(territories.get(15), territories.get(17));
		
		super.territoryGraph.connectTerritory(territories.get(16), territories.get(17));
		super.territoryGraph.connectTerritory(territories.get(16), territories.get(18));
		super.territoryGraph.connectTerritory(territories.get(16), territories.get(26));
		super.territoryGraph.connectTerritory(territories.get(16), territories.get(27));
		super.territoryGraph.connectTerritory(territories.get(16), territories.get(28));
		
		super.territoryGraph.connectTerritory(territories.get(17), territories.get(18));
		super.territoryGraph.connectTerritory(territories.get(17), territories.get(19));
		
		super.territoryGraph.connectTerritory(territories.get(18), territories.get(19));
		super.territoryGraph.connectTerritory(territories.get(18), territories.get(20));
		super.territoryGraph.connectTerritory(territories.get(18), territories.get(21));
		super.territoryGraph.connectTerritory(territories.get(18), territories.get(26));
		
		super.territoryGraph.connectTerritory(territories.get(20), territories.get(21));
		super.territoryGraph.connectTerritory(territories.get(20), territories.get(22));
		super.territoryGraph.connectTerritory(territories.get(20), territories.get(23));
		
		super.territoryGraph.connectTerritory(territories.get(21), territories.get(22));
		super.territoryGraph.connectTerritory(territories.get(21), territories.get(26));
		
		super.territoryGraph.connectTerritory(territories.get(22), territories.get(23));
		super.territoryGraph.connectTerritory(territories.get(22), territories.get(24));
		super.territoryGraph.connectTerritory(territories.get(22), territories.get(25));
		super.territoryGraph.connectTerritory(territories.get(22), territories.get(26));
		
		super.territoryGraph.connectTerritory(territories.get(23), territories.get(24));
		
		super.territoryGraph.connectTerritory(territories.get(24), territories.get(25));
		
		super.territoryGraph.connectTerritory(territories.get(26), territories.get(27));
		super.territoryGraph.connectTerritory(territories.get(26), territories.get(34));
		
		super.territoryGraph.connectTerritory(territories.get(27), territories.get(28));
		super.territoryGraph.connectTerritory(territories.get(27), territories.get(33));
		super.territoryGraph.connectTerritory(territories.get(27), territories.get(34));
		
		super.territoryGraph.connectTerritory(territories.get(28), territories.get(29));
		super.territoryGraph.connectTerritory(territories.get(28), territories.get(33));
		
		super.territoryGraph.connectTerritory(territories.get(29), territories.get(30));
		super.territoryGraph.connectTerritory(territories.get(29), territories.get(31));
		super.territoryGraph.connectTerritory(territories.get(29), territories.get(32));
		super.territoryGraph.connectTerritory(territories.get(29), territories.get(33));
		
		super.territoryGraph.connectTerritory(territories.get(30), territories.get(31));
		super.territoryGraph.connectTerritory(territories.get(30), territories.get(37));
		
		super.territoryGraph.connectTerritory(territories.get(31), territories.get(32));
		super.territoryGraph.connectTerritory(territories.get(31), territories.get(37));
		
		super.territoryGraph.connectTerritory(territories.get(32), territories.get(33));
		super.territoryGraph.connectTerritory(territories.get(32), territories.get(36));
		super.territoryGraph.connectTerritory(territories.get(32), territories.get(37));
		
		super.territoryGraph.connectTerritory(territories.get(33), territories.get(34));
		super.territoryGraph.connectTerritory(territories.get(33), territories.get(35));
		
		super.territoryGraph.connectTerritory(territories.get(34), territories.get(35));
		
		super.territoryGraph.connectTerritory(territories.get(35), territories.get(39));
		
		super.territoryGraph.connectTerritory(territories.get(36), territories.get(37));
		
		super.territoryGraph.connectTerritory(territories.get(38), territories.get(39));
		super.territoryGraph.connectTerritory(territories.get(38), territories.get(40));
		super.territoryGraph.connectTerritory(territories.get(38), territories.get(41));
		
		super.territoryGraph.connectTerritory(territories.get(39), territories.get(40));
		
		super.territoryGraph.connectTerritory(territories.get(38), territories.get(41));
	}
	
}
