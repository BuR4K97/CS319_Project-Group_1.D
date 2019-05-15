package UIComponents.Notifications;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;


public class Box {
	Timer disappear, startMoving;
	ArrayList<Coordinate> list, tempList;
	int biggestX, x, y;
	double leftToRight;
	int opacityChanger = NotificationArea.opacityChanger;
	double leftTorightMovement = NotificationArea.leftTorightMovement;
	notificationString vs;
	String string;
	boolean done = false;
	public Box(int x, int y, String s) {
		this.x = x;
		this.y = y;
		string = s;
		list = new ArrayList<Coordinate>();
		tempList = new ArrayList<Coordinate>();
		earthList();
		disappear = new Timer(16, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		disappear.start();
		startMoving = new Timer(16, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeLeftToRightMove();
				if(list.size() == 0) {
					startMoving.stop();
					disappear.stop();
					done = true;
				}
			}
		});
		startMoving.start();
	}
	private void earthList() {
		vs = new notificationString(x, y, NotificationArea.border, string, "notification");
			for (int i = 0; i < vs.list.size(); i++) {
						list.add(new Coordinate(vs.list.get(i).x, vs.list.get(i).y));
						tempList.add(new Coordinate(vs.list.get(i).x, vs.list.get(i).y));
			}
		biggestX = vs.list.get(vs.list.size()-1).x;
		vs = null;
		leftToRight = biggestX + 20;
	}
	public void paint(Graphics g){
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).opacity > 0) {
				g.setColor(new Color(255, 255, 255, list.get(i).opacity));
				g.fillRect(list.get(i).x, list.get(i).y, NotificationArea.border *7/7, NotificationArea.border *7/7); // for earth
			}
		}
	}
	private void update() {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).move) {
				list.get(i).x += list.get(i).xMove;
				list.get(i).y += list.get(i).yMove;
				list.get(i).opacity -= opacityChanger;
			}
			if(list.get(i).opacity < 1)
				list.remove(i);
		}
	}
	private void makeLeftToRightMove() {
		leftToRight -= leftTorightMovement;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).x > leftToRight)
				list.get(i).move = true;
		}
	}
	public boolean getDone() {
		return done;
	}
}
