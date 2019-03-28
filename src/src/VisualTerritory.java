import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

public class VisualTerritory implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static enum TERRITORIES { ALASKA, NORTH_WEST_TERRITORY, GREENLAND, ALBERIA, ONTARIO
		, EASTERN_CANADA, WESTERN_UNITED_STATES, EASTERN_UNITED_STATES, CENTRAL_AMERICA, VENEZUELA
		, PERU, BRAZIL, ARGENTINA, ICELAND, GREAT_BRITAIN, SCANDINAVIA, RUSIA, NOTHERN_EUROPE, SOUTHERN_EUROPE, WESTERN_EUROPE
		, NORTH_AFRICA, EGYPT, EAST_AFRICA, CENTRAL_AFRICA, SOUTH_AFRICA, MADAGASCAR, MIDDLE_EAST
		, AFGHANISTAN, URAL, SIBERIA, YAKUTSK, IRKUTSK, MONGOLA, CHINA, INDIA, SOUTHEAST_ASIA, JAPAN
		, KAMCHATKA, NEW_GUINESS, INDONESIA, WESTERN_AUSTRALIA, EASTERN_AUSTRALIA }
	public TERRITORIES territory;
	public ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
	public Coordinate mainCoordinate;
	public Color color;
	
	public VisualTerritory(TERRITORIES terr, Color c) {
		color = c;
		territory = terr;
	}
	public void print() {
		System.out.println("territory: " + territory.toString() + "	color: " + color.toString());
		for(Coordinate c : coordinates)
			System.out.println("<" + c.x + ", " + c.y +  ">");
	}
	

}
