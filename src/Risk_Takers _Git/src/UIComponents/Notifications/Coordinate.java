package UIComponents.Notifications;
import java.awt.Rectangle;

public class Coordinate {
	public int y;
	public int x;
	int min = -5, max = -1;
	public boolean move = false;
	public int opacity = 255;
	int xMove = (int)(Math.random()* (max - min)) + min;
	int yMove = 0;//-((int)(Math.random()* (max - min)) + min);
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
