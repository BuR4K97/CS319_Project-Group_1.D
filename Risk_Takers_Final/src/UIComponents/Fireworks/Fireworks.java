package UIComponents.Fireworks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fireworks {
	int minSpeed = 8;
	int maxSpeed = 16;
	ArrayList<RisingOne> risingOneList = new ArrayList<RisingOne>();
	ArrayList<ExplodeOne> explodeOneList = new ArrayList<ExplodeOne>();
	public Fireworks() {
		// update firework timer
		Timer update = new Timer(15, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((int)(Math.random() * 100) > 95)
					risingOneList.add(new RisingOne((int)(Math.random() * 1920),
							(int)(Math.random() * 500) + 200,
							(int)(Math.random() * (maxSpeed - minSpeed)) + minSpeed,
							new Color((int)(Math.random() * 255),(int)(Math.random() * 255),(int)(Math.random() * 255))));
				// rising one
				for(int i = 0; i < risingOneList.size(); i++) {
					if(!risingOneList.get(i).boom) {
						risingOneList.get(i).update();
					}
					else {
						for(int xMovement = -3; xMovement <= 3; xMovement++) {
							for(int yMovement = -12; yMovement <= -5; yMovement++) {
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
			}

		});
		update.start();

	}
	public void paint(Graphics g) {
		for(RisingOne ro : risingOneList) {
			ro.paint(g);
		}
		for(ExplodeOne ro : explodeOneList) {
			ro.paint(g);
		}
	}

}

