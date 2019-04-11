import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Ani {
	public static void main(String[] args) {
		// initialize rectangle array
		ArrayList<MyRectangle> list = new ArrayList<MyRectangle>();
		// frame
		JFrame frame = new JFrame("alper");

		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true); // last

		// panel
		JPanel panel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				for(int i = 0; i < list.size(); i++) {
					list.get(i).print(g);
				}
			}

		};
		panel.setVisible(true);
		//panel.setBackground(Color.WHITE);
		frame.add(panel);
		// timer
		int movement = 5;
		Timer addRectTimer = new Timer(40, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int a = (int)(Math.random() * 4) + 1;
				Color c = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
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
		});
		addRectTimer.start();
		// timer
		Timer moveRectTimer = new Timer(5, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < list.size(); i++) {
					list.get(i).move();
					if(!list.get(i).isInScreen()) {
						list.remove(i);
					}
				}
				panel.repaint();
			}
		});
		moveRectTimer.start();




	}
}
