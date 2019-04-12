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
		final JPanel panel = new JPanel() {
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
		
		Timer ani = new Timer(60, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				if(Math.random() * 15 < 1) {
					numbers[0] = (int)(Math.random() * 10);
					numbers[1] = (int)(Math.random() * 10);
					numbers[2] = (int)(Math.random() * 10);
					numbers[3] = (int)(Math.random() * 10);
				}
			}
		});
		ani.start();
		// timer for numberaAnimation
		Timer numberaAnimation = new Timer(60, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawNumberOn(new Coordinate(5, 5), 10, 5);
				panel.repaint();
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
				if(list[i][j].border < 12)
					list[i][j].border++;
			}
		}
	}
	public static void makeAreaS(Coordinate topLeftCoordinate, int lenth) {
		for(int i = topLeftCoordinate.x; i < topLeftCoordinate.x + lenth; i++) {
			for(int j = topLeftCoordinate.y; j < topLeftCoordinate.y + lenth; j++) {
				if(list[i][j].border > 8)
					list[i][j].border--;
			}
		}
	}
	public static void drawNumberOn(Coordinate topLeft, int blockLength, int number) {
		if(number == 0) {
			makeAreaB(new Coordinate(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		if(number == 1) {
			makeAreaB(new Coordinate(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaS(new Coordinate(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaS(new Coordinate(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaS(new Coordinate(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		if(number == 2) {
			makeAreaB(new Coordinate(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaS(new Coordinate(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		if(number == 3) {
			makeAreaB(new Coordinate(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaS(new Coordinate(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaS(new Coordinate(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		if(number == 4) {
			makeAreaB(new Coordinate(topLeft.x, topLeft.y), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaS(new Coordinate(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaS(new Coordinate(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		if(number == 5) {
			makeAreaB(new Coordinate(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaS(new Coordinate(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		if(number == 6) {
			makeAreaB(new Coordinate(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		if(number == 7) {
			makeAreaB(new Coordinate(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaS(new Coordinate(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaS(new Coordinate(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaS(new Coordinate(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaS(new Coordinate(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		if(number == 8) {
			makeAreaB(new Coordinate(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		if(number == 9) {
			makeAreaB(new Coordinate(topLeft.x, topLeft.y), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 2), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 2), blockLength);

			makeAreaS(new Coordinate(topLeft.x, topLeft.y + blockLength * 3), blockLength);
			makeAreaS(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 3), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 3), blockLength);

			makeAreaB(new Coordinate(topLeft.x, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength, topLeft.y + blockLength * 4), blockLength);
			makeAreaB(new Coordinate(topLeft.x + blockLength * 2, topLeft.y + blockLength * 4), blockLength);
		}
		
	}
}