import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class main {
	static int red = 250;
	static int green= 50;
	static int blue = 50;
	static int counterForSea = 0;
	// create rectangle map
	static int min = 9;
	static int jump = 12;  // 12
	static Coordinate list[][] = new Coordinate[1920 / jump][1080 / jump];
	static int numbers[] = new int[4];
	static int diff = 40;
	public static void main(String[] args) throws IOException{
		
		numbers[0] = 1;
		numbers[1] = 2;
		numbers[2] = 3;
		numbers[3] = 4;
		// frame
		JFrame frame = new JFrame("alper");
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true); // last
		// file
		File file = new File("maps//Risk.png");
		BufferedImage image = ImageIO.read(file);

		// initialize coordinate array
		for (int i = 0; i < 1920; i+= jump) {
			for(int j = 0; j< 1080; j+= jump) {
				list[i / jump][j / jump] = new Coordinate(i, j);
			}
		}
		// draw
		JPanel panel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				for (int i = 0; i < 1920; i+= jump) {
					for(int j = 0; j< 1080; j+= jump) {
						g.setColor(new Color( red,green,blue));
						g.fillRect(list[i / jump][j / jump].x, list[i / jump][j / jump].y,
								list[i / jump][j / jump].border, list[i / jump][j / jump].border);
					}
				}
			}
		};
		// timer for animation
		
		Timer ani = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				if(Math.random() * 15 < 1) {
					numbers[0] = (int)(Math.random() * 10);
					numbers[1] = (int)(Math.random() * 10);
					numbers[2] = (int)(Math.random() * 10);
					numbers[3] = (int)(Math.random() * 10);
				}
				//		list[(int)(Math.random() *(1920 / jump))][(int)(Math.random() *(1080 / jump))].big = true;
				for (int i = 0; i < 1920; i+= jump) {
					for(int j = 0; j< 1080; j+= jump) {
						if(list[i / jump][j / jump].big) {
							if(list[i / jump][j / jump].border < jump) {
								list[i / jump][j / jump].border++;
							} else if(list[i / jump][j / jump].border == jump) {
								//	list[i / jump][j / jump].border--;
								//	list[i / jump][j / jump].big = false;
							}
						}else {
							if(list[i / jump][j / jump].border > min) {
								list[i / jump][j / jump].border--;
							} else if(list[i / jump][j / jump].border == min) {
								//	list[i / jump][j / jump].big = true;
							}
						}
					}
				}
				panel.repaint();
			}
		});
		ani.start();
		// timer for numberaAnimation
		Timer numberaAnimation = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setNumbersToPanel();
			}
		});
		numberaAnimation.start();
		panel.setPreferredSize(new Dimension(1920, 1080));
		panel.setBackground(Color.BLACK);
		frame.add(panel);
		frame.setVisible(true);
	}
	public static void makeAreaB(Coordinate topLeftCoordinate, int lenth) {
		for(int i = topLeftCoordinate.x; i < topLeftCoordinate.x + lenth; i++) {
			for(int j = topLeftCoordinate.y; j < topLeftCoordinate.y + lenth; j++) {
				if(list[i][j].big != true)
					list[i][j].big = true;
			}
		}
	}
	public static void makeAreaS(Coordinate topLeftCoordinate, int lenth) {
		for(int i = topLeftCoordinate.x; i < topLeftCoordinate.x + lenth; i++) {
			for(int j = topLeftCoordinate.y; j < topLeftCoordinate.y + lenth; j++) {
				if(list[i][j].big != false)
					list[i][j].big = false;
			}
		}
	}
	public static void setNumbersToPanel() {
		for(int i = 0; i < 4; i++) {
			if(i == 0 || i == 1) {
				if(0 == numbers[i]) {
					makeAreaB(new Coordinate(23 + i * 20, 8), 5);
					makeAreaB(new Coordinate(28 + i * 20, 8), 5);
					makeAreaB(new Coordinate(33 + i * 20, 8), 5);

					makeAreaB(new Coordinate(23 + i * 20, 13), 5);
					makeAreaS(new Coordinate(28 + i * 20, 13), 5);
					makeAreaB(new Coordinate(33 + i * 20, 13), 5);

					makeAreaB(new Coordinate(23 + i * 20, 18), 5);
					makeAreaS(new Coordinate(28 + i * 20, 18), 5);
					makeAreaB(new Coordinate(33 + i * 20, 18), 5);

					makeAreaB(new Coordinate(23 + i * 20, 23), 5);
					makeAreaS(new Coordinate(28 + i * 20, 23), 5);
					makeAreaB(new Coordinate(33 + i * 20, 23), 5);

					makeAreaB(new Coordinate(23 + i * 20, 28), 5);
					makeAreaB(new Coordinate(28 + i * 20, 28), 5);
					makeAreaB(new Coordinate(33 + i * 20, 28), 5);
				}
				if(1 == numbers[i]) {
					makeAreaB(new Coordinate(23 + i * 20, 8), 5);
					makeAreaB(new Coordinate(28 + i * 20, 8), 5);
					makeAreaS(new Coordinate(33 + i * 20, 8), 5);

					makeAreaS(new Coordinate(23 + i * 20, 13), 5);
					makeAreaB(new Coordinate(28 + i * 20, 13), 5);
					makeAreaS(new Coordinate(33 + i * 20, 13), 5);

					makeAreaS(new Coordinate(23 + i * 20, 18), 5);
					makeAreaB(new Coordinate(28 + i * 20, 18), 5);
					makeAreaS(new Coordinate(33 + i * 20, 18), 5);

					makeAreaS(new Coordinate(23 + i * 20, 23), 5);
					makeAreaB(new Coordinate(28 + i * 20, 23), 5);
					makeAreaS(new Coordinate(33 + i * 20, 23), 5);

					makeAreaB(new Coordinate(23 + i * 20, 28), 5);
					makeAreaB(new Coordinate(28 + i * 20, 28), 5);
					makeAreaB(new Coordinate(33 + i * 20, 28), 5);
				}
				if(2 == numbers[i]) {
					makeAreaB(new Coordinate(23 + i * 20, 8), 5);
					makeAreaB(new Coordinate(28 + i * 20, 8), 5);
					makeAreaB(new Coordinate(33 + i * 20, 8), 5);

					makeAreaS(new Coordinate(23 + i * 20, 13), 5);
					makeAreaS(new Coordinate(28 + i * 20, 13), 5);
					makeAreaB(new Coordinate(33 + i * 20, 13), 5);

					makeAreaB(new Coordinate(23 + i * 20, 18), 5);
					makeAreaB(new Coordinate(28 + i * 20, 18), 5);
					makeAreaB(new Coordinate(33 + i * 20, 18), 5);

					makeAreaB(new Coordinate(23 + i * 20, 23), 5);
					makeAreaS(new Coordinate(28 + i * 20, 23), 5);
					makeAreaS(new Coordinate(33 + i * 20, 23), 5);

					makeAreaB(new Coordinate(23 + i * 20, 28), 5);
					makeAreaB(new Coordinate(28 + i * 20, 28), 5);
					makeAreaB(new Coordinate(33 + i * 20, 28), 5);
				}
				if(3 == numbers[i]) {
					makeAreaB(new Coordinate(23 + i * 20, 8), 5);
					makeAreaB(new Coordinate(28 + i * 20, 8), 5);
					makeAreaB(new Coordinate(33 + i * 20, 8), 5);

					makeAreaS(new Coordinate(23 + i * 20, 13), 5);
					makeAreaS(new Coordinate(28 + i * 20, 13), 5);
					makeAreaB(new Coordinate(33 + i * 20, 13), 5);

					makeAreaB(new Coordinate(23 + i * 20, 18), 5);
					makeAreaB(new Coordinate(28 + i * 20, 18), 5);
					makeAreaB(new Coordinate(33 + i * 20, 18), 5);

					makeAreaS(new Coordinate(23 + i * 20, 23), 5);
					makeAreaS(new Coordinate(28 + i * 20, 23), 5);
					makeAreaB(new Coordinate(33 + i * 20, 23), 5);

					makeAreaB(new Coordinate(23 + i * 20, 28), 5);
					makeAreaB(new Coordinate(28 + i * 20, 28), 5);
					makeAreaB(new Coordinate(33 + i * 20, 28), 5);
				}
				if(4 == numbers[i]) {
					makeAreaB(new Coordinate(23 + i * 20, 8), 5);
					makeAreaS(new Coordinate(28 + i * 20, 8), 5);
					makeAreaB(new Coordinate(33 + i * 20, 8), 5);

					makeAreaB(new Coordinate(23 + i * 20, 13), 5);
					makeAreaS(new Coordinate(28 + i * 20, 13), 5);
					makeAreaB(new Coordinate(33 + i * 20, 13), 5);

					makeAreaB(new Coordinate(23 + i * 20, 18), 5);
					makeAreaB(new Coordinate(28 + i * 20, 18), 5);
					makeAreaB(new Coordinate(33 + i * 20, 18), 5);

					makeAreaS(new Coordinate(23 + i * 20, 23), 5);
					makeAreaS(new Coordinate(28 + i * 20, 23), 5);
					makeAreaB(new Coordinate(33 + i * 20, 23), 5);

					makeAreaS(new Coordinate(23 + i * 20, 28), 5);
					makeAreaS(new Coordinate(28 + i * 20, 28), 5);
					makeAreaB(new Coordinate(33 + i * 20, 28), 5);
				}
				if(5 == numbers[i]) {
					makeAreaB(new Coordinate(23 + i * 20, 8), 5);
					makeAreaB(new Coordinate(28 + i * 20, 8), 5);
					makeAreaB(new Coordinate(33 + i * 20, 8), 5);

					makeAreaB(new Coordinate(23 + i * 20, 13), 5);
					makeAreaS(new Coordinate(28 + i * 20, 13), 5);
					makeAreaS(new Coordinate(33 + i * 20, 13), 5);

					makeAreaB(new Coordinate(23 + i * 20, 18), 5);
					makeAreaB(new Coordinate(28 + i * 20, 18), 5);
					makeAreaB(new Coordinate(33 + i * 20, 18), 5);

					makeAreaS(new Coordinate(23 + i * 20, 23), 5);
					makeAreaS(new Coordinate(28 + i * 20, 23), 5);
					makeAreaB(new Coordinate(33 + i * 20, 23), 5);

					makeAreaB(new Coordinate(23 + i * 20, 28), 5);
					makeAreaB(new Coordinate(28 + i * 20, 28), 5);
					makeAreaB(new Coordinate(33 + i * 20, 28), 5);
				}
				if(6 == numbers[i]) {
					makeAreaB(new Coordinate(23 + i * 20, 8), 5);
					makeAreaB(new Coordinate(28 + i * 20, 8), 5);
					makeAreaB(new Coordinate(33 + i * 20, 8), 5);

					makeAreaB(new Coordinate(23 + i * 20, 13), 5);
					makeAreaS(new Coordinate(28 + i * 20, 13), 5);
					makeAreaS(new Coordinate(33 + i * 20, 13), 5);

					makeAreaB(new Coordinate(23 + i * 20, 18), 5);
					makeAreaB(new Coordinate(28 + i * 20, 18), 5);
					makeAreaB(new Coordinate(33 + i * 20, 18), 5);

					makeAreaB(new Coordinate(23 + i * 20, 23), 5);
					makeAreaS(new Coordinate(28 + i * 20, 23), 5);
					makeAreaB(new Coordinate(33 + i * 20, 23), 5);

					makeAreaB(new Coordinate(23 + i * 20, 28), 5);
					makeAreaB(new Coordinate(28 + i * 20, 28), 5);
					makeAreaB(new Coordinate(33 + i * 20, 28), 5);
				}
				if(7 == numbers[i]) {
					makeAreaB(new Coordinate(23 + i * 20, 8), 5);
					makeAreaB(new Coordinate(28 + i * 20, 8), 5);
					makeAreaB(new Coordinate(33 + i * 20, 8), 5);

					makeAreaS(new Coordinate(23 + i * 20, 13), 5);
					makeAreaS(new Coordinate(28 + i * 20, 13), 5);
					makeAreaB(new Coordinate(33 + i * 20, 13), 5);

					makeAreaS(new Coordinate(23 + i * 20, 18), 5);
					makeAreaS(new Coordinate(28 + i * 20, 18), 5);
					makeAreaB(new Coordinate(33 + i * 20, 18), 5);

					makeAreaS(new Coordinate(23 + i * 20, 23), 5);
					makeAreaS(new Coordinate(28 + i * 20, 23), 5);
					makeAreaB(new Coordinate(33 + i * 20, 23), 5);

					makeAreaS(new Coordinate(23 + i * 20, 28), 5);
					makeAreaS(new Coordinate(28 + i * 20, 28), 5);
					makeAreaB(new Coordinate(33 + i * 20, 28), 5);
				}
				if(8 == numbers[i]) {
					makeAreaB(new Coordinate(23 + i * 20, 8), 5);
					makeAreaB(new Coordinate(28 + i * 20, 8), 5);
					makeAreaB(new Coordinate(33 + i * 20, 8), 5);

					makeAreaB(new Coordinate(23 + i * 20, 13), 5);
					makeAreaS(new Coordinate(28 + i * 20, 13), 5);
					makeAreaB(new Coordinate(33 + i * 20, 13), 5);

					makeAreaB(new Coordinate(23 + i * 20, 18), 5);
					makeAreaB(new Coordinate(28 + i * 20, 18), 5);
					makeAreaB(new Coordinate(33 + i * 20, 18), 5);

					makeAreaB(new Coordinate(23 + i * 20, 23), 5);
					makeAreaS(new Coordinate(28 + i * 20, 23), 5);
					makeAreaB(new Coordinate(33 + i * 20, 23), 5);

					makeAreaB(new Coordinate(23 + i * 20, 28), 5);
					makeAreaB(new Coordinate(28 + i * 20, 28), 5);
					makeAreaB(new Coordinate(33 + i * 20, 28), 5);
				}
				if(9 == numbers[i]) {
					makeAreaB(new Coordinate(23 + i * 20, 8), 5);
					makeAreaB(new Coordinate(28 + i * 20, 8), 5);
					makeAreaB(new Coordinate(33 + i * 20, 8), 5);

					makeAreaB(new Coordinate(23 + i * 20, 13), 5);
					makeAreaS(new Coordinate(28 + i * 20, 13), 5);
					makeAreaB(new Coordinate(33 + i * 20, 13), 5);

					makeAreaB(new Coordinate(23 + i * 20, 18), 5);
					makeAreaB(new Coordinate(28 + i * 20, 18), 5);
					makeAreaB(new Coordinate(33 + i * 20, 18), 5);

					makeAreaS(new Coordinate(23 + i * 20, 23), 5);
					makeAreaS(new Coordinate(28 + i * 20, 23), 5);
					makeAreaB(new Coordinate(33 + i * 20, 23), 5);

					makeAreaB(new Coordinate(23 + i * 20, 28), 5);
					makeAreaB(new Coordinate(28 + i * 20, 28), 5);
					makeAreaB(new Coordinate(33 + i * 20, 28), 5);
				}


			} else if(i == 2 || i == 3) {
				if(0 == numbers[i]) {
					makeAreaB(new Coordinate(23 + i * 20 + diff, 8), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 8), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 8), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 13), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 13), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 13), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 18), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 18), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 18), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 23), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 23), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 23), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 28), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 28), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 28), 5);
				}
				if(1 == numbers[i]) {
					makeAreaB(new Coordinate(23 + i * 20 + diff, 8), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 8), 5);
					makeAreaS(new Coordinate(33 + i * 20 + diff, 8), 5);

					makeAreaS(new Coordinate(23 + i * 20 + diff, 13), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 13), 5);
					makeAreaS(new Coordinate(33 + i * 20 + diff, 13), 5);

					makeAreaS(new Coordinate(23 + i * 20 + diff, 18), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 18), 5);
					makeAreaS(new Coordinate(33 + i * 20 + diff, 18), 5);

					makeAreaS(new Coordinate(23 + i * 20 + diff, 23), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 23), 5);
					makeAreaS(new Coordinate(33 + i * 20 + diff, 23), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 28), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 28), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 28), 5);
				}
				if(2 == numbers[i]) {
					makeAreaB(new Coordinate(23 + i * 20 + diff, 8), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 8), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 8), 5);

					makeAreaS(new Coordinate(23 + i * 20 + diff, 13), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 13), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 13), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 18), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 18), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 18), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 23), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 23), 5);
					makeAreaS(new Coordinate(33 + i * 20 + diff, 23), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 28), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 28), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 28), 5);
				}
				if(3 == numbers[i]) {
					makeAreaB(new Coordinate(23 + i * 20 + diff, 8), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 8), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 8), 5);

					makeAreaS(new Coordinate(23 + i * 20 + diff, 13), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 13), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 13), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 18), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 18), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 18), 5);

					makeAreaS(new Coordinate(23 + i * 20 + diff, 23), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 23), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 23), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 28), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 28), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 28), 5);
				}
				if(4 == numbers[i]) {
					makeAreaB(new Coordinate(23 + i * 20 + diff, 8), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 8), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 8), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 13), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 13), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 13), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 18), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 18), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 18), 5);

					makeAreaS(new Coordinate(23 + i * 20 + diff, 23), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 23), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 23), 5);

					makeAreaS(new Coordinate(23 + i * 20 + diff, 28), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 28), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 28), 5);
				}
				if(5 == numbers[i]) {
					makeAreaB(new Coordinate(23 + i * 20 + diff, 8), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 8), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 8), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 13), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 13), 5);
					makeAreaS(new Coordinate(33 + i * 20 + diff, 13), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 18), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 18), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 18), 5);

					makeAreaS(new Coordinate(23 + i * 20 + diff, 23), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 23), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 23), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 28), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 28), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 28), 5);
				}
				if(6 == numbers[i]) {
					makeAreaB(new Coordinate(23 + i * 20 + diff, 8), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 8), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 8), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 13), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 13), 5);
					makeAreaS(new Coordinate(33 + i * 20 + diff, 13), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 18), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 18), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 18), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 23), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 23), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 23), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 28), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 28), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 28), 5);
				}
				if(7 == numbers[i]) {
					makeAreaB(new Coordinate(23 + i * 20 + diff, 8), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 8), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 8), 5);

					makeAreaS(new Coordinate(23 + i * 20 + diff, 13), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 13), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 13), 5);

					makeAreaS(new Coordinate(23 + i * 20 + diff, 18), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 18), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 18), 5);

					makeAreaS(new Coordinate(23 + i * 20 + diff, 23), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 23), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 23), 5);

					makeAreaS(new Coordinate(23 + i * 20 + diff, 28), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 28), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 28), 5);
				}
				if(8 == numbers[i]) {
					makeAreaB(new Coordinate(23 + i * 20 + diff, 8), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 8), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 8), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 13), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 13), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 13), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 18), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 18), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 18), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 23), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 23), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 23), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 28), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 28), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 28), 5);
				}
				if(9 == numbers[i]) {
					makeAreaB(new Coordinate(23 + i * 20 + diff, 8), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 8), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 8), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 13), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 13), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 13), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 18), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 18), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 18), 5);

					makeAreaS(new Coordinate(23 + i * 20 + diff, 23), 5);
					makeAreaS(new Coordinate(28 + i * 20 + diff, 23), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 23), 5);

					makeAreaB(new Coordinate(23 + i * 20 + diff, 28), 5);
					makeAreaB(new Coordinate(28 + i * 20 + diff, 28), 5);
					makeAreaB(new Coordinate(33 + i * 20 + diff, 28), 5);
				}



			}

		}
	}
}