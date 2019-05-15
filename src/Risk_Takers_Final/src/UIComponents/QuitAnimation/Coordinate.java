package UIComponents.QuitAnimation;
import java.awt.Rectangle;

public class Coordinate {
	public int y;
	public int x;
	int min = -2, max = 2;
	public boolean move = false;
	public int opacity = 255;
	int xMove = (int)(Math.random()* (max - min)) + min, yMove = -((int)(Math.random()* (max - min)) + min);
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
