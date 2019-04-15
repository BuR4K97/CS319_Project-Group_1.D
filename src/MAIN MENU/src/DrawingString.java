import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class DrawingString{
	boolean end = false;
	public int x;
	public int y;
	public int minLength;
	public int maxLength;
	public String string;
	public ArrayList<Rect> list = new ArrayList<Rect>();
	int startAnimationDelay = 15;
	int startAnimationSpeed = 25;
	int endAnimationDelay = 15;
	int endAnimationSpeed = 25;
	Timer startAnimation, endAnimation;
	Timer mexicoWave;
	int mexicoWaveCoordinate;
	int mexicoWaveDelay = 15;
	int mexicoWaveLength;
	boolean mouseEntered = false;
	public class Rect{
		public int y;
		public int x;
		public int xCurrent;
		public int yEnd;
		public int border;
		public boolean startAnimation = false;
		public boolean endAnimation = false;
		public Rect(int xx, int yy) {			
			border = minLength;
			this.x = xx;
			//xCurrent = -minLength;
			xCurrent = (int)(Math.random() * 1920 / 2);
			this.y = yy;
			yEnd = 1080;
		}
	}
	public DrawingString(int x, int y, int maxLength, String string) { // width = maxlength*5
		this.maxLength = maxLength;
		this.minLength = (int)(maxLength * 6 / 7);
		mexicoWaveLength = maxLength - minLength;
		this.x = x;
		this.y = y;
		this.string = string;
		mexicoWaveCoordinate = x;
		for(int i = 0; i < string.length(); i++)
			drawNumberOn(new Rect(x + (i* maxLength* 4), y), maxLength, string.toUpperCase().charAt(i));
		endAnimation = new Timer(endAnimationDelay, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean done = true;
				for(int i = 0; i < list.size(); i++) {
					
					if(Math.random() * 4 < 1)
						list.get(i).endAnimation = true;
					if(Math.abs(list.get(i).y - list.get(i).yEnd) <= endAnimationSpeed && list.get(i).endAnimation) {
						list.get(i).y = list.get(i).yEnd;
						list.get(i).endAnimation = false;
					}
					else if(list.get(i).y < list.get(i).yEnd && list.get(i).endAnimation) {

						list.get(i).y += endAnimationSpeed;
						done = false;
					}
					else if(list.get(i).y > list.get(i).yEnd && list.get(i).endAnimation) {
						list.get(i).y -= endAnimationSpeed;
						done = false;
					}
				}
				if(done) {
					endAnimation.stop();
					end = true;
				}
			}
		});
		//endAnimation.start();
		startAnimation = new Timer(startAnimationDelay, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean done = true;
				for(int i = 0; i < list.size(); i++) {
					if(Math.random() * 4 < 1)
						list.get(i).startAnimation = true;
					if(Math.abs(list.get(i).x - list.get(i).xCurrent) <= startAnimationSpeed && list.get(i).startAnimation) {
						list.get(i).xCurrent = list.get(i).x;
						list.get(i).startAnimation = false;
					}
					else if(list.get(i).x < list.get(i).xCurrent && list.get(i).startAnimation) {
						list.get(i).xCurrent -= startAnimationSpeed;
						done = false;
					}
					else if(list.get(i).x > list.get(i).xCurrent && list.get(i).startAnimation) {
						list.get(i).xCurrent += startAnimationSpeed;
						done = false;
					}
				}
				if(done)
					startAnimation.stop();
			}
		});
		startAnimation.start();
		mexicoWave = new Timer(mexicoWaveDelay, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				if(mexicoWaveCoordinate < (x + string.length() * 4 * maxLength) + mexicoWaveLength)
					mexicoWaveCoordinate+=mexicoWaveLength;
				else
					mexicoWaveCoordinate =-mexicoWaveLength*2+ x;
				 */
				if(mexicoWaveCoordinate < (1920) + mexicoWaveLength * maxLength)
					mexicoWaveCoordinate += mexicoWaveLength * 3;
				else
					mexicoWaveCoordinate = - mexicoWaveLength * 3;
				for(int i = 0; i < list.size(); i++) {
					for(int j = 0; j <= mexicoWaveLength;j++) {
						if(Math.abs(list.get(i).x - mexicoWaveCoordinate) <= maxLength * (j+1) && Math.abs(list.get(i).x - mexicoWaveCoordinate) > maxLength * (j)) {
							list.get(i).border = maxLength - j;
							break;
						} 
					}
				}
			}

		});
		mexicoWave.start();
	}
	public void paint(Graphics g) {

		for(int i = 0; i < list.size(); i++) {
			if(mouseEntered)
				g.setColor(Color.CYAN);
			else
				g.setColor(Color.LIGHT_GRAY);
			//g.drawRect(x, y, maxLength * string.length() * 4 , maxLength*5);
			g.fillRect(list.get(i).xCurrent, list.get(i).y, list.get(i).border, list.get(i).border);
		}
	}
	public  void makeAreaB(Rect topLeftCoordinate, int length) {
		list.add(new Rect(topLeftCoordinate.x, topLeftCoordinate.y));
	}
	public  void makeAreaS(Rect topLeftCoordinate, int lenth) {}
	public  void drawNumberOn(Rect topLeft, int blockLength, char ch) {
		if(ch == ' ') {
			makeAreaS(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'Z') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'Y') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'X') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'W') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'V') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'U') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'T') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'Q') {
			makeAreaS(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'S') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'R') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'P') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'O') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'N') {
			makeAreaS(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'M') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'L') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'K') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'J') {
			makeAreaS(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'I') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'I') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'H') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'G') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'F') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'E') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'D') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'C') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'B') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == 'A') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == '0') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == '1') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == '2') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == '3') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == '4') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == '5') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		if(ch == '6') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		if(ch == '7') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == '8') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		else if(ch == '9') {
			makeAreaB(new Rect(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaS(new Rect(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Rect(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Rect(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}


	}
	public Rectangle getRectangle() {
		return new Rectangle(x, y, maxLength * string.length() * 4 , maxLength*5);
	}
}
