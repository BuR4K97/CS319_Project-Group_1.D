package UIComponents.QuitAnimation;
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

import UIComponents.MenuPanel;
import UIComponents.PixelMapWithSea.Pixel;

public class QuitAnimation {
	Timer disappear, startMoving;
	ArrayList<Coordinate> list;
	int opacityChanger = 3;
	Color c = new Color((int)(Math.random()* 100) + 150, (int)(Math.random()* 100) + 150, (int)(Math.random()* 100) + 150);
	double leftToRight = 1920;
	double leftTorightMovement = 20;
	public QuitAnimation() {
		list = new ArrayList<Coordinate>();
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
					System.exit(1);
				}
			}
		});
		startMoving.start();
	}
	
	private void earthList() {
		for(int i = 0; i < Pixel.list.size(); i++) {
			list.add(new Coordinate(Pixel.list.get(i).x, Pixel.list.get(i).y));
		}
	}
	public void paint(Graphics g){
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).opacity > 0) {
				g.setColor(new Color(
						Math.abs(MenuPanel.mapAnimation.red - list.get(i).x/ 12) % 255, 
						Math.abs(MenuPanel.mapAnimation.green - list.get(i).x/ 12) % 255, 
						Math.abs(MenuPanel.mapAnimation.blue - list.get(i).x/ 12) % 255,
						list.get(i).opacity));
				g.fillRect(list.get(i).x, list.get(i).y, 9, 9); // for earth
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
}
