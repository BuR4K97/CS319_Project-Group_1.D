package UIComponents.RectAnimation;
import java.awt.Color;
import java.awt.Graphics;

public class MyRectangle {
	// CONSTANTS
	private int BORDER = 12;
	// PROPERTIES
	private int x, y, xDir, yDir;

	private Color color;
	// CONSTRUSTORS
	public MyRectangle(int x, int y, Color c, int xDir, int yDir) {
		this.x = x;
		this.y = y;
		this.xDir = xDir;
		this.yDir = yDir;
		BORDER = (xDir + yDir)*5;
		color = c;
	}
	// METHODS
	public void print(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, BORDER, BORDER);
	}
	
	public void move() {
		x+=xDir;
		y+=yDir;
	}
	public boolean isInScreen() {
		return (x >= -10) && (1930 >= x + BORDER) && (y >= -10) && (1090 >= y + BORDER);
	}
}
