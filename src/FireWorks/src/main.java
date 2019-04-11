import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class main {
	static int minSpeed = 8;
	static int maxSpeed = 16;
	public static void main(String[] args) {
		// list of fireworks
		ArrayList<RisingOne> risingOneList = new ArrayList<RisingOne>();
		ArrayList<ExplodeOne> explodeOneList = new ArrayList<ExplodeOne>();
		// frame
		JFrame frame = new JFrame("alper");
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true); // last
		// draw
		JPanel panel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				for(RisingOne ro : risingOneList) {
					ro.paint(g);
				}
				for(ExplodeOne ro : explodeOneList) {
					ro.paint(g);
				}
			}
		};
		panel.setBackground(Color.BLACK);
		// add firework timer
		Timer add = new Timer(125, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				risingOneList.add(new RisingOne((int)(Math.random() * 1920),
						(int)(Math.random() * 500) + 200,
						//500,
						(int)(Math.random() * (maxSpeed - minSpeed)) + minSpeed,
						new Color((int)(Math.random() * 255),(int)(Math.random() * 255),(int)(Math.random() * 255))));
			panel.repaint();
			}
		});
		add.start();
		// update firework timer
		Timer update = new Timer(15, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// rising one
				for(int i = 0; i < risingOneList.size(); i++) {
					if(!risingOneList.get(i).boom) {
						risingOneList.get(i).update();
					}
					else {
						for(int xMovement = -3; xMovement <= 3; xMovement++) {
							for(int yMovement = -10; yMovement <= -4; yMovement++) {
								explodeOneList.add(new ExplodeOne(risingOneList.get(i).xStart,
										risingOneList.get(i).yStart, 
										xMovement,
										yMovement,
										risingOneList.get(i).length / 2,
										risingOneList.get(i).c));
							}
						}
						risingOneList.remove(i);
					}
				}
				// explode one
				for(int i = 0; i < explodeOneList.size(); i++) {
					if(!explodeOneList.get(i).done) {
						explodeOneList.get(i).update();
					}
					else {
						explodeOneList.remove(i);
					}
				}
				panel.repaint();
			}
			
		});
		update.start();

		panel.setPreferredSize(new Dimension(1920, 1080));
		frame.add(panel);
		frame.setVisible(true);

	}

}
