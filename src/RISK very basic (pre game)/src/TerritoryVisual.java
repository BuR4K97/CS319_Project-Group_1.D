import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TerritoryVisual {
	// constants

	// parameters
	private int xCoor;
	private int yCoor;
	private int width;
	private int height;
	private Territory territory;
	private boolean mouseEntered;
	private boolean selected;
	// constructors
	public TerritoryVisual(int xCoor, int yCoor, int width, int height) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.width = width;
		this.height = height;
		this.territory = new Territory();
		mouseEntered = false;
		selected = false;
	}
	// methods
	void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(20));
		g.setColor(Color.BLACK);
		g.drawRect(xCoor, yCoor, width, height);
		if(territory.getPlayer() != null)
			g.setColor(territory.getPlayer().getColor());
		else
			g.setColor(Color.white);
		g.fillRect(xCoor, yCoor, width, height);
		g.setColor(Color.black);
		g.setFont(new Font("TimesRoman", Font.BOLD, 40));
		g.drawString("" + territory.getUnitNumber(), xCoor + 80, yCoor + 50);
		if(mouseEntered) {
			g.setColor(new Color(0,0,0,100));
			g.fillRect(xCoor, yCoor, width, height);
		}
		if(selected) {
			g.drawString("selected", xCoor + 5, yCoor + height - 15);
		}
	}
	public void setTerritory(Territory t) {
		this.territory = t;
	}
	public Territory getTerritory() {
		return territory;
	}
	public void setMouseEntered(boolean mouseEntered) {
		this.mouseEntered = mouseEntered;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public boolean getSelected() {
		return selected;
	}
	public Rectangle getRectangle() {
		return new Rectangle(xCoor, yCoor, width, height);
	}
}
