package HelperTools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class PixelString{
	final int five = 2;
	int movement = five;
	boolean end = false;
	public int x, xNew;
	public int y, yNew;
	public int minLength;
	public int maxLength;
	public String string;
	public ArrayList<Rect> list = new ArrayList<Rect>();
	Color color;
	Timer moveToCorrect;
	public class Rect{
		public int x, y, border;
		public int xCorrect, yCorrect;
		public int xFirst, yFirst;
		public Rect(int x, int y) {			
			border = minLength;
			this.x = x;
			this.y = y;
			xCorrect = x;
			yCorrect = y;
			xFirst = x;
			yFirst = y;
		}
	}
	public PixelString(int x, int y, int maxLength, String string, Color c) { // width = maxlength*5
		this.maxLength = maxLength;
		this.minLength = (int)(maxLength * 7 / 7);
		this.x = x;
		this.y = y;
		this.string = string;
		for(int i = 0; i < string.length(); i++)
			drawNumberOn(new Rect(x + (i* maxLength* 4), y), maxLength, string.toUpperCase().charAt(i));
		moveToCorrect = new Timer(16, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean done = true;
				
				for(int i = 0; i < list.size(); i++) {
					movement = (int)(Math.random() * five) + five;
					
						if(Math.abs(list.get(i).x - list.get(i).xCorrect) <= movement) {
							list.get(i).x = list.get(i).xCorrect;
						}else if(list.get(i).x < list.get(i).xCorrect) {
							list.get(i).x += movement;
							done = false;
						} else if(list.get(i).x > list.get(i).xCorrect) {
							list.get(i).x -= movement;
							done = false;
						}
					
						if(Math.abs(list.get(i).y - list.get(i).yCorrect) <= movement) {
							list.get(i).y = list.get(i).yCorrect;
						}else if(list.get(i).y < list.get(i).yCorrect) {
							list.get(i).y+=movement;
							done = false;
						} else if(list.get(i).y > list.get(i).yCorrect) {
							list.get(i).y-=movement;
							done = false;
						}
					
				}
				if(done)
					moveToCorrect.stop();
			}
		});
	}
	public void paint(Graphics g) {
		g.setColor(color);
		for(int i = 0; i < list.size(); i++) {

			//g.drawRect(x, y, maxLength * string.length() * 4 , maxLength*5);
			g.fillRect(list.get(i).x, list.get(i).y, list.get(i).border, list.get(i).border);
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
	public void setNewCoordinate(int x, int y) {
		xNew = x;
		yNew = y;
		for(int i = 0; i < list.size(); i++) {
			list.get(i).xCorrect = list.get(i).xFirst + xNew - this.x;
			list.get(i).yCorrect = list.get(i).yFirst + yNew - this.y;
		}
		moveToCorrect.start();
	}
}

