package UIComponents.RectAnimation;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class RectAnimation {
	// initialize rectangle array
	ArrayList<MyRectangle> list = new ArrayList<MyRectangle>();
	// timer
	int movement = 4;
	Timer moveRectTimer;
	public RectAnimation() {
		// timer
		moveRectTimer = new Timer(30, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if((int)(Math.random() * 100) > 90) {
					int a = (int)(Math.random() * 4) + 1;
					Color c = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255), 70);
					if(a == 1) { // left of screen
						list.add(new MyRectangle(-10, (int)(Math.random() * 1080),c, (int)(Math.random()*movement)+movement, 0));

					} else if(a == 2) { // bottom of screen
						list.add(new MyRectangle((int)(Math.random() * 1920), 1080 ,c, 0, -movement-(int)(Math.random()*movement)));
					}else if(a == 3) { // right of screen
						list.add(new MyRectangle(1920, (int)(Math.random() * 1080),c, -movement-(int)(Math.random()*movement), 0));
					}else { // top of screen
						list.add(new MyRectangle((int)(Math.random() * 1920), -10 ,c, 0, movement+(int)(Math.random()*movement)));
					}
				}
				for(int i = 0; i < list.size(); i++) {
					list.get(i).move();
					if(!list.get(i).isInScreen()) {
						list.remove(i);
					}
				}
			}
		});
		moveRectTimer.start();
	}
	public void paint(Graphics g) {
		for(int i = 0; i < list.size(); i++)
			list.get(i).print(g);
	}
}
